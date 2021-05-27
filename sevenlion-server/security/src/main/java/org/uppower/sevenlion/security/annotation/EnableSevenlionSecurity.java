package org.uppower.sevenlion.security.annotation;

import org.uppower.sevenlion.security.config.SevenlionSecurityConfig;
import org.uppower.sevenlion.security.config.SevenlionSecurityMethodConfiguration;
import org.uppower.sevenlion.security.enums.TokenServiceEnum;
import org.uppower.sevenlion.security.selector.SevenlionSecurityTokenSelector;
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
 * @date 2021/4/20 10:53 上午
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({SevenlionSecurityConfig.class, SevenlionSecurityTokenSelector.class, SevenlionSecurityMethodConfiguration.class})
public @interface EnableSevenlionSecurity {

    TokenServiceEnum value() default TokenServiceEnum.JWT;

}
