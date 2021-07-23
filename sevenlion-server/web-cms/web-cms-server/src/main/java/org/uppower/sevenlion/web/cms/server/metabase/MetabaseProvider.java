package org.uppower.sevenlion.web.cms.server.metabase;

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
 * @date 2021/7/9 4:37 下午
 */
public interface MetabaseProvider {

    /**
     * 获取一个String
     *
     * @param key
     * @return
     */
    String getString(String key);

    /**
     * 批量获取数据
     *
     * @param keyList
     * @return
     */
    Map<String, Object> getValuesByKeys(Collection<String> keyList);

    /**
     * 获取对应类型的值
     *
     * @param key
     * @param clazz
     * @param <T>
     * @return
     */
    <T> T get(String key, Class<T> clazz);

    /**
     * 存储一个k,v
     *
     * @param key
     * @param value
     */
    void push(String key, Object value);
}
