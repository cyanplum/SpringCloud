package org.uppower.sevenlion.security.annotation;

import org.uppower.sevenlion.security.config.SevenlionValidateCodeConfig;
import org.uppower.sevenlion.security.enums.CodeServiceEnum;
import org.uppower.sevenlion.security.selector.SevenlionImageCodeSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/21 11:28 上午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({SevenlionValidateCodeConfig.class, SevenlionImageCodeSelector.class})
public @interface EnableSevenlionCode {

    CodeServiceEnum value() default CodeServiceEnum.IMAGE;
}
