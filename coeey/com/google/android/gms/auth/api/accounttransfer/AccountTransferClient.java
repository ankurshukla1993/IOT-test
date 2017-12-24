package com.google.android.gms.auth.api.accounttransfer;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.api.zzd;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzatl;
import com.google.android.gms.internal.zzatn;
import com.google.android.gms.internal.zzato;
import com.google.android.gms.internal.zzatr;
import com.google.android.gms.internal.zzats;
import com.google.android.gms.internal.zzatu;
import com.google.android.gms.internal.zzatw;
import com.google.android.gms.internal.zzaty;
import com.google.android.gms.internal.zzaua;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public class AccountTransferClient extends GoogleApi<zzo> {
    private static final zzf<zzatn> zzeal = new zzf();
    private static final com.google.android.gms.common.api.Api.zza<zzatn, zzo> zzeam = new zzc();
    private static final Api<zzo> zzean = new Api("AccountTransfer.ACCOUNT_TRANSFER_API", zzeam, zzeal);

    static class zza<T> extends zzatl {
        private zzb<T> zzeax;

        public zza(zzb<T> com_google_android_gms_auth_api_accounttransfer_AccountTransferClient_zzb_T) {
            this.zzeax = com_google_android_gms_auth_api_accounttransfer_AccountTransferClient_zzb_T;
        }

        public final void onFailure(Status status) {
            this.zzeax.zzd(status);
        }
    }

    static abstract class zzb<T> extends zzdf<zzatn, T> {
        private TaskCompletionSource<T> zzeay;

        private zzb() {
        }

        protected final void setResult(T t) {
            this.zzeay.setResult(t);
        }

        protected final /* synthetic */ void zza(com.google.android.gms.common.api.Api.zzb com_google_android_gms_common_api_Api_zzb, TaskCompletionSource taskCompletionSource) throws RemoteException {
            zzatn com_google_android_gms_internal_zzatn = (zzatn) com_google_android_gms_common_api_Api_zzb;
            this.zzeay = taskCompletionSource;
            zza((zzats) com_google_android_gms_internal_zzatn.zzakb());
        }

        protected abstract void zza(zzats com_google_android_gms_internal_zzats) throws RemoteException;

        protected final void zzd(Status status) {
            AccountTransferClient.zza(this.zzeay, status);
        }
    }

    static abstract class zzc extends zzb<Void> {
        zzatr zzeaz;

        private zzc() {
            super();
            this.zzeaz = new zzk(this);
        }
    }

    AccountTransferClient(@NonNull Activity activity) {
        super(activity, zzean, null, new zzd().zza(new zzg()).zzagd());
    }

    AccountTransferClient(@NonNull Context context) {
        super(context, zzean, null, new zzd().zza(new zzg()).zzagd());
    }

    private static void zza(TaskCompletionSource taskCompletionSource, Status status) {
        String str = "Exception with Status code=";
        String valueOf = String.valueOf(status.zzagk());
        taskCompletionSource.setException(new zzl(valueOf.length() != 0 ? str.concat(valueOf) : new String(str)));
    }

    public Task<DeviceMetaData> getDeviceMetaData(String str) {
        zzbq.checkNotNull(str);
        return zza(new zzg(this, new zzato(str)));
    }

    public Task<Void> notifyCompletion(String str, int i) {
        zzbq.checkNotNull(str);
        return zzb(new zzj(this, new zzatu(str, i)));
    }

    public Task<byte[]> retrieveData(String str) {
        zzbq.checkNotNull(str);
        return zza(new zze(this, new zzatw(str)));
    }

    public Task<Void> sendData(String str, byte[] bArr) {
        zzbq.checkNotNull(str);
        zzbq.checkNotNull(bArr);
        return zzb(new zzd(this, new zzaty(str, bArr)));
    }

    public Task<Void> showUserChallenge(String str, PendingIntent pendingIntent) {
        zzbq.checkNotNull(str);
        zzbq.checkNotNull(pendingIntent);
        return zzb(new zzi(this, new zzaua(str, pendingIntent)));
    }
}
