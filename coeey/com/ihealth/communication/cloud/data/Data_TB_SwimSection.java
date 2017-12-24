package com.ihealth.communication.cloud.data;

public class Data_TB_SwimSection {
    private String f843a;
    private String f844b;
    private String f845c;
    private String f846d;
    private double f847e;
    private double f848f;
    private long f849g;
    private String f850h;
    private long f851i;
    private int f852j;
    private long f853k;
    private long f854l;
    private int f855m;
    private String f856n;
    private int f857o;
    private int f858p;
    private int f859q;
    private int f860r;
    private float f861s;
    private int f862t;
    private int f863u;
    private float f864v;
    private String f865w;

    public Data_TB_SwimSection() {
        this.f843a = new String();
        this.f844b = new String();
        this.f845c = new String();
        this.f846d = new String();
        this.f850h = new String();
        this.f856n = new String();
        this.f865w = new String();
    }

    public Data_TB_SwimSection(double swimSection_Lat, double swimSection_Lon, long swimSection_LastChangeTime, long swimSection_NoteTS, int swimSection_SwimCoastTime, long swimSection_Endtime, long swimSection_StartTime, int swimSection_PoolLength, int swimSection_SpendTimeBackStroke, int swimSection_SpendTimeBreastStroke, int swimSection_SpendTimeFreeStroke, int swimSection_SpendTimeUnrecognized, float swimSection_SumCalories, int swimSection_SumThrashTimes, int swimSection_SumTripCount, float swimSection_TimeZone) {
        this.f847e = swimSection_Lat;
        this.f848f = swimSection_Lon;
        this.f849g = swimSection_LastChangeTime;
        this.f851i = swimSection_NoteTS;
        this.f852j = swimSection_SwimCoastTime;
        this.f853k = swimSection_Endtime;
        this.f854l = swimSection_StartTime;
        this.f855m = swimSection_PoolLength;
        this.f857o = swimSection_SpendTimeBackStroke;
        this.f858p = swimSection_SpendTimeBreastStroke;
        this.f859q = swimSection_SpendTimeFreeStroke;
        this.f860r = swimSection_SpendTimeUnrecognized;
        this.f861s = swimSection_SumCalories;
        this.f862t = swimSection_SumThrashTimes;
        this.f863u = swimSection_SumTripCount;
        this.f864v = swimSection_TimeZone;
    }

    public String getSwimSection_City() {
        return this.f844b;
    }

    public String getSwimSection_DataID() {
        return this.f856n;
    }

    public String getSwimSection_DeviceID() {
        return this.f845c;
    }

    public String getSwimSection_DeviceSource() {
        return this.f846d;
    }

    public long getSwimSection_Endtime() {
        return this.f853k;
    }

    public long getSwimSection_LastChangeTime() {
        return this.f849g;
    }

    public double getSwimSection_Lat() {
        return this.f847e;
    }

    public double getSwimSection_Lon() {
        return this.f848f;
    }

    public String getSwimSection_Note() {
        return this.f850h;
    }

    public long getSwimSection_NoteTS() {
        return this.f851i;
    }

    public int getSwimSection_PoolLength() {
        return this.f855m;
    }

    public int getSwimSection_SpendTimeBackStroke() {
        return this.f857o;
    }

    public int getSwimSection_SpendTimeBreastStroke() {
        return this.f858p;
    }

    public int getSwimSection_SpendTimeFreeStroke() {
        return this.f859q;
    }

    public int getSwimSection_SpendTimeUnrecognized() {
        return this.f860r;
    }

    public long getSwimSection_StartTime() {
        return this.f854l;
    }

    public float getSwimSection_SumCalories() {
        return this.f861s;
    }

    public int getSwimSection_SumThrashTimes() {
        return this.f862t;
    }

    public int getSwimSection_SumTripCount() {
        return this.f863u;
    }

    public int getSwimSection_SwimCoastTime() {
        return this.f852j;
    }

    public float getSwimSection_TimeZone() {
        return this.f864v;
    }

    public String getSwimSection_Weather() {
        return this.f865w;
    }

    public String getSwimSection_iHealthCloud() {
        return this.f843a;
    }

    public void setSwimSection_City(String swimSection_City) {
        this.f844b = swimSection_City;
    }

    public void setSwimSection_DataID(String swimSection_DataID) {
        this.f856n = swimSection_DataID;
    }

