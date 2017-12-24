package com.alamkanak.weekview;

import java.util.Calendar;
import java.util.List;

public interface WeekViewLoader {
    List<? extends WeekViewEvent> onLoad(int i);

    double toWeekViewPeriodIndex(Calendar calendar);
}
