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
 * @date 2021/5/12 10:10 上午
 */
@Getter
public enum VipLevelTypeEnum {

    NORMAL(0,"普通用户"),
    VIP(1,"vip"),
    SVIP(2,"svip")
    ;

    private Integer type;

    private String msg;

    VipLevelTypeEnum(Integer type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public static String msgByStatus(Integer type) {
        for (VipLevelTypeEnum value : VipLevelTypeEnum.values()) {
            if (value.type.equals(type)) {
                return value.msg;
            }
        }
        throw new BaseException("枚举类型错误！");
    }

    public static Integer statusByMsg(String msg) {
        for (VipLevelTypeEnum value : VipLevelTypeEnum.values()) {
            if (value.msg.equals(msg)) {
                return value.type;
            }
        }
        throw new BaseException("枚举类型错误！");
    }
}
