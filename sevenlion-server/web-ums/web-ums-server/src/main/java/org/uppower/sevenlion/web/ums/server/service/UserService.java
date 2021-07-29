package org.uppower.sevenlion.web.ums.server.service;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.common.constant.RedisConst;
import org.uppower.sevenlion.common.exceptions.BaseException;
import org.uppower.sevenlion.common.model.user.UserInfo;
import org.uppower.sevenlion.web.ums.dao.mapper.UserMapper;

import java.util.Date;
import java.util.concurrent.TimeUnit;

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
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    public Object show(UserInfo userInfo) {

        return null;
    }

    public void singIn(Long userId) {
        String key = RedisConst.buildKey(RedisConst.SING_IN_KEY, userId);
        Boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey && StrUtil.isNotBlank(key)) {
            throw new BaseException("今天已经签到过了!");
        }
        Date now = new Date();
        long second = DateUtil.between(now, DateUtil.endOfDay(now), DateUnit.SECOND);
        redisTemplate.opsForValue().set(key, RedisConst.DEFAULT_VALUE, second, TimeUnit.SECONDS);
        // TODO: 2021/7/29 签到修改积分
    }
}
