package org.uppower.sevenlion.web.order.common.utils;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/1 8:15 下午
 */
public class RedisUtils {
    /**
     * ms
     */
    public static final int LOCK_EXPIRE = 300;

    public static RedisTemplate getRedisTemplate() {
        return SpringApplicationContext.getBean("redisTemplate");
    }
    public static boolean tryLock(String key, int expireTime) {
        return (Boolean) getRedisTemplate().execute((RedisCallback) connection -> {
            long expireAt = System.currentTimeMillis() +  expireTime + 1;
            Boolean acquire = connection.setNX(key.getBytes(),String.valueOf(expireAt).getBytes());
            if (acquire) {
                return true;
            }else {
                byte[] value = connection.get(key.getBytes());
                if (Objects.nonNull(value) && value.length > 0) {

                    long expire = Long.parseLong(new String(value));
                    // 如果锁已经过期
                    if (expire < System.currentTimeMillis()) {
                        // 重新加锁，防止死锁
                        byte[] oldValue = connection.getSet(key.getBytes(), String.valueOf(System.currentTimeMillis() + LOCK_EXPIRE + 1).getBytes());
                        return Long.parseLong(new String(oldValue)) < System.currentTimeMillis();
                    }
                }
            }
            return false;
        });
    }

    public static void delete(String key) {
        getRedisTemplate().delete(key);
    }

}
