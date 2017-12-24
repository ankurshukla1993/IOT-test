package com.cooey.android.users.old.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cooey.android.users.C0757R;
import com.cooey.android.users.old.PatientInterventionHeader;
import com.cooey.android.users.old.adapters.MultiTypeInterventionAdapter;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.android.users.old.utils.DateUtil;
import com.cooey.common.services.ApiClient;
import com.cooey.common.vo.User;
import com.cooey.common.vo.careplan.Careplan;
import com.cooey.common.vo.careplan.Intervention;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterventionFragment extends Fragment {
    MultiTypeInterventionAdapter multiTypeInterventionAdapter;
    RecyclerView revAboutPatient;
    public String serverUrl;
    public String sessionId;
    public TextView txtCareplanName;
    public TextView txtDuration;
    public TextView txtEndTime;
    public TextView txtNoInterevetions;
    public TextView txtStartTime;
    public User user;

    class C07981 implements Observer<Careplan> {
        C07981() {
        }

        public void onSubscribe(@NonNull Disposable d) {
        }

        public void onNext(@NonNull Careplan careplan) {
            InterventionFragment.this.revAboutPatient.setLayoutManager(new LinearLayoutManager(InterventionFragment.this.getContext()));
            InterventionFragment.this.setCareplan(careplan);
            InterventionFragment.this.getInterventions(careplan.getId());
        }

        public void onError(@NonNull Throwable e) {
            e.printStackTrace();
        }

        public void onComplete() {
        }
    }

    class C07992 implements Callback<List<Intervention>> {
        C07992() {
        }

        public void onResponse(Call<List<Intervention>> call, Response<List<Intervention>> response) {
            if (response.isSuccessful() && response.body() != null && ((List) response.body()).size() > 0) {
                InterventionFragment.this.multiTypeInterventionAdapter = new MultiTypeInterventionAdapter(InterventionFragment.this.getContext(), InterventionFragment.this.makeInterventions((List) response.body()), InterventionFragment.this.user, InterventionFragment.this.sessionId);
                InterventionFragment.this.revAboutPatient.setLayoutManager(new LinearLayoutManager(InterventionFragment.this.getContext()));
                for (int i = InterventionFragment.this.makeInterventions((List) response.body()).size() - 1; i >= 0; i--) {
                    InterventionFragment.this.expandGroup(i);
                }
                if (InterventionFragment.this.txtNoInterevetions.getVisibility() == 0) {
                    InterventionFragment.this.txtNoInterevetions.setVisibility(4);
                    InterventionFragment.this.revAboutPatient.setVisibility(0);
                }
                InterventionFragment.this.revAboutPatient.setAdapter(InterventionFragment.this.multiTypeInterventionAdapter);
            } else if (InterventionFragment.this.txtNoInterevetions.getVisibility() == 4) {
                InterventionFragment.this.txtNoInterevetions.setVisibility(0);
                InterventionFragment.this.revAboutPatient.setVisibility(4);
            }
        }

        public void onFailure(Call<List<Intervention>> call, Throwable t) {
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(C0757R.layout.intervention_fragment_user, container, false);
        initView(view);
        this.revAboutPatient = (RecyclerView) view.findViewById(C0757R.id.rev_intervention_patient);
        getCareplan();
        return view;
    }

    private void initView(View view) {
        this.txtCareplanName = (TextView) view.findViewById(C0757R.id.txt_view_careplan_name_value);
        this.txtStartTime = (TextView) view.findViewById(C0757R.id.txt_start_time_value);
        this.txtEndTime = (TextView) view.findViewById(C0757R.id.txt_end_time_value);
        this.txtDuration = (TextView) view.findViewById(C0757R.id.txt_duration_value);
        this.txtNoInterevetions = (TextView) view.findViewById(C0757R.id.txt_no_intervetions);
    }

    public void getCareplan() {
        String token = this.sessionId;
        if (this.serverUrl != null) {
            new ApiClient(getContext(), this.serverUrl).getCareplanServiceWithObservable().getCareplanForPatient(token, this.user.getId()).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new C07981());
        }
    }

    private void setCareplan(Careplan careplanName) {
        if (careplanName != null) {
            if (careplanName.getName() != null) {
                this.txtCareplanName.setText(careplanName.getName());
            }
            if (careplanName.getCreatedOn() > 0) {
                this.txtStartTime.setText(DateUtil.getReadableStringFromDate(new Date(careplanName.getCreatedOn())));
            }
            if (careplanName.getEndTime() > 0) {
                this.txtEndTime.setText(DateUtil.getReadableStringFromDate(new Date(careplanName.getEndTime())));
            }
            if (careplanName.getNumOfDays() > 0) {
                this.txtDuration.setText(String.format(Locale.ENGLISH, "%d Days", new Object[]{Integer.valueOf(careplanName.getNumOfDays())}));
            }
        }
    }

    public void getInterventions(String careplanId) {
        String token = this.sessionId;
        if (this.serverUrl != null) {
            new ApiClient(getContext(), this.serverUrl).getCareplanServiceWithObservable().getCareplanInterventions(this.sessionId, careplanId).enqueue(new C07992());
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null && this.multiTypeInterventionAdapter != null) {
            this.multiTypeInterventionAdapter.onSaveInstanceState(outState);
        }
    }

    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null && this.multiTypeInterventionAdapter != null) {
            this.multiTypeInterventionAdapter.onRestoreInstanceState(savedInstanceState);
        }
    }

    public void expandGroup(int gPos) {
        if (!this.multiTypeInterventionAdapter.isGroupExpanded(gPos)) {
            this.multiTypeInterventionAdapter.toggleGroup(gPos);
        }
    }

    public List<PatientInterventionHeader> makeInterventions(List<Intervention> interventions) {
        List<PatientInterventionHeader> patientInterventionHeaders = new ArrayList();
        for (Entry<String, List<Intervention>> entry : sortInterventions(interventions).entrySet()) {
            if (entry.getValue() != null && ((List) entry.getValue()).size() > 0) {
                patientInterventionHeaders.add(new PatientInterventionHeader((String) entry.getKey(), (List) entry.getValue()));
            }
        }
        return patientInterventionHeaders;
    }

    public LinkedHashMap<String, List<Intervention>> sortInterventions(List<Intervention> interventions) {
        LinkedHashMap<String, List<Intervention>> listLinkedHashMap = new LinkedHashMap();
        listLinkedHashMap.put(CTConstants.TODO_TYPE, new ArrayList());
        listLinkedHashMap.put(CTConstants.VITAL_TYPE, new ArrayList());
        listLinkedHashMap.put(CTConstants.ACTIVITY_TYPE, new ArrayList());
        listLinkedHashMap.put(CTConstants.DIET_TYPE, new ArrayList());
        for (Intervention intervention : interventions) {
            if (intervention.getType().contentEquals(CTConstants.TODO_TYPE)) {
                ((List) listLinkedHashMap.get(CTConstants.TODO_TYPE)).add(intervention);
            }
            if (intervention.getType().contentEquals(CTConstants.VITAL_TYPE)) {
                ((List) listLinkedHashMap.get(CTConstants.VITAL_TYPE)).add(intervention);
            }
            if (intervention.getType().contentEquals(CTConstants.ACTIVITY_TYPE)) {
                ((List) listLinkedHashMap.get(CTConstants.ACTIVITY_TYPE)).add(intervention);
            }
            if (intervention.getType().contentEquals(CTConstants.DIET_TYPE)) {
                ((List) listLinkedHashMap.get(CTConstants.DIET_TYPE)).add(intervention);
            }
        }
        return listLinkedHashMap;
    }
}
