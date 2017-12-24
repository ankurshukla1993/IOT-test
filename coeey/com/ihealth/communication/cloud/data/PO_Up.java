package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.database.Cursor;
import com.evernote.android.job.JobRequest;
import com.google.common.net.HttpHeaders;
import com.ihealth.communication.cloud.p002a.C2041b;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PO_Up {
    private String f965a;
    private String f966b;
    private String f967c;
    public Context context;
    private String f968d;
    public TimerTask task;
    public final Timer timer = new Timer();

    public PO_Up(Context context) {
        this.f965a = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "");
        this.f966b = context.getSharedPreferences("jiuan.sdk.author", 0).getString("accessToken", "");
        this.f967c = context.getSharedPreferences("jiuan.sdk.author", 0).getString("refreshToken", "");
        this.f968d = context.getSharedPreferences("jiuan.sdk.author", 0).getString(HttpHeaders.HOST, "");
        if ("".equals(this.f968d)) {
            this.f968d = C2041b.f506b;
        }
        this.context = context;
    }

    private ArrayList m450a() {
        Cursor selectData = new DataBaseTools(this.context).selectData(DataBaseConstants.TABLE_TB_PO, null, "iHealthID='" + this.f965a + "'");
        selectData.moveToFirst();
        if (selectData.getCount() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < selectData.getCount(); i++) {
                Data_PO_Result data_PO_Result = new Data_PO_Result();
                data_PO_Result.setChangeType(selectData.getInt(selectData.getColumnIndex("ChangeType")));
                data_PO_Result.setLastChangeTime(selectData.getLong(selectData.getColumnIndex("LastChangeTime")));
                data_PO_Result.setPhoneDataID(selectData.getString(selectData.getColumnIndex("PhoneDataID")));
                data_PO_Result.setPhoneCreateTime(selectData.getLong(selectData.getColumnIndex("PhoneCreateTime")));
                data_PO_Result.setLat((double) selectData.getFloat(selectData.getColumnIndex("Lat")));
                data_PO_Result.setLon((double) selectData.getFloat(selectData.getColumnIndex("Lon")));
                data_PO_Result.setTimeZone(selectData.getFloat(selectData.getColumnIndex("TimeZone")));
                data_PO_Result.setActivity(selectData.getInt(selectData.getColumnIndex("Activity")));
                data_PO_Result.setWave(selectData.getString(selectData.getColumnIndex(DataBaseConstants.PO_WAVE)));
                data_PO_Result.setPR(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.PO_PR)));
                data_PO_Result.setResult(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.PO_RESULT)));
                data_PO_Result.setFlowrate(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.PO_FLOWRATE)));
                data_PO_Result.setResultSource(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.PO_RESULTSOURCE)));
                data_PO_Result.setMood(selectData.getInt(selectData.getColumnIndex("Mood")));
                data_PO_Result.setWeather(selectData.getString(selectData.getColumnIndex("Weather")));
                data_PO_Result.setNote(selectData.getString(selectData.getColumnIndex("Note")));
                data_PO_Result.setNoteTS(selectData.getLong(selectData.getColumnIndex("NoteTS")));
                data_PO_Result.setMeasureTime(selectData.getLong(selectData.getColumnIndex("MeasureTime")));
                data_PO_Result.setMechineType(selectData.getString(selectData.getColumnIndex("MechineType")));
                data_PO_Result.setMechineDeviceID(selectData.getString(selectData.getColumnIndex("MechineDeviceID")));
                data_PO_Result.setiHealthID(selectData.getString(selectData.getColumnIndex("iHealthID")));
                arrayList.add(data_PO_Result);
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
        this.task = new C2060f(this);
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
