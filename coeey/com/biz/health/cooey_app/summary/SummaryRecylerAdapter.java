package com.biz.health.cooey_app.summary;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.biz.health.cooey_app.C0644R;
import com.biz.health.cooey_app.databinding.ItemLayoutNotesBinding;
import com.biz.health.cooey_app.databinding.ItemLayoutSummaryInfoBinding;
import com.biz.health.cooey_app.databinding.ItemLayoutVitalsBinding;
import com.biz.health.cooey_app.databinding.ItemSummaryMedicinesBinding;
import com.biz.health.cooey_app.summary.info.SummaryInfoViewHolder;
import com.biz.health.cooey_app.summary.medicines.MedicinesInfoViewHolder;
import com.biz.health.cooey_app.summary.notes.SummaryPatientNotesViewHolder;
import com.biz.health.cooey_app.summary.vitals.SummaryVitalsViewHolder;
import com.cooey.common.vo.User;

public class SummaryRecylerAdapter extends Adapter<SummaryRecylerViewHolder> {
    private Context context;
    private final LayoutInflater layoutInflater;
    private User user;

    public SummaryRecylerAdapter(Context context, LayoutInflater layoutInflater, User user) {
        this.context = context;
        this.layoutInflater = layoutInflater;
        this.user = user;
    }

    public SummaryRecylerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new SummaryInfoViewHolder((ItemLayoutSummaryInfoBinding) DataBindingUtil.inflate(this.layoutInflater, C0644R.layout.item_layout_summary_info, parent, false));
            case 1:
                return new SummaryVitalsViewHolder((ItemLayoutVitalsBinding) DataBindingUtil.inflate(this.layoutInflater, C0644R.layout.item_layout_vitals, parent, false));
            case 2:
                return new MedicinesInfoViewHolder((ItemSummaryMedicinesBinding) DataBindingUtil.inflate(this.layoutInflater, C0644R.layout.item_summary_medicines, parent, false));
            case 3:
                return new SummaryPatientNotesViewHolder((ItemLayoutNotesBinding) DataBindingUtil.inflate(this.layoutInflater, C0644R.layout.item_layout_notes, parent, false));
            default:
                return new SummaryInfoViewHolder((ItemLayoutSummaryInfoBinding) DataBindingUtil.inflate(this.layoutInflater, C0644R.layout.item_layout_summary_info, parent, false));
        }
    }

    public void onBindViewHolder(SummaryRecylerViewHolder holder, int position) {
    }

    public int getItemViewType(int position) {
        return position;
    }

    public int getItemCount() {
        return 4;
    }
}
