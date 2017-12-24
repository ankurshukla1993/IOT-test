package com.ihealth.communication.cloud.data;

import com.ihealth.communication.cloud.UserCheckSDK;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimerTask;

class C2056b extends TimerTask {
    final /* synthetic */ BG_Up f984a;

    C2056b(BG_Up bG_Up) {
        this.f984a = bG_Up;
    }

    public void run() {
        Object obj = null;
        if (UserCheckSDK.isNetworkAvailable(this.f984a.context)) {
            ArrayList a = this.f984a.m418a();
            if (a != null && a != null && a.size() > 0) {
                try {
                    if ("100".equals(new BG_CommCloud(this.f984a.context).bg_upload(this.f984a.f590a, this.f984a.f591b, a, this.f984a.f593d).getResultMessage())) {
                        obj = 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (obj != null) {
                    DataBaseTools dataBaseTools = new DataBaseTools(this.f984a.context);
                    Iterator it = a.iterator();
                    while (it.hasNext()) {
                        dataBaseTools.deleteData(DataBaseConstants.TABLE_TB_BGRESULT, "iHealthID = '" + this.f984a.f590a + "' and " + "PhoneDataID" + " = '" + ((Data_BG_Result) it.next()).getPhoneDataID() + "'").booleanValue();
                    }
                }
                new DataBaseTools(this.f984a.context).selectData(DataBaseConstants.TABLE_TB_BGRESULT, null, "iHealthID='" + this.f984a.f590a + "'");
            }
        }
    }
}
