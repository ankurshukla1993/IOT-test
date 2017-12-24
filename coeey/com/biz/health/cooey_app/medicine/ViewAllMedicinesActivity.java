package com.biz.health.cooey_app.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.MainActivity;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.services.ApiClient;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.views.DividerItemDecoration;
import com.cooey.common.vo.Medicine;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewAllMedicinesActivity extends AppCompatActivity {
    @BindView(2131361899)
    FloatingActionButton btnAddNewMedicine;
    private List<Medicine> medicineList;
    private MedicineListAdapter medicineListAdapter;
    @BindView(2131362389)
    ProgressBar progressBar;
    @BindView(2131362443)
    RecyclerView revMedicines;
    private String token;
    @BindView(2131362626)
    Toolbar toolbar;
    @BindView(2131362714)
    TextView txtNoMedicineText;
    private String userId;

    class C07031 implements Callback<List<Medicine>> {
        C07031() {
        }

        public void onResponse(Call<List<Medicine>> call, Response<List<Medicine>> response) {
            if (response.isSuccessful() && response.body() != null && ((List) response.body()).size() > 0) {
                ViewAllMedicinesActivity.this.medicineList.addAll((Collection) response.body());
                ViewAllMedicinesActivity.this.medicineListAdapter = new MedicineListAdapter(ViewAllMedicinesActivity.this.medicineList, ViewAllMedicinesActivity.this);
                ViewAllMedicinesActivity.this.revMedicines.setAdapter(ViewAllMedicinesActivity.this.medicineListAdapter);
                ViewAllMedicinesActivity.this.revMedicines.setLayoutManager(new LinearLayoutManager(ViewAllMedicinesActivity.this));
                ViewAllMedicinesActivity.this.revMedicines.addItemDecoration(new DividerItemDecoration(ViewAllMedicinesActivity.this, 1));
            } else if (response.body() != null && ((List) response.body()).size() == 0) {
                ViewAllMedicinesActivity.this.txtNoMedicineText.setVisibility(0);
            }
            ViewAllMedicinesActivity.this.progressBar.setVisibility(4);
        }

        public void onFailure(Call<List<Medicine>> call, Throwable t) {
            ViewAllMedicinesActivity.this.progressBar.setVisibility(4);
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0644R.layout.activity_medicines);
        ButterKnife.bind(this);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(CTConstants.MEDICINES);
    }

    public void getUserMedicines() {
        this.progressBar.setVisibility(0);
        new ApiClient(this, "http://api.cooey.co.in/ehealth/").getMedicinesService().getMedicineReminderForPatient(this.token, this.userId).enqueue(new C07031());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick({2131361899})
    public void addNewMedicine() {
        startActivity(new Intent(this, MedicineSearchActivity.class));
        finish();
    }

    protected void onResume() {
        super.onResume();
        this.medicineList = new ArrayList();
        PreferenceStore preferenceStore = new PreferenceStore();
        this.token = preferenceStore.getSession(this).getId();
        this.userId = preferenceStore.getUser(this).getId();
        getUserMedicines();
    }
}
