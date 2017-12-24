package com.thoughtbot.expandablerecyclerview.listeners;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

public interface GroupExpandCollapseListener {
    void onGroupCollapsed(ExpandableGroup expandableGroup);

    void onGroupExpanded(ExpandableGroup expandableGroup);
}
