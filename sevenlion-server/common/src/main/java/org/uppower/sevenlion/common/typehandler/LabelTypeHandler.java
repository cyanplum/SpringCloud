package org.uppower.sevenlion.common.typehandler;

import org.apache.ibatis.type.MappedTypes;
import org.uppower.sevenlion.common.jsonobject.LabelObject;


@MappedTypes(LabelObject.class)
public class LabelTypeHandler extends JsonTypeHandler<LabelObject> {
    public LabelTypeHandler() {
        super(LabelObject.class);
    }
}
