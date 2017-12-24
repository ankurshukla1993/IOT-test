package com.biz.health.cooey_app.account;

import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import com.biz.health.cooey_app.MainActivity;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.User;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J$\u0010\n\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\fH\u0016¨\u0006\r"}, d2 = {"com/biz/health/cooey_app/account/CooeyLoginActivity$getUser$1", "Lretrofit2/Callback;", "Lcom/cooey/common/vo/User;", "(Lcom/biz/health/cooey_app/account/CooeyLoginActivity;Lcom/cooey/common/vo/Session;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: CooeyLoginActivity.kt */
public final class CooeyLoginActivity$getUser$1 implements Callback<User> {
    final /* synthetic */ Session $session;
    final /* synthetic */ CooeyLoginActivity this$0;

    CooeyLoginActivity$getUser$1(CooeyLoginActivity $outer, Session $captured_local_variable$1) {
        this.this$0 = $outer;
        this.$session = $captured_local_variable$1;
    }

    public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
        Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(response, "response");
        if (response.body() != null && response.isSuccessful()) {
            new PreferenceStore().setUser(this.this$0, (User) response.body());
            this.this$0.registerFCMToken(this.$session);
            this.this$0.startActivity(new Intent(this.this$0, MainActivity.class));
            CooeyLoginActivity cooeyLoginActivity = this.this$0;
            if (cooeyLoginActivity == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.support.v7.app.AppCompatActivity");
            }
            cooeyLoginActivity.finish();
        }
    }

    public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {
        Intrinsics.checkParameterIsNotNull(call, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(t, "t");
    }
}
