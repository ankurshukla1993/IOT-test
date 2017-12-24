package org.ocpsoft.prettytime.units;

import org.ocpsoft.prettytime.TimeUnit;
import org.ocpsoft.prettytime.impl.ResourcesTimeUnit;

public class Hour extends ResourcesTimeUnit implements TimeUnit {
    public Hour() {
        setMillisPerUnit(3600000);
    }

    protected String getResourceKeyPrefix() {
        return "Hour";
    }
}
