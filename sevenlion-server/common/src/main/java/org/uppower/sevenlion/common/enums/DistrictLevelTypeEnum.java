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
 * @date 2021/5/27 2:04 下午
 */
@Getter
public enum DistrictLevelTypeEnum {
    PROVINCE(1,"省"),
    CITY(2,"市"),
    DISTRICT(3,"区"),
    ;

    private Integer level;

    private String msg;

    DistrictLevelTypeEnum(Integer level, String msg) {
        this.level = level;
        this.msg = msg;
    }

    public static String msgByStatus(Integer level) {
        for (DistrictLevelTypeEnum value : DistrictLevelTypeEnum.values()) {
            if (value.level.equals(level)) {
                return value.msg;
            }
        }
        throw new BaseException("枚举类型错误！");
    }

    public static Integer statusByMsg(String msg) {
        for (DistrictLevelTypeEnum value : DistrictLevelTypeEnum.values()) {
            if (value.msg.equals(msg)) {
                return value.level;
            }
        }
        throw new BaseException("枚举类型错误！");
    }
}
