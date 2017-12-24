package com.cooey.android.users.old.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cooey.android.users.C0757R;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.android.users.old.viewholders.ActivityViewHolder;
import com.cooey.android.users.old.viewholders.DietViewHolder;
import com.cooey.android.users.old.viewholders.InterventionHeaderViewHolder;
import com.cooey.android.users.old.viewholders.TodoTypeViewHolder;
import com.cooey.android.users.old.viewholders.VitalViewHolder;
import com.cooey.common.vo.User;
import com.cooey.common.vo.careplan.Intervention;
import com.thoughtbot.expandablerecyclerview.MultiTypeExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import java.util.List;

public class MultiTypeInterventionAdapter extends MultiTypeExpandableRecyclerViewAdapter<InterventionHeaderViewHolder, ChildViewHolder> {
    private static final int ACITIVITY_TYPE = 7;
    private static final int DIET_TYPE = 8;
    private static final int TODO_TYPE = 5;
    private static final int VITAL_TYPE = 6;
    private Context context;
    private String sessionId;
    private User user;

    public MultiTypeInterventionAdapter(Context context, List<? extends ExpandableGroup> groups, User user, String sessionId) {
        super(groups);
        this.context = context;
        this.user = user;
        this.sessionId = sessionId;
    }

    public InterventionHeaderViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        return new InterventionHeaderViewHolder(LayoutInflater.from(parent.getContext()).inflate(C0757R.layout.list_item_intervention_header_user, parent, false));
    }

    public ChildViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 5:
                return new TodoTypeViewHolder(this.context, LayoutInflater.from(parent.getContext()).inflate(C0757R.layout.item_todo_type_user, parent, false));
            case 6:
                return new VitalViewHolder(this.context, LayoutInflater.from(parent.getContext()).inflate(C0757R.layout.item_vital_type_user, parent, false));
            case 7:
                return new ActivityViewHolder(this.context, LayoutInflater.from(parent.getContext()).inflate(C0757R.layout.item_activity_type_user, parent, false));
            case 8:
                return new DietViewHolder(this.context, LayoutInflater.from(parent.getContext()).inflate(C0757R.layout.item_diet_type_user, parent, false));
            default:
                throw new IllegalArgumentException("Invalid viewType");
        }
    }

    public void onBindChildViewHolder(ChildViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        Intervention intervention = (Intervention) group.getItems().get(childIndex);
        switch (getItemViewType(flatPosition)) {
            case 5:
                ((TodoTypeViewHolder) holder).setTodoViewHolder(intervention);
                return;
            case 6:
                ((VitalViewHolder) holder).setVitalViewHolder(intervention);
                return;
            case 7:
                ((ActivityViewHolder) holder).setActivityViewHolder(intervention);
                return;
            case 8:
                ((DietViewHolder) holder).setDietViewHolder(intervention);
                return;
            default:
                return;
        }
    }

    public void onBindGroupViewHolder(InterventionHeaderViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setInterventionHeaderTitle(group);
    }

    public int getChildViewType(int position, ExpandableGroup group, int childIndex) {
        String title = group.getTitle();
        int i = -1;
        switch (title.hashCode()) {
            case -873340145:
                if (title.equals(CTConstants.ACTIVITY_TYPE)) {
                    i = 1;
                    break;
                }
                break;
            case 2098164:
                if (title.equals(CTConstants.DIET_TYPE)) {
                    i = 2;
                    break;
                }
                break;
            case 2580550:
                if (title.equals(CTConstants.TODO_TYPE)) {
                    i = 0;
                    break;
                }
                break;
            case 81680364:
                if (title.equals(CTConstants.VITAL_TYPE)) {
                    i = 3;
                    break;
                }
                break;
        }
        switch (i) {
            case 0:
                return 5;
            case 1:
                return 7;
            case 2:
                return 8;
            case 3:
                return 6;
            default:
                return 0;
        }
    }

    public boolean isGroup(int viewType) {
        return viewType == 2;
    }

    public boolean isChild(int viewType) {
        return viewType == 5 || viewType == 6 || viewType == 7 || viewType == 8;
    }
}
