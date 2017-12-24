package net.vrallev.android.cat.instance;

import net.vrallev.android.cat.CatLog;

public class CatSimple extends CatLog {
    private final String mTag;

    public CatSimple(Class<?> clazz) {
        this(clazz.getSimpleName());
    }

    public CatSimple(String tag) {
        if (tag == null) {
            tag = "";
        }
        this.mTag = tag;
    }

    protected String getTag() {
        return this.mTag;
    }
}
