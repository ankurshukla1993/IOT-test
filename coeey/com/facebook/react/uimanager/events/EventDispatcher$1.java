package com.facebook.react.uimanager.events;

import java.util.Comparator;

class EventDispatcher$1 implements Comparator<Event> {
    EventDispatcher$1() {
    }

    public int compare(Event lhs, Event rhs) {
        if (lhs == null && rhs == null) {
            return 0;
        }
        if (lhs == null) {
            return -1;
        }
        if (rhs == null) {
            return 1;
        }
        long diff = lhs.getTimestampMs() - rhs.getTimestampMs();
        if (diff == 0) {
            return 0;
        }
        if (diff < 0) {
            return -1;
        }
        return 1;
    }
}
