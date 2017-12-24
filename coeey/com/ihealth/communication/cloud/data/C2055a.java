package com.ihealth.communication.cloud.data;

import com.ihealth.communication.cloud.UserCheckSDK;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimerTask;

class C2055a extends TimerTask {
    final /* synthetic */ AM_Up f983a;

    C2055a(AM_Up aM_Up) {
        this.f983a = aM_Up;
    }

    public void run() {
        Object obj = 1;
        if (UserCheckSDK.isNetworkAvailable(this.f983a.context)) {
            Object obj2;
            DataBaseTools dataBaseTools;
            Iterator it;
            ArrayList a = this.f983a.m399a();
            if (!(a == null || a == null || a.size() <= 0)) {
                try {
                    obj2 = "100".equals(new AM_CommCloud(this.f983a.context).am_activity_upload(this.f983a.f568a, this.f983a.f569b, a, this.f983a.f571d).getResultMessage()) ? 1 : null;
                } catch (Exception e) {
                    e.printStackTrace();
                    obj2 = null;
                }
                if (obj2 != null) {
                    dataBaseTools = new DataBaseTools(this.f983a.context);
                    it = a.iterator();
                    while (it.hasNext()) {
                        dataBaseTools.deleteData(DataBaseConstants.TABLE_TB_AM_ACTIVITY, "iHealthID = '" + this.f983a.f568a + "' and " + "PhoneDataID" + " = '" + ((Data_AM_Activity) it.next()).getPhoneDataID() + "'").booleanValue();
                    }
                }
                new DataBaseTools(this.f983a.context).selectData(DataBaseConstants.TABLE_TB_AM_ACTIVITY, null, "iHealthID='" + this.f983a.f568a + "'").close();
            }
            a = this.f983a.m402b();
            if (!(a == null || a == null || a.size() <= 0)) {
                try {
                    obj2 = "100".equals(new AM_CommCloud(this.f983a.context).am_report_upload(this.f983a.f568a, this.f983a.f569b, a, this.f983a.f571d).getResultMessage()) ? 1 : null;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    obj2 = null;
                }
                if (obj2 != null) {
                    dataBaseTools = new DataBaseTools(this.f983a.context);
                    it = a.iterator();
                    while (it.hasNext()) {
                        dataBaseTools.deleteData(DataBaseConstants.TABLE_TB_AM_ACTIVITYREPORT, "iHealthID = '" + this.f983a.f568a + "' and " + "PhoneDataID" + " = '" + ((Data_AM_ActivityDayReport) it.next()).getPhoneDataID() + "'").booleanValue();
                    }
                }
                new DataBaseTools(this.f983a.context).selectData(DataBaseConstants.TABLE_TB_AM_ACTIVITYREPORT, null, "iHealthID='" + this.f983a.f568a + "'").close();
            }
            a = this.f983a.m404c();
            if (!(a == null || a == null || a.size() <= 0)) {
                try {
                    obj2 = "100".equals(new AM_CommCloud(this.f983a.context).am_sleep_upload(this.f983a.f568a, this.f983a.f569b, a, this.f983a.f571d).getResultMessage()) ? 1 : null;
                } catch (Exception e22) {
                    e22.printStackTrace();
                    obj2 = null;
                }
                if (obj2 != null) {
                    dataBaseTools = new DataBaseTools(this.f983a.context);
                    it = a.iterator();
                    while (it.hasNext()) {
                        dataBaseTools.deleteData(DataBaseConstants.TABLE_TB_AM_SLEEP, "iHealthID = '" + this.f983a.f568a + "' and " + "PhoneDataID" + " = '" + ((Data_AM_Sleep) it.next()).getPhoneDataID() + "'").booleanValue();
                    }
                }
                new DataBaseTools(this.f983a.context).selectData(DataBaseConstants.TABLE_TB_AM_SLEEP, null, "iHealthID='" + this.f983a.f568a + "'").close();
            }
            a = this.f983a.m406d();
            if (!(a == null || a == null || a.size() <= 0)) {
                try {
                    obj2 = "100".equals(new AM_CommCloud(this.f983a.context).am_section_upload(this.f983a.f568a, this.f983a.f569b, a, this.f983a.f571d).getResultMessage()) ? 1 : null;
                } catch (Exception e222) {
                    e222.printStackTrace();
                    obj2 = null;
                }
                if (obj2 != null) {
                    dataBaseTools = new DataBaseTools(this.f983a.context);
                    it = a.iterator();
                    while (it.hasNext()) {
                        dataBaseTools.deleteData(DataBaseConstants.TABLE_TB_AM_SLEEPREPORT, "iHealthID = '" + this.f983a.f568a + "' and " + "PhoneDataID" + " = '" + ((Data_AM_SleepSectionReport) it.next()).getPhoneDataID() + "'").booleanValue();
                    }
                }
                new DataBaseTools(this.f983a.context).selectData(DataBaseConstants.TABLE_TB_AM_SLEEPREPORT, null, "iHealthID='" + this.f983a.f568a + "'").close();
            }
            a = this.f983a.m407e();
            if (!(a == null || a == null || a.size() <= 0)) {
                try {
                    obj2 = "100".equals(new AM_CommCloud(this.f983a.context).am_workout_upload(this.f983a.f568a, this.f983a.f569b, a, this.f983a.f571d).getResultMessage()) ? 1 : null;
                } catch (Exception e2222) {
                    e2222.printStackTrace();
                    obj2 = null;
                }
                if (obj2 != null) {
                    dataBaseTools = new DataBaseTools(this.f983a.context);
                    it = a.iterator();
                    while (it.hasNext()) {
                        dataBaseTools.deleteData(DataBaseConstants.TABLE_TB_WORKOUT, "workout_iHealthCloud = '" + this.f983a.f568a + "' and " + DataBaseConstants.WORKOUT_PHONEDATAID + " = '" + ((Data_TB_Workout) it.next()).getWorkout_PhoneDataID() + "'").booleanValue();
                    }
                }
                new DataBaseTools(this.f983a.context).selectData(DataBaseConstants.TABLE_TB_WORKOUT, null, "workout_iHealthCloud='" + this.f983a.f568a + "'").close();
            }
            a = this.f983a.m409f();
            if (!(a == null || a == null || a.size() <= 0)) {
                try {
                    obj2 = new AM_CommCloud(this.f983a.context).swimActivity_upload(this.f983a.f568a, this.f983a.f569b, a, this.f983a.f571d) == 100 ? 1 : null;
                } catch (Exception e22222) {
                    e22222.printStackTrace();
                    obj2 = null;
                }
                if (obj2 != null) {
                    dataBaseTools = new DataBaseTools(this.f983a.context);
                    it = a.iterator();
                    while (it.hasNext()) {
                        dataBaseTools.deleteData(DataBaseConstants.TABLE_TB_SWIM, "swim_iHealthCloud = '" + this.f983a.f568a + "' and " + DataBaseConstants.SWIM_PHONEDATAID + " = '" + ((Data_TB_Swim) it.next()).getSwim_PhoneDataID() + "'").booleanValue();
                    }
                }
                new DataBaseTools(this.f983a.context).selectData(DataBaseConstants.TABLE_TB_SWIM, null, "swim_iHealthCloud='" + this.f983a.f568a + "'").close();
            }
            a = this.f983a.m411g();
            if (a != null && a != null && a.size() > 0) {
                try {
                    if (new AM_CommCloud(this.f983a.context).swimReport_upload(this.f983a.f568a, this.f983a.f569b, a, this.f983a.f571d) != 100) {
                        obj = null;
                    }
                } catch (Exception e222222) {
                    e222222.printStackTrace();
                    obj = null;
                }
                if (obj != null) {
                    DataBaseTools dataBaseTools2 = new DataBaseTools(this.f983a.context);
                    Iterator it2 = a.iterator();
                    while (it2.hasNext()) {
                        dataBaseTools2.deleteData(DataBaseConstants.TABLE_TB_SWIMSECTION, "swimSection_iHealthCloud = '" + this.f983a.f568a + "' and " + DataBaseConstants.SWIMSECTION_DATAID + " = '" + ((Data_TB_SwimSection) it2.next()).getSwimSection_DataID() + "'").booleanValue();
                    }
                }
                new DataBaseTools(this.f983a.context).selectData(DataBaseConstants.TABLE_TB_SWIMSECTION, null, "swimSection_iHealthCloud='" + this.f983a.f568a + "'").close();
            }
        }
    }
}
