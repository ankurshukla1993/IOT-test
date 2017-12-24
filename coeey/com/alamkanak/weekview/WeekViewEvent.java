package com.alamkanak.weekview;

import java.util.Calendar;

public class WeekViewEvent {
    private int mColor;
    private Calendar mEndTime;
    private long mId;
    private String mLocation;
    private String mName;
    private Calendar mStartTime;

    public WeekViewEvent(long id, String name, int startYear, int startMonth, int startDay, int startHour, int startMinute, int endYear, int endMonth, int endDay, int endHour, int endMinute) {
        this.mId = id;
        this.mStartTime = Calendar.getInstance();
        this.mStartTime.set(1, startYear);
        this.mStartTime.set(2, startMonth - 1);
        this.mStartTime.set(5, startDay);
        this.mStartTime.set(11, startHour);
        this.mStartTime.set(12, startMinute);
        this.mEndTime = Calendar.getInstance();
        this.mEndTime.set(1, endYear);
        this.mEndTime.set(2, endMonth - 1);
        this.mEndTime.set(5, endDay);
        this.mEndTime.set(11, endHour);
        this.mEndTime.set(12, endMinute);
        this.mName = name;
    }

    public WeekViewEvent(long id, String name, String location, Calendar startTime, Calendar endTime) {
        this.mId = id;
        this.mName = name;
        this.mLocation = location;
        this.mStartTime = startTime;
        this.mEndTime = endTime;
    }

    public WeekViewEvent(long id, String name, Calendar startTime, Calendar endTime) {
        this(id, name, null, startTime, endTime);
    }

    public Calendar getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(Calendar startTime) {
        this.mStartTime = startTime;
    }

    public Calendar getEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(Calendar endTime) {
        this.mEndTime = endTime;
    }

    public String getName() {
        return this.mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getLocation() {
        return this.mLocation;
    }

    public void setLocation(String location) {
        this.mLocation = location;
    }

    public int getColor() {
        return this.mColor;
    }

    public void setColor(int color) {
        this.mColor = color;
    }

    public long getId() {
        return this.mId;
    }

    public void setId(long id) {
        this.mId = id;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (this.mId != ((WeekViewEvent) o).mId) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.mId ^ (this.mId >>> 32));
    }
}
