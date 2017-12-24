package com.ihealth.communication.cloud.data;

import com.ihealth.communication.cloud.UserCheckSDK;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimerTask;

class C2060f extends TimerTask {
    final /* synthetic */ PO_Up f987a;

    C2060f(PO_Up pO_Up) {
        this.f987a = pO_Up;
    }

    public void run() {
        Object obj = null;
        if (UserCheckSDK.isNetworkAvailable(this.f987a.context)) {
            ArrayList a = this.f987a.m450a();
            if (a != null && a != null && a.size() > 0) {
                try {
                    if ("100".equals(new PO_CommCloud(this.f987a.context).am_po_upload(this.f987a.f965a, this.f987a.f966b, a, this.f987a.f968d).getResultMessage())) {
                        obj = 1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (obj != null) {
                    DataBaseTools dataBaseTools = new DataBaseTools(this.f987a.context);
                    Iterator it = a.iterator();
                    while (it.hasNext()) {
                        dataBaseTools.deleteData(DataBaseConstants.TABLE_TB_PO, "iHealthID = '" + this.f987a.f965a + "' and " + "PhoneDataID" + " = '" + ((Data_PO_Result) it.next()).getPhoneDataID() + "'").booleanValue();
                    }
                }
                new DataBaseTools(this.f987a.context).selectData(DataBaseConstants.TABLE_TB_PO, null, "iHealthID='" + this.f987a.f965a + "'");
            }
        }
    }
}
