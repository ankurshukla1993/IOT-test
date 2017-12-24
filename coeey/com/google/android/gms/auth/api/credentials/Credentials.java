package com.google.android.gms.auth.api.credentials;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.Auth.AuthCredentialsOptions;

public class Credentials {
    public static CredentialsClient getClient(@NonNull Activity activity) {
        return new CredentialsClient(activity, AuthCredentialsOptions.zzeae);
    }

    public static CredentialsClient getClient(@NonNull Context context) {
        return new CredentialsClient(context, AuthCredentialsOptions.zzeae);
    }
}
