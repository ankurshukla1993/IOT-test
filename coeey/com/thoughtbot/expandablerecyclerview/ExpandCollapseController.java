package com.thoughtbot.expandablerecyclerview;

import com.thoughtbot.expandablerecyclerview.listeners.ExpandCollapseListener;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.models.ExpandableList;
import com.thoughtbot.expandablerecyclerview.models.ExpandableListPosition;

public class ExpandCollapseController {
    private ExpandableList expandableList;
    private ExpandCollapseListener listener;

    public ExpandCollapseController(ExpandableList expandableList, ExpandCollapseListener listener) {
        this.expandableList = expandableList;
        this.listener = listener;
    }

    private void collapseGroup(ExpandableListPosition listPosition) {
        this.expandableList.expandedGroupIndexes[listPosition.groupPos] = false;
        if (this.listener != null) {
            this.listener.onGroupCollapsed(this.expandableList.getFlattenedGroupIndex(listPosition) + 1, ((ExpandableGroup) this.expandableList.groups.get(listPosition.groupPos)).getItemCount());
        }
    }

    private void expandGroup(ExpandableListPosition listPosition) {
        this.expandableList.expandedGroupIndexes[listPosition.groupPos] = true;
        if (this.listener != null) {
            this.listener.onGroupExpanded(this.expandableList.getFlattenedGroupIndex(listPosition) + 1, ((ExpandableGroup) this.expandableList.groups.get(listPosition.groupPos)).getItemCount());
        }
    }

    public boolean isGroupExpanded(ExpandableGroup group) {
        return this.expandableList.expandedGroupIndexes[this.expandableList.groups.indexOf(group)];
    }

    public boolean isGroupExpanded(int flatPos) {
        return this.expandableList.expandedGroupIndexes[this.expandableList.getUnflattenedPosition(flatPos).groupPos];
    }

    public boolean toggleGroup(int flatPos) {
        ExpandableListPosition listPos = this.expandableList.getUnflattenedPosition(flatPos);
        boolean expanded = this.expandableList.expandedGroupIndexes[listPos.groupPos];
        if (expanded) {
            collapseGroup(listPos);
        } else {
            expandGroup(listPos);
        }
        return expanded;
    }

    public boolean toggleGroup(ExpandableGroup group) {
        ExpandableListPosition listPos = this.expandableList.getUnflattenedPosition(this.expandableList.getFlattenedGroupIndex(group));
        boolean expanded = isGroupExpanded(listPos.groupPos);
        if (expanded) {
            collapseGroup(listPos);
        } else {
            expandGroup(listPos);
        }
        return expanded;
    }
}
