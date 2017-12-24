package com.biz.health.cooey_app.summary.medicines;

import android.databinding.ViewDataBinding;
import com.biz.health.cooey_app.databinding.ItemSummaryMedicinesBinding;
import com.biz.health.cooey_app.summary.SummaryRecylerViewHolder;
import com.cooey.common.vo.User;

public class MedicinesInfoViewHolder extends SummaryRecylerViewHolder {
    private ItemSummaryMedicinesBinding medicinesBinding;

    public MedicinesInfoViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding);
        this.medicinesBinding = (ItemSummaryMedicinesBinding) viewDataBinding;
    }

    public void bind(User user) {
    }
}
