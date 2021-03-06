package com.biz.health.cooey_app.account;

import android.support.v4.app.NotificationCompat;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.SettingsConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J$\u0010\n\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u0016¨\u0006\r"}, d2 = {"com/biz/health/cooey_app/account/RegisterCooeyUserActivity$getPartnerConfig$1", "Lretrofit2/Callback;", "Lcom/cooey/common/vo/SettingsConfig;", "(Lcom/biz/health/cooey_app/account/RegisterCooeyUserActivity;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: RegisterCooeyUserActivity.kt */
public final class RegisterCooeyUserActivity$getPartnerConfig$1 implements Callback<SettingsConfig> {
    final /* synthetic */ RegisterCooeyUserActivity this$0;

    RegisterCooeyUserActivity$getPartnerConfig$1(RegisterCooeyUserActivity $outer) {
        this.this$0 = $outer;
    }

    public void onResponse(@NotNull Call<SettingsConfig> call, @NotNull Response<SettingsConfig> response) {
        Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(response, "response");
        if (response.body() != null) {
            Object body = response.body();
            if (body == null) {
                Intrinsics.throwNpe();
            }
            ((SettingsConfig) body).setIscareplanEnabled(Boolean.valueOf(true));
            new PreferenceStore().setPartnerConfig(this.this$0, (SettingsConfig) response.body());
        }
    }

    public void onFailure(@NotNull Call<SettingsConfig> call, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(t, "t");
        t.printStackTrace();
    }
}
