package com.biz.health.cooey_app.diet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.services.ApiClient;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.DietTemplate;
import com.cooey.common.vo.MealPlan;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientDietList extends AppCompatActivity {
    LinearLayout linearLayout;
    private MealPlanListAdapter mealListAdapter;
    private String patientId;
    private ProgressBar progressBarDiet;
    private RecyclerView recyclerViewMealList;

    class C06861 implements Callback<List<DietTemplate>> {
        C06861() {
        }

        public void onResponse(Call<List<DietTemplate>> call, Response<List<DietTemplate>> response) {
            List<MealPlan> mealList = new ArrayList();
            if (response.body() == null || ((List) response.body()).size() <= 0) {
                PatientDietList.this.showError();
                return;
            }
            mealList.addAll(((DietTemplate) ((List) response.body()).get(0)).getMealPlan());
            PatientDietList.this.mealListAdapter = new MealPlanListAdapter(mealList, PatientDietList.this);
            PatientDietList.this.getSupportActionBar().setTitle(((DietTemplate) ((List) response.body()).get(0)).getDietTitle());
            PatientDietList.this.recyclerViewMealList.setAdapter(PatientDietList.this.mealListAdapter);
            PatientDietList.this.hideError();
        }

        public void onFailure(Call<List<DietTemplate>> call, Throwable t) {
            PatientDietList.this.showError();
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0644R.layout.activity_patient_meal_list);
        setSupportActionBar((Toolbar) findViewById(C0644R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Today's Diet");
        this.patientId = new PreferenceStore().getUser(this).getId();
        this.recyclerViewMealList = (RecyclerView) findViewById(C0644R.id.rev_meal_list);
        this.linearLayout = (LinearLayout) findViewById(C0644R.id.error_layout);
        this.progressBarDiet = (ProgressBar) findViewById(C0644R.id.pb_diet);
        this.recyclerViewMealList.setLayoutManager(new LinearLayoutManager(this));
        getMealList();
    }

    private void getMealList() {
        this.progressBarDiet.setVisibility(0);
        new ApiClient(this, "http://api.cooey.co.in/ehealth/").getDietTemplatesService().getDietTemplateForUser(this.patientId).enqueue(new C06861());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 16908332:
                super.onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showError() {
        if (this.progressBarDiet.getVisibility() == 0) {
            this.progressBarDiet.setVisibility(8);
            this.linearLayout.setVisibility(0);
        }
    }

    public void hideError() {
        if (this.progressBarDiet.getVisibility() == 0) {
            this.progressBarDiet.setVisibility(8);
            this.linearLayout.setVisibility(8);
        }
    }
}
