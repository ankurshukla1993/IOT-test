package com.cooey.devices.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CooeySQLHelper extends SQLiteOpenHelper {
    public static final String COLUMN_AREA = "area";
    public static final String COLUMN_BGRP = "bloodGroup";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_COUNTRY = "country";
    public static final String COLUMN_DOB = "dob";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_FNAME = "firstname";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_HT = "height";
    public static final String COLUMN_LNAME = "lastname";
    public static final String COLUMN_NICKN = "nickname";
    public static final String COLUMN_PARENT = "parentId";
    public static final String COLUMN_PH = "phoneNumber";
    public static final String COL_ATHLETELEVEL = "athleteLevel";
    public static final String COL_BCOMP = "basecomponent";
    public static final String COL_BMI = "bmi";
    public static final String COL_BONE = "bonedensity";
    public static final String COL_DATE = "date";
    public static final String COL_DIA = "diastolic";
    public static final String COL_DOSAGE = "dosage";
    public static final String COL_DOSAGE_UNIT = "dosageunit";
    public static final String COL_EXT_ID = "externalId";
    public static final String COL_FAT = "fatratio";
    public static final String COL_FREQ = "usagefreq";
    public static final String COL_GOALWT = "goalWeight";
    public static final String COL_ID = "_id";
    public static final String COL_IMP = "impedance";
    public static final String COL_ISATHLETE = "isAthlete";
    public static final String COL_ISLOGGED = "isloggedin";
    public static final String COL_ISREMIND = "isreminder";
    public static final String COL_MEDID = "medicineId";
    public static final String COL_MEDNAME = "medname";
    public static final String COL_MEDTYPE = "type";
    public static final String COL_MUS = "muscleratio";
    public static final String COL_ONBOARDWT = "onboardweight";
    public static final String COL_PAR_IwD = "parentid";
    public static final String COL_PATIENTID = "patientid";
    public static final String COL_PUL = "pulse";
    public static final String COL_REMINDER = "reminders";
    public static final String COL_SYS = "systolic";
    public static final String COL_WAISTLINE = "waistline";
    public static final String COL_WT = "weight";
    public static final String COL_WTR = "waterratio";
    public static final String COL_WTUNITS = "weightunits";
    private static final String DATABASE_NAME = "cooeyprofile.db";
    private static final int DATABASE_VERSION = 1;
    public static final String KEY_DEVICE_ADDRESS = "device_address";
    public static final String KEY_DEVICE_BROADCASTID = "device_brocastID";
    public static final String KEY_DEVICE_FIRMWARE_VERSION = "firmware_version";
    public static final String KEY_DEVICE_HARDWARE_VERSION = "hardware_version";
    public static final String KEY_DEVICE_ID = "device_id";
    public static final String KEY_DEVICE_MANUFACTURENAME = "manufacture_name";
    public static final String KEY_DEVICE_MODEL = "device_model";
    public static final String KEY_DEVICE_MODELNUMBER = "device_modelnumber";
    public static final String KEY_DEVICE_NAME = "device_name";
    public static final String KEY_DEVICE_PAIRFLAGS = "device_pairflags";
    public static final String KEY_DEVICE_PASSWORD = "device_password";
    public static final String KEY_DEVICE_SERVICE_UUID = "service_uuid";
    public static final String KEY_DEVICE_SN = "device_sn";
    public static final String KEY_DEVICE_SOFTWARE_VERSION = "software_version";
    public static final String KEY_DEVICE_STATUS = "device_status";
    public static final String KEY_DEVICE_SYSTEMID = "device_systemID";
    public static final String KEY_DEVICE_TYPE = "device_type";
    public static final String KEY_DEVICE_USER_NAME = "user_name";
    public static final String KEY_DEVICE_USER_NUMBER = "user_number";
    public static final String KEY_MAX_USER_QUANTITY = "max_user_number";
    public static final String KEY_PAIRSTATUS = "pair_status";
    public static final String KEY_PROTOCOL_TYPE = "protocol_type";
    public static final String TABLE_BP = "cooeyuserbp";
    private static final String TABLE_BP_CREATE = "create table cooeyuserbp(_id integer primary key autoincrement, uid integer, systolic float(3,2), diastolic float(3,2), pulse float(3,2), date DATETIME DEFAULT CURRENT_TIMESTAMP); ";
    public static final String TABLE_DEV = "cooeydevice";
    private static final String TABLE_DEV_CREATE = "create table cooeydevice (_id integer primary key autoincrement, uid integer, device_name text , device_address text, device_type text, device_id text , device_sn text , device_pairflags text , device_modelnumber text , device_password text , device_brocastID text , software_version text , hardware_version text , firmware_version text , manufacture_name text , device_systemID text , device_model text , user_number int , pair_status int, user_name text , service_uuid text , protocol_type text , max_user_number int , device_status text, date DATETIME DEFAULT CURRENT_TIMESTAMP); ";
    public static final String TABLE_EMCONTACT = "cooeyemergcontact";
    private static final String TABLE_EMCONTACT_CREATE = " create table cooeyemergcontact(_id integer primary key autoincrement, parentId integer, email text not null, phoneNumber text not null, firstname text not null, date DATETIME DEFAULT CURRENT_TIMESTAMP ); ";
    public static final String TABLE_MED = "cooeyusermed";
    private static final String TABLE_MED_CREATE = "create table cooeyusermed(_id integer primary key autoincrement, uid integer, medname text not null, basecomponent text, dosage text, dosageunit text, isreminder integer default 0, reminders text, usagefreq text, type text, medicineId text, date DATETIME DEFAULT CURRENT_TIMESTAMP); ";
    public static final String TABLE_USER = "cooeyuser";
    private static final String TABLE_USR_CREATE = "create table cooeyuser(_id integer primary key autoincrement, firstname text not null, lastname text, gender text, area text, country text, city text, dob text, bloodGroup text, email text not null, nickname text not null, isloggedin integer default 0, patientid integer default 0, phoneNumber text not null, isAthlete integer default 0, athleteLevel integer default 0, onboardweight float default 0.0, externalId text, parentId integer, height float(4,2), date DATETIME DEFAULT CURRENT_TIMESTAMP); ";
    public static final String TABLE_WT = "cooeyuserweight";
    private static final String TABLE_WT_CREATE = "create table cooeyuserweight(_id integer primary key autoincrement, uid integer, weight float(3,1), muscleratio float(3,2), bmi float(3,2), fatratio float(3,2), weightunits text, waterratio float(3,2), impedance float(3,2), bonedensity float(3,2), goalWeight float default 0.0, waistline float default 0.0, date DATETIME DEFAULT CURRENT_TIMESTAMP); ";
    public static final String USR_ID = "uid";

    public CooeySQLHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public CooeySQLHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_USR_CREATE);
        db.execSQL(TABLE_DEV_CREATE);
        db.execSQL(TABLE_BP_CREATE);
        db.execSQL(TABLE_WT_CREATE);
        db.execSQL(TABLE_MED_CREATE);
        db.execSQL(TABLE_EMCONTACT_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(CooeySQLHelper.class.getName(), "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS cooeyuser");
        db.execSQL("DROP TABLE IF EXISTS cooeydevice");
        db.execSQL("DROP TABLE IF EXISTS create table cooeyuserbp(_id integer primary key autoincrement, uid integer, systolic float(3,2), diastolic float(3,2), pulse float(3,2), date DATETIME DEFAULT CURRENT_TIMESTAMP); ");
        db.execSQL("DROP TABLE IF EXISTS create table cooeyuserweight(_id integer primary key autoincrement, uid integer, weight float(3,1), muscleratio float(3,2), bmi float(3,2), fatratio float(3,2), weightunits text, waterratio float(3,2), impedance float(3,2), bonedensity float(3,2), goalWeight float default 0.0, waistline float default 0.0, date DATETIME DEFAULT CURRENT_TIMESTAMP); ");
        db.execSQL("DROP TABLE IF EXISTS create table cooeyusermed(_id integer primary key autoincrement, uid integer, medname text not null, basecomponent text, dosage text, dosageunit text, isreminder integer default 0, reminders text, usagefreq text, type text, medicineId text, date DATETIME DEFAULT CURRENT_TIMESTAMP); ");
        db.execSQL("DROP TABLE IF EXISTS  create table cooeyemergcontact(_id integer primary key autoincrement, parentId integer, email text not null, phoneNumber text not null, firstname text not null, date DATETIME DEFAULT CURRENT_TIMESTAMP ); ");
        onCreate(db);
    }
}
