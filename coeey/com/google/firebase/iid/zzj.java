package com.google.firebase.iid;

import android.support.annotation.Nullable;
import android.text.TextUtils;

public final class zzj {
    private static final Object sLock = new Object();
    private final zzab zznuc;

    zzj(zzab com_google_firebase_iid_zzab) {
        this.zznuc = com_google_firebase_iid_zzab;
    }

    @Nullable
    final String zzche() {
        String str = null;
        synchronized (sLock) {
            String string = this.zznuc.zzidi.getString("topic_operaion_queue", null);
            if (string != null) {
                String[] split = string.split(",");
                if (split.length > 1 && !TextUtils.isEmpty(split[1])) {
                    str = split[1];
                }
            }
        }
        return str;
    }

    final void zzqp(String str) {
        synchronized (sLock) {
            String string = this.zznuc.zzidi.getString("topic_operaion_queue", "");
            String str2 = ",";
            this.zznuc.zzidi.edit().putString("topic_operaion_queue", new StringBuilder((String.valueOf(string).length() + String.valueOf(str2).length()) + String.valueOf(str).length()).append(string).append(str2).append(str).toString()).apply();
        }
    }

    final boolean zzqs(String str) {
        boolean z;
        synchronized (sLock) {
            String string = this.zznuc.zzidi.getString("topic_operaion_queue", "");
            String valueOf = String.valueOf(",");
            String valueOf2 = String.valueOf(str);
            if (string.startsWith(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf))) {
                valueOf = String.valueOf(",");
                valueOf2 = String.valueOf(str);
                this.zznuc.zzidi.edit().putString("topic_operaion_queue", string.substring((valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).length())).apply();
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }
}
