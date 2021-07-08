package org.uppower.sevenlion.web.order.common.model.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.uppower.sevenlion.web.order.common.model.jsonobject.AddressJsonObject;


/**
 * @author create by:
 * *      ____        ___  ___       __          __
 * *    /  _  \     /   |/   |      | |        / /
 * *   | | | |     / /|   /| |     | |  __   / /
 * *  | | | |     / / |__/ | |    | | /  | / /
 * * | |_| |_    / /       | |   | |/   |/ /
 * * \_______|  /_/        |_|  |___/|___/
 * @date 2021/3/10 1:34 下午
 */
@MappedTypes(AddressJsonObject.class)
public class AddressTypeHandler extends JsonTypeHandler<AddressJsonObject>{

    public AddressTypeHandler() {
        super(AddressJsonObject.class);
    }
}
