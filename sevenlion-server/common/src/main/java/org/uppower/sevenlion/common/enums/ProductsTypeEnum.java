package org.uppower.sevenlion.common.enums;

import lombok.Getter;
import org.uppower.sevenlion.common.exceptions.BaseException;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/7/8 8:13 下午
 */
@Getter
public enum ProductsTypeEnum {

    SHOP(1,"商家"),
    USER(2,"用户");

    private Integer type;

    private String name;

    ProductsTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }
    public static String getNameByType(Integer type) {
        for (ProductsTypeEnum value : ProductsTypeEnum.values()) {
            if (value.type.equals(type)) {
                return value.getName();
            }
        }
        throw new BaseException("内部错误！");
    }
    public static Integer getTypeByName(String name) {
        for (ProductsTypeEnum value : ProductsTypeEnum.values()) {
            if (value.name.equals(name)) {
                return value.getType();
            }
        }
        throw new BaseException("内部错误！");
    }
}
