package org.uppower.sevenlion.web.cms.server.provider.metabase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.uppower.sevenlion.web.cms.dao.mapper.CustomBaseMapper;
import org.uppower.sevenlion.web.cms.server.metabase.MetabaseProvider;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/9 5:36 下午
 */
@Slf4j
@Service
public class MysqlMetabaseProvider implements MetabaseProvider {

    @Autowired
    private CustomBaseMapper customBaseMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public String getString(String key) {
        String result = null;
        try {
            List<Object> data = customBaseMapper.selectRawData(key);
            if (data != null) {
                result = objectMapper.writeValueAsString(data);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public Map<String, Object> getValuesByKeys(Collection<String> keyList) {
        Map<String, Object> result = null;
        try {
            result = Maps.newHashMap();
            for (String key : keyList) {
                List<Object> objects = customBaseMapper.selectRawData(key);
                String jsonDataStr = objectMapper.writeValueAsString(objects);
                result.put(key, jsonDataStr);
            }
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return result;
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        String jsonDataStr = null;
        try {
            List<Object> objects = customBaseMapper.selectRawData(key);
            jsonDataStr = objectMapper.writeValueAsString(objects);
            if (jsonDataStr != null) {
                return objectMapper.readValue(jsonDataStr,new TypeReference<T>(){});
            }
        } catch (Exception e) {
            log.error("org.uppower.sevenlion.web.cms.server.provider.metabase.MysqlMetabaseProvider.get 序列化异常，data：{}", jsonDataStr);
        }
        return null;
    }

    @Override
    public void push(String key, Object value) {

    }
}
