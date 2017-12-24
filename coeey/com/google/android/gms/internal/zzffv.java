package com.google.android.gms.internal;

import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

final class zzffv extends zzffu<FieldDescriptorType, Object> {
    zzffv(int i) {
        super(i);
    }

    public final void zzbim() {
        if (!isImmutable()) {
            for (int i = 0; i < zzcwj(); i++) {
                Entry zzlq = zzlq(i);
                if (((zzfed) zzlq.getKey()).zzcvc()) {
                    zzlq.setValue(Collections.unmodifiableList((List) zzlq.getValue()));
                }
            }
            for (Entry entry : zzcwk()) {
                if (((zzfed) entry.getKey()).zzcvc()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzbim();
    }
}
