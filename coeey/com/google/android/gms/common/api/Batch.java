package com.google.android.gms.common.api;

import com.google.android.gms.common.api.internal.zzs;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends zzs<BatchResult> {
    private final Object mLock;
    private int zzfjd;
    private boolean zzfje;
    private boolean zzfjf;
    private final PendingResult<?>[] zzfjg;

    public static final class Builder {
        private GoogleApiClient zzerl;
        private List<PendingResult<?>> zzfji = new ArrayList();

        public Builder(GoogleApiClient googleApiClient) {
            this.zzerl = googleApiClient;
        }

        public final <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken(this.zzfji.size());
            this.zzfji.add(pendingResult);
            return batchResultToken;
        }

        public final Batch build() {
            return new Batch(this.zzfji, this.zzerl);
        }
    }

    private Batch(List<PendingResult<?>> list, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.mLock = new Object();
        this.zzfjd = list.size();
        this.zzfjg = new PendingResult[this.zzfjd];
        if (list.isEmpty()) {
            setResult(new BatchResult(Status.zzfko, this.zzfjg));
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            PendingResult pendingResult = (PendingResult) list.get(i);
            this.zzfjg[i] = pendingResult;
            pendingResult.zza(new zza(this));
        }
    }

    public final void cancel() {
        super.cancel();
        for (PendingResult cancel : this.zzfjg) {
            cancel.cancel();
        }
    }

    public final BatchResult createFailedResult(Status status) {
        return new BatchResult(status, this.zzfjg);
    }

    public final /* synthetic */ Result zzb(Status status) {
        return createFailedResult(status);
    }
}
