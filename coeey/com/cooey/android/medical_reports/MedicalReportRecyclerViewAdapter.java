package com.cooey.android.medical_reports;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.cooey.android.medical_reports.databinding.LayoutMedicalReportItemViewBinding;
import java.util.List;

public class MedicalReportRecyclerViewAdapter extends Adapter<MedicalReportViewHolder> {
    private final Context context;
    private List<MedicalReport> medicalReports;

    public MedicalReportRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public MedicalReportViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MedicalReportViewHolder((LayoutMedicalReportItemViewBinding) DataBindingUtil.inflate(LayoutInflater.from(this.context), C0756R.layout.layout_medical_report_item_view, parent, false));
    }

    public void onBindViewHolder(MedicalReportViewHolder holder, int position) {
        holder.bind((MedicalReport) this.medicalReports.get(position));
    }

    public int getItemCount() {
        if (this.medicalReports != null) {
            return this.medicalReports.size();
        }
        return 0;
    }

    public void setMedicalReports(List<MedicalReport> medicalReportList) {
        this.medicalReports = medicalReportList;
        notifyDataSetChanged();
    }
}
