package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.database.Cursor;
import com.evernote.android.job.JobRequest;
import com.google.common.net.HttpHeaders;
import com.ihealth.communication.cloud.p002a.C2041b;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AM_Up {
    private String f568a;
    private String f569b;
    private String f570c;
    public Context context;
    private String f571d;
    public TimerTask task;
    public final Timer timer = new Timer();

    public AM_Up(Context context) {
        this.f568a = context.getSharedPreferences("jiuan.sdk.author", 0).getString("email", "");
        this.f569b = context.getSharedPreferences("jiuan.sdk.author", 0).getString("accessToken", "");
        this.f570c = context.getSharedPreferences("jiuan.sdk.author", 0).getString("refreshToken", "");
        this.f571d = context.getSharedPreferences("jiuan.sdk.author", 0).getString(HttpHeaders.HOST, "");
        if ("".equals(this.f571d)) {
            this.f571d = C2041b.f506b;
        }
        this.context = context;
    }

    private ArrayList m399a() {
        Cursor selectData = new DataBaseTools(this.context).selectData(DataBaseConstants.TABLE_TB_AM_ACTIVITY, null, "iHealthID='" + this.f568a + "'");
        selectData.moveToFirst();
        if (selectData.getCount() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < selectData.getCount(); i++) {
                Data_AM_Activity data_AM_Activity = new Data_AM_Activity();
                data_AM_Activity.setChangeType(selectData.getInt(selectData.getColumnIndex("ChangeType")));
                data_AM_Activity.setLastChangeTime(selectData.getLong(selectData.getColumnIndex("LastChangeTime")));
                data_AM_Activity.setPhoneDataID(selectData.getString(selectData.getColumnIndex("PhoneDataID")));
                data_AM_Activity.setPhoneCreateTime(selectData.getLong(selectData.getColumnIndex("PhoneCreateTime")));
                data_AM_Activity.setLat((double) selectData.getFloat(selectData.getColumnIndex("Lat")));
                data_AM_Activity.setLon((double) selectData.getFloat(selectData.getColumnIndex("Lon")));
                data_AM_Activity.setTimeZone(selectData.getFloat(selectData.getColumnIndex("TimeZone")));
                data_AM_Activity.setCalorie(selectData.getFloat(selectData.getColumnIndex("Calorie")));
                data_AM_Activity.setStepLength(selectData.getInt(selectData.getColumnIndex("StepLength")));
                data_AM_Activity.setSteps(selectData.getInt(selectData.getColumnIndex("Steps")));
                data_AM_Activity.setSumCalorie(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.ACTIVITY_SUMCALORIE)));
                data_AM_Activity.setSumSteps(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.ACTIVITY_SUMSTEPS)));
                data_AM_Activity.setMeasureTime(selectData.getLong(selectData.getColumnIndex("MeasureTime")));
                data_AM_Activity.setMechineType(selectData.getString(selectData.getColumnIndex("MechineType")));
                data_AM_Activity.setMechineDeviceID(selectData.getString(selectData.getColumnIndex("MechineDeviceID")));
                data_AM_Activity.setiHealthID(selectData.getString(selectData.getColumnIndex("iHealthID")));
                arrayList.add(data_AM_Activity);
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

    private ArrayList m402b() {
        Cursor selectData = new DataBaseTools(this.context).selectData(DataBaseConstants.TABLE_TB_AM_ACTIVITYREPORT, null, "iHealthID='" + this.f568a + "'");
        selectData.moveToFirst();
        if (selectData.getCount() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < selectData.getCount(); i++) {
                Data_AM_ActivityDayReport data_AM_ActivityDayReport = new Data_AM_ActivityDayReport();
                data_AM_ActivityDayReport.setChangeType(selectData.getInt(selectData.getColumnIndex("ChangeType")));
                data_AM_ActivityDayReport.setLastChangeTime(selectData.getLong(selectData.getColumnIndex("LastChangeTime")));
                data_AM_ActivityDayReport.setPhoneDataID(selectData.getString(selectData.getColumnIndex("PhoneDataID")));
                data_AM_ActivityDayReport.setPhoneCreateTime(selectData.getLong(selectData.getColumnIndex("PhoneCreateTime")));
                data_AM_ActivityDayReport.setLat((double) selectData.getFloat(selectData.getColumnIndex("Lat")));
                data_AM_ActivityDayReport.setLon((double) selectData.getFloat(selectData.getColumnIndex("Lon")));
                data_AM_ActivityDayReport.setTimeZone(selectData.getFloat(selectData.getColumnIndex("TimeZone")));
                data_AM_ActivityDayReport.setCalorie(selectData.getFloat(selectData.getColumnIndex("Calorie")));
                data_AM_ActivityDayReport.setStepLength(selectData.getInt(selectData.getColumnIndex("StepLength")));
                data_AM_ActivityDayReport.setSteps(selectData.getInt(selectData.getColumnIndex("Steps")));
                data_AM_ActivityDayReport.setPlanSteps(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_PLANSTEPS)));
                data_AM_ActivityDayReport.setPlanCalorie(selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.ACTIVITYREPORT_PLANCALORIE)));
                data_AM_ActivityDayReport.setCity(selectData.getString(selectData.getColumnIndex("City")));
                data_AM_ActivityDayReport.setWeather(selectData.getString(selectData.getColumnIndex("Weather")));
                data_AM_ActivityDayReport.setComment(selectData.getString(selectData.getColumnIndex("Comment")));
                data_AM_ActivityDayReport.setMeasureTime(selectData.getLong(selectData.getColumnIndex("MeasureTime")));
                data_AM_ActivityDayReport.setMechineType(selectData.getString(selectData.getColumnIndex("MechineType")));
                data_AM_ActivityDayReport.setMechineDeviceID(selectData.getString(selectData.getColumnIndex("MechineDeviceID")));
                data_AM_ActivityDayReport.setiHealthID(selectData.getString(selectData.getColumnIndex("iHealthID")));
                arrayList.add(data_AM_ActivityDayReport);
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

    private ArrayList m404c() {
        Cursor selectData = new DataBaseTools(this.context).selectData(DataBaseConstants.TABLE_TB_AM_SLEEP, null, "iHealthID='" + this.f568a + "'");
        selectData.moveToFirst();
        if (selectData.getCount() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < selectData.getCount(); i++) {
                Data_AM_Sleep data_AM_Sleep = new Data_AM_Sleep();
                data_AM_Sleep.setChangeType(selectData.getInt(selectData.getColumnIndex("ChangeType")));
                data_AM_Sleep.setLastChangeTime(selectData.getLong(selectData.getColumnIndex("LastChangeTime")));
                data_AM_Sleep.setPhoneDataID(selectData.getString(selectData.getColumnIndex("PhoneDataID")));
                data_AM_Sleep.setPhoneCreateTime(selectData.getLong(selectData.getColumnIndex("PhoneCreateTime")));
                data_AM_Sleep.setLat((double) selectData.getFloat(selectData.getColumnIndex("Lat")));
                data_AM_Sleep.setLon((double) selectData.getFloat(selectData.getColumnIndex("Lon")));
                data_AM_Sleep.setTimeZone(selectData.getFloat(selectData.getColumnIndex("TimeZone")));
                data_AM_Sleep.setSleepLevel(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SLEEP_SLEEPLEVEL)));
                data_AM_Sleep.setTimeSectionId(selectData.getString(selectData.getColumnIndex("TimeSectionId")));
                data_AM_Sleep.setMeasureTime(selectData.getLong(selectData.getColumnIndex("MeasureTime")));
                data_AM_Sleep.setMechineType(selectData.getString(selectData.getColumnIndex("MechineType")));
                data_AM_Sleep.setMechineDeviceID(selectData.getString(selectData.getColumnIndex("MechineDeviceID")));
                data_AM_Sleep.setiHealthID(selectData.getString(selectData.getColumnIndex("iHealthID")));
                arrayList.add(data_AM_Sleep);
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

    private ArrayList m406d() {
        Cursor selectData = new DataBaseTools(this.context).selectData(DataBaseConstants.TABLE_TB_AM_SLEEPREPORT, null, "iHealthID='" + this.f568a + "'");
        selectData.moveToFirst();
        if (selectData.getCount() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < selectData.getCount(); i++) {
                Data_AM_SleepSectionReport data_AM_SleepSectionReport = new Data_AM_SleepSectionReport();
                data_AM_SleepSectionReport.setChangeType(selectData.getInt(selectData.getColumnIndex("ChangeType")));
                data_AM_SleepSectionReport.setLastChangeTime(selectData.getLong(selectData.getColumnIndex("LastChangeTime")));
                data_AM_SleepSectionReport.setPhoneDataID(selectData.getString(selectData.getColumnIndex("PhoneDataID")));
                data_AM_SleepSectionReport.setPhoneCreateTime(selectData.getLong(selectData.getColumnIndex("PhoneCreateTime")));
                data_AM_SleepSectionReport.setLat((double) selectData.getFloat(selectData.getColumnIndex("Lat")));
                data_AM_SleepSectionReport.setLon((double) selectData.getFloat(selectData.getColumnIndex("Lon")));
                data_AM_SleepSectionReport.setTimeZone(selectData.getFloat(selectData.getColumnIndex("TimeZone")));
                data_AM_SleepSectionReport.setAwake(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SLEEPREPORT_AWAKE)));
                data_AM_SleepSectionReport.setDeepSleep(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SLEEPREPORT_DEEPSLEEP)));
                data_AM_SleepSectionReport.setFallSleep(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SLEEPREPORT_FALLSLEEP)));
                data_AM_SleepSectionReport.setSleep(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SLEEPREPORT_SLEEP)));
                data_AM_SleepSectionReport.setAwakenTimes(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SLEEPREPORT_AWAKENTIMES)));
                data_AM_SleepSectionReport.setSleepStartTime(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.SLEEPREPORT_SLEEPSTARTTIME)));
                data_AM_SleepSectionReport.setSleepEndTime(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.SLEEPREPORT_SLEEPENDTIME)));
                data_AM_SleepSectionReport.setTimeSectionId(selectData.getString(selectData.getColumnIndex("TimeSectionId")));
                data_AM_SleepSectionReport.setCity(selectData.getString(selectData.getColumnIndex("City")));
                data_AM_SleepSectionReport.setWeather(selectData.getString(selectData.getColumnIndex("Weather")));
                data_AM_SleepSectionReport.setComment(selectData.getString(selectData.getColumnIndex("Comment")));
                data_AM_SleepSectionReport.setNap(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SLEEPREPORT_NAP)));
                data_AM_SleepSectionReport.setMood(selectData.getInt(selectData.getColumnIndex("Mood")));
                data_AM_SleepSectionReport.setActivity(selectData.getInt(selectData.getColumnIndex("Activity")));
                data_AM_SleepSectionReport.setMeasureTime(selectData.getLong(selectData.getColumnIndex("MeasureTime")));
                data_AM_SleepSectionReport.setMechineType(selectData.getString(selectData.getColumnIndex("MechineType")));
                data_AM_SleepSectionReport.setMechineDeviceID(selectData.getString(selectData.getColumnIndex("MechineDeviceID")));
                data_AM_SleepSectionReport.setiHealthID(selectData.getString(selectData.getColumnIndex("iHealthID")));
                arrayList.add(data_AM_SleepSectionReport);
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

    private ArrayList m407e() {
        Cursor selectData = new DataBaseTools(this.context).selectData(DataBaseConstants.TABLE_TB_WORKOUT, null, "workout_iHealthCloud='" + this.f568a + "'");
        selectData.moveToFirst();
        if (selectData.getCount() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < selectData.getCount(); i++) {
                Data_TB_Workout data_TB_Workout = new Data_TB_Workout();
                data_TB_Workout.setWorkout_iHealthCloud(this.f568a);
                data_TB_Workout.setWorkout_ChangeType(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.WORKOUT_CHANGETYPE)));
                data_TB_Workout.setWorkout_LastChangeTime(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.WORKOUT_LASTCHANGETIME)));
                data_TB_Workout.setWorkout_PhoneCreateTime(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.WORKOUT_PHONECREATETIME)));
                data_TB_Workout.setWorkout_PhoneDataID(selectData.getString(selectData.getColumnIndex(DataBaseConstants.WORKOUT_PHONEDATAID)));
                data_TB_Workout.setWorkout_Lat(selectData.getDouble(selectData.getColumnIndex(DataBaseConstants.WORKOUT_LAT)));
                data_TB_Workout.setWorkout_Lon(selectData.getDouble(selectData.getColumnIndex(DataBaseConstants.WORKOUT_LON)));
                data_TB_Workout.setWorkout_TimeZone(selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.WORKOUT_TIMEZONE)));
                data_TB_Workout.setWorkout_SpendMinutes(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.WORKOUT_SPENDMINUTES)));
                data_TB_Workout.setWorkout_Steps(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.WORKOUT_STEPS)));
                data_TB_Workout.setWorkout_Distance(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.WORKOUT_DISTANCE)));
                data_TB_Workout.setWorkout_Calories(selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.WORKOUT_CALORIES)));
                data_TB_Workout.setWorkout_City(selectData.getString(selectData.getColumnIndex(DataBaseConstants.WORKOUT_CITY)));
                data_TB_Workout.setWorkout_Temperature(selectData.getString(selectData.getColumnIndex(DataBaseConstants.WORKOUT_TEMPERATURE)));
                data_TB_Workout.setWorkout_Humidity(selectData.getString(selectData.getColumnIndex(DataBaseConstants.WORKOUT_HUMIDITY)));
                data_TB_Workout.setWorkout_Atmosphere(selectData.getString(selectData.getColumnIndex(DataBaseConstants.WORKOUT_ATMOSPHERE)));
                data_TB_Workout.setWorkout_WeatherCode(selectData.getString(selectData.getColumnIndex(DataBaseConstants.WORKOUT_WEATHERCODE)));
                data_TB_Workout.setWorkout_CommentNote(selectData.getString(selectData.getColumnIndex(DataBaseConstants.WORKOUT_COMMENTNOTE)));
                data_TB_Workout.setWorkout_CommentTS(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.WORKOUT_COMMENTTS)));
                data_TB_Workout.setWorkout_CommentPic(selectData.getString(selectData.getColumnIndex(DataBaseConstants.WORKOUT_COMMENTPIC)));
                data_TB_Workout.setWorkout_MechineType(selectData.getString(selectData.getColumnIndex(DataBaseConstants.WORKOUT_MECHINETYPE)));
                data_TB_Workout.setWorkout_MechineDeviceID(selectData.getString(selectData.getColumnIndex(DataBaseConstants.WORKOUT_MECHINEDEVICEID)));
                arrayList.add(data_TB_Workout);
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

    private ArrayList m409f() {
        Cursor selectData = new DataBaseTools(this.context).selectData(DataBaseConstants.TABLE_TB_SWIM, null, "swim_iHealthCloud='" + this.f568a + "'");
        selectData.moveToFirst();
        if (selectData.getCount() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < selectData.getCount(); i++) {
                Data_TB_Swim data_TB_Swim = new Data_TB_Swim();
                data_TB_Swim.setSwim_iHealthCloud(this.f568a);
                data_TB_Swim.setSwim_ChangeType(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIM_CHANGETYPE)));
                data_TB_Swim.setSwim_LastChangeTime(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.SWIM_LASTCHANGETIME)));
                data_TB_Swim.setSwim_PhoneCreateTime(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.SWIM_PHONECREATETIME)));
                data_TB_Swim.setSwim_PhoneDataID(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIM_PHONEDATAID)));
                data_TB_Swim.setSwim_Lat(selectData.getDouble(selectData.getColumnIndex(DataBaseConstants.SWIM_LAT)));
                data_TB_Swim.setSwim_Lon(selectData.getDouble(selectData.getColumnIndex(DataBaseConstants.SWIM_LON)));
                data_TB_Swim.setSwim_TimeZone(selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.SWIM_TIMEZONE)));
                data_TB_Swim.setSwim_SpendMinutes(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIM_SPENDMINUTES)));
                data_TB_Swim.setSwim_PullTimes(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIM_PULLTIMES)));
                data_TB_Swim.setSwim_Storke(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIM_STROKE)));
                data_TB_Swim.setSwim_Cycles(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIM_CYCLES)));
                data_TB_Swim.setSwim_Distance(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIM_DISTANCE)));
                data_TB_Swim.setSwim_Calories(selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.SWIM_CALORIES)));
                data_TB_Swim.setSwim_City(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIM_CITY)));
                data_TB_Swim.setSwim_Temperature(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIM_TEMPERATURE)));
                data_TB_Swim.setSwim_WeatherCode(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIM_WEATHERCODE)));
                data_TB_Swim.setSwim_Humidity(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIM_HUMIDITY)));
                data_TB_Swim.setSwim_Atmosphere(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIM_ATMOSPHERE)));
                data_TB_Swim.setSwim_CommentPic(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIM_COMMENTPIC)));
                data_TB_Swim.setSwim_CommentTS(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.SWIM_COMMENTTS)));
                data_TB_Swim.setSwim_endtime(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.SWIM_ENDTIME)));
                data_TB_Swim.setSwim_MechineType(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIM_MECHINETYPE)));
                data_TB_Swim.setSwim_MechineDeviceID(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIM_MECHINEDEVICEID)));
                data_TB_Swim.setSwim_CommentNote(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIM_COMMENTNOTE)));
                data_TB_Swim.setSwim_CutInTimeDif(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIM_CUTINDIF)));
                data_TB_Swim.setSwim_CutOutTimeDif(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIM_CUTOUTDIF)));
                data_TB_Swim.setSwim_ProcessFlag(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIM_PROCESSFLAG)));
                data_TB_Swim.setSwim_StartTimeStamp(Long.valueOf(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.SWIM_STARTTIME))));
                arrayList.add(data_TB_Swim);
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

    private ArrayList m411g() {
        Cursor selectData = new DataBaseTools(this.context).selectData(DataBaseConstants.TABLE_TB_SWIMSECTION, null, "swimSection_iHealthCloud='" + this.f568a + "'");
        selectData.moveToFirst();
        if (selectData.getCount() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < selectData.getCount(); i++) {
                Data_TB_SwimSection data_TB_SwimSection = new Data_TB_SwimSection();
                data_TB_SwimSection.setSwimSection_iHealthCloud(this.f568a);
                data_TB_SwimSection.setSwimSection_City(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_CITY)));
                data_TB_SwimSection.setSwimSection_DeviceID(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_DEVICEID)));
                data_TB_SwimSection.setSwimSection_DeviceSource(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_DEVICESOURCE)));
                data_TB_SwimSection.setSwimSection_Lat(selectData.getDouble(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_LAT)));
                data_TB_SwimSection.setSwimSection_Lon(selectData.getDouble(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_LON)));
                data_TB_SwimSection.setSwimSection_LastChangeTime(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_LASTCHANGETIME)));
                data_TB_SwimSection.setSwimSection_Note(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_NOTE)));
                data_TB_SwimSection.setSwimSection_NoteTS(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_NOTETS)));
                data_TB_SwimSection.setSwimSection_SwimCoastTime(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_SWIMCOASTTIME)));
                data_TB_SwimSection.setSwimSection_Endtime(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_ENDTIME)));
                data_TB_SwimSection.setSwimSection_StartTime(selectData.getLong(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_STARTTIME)));
                data_TB_SwimSection.setSwimSection_PoolLength(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_POOLLENGTH)));
                data_TB_SwimSection.setSwimSection_DataID(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_DATAID)));
                data_TB_SwimSection.setSwimSection_SpendTimeBackStroke(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_SPENDTIMEBACKSTROKE)));
                data_TB_SwimSection.setSwimSection_SpendTimeBreastStroke(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_SPENDTIMEBREASTSTROKE)));
                data_TB_SwimSection.setSwimSection_SpendTimeFreeStroke(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_SPENDTIMEFREESTROKE)));
                data_TB_SwimSection.setSwimSection_SpendTimeUnrecognized(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_SPENDTIMEUNRECOGNIZED)));
                data_TB_SwimSection.setSwimSection_SumCalories(selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_SUMCALORIES)));
                data_TB_SwimSection.setSwimSection_SumThrashTimes(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_SUMTHRASHTIMES)));
                data_TB_SwimSection.setSwimSection_SumTripCount(selectData.getInt(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_SUMTRIPCOUNT)));
                data_TB_SwimSection.setSwimSection_TimeZone(selectData.getFloat(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_TIMEZONE)));
                data_TB_SwimSection.setSwimSection_Weather(selectData.getString(selectData.getColumnIndex(DataBaseConstants.SWIMSECTION_WEATHER)));
                arrayList.add(data_TB_SwimSection);
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
        this.task = new C2055a(this);
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
