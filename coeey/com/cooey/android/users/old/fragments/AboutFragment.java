package com.cooey.android.users.old.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.cooey.android.users.C0757R;
import com.cooey.android.users.old.PatientCareplanHeader;
import com.cooey.android.users.old.adapters.MultiTypeAboutAdapter;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.services.ApiClient;
import com.cooey.common.vo.User;
import com.cooey.common.vo.careplan.Careplan;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

public class AboutFragment extends Fragment {
    LinearLayout linearLayout;
    MultiTypeAboutAdapter multiTypeAboutAdapter;
    private Careplan patientCareplan;
    RecyclerView revAboutPatient;
    public String serverUrl;
    public String sessionId;
    public String tenantId;
    public User user;

    class C07971 implements Observer<Careplan> {
        C07971() {
        }

        public void onSubscribe(@NonNull Disposable d) {
        }

        public void onNext(@NonNull Careplan careplan) {
            AboutFragment.this.patientCareplan = careplan;
            AboutFragment.this.multiTypeAboutAdapter = new MultiTypeAboutAdapter(AboutFragment.this.getContext(), AboutFragment.this.makeInterventions(careplan), AboutFragment.this.user, AboutFragment.this.sessionId, AboutFragment.this.serverUrl);
            AboutFragment.this.revAboutPatient.setLayoutManager(new LinearLayoutManager(AboutFragment.this.getContext()));
            AboutFragment.this.revAboutPatient.setAdapter(AboutFragment.this.multiTypeAboutAdapter);
            AboutFragment.this.linearLayout.setVisibility(8);
        }

        public void onError(@NonNull Throwable e) {
            AboutFragment.this.multiTypeAboutAdapter = new MultiTypeAboutAdapter(AboutFragment.this.getContext(), AboutFragment.this.makeInterventions(null), AboutFragment.this.user, AboutFragment.this.sessionId, AboutFragment.this.serverUrl);
            AboutFragment.this.revAboutPatient.setLayoutManager(new LinearLayoutManager(AboutFragment.this.getContext()));
            AboutFragment.this.revAboutPatient.setAdapter(AboutFragment.this.multiTypeAboutAdapter);
            AboutFragment.this.linearLayout.setVisibility(8);
            e.printStackTrace();
        }

        public void onComplete() {
        }
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(C0757R.layout.about_fragment_user, container, false);
        this.revAboutPatient = (RecyclerView) view.findViewById(C0757R.id.rev_about_patient);
        this.linearLayout = (LinearLayout) view.findViewById(C0757R.id.error_layout);
        if (this.user != null) {
            getPatientCareplan();
        }
        return view;
    }

    private void getPatientCareplan() {
        String token = this.sessionId;
        if (this.serverUrl != null) {
            new ApiClient(getContext(), this.serverUrl).getCareplanServiceWithObservable().getCareplanForPatient(token, this.user.getId()).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new C07971());
        }
    }

    public List<PatientCareplanHeader> makeInterventions(Careplan careplan) {
        List<PatientCareplanHeader> interventionBlueprintHeaders = new ArrayList();
        List<Careplan> careplen = new ArrayList();
        LinkedHashMap<String, List<Careplan>> stringListHashMap = new LinkedHashMap();
        careplen.add(careplan);
        if (this.tenantId == null || !this.tenantId.equalsIgnoreCase("59103fd758e21e3dcc022760")) {
            stringListHashMap.put(CTConstants.ABOUT, careplen);
            stringListHashMap.put(CTConstants.ASSESSMENT, careplen);
            stringListHashMap.put(CTConstants.DIAGNOSIS, careplen);
            stringListHashMap.put(CTConstants.HISTORY, careplen);
            stringListHashMap.put(CTConstants.CAREPLAN_GOAL, careplen);
            stringListHashMap.put(CTConstants.EVALUATION, careplen);
            stringListHashMap.put(CTConstants.MEDICINES, careplen);
        } else {
            stringListHashMap.put(CTConstants.ABOUT, careplen);
            stringListHashMap.put(CTConstants.MEDICINES, careplen);
        }
        for (Entry<String, List<Careplan>> entry : stringListHashMap.entrySet()) {
            if (entry.getValue() != null && ((List) entry.getValue()).size() > 0) {
                interventionBlueprintHeaders.add(new PatientCareplanHeader((String) entry.getKey(), (List) entry.getValue()));
            }
        }
        return interventionBlueprintHeaders;
    }
}
