package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

public final class DateMidnight$Property extends AbstractReadableInstantFieldProperty {
    private static final long serialVersionUID = 257629620;
    private DateTimeField iField;
    private DateMidnight iInstant;

    DateMidnight$Property(DateMidnight dateMidnight, DateTimeField dateTimeField) {
        this.iInstant = dateMidnight;
        this.iField = dateTimeField;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.iInstant);
        objectOutputStream.writeObject(this.iField.getType());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.iInstant = (DateMidnight) objectInputStream.readObject();
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

    public DateMidnight getDateMidnight() {
        return this.iInstant;
    }

    public DateMidnight addToCopy(int i) {
        return this.iInstant.withMillis(this.iField.add(this.iInstant.getMillis(), i));
    }

    public DateMidnight addToCopy(long j) {
        return this.iInstant.withMillis(this.iField.add(this.iInstant.getMillis(), j));
    }

    public DateMidnight addWrapFieldToCopy(int i) {
        return this.iInstant.withMillis(this.iField.addWrapField(this.iInstant.getMillis(), i));
    }

    public DateMidnight setCopy(int i) {
        return this.iInstant.withMillis(this.iField.set(this.iInstant.getMillis(), i));
    }

    public DateMidnight setCopy(String str, Locale locale) {
        return this.iInstant.withMillis(this.iField.set(this.iInstant.getMillis(), str, locale));
    }

    public DateMidnight setCopy(String str) {
        return setCopy(str, null);
    }

    public DateMidnight withMaximumValue() {
        return setCopy(getMaximumValue());
    }

    public DateMidnight withMinimumValue() {
        return setCopy(getMinimumValue());
    }

    public DateMidnight roundFloorCopy() {
        return this.iInstant.withMillis(this.iField.roundFloor(this.iInstant.getMillis()));
    }

    public DateMidnight roundCeilingCopy() {
        return this.iInstant.withMillis(this.iField.roundCeiling(this.iInstant.getMillis()));
    }

    public DateMidnight roundHalfFloorCopy() {
        return this.iInstant.withMillis(this.iField.roundHalfFloor(this.iInstant.getMillis()));
    }

    public DateMidnight roundHalfCeilingCopy() {
        return this.iInstant.withMillis(this.iField.roundHalfCeiling(this.iInstant.getMillis()));
    }

    public DateMidnight roundHalfEvenCopy() {
        return this.iInstant.withMillis(this.iField.roundHalfEven(this.iInstant.getMillis()));
    }
}
