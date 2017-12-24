package com.biz.health.cooey_app.dashboard.widgets.holders;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.biz.health.cooey_app.careplan.actions.ActionItemsActivity;
import com.cooey.android.users.old.utils.CTConstants;
import com.cooey.common.stores.PreferenceStore;
import com.cooey.common.vo.Session;
import com.cooey.common.vo.SettingsConfig;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 7})
/* compiled from: CareplanWidgetViewHolder.kt */
final class CareplanWidgetViewHolder$bind$2 implements OnClickListener {
    final /* synthetic */ CareplanWidgetViewHolder this$0;

    CareplanWidgetViewHolder$bind$2(CareplanWidgetViewHolder careplanWidgetViewHolder) {
        this.this$0 = careplanWidgetViewHolder;
    }

    public final void onClick(View it) {
        Intent intent = new Intent(this.this$0.context, ActionItemsActivity.class);
        Session session = new PreferenceStore().getSession(this.this$0.context);
        intent.putExtra(CTConstants.PATIENT_ID, new PreferenceStore().getUser(this.this$0.context).getId());
        intent.putExtra(CTConstants.SESSION_ID, session.getId());
        intent.putExtra("serverurl", "http://api.cooey.co.in/ehealth/");
        List list = (List) null;
        list = new ArrayList();
        SettingsConfig settingsConfig = new PreferenceStore().getPartnerConfig(this.this$0.context);
        Gson gson = new Gson();
        String partnerConfigRes = gson.toJson(settingsConfig);
        String vitalTempList = gson.toJson(list);
        intent.putExtra("settingsConfig", partnerConfigRes);
        intent.putExtra(CTConstants.APP_TYPE, CTConstants.PATIENT_APP_TYPE);
        intent.putExtra("vitaltemplist", vitalTempList);
        intent.putExtra("setPartnerConfig", true);
        this.this$0.context.startActivity(intent);
    }
}
