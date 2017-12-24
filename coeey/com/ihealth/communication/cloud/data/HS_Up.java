package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.database.Cursor;
import com.evernote.android.job.JobRequest;
import com.google.common.net.HttpHeaders;
import com.ihealth.communication.cloud.p002a.C2041b;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HS_Up {
    private String f942a;
    private String f943b;
    private String f944c;
    public Context context;
    private String f945d;
    public TimerTask task;
    public final Timer timer = new Timer();

    public HS_Up(Context context) {
        this.f942a = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "");
        this.f943b = context.getSharedPreferences("jiuan.sdk.author", 0).getString("accessToken", "");
        this.f944c = context.getSharedPreferences("jiuan.sdk.author", 0).getString("refreshToken", "");
        this.f945d = context.getSharedPreferences("jiuan.sdk.author", 0).getString(HttpHeaders.HOST, "");
        if ("".equals(this.f945d)) {
            this.f945d = C2041b.f506b;
        }
        this.context = context;
    }

    private ArrayList m443a() {
        Cursor selectData = new DataBaseTools(this.context).selectData(DataBaseConstants.TABLE_TB_HSRESULT, null, "iHealthID='" + this.f942a + "'");
        selectData.moveToFirst();
        if (selectData.getCount() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < selectData.getCount(); i++) {
                Data_HS_Result data_HS_Result = new Data_HS_Result();
                data_HS_Result.setChangeType(selectData.getInt(selectData.getColumnIndex("ChangeType")));
                data_HS_Result.setLastChangeTime(selectData.getLong(selectData.getColumnIndex("LastChangeTime")));
                data_HS_Result.setPhoneDataID(selectData.getString(selectData.getColumnIndex("PhoneDataID")));
                data_HS_Result.setPhoneCreateTime(selectData.getLong(selectData.getColumnIndex("PhoneCreateTime")));
                data_HS_Result.setLat((double) selectData.getFloat(selectData.getColumnIndex("Lat")));
                data_HS_Result.setLon((double) selectData.getFloat(selectData.getColumnIndex("Lon")));
                data_HS_Result.setTimeZone(selectData.getFloat(selectData.getColumnIndex("TimeZone")));
                data_HS_Result.setBMI(selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.HSRESULT_BMI)));
                data_HS_Result.setBoneValue(selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.HSRESULT_BONEVALUE)));
                data_HS_Result.setDCI(selectData.getFloat(selectData.getColumnIndex("DCI")));
                data_HS_Result.setFatValue(selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.HSRESULT_FATVALUE)));
                data_HS_Result.setMuscaleValue(selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.HSRESULT_MUSCALEVALUE)));
                data_HS_Result.setMeasureType(selectData.getInt(selectData.getColumnIndex("MeasureType")));
                data_HS_Result.setWaterValue((float) selectData.getInt(selectData.getColumnIndex(DataBaseConstants.HSRESULT_WATERVALUE)));
                data_HS_Result.setWeightValue(selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.HSRESULT_WEIGHTVALUE)));
                data_HS_Result.setMeasureTime(selectData.getLong(selectData.getColumnIndex("MeasureTime")));
                data_HS_Result.setNote(selectData.getString(selectData.getColumnIndex("Note")));
                data_HS_Result.setVisceraFatLevel(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.HSRESULT_VISCERAFATLEVEL)));
                data_HS_Result.setMechineType(selectData.getString(selectData.getColumnIndex("MechineType")));
                data_HS_Result.setMechineDeviceID(selectData.getString(selectData.getColumnIndex("MechineDeviceID")));
                data_HS_Result.setiHealthID(selectData.getString(selectData.getColumnIndex("iHealthID")));
                data_HS_Result.setEmotions(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.HSRESULT_EMOTIONS)));
                data_HS_Result.setNoteTS(selectData.getLong(selectData.getColumnIndex("NoteTS")));
                data_HS_Result.setMood(selectData.getInt(selectData.getColumnIndex("Mood")));
                data_HS_Result.setActivity(selectData.getInt(selectData.getColumnIndex("Activity")));
                data_HS_Result.setTemp(selectData.getString(selectData.getColumnIndex(DataBaseConstants.HSRESULT_TEMP)));
                data_HS_Result.setWeather(selectData.getString(selectData.getColumnIndex("Weather")));
                data_HS_Result.setHumidity(selectData.getString(selectData.getColumnIndex(DataBaseConstants.HSRESULT_HUMIDITY)));
                data_HS_Result.setVisibility(selectData.getString(selectData.getColumnIndex(DataBaseConstants.HSRESULT_VISIBILITY)));
                data_HS_Result.setUsedUserid(selectData.getInt(selectData.getColumnIndex("UsedUserid")));
                arrayList.add(data_HS_Result);
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
        this.task = new C2059e(this);
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
