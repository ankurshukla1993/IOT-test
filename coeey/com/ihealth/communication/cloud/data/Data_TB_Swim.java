package com.ihealth.communication.cloud.data;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Data_TB_Swim implements Parcelable {
    public static final Creator CREATOR = new C2058d();
    private String f814A;
    private String f815B;
    private String f816C;
    private int f817a;
    private long f818b;
    private String f819c;
    private long f820d;
    private double f821e;
    private double f822f;
    private float f823g;
    private long f824h;
    private int f825i;
    private Long f826j;
    private int f827k;
    private float f828l;
    private int f829m;
    private int f830n;
    private int f831o;
    private int f832p;
    private int f833q;
    private int f834r;
    private String f835s;
    private String f836t;
    private String f837u;
    private String f838v;
    private String f839w;
    private long f840x;
    private String f841y;
    private String f842z;

    public Data_TB_Swim() {
        this.f819c = new String();
        this.f835s = new String();
        this.f836t = new String();
        this.f837u = new String();
        this.f838v = new String();
        this.f839w = new String();
        this.f841y = new String();
        this.f842z = new String();
        this.f814A = new String();
        this.f815B = new String();
        this.f816C = new String();
    }

    public Data_TB_Swim(int swim_ChangeType, long swim_LastChangeTime, String swim_PhoneDataID, long swim_PhoneCreateTime, double swim_Lat, double swim_Lon, float swim_TimeZone, int swim_SpendMinutes, int swim_PullTimes, float swim_Calories, int swim_Stroke, int swim_Distance, int swim_Cycles, String swim_City, String swim_Temperature, String swim_WeatherCode, String swim_Humidity, String swim_Atmosphere, long swim_CommentTS, String swim_CommentPic, String swim_CommentNote, long swim_endtime, String swim_MechineType, String swim_MechineDeviceID, String swim_iHealthCloud) {
        this.f817a = swim_ChangeType;
        this.f818b = swim_LastChangeTime;
        this.f819c = swim_PhoneDataID;
        this.f820d = swim_PhoneCreateTime;
        this.f821e = swim_Lat;
        this.f822f = swim_Lon;
        this.f823g = swim_TimeZone;
        this.f825i = swim_SpendMinutes;
        this.f827k = swim_PullTimes;
        this.f828l = swim_Calories;
        this.f829m = swim_Stroke;
        this.f830n = swim_Distance;
        this.f831o = swim_Cycles;
        this.f835s = swim_City;
        this.f836t = swim_Temperature;
        this.f837u = swim_WeatherCode;
        this.f838v = swim_Humidity;
        this.f839w = swim_Atmosphere;
        this.f840x = swim_CommentTS;
        this.f841y = swim_CommentPic;
        this.f842z = swim_CommentNote;
        this.f824h = swim_endtime;
        this.f814A = swim_MechineType;
        this.f814A = swim_MechineType;
        this.f816C = swim_iHealthCloud;
    }

    public Data_TB_Swim(Parcel in) {
        this.f817a = in.readInt();
        this.f825i = in.readInt();
        this.f827k = in.readInt();
        this.f829m = in.readInt();
        this.f830n = in.readInt();
        this.f831o = in.readInt();
        this.f832p = in.readInt();
        this.f833q = in.readInt();
        this.f834r = in.readInt();
        this.f819c = in.readString();
        this.f835s = in.readString();
        this.f836t = in.readString();
        this.f837u = in.readString();
        this.f838v = in.readString();
        this.f839w = in.readString();
        this.f841y = in.readString();
        this.f842z = in.readString();
        this.f814A = in.readString();
        this.f815B = in.readString();
        this.f816C = in.readString();
        this.f823g = in.readFloat();
        this.f828l = in.readFloat();
        this.f821e = in.readDouble();
        this.f822f = in.readDouble();
        this.f818b = in.readLong();
        this.f820d = in.readLong();
        this.f824h = in.readLong();
        this.f840x = in.readLong();
    }

    public int describeContents() {
        return 0;
    }

    public String getSwim_Atmosphere() {
        return this.f839w;
    }

    public float getSwim_Calories() {
        return this.f828l;
    }

    public int getSwim_ChangeType() {
        return this.f817a;
    }

    public String getSwim_City() {
        return this.f835s;
    }

    public String getSwim_CommentNote() {
        return this.f842z;
    }

    public String getSwim_CommentPic() {
        return this.f841y;
    }

    public long getSwim_CommentTS() {
        return this.f840x;
    }

    public int getSwim_CutInTimeDif() {
        return this.f832p;
    }

    public int getSwim_CutOutTimeDif() {
        return this.f833q;
    }

    public int getSwim_Cycles() {
        return this.f831o;
    }

    public int getSwim_Distance() {
        return this.f830n;
    }

    public String getSwim_Humidity() {
        return this.f838v;
    }

    public long getSwim_LastChangeTime() {
        return this.f818b;
    }

    public double getSwim_Lat() {
        return this.f821e;
    }

    public double getSwim_Lon() {
        return this.f822f;
    }

    public String getSwim_MechineDeviceID() {
        return this.f815B;
    }

    public String getSwim_MechineType() {
        return this.f814A;
    }

    public long getSwim_PhoneCreateTime() {
        return this.f820d;
    }

    public String getSwim_PhoneDataID() {
        return this.f819c;
    }

    public int getSwim_ProcessFlag() {
        return this.f834r;
    }

    public int getSwim_PullTimes() {
        return this.f827k;
    }

    public int getSwim_SpendMinutes() {
        return this.f825i;
    }

    public Long getSwim_StartTimeStamp() {
        return this.f826j;
    }

    public int getSwim_Stroke() {
        return this.f829m;
    }

    public String getSwim_Temperature() {
        return this.f836t;
    }

    public float getSwim_TimeZone() {
        return this.f823g;
    }

    public String getSwim_WeatherCode() {
        return this.f837u;
    }

    public long getSwim_endtime() {
        return this.f824h;
    }

    public String getSwim_iHealthCloud() {
        return this.f816C;
    }

    public void setSwim_Atmosphere(String swim_Atmosphere) {
        this.f839w = swim_Atmosphere;
    }

    public void setSwim_Calories(float swim_Calories) {
        this.f828l = swim_Calories;
    }

    public void setSwim_ChangeType(int swim_ChangeType) {
        this.f817a = swim_ChangeType;
    }

    public void setSwim_City(String swim_City) {
        this.f835s = swim_City;
    }

    public void setSwim_CommentNote(String swim_CommentNote) {
        this.f842z = swim_CommentNote;
    }

    public void setSwim_CommentPic(String swim_CommentPic) {
        this.f841y = swim_CommentPic;
    }

    public void setSwim_CommentTS(long swim_CommentTS) {
        this.f840x = swim_CommentTS;
    }

    public void setSwim_CutInTimeDif(int swim_CutInTimeDif) {
        this.f832p = swim_CutInTimeDif;
    }

    public void setSwim_CutOutTimeDif(int swim_CutOutTimeDif) {
        this.f833q = swim_CutOutTimeDif;
    }

    public void setSwim_Cycles(int swim_Cycles) {
        this.f831o = swim_Cycles;
    }

    public void setSwim_Distance(int swim_Distance) {
        this.f830n = swim_Distance;
    }

    public void setSwim_Humidity(String swim_Humidity) {
        this.f838v = swim_Humidity;
    }

    public void setSwim_LastChangeTime(long swim_LastChangeTime) {
        this.f818b = swim_LastChangeTime;
    }

    public void setSwim_Lat(double swim_Lat) {
        this.f821e = swim_Lat;
    }

    public void setSwim_Lon(double swim_Lon) {
        this.f822f = swim_Lon;
    }

    public void setSwim_MechineDeviceID(String swim_MechineDeviceID) {
        this.f815B = swim_MechineDeviceID;
    }

    public void setSwim_MechineType(String swim_MechineType) {
        this.f814A = swim_MechineType;
    }

    public void setSwim_PhoneCreateTime(long swim_PhoneCreateTime) {
        this.f820d = swim_PhoneCreateTime;
    }

    public void setSwim_PhoneDataID(String swim_PhoneDataID) {
        this.f819c = swim_PhoneDataID;
    }

    public void setSwim_ProcessFlag(int swim_ProcessFlag) {
        this.f834r = swim_ProcessFlag;
    }

    public void setSwim_PullTimes(int swim_PullTimes) {
        this.f827k = swim_PullTimes;
    }

    public void setSwim_SpendMinutes(int swim_SpendMinutes) {
        this.f825i = swim_SpendMinutes;
    }

    public void setSwim_StartTimeStamp(Long swim_StartTimeStamp) {
        this.f826j = swim_StartTimeStamp;
    }

    public void setSwim_Storke(int swim_Stroke) {
        this.f829m = swim_Stroke;
    }

    public void setSwim_Temperature(String swim_Temperature) {
        this.f836t = swim_Temperature;
    }

    public void setSwim_TimeZone(float swim_TimeZone) {
        this.f823g = swim_TimeZone;
    }

    public void setSwim_WeatherCode(String swim_WeatherCode) {
        this.f837u = swim_WeatherCode;
    }

    public void setSwim_endtime(long swim_endtime) {
        this.f824h = swim_endtime;
    }

    public void setSwim_iHealthCloud(String swim_iHealthCloud) {
        this.f816C = swim_iHealthCloud;
    }

    public String toString() {
        return "Data_TB_Swim [swim_ChangeType=" + this.f817a + ", swim_LastChangeTime=" + this.f818b + ", swim_PhoneDataID=" + this.f819c + ", swim_PhoneCreateTime=" + this.f820d + ", swim_Lat=" + this.f821e + ", swim_Lon=" + this.f822f + ", swim_TimeZone=" + this.f823g + ", swim_SpendMinutes=" + this.f825i + ", swim_PullTimes=" + this.f827k + ", swim_Stroke=" + this.f829m + ", swim_Cycles=" + this.f831o + ",  swim_Distance=" + this.f830n + ", " + "swim_Calories=" + this.f828l + ", swim_City=" + this.f835s + ", swim_Temperature=" + this.f836t + ", swim_WeatherCode=" + this.f837u + ", swim_Humidity=" + this.f838v + ", swim_Atmosphere=" + this.f839w + ", swim_CommentTS=" + this.f840x + ", swim_CommentPic=" + this.f841y + ", swim_CommentNote=" + this.f842z + ", swim_endtime=" + this.f824h + ", swim_MechineType=" + this.f814A + ", swim_MechineDeviceID=" + this.f815B + ", swim_iHealthCloud=" + this.f816C + "]";
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(this.f817a);
        out.writeInt(this.f825i);
        out.writeInt(this.f827k);
        out.writeInt(this.f829m);
        out.writeInt(this.f830n);
        out.writeInt(this.f831o);
        out.writeInt(this.f832p);
        out.writeInt(this.f833q);
        out.writeInt(this.f834r);
        out.writeString(this.f819c);
        out.writeString(this.f835s);
        out.writeString(this.f836t);
        out.writeString(this.f837u);
        out.writeString(this.f838v);
        out.writeString(this.f839w);
        out.writeString(this.f841y);
        out.writeString(this.f842z);
        out.writeString(this.f814A);
        out.writeString(this.f815B);
        out.writeString(this.f816C);
        out.writeFloat(this.f823g);
        out.writeFloat(this.f828l);
        out.writeDouble(this.f821e);
        out.writeDouble(this.f822f);
        out.writeLong(this.f818b);
        out.writeLong(this.f820d);
        out.writeLong(this.f824h);
        out.writeLong(this.f840x);
    }
}
