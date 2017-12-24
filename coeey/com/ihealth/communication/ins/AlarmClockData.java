package com.ihealth.communication.ins;

import java.io.Serializable;

public class AlarmClockData implements Serializable {
    private int f1714a;
    private int f1715b;
    private int f1716c;
    private boolean f1717d;
    private byte f1718e;
    private boolean f1719f;

    public AlarmClockData(int id, int hour, int min, boolean isRepeat, byte week, boolean isOpen) {
        this.f1714a = id;
        this.f1715b = hour;
        this.f1716c = min;
        this.f1717d = isRepeat;
        this.f1718e = week;
        this.f1719f = isOpen;
    }

    public int getHour() {
        return this.f1715b;
    }

    public int getId() {
        return this.f1714a;
    }

    public int getMin() {
        return this.f1716c;
    }

    public byte getWeek() {
        return this.f1718e;
    }

    public boolean isOpen() {
        return this.f1719f;
    }

    public boolean isRepeat() {
        return this.f1717d;
    }

    public void setHour(int hour) {
        this.f1715b = hour;
    }

    public void setId(int id) {
        this.f1714a = id;
    }

    public void setMin(int min) {
        this.f1716c = min;
    }

    public void setOpen(boolean isOpen) {
        this.f1719f = isOpen;
    }

    public void setRepeat(boolean isRepeat) {
        this.f1717d = isRepeat;
    }

    public void setWeek(byte week) {
        this.f1718e = week;
    }
}
