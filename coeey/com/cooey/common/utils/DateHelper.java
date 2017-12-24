package com.cooey.common.utils;

import java.util.Calendar;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Lcom/cooey/common/utils/DateHelper;", "", "()V", "checkIfSameDay", "", "timestamp1", "", "timestamp2", "common_release"}, k = 1, mv = {1, 1, 7})
/* compiled from: Datehelper.kt */
public final class DateHelper {
    public final boolean checkIfSameDay(long timestamp1, long timestamp2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTimeInMillis(timestamp1);
        cal2.setTimeInMillis(timestamp2);
        if (cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6)) {
            return true;
        }
        return false;
    }
}
