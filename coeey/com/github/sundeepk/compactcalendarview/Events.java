package com.github.sundeepk.compactcalendarview;

import com.github.sundeepk.compactcalendarview.domain.Event;
import java.util.List;

class Events {
    private final List<Event> events;
    private final long timeInMillis;

    Events(long timeInMillis, List<Event> events) {
        this.timeInMillis = timeInMillis;
        this.events = events;
    }

    long getTimeInMillis() {
        return this.timeInMillis;
    }

    List<Event> getEvents() {
        return this.events;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Events event = (Events) o;
        if (this.timeInMillis != event.timeInMillis) {
            return false;
        }
        if (this.events != null) {
            if (this.events.equals(event.events)) {
                return true;
            }
        } else if (event.events == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.events != null ? this.events.hashCode() : 0) * 31) + ((int) (this.timeInMillis ^ (this.timeInMillis >>> 32)));
    }

    public String toString() {
        return "Events{events=" + this.events + ", timeInMillis=" + this.timeInMillis + '}';
    }
}
