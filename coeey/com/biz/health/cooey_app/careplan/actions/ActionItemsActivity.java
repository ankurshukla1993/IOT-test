package com.biz.health.cooey_app.careplan.actions;

import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.PatientApplication;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.android.users.old.utils.CTUtility;
import com.cooey.android.users.old.utils.PartnerConfigPreferences;
import com.cooey.common.CommonDatabase;
import com.cooey.common.config.VitalTemplatesConfigListItem;
import com.cooey.common.dao.ActionItemDao;
import com.cooey.common.vo.ActionItem;
import com.cooey.common.vo.SettingsConfig;
import com.cooey.common.vo.User;
import com.google.gson.Gson;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010O\u001a\u00020PH\u0002J\u0006\u0010Q\u001a\u00020RJ\u0006\u0010S\u001a\u00020RJ\b\u0010T\u001a\u00020PH\u0002J\b\u0010U\u001a\u00020PH\u0016J\u0012\u0010V\u001a\u00020P2\b\u0010W\u001a\u0004\u0018\u00010XH\u0014J\u0010\u0010Y\u001a\u00020Z2\u0006\u0010[\u001a\u00020\\H\u0016R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0019\"\u0004\b\u001e\u0010\u001bR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001c\u0010+\u001a\u0004\u0018\u00010,X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001c\u00101\u001a\u0004\u0018\u000102X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\u001c\u00107\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010(\"\u0004\b9\u0010*R\u001c\u0010:\u001a\u0004\u0018\u00010&X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010(\"\u0004\b<\u0010*R\u001c\u0010=\u001a\u0004\u0018\u00010>X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\u001c\u0010C\u001a\u0004\u0018\u00010DX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\u001c\u0010I\u001a\u0004\u0018\u00010JX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bK\u0010L\"\u0004\bM\u0010N¨\u0006]"}, d2 = {"Lcom/biz/health/cooey_app/careplan/actions/ActionItemsActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "actionItemAdapter", "Lcom/biz/health/cooey_app/careplan/actions/ActionItemAdapter;", "getActionItemAdapter", "()Lcom/biz/health/cooey_app/careplan/actions/ActionItemAdapter;", "setActionItemAdapter", "(Lcom/biz/health/cooey_app/careplan/actions/ActionItemAdapter;)V", "actionItemList", "", "Lcom/cooey/common/vo/ActionItem;", "getActionItemList", "()Ljava/util/List;", "setActionItemList", "(Ljava/util/List;)V", "btnRetry", "Landroid/widget/Button;", "getBtnRetry", "()Landroid/widget/Button;", "setBtnRetry", "(Landroid/widget/Button;)V", "errorLayout", "Landroid/widget/LinearLayout;", "getErrorLayout", "()Landroid/widget/LinearLayout;", "setErrorLayout", "(Landroid/widget/LinearLayout;)V", "linearLayout", "getLinearLayout", "setLinearLayout", "mContext", "Landroid/content/Context;", "getMContext", "()Landroid/content/Context;", "setMContext", "(Landroid/content/Context;)V", "patientId", "", "getPatientId", "()Ljava/lang/String;", "setPatientId", "(Ljava/lang/String;)V", "progressBar", "Landroid/widget/ProgressBar;", "getProgressBar", "()Landroid/widget/ProgressBar;", "setProgressBar", "(Landroid/widget/ProgressBar;)V", "recyclerView", "Landroid/support/v7/widget/RecyclerView;", "getRecyclerView", "()Landroid/support/v7/widget/RecyclerView;", "setRecyclerView", "(Landroid/support/v7/widget/RecyclerView;)V", "serverUrl", "getServerUrl", "setServerUrl", "sessionId", "getSessionId", "setSessionId", "toolbar", "Landroid/support/v7/widget/Toolbar;", "getToolbar", "()Landroid/support/v7/widget/Toolbar;", "setToolbar", "(Landroid/support/v7/widget/Toolbar;)V", "txtNoActionItems", "Landroid/widget/TextView;", "getTxtNoActionItems", "()Landroid/widget/TextView;", "setTxtNoActionItems", "(Landroid/widget/TextView;)V", "user", "Lcom/cooey/common/vo/User;", "getUser", "()Lcom/cooey/common/vo/User;", "setUser", "(Lcom/cooey/common/vo/User;)V", "getActionItems", "", "getEndOfDay", "", "getStartOfDay", "initView", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onOptionsItemSelected", "", "item", "Landroid/view/MenuItem;", "app_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: ActionItemsActivity.kt */
public final class ActionItemsActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;
    @Nullable
    private ActionItemAdapter actionItemAdapter;
    @Nullable
    private List<? extends ActionItem> actionItemList;
    @Nullable
    private Button btnRetry;
    @Nullable
    private LinearLayout errorLayout;
    @Nullable
    private LinearLayout linearLayout;
    @Nullable
    private Context mContext;
    @Nullable
    private String patientId;
    @Nullable
    private ProgressBar progressBar;
    @Nullable
    private RecyclerView recyclerView;
    @Nullable
    private String serverUrl;
    @Nullable
    private String sessionId;
    @Nullable
    private Toolbar toolbar;
    @Nullable
    private TextView txtNoActionItems;
    @Nullable
    private User user;

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
    public final User getUser() {
        return this.user;
    }

    public final void setUser(@Nullable User <set-?>) {
        this.user = <set-?>;
    }

    @Nullable
    public final RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    public final void setRecyclerView(@Nullable RecyclerView <set-?>) {
        this.recyclerView = <set-?>;
    }

    @Nullable
    public final LinearLayout getErrorLayout() {
        return this.errorLayout;
    }

    public final void setErrorLayout(@Nullable LinearLayout <set-?>) {
        this.errorLayout = <set-?>;
    }

    @Nullable
    public final Button getBtnRetry() {
        return this.btnRetry;
    }

    public final void setBtnRetry(@Nullable Button <set-?>) {
        this.btnRetry = <set-?>;
    }

    @Nullable
    public final ProgressBar getProgressBar() {
        return this.progressBar;
    }

    public final void setProgressBar(@Nullable ProgressBar <set-?>) {
        this.progressBar = <set-?>;
    }

    @Nullable
    public final LinearLayout getLinearLayout() {
        return this.linearLayout;
    }

    public final void setLinearLayout(@Nullable LinearLayout <set-?>) {
        this.linearLayout = <set-?>;
    }

    @Nullable
    public final Toolbar getToolbar() {
        return this.toolbar;
    }

    public final void setToolbar(@Nullable Toolbar <set-?>) {
        this.toolbar = <set-?>;
    }

    @Nullable
    public final String getPatientId() {
        return this.patientId;
    }

    public final void setPatientId(@Nullable String <set-?>) {
        this.patientId = <set-?>;
    }

    @Nullable
    public final String getSessionId() {
        return this.sessionId;
    }

    public final void setSessionId(@Nullable String <set-?>) {
        this.sessionId = <set-?>;
    }

    @Nullable
    public final String getServerUrl() {
        return this.serverUrl;
    }

    public final void setServerUrl(@Nullable String <set-?>) {
        this.serverUrl = <set-?>;
    }

    @Nullable
    public final ActionItemAdapter getActionItemAdapter() {
        return this.actionItemAdapter;
    }

    public final void setActionItemAdapter(@Nullable ActionItemAdapter <set-?>) {
        this.actionItemAdapter = <set-?>;
    }

    @Nullable
    public final Context getMContext() {
        return this.mContext;
    }

    public final void setMContext(@Nullable Context <set-?>) {
        this.mContext = <set-?>;
    }

    @Nullable
    public final List<ActionItem> getActionItemList() {
        return this.actionItemList;
    }

    public final void setActionItemList(@Nullable List<? extends ActionItem> <set-?>) {
        this.actionItemList = <set-?>;
    }

    @Nullable
    public final TextView getTxtNoActionItems() {
        return this.txtNoActionItems;
    }

    public final void setTxtNoActionItems(@Nullable TextView <set-?>) {
        this.txtNoActionItems = <set-?>;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView((int) C0644R.layout.activity_careplan_todo_user);
        Intent intent = getIntent();
        this.patientId = intent.getStringExtra(CTConstants.PATIENT_ID);
        this.sessionId = intent.getStringExtra(CTConstants.SESSION_ID);
        if (intent.getBooleanExtra("setPartnerConfig", false)) {
            String partnerConfigRes = intent.getStringExtra("settingsConfig");
            String vitalTempList = intent.getStringExtra("vitaltemplist");
            this.serverUrl = intent.getStringExtra("serverurl");
            Gson gson = new Gson();
            SettingsConfig settingsConfig = (SettingsConfig) gson.fromJson(partnerConfigRes, SettingsConfig.class);
            CTUtility.storeStringInSharedPref(this, CTConstants.SESSION_ID, this.sessionId);
            CTUtility.storeStringInSharedPref(this, CTConstants.APP_TYPE, intent.getStringExtra(CTConstants.APP_TYPE));
            VitalTemplatesConfigListItem[] vitalTemplatesConfigListItems = (VitalTemplatesConfigListItem[]) gson.fromJson(vitalTempList, VitalTemplatesConfigListItem[].class);
            PartnerConfigPreferences partnerConfigPreferences = new PartnerConfigPreferences();
            partnerConfigPreferences.setPartnerConfig(this, settingsConfig);
            partnerConfigPreferences.setVitalConfig(this, Arrays.asList((VitalTemplatesConfigListItem[]) Arrays.copyOf(vitalTemplatesConfigListItems, vitalTemplatesConfigListItems.length)));
        }
        this.mContext = this;
        initView();
        getActionItems();
    }

    private final void initView() {
        View findViewById = findViewById(C0644R.id.error_layout);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        this.errorLayout = (LinearLayout) findViewById;
        findViewById = findViewById(C0644R.id.rev_action_items);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.widget.RecyclerView");
        }
        this.recyclerView = (RecyclerView) findViewById;
        findViewById = findViewById(C0644R.id.error_btn_retry);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.Button");
        }
        this.btnRetry = (Button) findViewById;
        findViewById = findViewById(C0644R.id.pb_careplan_list);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ProgressBar");
        }
        this.progressBar = (ProgressBar) findViewById;
        ProgressBar progressBar = this.progressBar;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        findViewById = findViewById(C0644R.id.error_layout);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.LinearLayout");
        }
        this.linearLayout = (LinearLayout) findViewById;
        findViewById = findViewById(C0644R.id.toolbar);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.support.v7.widget.Toolbar");
        }
        this.toolbar = (Toolbar) findViewById;
        findViewById = findViewById(C0644R.id.txt_no_action_items);
        if (findViewById == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        this.txtNoActionItems = (TextView) findViewById;
        setSupportActionBar(this.toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            Intrinsics.throwNpe();
        }
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar = getSupportActionBar();
        if (supportActionBar == null) {
            Intrinsics.throwNpe();
        }
        supportActionBar.setDisplayShowHomeEnabled(true);
        ActionBar supportActionBar2 = getSupportActionBar();
        if (supportActionBar2 == null) {
            Intrinsics.throwNpe();
        }
        supportActionBar2.setTitle((CharSequence) "Today's Actions");
    }

    private final void getActionItems() {
        LiveData actionItemLiveData;
        RecyclerView recyclerView = this.recyclerView;
        if (recyclerView != null) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        this.actionItemAdapter = new ActionItemAdapter(this.mContext, this.user);
        RecyclerView recyclerView2 = this.recyclerView;
        if (recyclerView2 != null) {
            recyclerView2.setAdapter(this.actionItemAdapter);
        }
        CommonDatabase commonDatabase = PatientApplication.Companion.getCommonDatabase();
        if (commonDatabase != null) {
            ActionItemDao ActionItemDao = commonDatabase.ActionItemDao();
            if (ActionItemDao != null) {
                String str = this.patientId;
                if (str == null) {
                    Intrinsics.throwNpe();
                }
                actionItemLiveData = ActionItemDao.getActionItemsBetween(str, getStartOfDay(), getEndOfDay());
                if (actionItemLiveData != null) {
                    actionItemLiveData.observe(this, new ActionItemsActivity$getActionItems$1(this));
                }
            }
        }
        actionItemLiveData = null;
        if (actionItemLiveData != null) {
            actionItemLiveData.observe(this, new ActionItemsActivity$getActionItems$1(this));
        }
    }

    public boolean onOptionsItemSelected(@NotNull MenuItem item) {
        Intrinsics.checkParameterIsNotNull(item, "item");
        switch (item.getItemId()) {
            case 16908332:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    public final long getEndOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime().getTime();
    }

    public final long getStartOfDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime().getTime();
    }
}
