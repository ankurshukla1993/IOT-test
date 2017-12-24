package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.internal.zzatm;
import com.google.android.gms.internal.zzatn;

public final class AccountTransfer {
    public static final String ACTION_ACCOUNT_EXPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_EXPORT_DATA_AVAILABLE";
    public static final String ACTION_ACCOUNT_IMPORT_DATA_AVAILABLE = "com.google.android.gms.auth.ACCOUNT_IMPORT_DATA_AVAILABLE";
    public static final String ACTION_START_ACCOUNT_EXPORT = "com.google.android.gms.auth.START_ACCOUNT_EXPORT";
    private static final zzf<zzatn> zzeal = new zzf();
    private static final zza<zzatn, zzo> zzeam = new zza();
    private static Api<zzo> zzean = new Api("AccountTransfer.ACCOUNT_TRANSFER_API", zzeam, zzeal);
    @Deprecated
    private static zzb zzeao = new zzatm();
    private static zzr zzeap = new zzatm();

    private AccountTransfer() {
    }

    public static AccountTransferClient getAccountTransferClient(@NonNull Activity activity) {
        return new AccountTransferClient(activity);
    }

    public static AccountTransferClient getAccountTransferClient(@NonNull Context context) {
        return new AccountTransferClient(context);
    }
}
