package com.biz.health.cooey_app.account;

import com.cooey.common.services.requests.CreateSessionRequest;
import com.cooey.common.services.requests.CreateSessionRequest.TypeEnum;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.User;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000+\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J(\u0010\n\u001a\u00020\u00052\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u00072\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\fH\u0016¨\u0006\r"}, d2 = {"com/biz/health/cooey_app/account/RegisterCooeyUserActivity$createUser$1", "Lretrofit2/Callback;", "Lcom/cooey/common/vo/User;", "(Lcom/biz/health/cooey_app/account/RegisterCooeyUserActivity;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: RegisterCooeyUserActivity.kt */
public final class RegisterCooeyUserActivity$createUser$1 implements Callback<User> {
    final /* synthetic */ RegisterCooeyUserActivity this$0;

    RegisterCooeyUserActivity$createUser$1(RegisterCooeyUserActivity $outer) {
        this.this$0 = $outer;
    }

    public void onFailure(@Nullable Call<User> call, @Nullable Throwable t) {
        this.this$0.getDialog().dismiss();
    }

    public void onResponse(@Nullable Call<User> call, @Nullable Response<User> response) {
        String str = null;
        if ((response != null ? (User) response.body() : null) != null && response.isSuccessful()) {
            String email;
            User user = (User) response.body();
            new PreferenceStore().setUser(this.this$0, user);
            CreateSessionRequest createSessionRequest = new CreateSessionRequest();
            if (user != null) {
                email = user.getEmail();
            } else {
                email = null;
            }
            createSessionRequest.setEmail(email);
            if (user != null) {
                str = user.getPassword();
            }
            createSessionRequest.setPassword(str);
            createSessionRequest.setType(TypeEnum.PATIENT);
            this.this$0.cooeySignIn(createSessionRequest);
        }
    }
}
