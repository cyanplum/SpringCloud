package org.uppower.sevenlion.security.selector;

import org.uppower.sevenlion.security.annotation.EnableSevenlionCode;
import org.uppower.sevenlion.security.config.SevenlionImageCodeConfig;
import org.uppower.sevenlion.security.enums.CodeServiceEnum;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/4/21 12:04 下午
 */
public class SevenlionImageCodeSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        Map<String, Object> annotationMaps = annotationMetadata.getAnnotationAttributes(EnableSevenlionCode.class.getName());
        Object service = annotationMaps.get("value");
        if (service instanceof CodeServiceEnum) {
            if (service == CodeServiceEnum.IMAGE) {
                return new String[]{SevenlionImageCodeConfig.class.getName()};
            }
        }
       return new String[0];
    }
}
