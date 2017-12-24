package com.ihealth.communication.ins;

import android.content.Context;
import com.ihealth.communication.cloud.data.BP_InAuthor;
import com.ihealth.communication.cloud.data.DataBaseConstants;
import com.ihealth.communication.cloud.data.DataBaseTools;
import com.ihealth.communication.cloud.data.Make_Data_Util;
import com.ihealth.communication.cloud.p002a.C2041b;

public class A1DBtools {
    public void save(Context context, String userName, String mac, String type, int Hp, int Lp, int HR, long TS) {
        if (C2041b.f505a) {
            new DataBaseTools(context).addData(DataBaseConstants.TABLE_TB_BPMEASURERESULT, Make_Data_Util.makeDataSingleBp(userName, Hp, Lp, HR, type, mac, TS));
            BP_InAuthor instance = BP_InAuthor.getInstance();
            instance.initAuthor(context, userName);
            instance.run();
        }
    }
}
