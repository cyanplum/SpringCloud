package org.uppower.sevenlion.web.order.common.model.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.uppower.sevenlion.web.order.common.model.jsonobject.ProductSnapObject;


/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/3/10 1:48 下午
 */
@MappedTypes(ProductSnapObject.class)
public class ProductSnapTypeHandler extends JsonTypeHandler<ProductSnapObject> {

    public ProductSnapTypeHandler() {
        super(ProductSnapObject.class);
    }
}