    public void setSwimSection_DeviceID(String swimSection_DeviceID) {
        this.f845c = swimSection_DeviceID;
    }

    public void setSwimSection_DeviceSource(String swimSection_DeviceSource) {
        this.f846d = swimSection_DeviceSource;
    }

    public void setSwimSection_Endtime(long swimSection_Endtime) {
        this.f853k = swimSection_Endtime;
    }

    public void setSwimSection_LastChangeTime(long swimSection_LastChangeTime) {
        this.f849g = swimSection_LastChangeTime;
    }

    public void setSwimSection_Lat(double swimSection_Lat) {
        this.f847e = swimSection_Lat;
    }

    public void setSwimSection_Lon(double swimSection_Lon) {
        this.f848f = swimSection_Lon;
    }

    public void setSwimSection_Note(String swimSection_Note) {
        this.f850h = swimSection_Note;
    }

    public void setSwimSection_NoteTS(long swimSection_NoteTS) {
        this.f851i = swimSection_NoteTS;
    }

    public void setSwimSection_PoolLength(int swimSection_PoolLength) {
        this.f855m = swimSection_PoolLength;
    }

    public void setSwimSection_SpendTimeBackStroke(int swimSection_SpendTimeBackStroke) {
        this.f857o = swimSection_SpendTimeBackStroke;
    }

    public void setSwimSection_SpendTimeBreastStroke(int swimSection_SpendTimeBreastStroke) {
        this.f858p = swimSection_SpendTimeBreastStroke;
    }

    public void setSwimSection_SpendTimeFreeStroke(int swimSection_SpendTimeFreeStroke) {
        this.f859q = swimSection_SpendTimeFreeStroke;
    }

    public void setSwimSection_SpendTimeUnrecognized(int swimSection_SpendTimeUnrecognized) {
        this.f860r = swimSection_SpendTimeUnrecognized;
    }

    public void setSwimSection_StartTime(long swimSection_StartTime) {
        this.f854l = swimSection_StartTime;
    }

    public void setSwimSection_SumCalories(float swimSection_SumCalories) {
        this.f861s = swimSection_SumCalories;
    }

    public void setSwimSection_SumThrashTimes(int swimSection_SumThrashTimes) {
        this.f862t = swimSection_SumThrashTimes;
    }

    public void setSwimSection_SumTripCount(int swimSection_SumTripCount) {
        this.f863u = swimSection_SumTripCount;
    }

    public void setSwimSection_SwimCoastTime(int swimSection_SwimCoastTime) {
        this.f852j = swimSection_SwimCoastTime;
    }

    public void setSwimSection_TimeZone(float swimSection_TimeZone) {
        this.f864v = swimSection_TimeZone;
    }

    public void setSwimSection_Weather(String swimSection_Weather) {
        this.f865w = swimSection_Weather;
    }

    public void setSwimSection_iHealthCloud(String swimSection_iHealthCloud) {
        this.f843a = swimSection_iHealthCloud;
    }

    public String toString() {
        return "Data_TB_SwimSection [swimSection_City=" + this.f844b + ", swimSection_DeviceID=" + this.f845c + ", swimSection_DeviceSource=" + this.f846d + ", swimSection_Lat=" + this.f847e + ", swimSection_Lon=" + this.f848f + ", swimSection_LastChangeTime=" + this.f849g + ", swimSection_Note=" + this.f850h + ", swimSection_NoteTS=" + this.f851i + ", swimSection_SwimCoastTime=" + this.f852j + ", swimSection_Endtime=" + this.f853k + ", swimSection_StartTime=" + this.f854l + ", swimSection_PoolLength=" + this.f855m + ", swimSection_DataID=" + this.f856n + ", swimSection_SpendTimeBackStroke=" + this.f857o + ", swimSection_SpendTimeBreastStroke=" + this.f858p + ", swimSection_SpendTimeFreeStroke=" + this.f859q + ", swimSection_SpendTimeUnrecognized=" + this.f860r + ", swimSection_SumCalories=" + this.f861s + ", swimSection_SumThrashTimes=" + this.f862t + ", swimSection_SumTripCount=" + this.f863u + ", swimSection_TimeZone=" + this.f864v + ", swimSection_Weather=" + this.f865w + ", swimSection_iHealthCloud=" + this.f843a + "]";
    }
}
