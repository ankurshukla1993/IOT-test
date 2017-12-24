package com.biz.health.cooey_app;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.biz.health.cooey_app.utils.DateUtil;
import com.cooey.common.services.ApiClient;
import com.cooey.common.services.UsersService;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Activity;
import com.cooey.common.vo.ChangeEvent;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.User;
import humanize.util.Constants;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.ObjectRef;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\u00020\u0006B\u0005¢\u0006\u0002\u0010\u0007J\u0006\u0010m\u001a\u00020nJ\u0006\u0010o\u001a\u00020nJ\"\u0010p\u001a\u00020n2\u0006\u0010q\u001a\u00020r2\u0006\u0010s\u001a\u00020r2\b\u0010t\u001a\u0004\u0018\u00010uH\u0014J\b\u0010v\u001a\u00020nH\u0016J\u001a\u0010w\u001a\u00020n2\b\u0010x\u001a\u0004\u0018\u00010y2\u0006\u0010z\u001a\u00020rH\u0016J\u0012\u0010w\u001a\u00020n2\b\u0010x\u001a\u0004\u0018\u00010{H\u0016J\u0012\u0010|\u001a\u00020n2\b\u0010}\u001a\u0004\u0018\u00010~H\u0014J,\u0010\u001a\u00020n2\u0016\u0010\u0001\u001a\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00012\n\u0010\u0001\u001a\u0005\u0018\u00010\u0001H\u0016J\u0014\u0010\u0001\u001a\u00030\u00012\b\u0010\u0001\u001a\u00030\u0001H\u0016J9\u0010\u0001\u001a\u00020n2\u0016\u0010\u0001\u001a\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00012\u0016\u0010\u0001\u001a\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u0001H\u0016J\u0007\u0010\u0001\u001a\u00020nJ\u0007\u0010\u0001\u001a\u00020nJ\u0007\u0010\u0001\u001a\u00020nR\u001a\u0010\b\u001a\u00020\tX.¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\tX.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u000b\"\u0004\b\u0010\u0010\rR \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001c\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010 \"\u0004\b!\u0010\"R\u001e\u0010#\u001a\u0004\u0018\u00010$X\u000e¢\u0006\u0010\n\u0002\u0010)\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001e\u0010*\u001a\u0004\u0018\u00010$X\u000e¢\u0006\u0010\n\u0002\u0010)\u001a\u0004\b+\u0010&\"\u0004\b,\u0010(R\u001c\u0010-\u001a\u0004\u0018\u00010.X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\u001a\u00103\u001a\u000204X.¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u001c\u00109\u001a\u0004\u0018\u00010:X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001c\u0010?\u001a\u0004\u0018\u00010:X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b@\u0010<\"\u0004\bA\u0010>R\u001c\u0010B\u001a\u0004\u0018\u00010:X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010<\"\u0004\bD\u0010>R\u001c\u0010E\u001a\u0004\u0018\u00010:X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bF\u0010<\"\u0004\bG\u0010>R\u001c\u0010H\u001a\u0004\u0018\u00010:X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bI\u0010<\"\u0004\bJ\u0010>R\u001c\u0010K\u001a\u0004\u0018\u00010:X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bL\u0010<\"\u0004\bM\u0010>R\u001a\u0010N\u001a\u00020OX.¢\u0006\u000e\n\u0000\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u001a\u0010T\u001a\u00020UX.¢\u0006\u000e\n\u0000\u001a\u0004\bV\u0010W\"\u0004\bX\u0010YR\u001a\u0010Z\u001a\u00020UX.¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010W\"\u0004\b\\\u0010YR\u001a\u0010]\u001a\u00020UX.¢\u0006\u000e\n\u0000\u001a\u0004\b^\u0010W\"\u0004\b_\u0010YR\u001a\u0010`\u001a\u00020UX.¢\u0006\u000e\n\u0000\u001a\u0004\ba\u0010W\"\u0004\bb\u0010YR\u001a\u0010c\u001a\u00020UX.¢\u0006\u000e\n\u0000\u001a\u0004\bd\u0010W\"\u0004\be\u0010YR*\u0010f\u001a\u0012\u0012\u0004\u0012\u00020:0gj\b\u0012\u0004\u0012\u00020:`hX.¢\u0006\u000e\n\u0000\u001a\u0004\bi\u0010j\"\u0004\bk\u0010l¨\u0006\u0001"}, d2 = {"Lcom/biz/health/cooey_app/RescheduleEventActivity;", "Landroid/support/v7/app/AppCompatActivity;", "Landroid/view/View$OnClickListener;", "Lretrofit2/Callback;", "", "Lcom/cooey/common/vo/User;", "Landroid/content/DialogInterface$OnClickListener;", "()V", "btnRequest", "Landroid/widget/Button;", "getBtnRequest", "()Landroid/widget/Button;", "setBtnRequest", "(Landroid/widget/Button;)V", "btnViewCalendar", "getBtnViewCalendar", "setBtnViewCalendar", "caretakerList", "", "getCaretakerList", "()Ljava/util/List;", "setCaretakerList", "(Ljava/util/List;)V", "edTxtNotes", "Landroid/widget/EditText;", "getEdTxtNotes", "()Landroid/widget/EditText;", "setEdTxtNotes", "(Landroid/widget/EditText;)V", "event", "Lcom/cooey/common/vo/Activity;", "getEvent", "()Lcom/cooey/common/vo/Activity;", "setEvent", "(Lcom/cooey/common/vo/Activity;)V", "newEndTime", "", "getNewEndTime", "()Ljava/lang/Long;", "setNewEndTime", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "newStartTime", "getNewStartTime", "setNewStartTime", "preferenceStore", "Lcom/cooey/common/stores/PreferenceStore;", "getPreferenceStore", "()Lcom/cooey/common/stores/PreferenceStore;", "setPreferenceStore", "(Lcom/cooey/common/stores/PreferenceStore;)V", "progressDialog", "Landroid/app/ProgressDialog;", "getProgressDialog", "()Landroid/app/ProgressDialog;", "setProgressDialog", "(Landroid/app/ProgressDialog;)V", "strActivityId", "", "getStrActivityId", "()Ljava/lang/String;", "setStrActivityId", "(Ljava/lang/String;)V", "strCaretakerId", "getStrCaretakerId", "setStrCaretakerId", "strEventName", "getStrEventName", "setStrEventName", "strOriginalEndTime", "getStrOriginalEndTime", "setStrOriginalEndTime", "strOriginalStartTime", "getStrOriginalStartTime", "setStrOriginalStartTime", "strPatientId", "getStrPatientId", "setStrPatientId", "toolBar", "Landroid/support/v7/widget/Toolbar;", "getToolBar", "()Landroid/support/v7/widget/Toolbar;", "setToolBar", "(Landroid/support/v7/widget/Toolbar;)V", "txtEventName", "Landroid/widget/TextView;", "getTxtEventName", "()Landroid/widget/TextView;", "setTxtEventName", "(Landroid/widget/TextView;)V", "txtModifiedEndTime", "getTxtModifiedEndTime", "setTxtModifiedEndTime", "txtModifiedStartTime", "getTxtModifiedStartTime", "setTxtModifiedStartTime", "txtOriginalEndTime", "getTxtOriginalEndTime", "setTxtOriginalEndTime", "txtOriginalStartTime", "getTxtOriginalStartTime", "setTxtOriginalStartTime", "userNameList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getUserNameList", "()Ljava/util/ArrayList;", "setUserNameList", "(Ljava/util/ArrayList;)V", "getCareTakers", "", "initUI", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onBackPressed", "onClick", "p0", "Landroid/content/DialogInterface;", "p1", "Landroid/view/View;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onFailure", "call", "Lretrofit2/Call;", "t", "", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "onResponse", "response", "Lretrofit2/Response;", "requestNewEvent", "setValue", "showCaretakers", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: RescheduleEventActivity.kt */
public final class RescheduleEventActivity extends AppCompatActivity implements OnClickListener, Callback<List<? extends User>>, DialogInterface.OnClickListener {
    private HashMap _$_findViewCache;
    @NotNull
    public Button btnRequest;
    @NotNull
    public Button btnViewCalendar;
    @NotNull
    public List<User> caretakerList;
    @NotNull
    public EditText edTxtNotes;
    @Nullable
    private Activity event;
    @Nullable
    private Long newEndTime = Long.valueOf(0);
    @Nullable
    private Long newStartTime = Long.valueOf(0);
    @Nullable
    private PreferenceStore preferenceStore;
    @NotNull
    public ProgressDialog progressDialog;
    @Nullable
    private String strActivityId;
    @Nullable
    private String strCaretakerId;
    @Nullable
    private String strEventName;
    @Nullable
    private String strOriginalEndTime;
    @Nullable
    private String strOriginalStartTime;
    @Nullable
    private String strPatientId;
    @NotNull
    public Toolbar toolBar;
    @NotNull
    public TextView txtEventName;
    @NotNull
    public TextView txtModifiedEndTime;
    @NotNull
    public TextView txtModifiedStartTime;
    @NotNull
    public TextView txtOriginalEndTime;
    @NotNull
    public TextView txtOriginalStartTime;
    @NotNull
    public ArrayList<String> userNameList;

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
    public final Activity getEvent() {
        return this.event;
    }

    public final void setEvent(@Nullable Activity <set-?>) {
        this.event = <set-?>;
    }

    @NotNull
    public final TextView getTxtEventName() {
        TextView textView = this.txtEventName;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtEventName");
        }
        return textView;
    }

    public final void setTxtEventName(@NotNull TextView <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.txtEventName = <set-?>;
    }

    @NotNull
    public final TextView getTxtOriginalStartTime() {
        TextView textView = this.txtOriginalStartTime;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtOriginalStartTime");
        }
        return textView;
    }

    public final void setTxtOriginalStartTime(@NotNull TextView <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.txtOriginalStartTime = <set-?>;
    }

    @NotNull
    public final TextView getTxtOriginalEndTime() {
        TextView textView = this.txtOriginalEndTime;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtOriginalEndTime");
        }
        return textView;
    }

    public final void setTxtOriginalEndTime(@NotNull TextView <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.txtOriginalEndTime = <set-?>;
    }

    @NotNull
    public final TextView getTxtModifiedStartTime() {
        TextView textView = this.txtModifiedStartTime;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtModifiedStartTime");
        }
        return textView;
    }

    public final void setTxtModifiedStartTime(@NotNull TextView <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.txtModifiedStartTime = <set-?>;
    }

    @NotNull
    public final TextView getTxtModifiedEndTime() {
        TextView textView = this.txtModifiedEndTime;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtModifiedEndTime");
        }
        return textView;
    }

    public final void setTxtModifiedEndTime(@NotNull TextView <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.txtModifiedEndTime = <set-?>;
    }

    @NotNull
    public final EditText getEdTxtNotes() {
        EditText editText = this.edTxtNotes;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("edTxtNotes");
        }
        return editText;
    }

    public final void setEdTxtNotes(@NotNull EditText <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.edTxtNotes = <set-?>;
    }

    @NotNull
    public final Button getBtnViewCalendar() {
        Button button = this.btnViewCalendar;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnViewCalendar");
        }
        return button;
    }

    public final void setBtnViewCalendar(@NotNull Button <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.btnViewCalendar = <set-?>;
    }

    @NotNull
    public final Button getBtnRequest() {
        Button button = this.btnRequest;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnRequest");
        }
        return button;
    }

    public final void setBtnRequest(@NotNull Button <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.btnRequest = <set-?>;
    }

    @NotNull
    public final Toolbar getToolBar() {
        Toolbar toolbar = this.toolBar;
        if (toolbar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolBar");
        }
        return toolbar;
    }

    public final void setToolBar(@NotNull Toolbar <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.toolBar = <set-?>;
    }

    @Nullable
    public final String getStrEventName() {
        return this.strEventName;
    }

    public final void setStrEventName(@Nullable String <set-?>) {
        this.strEventName = <set-?>;
    }

    @Nullable
    public final String getStrOriginalStartTime() {
        return this.strOriginalStartTime;
    }

    public final void setStrOriginalStartTime(@Nullable String <set-?>) {
        this.strOriginalStartTime = <set-?>;
    }

    @Nullable
    public final String getStrOriginalEndTime() {
        return this.strOriginalEndTime;
    }

    public final void setStrOriginalEndTime(@Nullable String <set-?>) {
        this.strOriginalEndTime = <set-?>;
    }

    @Nullable
    public final String getStrActivityId() {
        return this.strActivityId;
    }

    public final void setStrActivityId(@Nullable String <set-?>) {
        this.strActivityId = <set-?>;
    }

    @Nullable
    public final String getStrPatientId() {
        return this.strPatientId;
    }

    public final void setStrPatientId(@Nullable String <set-?>) {
        this.strPatientId = <set-?>;
    }

    @Nullable
    public final String getStrCaretakerId() {
        return this.strCaretakerId;
    }

    public final void setStrCaretakerId(@Nullable String <set-?>) {
        this.strCaretakerId = <set-?>;
    }

    @Nullable
    public final PreferenceStore getPreferenceStore() {
        return this.preferenceStore;
    }

    public final void setPreferenceStore(@Nullable PreferenceStore <set-?>) {
        this.preferenceStore = <set-?>;
    }

    @NotNull
    public final List<User> getCaretakerList() {
        List<User> list = this.caretakerList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("caretakerList");
        }
        return list;
    }

    public final void setCaretakerList(@NotNull List<User> <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.caretakerList = <set-?>;
    }

    @NotNull
    public final ArrayList<String> getUserNameList() {
        ArrayList<String> arrayList = this.userNameList;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userNameList");
        }
        return arrayList;
    }

    public final void setUserNameList(@NotNull ArrayList<String> <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.userNameList = <set-?>;
    }

    @Nullable
    public final Long getNewStartTime() {
        return this.newStartTime;
    }

    public final void setNewStartTime(@Nullable Long <set-?>) {
        this.newStartTime = <set-?>;
    }

    @Nullable
    public final Long getNewEndTime() {
        return this.newEndTime;
    }

    public final void setNewEndTime(@Nullable Long <set-?>) {
        this.newEndTime = <set-?>;
    }

    @NotNull
    public final ProgressDialog getProgressDialog() {
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialog");
        }
        return progressDialog;
    }

    public final void setProgressDialog(@NotNull ProgressDialog <set-?>) {
        Intrinsics.checkParameterIsNotNull(<set-?>, "<set-?>");
        this.progressDialog = <set-?>;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0644R.layout.activity_reschedule_event);
        this.strEventName = getIntent().getStringExtra("eventName");
        this.strOriginalStartTime = getIntent().getStringExtra("start");
        this.strOriginalEndTime = getIntent().getStringExtra("end");
        this.strActivityId = getIntent().getStringExtra("Activity");
        this.strPatientId = getIntent().getStringExtra("patientId");
        this.strCaretakerId = getIntent().getStringExtra("caretakerId");
        this.preferenceStore = new PreferenceStore();
        this.caretakerList = new ArrayList();
        this.userNameList = new ArrayList();
        initUI();
        setValue();
        Toolbar toolbar = this.toolBar;
        if (toolbar == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toolBar");
        }
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
        supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        Button button = this.btnViewCalendar;
        if (button == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnViewCalendar");
        }
        button.setOnClickListener(this);
        Button button2 = this.btnRequest;
        if (button2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("btnRequest");
        }
        button2.setOnClickListener(this);
    }

    public final void initUI() {
        View findViewById = findViewById(C0644R.id.txt_eventNameValue);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.txt_eventNameValue)");
        this.txtEventName = (TextView) findViewById;
        findViewById = findViewById(C0644R.id.txt_original_start_time);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.txt_original_start_time)");
        this.txtOriginalStartTime = (TextView) findViewById;
        findViewById = findViewById(C0644R.id.txt_original_end_time);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.txt_original_end_time)");
        this.txtOriginalEndTime = (TextView) findViewById;
        findViewById = findViewById(C0644R.id.txt_modified_start_time);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.txt_modified_start_time)");
        this.txtModifiedStartTime = (TextView) findViewById;
        findViewById = findViewById(C0644R.id.txt_modified_end_time);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.txt_modified_end_time)");
        this.txtModifiedEndTime = (TextView) findViewById;
        findViewById = findViewById(C0644R.id.txt_rescheduleNote);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.txt_rescheduleNote)");
        this.edTxtNotes = (EditText) findViewById;
        findViewById = findViewById(C0644R.id.btn_viewCaretakerCalendar);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.btn_viewCaretakerCalendar)");
        this.btnViewCalendar = (Button) findViewById;
        findViewById = findViewById(C0644R.id.btn_rescheduleRequest);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.btn_rescheduleRequest)");
        this.btnRequest = (Button) findViewById;
        findViewById = findViewById(C0644R.id.toolbar);
        Intrinsics.checkExpressionValueIsNotNull(findViewById, "findViewById(R.id.toolbar)");
        this.toolBar = (Toolbar) findViewById;
    }

    public final void setValue() {
        String str;
        TextView textView;
        TextView textView2;
        Long valueOf;
        TextView textView3;
        Date date;
        Date date2;
        Date date3;
        if (this.strEventName != null) {
            Boolean valueOf2;
            str = this.strEventName;
            if (str != null) {
                valueOf2 = Boolean.valueOf(((CharSequence) str).length() == 0);
            } else {
                valueOf2 = null;
            }
            if (valueOf2 == null) {
                Intrinsics.throwNpe();
            }
            if (!valueOf2.booleanValue()) {
                textView = this.txtEventName;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("txtEventName");
                }
                textView.setText(this.strEventName);
                if (this.strOriginalStartTime == null) {
                    textView2 = this.txtOriginalStartTime;
                    if (textView2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("txtOriginalStartTime");
                    }
                    str = this.strOriginalStartTime;
                    if (str == null) {
                        valueOf = Long.valueOf(Long.parseLong(str));
                        textView3 = textView2;
                        date = date2;
                    } else {
                        valueOf = null;
                        textView3 = textView2;
                        date = date2;
                    }
                    if (valueOf == null) {
                        Intrinsics.throwNpe();
                    }
                    date2 = new Date(valueOf.longValue());
                    textView3.setText(DateUtil.getReadableStringFromDate(date));
                } else {
                    textView = this.txtOriginalStartTime;
                    if (textView == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("txtOriginalStartTime");
                    }
                    textView.setText("-");
                }
                if (this.strOriginalEndTime == null) {
                    textView2 = this.txtOriginalEndTime;
                    if (textView2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("txtOriginalEndTime");
                    }
                    str = this.strOriginalEndTime;
                    if (str == null) {
                        valueOf = Long.valueOf(Long.parseLong(str));
                        date3 = date2;
                    } else {
                        valueOf = null;
                        date3 = date2;
                    }
                    if (valueOf == null) {
                        Intrinsics.throwNpe();
                    }
                    date3 = new Date(valueOf.longValue());
                    textView2.setText(DateUtil.getReadableStringFromDate(date2));
                }
                TextView textView4;
                textView4 = this.txtOriginalEndTime;
                if (textView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("txtOriginalEndTime");
                }
                textView4.setText("-");
                return;
            }
        }
        textView = this.txtEventName;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtEventName");
        }
        textView.setText("-");
        if (this.strOriginalStartTime == null) {
            textView = this.txtOriginalStartTime;
            if (textView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("txtOriginalStartTime");
            }
            textView.setText("-");
        } else {
            textView2 = this.txtOriginalStartTime;
            if (textView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("txtOriginalStartTime");
            }
            str = this.strOriginalStartTime;
            if (str == null) {
                valueOf = null;
                textView3 = textView2;
                date = date2;
            } else {
                valueOf = Long.valueOf(Long.parseLong(str));
                textView3 = textView2;
                date = date2;
            }
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            date2 = new Date(valueOf.longValue());
            textView3.setText(DateUtil.getReadableStringFromDate(date));
        }
        if (this.strOriginalEndTime == null) {
            textView4 = this.txtOriginalEndTime;
            if (textView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("txtOriginalEndTime");
            }
            textView4.setText("-");
            return;
        }
        textView2 = this.txtOriginalEndTime;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("txtOriginalEndTime");
        }
        str = this.strOriginalEndTime;
        if (str == null) {
            valueOf = null;
            date3 = date2;
        } else {
            valueOf = Long.valueOf(Long.parseLong(str));
            date3 = date2;
        }
        if (valueOf == null) {
            Intrinsics.throwNpe();
        }
        date3 = new Date(valueOf.longValue());
        textView2.setText(DateUtil.getReadableStringFromDate(date2));
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        switch (item.getItemId()) {
            case 16908332:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(@Nullable View p0) {
        Integer valueOf = p0 != null ? Integer.valueOf(p0.getId()) : null;
        if (valueOf != null && valueOf.intValue() == C0644R.id.btn_viewCaretakerCalendar) {
            if (this.strCaretakerId != null) {
                Boolean valueOf2;
                String str = this.strCaretakerId;
                if (str != null) {
                    valueOf2 = Boolean.valueOf(((CharSequence) str).length() == 0);
                } else {
                    valueOf2 = null;
                }
                if (valueOf2 == null) {
                    Intrinsics.throwNpe();
                }
                if (!valueOf2.booleanValue()) {
                    Intent intent = new Intent(this, CaretakerCalendarActivity.class);
                    intent.putExtra("Activity", this.strActivityId);
                    intent.putExtra("caretakerId", this.strCaretakerId);
                    startActivityForResult(intent, 1221);
                    return;
                }
            }
            getCareTakers();
        } else if (valueOf != null && valueOf.intValue() == C0644R.id.btn_rescheduleRequest) {
            requestNewEvent();
        }
    }

    public final void getCareTakers() {
        String id;
        this.progressDialog = new ProgressDialog(this);
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialog");
        }
        progressDialog.setMessage("Loading caretakers....");
        ProgressDialog progressDialog2 = this.progressDialog;
        if (progressDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialog");
        }
        progressDialog2.setCanceledOnTouchOutside(false);
        progressDialog2 = this.progressDialog;
        if (progressDialog2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialog");
        }
        progressDialog2.show();
        List list = this.caretakerList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("caretakerList");
        }
        list.clear();
        ArrayList arrayList = this.userNameList;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userNameList");
        }
        arrayList.clear();
        UsersService usersService = new ApiClient(this, "http://api.cooey.co.in/ehealth/").getUsersService();
        PreferenceStore preferenceStore = this.preferenceStore;
        if (preferenceStore != null) {
            Session session = preferenceStore.getSession(this);
            if (session != null) {
                id = session.getId();
                usersService.getCaretakers(id, this.strPatientId).enqueue(this);
            }
        }
        id = null;
        usersService.getCaretakers(id, this.strPatientId).enqueue(this);
    }

    public void onResponse(@Nullable Call<List<User>> call, @Nullable Response<List<User>> response) {
        List list;
        if (response != null) {
            list = (List) response.body();
        } else {
            list = null;
        }
        if (list != null) {
            list = (List) response.body();
            Integer valueOf = list != null ? Integer.valueOf(list.size()) : null;
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            if (valueOf.intValue() > 0) {
                Object body = response.body();
                if (body == null) {
                    Intrinsics.throwNpe();
                }
                for (User user : (List) body) {
                    List list2 = this.caretakerList;
                    if (list2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("caretakerList");
                    }
                    list2.add(user);
                }
                list = this.caretakerList;
                if (list == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("caretakerList");
                }
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    ArrayList arrayList = this.userNameList;
                    if (arrayList == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("userNameList");
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    list = this.caretakerList;
                    if (list == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("caretakerList");
                    }
                    stringBuilder = stringBuilder.append(((User) list.get(i)).getFirstName()).append(Constants.SPACE);
                    list = this.caretakerList;
                    if (list == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("caretakerList");
                    }
                    arrayList.add(stringBuilder.append(((User) list.get(i)).getLastName()).toString());
                }
                ProgressDialog progressDialog = this.progressDialog;
                if (progressDialog == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("progressDialog");
                }
                progressDialog.dismiss();
                showCaretakers();
            }
        }
    }

    public void onFailure(@Nullable Call<List<User>> call, @Nullable Throwable t) {
        ProgressDialog progressDialog = this.progressDialog;
        if (progressDialog == null) {
            Intrinsics.throwUninitializedPropertyAccessException("progressDialog");
        }
        progressDialog.dismiss();
        if (t != null) {
            t.printStackTrace();
        }
    }

    public final void showCaretakers() {
        ArrayList arrayList = this.userNameList;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userNameList");
        }
        if (arrayList.size() > 0) {
            Context context = this;
            ArrayList arrayList2 = this.userNameList;
            if (arrayList2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("userNameList");
            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(context, 17367043, arrayList2);
            Builder alert = new Builder(this);
            alert.setTitle((CharSequence) "Select caretaker");
            alert.setAdapter(arrayAdapter, this);
            alert.show();
            return;
        }
        Toast.makeText(this, "You do not have care taker", 1).show();
    }

    public void onClick(@Nullable DialogInterface p0, int p1) {
        Intent intent = new Intent(this, CaretakerCalendarActivity.class);
        List list = this.caretakerList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("caretakerList");
        }
        this.strCaretakerId = ((User) list.get(p1)).getId();
        intent.putExtra("Activity", this.strActivityId);
        intent.putExtra("caretakerId", this.strCaretakerId);
        list = this.caretakerList;
        if (list == null) {
            Intrinsics.throwUninitializedPropertyAccessException("caretakerList");
        }
        list.clear();
        ArrayList arrayList = this.userNameList;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("userNameList");
        }
        arrayList.clear();
        startActivityForResult(intent, 1221);
        if (p0 != null) {
            p0.dismiss();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Long l = null;
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1221 && resultCode == -1) {
            TextView textView;
            Long l2;
            this.newStartTime = data != null ? Long.valueOf(data.getLongExtra("startTime", 0)) : null;
            if (data != null) {
                l = Long.valueOf(data.getLongExtra("endTime", 0));
            }
            this.newEndTime = l;
            Long l3 = this.newStartTime;
            if (l3 == null) {
                Intrinsics.throwNpe();
            }
            if (l3.longValue() > 0) {
                textView = this.txtModifiedStartTime;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("txtModifiedStartTime");
                }
                l2 = this.newStartTime;
                if (l2 == null) {
                    Intrinsics.throwNpe();
                }
                textView.setText(DateUtil.getReadableStringFromDate(new Date(l2.longValue())));
            }
            l3 = this.newEndTime;
            if (l3 == null) {
                Intrinsics.throwNpe();
            }
            if (l3.longValue() > 0) {
                textView = this.txtModifiedEndTime;
                if (textView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("txtModifiedEndTime");
                }
                l2 = this.newEndTime;
                if (l2 == null) {
                    Intrinsics.throwNpe();
                }
                textView.setText(DateUtil.getReadableStringFromDate(new Date(l2.longValue())));
            }
        }
    }

    public final void requestNewEvent() {
        PreferenceStore preferenceStore = new PreferenceStore();
        String str = this.strPatientId;
        String tenantId = preferenceStore.getSession(this).getTenantId();
        String str2 = this.strActivityId;
        EditText editText = this.edTxtNotes;
        if (editText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("edTxtNotes");
        }
        String obj = editText.getText().toString();
        if (obj == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        obj = StringsKt__StringsKt.trim((CharSequence) obj).toString();
        Long l = this.newStartTime;
        if (l == null) {
            Intrinsics.throwNpe();
        }
        long longValue = l.longValue();
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        long timeInMillis2 = Calendar.getInstance().getTimeInMillis();
        Long l2 = this.newEndTime;
        if (l2 == null) {
            Intrinsics.throwNpe();
        }
        ChangeEvent changeEvent = new ChangeEvent(str, null, tenantId, str2, obj, longValue, timeInMillis, timeInMillis2, l2.longValue());
        Long l3 = this.newStartTime;
        if (l3 == null) {
            Intrinsics.throwNpe();
        }
        if (l3.longValue() > 0) {
            ObjectRef objectRef = new ObjectRef();
            objectRef.element = new ProgressDialog(this);
            ProgressDialog progressDialog = (ProgressDialog) objectRef.element;
            StringBuilder append = new StringBuilder().append("Requesting to change the event ").append(this.strEventName).append(" to ");
            Long l4 = this.newStartTime;
            if (l4 == null) {
                Intrinsics.throwNpe();
            }
            progressDialog.setMessage(append.append(DateUtil.getReadableStringFromDate(new Date(l4.longValue()))).toString());
            ((ProgressDialog) objectRef.element).show();
            new ApiClient(this, "http://api.cooey.co.in/ehealth/").getActivitiesService().postEventChangeRequest(preferenceStore.getSession(this).getId(), changeEvent).enqueue(new RescheduleEventActivity$requestNewEvent$1(this, objectRef));
            return;
        }
        Toast.makeText(this, "Please select new Date and Time", 1).show();
    }

    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
