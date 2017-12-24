package com.ihealth.communication.cloud.data;

public class Data_TB_Workout {
    private int f881a;
    private long f882b;
    private String f883c;
    private long f884d;
    private double f885e;
    private double f886f;
    private float f887g;
    private int f888h;
    private int f889i;
    private int f890j;
    private float f891k;
    private String f892l;
    private String f893m;
    private String f894n;
    private String f895o;
    private String f896p;
    private long f897q;
    private String f898r;
    private String f899s;
    private long f900t;
    private String f901u;
    private String f902v;
    private String f903w;

    public Data_TB_Workout() {
        this.f883c = new String();
        this.f892l = new String();
        this.f893m = new String();
        this.f894n = new String();
        this.f895o = new String();
        this.f896p = new String();
        this.f898r = new String();
        this.f899s = new String();
        this.f901u = new String();
        this.f902v = new String();
        this.f903w = new String();
    }

    public Data_TB_Workout(int workout_ChangeType, long workout_LastChangeTime, String workout_PhoneDataID, long workout_PhoneCreateTime, double workout_Lat, double workout_Lon, float workout_TimeZone, int workout_SpendMinutes, int workout_Steps, int workout_Distance, float workout_Calories, String workout_City, String workout_Temperature, String workout_WeatherCode, String workout_Humidity, String workout_Atmosphere, long workout_CommentTS, String workout_CommentPic, String workout_CommentNote, long workout_endtime, long workout_ChangeTime, long workout_CreateTime, String workout_MechineType, String workout_MechineDeviceID, String workout_iHealthCloud) {
        this.f881a = workout_ChangeType;
        this.f882b = workout_LastChangeTime;
        this.f883c = workout_PhoneDataID;
        this.f884d = workout_PhoneCreateTime;
        this.f885e = workout_Lat;
        this.f886f = workout_Lon;
        this.f887g = workout_TimeZone;
        this.f888h = workout_SpendMinutes;
        this.f889i = workout_Steps;
        this.f890j = workout_Distance;
        this.f891k = workout_Calories;
        this.f892l = workout_City;
        this.f893m = workout_Temperature;
        this.f894n = workout_WeatherCode;
        this.f895o = workout_Humidity;
        this.f896p = workout_Atmosphere;
        this.f897q = workout_CommentTS;
        this.f898r = workout_CommentPic;
        this.f899s = workout_CommentNote;
        this.f900t = workout_endtime;
        this.f901u = workout_MechineType;
        this.f902v = workout_MechineDeviceID;
        this.f903w = workout_iHealthCloud;
    }

    public String getWorkout_Atmosphere() {
        return this.f896p;
    }

    public float getWorkout_Calories() {
        return this.f891k;
    }

    public int getWorkout_ChangeType() {
        return this.f881a;
    }

    public String getWorkout_City() {
        return this.f892l;
    }

    public String getWorkout_CommentNote() {
        return this.f899s;
    }

    public String getWorkout_CommentPic() {
        return this.f898r;
    }

    public long getWorkout_CommentTS() {
        return this.f897q;
    }

    public int getWorkout_Distance() {
        return this.f890j;
    }

    public String getWorkout_Humidity() {
        return this.f895o;
    }

    public long getWorkout_LastChangeTime() {
        return this.f882b;
    }

    public double getWorkout_Lat() {
        return this.f885e;
    }

    public double getWorkout_Lon() {
        return this.f886f;
    }

    public String getWorkout_MechineDeviceID() {
        return this.f902v;
    }

    public String getWorkout_MechineType() {
        return this.f901u;
    }

    public long getWorkout_PhoneCreateTime() {
        return this.f884d;
    }

    public String getWorkout_PhoneDataID() {
        return this.f883c;
    }

    public int getWorkout_SpendMinutes() {
        return this.f888h;
    }

    public int getWorkout_Steps() {
        return this.f889i;
    }

    public String getWorkout_Temperature() {
        return this.f893m;
    }

    public float getWorkout_TimeZone() {
        return this.f887g;
    }

    public String getWorkout_WeatherCode() {
        return this.f894n;
    }

