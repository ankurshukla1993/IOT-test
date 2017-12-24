package com.cooey.android.users.old.viewholders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import com.cooey.android.users.C0757R;
import com.cooey.android.users.old.activities.AllMedsActivity;
import com.cooey.android.users.old.adapters.MedicinesAdapter;
import com.cooey.common.services.ApiClient;
import com.cooey.common.vo.Medicine;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicineTypeViewHolder extends ChildViewHolder {
    Button btnMedsShowAll;
    Context context;
    List<Medicine> medicineList = new ArrayList();
    MedicinesAdapter medicinesAdapter;
    ProgressBar progressBar;
    RecyclerView recyclerView;
    String serverUrl;
    String sessionId;
    String userId;

    class C08082 implements Callback<List<Medicine>> {
        C08082() {
        }

        public void onResponse(Call<List<Medicine>> call, Response<List<Medicine>> response) {
            if (response.isSuccessful() && response.body() != null && ((List) response.body()).size() > 0) {
                MedicineTypeViewHolder.this.medicineList = (List) response.body();
                MedicineTypeViewHolder.this.medicinesAdapter = new MedicinesAdapter(MedicineTypeViewHolder.this.context, MedicineTypeViewHolder.this.medicineList);
                MedicineTypeViewHolder.this.recyclerView.setAdapter(MedicineTypeViewHolder.this.medicinesAdapter);
                MedicineTypeViewHolder.this.recyclerView.setLayoutManager(new LinearLayoutManager(MedicineTypeViewHolder.this.context));
                MedicineTypeViewHolder.this.progressBar.setVisibility(8);
            }
        }

        public void onFailure(Call<List<Medicine>> call, Throwable t) {
            MedicineTypeViewHolder.this.progressBar.setVisibility(8);
        }
    }

    public MedicineTypeViewHolder(Context context, View itemView, String sessionId, String userId, String serverUrl) {
        super(itemView);
        this.context = context;
        this.sessionId = sessionId;
        this.userId = userId;
        this.serverUrl = serverUrl;
        this.recyclerView = (RecyclerView) itemView.findViewById(C0757R.id.rev_list_meds);
        this.btnMedsShowAll = (Button) itemView.findViewById(C0757R.id.btn_meds_show_all);
        this.progressBar = (ProgressBar) itemView.findViewById(C0757R.id.progressBar);
        final View view = itemView;
        final String str = userId;
        final String str2 = sessionId;
        final String str3 = serverUrl;
        this.btnMedsShowAll.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext(), AllMedsActivity.class);
                intent.putExtra("patientId", str);
                intent.putExtra("sessionId", str2);
                intent.putExtra("serverurl", str3);
                view.getContext().startActivity(intent);
            }
        });
    }

    public void getUserMedicines() {
        this.progressBar.setVisibility(0);
        new ApiClient(this.context, this.serverUrl).getMedicinesService().getMedicineReminderForPatient(this.sessionId, this.userId).enqueue(new C08082());
    }

    public void setMedicineViewHolder() {
        getUserMedicines();
    }

    public void showError() {
        this.btnMedsShowAll.setVisibility(8);
        this.progressBar.setVisibility(8);
    }

    public void hideError() {
        this.btnMedsShowAll.setVisibility(0);
    }
}
