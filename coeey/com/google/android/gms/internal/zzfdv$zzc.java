package com.google.android.gms.internal;

import java.io.IOException;

public class zzfdv$zzc extends IOException {
    zzfdv$zzc() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }

    zzfdv$zzc(String str, Throwable th) {
        String valueOf = String.valueOf("CodedOutputStream was writing to a flat byte array and ran out of space.: ");
        String valueOf2 = String.valueOf(str);
        super(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), th);
    }

    zzfdv$zzc(Throwable th) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.", th);
    }
}
