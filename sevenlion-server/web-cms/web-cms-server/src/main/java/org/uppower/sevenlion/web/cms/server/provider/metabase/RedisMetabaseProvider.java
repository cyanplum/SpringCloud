package org.uppower.sevenlion.web.cms.server.provider.metabase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.web.cms.server.metabase.MetabaseProvider;

import java.util.Collection;
import java.util.Map;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/21 10:00 上午
 */
@Slf4j
@Service
public class RedisMetabaseProvider implements MetabaseProvider {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public String getString(String key) {
        try {
            Object data = redisTemplate.opsForValue().get(key);
            if (data != null) {
                return objectMapper.writeValueAsString(data);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<String, Object> getValuesByKeys(Collection<String> keyList) {
        Map<String, Object> result = null;
        try {
            result = Maps.newHashMap();
            for (String key : keyList) {
                Object data = redisTemplate.opsForValue().get(key);
                result.put(key, data);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        Object objects = null;
        try {
            objects = redisTemplate.opsForValue().get(key);
            if (objects != null) {
                return (T) objects;
            }
        } catch (Exception e) {
            log.error("org.uppower.sevenlion.web.cms.server.provider.metabase.RedisMetabaseProvider.get 序列化异常，data：{}", objects);
        }
        return null;
    }

    @Override
    public void push(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }
}
