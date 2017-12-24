package com.biz.health.cooey_app.secondary_vital;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.MainActivity;
import com.cooey.common.config.FieldConfig;
import com.cooey.common.config.VitalTemplatesConfigListItem;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Vital;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddDynamicVitalActivity extends AppCompatActivity {
    List<EditText> allEds;
    private Map<String, List<FieldConfig>> fieldConfigMap;
    private List<FieldConfig> fieldConfigs;
    private Gson gson;
    @BindView(2131362249)
    LinearLayout linear;
    String patientId;
    @BindView(2131362626)
    Toolbar toolbar;
    private List<String> vitalLabels;
    private List<Vital> vitalList;
    private String vitalName;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0644R.layout.activity_add_dynamic_vital);
        ButterKnife.bind(this);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.fieldConfigMap = new HashMap();
        for (VitalTemplatesConfigListItem vitalTemplatesConfigListItem : new PreferenceStore().getVitalConfigResponse(this)) {
            this.fieldConfigMap.put(vitalTemplatesConfigListItem.getType(), vitalTemplatesConfigListItem.getFieldConfigList());
        }
        this.gson = new GsonBuilder().create();
        if (getIntent().getExtras() != null) {
            this.vitalName = getIntent().getExtras().getString("SECONDARY_VITAL");
        }
        if (this.vitalName != null) {
            this.toolbar.setTitle(this.vitalName);
        }
        setEditText();
    }

    @OnClick({2131362201})
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
        String userId = new PreferenceStore().getUser(this).getId();
        secondaryVital.setTakenBy(userId);
        secondaryVital.setVitalType(this.vitalName);
        secondaryVital.setParameters(this.gson.toJson(secondaryVitalParameterMap));
        secondaryVital.setUserId(userId);
        secondaryVital.setTimestamp(Calendar.getInstance().getTime().getTime());
        secondaryVital.setTakenOn(Calendar.getInstance().getTime().getTime());
        secondaryVital.setDeviceReading(false);
        secondaryVital.setPlatform("ANDROID");
        startActivity(new Intent(this, MainActivity.class));
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
                startActivity(new Intent(this, MedicalProfileActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
