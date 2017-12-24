package org.joda.time.chrono;

import org.joda.time.DateTimeZone;
import org.joda.time.Instant;

class GJCacheKey {
    private final Instant cutoverInstant;
    private final int minDaysInFirstWeek;
    private final DateTimeZone zone;

    GJCacheKey(DateTimeZone dateTimeZone, Instant instant, int i) {
        this.zone = dateTimeZone;
        this.cutoverInstant = instant;
        this.minDaysInFirstWeek = i;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((this.cutoverInstant == null ? 0 : this.cutoverInstant.hashCode()) + 31) * 31) + this.minDaysInFirstWeek) * 31;
        if (this.zone != null) {
            i = this.zone.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof GJCacheKey)) {
            return false;
        }
        GJCacheKey gJCacheKey = (GJCacheKey) obj;
        if (this.cutoverInstant == null) {
            if (gJCacheKey.cutoverInstant != null) {
                return false;
            }
        } else if (!this.cutoverInstant.equals(gJCacheKey.cutoverInstant)) {
            return false;
        }
        if (this.minDaysInFirstWeek != gJCacheKey.minDaysInFirstWeek) {
            return false;
        }
        if (this.zone == null) {
            if (gJCacheKey.zone != null) {
                return false;
            }
            return true;
        } else if (this.zone.equals(gJCacheKey.zone)) {
            return true;
        } else {
            return false;
        }
    }
}
