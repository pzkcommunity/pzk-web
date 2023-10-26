package org.pzk.web.convert;

import java.util.HashMap;
import java.util.Map;

/**
 * map的场景: 表单->map
 */
public class MapConvert extends Convert<HashMap>{

    public MapConvert(Class<HashMap> type) {
        super(type);
    }

    @Override
    protected Object convert(Object arg) throws Exception {
        return this.type.getConstructor(Map.class).newInstance(arg);
    }
}
