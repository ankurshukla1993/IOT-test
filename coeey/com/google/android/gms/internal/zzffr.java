package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Stack;

final class zzffr {
    private final Stack<zzfdh> zzpdm;

    private zzffr() {
        this.zzpdm = new Stack();
    }

    private final void zzao(zzfdh com_google_android_gms_internal_zzfdh) {
        zzfdh com_google_android_gms_internal_zzfdh2 = com_google_android_gms_internal_zzfdh;
        while (!com_google_android_gms_internal_zzfdh2.zzctn()) {
            if (com_google_android_gms_internal_zzfdh2 instanceof zzffp) {
                zzffp com_google_android_gms_internal_zzffp = (zzffp) com_google_android_gms_internal_zzfdh2;
                zzao(com_google_android_gms_internal_zzffp.zzpdi);
                com_google_android_gms_internal_zzfdh2 = com_google_android_gms_internal_zzffp.zzpdj;
            } else {
                String valueOf = String.valueOf(com_google_android_gms_internal_zzfdh2.getClass());
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(valueOf).length() + 49).append("Has a new type of ByteString been created? Found ").append(valueOf).toString());
            }
        }
        int zzlo = zzlo(com_google_android_gms_internal_zzfdh2.size());
        int i = zzffp.zzpdg[zzlo + 1];
        if (this.zzpdm.isEmpty() || ((zzfdh) this.zzpdm.peek()).size() >= i) {
            this.zzpdm.push(com_google_android_gms_internal_zzfdh2);
            return;
        }
        int i2 = zzffp.zzpdg[zzlo];
        zzfdh com_google_android_gms_internal_zzfdh3 = (zzfdh) this.zzpdm.pop();
        while (!this.zzpdm.isEmpty() && ((zzfdh) this.zzpdm.peek()).size() < i2) {
            com_google_android_gms_internal_zzfdh3 = new zzffp((zzfdh) this.zzpdm.pop(), com_google_android_gms_internal_zzfdh3);
        }
        com_google_android_gms_internal_zzfdh2 = new zzffp(com_google_android_gms_internal_zzfdh3, com_google_android_gms_internal_zzfdh2);
        while (!this.zzpdm.isEmpty()) {
            if (((zzfdh) this.zzpdm.peek()).size() >= zzffp.zzpdg[zzlo(com_google_android_gms_internal_zzfdh2.size()) + 1]) {
                break;
            }
            com_google_android_gms_internal_zzfdh2 = new zzffp((zzfdh) this.zzpdm.pop(), com_google_android_gms_internal_zzfdh2);
        }
        this.zzpdm.push(com_google_android_gms_internal_zzfdh2);
    }

    private final zzfdh zzc(zzfdh com_google_android_gms_internal_zzfdh, zzfdh com_google_android_gms_internal_zzfdh2) {
        zzao(com_google_android_gms_internal_zzfdh);
        zzao(com_google_android_gms_internal_zzfdh2);
        zzfdh com_google_android_gms_internal_zzfdh3 = (zzfdh) this.zzpdm.pop();
        while (!this.zzpdm.isEmpty()) {
            com_google_android_gms_internal_zzfdh3 = new zzffp((zzfdh) this.zzpdm.pop(), com_google_android_gms_internal_zzfdh3);
        }
        return com_google_android_gms_internal_zzfdh3;
    }

    private static int zzlo(int i) {
        int binarySearch = Arrays.binarySearch(zzffp.zzpdg, i);
        return binarySearch < 0 ? (-(binarySearch + 1)) - 1 : binarySearch;
    }
}
