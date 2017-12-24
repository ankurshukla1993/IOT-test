package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

public final class DateTime$Property extends AbstractReadableInstantFieldProperty {
    private static final long serialVersionUID = -6983323811635733510L;
    private DateTimeField iField;
    private DateTime iInstant;

    DateTime$Property(DateTime dateTime, DateTimeField dateTimeField) {
        this.iInstant = dateTime;
        this.iField = dateTimeField;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.iInstant);
        objectOutputStream.writeObject(this.iField.getType());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.iInstant = (DateTime) objectInputStream.readObject();
        this.iField = ((DateTimeFieldType) objectInputStream.readObject()).getField(this.iInstant.getChronology());
    }

    public DateTimeField getField() {
        return this.iField;
    }

    protected long getMillis() {
        return this.iInstant.getMillis();
    }

    protected Chronology getChronology() {
        return this.iInstant.getChronology();
    }

    public DateTime getDateTime() {
        return this.iInstant;
    }

    public DateTime addToCopy(int i) {
        return this.iInstant.withMillis(this.iField.add(this.iInstant.getMillis(), i));
    }

    public DateTime addToCopy(long j) {
        return this.iInstant.withMillis(this.iField.add(this.iInstant.getMillis(), j));
    }

    public DateTime addWrapFieldToCopy(int i) {
        return this.iInstant.withMillis(this.iField.addWrapField(this.iInstant.getMillis(), i));
    }

    public DateTime setCopy(int i) {
        return this.iInstant.withMillis(this.iField.set(this.iInstant.getMillis(), i));
    }

    public DateTime setCopy(String str, Locale locale) {
        return this.iInstant.withMillis(this.iField.set(this.iInstant.getMillis(), str, locale));
    }

    public DateTime setCopy(String str) {
        return setCopy(str, null);
    }

    public DateTime withMaximumValue() {
        try {
            return setCopy(getMaximumValue());
        } catch (Throwable e) {
            if (IllegalInstantException.isIllegalInstant(e)) {
                return new DateTime(getChronology().getZone().previousTransition(getMillis() + 86400000), getChronology());
            }
            throw e;
        }
    }

    public DateTime withMinimumValue() {
        try {
            return setCopy(getMinimumValue());
        } catch (Throwable e) {
            if (IllegalInstantException.isIllegalInstant(e)) {
                return new DateTime(getChronology().getZone().nextTransition(getMillis() - 86400000), getChronology());
            }
            throw e;
        }
    }

    public DateTime roundFloorCopy() {
        return this.iInstant.withMillis(this.iField.roundFloor(this.iInstant.getMillis()));
    }

    public DateTime roundCeilingCopy() {
        return this.iInstant.withMillis(this.iField.roundCeiling(this.iInstant.getMillis()));
    }

    public DateTime roundHalfFloorCopy() {
        return this.iInstant.withMillis(this.iField.roundHalfFloor(this.iInstant.getMillis()));
    }

    public DateTime roundHalfCeilingCopy() {
        return this.iInstant.withMillis(this.iField.roundHalfCeiling(this.iInstant.getMillis()));
    }

    public DateTime roundHalfEvenCopy() {
        return this.iInstant.withMillis(this.iField.roundHalfEven(this.iInstant.getMillis()));
    }
}
