package net.vrallev.android.cat.instance;

import net.vrallev.android.cat.CatLog;
import net.vrallev.android.cat.CatUtil;

public class CatLazy extends CatLog {
    protected String getTag() {
        return CatUtil.getCallingClassNameSimple();
    }
}
