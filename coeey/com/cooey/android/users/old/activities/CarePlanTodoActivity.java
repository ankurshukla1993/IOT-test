package com.cooey.android.users.old.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.android.users.old.careplan.ActionItemAdapter;
import com.cooey.android.users.old.dialogs.BPFullScreenDialog;
import com.cooey.android.users.old.dialogs.BloodSugarFullScreenDialog.OnUpdateVitalsList;
import com.cooey.android.users.old.dialogs.WeightFullScreenDialog;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.android.users.old.utils.CTUtility;
import com.cooey.android.users.old.utils.NetworkUtils;
import com.cooey.android.users.old.utils.PartnerConfigPreferences;
import com.cooey.common.BaseApplication;
import com.cooey.common.config.VitalTemplatesConfigListItem;
import com.cooey.common.realm_store.VitalStore;
import com.cooey.common.services.ApiClient;
import com.cooey.common.vo.ActionItem;
import com.cooey.common.vo.ActionItemPatient;
import com.cooey.common.vo.ActionItemPatientHeader;
import com.cooey.common.vo.ListItem;
import com.cooey.common.vo.SettingsConfig;
import com.cooey.common.vo.User;
import com.cooey.common.vo.Vital;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarePlanTodoActivity extends AppCompatActivity implements OnUpdateVitalsList, BPFullScreenDialog.OnUpdateVitalsList, WeightFullScreenDialog.OnUpdateVitalsList {
    ActionItemAdapter actionItemAdapter;
    List<ActionItem> actionItemList;
    Button btnRetry;
    LinearLayout errorLayout;
    LinearLayout linearLayout;
    Context mContext;
    String patientId;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    String serverUrl;
    String sessionId;
    Toolbar toolbar;
    TextView txtNoActionItems;
    User user;

    class C07631 implements Callback<List<ActionItem>> {

        class C07621 implements OnClickListener {
            C07621() {
            }

            public void onClick(View v) {
                CarePlanTodoActivity.this.errorLayout.setVisibility(4);
            }
        }

        C07631() {
        }

        public void onResponse(Call<List<ActionItem>> call, Response<List<ActionItem>> response) {
            if (response.isSuccessful() && response.body() != null && ((List) response.body()).size() > 0) {
                CarePlanTodoActivity.this.actionItemList = (List) response.body();
                CarePlanTodoActivity.this.recyclerView.setLayoutManager(new LinearLayoutManager(BaseApplication.getContext()));
                CarePlanTodoActivity.this.actionItemAdapter = new ActionItemAdapter(CarePlanTodoActivity.this.sortedActionItemsOnPatients(CarePlanTodoActivity.this.actionItemList, true), CarePlanTodoActivity.this.mContext, CarePlanTodoActivity.this.user, CarePlanTodoActivity.this.serverUrl);
                CarePlanTodoActivity.this.recyclerView.setAdapter(CarePlanTodoActivity.this.actionItemAdapter);
            } else if (CarePlanTodoActivity.this.txtNoActionItems.getVisibility() == 4) {
                CarePlanTodoActivity.this.txtNoActionItems.setVisibility(0);
                CarePlanTodoActivity.this.txtNoActionItems.setText(C0757R.string.no_action_items);
            }
            CarePlanTodoActivity.this.hideDialog();
        }

        public void onFailure(Call<List<ActionItem>> call, Throwable t) {
            t.printStackTrace();
            CarePlanTodoActivity.this.hideDialog();
            if (CarePlanTodoActivity.this.errorLayout.getVisibility() == 4) {
                CarePlanTodoActivity.this.errorLayout.setVisibility(0);
                CarePlanTodoActivity.this.btnRetry.setOnClickListener(new C07621());
            }
        }
    }

    class C07642 implements Comparator<ActionItem> {
        C07642() {
        }

        public int compare(ActionItem m1, ActionItem m2) {
            return new Date(m1.getScheduledOn()).compareTo(new Date(m2.getScheduledOn()));
        }
    }

    class C07653 implements Callback<User> {
        C07653() {
        }

        public void onResponse(Call<User> call, Response<User> response) {
            CarePlanTodoActivity.this.hideError();
            if (response.body() != null && response.isSuccessful()) {
                CarePlanTodoActivity.this.user = (User) response.body();
                CarePlanTodoActivity.this.getActionItems();
            }
        }

        public void onFailure(Call<User> call, Throwable t) {
            CarePlanTodoActivity.this.user = null;
            CarePlanTodoActivity.this.hideDialog();
            CarePlanTodoActivity.this.showError();
        }
    }

    class C07664 implements Callback<Vital> {
        C07664() {
        }

        public void onResponse(Call<Vital> call, Response<Vital> response) {
        }

        public void onFailure(Call<Vital> call, Throwable t) {
            t.printStackTrace();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0757R.layout.activity_careplan_todo_user);
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
            partnerConfigPreferences.setVitalConfig(this, Arrays.asList(vitalTemplatesConfigListItems));
        }
        this.mContext = this;
        initView();
        this.user = getUserInfo();
    }

    private void initView() {
        this.errorLayout = (LinearLayout) findViewById(C0757R.id.error_layout);
        this.recyclerView = (RecyclerView) findViewById(C0757R.id.rev_action_items);
        this.btnRetry = (Button) findViewById(C0757R.id.error_btn_retry);
        this.progressBar = (ProgressBar) findViewById(C0757R.id.pb_careplan_list);
        this.linearLayout = (LinearLayout) findViewById(C0757R.id.error_layout);
        this.toolbar = (Toolbar) findViewById(C0757R.id.toolbar);
        this.txtNoActionItems = (TextView) findViewById(C0757R.id.txt_no_action_items);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Today's Actions");
    }

    private void getActionItems() {
        showDialog();
        new ApiClient(BaseApplication.getContext(), this.serverUrl).getActionItemService().getActionItemsForPatients(this.sessionId, this.patientId, getStartOfDay(Calendar.getInstance().getTime()), getEndOfDay(Calendar.getInstance().getTime())).enqueue(new C07631());
    }

    private List<ListItem> sortedActionItemsOnPatients(List<ActionItem> actionItemList, boolean isByPatient) {
        List<ListItem> listItemList = new ArrayList();
        if (actionItemList != null && actionItemList.size() > 0) {
            String patientId;
            Collections.sort(actionItemList, new C07642());
            Set<String> uniquePatientId = new HashSet();
            for (ActionItem actionItem : actionItemList) {
                uniquePatientId.add(actionItem.getPatientId());
            }
            Map<String, List<ActionItem>> longListMap = new HashMap();
            for (String patientId2 : uniquePatientId) {
                longListMap.put(patientId2, new ArrayList());
            }
            for (ActionItem actionItem2 : actionItemList) {
                patientId2 = actionItem2.getPatientId();
                if (longListMap.containsKey(patientId2)) {
                    ((List) longListMap.get(patientId2)).add(actionItem2);
                }
            }
            for (Entry<String, List<ActionItem>> entry : longListMap.entrySet()) {
                if (entry.getValue() != null && ((List) entry.getValue()).size() > 0) {
                    if (isByPatient) {
                        listItemList.add(new ActionItemPatientHeader((String) entry.getKey()));
                        listItemList.addAll((Collection) entry.getValue());
                    } else {
                        listItemList.add(new ActionItemPatient((String) entry.getKey()));
                        listItemList.addAll((Collection) entry.getValue());
                    }
                }
            }
        }
        return listItemList;
    }

    public void showDialog() {
        if (this.progressBar.getVisibility() == 4) {
            this.progressBar.setVisibility(0);
        }
    }

    public void hideDialog() {
        if (this.progressBar.getVisibility() == 0) {
            this.progressBar.setVisibility(4);
        }
    }

    public void showError() {
        this.linearLayout.setVisibility(0);
    }

    public void hideError() {
        this.linearLayout.setVisibility(8);
    }

    public User getUserInfo() {
        if (this.serverUrl != null) {
            new ApiClient(this, this.serverUrl).getUsersService().get_0(this.patientId, this.sessionId).enqueue(new C07653());
        }
        return this.user;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
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

    public void onComplete(Vital vital) {
        if (NetworkUtils.isNetworkConnected(this)) {
            vital.setSync(true);
            updateVitalInServer(vital);
            return;
        }
        vital.setSync(false);
        vital.setVitalId(UUID.randomUUID().toString());
        VitalStore.getInstance(this).writeToVital(vital);
        CTUtility.showToast(this, getString(C0757R.string.refresh_once_connected));
    }

    private void updateVitalInServer(Vital vital) {
        new ApiClient(this, this.serverUrl).getVitalsService().addVitalForUser(this.sessionId, vital).enqueue(new C07664());
    }

    public static long getEndOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTime().getTime();
    }

    public static long getStartOfDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTime().getTime();
    }
}
