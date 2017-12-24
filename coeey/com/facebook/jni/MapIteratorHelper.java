package com.facebook.jni;

import com.facebook.proguard.annotations.DoNotStrip;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;

@DoNotStrip
public class MapIteratorHelper {
    @DoNotStrip
    private final Iterator<Entry> mIterator;
    @DoNotStrip
    @Nullable
    private Object mKey;
    @DoNotStrip
    @Nullable
    private Object mValue;

    @DoNotStrip
    public MapIteratorHelper(Map map) {
        this.mIterator = map.entrySet().iterator();
    }

    @DoNotStrip
    boolean hasNext() {
        if (this.mIterator.hasNext()) {
            Entry entry = (Entry) this.mIterator.next();
            this.mKey = entry.getKey();
            this.mValue = entry.getValue();
            return true;
        }
        this.mKey = null;
        this.mValue = null;
        return false;
    }
}
