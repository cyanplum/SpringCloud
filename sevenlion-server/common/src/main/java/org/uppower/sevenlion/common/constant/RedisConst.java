package org.uppower.sevenlion.common.constant;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/23 9:23 下午
 * redis key集合
 */
@Slf4j
public class RedisConst {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 类目列表
     */
    public final static String CATEGORY_LIST = "CATEGORY_LIST";

    public final static String ADD_CART_LOCK = "ADD_CART_LOCK::%s";

    public final static String ADD_CART_MAP = "ADD_CART::%s";

    public final static String ADD_CART_KEY = "ADD_CART::%s::%s";

    /**
     * 签到key
     */
    public final static String SING_IN_KEY = "SING_IN::%s";

    /**
     * 基础value
     */
    public final static String DEFAULT_VALUE = "1";

    public static String buildKey(String prefix, Object ... keys) {
        try {
            for (Object key : keys) {
                prefix = String.format(prefix, objectMapper.writeValueAsString(key));
            }
            return prefix;
        }catch (Exception e) {
            log.error("构建key失败！{}",e.getMessage());
        }
        return null;
    }
}
