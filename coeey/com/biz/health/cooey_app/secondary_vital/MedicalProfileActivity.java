package com.biz.health.cooey_app.secondary_vital;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.utils.DividerItemDecoration;
import com.cooey.common.realm_store.VitalStore;
import com.cooey.common.vo.Vital;
import java.util.ArrayList;
import java.util.List;

public class MedicalProfileActivity extends AppCompatActivity {
    @BindView(2131362442)
    RecyclerView revMedicalProfile;
    private List<Vital> secondaryVitalList;
    @BindView(2131362626)
    Toolbar toolbar;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0644R.layout.activity_medical_profile);
        ButterKnife.bind(this);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Medical Profile");
        this.secondaryVitalList = new ArrayList();
        if (VitalStore.getInstance(this).getAllVitals() != null && VitalStore.getInstance(this).getAllVitals().size() > 0) {
            this.secondaryVitalList = VitalStore.getInstance(this).getAllVitals();
            setAdapter();
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

    public void onBackPressed() {
        super.onBackPressed();
    }

    private void setAdapter() {
        List<Vital> vitals = new ArrayList();
        if (this.secondaryVitalList != null && this.secondaryVitalList.size() > 0) {
            for (Vital vital : this.secondaryVitalList) {
                if (!(vital.getVitalType().equalsIgnoreCase("BLOOD_PRESSURE") || vital.getVitalType().equalsIgnoreCase("BLOOD_GLUCOSE") || vital.getVitalType().equalsIgnoreCase("WEIGHT"))) {
                    vitals.add(vital);
                }
            }
            this.revMedicalProfile.setLayoutManager(new LinearLayoutManager(this));
            this.revMedicalProfile.addItemDecoration(new DividerItemDecoration(this, 1));
            this.revMedicalProfile.setAdapter(new MedicalProfileAdapter(vitals, this));
        }
    }
}
