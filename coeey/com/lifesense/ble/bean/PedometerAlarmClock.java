package com.lifesense.ble.bean;

public class PedometerAlarmClock {
    public static final int[] DAYS = new int[]{1, 2, 4, 8, 16, 32, 64};
    public static final int FRIDAY = 16;
    public static final int MONDAY = 1;
    public static final int SATURDAY = 32;
    public static final int SUNDAY = 64;
    public static final int THURSDAY = 8;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 4;
    public static final int WEEKEND = 96;
    public static final int WORKDAY = 31;
    private int alarmTimes;
    private int day1;
    private int day2;
    private int day3;
    private int day4;
    private String deviceId;
    private int hour1;
    private int hour2;
    private int hour3;
    private int hour4;
    private String memberId;
    private int minute1;
    private int minute2;
    private int minute3;
    private int minute4;
    private int switch1;
    private int switch2;
    private int switch3;
    private int switch4;

    public int getAlarmTimes() {
        return this.alarmTimes;
    }

    public byte[] getBytes() {
        return new byte[]{(byte) 66, (byte) this.alarmTimes, (byte) this.switch1, (byte) this.day1, (byte) this.hour1, (byte) this.minute1, (byte) this.switch2, (byte) this.day2, (byte) this.hour2, (byte) this.minute2, (byte) this.switch3, (byte) this.day3, (byte) this.hour3, (byte) this.minute3, (byte) this.switch4, (byte) this.day4, (byte) this.hour4, (byte) this.minute4};
    }

    public int getDay1() {
        return this.day1;
    }

    public int getDay2() {
        return this.day2;
    }

    public int getDay3() {
        return this.day3;
    }

    public int getDay4() {
        return this.day4;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public int getHour1() {
        return this.hour1;
    }

    public int getHour2() {
        return this.hour2;
    }

    public int getHour3() {
        return this.hour3;
    }

    public int getHour4() {
        return this.hour4;
    }

    public String getMemberId() {
        return this.memberId;
    }

    public int getMinute1() {
        return this.minute1;
    }

    public int getMinute2() {
        return this.minute2;
    }

    public int getMinute3() {
        return this.minute3;
    }

    public int getMinute4() {
        return this.minute4;
    }

    public int isSwitch1() {
        return this.switch1;
    }

    public int isSwitch2() {
        return this.switch2;
    }

    public int isSwitch3() {
        return this.switch3;
    }

    public int isSwitch4() {
        return this.switch4;
    }

    public void setAlarmTimes(int i) {
        this.alarmTimes = i;
    }

    public void setDay1(int i) {
        this.day1 = i;
    }

    public void setDay2(int i) {
        this.day2 = i;
    }

    public void setDay3(int i) {
        this.day3 = i;
    }

    public void setDay4(int i) {
        this.day4 = i;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setHour1(int i) {
        this.hour1 = i;
    }

    public void setHour2(int i) {
        this.hour2 = i;
    }

    public void setHour3(int i) {
        this.hour3 = i;
    }

    public void setHour4(int i) {
        this.hour4 = i;
    }

    public void setMemberId(String str) {
        this.memberId = str;
    }

    public void setMinute1(int i) {
        this.minute1 = i;
    }

    public void setMinute2(int i) {
        this.minute2 = i;
    }

    public void setMinute3(int i) {
        this.minute3 = i;
    }

    public void setMinute4(int i) {
        this.minute4 = i;
    }

    public void setSwitch1(int i) {
        this.switch1 = i;
    }

    public void setSwitch2(int i) {
        this.switch2 = i;
    }

    public void setSwitch3(int i) {
        this.switch3 = i;
    }

    public void setSwitch4(int i) {
        this.switch4 = i;
    }

    public String toString() {
        return "AlarmClock [deviceId=" + this.deviceId + ", memberId=" + this.memberId + ", alarmTimes=" + this.alarmTimes + ", switch1=" + this.switch1 + ", day1=" + this.day1 + ", hour1=" + this.hour1 + ", minute1=" + this.minute1 + ", switch2=" + this.switch2 + ", day2=" + this.day2 + ", hour2=" + this.hour2 + ", minute2=" + this.minute2 + ", switch3=" + this.switch3 + ", day3=" + this.day3 + ", hour3=" + this.hour3 + ", minute3=" + this.minute3 + ", switch4=" + this.switch4 + ", day4=" + this.day4 + ", hour4=" + this.hour4 + ", minute4=" + this.minute4 + "]";
    }
}
