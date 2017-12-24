package com.biz.health.cooey_app.account.login_viewpager;

import android.app.Activity;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Session;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class LoginViewModel$1 implements Callback<Session> {
    final /* synthetic */ LoginViewModel this$0;

    LoginViewModel$1(LoginViewModel this$0) {
        this.this$0 = this$0;
    }

    public void onResponse(Call<Session> call, Response<Session> response) {
        this.this$0.setInProgress(false);
        if (response.isSuccessful()) {
            new PreferenceStore().setSession(LoginViewModel.access$000(this.this$0), (Session) response.body());
            LoginViewModel.access$100(this.this$0, response);
            LoginViewModel.access$200(this.this$0, response);
            return;
        }
        this.this$0.showDialog((Activity) LoginViewModel.access$000(this.this$0), LoginViewModel.access$000(this.this$0).getString(C0644R.string.wrong_credentials));
    }

    public void onFailure(Call<Session> call, Throwable t) {
        this.this$0.setInProgress(false);
        this.this$0.showDialog((Activity) LoginViewModel.access$000(this.this$0), LoginViewModel.access$000(this.this$0).getString(C0644R.string.try_again_later));
    }
}
