package com.cooey.android.users.old;

import com.cooey.common.vo.careplan.Intervention;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;

public class PatientInterventionHeader extends ExpandableGroup<Intervention> {
    public PatientInterventionHeader(String title, List<Intervention> items) {
        super(title, items);
    }
}
