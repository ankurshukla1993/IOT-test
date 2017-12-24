package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.internal.zzbq;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONException;

public final class zzz {
    private static final Lock zzefu = new ReentrantLock();
    private static zzz zzefv;
    private final Lock zzefw = new ReentrantLock();
    private final SharedPreferences zzefx;

    private zzz(Context context) {
        this.zzefx = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }

    public static zzz zzbr(Context context) {
        zzbq.checkNotNull(context);
        zzefu.lock();
        try {
            if (zzefv == null) {
                zzefv = new zzz(context.getApplicationContext());
            }
            zzz com_google_android_gms_auth_api_signin_internal_zzz = zzefv;
            return com_google_android_gms_auth_api_signin_internal_zzz;
        } finally {
            zzefu.unlock();
        }
    }

    private final GoogleSignInAccount zzeq(String str) {
        GoogleSignInAccount googleSignInAccount = null;
        if (!TextUtils.isEmpty(str)) {
            String zzes = zzes(zzp("googleSignInAccount", str));
            if (zzes != null) {
                try {
                    googleSignInAccount = GoogleSignInAccount.zzen(zzes);
                } catch (JSONException e) {
                }
            }
        }
        return googleSignInAccount;
    }

    private final GoogleSignInOptions zzer(String str) {
        GoogleSignInOptions googleSignInOptions = null;
        if (!TextUtils.isEmpty(str)) {
            String zzes = zzes(zzp("googleSignInOptions", str));
            if (zzes != null) {
                try {
                    googleSignInOptions = GoogleSignInOptions.zzeo(zzes);
                } catch (JSONException e) {
                }
            }
        }
        return googleSignInOptions;
    }

    private final String zzes(String str) {
        this.zzefw.lock();
        try {
            String string = this.zzefx.getString(str, null);
            return string;
        } finally {
            this.zzefw.unlock();
        }
    }

    private final void zzet(String str) {
        this.zzefw.lock();
        try {
            this.zzefx.edit().remove(str).apply();
        } finally {
            this.zzefw.unlock();
        }
    }

    private static String zzp(String str, String str2) {
        String str3 = ":";
        return new StringBuilder((String.valueOf(str).length() + String.valueOf(str3).length()) + String.valueOf(str2).length()).append(str).append(str3).append(str2).toString();
    }

    public final void clear() {
        this.zzefw.lock();
        try {
            this.zzefx.edit().clear().apply();
        } finally {
            this.zzefw.unlock();
        }
    }

    final void zza(GoogleSignInAccount googleSignInAccount, GoogleSignInOptions googleSignInOptions) {
        zzbq.checkNotNull(googleSignInAccount);
        zzbq.checkNotNull(googleSignInOptions);
        String zzaao = googleSignInAccount.zzaao();
        zzo(zzp("googleSignInAccount", zzaao), googleSignInAccount.zzaap());
        zzo(zzp("googleSignInOptions", zzaao), googleSignInOptions.zzaat());
    }

    public final GoogleSignInAccount zzabg() {
        return zzeq(zzes("defaultGoogleSignInAccount"));
    }

    public final GoogleSignInOptions zzabh() {
        return zzer(zzes("defaultGoogleSignInAccount"));
    }

    public final void zzabi() {
        String zzes = zzes("defaultGoogleSignInAccount");
        zzet("defaultGoogleSignInAccount");
        if (!TextUtils.isEmpty(zzes)) {
            zzet(zzp("googleSignInAccount", zzes));
            zzet(zzp("googleSignInOptions", zzes));
        }
    }

    protected final void zzo(String str, String str2) {
        this.zzefw.lock();
        try {
            this.zzefx.edit().putString(str, str2).apply();
        } finally {
            this.zzefw.unlock();
        }
    }
}
