package com.cooey.common.vitals;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.cooey.common.C0842R;
import com.cooey.common.databinding.LayoutVitalIndividualViewBinding;
import com.cooey.common.databinding.LayoutVitalViewBinding;
import com.cooey.common.vo.Vital;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.Map;

public class VitalViewHolder extends ViewHolder {
    LayoutVitalViewBinding layoutVitalViewBinding;

    class C09011 extends TypeToken<Map<String, String>> {
        C09011() {
        }
    }

    public VitalViewHolder(View itemView) {
        super(itemView);
    }

    public void bind(Vital vital) {
        this.layoutVitalViewBinding = (LayoutVitalViewBinding) DataBindingUtil.bind(this.itemView);
        this.layoutVitalViewBinding.dateText.setText("20th March, 2017");
        Map<String, String> vitalMap = (Map) new GsonBuilder().create().fromJson((String) vital.getParameterMap().get("vitals"), new C09011().getType());
        for (String key : vitalMap.keySet()) {
            String value = (String) vitalMap.get(key);
            View view = LayoutInflater.from(this.itemView.getContext()).inflate(C0842R.layout.layout_vital_individual_view, (LinearLayout) this.itemView, false);
            LayoutVitalIndividualViewBinding layoutVitalIndividualViewBinding = (LayoutVitalIndividualViewBinding) DataBindingUtil.bind(view);
            layoutVitalIndividualViewBinding.valueText.setText(value);
            layoutVitalIndividualViewBinding.vitalText.setText(key);
            ((LinearLayout) this.itemView).addView(view);
        }
    }
}
