package com.github.sundeepk.compactcalendarview.comparators;

import com.github.sundeepk.compactcalendarview.domain.Event;
import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    public int compare(Event lhs, Event rhs) {
        if (lhs.getTimeInMillis() < rhs.getTimeInMillis()) {
            return -1;
        }
        return lhs.getTimeInMillis() == rhs.getTimeInMillis() ? 0 : 1;
    }
}
