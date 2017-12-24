package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.database.Cursor;
import com.evernote.android.job.JobRequest;
import com.google.common.net.HttpHeaders;
import com.ihealth.communication.cloud.p002a.C2041b;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BP_Up {
    private String f607a;
    private String f608b;
    private String f609c;
    public Context context;
    private String f610d;
    public TimerTask task;
    public final Timer timer = new Timer();

    public BP_Up(Context context) {
        this.f607a = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "");
        this.f608b = context.getSharedPreferences("jiuan.sdk.author", 0).getString("accessToken", "");
        this.f609c = context.getSharedPreferences("jiuan.sdk.author", 0).getString("refreshToken", "");
        this.f610d = context.getSharedPreferences("jiuan.sdk.author", 0).getString(HttpHeaders.HOST, "");
        if ("".equals(this.f610d)) {
            this.f610d = C2041b.f506b;
        }
        this.context = context;
    }

    private ArrayList m425a() {
        Cursor selectData = new DataBaseTools(this.context).selectData(DataBaseConstants.TABLE_TB_BPMEASURERESULT, null, "bpun='" + this.f607a + "'");
        selectData.moveToFirst();
        if (selectData.getCount() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < selectData.getCount(); i++) {
                Data_BP_Result data_BP_Result = new Data_BP_Result();
                data_BP_Result.setBPL(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_BPL)));
                data_BP_Result.setChangeType(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_CHANGETYPE)));
                data_BP_Result.setHP(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_HP)));
                data_BP_Result.setHR(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_HR)));
                data_BP_Result.setIsArr(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_ISARR)));
                data_BP_Result.setLastChangeTime(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_LASTCHANGETIME)));
                data_BP_Result.setLat((double) selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_LAT)));
                data_BP_Result.setLon((double) selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_LON)));
                data_BP_Result.setLP(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_LP)));
                data_BP_Result.setMeasureTime(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_MEASURETIME)));
                data_BP_Result.setMeasureType(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_MEASURETYPE)));
                data_BP_Result.setMechineDeviceID(selectData.getString(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_MECHINEDEVICEID)));
                data_BP_Result.setMechineType(selectData.getString(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_MECHINETYPE)));
                data_BP_Result.setNote(selectData.getString(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_NOTE)));
                data_BP_Result.setPhoneCreateTime(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_PHONECREATETIME)));
                data_BP_Result.setPhoneDataID(selectData.getString(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_PHONEDATAID)));
                data_BP_Result.setTimeZone(selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_TIMEZONE)));
                data_BP_Result.setWL(selectData.getString(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_WL)));
                data_BP_Result.setiHealthCloud(selectData.getString(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_UN)));
                data_BP_Result.setNoteTS(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_NOTETS)));
                data_BP_Result.setBpMood(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_BPMOOD)));
                data_BP_Result.setBpActivity(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_BPACTIVITY)));
                data_BP_Result.setTemp(selectData.getString(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_TEMP)));
                data_BP_Result.setWeather(selectData.getString(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_WEATHER)));
                data_BP_Result.setHumidity(selectData.getString(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_HUMIDITY)));
                data_BP_Result.setVisibility(selectData.getString(selectData.getColumnIndex(DataBaseConstants.BPMEASURERESULT_VISIBILITY)));
                data_BP_Result.setUsedUserid(selectData.getInt(selectData.getColumnIndex("UsedUserid")));
                arrayList.add(data_BP_Result);
                selectData.moveToNext();
                if (arrayList.size() > 50) {
                    break;
                }
            }
            selectData.close();
            return arrayList;
        }
        selectData.close();
        return null;
    }

    public void Start_timer() {
        this.task = new C2057c(this);
        try {
            this.timer.schedule(this.task, 1000, JobRequest.DEFAULT_BACKOFF_MS);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e2) {
            e2.printStackTrace();
        }
    }

    public void Stop_timer() {
        this.timer.cancel();
    }
}
