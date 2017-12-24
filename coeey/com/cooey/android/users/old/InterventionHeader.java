package com.cooey.android.users.old;

import com.cooey.common.vo.careplan.Intervention;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;

public class InterventionHeader extends ExpandableGroup<Intervention> {
    private String interventionType;

    public InterventionHeader(String title, List<Intervention> items) {
        super(title, items);
        this.interventionType = title;
    }

    public String getInterventionType() {
        return this.interventionType;
    }
}
