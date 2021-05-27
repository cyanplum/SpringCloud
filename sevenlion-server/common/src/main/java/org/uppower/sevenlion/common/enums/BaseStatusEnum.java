package org.uppower.sevenlion.common.enums;

import lombok.Getter;
import org.uppower.sevenlion.common.exceptions.BackException;
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
public enum BaseStatusEnum {

    ONLINE(0,"启用"),
    OFFLINE(1,"禁用"),
    ;

    private Integer status;

    private String msg;

    BaseStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static String msgByStatus(Integer status) {
        for (BaseStatusEnum value : BaseStatusEnum.values()) {
            if (value.status.equals(status)) {
                return value.msg;
            }
        }
        throw new BaseException("枚举类型错误！");
    }

    public static Integer statusByMsg(String msg) {
        for (BaseStatusEnum value : BaseStatusEnum.values()) {
            if (value.msg.equals(msg)) {
                return value.status;
            }
        }
        throw new BaseException("枚举类型错误！");
    }
}
