package com.cooey.android.users.old;

import com.cooey.common.vo.careplan.Careplan;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import java.util.List;

public class PatientCareplanHeader extends ExpandableGroup<Careplan> {
    public PatientCareplanHeader(String title, List<Careplan> items) {
        super(title, items);
    }
}
