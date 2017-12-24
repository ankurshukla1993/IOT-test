package com.biz.health.cooey_app.medicine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.FloatingSearchView.OnHomeActionClickListener;
import com.arlib.floatingsearchview.FloatingSearchView.OnQueryChangeListener;
import com.arlib.floatingsearchview.FloatingSearchView.OnSearchListener;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.MainActivity;
import com.biz.health.cooey_app.medicine.AddMedicineFullScreenDialog.OnUpdateMedicineRemiderList;
import com.cooey.common.realm_store.MedicineStore;
import com.cooey.common.services.ApiClient;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Medicine;
import com.cooey.common.vo.MedicineSearch;
import com.cooey.common.vo.Session;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicineSearchActivity extends AppCompatActivity implements OnUpdateMedicineRemiderList {
    @BindView(2131362123)
    FloatingSearchView floatingSearchView;
    PreferenceStore preferenceStore = new PreferenceStore();
    Session session;

    class C06901 implements OnHomeActionClickListener {
        C06901() {
        }

        public void onHomeClicked() {
            MedicineSearchActivity.this.startActivity(new Intent(MedicineSearchActivity.this, MainActivity.class));
            MedicineSearchActivity.this.finish();
        }
    }

    public class MedicineSearchResultListner implements OnSearchListener {
        public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
            MedicineSearchActivity.this.addMedine((MedicineSearch) searchSuggestion);
        }

        public void onSearchAction(String currentQuery) {
        }
    }

    public class SearchQueryChangeListener implements OnQueryChangeListener {

        class C06921 implements Callback<List<MedicineSearch>> {
            C06921() {
            }

            public void onResponse(Call<List<MedicineSearch>> call, Response<List<MedicineSearch>> response) {
                if (response.body() != null) {
                    MedicineSearchActivity.this.floatingSearchView.swapSuggestions((List) response.body());
                }
            }

            public void onFailure(Call<List<MedicineSearch>> call, Throwable t) {
            }
        }

        public void onSearchTextChanged(String oldQuery, String newQuery) {
            try {
                new ApiClient(MedicineSearchActivity.this, "http://api.cooey.co.in/ehealth/").getMedicinesService().medicineSearch(MedicineSearchActivity.this.session.getId(), newQuery).enqueue(new C06921());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0644R.layout.dialog_search_medicines);
        ButterKnife.bind(this);
        this.session = this.preferenceStore.getSession(this);
        initSearch();
    }

    public void initSearch() {
        this.floatingSearchView.setOnQueryChangeListener(new SearchQueryChangeListener());
        this.floatingSearchView.setOnSearchListener(new MedicineSearchResultListner());
        this.floatingSearchView.setOnHomeActionClickListener(new C06901());
    }

    public void onMedicineReminderAdded(final Medicine medicine) {
        try {
            new ApiClient(this, "http://api.cooey.co.in/ehealth/").getMedicinesService().addMedicineReminder(this.session.getId(), medicine).enqueue(new Callback<Medicine>() {
                public void onResponse(Call<Medicine> call, Response<Medicine> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        MedicineStore.getInstance(MedicineSearchActivity.this).writeToMedicine(medicine);
                    }
                }

                public void onFailure(Call<Medicine> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addMedine(MedicineSearch medicineSearch) {
        new AddMedicineFullScreenDialog().newInstance(medicineSearch).show(getFragmentManager(), "medicineDialog");
    }
}
