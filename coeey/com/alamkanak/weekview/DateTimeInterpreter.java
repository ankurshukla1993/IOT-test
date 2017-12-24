package com.alamkanak.weekview;

import java.util.Calendar;

public interface DateTimeInterpreter {
    String interpretDate(Calendar calendar);

    String interpretTime(int i);
}
