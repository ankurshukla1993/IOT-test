package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.ihealth.communication.utils.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class DataBaseTools {
    private static String f614a = "DataBaseTools";
    private SQLiteDatabase f615b;

    public DataBaseTools(Context context) {
        this.f615b = DataBaseOpenHelper.getInstance(context);
    }

    public Boolean addData(String tableName, Object obj) {
        Boolean valueOf;
        Boolean.valueOf(false);
        String str = "";
        if (tableName.equals(DataBaseConstants.TABLE_TB_AM_ACTIVITY)) {
            str = "";
            str = "insert into TB_AM_Activity (ChangeType,LastChangeTime,PhoneDataID,PhoneCreateTime,Lat,Lon,TimeZone,Calorie,StepLength,Steps,SumCalorie,SumSteps,MeasureTime,MechineType,MechineDeviceID,iHealthID)VALUES(" + ((Data_AM_Activity) obj).getChangeType() + ", " + ((Data_AM_Activity) obj).getLastChangeTime() + ", '" + ((Data_AM_Activity) obj).getPhoneDataID() + "', " + ((Data_AM_Activity) obj).getPhoneCreateTime() + ", " + ((Data_AM_Activity) obj).getLat() + ", " + ((Data_AM_Activity) obj).getLon() + ", " + ((Data_AM_Activity) obj).getTimeZone() + ", " + ((Data_AM_Activity) obj).getCalorie() + ", " + ((Data_AM_Activity) obj).getStepLength() + ", " + ((Data_AM_Activity) obj).getSteps() + ", " + ((Data_AM_Activity) obj).getSumCalorie() + ", " + ((Data_AM_Activity) obj).getSumSteps() + ", " + ((Data_AM_Activity) obj).getMeasureTime() + ", '" + ((Data_AM_Activity) obj).getMechineType() + "', '" + ((Data_AM_Activity) obj).getMechineDeviceID() + "', '" + ((Data_AM_Activity) obj).getiHealthID() + "');";
        }
        if (tableName.equals(DataBaseConstants.TABLE_TB_AM_ACTIVITYREPORT)) {
            str = "";
            str = "insert into TB_AM_ActivityReport (ChangeType,LastChangeTime,PhoneDataID,PhoneCreateTime,Lat,Lon,TimeZone,Calorie,StepLength,Steps,PlanSteps,PlanCalorie,City,Weather,Comment,MeasureTime,MechineType,MechineDeviceID,iHealthID)VALUES(" + ((Data_AM_ActivityDayReport) obj).getChangeType() + ", " + ((Data_AM_ActivityDayReport) obj).getLastChangeTime() + ", '" + ((Data_AM_ActivityDayReport) obj).getPhoneDataID() + "', " + ((Data_AM_ActivityDayReport) obj).getPhoneCreateTime() + ", " + ((Data_AM_ActivityDayReport) obj).getLat() + ", " + ((Data_AM_ActivityDayReport) obj).getLon() + ", " + ((Data_AM_ActivityDayReport) obj).getTimeZone() + ", " + ((Data_AM_ActivityDayReport) obj).getCalorie() + ", " + ((Data_AM_ActivityDayReport) obj).getStepLength() + ", " + ((Data_AM_ActivityDayReport) obj).getSteps() + ", " + ((Data_AM_ActivityDayReport) obj).getPlanSteps() + ", " + ((Data_AM_ActivityDayReport) obj).getPlanCalorie() + ", '" + ((Data_AM_ActivityDayReport) obj).getCity() + "', '" + ((Data_AM_ActivityDayReport) obj).getWeather() + "', '" + ((Data_AM_ActivityDayReport) obj).getComment() + "', " + ((Data_AM_ActivityDayReport) obj).getMeasureTime() + ", '" + ((Data_AM_ActivityDayReport) obj).getMechineType() + "', '" + ((Data_AM_ActivityDayReport) obj).getMechineDeviceID() + "', '" + ((Data_AM_ActivityDayReport) obj).getiHealthID() + "');";
        }
        if (tableName.equals(DataBaseConstants.TABLE_TB_AM_SLEEP)) {
            str = "";
            str = "insert into TB_AM_Sleep (ChangeType,LastChangeTime,PhoneDataID,PhoneCreateTime,Lat,Lon,TimeZone,SleepLevel,TimeSectionId,MeasureTime,MechineType,MechineDeviceID,iHealthID)VALUES(" + ((Data_AM_Sleep) obj).getChangeType() + ", " + ((Data_AM_Sleep) obj).getLastChangeTime() + ", '" + ((Data_AM_Sleep) obj).getPhoneDataID() + "', " + ((Data_AM_Sleep) obj).getPhoneCreateTime() + ", " + ((Data_AM_Sleep) obj).getLat() + ", " + ((Data_AM_Sleep) obj).getLon() + ", " + ((Data_AM_Sleep) obj).getTimeZone() + ", " + ((Data_AM_Sleep) obj).getSleepLevel() + ", '" + ((Data_AM_Sleep) obj).getTimeSectionId() + "', " + ((Data_AM_Sleep) obj).getMeasureTime() + ", '" + ((Data_AM_Sleep) obj).getMechineType() + "', '" + ((Data_AM_Sleep) obj).getMechineDeviceID() + "', '" + ((Data_AM_Sleep) obj).getiHealthID() + "');";
        }
        if (tableName.equals(DataBaseConstants.TABLE_TB_AM_SLEEPREPORT)) {
            str = "";
            str = "insert into TB_AM_SleepReport (ChangeType,LastChangeTime,PhoneDataID,PhoneCreateTime,Lat,Lon,TimeZone,Awake,DeepSleep,FallSleep,Sleep,AwakenTimes,SleepStartTime,SleepEndTime,TimeSectionId,Nap,City,Weather,Comment,MeasureTime,MechineType,MechineDeviceID,Mood,Activity,iHealthID)VALUES(" + ((Data_AM_SleepSectionReport) obj).getChangeType() + ", " + ((Data_AM_SleepSectionReport) obj).getLastChangeTime() + ", '" + ((Data_AM_SleepSectionReport) obj).getPhoneDataID() + "', " + ((Data_AM_SleepSectionReport) obj).getPhoneCreateTime() + ", " + ((Data_AM_SleepSectionReport) obj).getLat() + ", " + ((Data_AM_SleepSectionReport) obj).getLon() + ", " + ((Data_AM_SleepSectionReport) obj).getTimeZone() + ", " + ((Data_AM_SleepSectionReport) obj).getAwake() + ", " + ((Data_AM_SleepSectionReport) obj).getDeepSleep() + ", " + ((Data_AM_SleepSectionReport) obj).getFallSleep() + ", " + ((Data_AM_SleepSectionReport) obj).getSleep() + ", " + ((Data_AM_SleepSectionReport) obj).getAwakenTimes() + ", " + ((Data_AM_SleepSectionReport) obj).getSleepStartTime() + ", " + ((Data_AM_SleepSectionReport) obj).getSleepEndTime() + ", '" + ((Data_AM_SleepSectionReport) obj).getTimeSectionId() + "', " + ((Data_AM_SleepSectionReport) obj).getNap() + ", '" + ((Data_AM_SleepSectionReport) obj).getCity() + "', '" + ((Data_AM_SleepSectionReport) obj).getWeather() + "', '" + ((Data_AM_SleepSectionReport) obj).getComment() + "', " + ((Data_AM_SleepSectionReport) obj).getMeasureTime() + ", '" + ((Data_AM_SleepSectionReport) obj).getMechineType() + "', '" + ((Data_AM_SleepSectionReport) obj).getMechineDeviceID() + "', " + ((Data_AM_SleepSectionReport) obj).getMood() + ", " + ((Data_AM_SleepSectionReport) obj).getActivity() + ", '" + ((Data_AM_SleepSectionReport) obj).getiHealthID() + "');";
        }
        if (tableName.equals(DataBaseConstants.TABLE_TB_SWIMSECTION)) {
            str = "insert into TB_SwimSection (swimSection_City,swimSection_DeviceID,swimSection_DeviceSource,swimSection_Lat,swimSection_Lon,swimSection_LastChangeTime,swimSection_Note,swimSection_NoteTS,swimSection_SwimCoastTime,swimSection_Endtime,swimSection_StartTime,swimSection_PoolLength,swimSection_DataID,swimSection_SpendTimeBackStroke,swimSection_SpendTimeBreastStroke,swimSection_SpendTimeFreeStroke,swimSection_SpendTimeUnrecognized,swimSection_SumCalories,swimSection_SumThrashTimes,swimSection_SumTripCount,swimSection_TimeZone,swimSection_Weather,swimSection_iHealthCloud)VALUES('" + ((Data_TB_SwimSection) obj).getSwimSection_City() + "','" + ((Data_TB_SwimSection) obj).getSwimSection_DataID() + "','" + ((Data_TB_SwimSection) obj).getSwimSection_DeviceSource() + "'," + ((Data_TB_SwimSection) obj).getSwimSection_Lat() + "," + ((Data_TB_SwimSection) obj).getSwimSection_Lon() + "," + ((Data_TB_SwimSection) obj).getSwimSection_LastChangeTime() + ",'" + ((Data_TB_SwimSection) obj).getSwimSection_Note().replace("'", "''") + "'," + ((Data_TB_SwimSection) obj).getSwimSection_NoteTS() + "," + ((Data_TB_SwimSection) obj).getSwimSection_SwimCoastTime() + "," + ((Data_TB_SwimSection) obj).getSwimSection_Endtime() + "," + ((Data_TB_SwimSection) obj).getSwimSection_StartTime() + "," + ((Data_TB_SwimSection) obj).getSwimSection_PoolLength() + ",'" + ((Data_TB_SwimSection) obj).getSwimSection_DataID() + "'," + ((Data_TB_SwimSection) obj).getSwimSection_SpendTimeBackStroke() + "," + ((Data_TB_SwimSection) obj).getSwimSection_SpendTimeBreastStroke() + "," + ((Data_TB_SwimSection) obj).getSwimSection_SpendTimeFreeStroke() + "," + ((Data_TB_SwimSection) obj).getSwimSection_SpendTimeUnrecognized() + "," + ((Data_TB_SwimSection) obj).getSwimSection_SumCalories() + "," + ((Data_TB_SwimSection) obj).getSwimSection_SumThrashTimes() + "," + ((Data_TB_SwimSection) obj).getSwimSection_SumTripCount() + "," + ((Data_TB_SwimSection) obj).getSwimSection_TimeZone() + ",'" + ((Data_TB_SwimSection) obj).getSwimSection_Weather().replace("'", "''") + "','" + ((Data_TB_SwimSection) obj).getSwimSection_iHealthCloud().replace("'", "''") + "');";
        }
        if (tableName.equals(DataBaseConstants.TABLE_TB_SWIM)) {
            str = "insert into TB_Swim (swim_ChangeType,swim_LastChangeTime,swim_PhoneDataID,swim_PhoneCreateTime,swim_Lat,swim_Lon,swim_TimeZone,swim_SpendMinutes,swim_PullTimes,swim_Stroke,swim_Cycles,swim_CutInDif,swim_CutOutDif,swim_ProcessFlag,swim_Distance,swim_Calories,swim_City,swim_Temperature,swim_WeatherCode,swim_Humidity,swim_Atmosphere,swim_CommentTS,swim_Endtime,swim_StartTime,swim_CommentPic,swim_MechineType,swim_MechineDeviceID,swim_iHealthCloud,swim_CommentNote)VALUES(" + ((Data_TB_Swim) obj).getSwim_ChangeType() + "," + ((Data_TB_Swim) obj).getSwim_LastChangeTime() + ",'" + ((Data_TB_Swim) obj).getSwim_PhoneDataID() + "'," + ((Data_TB_Swim) obj).getSwim_PhoneCreateTime() + "," + ((Data_TB_Swim) obj).getSwim_Lat() + "," + ((Data_TB_Swim) obj).getSwim_Lon() + "," + ((Data_TB_Swim) obj).getSwim_TimeZone() + "," + ((Data_TB_Swim) obj).getSwim_SpendMinutes() + "," + ((Data_TB_Swim) obj).getSwim_PullTimes() + "," + ((Data_TB_Swim) obj).getSwim_Stroke() + "," + ((Data_TB_Swim) obj).getSwim_Cycles() + "," + ((Data_TB_Swim) obj).getSwim_CutInTimeDif() + "," + ((Data_TB_Swim) obj).getSwim_CutOutTimeDif() + "," + ((Data_TB_Swim) obj).getSwim_ProcessFlag() + "," + ((Data_TB_Swim) obj).getSwim_Distance() + "," + ((Data_TB_Swim) obj).getSwim_Calories() + ",'" + ((Data_TB_Swim) obj).getSwim_City() + "','" + ((Data_TB_Swim) obj).getSwim_Temperature() + "','" + ((Data_TB_Swim) obj).getSwim_WeatherCode() + "','" + ((Data_TB_Swim) obj).getSwim_Humidity() + "','" + ((Data_TB_Swim) obj).getSwim_Atmosphere() + "'," + ((Data_TB_Swim) obj).getSwim_CommentTS() + "," + ((Data_TB_Swim) obj).getSwim_endtime() + "," + ((Data_TB_Swim) obj).getSwim_StartTimeStamp() + ",'" + ((Data_TB_Swim) obj).getSwim_CommentPic() + "','" + ((Data_TB_Swim) obj).getSwim_MechineType() + "','" + ((Data_TB_Swim) obj).getSwim_MechineDeviceID() + "','" + ((Data_TB_Swim) obj).getSwim_iHealthCloud().replace("'", "''") + "','" + ((Data_TB_Swim) obj).getSwim_CommentNote().replace("'", "''") + "');";
        }
        if (tableName.equals(DataBaseConstants.TABLE_TB_WORKOUT)) {
            str = "insert into TB_WorkOut (workout_ChangeType,workout_LastChangeTime,workout_PhoneDataID,workout_PhoneCreateTime,workout_Lat,workout_Lon,workout_TimeZone,workout_SpendMinutes,workout_Steps,workout_Distance,workout_Calories,workout_City,workout_Temperature,workout_WeatherCode,workout_Humidity,workout_Atmosphere,workout_CommentTS,workout_Endtime,workout_CommentPic,workout_MechineType,workout_MechineDeviceID,workout_iHealthCloud,workout_CommentNote)VALUES(" + ((Data_TB_Workout) obj).getWorkout_ChangeType() + "," + ((Data_TB_Workout) obj).getWorkout_LastChangeTime() + ",'" + ((Data_TB_Workout) obj).getWorkout_PhoneDataID() + "'," + ((Data_TB_Workout) obj).getWorkout_PhoneCreateTime() + "," + ((Data_TB_Workout) obj).getWorkout_Lat() + "," + ((Data_TB_Workout) obj).getWorkout_Lon() + "," + ((Data_TB_Workout) obj).getWorkout_TimeZone() + "," + ((Data_TB_Workout) obj).getWorkout_SpendMinutes() + "," + ((Data_TB_Workout) obj).getWorkout_Steps() + "," + ((Data_TB_Workout) obj).getWorkout_Distance() + "," + ((Data_TB_Workout) obj).getWorkout_Calories() + ",'" + ((Data_TB_Workout) obj).getWorkout_City() + "','" + ((Data_TB_Workout) obj).getWorkout_Temperature() + "','" + ((Data_TB_Workout) obj).getWorkout_WeatherCode() + "','" + ((Data_TB_Workout) obj).getWorkout_Humidity() + "','" + ((Data_TB_Workout) obj).getWorkout_Atmosphere() + "'," + ((Data_TB_Workout) obj).getWorkout_CommentTS() + "," + ((Data_TB_Workout) obj).getWorkout_endtime() + ",'" + ((Data_TB_Workout) obj).getWorkout_CommentPic() + "','" + ((Data_TB_Workout) obj).getWorkout_MechineType() + "','" + ((Data_TB_Workout) obj).getWorkout_MechineDeviceID() + "','" + ((Data_TB_Workout) obj).getWorkout_iHealthCloud().replace("'", "''") + "','" + ((Data_TB_Workout) obj).getWorkout_CommentNote().replace("'", "''") + "');";
        }
        if (tableName.equals(DataBaseConstants.TABLE_TB_BGRESULT)) {
            str = "";
            str = "insert into TB_BGResult (ChangeType,LastChangeTime,PhoneDataID,PhoneCreateTime,Lat,Lon,TimeZone,BGValue,Medication,MTimeType,MeasureType,MeasureTime,Note,MechineType,MechineDeviceID,BottleId,Sports,Snacks,Effective,iHealthID)VALUES(" + ((Data_BG_Result) obj).getChangeType() + ", " + ((Data_BG_Result) obj).getLastChangeTime() + ", '" + ((Data_BG_Result) obj).getPhoneDataID() + "', " + ((Data_BG_Result) obj).getPhoneCreateTime() + ", " + ((Data_BG_Result) obj).getLat() + ", " + ((Data_BG_Result) obj).getLon() + ", " + ((Data_BG_Result) obj).getTimeZone() + ", " + ((Data_BG_Result) obj).getBGValue() + ", " + ((Data_BG_Result) obj).getMedication() + ", " + ((Data_BG_Result) obj).getMTimeType() + ", " + ((Data_BG_Result) obj).getMeasureType() + ", " + ((Data_BG_Result) obj).getMeasureTime() + ", '" + ((Data_BG_Result) obj).getNote() + "', '" + ((Data_BG_Result) obj).getMechineType() + "', '" + ((Data_BG_Result) obj).getMechineDeviceID() + "', '" + ((Data_BG_Result) obj).getBottleId() + "', " + ((Data_BG_Result) obj).getSports() + ", " + ((Data_BG_Result) obj).getSnacks() + ", " + ((Data_BG_Result) obj).getEffective() + ", '" + ((Data_BG_Result) obj).getiHealthID() + "');";
        }
        if (tableName.equals(DataBaseConstants.TABLE_TB_BPMEASURERESULT)) {
            str = "";
            str = "insert into TB_BPMeasureResult (bpChangeType,bpLastChangeTime,bpPhoneDataID,bpPhoneCreateTime,bpLat,bpLon,bpTimeZone,bpBPL,bpLP,bpHP,bpHR,bpIsArr,bpWL,bpMeasureType,bpMeasureTime,bpNote,bpMechineType,bpMechineDeviceID,bpNoteTS,bpMood,bpActivity,temp,weather,humidity,visibility,UsedUserid,bpun)VALUES(" + ((Data_BP_Result) obj).getChangeType() + ", " + ((Data_BP_Result) obj).getLastChangeTime() + ", '" + ((Data_BP_Result) obj).getPhoneDataID() + "', " + ((Data_BP_Result) obj).getPhoneCreateTime() + ", " + ((Data_BP_Result) obj).getLat() + ", " + ((Data_BP_Result) obj).getLon() + ", " + ((Data_BP_Result) obj).getTimeZone() + ", " + ((Data_BP_Result) obj).getBPL() + ", " + ((Data_BP_Result) obj).getLP() + ", " + ((Data_BP_Result) obj).getHP() + ", " + ((Data_BP_Result) obj).getHR() + ", " + ((Data_BP_Result) obj).getIsArr() + ", '" + ((Data_BP_Result) obj).getWL() + "', " + ((Data_BP_Result) obj).getMeasureType() + ", " + ((Data_BP_Result) obj).getMeasureTime() + ", '" + ((Data_BP_Result) obj).getNote() + "', '" + ((Data_BP_Result) obj).getMechineType() + "', '" + ((Data_BP_Result) obj).getMechineDeviceID() + "', " + ((Data_BP_Result) obj).getNoteTS() + ", " + ((Data_BP_Result) obj).getBpMood() + ", " + ((Data_BP_Result) obj).getBpActivity() + ", '" + ((Data_BP_Result) obj).getTemp() + "', '" + ((Data_BP_Result) obj).getWeather() + "', '" + ((Data_BP_Result) obj).getHumidity() + "', '" + ((Data_BP_Result) obj).getVisibility() + "', " + ((Data_BP_Result) obj).getUsedUserid() + ", '" + ((Data_BP_Result) obj).getiHealthCloud() + "');";
        }
        if (tableName.equals(DataBaseConstants.TABLE_TB_HSRESULT)) {
            str = "";
            str = "insert into TB_HSResult (ChangeType,LastChangeTime,PhoneDataID,PhoneCreateTime,Lat,Lon,TimeZone,MeasureType,MeasureTime,Note,MechineType,MechineDeviceID,BMI,BoneValue,DCI,FatValue,MuscaleValue,WaterValue,WeightValue,VisceraFatLevel,Emotions,NoteTS,Mood,Activity,Temp,Weather,Humidity,Visibility,UsedUserid,iHealthID)VALUES(" + ((Data_HS_Result) obj).getChangeType() + ", " + ((Data_HS_Result) obj).getLastChangeTime() + ", '" + ((Data_HS_Result) obj).getPhoneDataID() + "', " + ((Data_HS_Result) obj).getPhoneCreateTime() + ", " + ((Data_HS_Result) obj).getLat() + ", " + ((Data_HS_Result) obj).getLon() + ", " + ((Data_HS_Result) obj).getTimeZone() + ", " + ((Data_HS_Result) obj).getMeasureType() + ", " + ((Data_HS_Result) obj).getMeasureTime() + ", '" + ((Data_HS_Result) obj).getNote() + "', '" + ((Data_HS_Result) obj).getMechineType() + "', '" + ((Data_HS_Result) obj).getMechineDeviceID() + "', " + ((Data_HS_Result) obj).getBMI() + ", " + ((Data_HS_Result) obj).getBoneValue() + ", " + ((Data_HS_Result) obj).getDCI() + ", " + ((Data_HS_Result) obj).getFatValue() + ", " + ((Data_HS_Result) obj).getMuscaleValue() + ", " + ((Data_HS_Result) obj).getWaterValue() + ", " + ((Data_HS_Result) obj).getWeightValue() + ", " + ((Data_HS_Result) obj).getVisceraFatLevel() + ", " + ((Data_HS_Result) obj).getEmotions() + ", " + ((Data_HS_Result) obj).getNoteTS() + ", " + ((Data_HS_Result) obj).getMood() + ", " + ((Data_HS_Result) obj).getActivity() + ", '" + ((Data_HS_Result) obj).getTemp() + "', '" + ((Data_HS_Result) obj).getWeather() + "', '" + ((Data_HS_Result) obj).getHumidity() + "', '" + ((Data_HS_Result) obj).getVisibility() + "', " + ((Data_HS_Result) obj).getUsedUserid() + ", '" + ((Data_HS_Result) obj).getiHealthID() + "');";
        }
        if (tableName.equals(DataBaseConstants.TABLE_TB_PO)) {
            try {
                str = "insert into TB_PO (ChangeType,LastChangeTime,PhoneDataID,PhoneCreateTime,Lat,Lon,TimeZone,Activity,Wave,PR,Result,FlowRate,ResultSource,Note,NoteTS,MeasureTime,MechineType,MechineDeviceID,Mood,iHealthID)VALUES(" + ((Data_PO_Result) obj).getChangeType() + "," + ((Data_PO_Result) obj).getLastChangeTime() + ",'" + ((Data_PO_Result) obj).getPhoneDataID() + "'," + ((Data_PO_Result) obj).getPhoneCreateTime() + "," + ((Data_PO_Result) obj).getLat() + "," + ((Data_PO_Result) obj).getLon() + "," + ((Data_PO_Result) obj).getTimeZone() + "," + ((Data_PO_Result) obj).getActivity() + ",'" + ((Data_PO_Result) obj).getWave() + "'," + ((Data_PO_Result) obj).getPR() + "," + ((Data_PO_Result) obj).getResult() + "," + ((Data_PO_Result) obj).getFlowrate() + "," + ((Data_PO_Result) obj).getResultSource() + ",'" + ((Data_PO_Result) obj).getNote().replace("'", "''") + "'," + ((Data_PO_Result) obj).getNoteTS() + "," + ((Data_PO_Result) obj).getMeasureTime() + ",'" + ((Data_PO_Result) obj).getMechineType() + "','" + ((Data_PO_Result) obj).getMechineDeviceID() + "'," + ((Data_PO_Result) obj).getMood() + ",'" + ((Data_PO_Result) obj).getiHealthID().replace("'", "''") + "');";
            } catch (Exception e) {
                e.printStackTrace();
                return Boolean.valueOf(false);
            }
        }
        try {
            this.f615b.beginTransaction();
            this.f615b.execSQL(str);
            this.f615b.setTransactionSuccessful();
            valueOf = Boolean.valueOf(true);
        } catch (SQLException e2) {
            e2.printStackTrace();
            valueOf = Boolean.valueOf(false);
        } finally {
            this.f615b.endTransaction();
        }
        return valueOf;
    }

    public Boolean deleteAllTableData() {
        Boolean valueOf = Boolean.valueOf(false);
        Cursor rawQuery = this.f615b.rawQuery("select name from sqlite_master where type='table' order by name", null);
        while (rawQuery.moveToNext()) {
            String string = rawQuery.getString(0);
            String str = "DELETE FROM  " + string;
            Log.i(f614a, "删除所有表的数据name：" + string);
            Log.i(f614a, "删除所有表的数据sql：" + str);
            this.f615b.beginTransaction();
            try {
                this.f615b.execSQL(str);
                valueOf = Boolean.valueOf(true);
                this.f615b.setTransactionSuccessful();
            } catch (SQLException e) {
                e.printStackTrace();
                valueOf = Boolean.valueOf(false);
                Log.e(f614a, "删除表的数据SQLException:" + string);
            } finally {
                rawQuery = this.f615b;
                rawQuery.endTransaction();
            }
        }
        rawQuery.close();
        return valueOf;
    }

    public Boolean deleteData(String tableName) {
        Boolean valueOf;
        Boolean.valueOf(false);
        String str = "";
        str = "DELETE FROM  " + tableName;
        this.f615b.beginTransaction();
        try {
            this.f615b.execSQL(str);
            valueOf = Boolean.valueOf(true);
            this.f615b.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            valueOf = Boolean.valueOf(false);
        } finally {
            this.f615b.endTransaction();
        }
        return valueOf;
    }

    public Boolean deleteData(String tableName, String conditionStr) {
        Boolean valueOf;
        Boolean.valueOf(false);
        String str = "";
        str = (conditionStr == null || conditionStr.length() <= 0) ? "DELETE FROM  " + tableName : "DELETE FROM  " + tableName + " where " + conditionStr;
        this.f615b.beginTransaction();
        try {
            this.f615b.execSQL(str);
            valueOf = Boolean.valueOf(true);
            this.f615b.setTransactionSuccessful();
        } catch (SQLException e) {
            e.printStackTrace();
            valueOf = Boolean.valueOf(false);
        } finally {
            this.f615b.endTransaction();
        }
        return valueOf;
    }

    public Boolean dsa1() {
        Boolean valueOf = Boolean.valueOf(false);
        Cursor rawQuery = this.f615b.rawQuery("select name from sqlite_master where type='table' order by name", null);
        while (rawQuery.moveToNext()) {
            Log.i("System.out", rawQuery.getString(0));
        }
        rawQuery.close();
        return valueOf;
    }

    public Cursor selectData(String tableName, String[] columns, String conditionStr) {
        Cursor query;
        SQLException e;
        this.f615b.beginTransaction();
        try {
            query = this.f615b.query(tableName, columns, conditionStr, null, null, null, null);
            try {
                this.f615b.setTransactionSuccessful();
                this.f615b.endTransaction();
            } catch (SQLException e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    return query;
                } finally {
                    this.f615b.endTransaction();
                }
            }
        } catch (SQLException e3) {
            e = e3;
            query = null;
            e.printStackTrace();
            return query;
        }
        return query;
    }

    public Cursor selectDataOrderBy(String tableName, String[] columns, String conditionStr, String orderBy) {
        Cursor query;
        SQLException e;
        this.f615b.beginTransaction();
        try {
            query = this.f615b.query(tableName, columns, conditionStr, null, null, null, orderBy);
            try {
                this.f615b.setTransactionSuccessful();
                this.f615b.endTransaction();
            } catch (SQLException e2) {
                e = e2;
                try {
                    e.printStackTrace();
                    return query;
                } finally {
                    this.f615b.endTransaction();
                }
            }
        } catch (SQLException e3) {
            e = e3;
            query = null;
            e.printStackTrace();
            return query;
        }
        return query;
    }

    public Boolean updateData(String tableName, String conditionStr, String valueStr) {
        Boolean valueOf;
        Log.i(tableName, "tableName:" + tableName);
        Boolean.valueOf(false);
        String str = "";
        str = conditionStr.length() > 0 ? "UPDATE " + tableName + " SET " + valueStr + " where " + conditionStr + ";" : "UPDATE " + tableName + " SET " + valueStr;
        Log.i(f614a, "sql:" + str);
        this.f615b.beginTransaction();
        try {
            this.f615b.execSQL(str);
            valueOf = Boolean.valueOf(true);
            this.f615b.setTransactionSuccessful();
        } catch (SQLException e) {
            Log.e(f614a, "Update sql 进入Catch");
            Writer stringWriter = new StringWriter();
            e.printStackTrace(new PrintWriter(stringWriter));
            Log.e(f614a, "catch = " + stringWriter.getBuffer().toString());
            e.printStackTrace();
            valueOf = Boolean.valueOf(false);
        } finally {
            this.f615b.endTransaction();
        }
        return valueOf;
    }
}
