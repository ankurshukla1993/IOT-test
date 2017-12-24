package net.vrallev.android.cat.instance;

import java.util.HashMap;
import java.util.Map;
import net.vrallev.android.cat.CatUtil;

public class CatStaticClass extends CatLazy {
    private final Map<String, String> mClassTags;
    private final boolean mStripInnerClass;

    public CatStaticClass() {
        this(false);
    }

    public CatStaticClass(boolean stripInnerClass) {
        this.mClassTags = new HashMap();
        this.mStripInnerClass = stripInnerClass;
    }

    public CatStaticClass addMapping(Class<?> clazz, String tag) {
        this.mClassTags.put(clazz.getName(), tag);
        return this;
    }

    protected String getTag() {
        String callingClassName = CatUtil.getCallingClassName();
        if (this.mStripInnerClass) {
            callingClassName = CatUtil.stripInnerClass(callingClassName);
        }
        String tag = (String) this.mClassTags.get(callingClassName);
        return tag == null ? CatUtil.simpleClassName(callingClassName) : tag;
    }
}
