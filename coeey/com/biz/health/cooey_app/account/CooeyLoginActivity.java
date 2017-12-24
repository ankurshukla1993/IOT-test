package com.biz.health.cooey_app.account;

import android.app.ProgressDialog;
import android.arch.lifecycle.LifecycleObserver;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.medicine.AddMedicineFullScreenDialog;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.services.ApiClient;
import com.cooey.common.services.PartnerConfigService;
import com.cooey.common.services.SessionsService;
import com.cooey.common.services.UsersService;
import com.cooey.common.services.requests.CreateSessionRequest;
import com.cooey.common.services.requests.CreateSessionRequest.AuthenticationProviderEnum;
import com.cooey.common.services.requests.CreateSessionRequest.TypeEnum;
import com.cooey.common.vo.Channel;
import com.cooey.common.vo.ChannelType;
import com.cooey.common.vo.Session;
import com.crashlytics.android.Crashlytics;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuth.AuthStateListener;
import com.google.firebase.iid.FirebaseInstanceId;
import de.hdodenhof.circleimageview.CircleImageView;
import io.fabric.sdk.android.Fabric;
import io.fabric.sdk.android.Kit;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005B\u0005¢\u0006\u0002\u0010\u0006J\u0016\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\u0006\u0010O\u001a\u00020PJ\u0012\u0010Q\u001a\u00020L2\b\u0010R\u001a\u0004\u0018\u00010SH\u0002J\u0016\u0010T\u001a\u00020L2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020W0VH\u0002J\u0016\u0010X\u001a\u00020L2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020W0VH\u0002J\u0016\u0010Y\u001a\u00020L2\f\u0010Z\u001a\b\u0012\u0004\u0012\u00020\\0[H\u0002J \u0010]\u001a\u00020L2\u0006\u0010^\u001a\u00020\b2\u0006\u0010_\u001a\u00020\b2\u0006\u0010`\u001a\u00020aH\u0016J\u0012\u0010b\u001a\u00020L2\b\u0010c\u001a\u0004\u0018\u00010dH\u0016J\u0012\u0010e\u001a\u00020L2\b\u0010f\u001a\u0004\u0018\u00010gH\u0016J\u0010\u0010h\u001a\u00020L2\u0006\u0010f\u001a\u00020iH\u0016J\u0010\u0010j\u001a\u00020L2\u0006\u0010f\u001a\u00020\bH\u0016J\u0012\u0010k\u001a\u00020L2\b\u0010l\u001a\u0004\u0018\u00010gH\u0014J\b\u0010m\u001a\u00020LH\u0016J\b\u0010n\u001a\u00020LH\u0016J\u0012\u0010o\u001a\u00020L2\b\u0010p\u001a\u0004\u0018\u00010WH\u0002J\b\u0010q\u001a\u00020LH\u0002J\b\u0010r\u001a\u00020PH\u0002R\u000e\u0010\u0007\u001a\u00020\bXD¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nXD¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001c\u0010#\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010 \"\u0004\b%\u0010\"R\u001c\u0010&\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010+\u001a\u0004\u0018\u00010,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u00101\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010(\"\u0004\b3\u0010*R\u001c\u00104\u001a\u0004\u0018\u00010,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010.\"\u0004\b6\u00100R\u001c\u00107\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010(\"\u0004\b9\u0010*R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010?X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010@\u001a\u0004\u0018\u00010AX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010B\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010(\"\u0004\bD\u0010*R\u001c\u0010E\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010(\"\u0004\bG\u0010*R\u001c\u0010H\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010\u0014\"\u0004\bJ\u0010\u0016¨\u0006s"}, d2 = {"Lcom/biz/health/cooey_app/account/CooeyLoginActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Landroid/arch/lifecycle/LifecycleObserver;", "Lcom/google/android/gms/common/api/GoogleApiClient$OnConnectionFailedListener;", "Lcom/google/android/gms/common/api/GoogleApiClient$ConnectionCallbacks;", "Landroid/view/View$OnClickListener;", "()V", "RC_SIGN_IN", "", "TAG", "", "apiClient", "Lcom/cooey/common/services/ApiClient;", "getApiClient", "()Lcom/cooey/common/services/ApiClient;", "setApiClient", "(Lcom/cooey/common/services/ApiClient;)V", "btnLogin", "Landroid/widget/TextView;", "getBtnLogin", "()Landroid/widget/TextView;", "setBtnLogin", "(Landroid/widget/TextView;)V", "defaultfacebookButton", "Lcom/facebook/login/widget/LoginButton;", "getDefaultfacebookButton", "()Lcom/facebook/login/widget/LoginButton;", "setDefaultfacebookButton", "(Lcom/facebook/login/widget/LoginButton;)V", "edtEmail", "Landroid/widget/EditText;", "getEdtEmail", "()Landroid/widget/EditText;", "setEdtEmail", "(Landroid/widget/EditText;)V", "edtpassword", "getEdtpassword", "setEdtpassword", "email", "getEmail", "()Ljava/lang/String;", "setEmail", "(Ljava/lang/String;)V", "facebookLogin", "Lde/hdodenhof/circleimageview/CircleImageView;", "getFacebookLogin", "()Lde/hdodenhof/circleimageview/CircleImageView;", "setFacebookLogin", "(Lde/hdodenhof/circleimageview/CircleImageView;)V", "firstName", "getFirstName", "setFirstName", "googleLogin", "getGoogleLogin", "setGoogleLogin", "lastName", "getLastName", "setLastName", "mAuth", "Lcom/google/firebase/auth/FirebaseAuth;", "mAuthListener", "Lcom/google/firebase/auth/FirebaseAuth$AuthStateListener;", "mCallbackManager", "Lcom/facebook/CallbackManager;", "mGoogleApiClient", "Lcom/google/android/gms/common/api/GoogleApiClient;", "password", "getPassword", "setPassword", "profilePic", "getProfilePic", "setProfilePic", "textRegisterUser", "getTextRegisterUser", "setTextRegisterUser", "cooeySignIn", "", "createSessionRequest", "Lcom/cooey/common/services/requests/CreateSessionRequest;", "isboolean", "", "getFacebookDisplayName", "result", "Lcom/facebook/login/LoginResult;", "getPartnerConfig", "response", "Lretrofit2/Response;", "Lcom/cooey/common/vo/Session;", "getUser", "handleSignInResult", "completedTask", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/auth/api/signin/GoogleSignInAccount;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onClick", "v", "Landroid/view/View;", "onConnected", "p0", "Landroid/os/Bundle;", "onConnectionFailed", "Lcom/google/android/gms/common/ConnectionResult;", "onConnectionSuspended", "onCreate", "savedInstanceState", "onStart", "onStop", "registerFCMToken", "session", "signIn", "validateFields", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: CooeyLoginActivity.kt */
public final class CooeyLoginActivity extends AppCompatActivity implements LifecycleObserver, OnConnectionFailedListener, ConnectionCallbacks, OnClickListener {
    private final int RC_SIGN_IN = 9001;
    private final String TAG = "CooeyLoginActivity";
    private HashMap _$_findViewCache;
    @Nullable
    private ApiClient apiClient;
    @Nullable
    private TextView btnLogin;
    @Nullable
    private LoginButton defaultfacebookButton;
    @Nullable
    private EditText edtEmail;
    @Nullable
    private EditText edtpassword;
    @Nullable
    private String email;
    @Nullable
    private CircleImageView facebookLogin;
    @Nullable
    private String firstName;
    @Nullable
    private CircleImageView googleLogin;
    @Nullable
    private String lastName;
    private FirebaseAuth mAuth;
    private AuthStateListener mAuthListener;
    private CallbackManager mCallbackManager;
    private GoogleApiClient mGoogleApiClient;
    @Nullable
    private String password;
    @Nullable
    private String profilePic;
    @Nullable
    private TextView textRegisterUser;

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    @Nullable
    public final CircleImageView getFacebookLogin() {
        return this.facebookLogin;
    }

    public final void setFacebookLogin(@Nullable CircleImageView <set-?>) {
        this.facebookLogin = <set-?>;
    }

    @Nullable
    public final CircleImageView getGoogleLogin() {
        return this.googleLogin;
    }

    public final void setGoogleLogin(@Nullable CircleImageView <set-?>) {
        this.googleLogin = <set-?>;
    }

    @Nullable
    public final ApiClient getApiClient() {
        return this.apiClient;
    }

    public final void setApiClient(@Nullable ApiClient <set-?>) {
        this.apiClient = <set-?>;
    }

    @Nullable
    public final TextView getTextRegisterUser() {
        return this.textRegisterUser;
    }

    public final void setTextRegisterUser(@Nullable TextView <set-?>) {
        this.textRegisterUser = <set-?>;
    }

    @Nullable
    public final TextView getBtnLogin() {
        return this.btnLogin;
    }

    public final void setBtnLogin(@Nullable TextView <set-?>) {
        this.btnLogin = <set-?>;
    }

    @Nullable
    public final EditText getEdtEmail() {
        return this.edtEmail;
    }

    public final void setEdtEmail(@Nullable EditText <set-?>) {
        this.edtEmail = <set-?>;
    }

    @Nullable
    public final EditText getEdtpassword() {
        return this.edtpassword;
    }

    public final void setEdtpassword(@Nullable EditText <set-?>) {
        this.edtpassword = <set-?>;
    }

    @Nullable
    public final String getEmail() {
        return this.email;
    }

    public final void setEmail(@Nullable String <set-?>) {
        this.email = <set-?>;
    }

    @Nullable
    public final LoginButton getDefaultfacebookButton() {
        return this.defaultfacebookButton;
    }

    public final void setDefaultfacebookButton(@Nullable LoginButton <set-?>) {
        this.defaultfacebookButton = <set-?>;
    }

    @Nullable
    public final String getPassword() {
        return this.password;
    }

    public final void setPassword(@Nullable String <set-?>) {
        this.password = <set-?>;
    }

    @Nullable
    public final String getFirstName() {
        return this.firstName;
    }

    public final void setFirstName(@Nullable String <set-?>) {
        this.firstName = <set-?>;
    }

    @Nullable
    public final String getLastName() {
        return this.lastName;
    }

    public final void setLastName(@Nullable String <set-?>) {
        this.lastName = <set-?>;
    }

    @Nullable
    public final String getProfilePic() {
        return this.profilePic;
    }

    public final void setProfilePic(@Nullable String <set-?>) {
        this.profilePic = <set-?>;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(this);
        setContentView((int) C0644R.layout.activity_cooey_login);
        this.mCallbackManager = Factory.create();
        Fabric.with(this, new Kit[]{new Crashlytics()});
        this.facebookLogin = (CircleImageView) findViewById(C0644R.id.btn_facebook);
        this.defaultfacebookButton = (LoginButton) findViewById(C0644R.id.authButton);
        this.googleLogin = (CircleImageView) findViewById(C0644R.id.btn_google);
        this.btnLogin = (TextView) findViewById(C0644R.id.btn_login);
        this.edtEmail = (EditText) findViewById(C0644R.id.edt_username);
        this.edtpassword = (EditText) findViewById(C0644R.id.edt_password);
        TextView textView = this.btnLogin;
        if (textView != null) {
            textView.setOnClickListener(this);
        }
        this.textRegisterUser = (TextView) findViewById(C0644R.id.txt_sign_up);
        textView = this.textRegisterUser;
        if (textView != null) {
            textView.setOnClickListener(this);
        }
        CircleImageView circleImageView = this.googleLogin;
        if (circleImageView != null) {
            circleImageView.setOnClickListener(new CooeyLoginActivity$onCreate$1(this));
        }
        circleImageView = this.facebookLogin;
        if (circleImageView != null) {
            circleImageView.setOnClickListener(this);
        }
        LoginButton loginButton = this.defaultfacebookButton;
        if (loginButton != null) {
            loginButton.setReadPermissions(new String[]{"email, public_profile,user_birthday,user_friends"});
        }
        LoginButton loginButton2 = this.defaultfacebookButton;
        if (loginButton2 != null) {
            loginButton2.registerCallback(this.mCallbackManager, new CooeyLoginActivity$onCreate$2(this));
        }
        this.mGoogleApiClient = new Builder(this).addConnectionCallbacks(this).enableAutoManage(this, this).addApi(Auth.GOOGLE_SIGN_IN_API, new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(getResources().getString(C0644R.string.default_web_client_id)).requestEmail().build()).build();
        this.mAuth = FirebaseAuth.getInstance();
        this.apiClient = new ApiClient(this, "http://api.cooey.co.in/ehealth/");
        this.mAuthListener = new CooeyLoginActivity$onCreate$3(this);
    }

    private final void getFacebookDisplayName(LoginResult result) {
        AccessToken accessToken;
        if (result != null) {
            accessToken = result.getAccessToken();
        } else {
            accessToken = null;
        }
        GraphRequest graphRequest = GraphRequest.newMeRequest(accessToken, new CooeyLoginActivity$getFacebookDisplayName$graphRequest$1(this, result));
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id,email,first_name,last_name,gender");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    public void onStart() {
        super.onStart();
        FirebaseAuth firebaseAuth = this.mAuth;
        if (firebaseAuth != null) {
            AuthStateListener authStateListener = this.mAuthListener;
            if (authStateListener == null) {
                Intrinsics.throwNpe();
            }
            firebaseAuth.addAuthStateListener(authStateListener);
        }
    }

    public void onStop() {
        super.onStop();
        if (this.mAuthListener != null) {
            FirebaseAuth firebaseAuth = this.mAuth;
            if (firebaseAuth != null) {
                AuthStateListener authStateListener = this.mAuthListener;
                if (authStateListener == null) {
                    Intrinsics.throwNpe();
                }
                firebaseAuth.removeAuthStateListener(authStateListener);
            }
        }
    }

    private final void signIn() {
        startActivityForResult(Auth.GoogleSignInApi.getSignInIntent(this.mGoogleApiClient), this.RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, int resultCode, @NotNull Intent data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        super.onActivityResult(requestCode, resultCode, data);
        CallbackManager callbackManager = this.mCallbackManager;
        if (callbackManager != null) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
        if (requestCode == this.RC_SIGN_IN) {
            Task task = GoogleSignIn.getSignedInAccountFromIntent(data);
            Intrinsics.checkExpressionValueIsNotNull(task, "task");
            handleSignInResult(task);
        }
    }

    private final void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        String str = null;
        try {
            String id;
            GoogleSignInAccount account = (GoogleSignInAccount) completedTask.getResult(ApiException.class);
            CreateSessionRequest createSessionRequest = new CreateSessionRequest();
            if (account != null) {
                id = account.getId();
            } else {
                id = null;
            }
            createSessionRequest.setAuthId(id);
            createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.GOOGLE);
            createSessionRequest.setType(TypeEnum.PATIENT);
            if (account != null) {
                id = account.getIdToken();
            } else {
                id = null;
            }
            createSessionRequest.setAuthToken(id);
            createSessionRequest.setTenantId(getString(C0644R.string.tenant_id));
            if (account != null) {
                id = account.getEmail();
            } else {
                id = null;
            }
            createSessionRequest.setEmail(id);
            createSessionRequest.setActive(true);
            if (account != null) {
                id = account.getDisplayName();
            } else {
                id = null;
            }
            this.firstName = id;
            if (account != null) {
                str = account.getFamilyName();
            }
            this.lastName = str;
            cooeySignIn(createSessionRequest, true);
        } catch (ApiException e) {
            Log.w(this.TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    public final void cooeySignIn(@NotNull CreateSessionRequest createSessionRequest, boolean isboolean) {
        Intrinsics.checkParameterIsNotNull(createSessionRequest, "createSessionRequest");
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait");
        dialog.setTitle("Logging in...");
        dialog.setCancelable(true);
        dialog.setIndeterminate(true);
        dialog.show();
        try {
            ApiClient apiClient = this.apiClient;
            if (apiClient != null) {
                SessionsService sessionsService = apiClient.getSessionsService();
                if (sessionsService != null) {
                    Call create = sessionsService.create(createSessionRequest);
                    if (create != null) {
                        create.enqueue(new CooeyLoginActivity$cooeySignIn$1(this, isboolean, createSessionRequest, dialog));
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Sign in failed,Please try once again", 0).show();
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    public void onClick(@Nullable View v) {
        Integer valueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (valueOf != null && valueOf.intValue() == C0644R.id.txt_sign_up) {
            startActivity(new Intent(this, RegisterCooeyUserActivity.class));
        } else if (valueOf != null && valueOf.intValue() == C0644R.id.btn_login) {
            if (validateFields()) {
                CreateSessionRequest createSessionRequest = new CreateSessionRequest();
                createSessionRequest.setEmail(this.email);
                createSessionRequest.setPassword(this.password);
                createSessionRequest.setType(TypeEnum.PATIENT);
                createSessionRequest.setTenantId(getString(C0644R.string.tenant_id));
                createSessionRequest.setActive(true);
                createSessionRequest.setAuthenticationProvider(AuthenticationProviderEnum.COOEY);
                cooeySignIn(createSessionRequest, false);
            }
        } else if (valueOf != null && valueOf.intValue() == C0644R.id.btn_facebook) {
            LoginButton loginButton = this.defaultfacebookButton;
            if (loginButton != null) {
                loginButton.performClick();
            }
        }
    }

    private final boolean validateFields() {
        Object obj = null;
        try {
            EditText editText = this.edtEmail;
            this.email = String.valueOf(editText != null ? editText.getText() : null);
            if (this.email != null) {
                String str = this.email;
                if (str == null || str.length() != 0) {
                    editText = this.edtpassword;
                    if (editText != null) {
                        obj = editText.getText();
                    }
                    this.password = String.valueOf(obj);
                    if (this.password != null) {
                        String str2 = this.password;
                        if (str2 == null || str2.length() != 0) {
                            return true;
                        }
                    }
                    AddMedicineFullScreenDialog.buildErrorDialog(this, getString(C0644R.string.enter_password));
                    return false;
                }
            }
            AddMedicineFullScreenDialog.buildErrorDialog(this, getString(C0644R.string.enter_valide_mail));
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private final void getPartnerConfig(Response<Session> response) {
        Object body;
        String token = null;
        if (response.body() != null) {
            body = response.body();
            if (body == null) {
                Intrinsics.throwNpe();
            }
            if (((Session) body).getId() != null) {
                body = response.body();
                if (body == null) {
                    Intrinsics.throwNpe();
                }
                token = ((Session) body).getId();
            }
        }
        if (token != null) {
            ApiClient apiClient = this.apiClient;
            if (apiClient != null) {
                PartnerConfigService partnerConfigService = apiClient.getPartnerConfigService();
                if (partnerConfigService != null) {
                    body = response.body();
                    if (body == null) {
                        Intrinsics.throwNpe();
                    }
                    Call partnerConfig = partnerConfigService.getPartnerConfig(token, ((Session) body).getTenantId(), CTConstants.PATIENTTYPE);
                    if (partnerConfig != null) {
                        partnerConfig.enqueue(new CooeyLoginActivity$getPartnerConfig$1(this));
                    }
                }
            }
        }
    }

    private final void getUser(Response<Session> response) {
        Session session = (Session) response.body();
        ApiClient apiClient = this.apiClient;
        if (apiClient != null) {
            UsersService usersService = apiClient.getUsersService();
            if (usersService != null) {
                if (session == null) {
                    Intrinsics.throwNpe();
                }
                Call _0 = usersService.get_0(session.getUserId(), session.getId());
                if (_0 != null) {
                    _0.enqueue(new CooeyLoginActivity$getUser$1(this, session));
                }
            }
        }
    }

    private final void registerFCMToken(Session session) {
        String token = FirebaseInstanceId.getInstance().getToken();
        ApiClient apiClient = new ApiClient(this, "http://api.cooey.co.in/ehealth/");
        if (session != null && session.getId() != null && token != null) {
            String patientId = session.getUserId();
            Channel channel = new Channel();
            channel.setSource("ANDROID");
            channel.setSourceId(token);
            channel.setType(ChannelType.NOTIFICATION);
            channel.setUserId(patientId);
            apiClient.getChannelsService().create(channel).enqueue(new CooeyLoginActivity$registerFCMToken$1());
        }
    }

    public void onConnected(@Nullable Bundle p0) {
    }

    public void onConnectionSuspended(int p0) {
    }

    public void onConnectionFailed(@NotNull ConnectionResult p0) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
    }
}
