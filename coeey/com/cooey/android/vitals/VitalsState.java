package com.cooey.android.vitals;

import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0003\u0010\u0005\"\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/cooey/android/vitals/VitalsState;", "", "()V", "isSynced", "", "()Z", "setSynced", "(Z)V", "vitals_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: VitalsState.kt */
public final class VitalsState {
    public static final VitalsState INSTANCE = null;
    private static boolean isSynced;

    static {
        VitalsState vitalsState = new VitalsState();
    }

    private VitalsState() {
        INSTANCE = this;
    }

    public final boolean isSynced() {
        return isSynced;
    }

    public final void setSynced(boolean <set-?>) {
        isSynced = <set-?>;
    }
}
