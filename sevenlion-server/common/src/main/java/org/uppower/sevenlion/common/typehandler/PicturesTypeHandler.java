package org.uppower.sevenlion.common.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.uppower.sevenlion.common.jsonobject.PicturesObject;


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
@MappedTypes(PicturesObject.class)
public class PicturesTypeHandler extends JsonTypeHandler<PicturesObject> {

    public PicturesTypeHandler() {
        super(PicturesObject.class);
    }
}
