package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfhj extends IOException {
    public zzfhj(String str) {
        super(str);
    }

    static zzfhj zzcxh() {
        return new zzfhj("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }

    static zzfhj zzcxi() {
        return new zzfhj("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zzfhj zzcxj() {
        return new zzfhj("CodedInputStream encountered a malformed varint.");
    }

    static zzfhj zzcxk() {
        return new zzfhj("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }
}
