package com.thoughtbot.expandablerecyclerview;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;
import java.util.List;

public abstract class MultiTypeExpandableRecyclerViewAdapter<GVH extends GroupViewHolder, CVH extends ChildViewHolder> extends ExpandableRecyclerViewAdapter<GVH, CVH> {
    public MultiTypeExpandableRecyclerViewAdapter(List<? extends ExpandableGroup> groups) {
        super(groups);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (isGroup(viewType)) {
            GVH gvh = onCreateGroupViewHolder(parent, viewType);
            gvh.setOnGroupClickListener(this);
            return gvh;
        } else if (isChild(viewType)) {
            return onCreateChildViewHolder(parent, viewType);
        } else {
            throw new IllegalArgumentException("viewType is not valid");
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        ExpandableListPosition listPos = this.expandableList.getUnflattenedPosition(position);
        ExpandableGroup group = this.expandableList.getExpandableGroup(listPos);
        if (isGroup(getItemViewType(position))) {
            onBindGroupViewHolder((GroupViewHolder) holder, position, group);
        } else if (isChild(getItemViewType(position))) {
            onBindChildViewHolder((ChildViewHolder) holder, position, group, listPos.childPos);
        }
    }

    public int getItemViewType(int position) {
        ExpandableListPosition listPosition = this.expandableList.getUnflattenedPosition(position);
        ExpandableGroup group = this.expandableList.getExpandableGroup(listPosition);
        int viewType = listPosition.type;
        switch (viewType) {
            case 1:
                return getChildViewType(position, group, listPosition.childPos);
            case 2:
                return getGroupViewType(position, group);
            default:
                return viewType;
        }
    }

    public int getChildViewType(int position, ExpandableGroup group, int childIndex) {
        return super.getItemViewType(position);
    }

    public int getGroupViewType(int position, ExpandableGroup group) {
        return super.getItemViewType(position);
    }

    public boolean isGroup(int viewType) {
        return viewType == 2;
    }

    public boolean isChild(int viewType) {
        return viewType == 1;
    }
}
