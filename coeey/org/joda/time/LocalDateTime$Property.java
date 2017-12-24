package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

public final class LocalDateTime$Property extends AbstractReadableInstantFieldProperty {
    private static final long serialVersionUID = -358138762846288L;
    private transient DateTimeField iField;
    private transient LocalDateTime iInstant;

    LocalDateTime$Property(LocalDateTime localDateTime, DateTimeField dateTimeField) {
        this.iInstant = localDateTime;
        this.iField = dateTimeField;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.iInstant);
        objectOutputStream.writeObject(this.iField.getType());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.iInstant = (LocalDateTime) objectInputStream.readObject();
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

    public LocalDateTime getLocalDateTime() {
        return this.iInstant;
    }

    public LocalDateTime addToCopy(int i) {
        return this.iInstant.withLocalMillis(this.iField.add(this.iInstant.getLocalMillis(), i));
    }

    public LocalDateTime addToCopy(long j) {
        return this.iInstant.withLocalMillis(this.iField.add(this.iInstant.getLocalMillis(), j));
    }

    public LocalDateTime addWrapFieldToCopy(int i) {
        return this.iInstant.withLocalMillis(this.iField.addWrapField(this.iInstant.getLocalMillis(), i));
    }

    public LocalDateTime setCopy(int i) {
        return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), i));
    }

    public LocalDateTime setCopy(String str, Locale locale) {
        return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), str, locale));
    }

    public LocalDateTime setCopy(String str) {
        return setCopy(str, null);
    }

    public LocalDateTime withMaximumValue() {
        return setCopy(getMaximumValue());
    }

    public LocalDateTime withMinimumValue() {
        return setCopy(getMinimumValue());
    }

    public LocalDateTime roundFloorCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundFloor(this.iInstant.getLocalMillis()));
    }

    public LocalDateTime roundCeilingCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundCeiling(this.iInstant.getLocalMillis()));
    }

    public LocalDateTime roundHalfFloorCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundHalfFloor(this.iInstant.getLocalMillis()));
    }

    public LocalDateTime roundHalfCeilingCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundHalfCeiling(this.iInstant.getLocalMillis()));
    }

    public LocalDateTime roundHalfEvenCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundHalfEven(this.iInstant.getLocalMillis()));
    }
}
