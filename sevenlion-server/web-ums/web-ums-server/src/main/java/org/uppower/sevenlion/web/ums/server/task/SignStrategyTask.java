package org.uppower.sevenlion.web.ums.server.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.uppower.sevenlion.common.constant.RedisConst;
import org.uppower.sevenlion.common.pool.ThreadPool;
import org.uppower.sevenlion.web.ums.common.model.entity.SignStrategyEntity;
import org.uppower.sevenlion.web.ums.server.manager.SignManager;

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
 * @date 2021/7/29 9:03 下午
 */
@Component
@Slf4j
public class SignStrategyTask implements Runnable, InitializingBean {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private SignManager signManager;

    @Override
    public void run() {
        List<SignStrategyEntity> signStrategyEntities = signManager.selectStrategyList();
        Map<Integer, Integer> map = signStrategyEntities.stream().collect(Collectors.toMap(SignStrategyEntity::getDay, SignStrategyEntity::getIntegral));
        redisTemplate.opsForHash().putAll(RedisConst.SIGN_STRATEGY, map);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ThreadPool.registerDelayTask(this, 10, TimeUnit.SECONDS);
    }
}
