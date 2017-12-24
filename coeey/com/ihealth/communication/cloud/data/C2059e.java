package com.ihealth.communication.cloud.data;

import com.ihealth.communication.cloud.UserCheckSDK;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimerTask;

class C2059e extends TimerTask {
    final /* synthetic */ HS_Up f986a;

    C2059e(HS_Up hS_Up) {
        this.f986a = hS_Up;
    }

    public void run() {
        Object obj = null;
        if (UserCheckSDK.isNetworkAvailable(this.f986a.context)) {
            ArrayList a = this.f986a.m443a();
            if (a != null && a != null && a.size() > 0) {
                try {
                    if ("100".equals(new HS_CommCloud(this.f986a.context).weight_upload(this.f986a.f942a, this.f986a.f943b, a, this.f986a.f945d).getResultMessage())) {
                        obj = 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (obj != null) {
                    DataBaseTools dataBaseTools = new DataBaseTools(this.f986a.context);
                    Iterator it = a.iterator();
                    while (it.hasNext()) {
                        dataBaseTools.deleteData(DataBaseConstants.TABLE_TB_HSRESULT, "iHealthID = '" + this.f986a.f942a + "' and " + "PhoneDataID" + " = '" + ((Data_HS_Result) it.next()).getPhoneDataID() + "'").booleanValue();
                    }
                }
                new DataBaseTools(this.f986a.context).selectData(DataBaseConstants.TABLE_TB_HSRESULT, null, "iHealthID='" + this.f986a.f942a + "'");
            }
        }
    }
}
