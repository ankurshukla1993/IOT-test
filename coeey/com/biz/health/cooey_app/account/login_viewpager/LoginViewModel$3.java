package com.biz.health.cooey_app.account.login_viewpager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import com.biz.health.cooey_app.MainActivity;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class LoginViewModel$3 implements Callback<User> {
    final /* synthetic */ LoginViewModel this$0;
    final /* synthetic */ Session val$session;

    LoginViewModel$3(LoginViewModel this$0, Session session) {
        this.this$0 = this$0;
        this.val$session = session;
    }

    public void onResponse(Call<User> call, Response<User> response) {
        if (response.body() != null && response.isSuccessful()) {
            new PreferenceStore().setUser(LoginViewModel.access$000(this.this$0), (User) response.body());
            LoginViewModel.access$300(this.this$0, this.val$session);
            LoginViewModel.access$000(this.this$0).startActivity(new Intent(LoginViewModel.access$000(this.this$0), MainActivity.class));
            ((AppCompatActivity) LoginViewModel.access$000(this.this$0)).finish();
        }
    }

    public void onFailure(Call<User> call, Throwable t) {
    }
}
