package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.firebase.auth.FirebaseUserMetadata;

public final class zzdxc implements FirebaseUserMetadata {
    private long mCreationTimestamp;
    private long zzmdf;

    public zzdxc(long j, long j2) {
        this.zzmdf = j;
        this.mCreationTimestamp = j2;
    }

    @Nullable
    public final long getCreationTimestamp() {
        return this.mCreationTimestamp;
    }

    @Nullable
    public final long getLastSignInTimestamp() {
        return this.zzmdf;
    }
}
