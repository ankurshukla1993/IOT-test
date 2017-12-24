package com.cooey.android.users.old.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ProgressBar;
import com.cooey.android.users.C0757R;
import com.cooey.android.users.old.adapters.MedicinesAdapter;
import com.cooey.common.services.ApiClient;
import com.cooey.common.vo.Medicine;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllMedsActivity extends AppCompatActivity {
    Context context;
    List<Medicine> medicineList = new ArrayList();
    MedicinesAdapter medicinesAdapter;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    String serverUrl;
    String sessionId;
    Toolbar toolbar;
    String userId;

    class C07611 implements Callback<List<Medicine>> {
        C07611() {
        }

        public void onResponse(Call<List<Medicine>> call, Response<List<Medicine>> response) {
            if (response.isSuccessful() && ((List) response.body()).size() > 0) {
                AllMedsActivity.this.medicineList = (List) response.body();
            }
            AllMedsActivity.this.medicinesAdapter = new MedicinesAdapter(AllMedsActivity.this.context, AllMedsActivity.this.medicineList);
            AllMedsActivity.this.recyclerView.setAdapter(AllMedsActivity.this.medicinesAdapter);
            AllMedsActivity.this.recyclerView.setLayoutManager(new LinearLayoutManager(AllMedsActivity.this.context));
            AllMedsActivity.this.progressBar.setVisibility(8);
        }

        public void onFailure(Call<List<Medicine>> call, Throwable t) {
        }
    }

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0757R.layout.activity_all_meds_user);
        this.recyclerView = (RecyclerView) findViewById(C0757R.id.rev_list_meds);
        this.progressBar = (ProgressBar) findViewById(C0757R.id.progressBar);
        this.toolbar = (Toolbar) findViewById(C0757R.id.toolbar);
        setSupportActionBar(this.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        if (getIntent().getExtras() != null) {
            Intent intent = getIntent();
            this.userId = intent.getStringExtra("patientId");
            this.sessionId = intent.getStringExtra("sessionId");
            this.serverUrl = intent.getStringExtra("serverurl");
        }
        getUserMedicines();
    }

    public void getUserMedicines() {
        this.progressBar.setVisibility(0);
        new ApiClient(this.context, this.serverUrl).getMedicinesService().getMedicineReminderForPatient(this.sessionId, this.userId).enqueue(new C07611());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
