package com.ihealth.communication.cloud.data;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.ihealth.communication.utils.Log;

public class DataBaseOpenHelper extends SQLiteOpenHelper {
    private static DataBaseOpenHelper f611b;
    private static SQLiteDatabase f612c;
    private Context f613a;

    private DataBaseOpenHelper(Context context) {
        super(context, "iHealthLibrarySDK.DB", null, 4);
        this.f613a = context;
    }

    private void m430a(SQLiteDatabase sQLiteDatabase) {
        String str = "";
        str = "CREATE TABLE IF NOT EXISTS TB_AM_Activity (ChangeType int(4,0) default 1,LastChangeTime long(25,0),PhoneDataID varchar(128,0),PhoneCreateTime long(25,0),Lat double(64,0) default 0.0,Lon double(64,0) default 0.0,TimeZone float(8,0),Calorie float(16,0) default 0.0,StepLength int(4,0) default 0,Steps int(4,0) default 0,SumCalorie int(4,0) default 0,SumSteps int(4,0) default 0,MeasureTime long(25,0),MechineType varchar(128,0),MechineDeviceID varchar(128,0),iHealthID varchar(128,0));";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void m431b(SQLiteDatabase sQLiteDatabase) {
        String str = "";
        str = "CREATE TABLE IF NOT EXISTS TB_AM_ActivityReport (ChangeType int(4,0) default 1,LastChangeTime long(25,0),PhoneDataID varchar(128,0),PhoneCreateTime long(25,0),Lat double(64,0) default 0.0,Lon double(64,0) default 0.0,TimeZone float(8,0),Calorie float(16,0) default 0.0,StepLength int(4,0) default 0,Steps int(4,0) default 0,PlanSteps int(4,0) default 0,PlanCalorie float(16,0) default 0.0,City varchar(128,0),Weather varchar(128,0),Comment varchar(128,0),MeasureTime long(25,0),MechineType varchar(128,0),MechineDeviceID varchar(128,0),iHealthID varchar(128,0));";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void m432c(SQLiteDatabase sQLiteDatabase) {
        String str = "";
        str = "CREATE TABLE IF NOT EXISTS TB_AM_Sleep (ChangeType int(4,0) default 1,LastChangeTime long(25,0),PhoneDataID varchar(128,0),PhoneCreateTime long(25,0),Lat double(64,0) default 0.0,Lon double(64,0) default 0.0,TimeZone float(8,0),SleepLevel int(4,0) default 0,TimeSectionId varchar(128,0),MeasureTime long(25,0),MechineType varchar(128,0),MechineDeviceID varchar(128,0),iHealthID varchar(128,0));";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void m433d(SQLiteDatabase sQLiteDatabase) {
        String str = "";
        str = "CREATE TABLE IF NOT EXISTS TB_AM_SleepReport (ChangeType int(4,0) default 1,LastChangeTime long(25,0),PhoneDataID varchar(128,0),PhoneCreateTime long(25,0),Lat double(64,0) default 0.0,Lon double(64,0) default 0.0,TimeZone float(8,0),Awake int(4,0) default 0,DeepSleep int(4,0) default 0,FallSleep int(4,0) default 0,Sleep int(4,0) default 0,AwakenTimes int(4,0) default 0,SleepStartTime long(25,0),SleepEndTime long(25,0),TimeSectionId varchar(128,0),Nap int(4,0) default 0,City varchar(128,0),Weather varchar(128,0),Comment varchar(128,0),Mood int(4,0) default 0,Activity int(4,0) default 0,MeasureTime long(25,0),MechineType varchar(128,0),MechineDeviceID varchar(128,0),iHealthID varchar(128,0));";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void m434e(SQLiteDatabase sQLiteDatabase) {
        String str = "";
        str = "CREATE TABLE IF NOT EXISTS TB_WorkOut (workout_ChangeType int(4,0),workout_LastChangeTime long(10,0),workout_PhoneDataID varchar(128,0),workout_PhoneCreateTime long(10,0),workout_Lat double(4,0),workout_Lon double(4,0),workout_TimeZone float(12,0),workout_SpendMinutes int(4,0),workout_Steps int(4,0),workout_Distance int(4,0),workout_Calories float(12,0),workout_City varchar(128,0),workout_Temperature varchar(128,0),workout_WeatherCode varchar(128,0),workout_Humidity varchar(128,0),workout_Atmosphere varchar(128,0),workout_CommentTS varchar(128,0),workout_CommentPic varchar(128,0),workout_Endtime long(10,0),workout_MechineType varchar(128,0),workout_MechineDeviceID varchar(128,0),workout_iHealthCloud varchar(128,0),workout_CommentNote varchar(200,0));";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void m435f(SQLiteDatabase sQLiteDatabase) {
        String str = "";
        str = "CREATE TABLE IF NOT EXISTS TB_Swim (swim_ChangeType int(4,0),swim_LastChangeTime long(10,0),swim_PhoneDataID varchar(128,0),swim_PhoneCreateTime long(10,0),swim_Lat double(4,0),swim_Lon double(4,0),swim_TimeZone float(12,0),swim_SpendMinutes int(4,0),swim_PullTimes int(4,0),swim_Stroke int(4,0),swim_Cycles int(4,0),swim_Distance int(4,0),swim_Calories float(12,0),swim_City varchar(128,0),swim_Temperature varchar(128,0),swim_WeatherCode varchar(128,0),swim_Humidity varchar(128,0),swim_Atmosphere varchar(128,0),swim_CommentTS varchar(128,0),swim_CommentPic varchar(128,0),swim_Endtime long(10,0),swim_StartTime long(10,0),swim_MechineType varchar(128,0),swim_MechineDeviceID varchar(128,0),swim_iHealthCloud varchar(128,0),swim_CommentNote varchar(200,0),swim_CutInDif int(4,0),swim_CutOutDif int(4,0),swim_ProcessFlag int(4,0));";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void m436g(SQLiteDatabase sQLiteDatabase) {
        String str = "";
        str = "CREATE TABLE IF NOT EXISTS TB_SwimSection (swimSection_City varchar(128,0),swimSection_DeviceID varchar(128,0),swimSection_DeviceSource varchar(128,0),swimSection_Lat double(4,0),swimSection_Lon double(4,0),swimSection_LastChangeTime long(10,0),swimSection_Note varchar(200,0),swimSection_NoteTS long(10,0),swimSection_SwimCoastTime int(4,0),swimSection_Endtime long(10,0),swimSection_StartTime long(10,0),swimSection_PoolLength int(4,0),swimSection_DataID varchar(128,0),swimSection_SpendTimeBackStroke int(4,0),swimSection_SpendTimeBreastStroke int(4,0),swimSection_SpendTimeFreeStroke int(4,0),swimSection_SpendTimeUnrecognized int(4,0),swimSection_SumCalories float(12,0),swimSection_SumThrashTimes int(4,0),swimSection_SumTripCount int(4,0),swimSection_TimeZone float(12,0),swimSection_Weather varchar(128,0),swimSection_iHealthCloud varchar(128,0));";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public static SQLiteDatabase getInstance(Context context) {
        if (f611b == null) {
            synchronized (DataBaseOpenHelper.class) {
                if (f611b == null) {
                    f611b = new DataBaseOpenHelper(context);
                }
            }
        }
        if (f612c == null) {
            synchronized (DataBaseOpenHelper.class) {
                if (f612c == null && f611b != null) {
                    f612c = f611b.getWritableDatabase();
                }
            }
        }
        return f612c;
    }

    private void m437h(SQLiteDatabase sQLiteDatabase) {
        String str = "";
        str = "CREATE TABLE IF NOT EXISTS TB_BGResult (ChangeType int(4,0) default 1,LastChangeTime long(25,0),PhoneDataID varchar(128,0),PhoneCreateTime long(25,0),Lat double(64,0) default 0.0,Lon double(64,0) default 0.0,TimeZone float(8,0),BGValue float(16,0) default 60.0,Medication int(4,0) default 0,MTimeType int(4,0) default 1,MeasureType int(4,0) default 0,MeasureTime long(25,0),Note  varchar(128,0),MechineType  varchar(128,0),MechineDeviceID  varchar(128,0),BottleId  varchar(128,0),Sports  int(4,0) default 1,Snacks  int(4,0) default 1,Effective  int(4,0) default 1,iHealthID  varchar(128,0));";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void m438i(SQLiteDatabase sQLiteDatabase) {
        String str = "";
        str = "CREATE TABLE IF NOT EXISTS TB_BPMeasureResult (bpChangeType int(4,0) default 1 ,bpLastChangeTime long(10,0) ,bpPhoneDataID varchar(128,0) ,bpPhoneCreateTime long(10,0) ,bpLat int(4,0) default 0 ,bpLon int(4,0) default 0 ,bpTimeZone int(4,0) default 0 ,bpBPL int(4,0) default 0 ,bpLP int(4,0) default 0 ,bpHP int(4,0) default 0 ,bpHR int(4,0) default 0 ,bpIsArr int(4,0) default 0 ,bpWL varchar(1024,0) ,bpMeasureType int(4,0) default 0 ,bpMeasureTime long(10,0) ,bpNote varchar(1024,0) ,bpMechineType varchar(128,0) ,bpMechineDeviceID varchar(128,0) ,bpNoteTS long(10,0) ,bpMood int(4,0) default 0 ,bpActivity int(4,0) default 0 ,temp varchar(128,0) ,weather varchar(128,0) ,humidity varchar(128,0) ,visibility varchar(128,0) ,UsedUserid int(4,0) default 0 ,bpun varchar(128,0));";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void m439j(SQLiteDatabase sQLiteDatabase) {
        String str = "";
        str = "CREATE TABLE IF NOT EXISTS TB_HSResult (ChangeType int(4,0) default 1,LastChangeTime long(25,0),PhoneDataID varchar(128,0),PhoneCreateTime long(25,0),Lat double(64,0) default 0.0,Lon double(64,0) default 0.0,TimeZone float(8,0),BMI float(16,0) default 0.0,BoneValue float(16,0) default 0.0,DCI float(16,0) default 0.0,FatValue float(16,0) default 0.0,MuscaleValue float(16,0) default 0.0,WaterValue float(16,0) default 0.0,WeightValue float(16,0) default 0.0,VisceraFatLevel int(4,0) default 0,Emotions int(4,0) default 0,NoteTS long(25,0),Mood int(4,0) default 0,Activity int(4,0) default 0,Temp  varchar(128,0),Weather  varchar(128,0),Humidity  varchar(128,0),Visibility  varchar(128,0),UsedUserid int(4,0) default 0,MeasureType int(4,0) default 0,MeasureTime long(25,0),Note  varchar(128,0),MechineType  varchar(128,0),MechineDeviceID  varchar(128,0),iHealthID  varchar(128,0));";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void m440k(SQLiteDatabase sQLiteDatabase) {
        String str = "";
        str = "CREATE TABLE IF NOT EXISTS TB_PO (ChangeType int(4,0) default 1,LastChangeTime long(25,0),PhoneDataID varchar(128,0),PhoneCreateTime long(25,0),Lat double(64,0) default 0.0,Lon double(64,0) default 0.0,TimeZone float(8,0),Activity int(4,0) default 0,Wave varchar(128,0),PR int(4,0) default 0,Result int(4,0) default 0,FlowRate int(4,0) default 0,ResultSource int(4,0) default 0,Mood int(4,0) default 0,Weather varchar(128,0),Note varchar(128,0),NoteTS long(25,0),MeasureTime long(25,0),MechineType varchar(128,0),MechineDeviceID varchar(128,0),iHealthID varchar(128,0));";
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL(str);
            sQLiteDatabase.setTransactionSuccessful();
        } catch (SQLException e) {
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    public Boolean dropTable(SQLiteDatabase db) {
        Boolean valueOf = Boolean.valueOf(false);
        Cursor rawQuery = db.rawQuery("select name from sqlite_master where type='table' order by name", null);
        while (rawQuery.moveToNext()) {
            String string = rawQuery.getString(0);
            Log.i("TAG", "dropTable  = " + string);
            try {
                db.execSQL("DROP TABLE  " + string);
                valueOf = Boolean.valueOf(true);
            } catch (SQLException e) {
                e.printStackTrace();
                valueOf = Boolean.valueOf(false);
            }
        }
        return valueOf;
    }

    public void onCreate(SQLiteDatabase db) {
        Log.d("DataBaseOpenHelper", "DataBaseOpenHelper onCreate()");
        tableCreat(db);
    }

    public void onUpgrade(SQLiteDatabase myDataBase, int oldVersion, int newVersion) {
        Log.d("DataBaseOpenHelper", "onUpgrade() oldVersion:" + oldVersion + " newVersion:" + newVersion);
        dropTable(myDataBase);
        tableCreat(myDataBase);
    }

    public void tableCreat(SQLiteDatabase db) {
        Log.d("DataBaseOpenHelper", "tableCreat()  start");
        m430a(db);
        m431b(db);
        m432c(db);
        m433d(db);
        m434e(db);
        m435f(db);
        m436g(db);
        m437h(db);
        m438i(db);
        m439j(db);
        m440k(db);
        Log.d("DataBaseOpenHelper", "tableCreat()  end");
    }
}
