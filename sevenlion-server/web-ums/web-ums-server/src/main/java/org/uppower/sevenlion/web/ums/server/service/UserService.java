package org.uppower.sevenlion.web.ums.server.service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.common.constant.RedisConst;
import org.uppower.sevenlion.common.enums.ScoreLogTypeEnum;
import org.uppower.sevenlion.common.exceptions.BaseException;
import org.uppower.sevenlion.common.model.user.UserInfo;
import org.uppower.sevenlion.common.utils.DateUtils;
import org.uppower.sevenlion.web.ums.common.model.entity.SignStrategyEntity;
import org.uppower.sevenlion.web.ums.common.model.entity.UserInfoEntity;
import org.uppower.sevenlion.web.ums.common.model.entity.UserSignLogEntity;
import org.uppower.sevenlion.web.ums.dao.mapper.UserInfoMapper;
import org.uppower.sevenlion.web.ums.dao.mapper.UserMapper;
import org.uppower.sevenlion.web.ums.dao.mapper.UserSignLogMapper;
import org.uppower.sevenlion.web.ums.server.manager.ScoreLogManager;
import org.uppower.sevenlion.web.ums.server.manager.SignManager;
import org.uppower.sevenlion.web.ums.server.manager.UserManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/20 10:35 下午
 */
@Service
@Slf4j
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserSignLogMapper userSignLogMapper;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserManager userManager;

    @Autowired
    private SignManager signManager;

    @Autowired
    private ScoreLogManager scoreLogManager;

    public static final String SIGN_REMARK ="签到收入";


    public Object show(UserInfo userInfo) {

        return null;
    }

    public void signIn(Long userId) {
        String key = RedisConst.buildKey(RedisConst.SING_IN_KEY, userId);
        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey && StrUtil.isNotBlank(key)) {
            throw new BaseException("今天已经签到过了!");
        }
        List<SignStrategyEntity> strategyList = (List<SignStrategyEntity>) (List<?>) redisTemplate.opsForHash().values(RedisConst.SIGN_STRATEGY);
        Map<Integer, Integer> strategyMap = strategyList.stream().collect(Collectors.toMap(SignStrategyEntity::getDay, SignStrategyEntity::getIntegral));
        UserSignLogEntity userSignLogEntity = signManager.queryRecentLog(userId);
        //说明没有签到过
        Integer incrValue = 0;
        Integer count = 1;
        if (ObjectUtil.isNull(userSignLogEntity)) {
            incrValue = strategyMap.get(1);
        }else if (DateUtils.isSameToday(userSignLogEntity.getCreateTime().plusDays(1))) {
            //连续签到
            count = userSignLogEntity.getCount() + 1;
            Integer integer = strategyMap.get(count);
            if (ObjectUtil.isNull(integer)) {
                incrValue = strategyMap.get(7);
            }else {
                incrValue = integer;
            }
        }
        //插入日志
        UserSignLogEntity signLogEntity = new UserSignLogEntity();
        signLogEntity.setUserId(userId);
        signLogEntity.setCount(count);
        userSignLogMapper.insert(signLogEntity);
        //修改积分
        userInfoMapper.incrScore(userId, incrValue);
        //标识已签到
        LocalDateTime now = LocalDateTime.now();
        long second = DateUtils.betweenEndTimeWithSecond(now);
        redisTemplate.opsForValue().set(key, RedisConst.DEFAULT_VALUE, second, TimeUnit.SECONDS);
        try {
            scoreLogManager.insertScoreLog(incrValue.longValue(),userId,SIGN_REMARK, ScoreLogTypeEnum.in.getType());
        }catch (Exception e) {
            log.error("分数日志插入失败！{}",e.getMessage());
        }
    }
}
