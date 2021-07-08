package org.uppower.sevenlion.web.order.common.enums;

import lombok.Getter;


/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/3/10 1:10 下午
 */
@Getter
public enum PayStatusEnum {

    FAILED(0,"失败"),
    SUCCESS(1,"成功"),
    ;

    private Integer status;

    private String msg;

    PayStatusEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public static String msgByStatus(Integer status) {
        for (PayStatusEnum value : PayStatusEnum.values()) {
            if (value.status.equals(status)) {
                return value.msg;
            }
        }
        throw new RuntimeException("枚举类型错误！");
    }

    public static Integer statusByMsg(String msg) {
        for (PayStatusEnum value : PayStatusEnum.values()) {
            if (value.msg.equals(msg)) {
                return value.status;
            }
        }
        throw new RuntimeException("枚举类型错误！");
    }
}
