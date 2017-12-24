package com.thoughtbot.expandablerecyclerview.models;

import java.util.List;

public class ExpandableList {
    public boolean[] expandedGroupIndexes;
    public List<? extends ExpandableGroup> groups;

    public ExpandableList(List<? extends ExpandableGroup> groups) {
        this.groups = groups;
        this.expandedGroupIndexes = new boolean[groups.size()];
        for (int i = 0; i < groups.size(); i++) {
            this.expandedGroupIndexes[i] = false;
        }
    }

    private int numberOfVisibleItemsInGroup(int group) {
        if (this.expandedGroupIndexes[group]) {
            return ((ExpandableGroup) this.groups.get(group)).getItemCount() + 1;
        }
        return 1;
    }

    public int getVisibleItemCount() {
        int count = 0;
        for (int i = 0; i < this.groups.size(); i++) {
            count += numberOfVisibleItemsInGroup(i);
        }
        return count;
    }

    public ExpandableListPosition getUnflattenedPosition(int flPos) {
        int adapted = flPos;
        for (int i = 0; i < this.groups.size(); i++) {
            int groupItemCount = numberOfVisibleItemsInGroup(i);
            if (adapted == 0) {
                return ExpandableListPosition.obtain(2, i, -1, flPos);
            }
            if (adapted < groupItemCount) {
                return ExpandableListPosition.obtain(1, i, adapted - 1, flPos);
            }
            adapted -= groupItemCount;
        }
        throw new RuntimeException("Unknown state");
    }

    public int getFlattenedGroupIndex(ExpandableListPosition listPosition) {
        int runningTotal = 0;
        for (int i = 0; i < listPosition.groupPos; i++) {
            runningTotal += numberOfVisibleItemsInGroup(i);
        }
        return runningTotal;
    }

    public int getFlattenedGroupIndex(int groupIndex) {
        int runningTotal = 0;
        for (int i = 0; i < groupIndex; i++) {
            runningTotal += numberOfVisibleItemsInGroup(i);
        }
        return runningTotal;
    }

    public int getFlattenedGroupIndex(ExpandableGroup group) {
        int runningTotal = 0;
        for (int i = 0; i < this.groups.indexOf(group); i++) {
            runningTotal += numberOfVisibleItemsInGroup(i);
        }
        return runningTotal;
    }

    public int getFlattenedChildIndex(long packedPosition) {
        return getFlattenedChildIndex(ExpandableListPosition.obtainPosition(packedPosition));
    }

    public int getFlattenedChildIndex(ExpandableListPosition listPosition) {
        int groupIndex = listPosition.groupPos;
        int childIndex = listPosition.childPos;
        int runningTotal = 0;
        for (int i = 0; i < groupIndex; i++) {
            runningTotal += numberOfVisibleItemsInGroup(i);
        }
        return (runningTotal + childIndex) + 1;
    }

    public int getFlattenedChildIndex(int groupIndex, int childIndex) {
        int runningTotal = 0;
        for (int i = 0; i < groupIndex; i++) {
            runningTotal += numberOfVisibleItemsInGroup(i);
        }
        return (runningTotal + childIndex) + 1;
    }

    public int getFlattenedFirstChildIndex(int groupIndex) {
        return getFlattenedGroupIndex(groupIndex) + 1;
    }

    public int getFlattenedFirstChildIndex(ExpandableListPosition listPosition) {
        return getFlattenedGroupIndex(listPosition) + 1;
    }

    public int getExpandableGroupItemCount(ExpandableListPosition listPosition) {
        return ((ExpandableGroup) this.groups.get(listPosition.groupPos)).getItemCount();
    }

    public ExpandableGroup getExpandableGroup(ExpandableListPosition listPosition) {
        return (ExpandableGroup) this.groups.get(listPosition.groupPos);
    }
}
