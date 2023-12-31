package org.pzk.web.convert;

import java.util.Collection;
import java.util.HashSet;

public class SetConvert extends Convert<HashSet>{

    public SetConvert(Class<HashSet> type) {
        super(type);
    }

    @Override
    protected Object convert(Object arg) throws Exception {
        return this.type.getConstructor(Collection.class).newInstance(arg);
    }
}
