package com.google.android.gms.auth.api.signin.internal;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInStatusCodes;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;

@KeepName
public class SignInHubActivity extends FragmentActivity {
    private static boolean zzefn = false;
    private boolean zzefo = false;
    @VisibleForTesting
    private SignInConfiguration zzefp;
    private boolean zzefq;
    private int zzefr;
    private Intent zzefs;

    class zza implements LoaderCallbacks<Void> {
        private /* synthetic */ SignInHubActivity zzeft;

        private zza(SignInHubActivity signInHubActivity) {
            this.zzeft = signInHubActivity;
        }

        public final Loader<Void> onCreateLoader(int i, Bundle bundle) {
            return new zzb(this.zzeft, GoogleApiClient.zzage());
        }

        public final /* synthetic */ void onLoadFinished(Loader loader, Object obj) {
            this.zzeft.setResult(this.zzeft.zzefr, this.zzeft.zzefs);
            this.zzeft.finish();
        }

        public final void onLoaderReset(Loader<Void> loader) {
        }
    }

    private final void zzabf() {
        getSupportLoaderManager().initLoader(0, null, new zza());
        zzefn = false;
    }

    private final void zzba(int i) {
        Parcelable status = new Status(i);
        Intent intent = new Intent();
        intent.putExtra("googleSignInStatus", status);
        setResult(0, intent);
        finish();
        zzefn = false;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return true;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (!this.zzefo) {
            setResult(0);
            switch (i) {
                case 40962:
                    if (intent != null) {
                        SignInAccount signInAccount = (SignInAccount) intent.getParcelableExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                        if (signInAccount != null && signInAccount.getGoogleSignInAccount() != null) {
                            Parcelable googleSignInAccount = signInAccount.getGoogleSignInAccount();
                            zzo.zzbp(this).zza(this.zzefp.zzabe(), googleSignInAccount);
                            intent.removeExtra(GoogleSignInApi.EXTRA_SIGN_IN_ACCOUNT);
                            intent.putExtra("googleSignInAccount", googleSignInAccount);
                            this.zzefq = true;
                            this.zzefr = i2;
                            this.zzefs = intent;
                            zzabf();
                            return;
                        } else if (intent.hasExtra("errorCode")) {
                            zzba(intent.getIntExtra("errorCode", 8));
                            return;
                        }
                    }
                    zzba(8);
                    return;
                default:
                    return;
            }
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        String action = intent.getAction();
        if ("com.google.android.gms.auth.NO_IMPL".equals(action)) {
            zzba(GoogleSignInStatusCodes.SIGN_IN_FAILED);
        } else if (zzefn) {
            setResult(0);
            zzba(GoogleSignInStatusCodes.SIGN_IN_CURRENTLY_IN_PROGRESS);
        } else {
            zzefn = true;
            if (action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN") || action.equals("com.google.android.gms.auth.APPAUTH_SIGN_IN")) {
                this.zzefp = (SignInConfiguration) intent.getBundleExtra("config").getParcelable("config");
                if (this.zzefp == null) {
                    Log.e("AuthSignInClient", "Activity started with invalid configuration.");
                    setResult(0);
                    finish();
                    return;
                } else if (bundle == null) {
                    intent = new Intent(action);
                    if (action.equals("com.google.android.gms.auth.GOOGLE_SIGN_IN")) {
                        intent.setPackage("com.google.android.gms");
                    } else {
                        intent.setPackage(getPackageName());
                    }
                    intent.putExtra("config", this.zzefp);
                    try {
                        startActivityForResult(intent, 40962);
                        return;
                    } catch (ActivityNotFoundException e) {
                        this.zzefo = true;
                        Log.w("AuthSignInClient", "Could not launch sign in Intent. Google Play Service is probably being updated...");
                        zzba(17);
                        return;
                    }
                } else {
                    this.zzefq = bundle.getBoolean("signingInGoogleApiClients");
                    if (this.zzefq) {
                        this.zzefr = bundle.getInt("signInResultCode");
                        this.zzefs = (Intent) bundle.getParcelable("signInResultData");
                        zzabf();
                        return;
                    }
                    return;
                }
            }
            action = "AuthSignInClient";
            String str = "Unknown action: ";
            String valueOf = String.valueOf(intent.getAction());
            Log.e(action, valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            finish();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("signingInGoogleApiClients", this.zzefq);
        if (this.zzefq) {
            bundle.putInt("signInResultCode", this.zzefr);
            bundle.putParcelable("signInResultData", this.zzefs);
        }
    }
}
