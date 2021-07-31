package org.uppower.sevenlion.common.enums;

import lombok.Getter;
import org.uppower.sevenlion.common.exceptions.BaseException;

@Getter
public enum ScoreLogTypeEnum {
    in(1,"收入"),
    out(-1,"支出"),;

    private Integer type;

    private String name;

    ScoreLogTypeEnum(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public static String getNameByType(Integer type) {
        for (ScoreLogTypeEnum value : ScoreLogTypeEnum.values()) {
            if (value.getType().equals(type)) {
                return value.getName();
            }
        }
        throw new BaseException("枚举类型错误！");
    }

    public static Integer getTypeByName(String name) {
        for (ScoreLogTypeEnum value : ScoreLogTypeEnum.values()) {
            if (value.getName().equals(name)) {
                return value.getType();
            }
        }
        throw new BaseException("枚举类型错误！");
    }
}
