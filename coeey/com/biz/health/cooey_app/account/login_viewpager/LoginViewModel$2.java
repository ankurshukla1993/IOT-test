package com.biz.health.cooey_app.account.login_viewpager;

import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.SettingsConfig;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class LoginViewModel$2 implements Callback<SettingsConfig> {
    final /* synthetic */ LoginViewModel this$0;

    LoginViewModel$2(LoginViewModel this$0) {
        this.this$0 = this$0;
    }

    public void onResponse(Call<SettingsConfig> call, Response<SettingsConfig> response) {
        if (response.body() != null) {
            ((SettingsConfig) response.body()).setIscareplanEnabled(Boolean.valueOf(true));
            new PreferenceStore().setPartnerConfig(LoginViewModel.access$000(this.this$0), (SettingsConfig) response.body());
        }
    }

    public void onFailure(Call<SettingsConfig> call, Throwable t) {
        t.printStackTrace();
    }
}
