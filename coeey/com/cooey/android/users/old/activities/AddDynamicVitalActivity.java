package com.cooey.android.users.old.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.cooey.android.users.C0757R;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.android.users.old.utils.CTUtility;
import com.cooey.android.users.old.utils.PartnerConfigPreferences;
import com.cooey.common.config.FieldConfig;
import com.cooey.common.config.VitalTemplatesConfigListItem;
import com.cooey.common.services.ApiClient;
import com.cooey.common.vo.Vital;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddDynamicVitalActivity extends AppCompatActivity {
    List<EditText> allEds;
    private Map<String, List<FieldConfig>> fieldConfigMap;
    private List<FieldConfig> fieldConfigs;
    private Gson gson;
    ImageView imvSave;
    LinearLayout linear;
    String patientId;
    String serverUrl;
    Toolbar toolbar;
    private List<String> vitalLabels;
    private List<Vital> vitalList;
    private String vitalName;

    class C07591 implements OnClickListener {
        C07591() {
        }

        public void onClick(View view) {
            AddDynamicVitalActivity.this.saveSecondaryVital();
        }
    }

    class C07602 implements Callback<Vital> {
        C07602() {
        }

        public void onResponse(Call<Vital> call, Response<Vital> response) {
        }

        public void onFailure(Call<Vital> call, Throwable t) {
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0757R.layout.activity_add_dynamic_vital_user);
        this.linear = (LinearLayout) findViewById(C0757R.id.linear_layout);
        this.toolbar = (Toolbar) findViewById(C0757R.id.toolbar);
        this.imvSave = (ImageView) findViewById(C0757R.id.imv_save);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.fieldConfigMap = new HashMap();
        for (VitalTemplatesConfigListItem vitalTemplatesConfigListItem : new PartnerConfigPreferences().getVitalConfigResponse(this)) {
            this.fieldConfigMap.put(vitalTemplatesConfigListItem.getType(), vitalTemplatesConfigListItem.getFieldConfigList());
        }
        this.gson = new GsonBuilder().create();
        if (getIntent().getExtras() != null) {
            this.vitalName = getIntent().getExtras().getString("SECONDARY_VITAL");
            this.patientId = getIntent().getExtras().getString(CTConstants.PATIENT_ID);
            this.serverUrl = getIntent().getExtras().getString("serverurl");
        }
        if (this.vitalName != null) {
            this.toolbar.setTitle(this.vitalName);
        }
        this.imvSave.setOnClickListener(new C07591());
        setEditText();
    }

    public void saveSecondaryVital() {
        Object secondaryVitalParameterMap = new HashMap();
        int i = 0;
        while (i < this.allEds.size()) {
            if (((EditText) this.allEds.get(i)).getText().toString().trim().length() <= 0 || ((EditText) this.allEds.get(i)).getText().toString().trim().isEmpty()) {
                secondaryVitalParameterMap.put(((FieldConfig) this.fieldConfigs.get(i)).getLabel(), "");
            } else {
                secondaryVitalParameterMap.put(((FieldConfig) this.fieldConfigs.get(i)).getLabel(), ((EditText) this.allEds.get(i)).getText().toString());
            }
            i++;
        }
        Vital secondaryVital = new Vital();
        secondaryVital.setVitalType(this.vitalName);
        secondaryVital.setParameters(this.gson.toJson(secondaryVitalParameterMap));
        secondaryVital.setTakenOn(Calendar.getInstance().getTime().getTime());
        secondaryVital.setUserId(this.patientId);
        secondaryVital.setTimestamp(Calendar.getInstance().getTime().getTime());
        String token = CTUtility.getStringFromSharedPref(this, CTConstants.SESSION_ID, null);
        new ApiClient(this, this.serverUrl).getVitalsService().addVitalForUser(token, secondaryVital).enqueue(new C07602());
        Intent intent = new Intent(this, CarePlanTodoActivity.class);
        intent.putExtra(CTConstants.PATIENT_ID, this.patientId);
        intent.putExtra(CTConstants.SESSION_ID, token);
        intent.putExtra("setPartnerConfig", false);
        startActivity(intent);
        finish();
    }

    public void setEditText() {
        this.fieldConfigs = (List) this.fieldConfigMap.get(this.vitalName);
        this.allEds = new ArrayList();
        for (int i = 0; i < this.fieldConfigs.size(); i++) {
            EditText ed = new EditText(this);
            this.allEds.add(ed);
            ed.setId(i);
            ed.setHint(((FieldConfig) this.fieldConfigs.get(i)).getLabel());
            ed.setLayoutParams(new LayoutParams(-1, -2));
            this.linear.addView(ed);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
