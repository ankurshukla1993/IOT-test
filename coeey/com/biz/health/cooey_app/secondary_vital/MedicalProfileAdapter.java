package com.biz.health.cooey_app.secondary_vital;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.biz.health.cooey_app.C0644R;
import com.cooey.common.vo.Vital;
import java.util.List;

public class MedicalProfileAdapter extends Adapter<WidgetsViewHolder> {
    private Context context;
    private final LayoutInflater layoutInflater;
    private List<Vital> vitalList;
    private List<String> widgetSettings;

    public MedicalProfileAdapter(List<Vital> vitalList, Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.vitalList = vitalList;
    }

    public WidgetsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        String dashboardItem = null;
        if (this.widgetSettings != null && this.widgetSettings.size() > 0) {
            dashboardItem = (String) this.widgetSettings.get(viewType);
        }
        if (dashboardItem == null) {
            return new WidgetsEmpltyViewHolder(this.context, this.layoutInflater.inflate(C0644R.layout.empty_widgets, parent, false));
        }
        dashboardItem.hashCode();
        return new SecondaryVitalViewHolder(this.context, this.layoutInflater.inflate(C0644R.layout.widget_secondary_vital, parent, false), this.vitalList, dashboardItem);
    }

    public void onBindViewHolder(WidgetsViewHolder holder, int position) {
        holder.updateView(position);
    }

    public int getItemCount() {
        if (this.widgetSettings.size() > 0) {
            return this.widgetSettings.size();
        }
        return 0;
    }

    public int getItemViewType(int position) {
        return position;
    }
}
