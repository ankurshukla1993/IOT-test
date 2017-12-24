package com.github.sundeepk.compactcalendarview.domain;

import android.support.annotation.Nullable;

public class Event {
    private int color;
    private Object data;
    private long timeInMillis;

    public Event(int color, long timeInMillis) {
        this.color = color;
        this.timeInMillis = timeInMillis;
    }

    public Event(int color, long timeInMillis, Object data) {
        this.color = color;
        this.timeInMillis = timeInMillis;
        this.data = data;
    }

    public int getColor() {
        return this.color;
    }

    public long getTimeInMillis() {
        return this.timeInMillis;
    }

    @Nullable
    public Object getData() {
        return this.data;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        if (this.color != event.color) {
            return false;
        }
        if (this.timeInMillis != event.timeInMillis) {
            return false;
        }
        if (this.data != null) {
            if (this.data.equals(event.data)) {
                return true;
            }
        } else if (event.data == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((this.color * 31) + ((int) (this.timeInMillis ^ (this.timeInMillis >>> 32)))) * 31) + (this.data != null ? this.data.hashCode() : 0);
    }

    public String toString() {
        return "Event{color=" + this.color + ", timeInMillis=" + this.timeInMillis + ", data=" + this.data + '}';
    }
}
