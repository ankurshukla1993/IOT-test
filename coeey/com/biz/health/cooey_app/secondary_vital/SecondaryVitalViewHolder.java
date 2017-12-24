package com.biz.health.cooey_app.secondary_vital;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cooey.common.config.FieldConfig;
import com.cooey.common.config.VitalTemplatesConfigListItem;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Vital;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondaryVitalViewHolder extends WidgetsViewHolder {
    private Context context;
    private Map<String, List<FieldConfig>> fieldConfigMap = new HashMap();
    @BindView(2131362755)
    TextView txtViewSecondaryVital;
    private String vitalName;
    private List<Vital> vitalsList;

    public SecondaryVitalViewHolder(Context context, View itemView, List<Vital> vitalList, String vitalName) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
        this.vitalsList = vitalList;
        this.vitalName = vitalName;
        List<VitalTemplatesConfigListItem> vitalTemplatesConfigListItems = new PreferenceStore().getVitalConfigResponse(context);
        if (vitalTemplatesConfigListItems != null && vitalTemplatesConfigListItems.size() > 0) {
            for (VitalTemplatesConfigListItem vitalTemplatesConfigListItem : vitalTemplatesConfigListItems) {
                this.fieldConfigMap.put(vitalTemplatesConfigListItem.getType(), vitalTemplatesConfigListItem.getFieldConfigList());
            }
        }
    }

    public void updateView(int position) {
        setDynamicVitalList(this.vitalsList);
    }

    private void setDynamicVitalList(List<Vital> list) {
        if (this.vitalName != null && !this.vitalName.isEmpty()) {
            this.txtViewSecondaryVital.setText(this.vitalName);
        }
    }

    @OnClick({2131362421})
    public void addDynamicVital() {
        Intent intent = new Intent(this.context, AddDynamicVitalActivity.class);
        intent.putExtra("SECONDARY_VITAL", this.vitalName);
        this.context.startActivity(intent);
    }
}
