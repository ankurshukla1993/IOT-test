package com.thoughtbot.expandablerecyclerview;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;
import com.thoughtbot.expandablerecyclerview.listeners.ExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.listeners.GroupExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.listeners.OnGroupClickListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableList;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;
import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;
import java.util.List;

public abstract class ExpandableRecyclerViewAdapter<GVH extends GroupViewHolder, CVH extends ChildViewHolder> extends Adapter implements ExpandCollapseListener, OnGroupClickListener {
    private static final String EXPAND_STATE_MAP = "expandable_recyclerview_adapter_expand_state_map";
    private ExpandCollapseController expandCollapseController = new ExpandCollapseController(this.expandableList, this);
    private GroupExpandCollapseListener expandCollapseListener;
    protected ExpandableList expandableList;
    private OnGroupClickListener groupClickListener;

    public abstract void onBindChildViewHolder(CVH cvh, int i, ExpandableGroup expandableGroup, int i2);

    public abstract void onBindGroupViewHolder(GVH gvh, int i, ExpandableGroup expandableGroup);

    public abstract CVH onCreateChildViewHolder(ViewGroup viewGroup, int i);

    public abstract GVH onCreateGroupViewHolder(ViewGroup viewGroup, int i);

    public ExpandableRecyclerViewAdapter(List<? extends ExpandableGroup> groups) {
        this.expandableList = new ExpandableList(groups);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return onCreateChildViewHolder(parent, viewType);
            case 2:
                GVH gvh = onCreateGroupViewHolder(parent, viewType);
                gvh.setOnGroupClickListener(this);
                return gvh;
            default:
                throw new IllegalArgumentException("viewType is not valid");
        }
    }

    public void onBindViewHolder(ViewHolder holder, int position) {
        ExpandableListPosition listPos = this.expandableList.getUnflattenedPosition(position);
        ExpandableGroup group = this.expandableList.getExpandableGroup(listPos);
        switch (listPos.type) {
            case 1:
                onBindChildViewHolder((ChildViewHolder) holder, position, group, listPos.childPos);
                return;
            case 2:
                onBindGroupViewHolder((GroupViewHolder) holder, position, group);
                return;
            default:
                return;
        }
    }

    public int getItemCount() {
        return this.expandableList.getVisibleItemCount();
    }

    public int getItemViewType(int position) {
        return this.expandableList.getUnflattenedPosition(position).type;
    }

    public void onGroupExpanded(int positionStart, int itemCount) {
        if (itemCount > 0) {
            notifyItemRangeInserted(positionStart, itemCount);
            if (this.expandCollapseListener != null) {
                this.expandCollapseListener.onGroupExpanded((ExpandableGroup) getGroups().get(this.expandableList.getUnflattenedPosition(positionStart).groupPos));
            }
        }
    }

    public void onGroupCollapsed(int positionStart, int itemCount) {
        if (itemCount > 0) {
            notifyItemRangeRemoved(positionStart, itemCount);
            if (this.expandCollapseListener != null) {
                this.expandCollapseListener.onGroupCollapsed((ExpandableGroup) getGroups().get(this.expandableList.getUnflattenedPosition(positionStart - 1).groupPos));
            }
        }
    }

    public boolean onGroupClick(int flatPos) {
        if (this.groupClickListener != null) {
            this.groupClickListener.onGroupClick(flatPos);
        }
        return this.expandCollapseController.toggleGroup(flatPos);
    }

    public boolean toggleGroup(int flatPos) {
        return this.expandCollapseController.toggleGroup(flatPos);
    }

    public boolean toggleGroup(ExpandableGroup group) {
        return this.expandCollapseController.toggleGroup(group);
    }

    public boolean isGroupExpanded(int flatPos) {
        return this.expandCollapseController.isGroupExpanded(flatPos);
    }

    public boolean isGroupExpanded(ExpandableGroup group) {
        return this.expandCollapseController.isGroupExpanded(group);
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBooleanArray(EXPAND_STATE_MAP, this.expandableList.expandedGroupIndexes);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(EXPAND_STATE_MAP)) {
            this.expandableList.expandedGroupIndexes = savedInstanceState.getBooleanArray(EXPAND_STATE_MAP);
            notifyDataSetChanged();
        }
    }

    public void setOnGroupClickListener(OnGroupClickListener listener) {
        this.groupClickListener = listener;
    }

    public void setOnGroupExpandCollapseListener(GroupExpandCollapseListener listener) {
        this.expandCollapseListener = listener;
    }

    public List<? extends ExpandableGroup> getGroups() {
        return this.expandableList.groups;
    }
}
