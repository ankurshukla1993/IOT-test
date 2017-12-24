package com.facebook.login;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.AccessTokenSource;
import com.facebook.FacebookException;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.internal.ServerProtocol;
import com.facebook.internal.Utility;
import com.facebook.login.LoginClient.Request;
import com.facebook.login.LoginClient.Result;

class KatanaProxyLoginMethodHandler extends LoginMethodHandler {
    public static final Creator<KatanaProxyLoginMethodHandler> CREATOR = new C12441();

    static class C12441 implements Creator {
        C12441() {
        }

        public KatanaProxyLoginMethodHandler createFromParcel(Parcel source) {
            return new KatanaProxyLoginMethodHandler(source);
        }

        public KatanaProxyLoginMethodHandler[] newArray(int size) {
            return new KatanaProxyLoginMethodHandler[size];
        }
    }

    KatanaProxyLoginMethodHandler(LoginClient loginClient) {
        super(loginClient);
    }

    String getNameForLogging() {
        return "katana_proxy_auth";
    }

    boolean tryAuthorize(Request request) {
        String e2e = LoginClient.getE2E();
        Intent intent = NativeProtocol.createProxyAuthIntent(this.loginClient.getActivity(), request.getApplicationId(), request.getPermissions(), e2e, request.isRerequest(), request.hasPublishPermission(), request.getDefaultAudience());
        addLoggingExtra("e2e", e2e);
        return tryIntent(intent, LoginClient.getLoginRequestCode());
    }

    boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        Result outcome;
        Request request = this.loginClient.getPendingRequest();
        if (data == null) {
            outcome = Result.createCancelResult(request, "Operation canceled");
        } else if (resultCode == 0) {
            outcome = Result.createCancelResult(request, data.getStringExtra("error"));
        } else if (resultCode != -1) {
            outcome = Result.createErrorResult(request, "Unexpected resultCode from authorization.", null);
        } else {
            outcome = handleResultOk(request, data);
        }
        if (outcome != null) {
            this.loginClient.completeAndValidate(outcome);
        } else {
            this.loginClient.tryNextHandler();
        }
        return true;
    }

    private Result handleResultOk(Request request, Intent data) {
        Result result = null;
        Bundle extras = data.getExtras();
        String error = extras.getString("error");
        if (error == null) {
            error = extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_TYPE);
        }
        String errorCode = extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
        String errorMessage = extras.getString(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE);
        if (errorMessage == null) {
            errorMessage = extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
        }
        String e2e = extras.getString("e2e");
        if (!Utility.isNullOrEmpty(e2e)) {
            logWebLoginCompleted(e2e);
        }
        if (error == null && errorCode == null && errorMessage == null) {
            try {
                return Result.createTokenResult(request, LoginMethodHandler.createAccessTokenFromWebBundle(request.getPermissions(), extras, AccessTokenSource.FACEBOOK_APPLICATION_WEB, request.getApplicationId()));
            } catch (FacebookException ex) {
                return Result.createErrorResult(request, result, ex.getMessage());
            }
        } else if (ServerProtocol.errorsProxyAuthDisabled.contains(error)) {
            return result;
        } else {
            if (ServerProtocol.errorsUserCanceled.contains(error)) {
                return Result.createCancelResult(request, result);
            }
            return Result.createErrorResult(request, error, errorMessage, errorCode);
        }
    }

    protected boolean tryIntent(Intent intent, int requestCode) {
        if (intent == null) {
            return false;
        }
        try {
            this.loginClient.getFragment().startActivityForResult(intent, requestCode);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

    KatanaProxyLoginMethodHandler(Parcel source) {
        super(source);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }
}
