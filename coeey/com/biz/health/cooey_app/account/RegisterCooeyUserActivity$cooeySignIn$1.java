package com.biz.health.cooey_app.account;

import android.content.Intent;
import android.widget.Toast;
import com.biz.health.cooey_app.MainActivity;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Session;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J(\u0010\n\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"com/biz/health/cooey_app/account/RegisterCooeyUserActivity$cooeySignIn$1", "Lretrofit2/Callback;", "Lcom/cooey/common/vo/Session;", "(Lcom/biz/health/cooey_app/account/RegisterCooeyUserActivity;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: RegisterCooeyUserActivity.kt */
public final class RegisterCooeyUserActivity$cooeySignIn$1 implements Callback<Session> {
    final /* synthetic */ RegisterCooeyUserActivity this$0;

    RegisterCooeyUserActivity$cooeySignIn$1(RegisterCooeyUserActivity $outer) {
        this.this$0 = $outer;
    }

    public void onResponse(@Nullable Call<Session> call, @Nullable Response<Session> response) {
        Boolean valueOf = response != null ? Boolean.valueOf(response.isSuccessful()) : null;
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        if (valueOf.booleanValue() && response.body() != null && response.code() != 401) {
            new PreferenceStore().setSession(this.this$0, (Session) response.body());
            this.this$0.getPartnerConfig(response);
            this.this$0.registerFCMToken((Session) response.body());
            this.this$0.startActivity(new Intent(this.this$0, MainActivity.class));
            RegisterCooeyUserActivity registerCooeyUserActivity = this.this$0;
            if (registerCooeyUserActivity == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.support.v7.app.AppCompatActivity");
            }
            registerCooeyUserActivity.finish();
        } else if (response.code() == 401) {
            Toast.makeText(this.this$0, "User not registered, Please register", 0).show();
        } else {
            Toast.makeText(this.this$0, "Couldn't login,Please try once again", 0).show();
        }
        if (this.this$0.getDialog().isShowing()) {
            this.this$0.getDialog().dismiss();
        }
    }

    public void onFailure(@Nullable Call<Session> call, @Nullable Throwable t) {
        Toast.makeText(this.this$0, "Login Error", 0).show();
        if (this.this$0.getDialog().isShowing()) {
            this.this$0.getDialog().dismiss();
        }
    }
}
