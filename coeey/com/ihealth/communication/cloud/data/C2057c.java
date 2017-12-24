package com.ihealth.communication.cloud.data;

import com.ihealth.communication.cloud.UserCheckSDK;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimerTask;

class C2057c extends TimerTask {
    final /* synthetic */ BP_Up f985a;

    C2057c(BP_Up bP_Up) {
        this.f985a = bP_Up;
    }

    public void run() {
        Object obj = null;
        if (UserCheckSDK.isNetworkAvailable(this.f985a.context)) {
            ArrayList a = this.f985a.m425a();
            if (a != null && a != null && a.size() > 0) {
                try {
                    if ("100".equals(new BP_CommCloud(this.f985a.context).bp_upload(this.f985a.f607a, this.f985a.f608b, a, this.f985a.f610d).getResultMessage())) {
                        obj = 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (obj != null) {
                    DataBaseTools dataBaseTools = new DataBaseTools(this.f985a.context);
                    Iterator it = a.iterator();
                    while (it.hasNext()) {
                        dataBaseTools.deleteData(DataBaseConstants.TABLE_TB_BPMEASURERESULT, "bpun = '" + this.f985a.f607a + "' and " + DataBaseConstants.BPMEASURERESULT_PHONEDATAID + " = '" + ((Data_BP_Result) it.next()).getPhoneDataID() + "'").booleanValue();
                    }
                }
                new DataBaseTools(this.f985a.context).selectData(DataBaseConstants.TABLE_TB_BPMEASURERESULT, null, "bpun='" + this.f985a.f607a + "'");
            }
        }
    }
}
