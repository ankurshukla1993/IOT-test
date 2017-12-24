package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

public final class LocalTime$Property extends AbstractReadableInstantFieldProperty {
    private static final long serialVersionUID = -325842547277223L;
    private transient DateTimeField iField;
    private transient LocalTime iInstant;

    LocalTime$Property(LocalTime localTime, DateTimeField dateTimeField) {
        this.iInstant = localTime;
        this.iField = dateTimeField;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.iInstant);
        objectOutputStream.writeObject(this.iField.getType());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.iInstant = (LocalTime) objectInputStream.readObject();
        this.iField = ((DateTimeFieldType) objectInputStream.readObject()).getField(this.iInstant.getChronology());
    }

    public DateTimeField getField() {
        return this.iField;
    }

    protected long getMillis() {
        return this.iInstant.getLocalMillis();
    }

    protected Chronology getChronology() {
        return this.iInstant.getChronology();
    }

    public LocalTime getLocalTime() {
        return this.iInstant;
    }

    public LocalTime addCopy(int i) {
        return this.iInstant.withLocalMillis(this.iField.add(this.iInstant.getLocalMillis(), i));
    }

    public LocalTime addCopy(long j) {
        return this.iInstant.withLocalMillis(this.iField.add(this.iInstant.getLocalMillis(), j));
    }

    public LocalTime addNoWrapToCopy(int i) {
        long add = this.iField.add(this.iInstant.getLocalMillis(), i);
        if (((long) this.iInstant.getChronology().millisOfDay().get(add)) == add) {
            return this.iInstant.withLocalMillis(add);
        }
        throw new IllegalArgumentException("The addition exceeded the boundaries of LocalTime");
    }

    public LocalTime addWrapFieldToCopy(int i) {
        return this.iInstant.withLocalMillis(this.iField.addWrapField(this.iInstant.getLocalMillis(), i));
    }

    public LocalTime setCopy(int i) {
        return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), i));
    }

    public LocalTime setCopy(String str, Locale locale) {
        return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), str, locale));
    }

    public LocalTime setCopy(String str) {
        return setCopy(str, null);
    }

    public LocalTime withMaximumValue() {
        return setCopy(getMaximumValue());
    }

    public LocalTime withMinimumValue() {
        return setCopy(getMinimumValue());
    }

    public LocalTime roundFloorCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundFloor(this.iInstant.getLocalMillis()));
    }

    public LocalTime roundCeilingCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundCeiling(this.iInstant.getLocalMillis()));
    }

    public LocalTime roundHalfFloorCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundHalfFloor(this.iInstant.getLocalMillis()));
    }

    public LocalTime roundHalfCeilingCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundHalfCeiling(this.iInstant.getLocalMillis()));
    }

    public LocalTime roundHalfEvenCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundHalfEven(this.iInstant.getLocalMillis()));
    }
}
