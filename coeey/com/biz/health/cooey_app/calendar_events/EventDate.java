package com.biz.health.cooey_app.calendar_events;

import com.github.sundeepk.compactcalendarview.domain.Event;

public class EventDate extends Event {
    private long endLong;
    private long startLong;

    public EventDate(int color, long timeInMillis) {
        super(color, timeInMillis);
    }

    public EventDate(int color, long timeInMillis, Object data) {
        super(color, timeInMillis, data);
        this.startLong = timeInMillis;
    }

    public void setEndTime(long endLong) {
        this.endLong = endLong;
    }

    public long getEndTime() {
        return this.endLong;
    }

    public long getStartLong() {
        return this.startLong;
    }
}
