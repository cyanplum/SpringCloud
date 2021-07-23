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
public enum  BaseAuditStatusEnum {

    WAIT(0,"待审核"),
    PASS(1,"审核通过"),
    ;

    private Integer type;

    private String name;

    BaseAuditStatusEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(Integer type) {
        for (BaseAuditStatusEnum value : BaseAuditStatusEnum.values()) {
            if (value.getType().equals(type)) {
                return value.name;
            }
        }
        throw new BaseException("内部错误！");
    }

    public static Integer getTypeByName(String name) {
        for (BaseAuditStatusEnum value : BaseAuditStatusEnum.values()) {
            if (value.getName().equals(name)) {
                return value.type;
            }
        }
        throw new BaseException("内部错误！");
    }
}
