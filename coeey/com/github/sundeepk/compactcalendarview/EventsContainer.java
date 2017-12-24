package com.github.sundeepk.compactcalendarview;

import com.github.sundeepk.compactcalendarview.comparators.EventComparator;
import com.github.sundeepk.compactcalendarview.domain.Event;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EventsContainer {
    private Map<String, List<Events>> eventsByMonthAndYearMap = new HashMap();
    private Calendar eventsCalendar;
    private Comparator<Event> eventsComparator = new EventComparator();

    public EventsContainer(Calendar eventsCalendar) {
        this.eventsCalendar = eventsCalendar;
    }

    void addEvent(Event event) {
        this.eventsCalendar.setTimeInMillis(event.getTimeInMillis());
        String key = getKeyForCalendarEvent(this.eventsCalendar);
        List<Events> eventsForMonth = (List) this.eventsByMonthAndYearMap.get(key);
        if (eventsForMonth == null) {
            eventsForMonth = new ArrayList();
        }
        Events eventsForTargetDay = getEventDayEvent(event.getTimeInMillis());
        if (eventsForTargetDay == null) {
            List<Event> events = new ArrayList();
            events.add(event);
            eventsForMonth.add(new Events(event.getTimeInMillis(), events));
        } else {
            eventsForTargetDay.getEvents().add(event);
        }
        this.eventsByMonthAndYearMap.put(key, eventsForMonth);
    }

    void removeAllEvents() {
        this.eventsByMonthAndYearMap.clear();
    }

    void addEvents(List<Event> events) {
        int count = events.size();
        for (int i = 0; i < count; i++) {
            addEvent((Event) events.get(i));
        }
    }

    List<Event> getEventsFor(long epochMillis) {
        Events events = getEventDayEvent(epochMillis);
        if (events == null) {
            return new ArrayList();
        }
        return events.getEvents();
    }

    List<Events> getEventsForMonthAndYear(int month, int year) {
        return (List) this.eventsByMonthAndYearMap.get(year + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + month);
    }

    List<Event> getEventsForMonth(long eventTimeInMillis) {
        this.eventsCalendar.setTimeInMillis(eventTimeInMillis);
        List<Events> events = (List) this.eventsByMonthAndYearMap.get(getKeyForCalendarEvent(this.eventsCalendar));
        List<Event> allEventsForMonth = new ArrayList();
        if (events != null) {
            for (Events eve : events) {
                if (eve != null) {
                    allEventsForMonth.addAll(eve.getEvents());
                }
            }
        }
        Collections.sort(allEventsForMonth, this.eventsComparator);
        return allEventsForMonth;
    }

    private Events getEventDayEvent(long eventTimeInMillis) {
        this.eventsCalendar.setTimeInMillis(eventTimeInMillis);
        int dayInMonth = this.eventsCalendar.get(5);
        List<Events> eventsForMonthsAndYear = (List) this.eventsByMonthAndYearMap.get(getKeyForCalendarEvent(this.eventsCalendar));
        if (eventsForMonthsAndYear != null) {
            for (Events events : eventsForMonthsAndYear) {
                this.eventsCalendar.setTimeInMillis(events.getTimeInMillis());
                if (this.eventsCalendar.get(5) == dayInMonth) {
                    return events;
                }
            }
        }
        return null;
    }

    void removeEventByEpochMillis(long epochMillis) {
        this.eventsCalendar.setTimeInMillis(epochMillis);
        int dayInMonth = this.eventsCalendar.get(5);
        List<Events> eventsForMonthAndYear = (List) this.eventsByMonthAndYearMap.get(getKeyForCalendarEvent(this.eventsCalendar));
        if (eventsForMonthAndYear != null) {
            Iterator<Events> calendarDayEventIterator = eventsForMonthAndYear.iterator();
            while (calendarDayEventIterator.hasNext()) {
                this.eventsCalendar.setTimeInMillis(((Events) calendarDayEventIterator.next()).getTimeInMillis());
                if (this.eventsCalendar.get(5) == dayInMonth) {
                    calendarDayEventIterator.remove();
                    return;
                }
            }
        }
    }

    void removeEvent(Event event) {
        this.eventsCalendar.setTimeInMillis(event.getTimeInMillis());
        List<Events> eventsForMonthAndYear = (List) this.eventsByMonthAndYearMap.get(getKeyForCalendarEvent(this.eventsCalendar));
        if (eventsForMonthAndYear != null) {
            for (Events events : eventsForMonthAndYear) {
                int indexOfEvent = events.getEvents().indexOf(event);
                if (indexOfEvent >= 0) {
                    events.getEvents().remove(indexOfEvent);
                    return;
                }
            }
        }
    }

    void removeEvents(List<Event> events) {
        int count = events.size();
        for (int i = 0; i < count; i++) {
            removeEvent((Event) events.get(i));
        }
    }

    private String getKeyForCalendarEvent(Calendar cal) {
        return cal.get(1) + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + cal.get(2);
    }
}
