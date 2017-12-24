package org.joda.time;

import java.io.Serializable;
import java.util.Locale;
import org.joda.time.field.AbstractPartialFieldProperty;

public class YearMonth$Property extends AbstractPartialFieldProperty implements Serializable {
    private static final long serialVersionUID = 5727734012190224363L;
    private final YearMonth iBase;
    private final int iFieldIndex;

    YearMonth$Property(YearMonth yearMonth, int i) {
        this.iBase = yearMonth;
        this.iFieldIndex = i;
    }

    public DateTimeField getField() {
        return this.iBase.getField(this.iFieldIndex);
    }

    protected ReadablePartial getReadablePartial() {
        return this.iBase;
    }

    public YearMonth getYearMonth() {
        return this.iBase;
    }

    public int get() {
        return this.iBase.getValue(this.iFieldIndex);
    }

    public YearMonth addToCopy(int i) {
        return new YearMonth(this.iBase, getField().add(this.iBase, this.iFieldIndex, this.iBase.getValues(), i));
    }

    public YearMonth addWrapFieldToCopy(int i) {
        return new YearMonth(this.iBase, getField().addWrapField(this.iBase, this.iFieldIndex, this.iBase.getValues(), i));
    }

    public YearMonth setCopy(int i) {
        return new YearMonth(this.iBase, getField().set(this.iBase, this.iFieldIndex, this.iBase.getValues(), i));
    }

    public YearMonth setCopy(String str, Locale locale) {
        return new YearMonth(this.iBase, getField().set(this.iBase, this.iFieldIndex, this.iBase.getValues(), str, locale));
    }

    public YearMonth setCopy(String str) {
        return setCopy(str, null);
    }
}
