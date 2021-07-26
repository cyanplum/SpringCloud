package org.uppower.sevenlion.common.constant;

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
public class RedisConst {

    /**
     * 类目列表
     */
    public final static String CATEGORY_LIST = "CATEGORY_LIST";

    public final static String ADD_CART_LOCK = "ADD_CART_LOCK::%s";

    public final static String ADD_CART_MAP = "ADD_CART::%s";

    public final static String ADD_CART_KEY = "ADD_CART::%s::%s";
}
