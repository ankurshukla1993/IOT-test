package com.cooey.android.users.old.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cooey.android.users.C0757R;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.android.users.old.viewholders.AboutPatientViewHolder;
import com.cooey.android.users.old.viewholders.AssessmentViewHolder;
import com.cooey.android.users.old.viewholders.CareplanEvaluationViewHolder;
import com.cooey.android.users.old.viewholders.CareplanGoalViewHolder;
import com.cooey.android.users.old.viewholders.DiagnosisViewHolder;
import com.cooey.android.users.old.viewholders.HistoryViewHolder;
import com.cooey.android.users.old.viewholders.InterventionHeaderViewHolder;
import com.cooey.android.users.old.viewholders.MedicineTypeViewHolder;
import com.cooey.common.vo.User;
import com.cooey.common.vo.careplan.Careplan;
import com.thoughtbot.expandablerecyclerview.MultiTypeExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import java.util.List;

public class MultiTypeAboutAdapter extends MultiTypeExpandableRecyclerViewAdapter<InterventionHeaderViewHolder, ChildViewHolder> {
    private static final int ABOUT_TYPE = 4;
    private static final int ASSESSMENT_TYPE = 6;
    private static final int CAREPLAN_GOAL_TYPE = 8;
    private static final int DIAGNOSIS_TYPE = 7;
    private static final int EVALUATION_TYPE = 9;
    private static final int HISTORY_TYPE = 3;
    private static final int MEDICINES = 10;
    private Context context;
    private String serverUrl;
    private String sessionId;
    private User user;

    public MultiTypeAboutAdapter(Context context, List<? extends ExpandableGroup> groups, User user, String sessionId, String serverUrl) {
        super(groups);
        this.context = context;
        this.user = user;
        this.sessionId = sessionId;
        this.serverUrl = serverUrl;
    }

    public InterventionHeaderViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        return new InterventionHeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0757R.layout.list_item_intervention_header_user, parent, false));
    }

    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 3:
                return new HistoryViewHolder(this.context, LayoutInflater.from(parent.getContext()).inflate(C0757R.layout.item_history_type_user, parent, false));
            case 4:
                return new AboutPatientViewHolder(this.context, LayoutInflater.from(parent.getContext()).inflate(C0757R.layout.item_about_todo_user, parent, false), this.user, this.sessionId, this.serverUrl);
            case 6:
                return new AssessmentViewHolder(this.context, LayoutInflater.from(parent.getContext()).inflate(C0757R.layout.item_assessment_type_user, parent, false));
            case 7:
                return new DiagnosisViewHolder(this.context, LayoutInflater.from(parent.getContext()).inflate(C0757R.layout.item_diagnosis_type_user, parent, false));
            case 8:
                return new CareplanGoalViewHolder(this.context, LayoutInflater.from(parent.getContext()).inflate(C0757R.layout.item_careplan_goal_type_user, parent, false));
            case 9:
                return new CareplanEvaluationViewHolder(this.context, LayoutInflater.from(parent.getContext()).inflate(C0757R.layout.item_evaluation_type_user, parent, false));
            case 10:
                return new MedicineTypeViewHolder(this.context, LayoutInflater.from(parent.getContext()).inflate(C0757R.layout.item_medicine_type_user, parent, false), this.sessionId, this.user.getId(), this.serverUrl);
            default:
                throw new IllegalArgumentException("Invalid viewType");
        }
    }

    public void onBindChildViewHolder(ChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        switch (getItemViewType(flatPosition)) {
            case 3:
                if (group.getItems().get(childIndex) instanceof Careplan) {
                    ((HistoryViewHolder) holder).setHistoryAndExamination((Careplan) group.getItems().get(childIndex));
                    return;
                }
                ((HistoryViewHolder) holder).setHistoryAndExamination(null);
                return;
            case 4:
                ((AboutPatientViewHolder) holder).setPatientDetails(this.user.getId());
                return;
            case 6:
                if (group.getItems().get(childIndex) instanceof Careplan) {
                    ((AssessmentViewHolder) holder).setAssessmentViewHolder((Careplan) group.getItems().get(childIndex));
                    return;
                }
                ((AssessmentViewHolder) holder).setAssessmentViewHolder(null);
                return;
            case 7:
                if (group.getItems().get(childIndex) instanceof Careplan) {
                    ((DiagnosisViewHolder) holder).setDiagnosisViewHolder((Careplan) group.getItems().get(childIndex));
                    return;
                }
                ((DiagnosisViewHolder) holder).setDiagnosisViewHolder(null);
                return;
            case 8:
                if (group.getItems().get(childIndex) instanceof Careplan) {
                    ((CareplanGoalViewHolder) holder).setCareplanGoal((Careplan) group.getItems().get(childIndex));
                    return;
                }
                ((CareplanGoalViewHolder) holder).setCareplanGoal(null);
                return;
            case 9:
                if (group.getItems().get(childIndex) instanceof Careplan) {
                    ((CareplanEvaluationViewHolder) holder).setEvaluation((Careplan) group.getItems().get(childIndex));
                    return;
                }
                ((CareplanEvaluationViewHolder) holder).setEvaluation(null);
                return;
            case 10:
                ((MedicineTypeViewHolder) holder).setMedicineViewHolder();
                return;
            default:
                throw new IllegalArgumentException("Invalid viewType");
        }
    }

    public int getChildViewType(int position, ExpandableGroup group, int childIndex) {
        String title = group.getTitle();
        int i = -1;
        switch (title.hashCode()) {
            case -1442978980:
                if (title.equals(CTConstants.EVALUATION)) {
                    i = 5;
                    break;
                }
                break;
            case -1094006063:
                if (title.equals(CTConstants.DIAGNOSIS)) {
                    i = 3;
                    break;
                }
                break;
            case -182119710:
                if (title.equals(CTConstants.ASSESSMENT)) {
                    i = 2;
                    break;
                }
                break;
            case -148074151:
                if (title.equals(CTConstants.MEDICINES)) {
                    i = 6;
                    break;
                }
                break;
            case 21787691:
                if (title.equals(CTConstants.HISTORY)) {
                    i = 1;
                    break;
                }
                break;
            case 63058797:
                if (title.equals(CTConstants.ABOUT)) {
                    i = 0;
                    break;
                }
                break;
            case 2133950905:
                if (title.equals(CTConstants.CAREPLAN_GOAL)) {
                    i = 4;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                return 4;
            case 1:
                return 3;
            case 2:
                return 6;
            case 3:
                return 7;
            case 4:
                return 8;
            case 5:
                return 9;
            case 6:
                return 10;
            default:
                return 0;
        }
    }

    public void onBindGroupViewHolder(InterventionHeaderViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setInterventionHeaderTitle(group);
    }

    public boolean isGroup(int viewType) {
        return viewType == 2;
    }

    public boolean isChild(int viewType) {
        return viewType == 4 || viewType == 3 || viewType == 6 || viewType == 8 || viewType == 7 || viewType == 9 || viewType == 10;
    }
}
