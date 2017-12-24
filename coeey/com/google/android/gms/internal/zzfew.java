package com.google.android.gms.internal;

import java.io.IOException;

public class zzfew extends IOException {
    private zzffi zzpcu = null;

    public zzfew(String str) {
        super(str);
    }

    static zzfew zzcvr() {
        return new zzfew("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either that the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzfew zzcvs() {
        return new zzfew("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzfew zzcvt() {
        return new zzfew("CodedInputStream encountered a malformed varint.");
    }

    static zzfew zzcvu() {
        return new zzfew("Protocol message contained an invalid tag (zero).");
    }

    static zzfew zzcvv() {
        return new zzfew("Protocol message end-group tag did not match expected tag.");
    }

    static zzfex zzcvw() {
        return new zzfex("Protocol message tag had invalid wire type.");
    }

    static zzfew zzcvx() {
        return new zzfew("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    static zzfew zzcvy() {
        return new zzfew("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    static zzfew zzcvz() {
        return new zzfew("Protocol message had invalid UTF-8.");
    }

    public final zzfew zzh(zzffi com_google_android_gms_internal_zzffi) {
        this.zzpcu = com_google_android_gms_internal_zzffi;
        return this;
    }
}
