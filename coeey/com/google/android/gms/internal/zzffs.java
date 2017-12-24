package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

final class zzffs implements Iterator<zzfdn> {
    private final Stack<zzffp> zzpdn;
    private zzfdn zzpdo;

    private zzffs(zzfdh com_google_android_gms_internal_zzfdh) {
        this.zzpdn = new Stack();
        this.zzpdo = zzap(com_google_android_gms_internal_zzfdh);
    }

    private final zzfdn zzap(zzfdh com_google_android_gms_internal_zzfdh) {
        zzfdh com_google_android_gms_internal_zzfdh2 = com_google_android_gms_internal_zzfdh;
        while (com_google_android_gms_internal_zzfdh2 instanceof zzffp) {
            zzffp com_google_android_gms_internal_zzffp = (zzffp) com_google_android_gms_internal_zzfdh2;
            this.zzpdn.push(com_google_android_gms_internal_zzffp);
            com_google_android_gms_internal_zzfdh2 = com_google_android_gms_internal_zzffp.zzpdi;
        }
        return (zzfdn) com_google_android_gms_internal_zzfdh2;
    }

    private final zzfdn zzcwh() {
        while (!this.zzpdn.isEmpty()) {
            zzfdh zzap = zzap(((zzffp) this.zzpdn.pop()).zzpdj);
            if (!zzap.isEmpty()) {
                return zzap;
            }
        }
        return null;
    }

    public final boolean hasNext() {
        return this.zzpdo != null;
    }

    public final /* synthetic */ Object next() {
        if (this.zzpdo == null) {
            throw new NoSuchElementException();
        }
        zzfdn com_google_android_gms_internal_zzfdn = this.zzpdo;
        this.zzpdo = zzcwh();
        return com_google_android_gms_internal_zzfdn;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
