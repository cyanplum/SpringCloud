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
 * @date 2021/7/8 3:16 下午
 */
@Getter
public enum CategoryTypeEnum {
    HOMEPAGE(1,"首页类目"),
    PRODUCT(2,"产品类目"),
    ;

    private Integer type;

    private String name;

    CategoryTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(Integer type) {
        for (CategoryTypeEnum value : CategoryTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value.name;
            }
        }
        throw new BaseException("内部错误！");
    }

    public static Integer getTypeByName(String name) {
        for (CategoryTypeEnum value : CategoryTypeEnum.values()) {
            if (value.getName().equals(name)) {
                return value.type;
            }
        }
        throw new BaseException("内部错误！");
    }
}
