package org.uppower.sevenlion.common.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/5/24 11:16 下午
 */
@Service
public class RedisService {

    private StringRedisTemplate stringRedisTemplate;

    private ObjectMapper objectMapper;

    public RedisService(StringRedisTemplate stringRedisTemplate, ObjectMapper objectMapper) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.objectMapper = objectMapper;
    }

    public String get(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        String value = null;
        try {
            value = stringRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public <T> T get(String key, Class<T> clazz) {
        if (key == null || clazz == null) {
            throw new NullPointerException();
        }
        T value = null;
        try {
            value = objectMapper.readValue(stringRedisTemplate.opsForValue().get(key), clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public void setObject(String key, Object o) {
        if (o == null) {
            throw new NullPointerException();
        }
        String valueStr;
        try {
            if (o instanceof String) {
                valueStr = (String) o;
            } else {
                valueStr = objectMapper.writeValueAsString(o);
            }
            set(key, valueStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void set(String key, String value) {
        if (value == null || key == null) {
            throw new NullPointerException();
        }
        try {
            stringRedisTemplate.opsForValue().set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
