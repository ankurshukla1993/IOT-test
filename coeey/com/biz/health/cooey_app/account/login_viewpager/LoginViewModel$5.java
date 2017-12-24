package com.biz.health.cooey_app.account.login_viewpager;

import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;

class LoginViewModel$5 implements OnClickListener {
    final /* synthetic */ LoginViewModel this$0;
    final /* synthetic */ Dialog val$dialog;

    LoginViewModel$5(LoginViewModel this$0, Dialog dialog) {
        this.this$0 = this$0;
        this.val$dialog = dialog;
    }

    public void onClick(View v) {
        this.val$dialog.dismiss();
    }
}
