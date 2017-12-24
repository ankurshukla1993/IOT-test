package com.biz.health.cooey_app.dashboard;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.dashboard.widgets.ExpandableListAdapter;
import com.cooey.common.vo.careplan.Careplan;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AboutFragment extends Fragment {
    public Careplan careplan = null;
    private Context context;
    ExpandableListAdapter expandableListAdapter;
    private ExpandableListView expandableListView;
    private HashMap<String, List<String>> listDataChild = new HashMap();
    private List<String> listDataHeader = new ArrayList();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getContext();
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.listDataHeader.addAll(Arrays.asList(new String[]{"Subjective", "Health Condition", "History", "Goal"}));
        if (this.careplan != null) {
            if (this.careplan.getAssessment() == null || this.careplan.getAssessment().getSubjective() == null) {
                this.listDataChild.put("Subjective", Arrays.asList(new String[0]));
            } else {
                this.listDataChild.put("Subjective", Arrays.asList(new String[]{this.careplan.getAssessment().getSubjective()}));
            }
            if (this.careplan.getDiagnosis() == null || this.careplan.getDiagnosis().getHealthConditions() == null) {
                this.listDataChild.put("Health Condition", Arrays.asList(new String[0]));
            } else {
                this.listDataChild.put("Health Condition", Arrays.asList(new String[]{this.careplan.getDiagnosis().getHealthConditions()}));
            }
            if (this.careplan.getHistory() != null) {
                this.listDataChild.put("History", Arrays.asList(new String[]{this.careplan.getHistory()}));
            } else {
                this.listDataChild.put("History", Arrays.asList(new String[0]));
            }
            if (this.careplan.getGoal() != null) {
                this.listDataChild.put("Goal", Arrays.asList(new String[]{this.careplan.getGoal()}));
            } else {
                this.listDataChild.put("Goal", Arrays.asList(new String[0]));
            }
        } else {
            this.listDataChild.put("Subjective", Arrays.asList(new String[0]));
            this.listDataChild.put("Health Condition", Arrays.asList(new String[0]));
            this.listDataChild.put("History", Arrays.asList(new String[0]));
            this.listDataChild.put("Goal", Arrays.asList(new String[0]));
        }
        this.expandableListAdapter = new ExpandableListAdapter(this.context, this.listDataHeader, this.listDataChild, "ABOUT");
        View view = inflater.inflate(C0644R.layout.fragment_about, container, false);
        this.expandableListView = (ExpandableListView) view.findViewById(C0644R.id.exp_lv);
        this.expandableListView.setAdapter(this.expandableListAdapter);
        this.expandableListAdapter.notifyDataSetChanged();
        return view;
    }
}
