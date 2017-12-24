package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.database.Cursor;
import com.evernote.android.job.JobRequest;
import com.google.common.net.HttpHeaders;
import com.ihealth.communication.cloud.p002a.C2041b;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class BG_Up {
    private String f590a;
    private String f591b;
    private String f592c;
    public Context context;
    private String f593d;
    public TimerTask task;
    public final Timer timer = new Timer();

    public BG_Up(Context context) {
        this.f590a = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "");
        this.f591b = context.getSharedPreferences("jiuan.sdk.author", 0).getString("accessToken", "");
        this.f592c = context.getSharedPreferences("jiuan.sdk.author", 0).getString("refreshToken", "");
        this.f593d = context.getSharedPreferences("jiuan.sdk.author", 0).getString(HttpHeaders.HOST, "");
        if ("".equals(this.f593d)) {
            this.f593d = C2041b.f506b;
        }
        this.context = context;
    }

    private ArrayList m418a() {
        Cursor selectData = new DataBaseTools(this.context).selectData(DataBaseConstants.TABLE_TB_BGRESULT, null, "iHealthID='" + this.f590a + "'");
        selectData.moveToFirst();
        if (selectData.getCount() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < selectData.getCount(); i++) {
                Data_BG_Result data_BG_Result = new Data_BG_Result();
                data_BG_Result.setChangeType(selectData.getInt(selectData.getColumnIndex("ChangeType")));
                data_BG_Result.setLastChangeTime(selectData.getLong(selectData.getColumnIndex("LastChangeTime")));
                data_BG_Result.setPhoneDataID(selectData.getString(selectData.getColumnIndex("PhoneDataID")));
                data_BG_Result.setPhoneCreateTime(selectData.getLong(selectData.getColumnIndex("PhoneCreateTime")));
                data_BG_Result.setLat((double) selectData.getFloat(selectData.getColumnIndex("Lat")));
                data_BG_Result.setLon((double) selectData.getFloat(selectData.getColumnIndex("Lon")));
                data_BG_Result.setTimeZone(selectData.getFloat(selectData.getColumnIndex("TimeZone")));
                data_BG_Result.setBGValue(selectData.getFloat(selectData.getColumnIndexOrThrow(DataBaseConstants.BGRESULT_BGVALUE)));
                data_BG_Result.setMedication(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.BGRESULT_MEDICATION)));
                data_BG_Result.setMTimeType(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.BGRESULT_MTIMETYPE)));
                data_BG_Result.setMeasureType(selectData.getInt(selectData.getColumnIndex("MeasureType")));
                data_BG_Result.setMeasureTime(selectData.getLong(selectData.getColumnIndex("MeasureTime")));
                data_BG_Result.setNote(selectData.getString(selectData.getColumnIndex("Note")));
                data_BG_Result.setMechineType(selectData.getString(selectData.getColumnIndex("MechineType")));
                data_BG_Result.setMechineDeviceID(selectData.getString(selectData.getColumnIndex("MechineDeviceID")));
                data_BG_Result.setBottleId(selectData.getString(selectData.getColumnIndex(DataBaseConstants.BGRESULT_BOTTLEID)));
                data_BG_Result.setSports(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.BGRESULT_SPORTS)));
                data_BG_Result.setSnacks(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.BGRESULT_SNACKS)));
                data_BG_Result.setEffective(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.BGRESULT_EFFECTIVE)));
                data_BG_Result.setiHealthID(selectData.getString(selectData.getColumnIndex("iHealthID")));
                arrayList.add(data_BG_Result);
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
        this.task = new C2056b(this);
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
