package com.cooey.common.vo;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;

public class InterventionBlueprintHeader extends ExpandableGroup<InterventionBlueprint> {
    private String interventionType;

    public InterventionBlueprintHeader(String title, List<InterventionBlueprint> items) {
        super(title, items);
        this.interventionType = title;
    }

    public String getInterventionType() {
        return this.interventionType;
    }
}
