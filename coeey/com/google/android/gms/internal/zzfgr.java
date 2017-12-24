package com.google.android.gms.internal;

public enum zzfgr {
    DOUBLE(zzfgw.DOUBLE, 1),
    FLOAT(zzfgw.FLOAT, 5),
    INT64(zzfgw.LONG, 0),
    UINT64(zzfgw.LONG, 0),
    INT32(zzfgw.INT, 0),
    FIXED64(zzfgw.LONG, 1),
    FIXED32(zzfgw.INT, 5),
    BOOL(zzfgw.BOOLEAN, 0),
    STRING(zzfgw.STRING, 2),
    GROUP(zzfgw.MESSAGE, 3),
    MESSAGE(zzfgw.MESSAGE, 2),
    BYTES(zzfgw.BYTE_STRING, 2),
    UINT32(zzfgw.INT, 0),
    ENUM(zzfgw.ENUM, 0),
    SFIXED32(zzfgw.INT, 5),
    SFIXED64(zzfgw.LONG, 1),
    SINT32(zzfgw.INT, 0),
    SINT64(zzfgw.LONG, 0);
    
    private final zzfgw zzpgd;
    private final int zzpge;

    private zzfgr(zzfgw com_google_android_gms_internal_zzfgw, int i) {
        this.zzpgd = com_google_android_gms_internal_zzfgw;
        this.zzpge = i;
    }

    public final zzfgw zzcxc() {
        return this.zzpgd;
    }

    public final int zzcxd() {
        return this.zzpge;
    }
}
