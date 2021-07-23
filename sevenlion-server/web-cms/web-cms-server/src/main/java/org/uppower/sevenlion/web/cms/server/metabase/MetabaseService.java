package org.uppower.sevenlion.web.cms.server.metabase;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/9 4:53 下午
 */
public class MetabaseService  {

    /**
     * 数据提供者
     */
    private MetabaseProvider metabaseProvider;


    /**
     * 存储本地需要拉取的key
     */
    private volatile Set<String> localKeyList = Sets.newCopyOnWriteArraySet();

    /**
     * 本地元数据Map
     */
    private volatile Map<String, Object> localMetabaseMap = Maps.newHashMap();

    public MetabaseService() {
    }

    public MetabaseService(MetabaseProvider metabaseProvider) {
        this.metabaseProvider = metabaseProvider;
    }


    public void start() {
        Map<String, Object> valuesByKeys = metabaseProvider.getValuesByKeys(localKeyList);
        localMetabaseMap = valuesByKeys;
    }

    /**
     * 注册key
     *
     * @param key
     */
    public boolean register(String key) {
        return localKeyList.add(key);
    }

    /**
     * 注册key
     *
     * @param keyList
     */
    public boolean register(List<String> keyList) {
        return localKeyList.addAll(keyList);
    }

    /**
     * 获取String
     *
     * @param key
     * @return
     */
    public String getString(String key) {
        String value = null;
        try {
            Object temp = localMetabaseMap.get(key);
            if (temp != null) {
                value = String.valueOf(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取指定类型
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T get(String key, Class<T> clazz) {
        T value = null;
        try {
            Object temp = localMetabaseMap.get(key);
            if (temp != null) {
                value = (T) temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取当前最新值String
     *
     * @param key
     * @return
     */
    public String getStringCurrent(String key) {
        String value = null;
        try {
            this.register(key);
            value = metabaseProvider.getString(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    /**
     * 获取指定类型的最新值
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getCurrent(String key, Class<T> clazz) {
        T value = null;
        try {
            Object temp = metabaseProvider.get(key, clazz);
            if (temp != null) {
                value = (T) temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

    public Map<String, Object> getLocalMetabaseMap() {
        return localMetabaseMap;
    }

    public void push(String key, Object data) {
        metabaseProvider.push(key, data);
    }
}
