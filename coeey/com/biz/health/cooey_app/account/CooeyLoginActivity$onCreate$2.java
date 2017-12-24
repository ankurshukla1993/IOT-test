package com.biz.health.cooey_app.account;

import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0016¨\u0006\u000b"}, d2 = {"com/biz/health/cooey_app/account/CooeyLoginActivity$onCreate$2", "Lcom/facebook/FacebookCallback;", "Lcom/facebook/login/LoginResult;", "(Lcom/biz/health/cooey_app/account/CooeyLoginActivity;)V", "onCancel", "", "onError", "error", "Lcom/facebook/FacebookException;", "onSuccess", "result", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: CooeyLoginActivity.kt */
public final class CooeyLoginActivity$onCreate$2 implements FacebookCallback<LoginResult> {
    final /* synthetic */ CooeyLoginActivity this$0;

    CooeyLoginActivity$onCreate$2(CooeyLoginActivity $outer) {
        this.this$0 = $outer;
    }

    public void onError(@Nullable FacebookException error) {
        if (error != null) {
            error.printStackTrace();
        }
    }

    public void onCancel() {
    }

    public void onSuccess(@Nullable LoginResult result) {
        this.this$0.getFacebookDisplayName(result);
    }
}
