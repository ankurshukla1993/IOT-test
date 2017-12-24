package com.biz.health.cooey_app.dashboard.widgets.holders;

import com.biz.health.cooey_app.databinding.SummaryWidgetBinding;
import com.cooey.common.realm_store.VitalStore;
import com.cooey.common.vo.User;
import com.cooey.common.vo.Vital;
import com.cooey.common.vo.timeline.TimelineItem;
import com.cooey.devices.helpers.CooeySQLHelper;
import io.realm.RealmResults;
import java.util.HashMap;

public class SummaryViewHolder extends WidgetViewHolder {
    private final RealmResults<Vital> bpResults;
    private final RealmResults<Vital> glucoseVitalsResults;
    private final SummaryWidgetBinding summaryWidgetBinding;
    private User user;
    private final RealmResults<Vital> weightVitalsResults;
    private HashMap<Integer, TimelineItem> widgetsEnabled;

    public SummaryViewHolder(SummaryWidgetBinding summaryWidgetBinding) {
        super(summaryWidgetBinding);
        this.summaryWidgetBinding = summaryWidgetBinding;
        this.bpResults = VitalStore.getInstance(summaryWidgetBinding.getRoot().getContext()).getVitals("BLOOD_PRESSURE");
        this.glucoseVitalsResults = VitalStore.getInstance(summaryWidgetBinding.getRoot().getContext()).getVitals("BLOOD_GLUCOSE");
        this.weightVitalsResults = VitalStore.getInstance(summaryWidgetBinding.getRoot().getContext()).getVitals("WEIGHT");
    }

    public void bind(User user) {
        this.user = user;
        updateView();
    }

    private void updateView() {
        SummaryViewModel summaryViewModel = new SummaryViewModel(this.user);
        if (this.bpResults == null || this.bpResults.size() <= 0) {
            summaryViewModel.setBpText("NA");
        } else {
            Vital bpVital = (Vital) this.bpResults.get(0);
            String systolic = String.valueOf(bpVital.getParameterMap().get(CooeySQLHelper.COL_SYS));
            String diastolic = String.valueOf(bpVital.getParameterMap().get(CooeySQLHelper.COL_DIA));
            systolic = systolic.replaceAll("\\.0*$", "");
            summaryViewModel.setBpText(systolic + "/" + diastolic.replaceAll("\\.0*$", ""));
        }
        if (this.glucoseVitalsResults == null || this.glucoseVitalsResults.size() <= 0) {
            summaryViewModel.setBloodGlucoseText("NA");
        } else {
            summaryViewModel.setBloodGlucoseText(((String) ((Vital) this.glucoseVitalsResults.get(0)).getParameterMap().get("glucose")).replaceAll("\\.0*$", ""));
        }
        if (this.weightVitalsResults == null || this.weightVitalsResults.size() <= 0) {
            summaryViewModel.setWeight("NA");
        } else {
            summaryViewModel.setWeight(String.valueOf(((Vital) this.weightVitalsResults.get(0)).getParameterMap().get("weight")).replaceAll("\\.0*$", ""));
        }
        this.summaryWidgetBinding.setSummaryViewModel(summaryViewModel);
        this.summaryWidgetBinding.executePendingBindings();
    }
}