    public long getWorkout_endtime() {
        return this.f900t;
    }

    public String getWorkout_iHealthCloud() {
        return this.f903w;
    }

    public void setWorkout_Atmosphere(String workout_Atmosphere) {
        this.f896p = workout_Atmosphere;
    }

    public void setWorkout_Calories(float workout_Calories) {
        this.f891k = workout_Calories;
    }

    public void setWorkout_ChangeType(int workout_ChangeType) {
        this.f881a = workout_ChangeType;
    }

    public void setWorkout_City(String workout_City) {
        this.f892l = workout_City;
    }

    public void setWorkout_CommentNote(String workout_CommentNote) {
        this.f899s = workout_CommentNote;
    }

    public void setWorkout_CommentPic(String workout_CommentPic) {
        this.f898r = workout_CommentPic;
    }

    public void setWorkout_CommentTS(long workout_CommentTS) {
        this.f897q = workout_CommentTS;
    }

    public void setWorkout_Distance(int workout_Distance) {
        this.f890j = workout_Distance;
    }

    public void setWorkout_Humidity(String workout_Humidity) {
        this.f895o = workout_Humidity;
    }

    public void setWorkout_LastChangeTime(long workout_LastChangeTime) {
        this.f882b = workout_LastChangeTime;
    }

    public void setWorkout_Lat(double workout_Lat) {
        this.f885e = workout_Lat;
    }

    public void setWorkout_Lon(double workout_Lon) {
        this.f886f = workout_Lon;
    }

    public void setWorkout_MechineDeviceID(String workout_MechineDeviceID) {
        this.f902v = workout_MechineDeviceID;
    }

    public void setWorkout_MechineType(String workout_MechineType) {
        this.f901u = workout_MechineType;
    }

    public void setWorkout_PhoneCreateTime(long workout_PhoneCreateTime) {
        this.f884d = workout_PhoneCreateTime;
    }

    public void setWorkout_PhoneDataID(String workout_PhoneDataID) {
        this.f883c = workout_PhoneDataID;
    }

    public void setWorkout_SpendMinutes(int workout_SpendMinutes) {
        this.f888h = workout_SpendMinutes;
    }

    public void setWorkout_Steps(int workout_Steps) {
        this.f889i = workout_Steps;
    }

    public void setWorkout_Temperature(String workout_Temperature) {
        this.f893m = workout_Temperature;
    }

    public void setWorkout_TimeZone(float workout_TimeZone) {
        this.f887g = workout_TimeZone;
    }

    public void setWorkout_WeatherCode(String workout_WeatherCode) {
        this.f894n = workout_WeatherCode;
    }

    public void setWorkout_endtime(long workout_endtime) {
        this.f900t = workout_endtime;
    }

    public void setWorkout_iHealthCloud(String workout_iHealthCloud) {
        this.f903w = workout_iHealthCloud;
    }

    public String toString() {
        return "Data_TB_Workout [workout_ChangeType=" + this.f881a + ", workout_LastChangeTime=" + this.f882b + ", workout_PhoneDataID=" + this.f883c + ", workout_PhoneCreateTime=" + this.f884d + ", workout_Lat=" + this.f885e + ", workout_Lon=" + this.f886f + ", workout_TimeZone=" + this.f887g + ", workout_SpendMinutes=" + this.f888h + ", workout_Steps=" + this.f889i + ", workout_Distance=" + this.f890j + ", workout_Calories=" + this.f891k + ", workout_City=" + this.f892l + ", workout_Temperature=" + this.f893m + ", workout_WeatherCode=" + this.f894n + ", workout_Humidity=" + this.f895o + ", workout_Atmosphere=" + this.f896p + ", workout_CommentTS=" + this.f897q + ", workout_CommentPic=" + this.f898r + ", workout_CommentNote=" + this.f899s + ", workout_endtime=" + this.f900t + ", workout_MechineType=" + this.f901u + ", workout_MechineDeviceID=" + this.f902v + ", workout_iHealthCloud=" + this.f903w + "]";
    }
}
