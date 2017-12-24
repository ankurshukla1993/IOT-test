package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzbq;
import java.util.concurrent.TimeUnit;

public final class BatchResult implements Result {
    private final Status mStatus;
    private final PendingResult<?>[] zzfjg;

    BatchResult(Status status, PendingResult<?>[] pendingResultArr) {
        this.mStatus = status;
        this.zzfjg = pendingResultArr;
    }

    public final Status getStatus() {
        return this.mStatus;
    }

    public final <R extends Result> R take(BatchResultToken<R> batchResultToken) {
        zzbq.checkArgument(batchResultToken.mId < this.zzfjg.length, "The result token does not belong to this batch");
        return this.zzfjg[batchResultToken.mId].await(0, TimeUnit.MILLISECONDS);
    }
}
