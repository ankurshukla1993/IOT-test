package org.ocpsoft.prettytime.impl;

import org.ocpsoft.prettytime.TimeUnit;

public abstract class ResourcesTimeUnit implements TimeUnit {
    private long maxQuantity = 0;
    private long millisPerUnit = 1;

    protected abstract String getResourceKeyPrefix();

    protected String getResourceBundleName() {
        return "org.ocpsoft.prettytime.i18n.Resources";
    }

    public long getMaxQuantity() {
        return this.maxQuantity;
    }

    public void setMaxQuantity(long maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    public long getMillisPerUnit() {
        return this.millisPerUnit;
    }

    public void setMillisPerUnit(long millisPerUnit) {
        this.millisPerUnit = millisPerUnit;
    }
}
