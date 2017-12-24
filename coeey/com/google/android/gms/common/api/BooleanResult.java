package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzbq;

public class BooleanResult implements Result {
    private final Status mStatus;
    private final boolean zzfjj;

    public BooleanResult(Status status, boolean z) {
        this.mStatus = (Status) zzbq.checkNotNull(status, "Status must not be null");
        this.zzfjj = z;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BooleanResult)) {
            return false;
        }
        BooleanResult booleanResult = (BooleanResult) obj;
        return this.mStatus.equals(booleanResult.mStatus) && this.zzfjj == booleanResult.zzfjj;
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public boolean getValue() {
        return this.zzfjj;
    }

    public final int hashCode() {
        return (this.zzfjj ? 1 : 0) + ((this.mStatus.hashCode() + 527) * 31);
    }
}
