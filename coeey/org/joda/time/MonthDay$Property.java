package org.joda.time;

import java.io.Serializable;
import java.util.Locale;
import org.joda.time.field.AbstractPartialFieldProperty;

public class MonthDay$Property extends AbstractPartialFieldProperty implements Serializable {
    private static final long serialVersionUID = 5727734012190224363L;
    private final MonthDay iBase;
    private final int iFieldIndex;

    MonthDay$Property(MonthDay monthDay, int i) {
        this.iBase = monthDay;
        this.iFieldIndex = i;
    }

    public DateTimeField getField() {
        return this.iBase.getField(this.iFieldIndex);
    }

    protected ReadablePartial getReadablePartial() {
        return this.iBase;
    }

    public MonthDay getMonthDay() {
        return this.iBase;
    }

    public int get() {
        return this.iBase.getValue(this.iFieldIndex);
    }

    public MonthDay addToCopy(int i) {
        return new MonthDay(this.iBase, getField().add(this.iBase, this.iFieldIndex, this.iBase.getValues(), i));
    }

    public MonthDay addWrapFieldToCopy(int i) {
        return new MonthDay(this.iBase, getField().addWrapField(this.iBase, this.iFieldIndex, this.iBase.getValues(), i));
    }

    public MonthDay setCopy(int i) {
        return new MonthDay(this.iBase, getField().set(this.iBase, this.iFieldIndex, this.iBase.getValues(), i));
    }

    public MonthDay setCopy(String str, Locale locale) {
        return new MonthDay(this.iBase, getField().set(this.iBase, this.iFieldIndex, this.iBase.getValues(), str, locale));
    }

    public MonthDay setCopy(String str) {
        return setCopy(str, null);
    }
}
