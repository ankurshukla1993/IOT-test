package com.google.android.gms.internal;

public enum zzfgw {
    INT(Integer.valueOf(0)),
    LONG(Long.valueOf(0)),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(0.0d)),
    BOOLEAN(Boolean.valueOf(false)),
    STRING(""),
    BYTE_STRING(zzfdh.zzpal),
    ENUM(null),
    MESSAGE(null);
    
    private final Object zzpgp;

    private zzfgw(Object obj) {
        this.zzpgp = obj;
    }
}
