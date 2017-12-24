package com.biz.health.cooey_app.account;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions;
import com.appeaser.sublimepickerlibrary.helpers.SublimeOptions.Picker;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker.RecurrenceOption;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.utils.DateUtil;
import com.biz.health.cooey_app.vitals.SublimePickerFragment.Callback;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.services.ApiClient;
import com.cooey.common.services.PartnerConfigService;
import com.cooey.common.services.SessionsService;
import com.cooey.common.services.UsersService;
import com.cooey.common.services.requests.CreateSessionRequest;
import com.cooey.common.services.requests.CreateSessionRequest.TypeEnum;
import com.cooey.common.services.requests.CreateUserRequest;
import com.cooey.common.services.requests.CreateUserRequest.AuthenticationProviderEnum;
import com.cooey.common.vo.Channel;
import com.cooey.common.vo.ChannelType;
import com.cooey.common.vo.Session;
import com.google.firebase.iid.FirebaseInstanceId;
import com.lifesense.ble.bean.SexType;
import humanize.util.Constants;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\u0016\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020I2\u0006\u0010J\u001a\u00020\fJ\u000e\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020NJ\b\u0010O\u001a\u00020LH\u0002J\u0019\u0010P\u001a\u000e\u0012\u0004\u0012\u00020R\u0012\u0004\u0012\u00020S0QH\u0000¢\u0006\u0002\bTJ\u0016\u0010U\u001a\u00020L2\f\u0010V\u001a\b\u0012\u0004\u0012\u00020X0WH\u0002J\b\u0010Y\u001a\u00020LH\u0002J\b\u0010Z\u001a\u00020LH\u0002J\u0010\u0010[\u001a\u00020R2\u0006\u0010:\u001a\u00020\fH\u0002J\b\u0010\\\u001a\u00020LH\u0016J\u0012\u0010]\u001a\u00020L2\b\u0010^\u001a\u0004\u0018\u00010_H\u0016J\u0012\u0010`\u001a\u00020L2\b\u0010a\u001a\u0004\u0018\u00010bH\u0014JR\u0010c\u001a\u0010\u0012\u0004\u0012\u00020R\u0012\u0004\u0012\u00020S\u0018\u00010Q2\u0006\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020e2\u0006\u0010g\u001a\u00020e2\u0006\u0010h\u001a\u00020e2\u0006\u0010i\u001a\u00020e2\b\u0010j\u001a\u0004\u0018\u00010k2\b\u0010l\u001a\u0004\u0018\u00010\fH\u0016J\u0010\u0010m\u001a\u00020L2\b\u0010^\u001a\u0004\u0018\u00010_J\u0012\u0010n\u001a\u00020L2\b\u0010o\u001a\u0004\u0018\u00010XH\u0002J\b\u0010p\u001a\u00020RH\u0002R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000e\"\u0004\b\u0013\u0010\u0010R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u000e\"\u0004\b\u0016\u0010\u0010R\u001c\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u0010\u0010\u001d\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u001f\u001a\u00020 X.¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010+\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010(\"\u0004\b-\u0010*R\u001c\u0010.\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010(\"\u0004\b0\u0010*R\u001c\u00101\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010(\"\u0004\b3\u0010*R\u001c\u00104\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010(\"\u0004\b6\u0010*R\u001c\u00107\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010(\"\u0004\b9\u0010*R\u0010\u0010:\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010;\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010<\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010=\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010>\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010?\u001a\u0004\u0018\u00010\fX\u000e¢\u0006\u0002\n\u0000R\u001c\u0010@\u001a\u0004\u0018\u00010AX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010E¨\u0006q"}, d2 = {"Lcom/biz/health/cooey_app/account/RegisterCooeyUserActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Landroid/view/View$OnClickListener;", "Lcom/biz/health/cooey_app/vitals/SublimePickerFragment$Callback;", "()V", "apiClient", "Lcom/cooey/common/services/ApiClient;", "getApiClient", "()Lcom/cooey/common/services/ApiClient;", "setApiClient", "(Lcom/cooey/common/services/ApiClient;)V", "authId", "", "getAuthId", "()Ljava/lang/String;", "setAuthId", "(Ljava/lang/String;)V", "authToken", "getAuthToken", "setAuthToken", "authenticationProvider", "getAuthenticationProvider", "setAuthenticationProvider", "btnRegister", "Landroid/widget/Button;", "getBtnRegister", "()Landroid/widget/Button;", "setBtnRegister", "(Landroid/widget/Button;)V", "confirmPassword", "dateOfBirth", "dialog", "Landroid/app/ProgressDialog;", "getDialog", "()Landroid/app/ProgressDialog;", "setDialog", "(Landroid/app/ProgressDialog;)V", "edtConfirmPassword", "Landroid/widget/EditText;", "getEdtConfirmPassword", "()Landroid/widget/EditText;", "setEdtConfirmPassword", "(Landroid/widget/EditText;)V", "edtEmail", "getEdtEmail", "setEdtEmail", "edtFirstName", "getEdtFirstName", "setEdtFirstName", "edtPassword", "getEdtPassword", "setEdtPassword", "edtPhonenumber", "getEdtPhonenumber", "setEdtPhonenumber", "edtlastName", "getEdtlastName", "setEdtlastName", "email", "firstname", "gender", "lastname", "password", "phoneNumber", "txtDateOfBirth", "Landroid/widget/TextView;", "getTxtDateOfBirth", "()Landroid/widget/TextView;", "setTxtDateOfBirth", "(Landroid/widget/TextView;)V", "buildErrorDialog", "Landroid/support/v7/app/AlertDialog;", "context", "Landroid/content/Context;", "message", "cooeySignIn", "", "createSessionRequest", "Lcom/cooey/common/services/requests/CreateSessionRequest;", "createUser", "getOptions", "Landroid/support/v4/util/Pair;", "", "Lcom/appeaser/sublimepickerlibrary/helpers/SublimeOptions;", "getOptions$app_release", "getPartnerConfig", "response", "Lretrofit2/Response;", "Lcom/cooey/common/vo/Session;", "getUsernameAndAuthId", "initViews", "isValidEmail", "onCancelled", "onClick", "v", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDateTimeRecurrenceSet", "year", "", "monthOfYear", "dayOfMonth", "hourOfDay", "minute", "recurrenceOption", "Lcom/appeaser/sublimepickerlibrary/recurrencepicker/SublimeRecurrencePicker$RecurrenceOption;", "recurrenceRule", "onRadioButtonClicked", "registerFCMToken", "session", "validateFields", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: RegisterCooeyUserActivity.kt */
public final class RegisterCooeyUserActivity extends AppCompatActivity implements OnClickListener, Callback {
    private HashMap _$_findViewCache;
    @Nullable
    private ApiClient apiClient;
    @Nullable
    private String authId;
    @Nullable
    private String authToken;
    @Nullable
    private String authenticationProvider;
    @Nullable
    private Button btnRegister;
    private String confirmPassword;
    private String dateOfBirth;
    @NotNull
    public ProgressDialog dialog;
    @Nullable
    private EditText edtConfirmPassword;
    @Nullable
    private EditText edtEmail;
    @Nullable
    private EditText edtFirstName;
    @Nullable
    private EditText edtPassword;
    @Nullable
    private EditText edtPhonenumber;
    @Nullable
    private EditText edtlastName;
    private String email;
    private String firstname;
    private String gender;
    private String lastname;
    private String password;
    private String phoneNumber;
    @Nullable
    private TextView txtDateOfBirth;

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
    public final Button getBtnRegister() {
        return this.btnRegister;
    }

    public final void setBtnRegister(@Nullable Button <set-?>) {
        this.btnRegister = <set-?>;
    }

    @Nullable
    public final EditText getEdtFirstName() {
        return this.edtFirstName;
    }

    public final void setEdtFirstName(@Nullable EditText <set-?>) {
        this.edtFirstName = <set-?>;
    }

    @Nullable
    public final EditText getEdtlastName() {
        return this.edtlastName;
    }

    public final void setEdtlastName(@Nullable EditText <set-?>) {
        this.edtlastName = <set-?>;
    }

    @Nullable
    public final EditText getEdtEmail() {
        return this.edtEmail;
    }

    public final void setEdtEmail(@Nullable EditText <set-?>) {
        this.edtEmail = <set-?>;
    }

    @Nullable
    public final EditText getEdtPhonenumber() {
        return this.edtPhonenumber;
    }

    public final void setEdtPhonenumber(@Nullable EditText <set-?>) {
        this.edtPhonenumber = <set-?>;
    }

    @Nullable
    public final TextView getTxtDateOfBirth() {
        return this.txtDateOfBirth;
    }

    public final void setTxtDateOfBirth(@Nullable TextView <set-?>) {
        this.txtDateOfBirth = <set-?>;
    }

    @Nullable
    public final EditText getEdtPassword() {
        return this.edtPassword;
    }

    public final void setEdtPassword(@Nullable EditText <set-?>) {
        this.edtPassword = <set-?>;
    }

    @Nullable
    public final EditText getEdtConfirmPassword() {
        return this.edtConfirmPassword;
    }

    public final void setEdtConfirmPassword(@Nullable EditText <set-?>) {
        this.edtConfirmPassword = <set-?>;
    }

    @Nullable
    public final ApiClient getApiClient() {
        return this.apiClient;
    }

    public final void setApiClient(@Nullable ApiClient <set-?>) {
        this.apiClient = <set-?>;
    }

    @NotNull
    public final ProgressDialog getDialog() {
        ProgressDialog progressDialog = this.dialog;
        if (progressDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialog");
        }
        return progressDialog;
    }

    public final void setDialog(@NotNull ProgressDialog <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.dialog = <set-?>;
    }

    @Nullable
    public final String getAuthId() {
        return this.authId;
    }

    public final void setAuthId(@Nullable String <set-?>) {
        this.authId = <set-?>;
    }

    @Nullable
    public final String getAuthToken() {
        return this.authToken;
    }

    public final void setAuthToken(@Nullable String <set-?>) {
        this.authToken = <set-?>;
    }

    @Nullable
    public final String getAuthenticationProvider() {
        return this.authenticationProvider;
    }

    public final void setAuthenticationProvider(@Nullable String <set-?>) {
        this.authenticationProvider = <set-?>;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView((int) C0644R.layout.activity_cooey_user_register);
        initViews();
        getUsernameAndAuthId();
        this.apiClient = new ApiClient(this, "http://api.cooey.co.in/ehealth/");
        this.dialog = new ProgressDialog(this);
        ProgressDialog progressDialog = this.dialog;
        if (progressDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialog");
        }
        progressDialog.setMessage("Please wait");
        progressDialog = this.dialog;
        if (progressDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialog");
        }
        progressDialog.setTitle("Registering...");
        ProgressDialog progressDialog2 = this.dialog;
        if (progressDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialog");
        }
        progressDialog2.setCancelable(true);
        progressDialog2 = this.dialog;
        if (progressDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialog");
        }
        progressDialog2.setIndeterminate(true);
        Button button = this.btnRegister;
        if (button != null) {
            button.setOnClickListener(this);
        }
    }

    private final void getUsernameAndAuthId() {
        Intent intent = getIntent();
        if (intent.getExtras() != null && intent.getExtras().getString("authId") != null && intent.getExtras().getString("authToken") != null && intent.getExtras().getString("authenticationProvider") != null) {
            EditText editText;
            String authIdIntent = intent.getStringExtra("authId");
            String authTokenIntent = intent.getStringExtra("authToken");
            String authenticationProviderIntent = intent.getStringExtra("authenticationProvider");
            if (intent.getExtras().getString("firstName") != null) {
                EditText editText2 = this.edtFirstName;
                if (editText2 != null) {
                    editText2.setText((CharSequence) StringsKt__StringsKt.split$default((CharSequence) intent.getStringExtra("firstName"), new String[]{Constants.SPACE}, false, 0, 6, null).get(0));
                }
            }
            if (intent.getExtras().getString("lastName") != null) {
                editText = this.edtlastName;
                if (editText != null) {
                    editText.setText(intent.getStringExtra("lastName"));
                }
            }
            if (intent.getStringExtra("email") != null) {
                editText = this.edtEmail;
                if (editText != null) {
                    editText.setText(intent.getStringExtra("email"));
                }
            }
            this.authId = authIdIntent;
            this.authToken = authTokenIntent;
            if (authIdIntent != null && authIdIntent.toString().length() > 0) {
                this.authId = authIdIntent;
            }
            if (authTokenIntent != null && authTokenIntent.toString().length() > 0) {
                this.authToken = authTokenIntent;
            }
            if (authenticationProviderIntent != null && authenticationProviderIntent.toString().length() > 0) {
                this.authenticationProvider = authenticationProviderIntent;
            }
        }
    }

    private final void initViews() {
        this.btnRegister = (Button) findViewById(C0644R.id.btn_register);
        this.edtFirstName = (EditText) findViewById(C0644R.id.edt_first_name);
        this.edtlastName = (EditText) findViewById(C0644R.id.edt_last_name);
        this.edtEmail = (EditText) findViewById(C0644R.id.edt_email);
        this.edtPhonenumber = (EditText) findViewById(C0644R.id.edt_phone_number);
        this.txtDateOfBirth = (TextView) findViewById(C0644R.id.edt_dob);
        this.edtPassword = (EditText) findViewById(C0644R.id.edt_password);
        this.edtConfirmPassword = (EditText) findViewById(C0644R.id.edt_confirm_password);
        TextView textView = this.txtDateOfBirth;
        if (textView != null) {
            textView.setOnClickListener(new RegisterCooeyUserActivity$initViews$1(this));
        }
    }

    public void onClick(@Nullable View v) {
        Integer valueOf = v != null ? Integer.valueOf(v.getId()) : null;
        if (valueOf != null && valueOf.intValue() == C0644R.id.btn_register && validateFields()) {
            createUser();
        }
    }

    private final void createUser() {
        ProgressDialog progressDialog = this.dialog;
        if (progressDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dialog");
        }
        progressDialog.show();
        CreateUserRequest patientProfile = new CreateUserRequest();
        patientProfile.setId(UUID.randomUUID().toString());
        patientProfile.setFirstName(this.firstname);
        EditText editText = this.edtlastName;
        this.lastname = String.valueOf(editText != null ? editText.getText() : null);
        if (this.lastname != null) {
            patientProfile.setLastName(this.lastname);
        }
        patientProfile.setEmail(this.email);
        patientProfile.setPassword(this.password);
        patientProfile.setGender(this.gender);
        patientProfile.setType(TypeEnum.PATIENT.name());
        patientProfile.setMobile(this.phoneNumber);
        patientProfile.setDateOfBirth("" + DateUtil.getDateFromStringDate(this.dateOfBirth));
        if (this.authId != null && String.valueOf(this.authId).length() > 0) {
            patientProfile.setauthId(this.authId);
        }
        if (this.authToken != null && String.valueOf(this.authToken).length() > 0) {
            patientProfile.setauthToken(this.authToken);
        }
        if (this.authenticationProvider == null || String.valueOf(this.authenticationProvider).length() <= 0) {
            patientProfile.setAuthenticationProvider(AuthenticationProviderEnum.COOEY);
        } else if (StringsKt__StringsJVMKt.equals$default(this.authenticationProvider, CreateSessionRequest.AuthenticationProviderEnum.GOOGLE.name(), false, 2, null)) {
            patientProfile.setAuthenticationProvider(AuthenticationProviderEnum.GOOGLE);
        } else if (StringsKt__StringsJVMKt.equals$default(this.authenticationProvider, CreateSessionRequest.AuthenticationProviderEnum.FACEBOOK.name(), false, 2, null)) {
            patientProfile.setAuthenticationProvider(AuthenticationProviderEnum.GOOGLE);
        } else {
            patientProfile.setAuthenticationProvider(AuthenticationProviderEnum.COOEY);
        }
        ApiClient apiClient = this.apiClient;
        if (apiClient != null) {
            UsersService usersService = apiClient.getUsersService();
            if (usersService != null) {
                Call create = usersService.create("5a2a5a5f5a223dc81cc6b4a7", patientProfile);
                if (create != null) {
                    create.enqueue(new RegisterCooeyUserActivity$createUser$1(this));
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean validateFields() {
        /*
        r6 = this;
        r2 = 0;
        r3 = 0;
        r4 = r6.edtFirstName;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x0034;
    L_0x0007:
        r4 = r4.getText();	 Catch:{ Exception -> 0x011c }
    L_0x000b:
        r4 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x011c }
        r6.firstname = r4;	 Catch:{ Exception -> 0x011c }
        r4 = r6.firstname;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x001f;
    L_0x0015:
        r4 = r6.firstname;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x0036;
    L_0x0019:
        r4 = r4.length();	 Catch:{ Exception -> 0x011c }
        if (r4 != 0) goto L_0x0036;
    L_0x001f:
        r0 = r6;
        r0 = (android.content.Context) r0;	 Catch:{ Exception -> 0x011c }
        r2 = r0;
        r4 = 2131755187; // 0x7f1000b3 float:1.9141246E38 double:1.053227003E-314;
        r4 = r6.getString(r4);	 Catch:{ Exception -> 0x011c }
        r5 = "getString(R.string.enter_first_name)";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5);	 Catch:{ Exception -> 0x011c }
        r6.buildErrorDialog(r2, r4);	 Catch:{ Exception -> 0x011c }
        r2 = r3;
    L_0x0033:
        return r2;
    L_0x0034:
        r4 = r2;
        goto L_0x000b;
    L_0x0036:
        r4 = r6.edtPhonenumber;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x0067;
    L_0x003a:
        r4 = r4.getText();	 Catch:{ Exception -> 0x011c }
    L_0x003e:
        r4 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x011c }
        r6.phoneNumber = r4;	 Catch:{ Exception -> 0x011c }
        r4 = r6.phoneNumber;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x0052;
    L_0x0048:
        r4 = r6.phoneNumber;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x0069;
    L_0x004c:
        r4 = r4.length();	 Catch:{ Exception -> 0x011c }
        if (r4 != 0) goto L_0x0069;
    L_0x0052:
        r0 = r6;
        r0 = (android.content.Context) r0;	 Catch:{ Exception -> 0x011c }
        r2 = r0;
        r4 = 2131755190; // 0x7f1000b6 float:1.9141252E38 double:1.0532270047E-314;
        r4 = r6.getString(r4);	 Catch:{ Exception -> 0x011c }
        r5 = "getString(R.string.enter_mobile_numbert)";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5);	 Catch:{ Exception -> 0x011c }
        r6.buildErrorDialog(r2, r4);	 Catch:{ Exception -> 0x011c }
        r2 = r3;
        goto L_0x0033;
    L_0x0067:
        r4 = r2;
        goto L_0x003e;
    L_0x0069:
        r4 = r6.edtEmail;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x00a7;
    L_0x006d:
        r4 = r4.getText();	 Catch:{ Exception -> 0x011c }
    L_0x0071:
        r4 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x011c }
        r6.email = r4;	 Catch:{ Exception -> 0x011c }
        r4 = r6.email;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x0092;
    L_0x007b:
        r4 = r6.email;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x00a9;
    L_0x007f:
        r4 = r4.length();	 Catch:{ Exception -> 0x011c }
        if (r4 != 0) goto L_0x00a9;
    L_0x0085:
        r4 = r6.email;	 Catch:{ Exception -> 0x011c }
        if (r4 != 0) goto L_0x008c;
    L_0x0089:
        kotlin.jvm.internal.Intrinsics.throwNpe();	 Catch:{ Exception -> 0x011c }
    L_0x008c:
        r4 = r6.isValidEmail(r4);	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x00a9;
    L_0x0092:
        r0 = r6;
        r0 = (android.content.Context) r0;	 Catch:{ Exception -> 0x011c }
        r2 = r0;
        r4 = 2131755192; // 0x7f1000b8 float:1.9141256E38 double:1.0532270057E-314;
        r4 = r6.getString(r4);	 Catch:{ Exception -> 0x011c }
        r5 = "getString(R.string.enter_valide_mail)";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5);	 Catch:{ Exception -> 0x011c }
        r6.buildErrorDialog(r2, r4);	 Catch:{ Exception -> 0x011c }
        r2 = r3;
        goto L_0x0033;
    L_0x00a7:
        r4 = r2;
        goto L_0x0071;
    L_0x00a9:
        r4 = r6.gender;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x00b7;
    L_0x00ad:
        r4 = r6.gender;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x00cd;
    L_0x00b1:
        r4 = r4.length();	 Catch:{ Exception -> 0x011c }
        if (r4 != 0) goto L_0x00cd;
    L_0x00b7:
        r0 = r6;
        r0 = (android.content.Context) r0;	 Catch:{ Exception -> 0x011c }
        r2 = r0;
        r4 = 2131755349; // 0x7f100155 float:1.9141575E38 double:1.0532270833E-314;
        r4 = r6.getString(r4);	 Catch:{ Exception -> 0x011c }
        r5 = "getString(R.string.select_gender)";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5);	 Catch:{ Exception -> 0x011c }
        r6.buildErrorDialog(r2, r4);	 Catch:{ Exception -> 0x011c }
        r2 = r3;
        goto L_0x0033;
    L_0x00cd:
        r4 = r6.edtPassword;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x0123;
    L_0x00d1:
        r4 = r4.getText();	 Catch:{ Exception -> 0x011c }
    L_0x00d5:
        r4 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x011c }
        r6.password = r4;	 Catch:{ Exception -> 0x011c }
        r4 = r6.edtConfirmPassword;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x0125;
    L_0x00df:
        r4 = r4.getText();	 Catch:{ Exception -> 0x011c }
    L_0x00e3:
        r4 = java.lang.String.valueOf(r4);	 Catch:{ Exception -> 0x011c }
        r6.confirmPassword = r4;	 Catch:{ Exception -> 0x011c }
        r4 = r6.password;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x013a;
    L_0x00ed:
        r4 = r6.password;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x00f7;
    L_0x00f1:
        r4 = r4.length();	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x013a;
    L_0x00f7:
        r4 = r6.confirmPassword;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x013a;
    L_0x00fb:
        r4 = r6.confirmPassword;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x0105;
    L_0x00ff:
        r4 = r4.length();	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x013a;
    L_0x0105:
        r4 = r6.password;	 Catch:{ Exception -> 0x011c }
        if (r4 == 0) goto L_0x012f;
    L_0x0109:
        r2 = r6.confirmPassword;	 Catch:{ Exception -> 0x011c }
        if (r2 != 0) goto L_0x0110;
    L_0x010d:
        kotlin.jvm.internal.Intrinsics.throwNpe();	 Catch:{ Exception -> 0x011c }
    L_0x0110:
        r2 = (java.lang.CharSequence) r2;	 Catch:{ Exception -> 0x011c }
        if (r4 != 0) goto L_0x0127;
    L_0x0114:
        r2 = new kotlin.TypeCastException;	 Catch:{ Exception -> 0x011c }
        r4 = "null cannot be cast to non-null type java.lang.String";
        r2.<init>(r4);	 Catch:{ Exception -> 0x011c }
        throw r2;	 Catch:{ Exception -> 0x011c }
    L_0x011c:
        r1 = move-exception;
        r1.printStackTrace();
        r2 = r3;
        goto L_0x0033;
    L_0x0123:
        r4 = r2;
        goto L_0x00d5;
    L_0x0125:
        r4 = r2;
        goto L_0x00e3;
    L_0x0127:
        r2 = r4.contentEquals(r2);	 Catch:{ Exception -> 0x011c }
        r2 = java.lang.Boolean.valueOf(r2);	 Catch:{ Exception -> 0x011c }
    L_0x012f:
        if (r2 != 0) goto L_0x0134;
    L_0x0131:
        kotlin.jvm.internal.Intrinsics.throwNpe();	 Catch:{ Exception -> 0x011c }
    L_0x0134:
        r2 = r2.booleanValue();	 Catch:{ Exception -> 0x011c }
        if (r2 != 0) goto L_0x0150;
    L_0x013a:
        r0 = r6;
        r0 = (android.content.Context) r0;	 Catch:{ Exception -> 0x011c }
        r2 = r0;
        r4 = 2131755287; // 0x7f100117 float:1.914145E38 double:1.0532270526E-314;
        r4 = r6.getString(r4);	 Catch:{ Exception -> 0x011c }
        r5 = "getString(R.string.password_error)";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r4, r5);	 Catch:{ Exception -> 0x011c }
        r6.buildErrorDialog(r2, r4);	 Catch:{ Exception -> 0x011c }
        r2 = r3;
        goto L_0x0033;
    L_0x0150:
        r2 = 1;
        goto L_0x0033;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.biz.health.cooey_app.account.RegisterCooeyUserActivity.validateFields():boolean");
    }

    @NotNull
    public final AlertDialog buildErrorDialog(@NotNull Context context, @NotNull String message) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(message, "message");
        Builder alertBuilder = new Builder(context);
        alertBuilder.setTitle((CharSequence) context.getResources().getString(C0644R.string.error_dialog_title));
        alertBuilder.setMessage((CharSequence) message);
        alertBuilder.setPositiveButton((CharSequence) context.getResources().getString(C0644R.string.error_dialog_btn), (DialogInterface.OnClickListener) RegisterCooeyUserActivity$buildErrorDialog$1.INSTANCE);
        AlertDialog show = alertBuilder.show();
        Intrinsics.checkExpressionValueIsNotNull(show, "alertBuilder.show()");
        return show;
    }

    private final boolean isValidEmail(String email) {
        return !TextUtils.isEmpty((CharSequence) email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public void onCancelled() {
    }

    @Nullable
    public Pair<Boolean, SublimeOptions> onDateTimeRecurrenceSet(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, @Nullable RecurrenceOption recurrenceOption, @Nullable String recurrenceRule) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        cal.set(year, monthOfYear, dayOfMonth, hourOfDay, minute);
        this.dateOfBirth = DateUtil.getStrinFromDate(cal.getTime());
        TextView textView = this.txtDateOfBirth;
        if (textView != null) {
            textView.setText(this.dateOfBirth);
        }
        return null;
    }

    @NotNull
    public final Pair<Boolean, SublimeOptions> getOptions$app_release() {
        SublimeOptions options = new SublimeOptions();
        int displayOptions = 0 | 1;
        options.setDisplayOptions(displayOptions);
        options.setPickerToShow(Picker.DATE_PICKER);
        return new Pair(displayOptions != 0 ? Boolean.TRUE : Boolean.FALSE, options);
    }

    public final void cooeySignIn(@NotNull CreateSessionRequest createSessionRequest) {
        Intrinsics.checkParameterIsNotNull(createSessionRequest, "createSessionRequest");
        try {
            ApiClient apiClient = this.apiClient;
            if (apiClient != null) {
                SessionsService sessionsService = apiClient.getSessionsService();
                if (sessionsService != null) {
                    Call create = sessionsService.create(createSessionRequest);
                    if (create != null) {
                        create.enqueue(new RegisterCooeyUserActivity$cooeySignIn$1(this));
                    }
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Sign in failed,Please try once again", 0).show();
            ProgressDialog progressDialog = this.dialog;
            if (progressDialog == null) {
                Intrinsics.throwUninitializedPropertyAccessException("dialog");
            }
            if (progressDialog.isShowing()) {
                progressDialog = this.dialog;
                if (progressDialog == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("dialog");
                }
                progressDialog.dismiss();
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
            apiClient.getChannelsService().create(channel).enqueue(new RegisterCooeyUserActivity$registerFCMToken$1());
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
                        partnerConfig.enqueue(new RegisterCooeyUserActivity$getPartnerConfig$1(this));
                    }
                }
            }
        }
    }

    public final void onRadioButtonClicked(@Nullable View v) {
        if (v == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.RadioButton");
        }
        boolean isChecked = ((RadioButton) v).isChecked();
        switch (((RadioButton) v).getId()) {
            case C0644R.id.radio_female:
                if (isChecked) {
                    this.gender = SexType.FEMALE.name();
                    return;
                }
                return;
            case C0644R.id.radio_male:
                if (isChecked) {
                    this.gender = SexType.MALE.name();
                    return;
                }
                return;
            default:
                return;
        }
    }
}
