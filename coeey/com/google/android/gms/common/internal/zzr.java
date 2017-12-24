package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzcwc;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class zzr {
    private final String zzdyu;
    private final Account zzdzb;
    private final Set<Scope> zzfju;
    private final int zzfjw;
    private final View zzfjx;
    private final String zzfjy;
    private final Set<Scope> zzfwm;
    private final Map<Api<?>, zzt> zzfwn;
    private final zzcwc zzfwo;
    private Integer zzfwp;

    public zzr(Account account, Set<Scope> set, Map<Api<?>, zzt> map, int i, View view, String str, String str2, zzcwc com_google_android_gms_internal_zzcwc) {
        Map map2;
        this.zzdzb = account;
        this.zzfju = set == null ? Collections.EMPTY_SET : Collections.unmodifiableSet(set);
        if (map == null) {
            map2 = Collections.EMPTY_MAP;
        }
        this.zzfwn = map2;
        this.zzfjx = view;
        this.zzfjw = i;
        this.zzdyu = str;
        this.zzfjy = str2;
        this.zzfwo = com_google_android_gms_internal_zzcwc;
        Set hashSet = new HashSet(this.zzfju);
        for (zzt com_google_android_gms_common_internal_zzt : this.zzfwn.values()) {
            hashSet.addAll(com_google_android_gms_common_internal_zzt.zzees);
        }
        this.zzfwm = Collections.unmodifiableSet(hashSet);
    }

    public static zzr zzcj(Context context) {
        return new Builder(context).zzagh();
    }

    public final Account getAccount() {
        return this.zzdzb;
    }

    @Deprecated
    public final String getAccountName() {
        return this.zzdzb != null ? this.zzdzb.name : null;
    }

    public final Account zzakh() {
        return this.zzdzb != null ? this.zzdzb : new Account("<<default account>>", "com.google");
    }

    public final int zzaki() {
        return this.zzfjw;
    }

    public final Set<Scope> zzakj() {
        return this.zzfju;
    }

    public final Set<Scope> zzakk() {
        return this.zzfwm;
    }

    public final Map<Api<?>, zzt> zzakl() {
        return this.zzfwn;
    }

    public final String zzakm() {
        return this.zzdyu;
    }

    public final String zzakn() {
        return this.zzfjy;
    }

    public final View zzako() {
        return this.zzfjx;
    }

    public final zzcwc zzakp() {
        return this.zzfwo;
    }

    public final Integer zzakq() {
        return this.zzfwp;
    }

    public final Set<Scope> zzc(Api<?> api) {
        zzt com_google_android_gms_common_internal_zzt = (zzt) this.zzfwn.get(api);
        if (com_google_android_gms_common_internal_zzt == null || com_google_android_gms_common_internal_zzt.zzees.isEmpty()) {
            return this.zzfju;
        }
        Set<Scope> hashSet = new HashSet(this.zzfju);
        hashSet.addAll(com_google_android_gms_common_internal_zzt.zzees);
        return hashSet;
    }

    public final void zzc(Integer num) {
        this.zzfwp = num;
    }
}
