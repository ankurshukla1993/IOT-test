package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

public final class MutableDateTime$Property extends AbstractReadableInstantFieldProperty {
    private static final long serialVersionUID = -4481126543819298617L;
    private DateTimeField iField;
    private MutableDateTime iInstant;

    MutableDateTime$Property(MutableDateTime mutableDateTime, DateTimeField dateTimeField) {
        this.iInstant = mutableDateTime;
        this.iField = dateTimeField;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.iInstant);
        objectOutputStream.writeObject(this.iField.getType());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.iInstant = (MutableDateTime) objectInputStream.readObject();
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

    public MutableDateTime getMutableDateTime() {
        return this.iInstant;
    }

    public MutableDateTime add(int i) {
        this.iInstant.setMillis(getField().add(this.iInstant.getMillis(), i));
        return this.iInstant;
    }

    public MutableDateTime add(long j) {
        this.iInstant.setMillis(getField().add(this.iInstant.getMillis(), j));
        return this.iInstant;
    }

    public MutableDateTime addWrapField(int i) {
        this.iInstant.setMillis(getField().addWrapField(this.iInstant.getMillis(), i));
        return this.iInstant;
    }

    public MutableDateTime set(int i) {
        this.iInstant.setMillis(getField().set(this.iInstant.getMillis(), i));
        return this.iInstant;
    }

    public MutableDateTime set(String str, Locale locale) {
        this.iInstant.setMillis(getField().set(this.iInstant.getMillis(), str, locale));
        return this.iInstant;
    }

    public MutableDateTime set(String str) {
        set(str, null);
        return this.iInstant;
    }

    public MutableDateTime roundFloor() {
        this.iInstant.setMillis(getField().roundFloor(this.iInstant.getMillis()));
        return this.iInstant;
    }

    public MutableDateTime roundCeiling() {
        this.iInstant.setMillis(getField().roundCeiling(this.iInstant.getMillis()));
        return this.iInstant;
    }

    public MutableDateTime roundHalfFloor() {
        this.iInstant.setMillis(getField().roundHalfFloor(this.iInstant.getMillis()));
        return this.iInstant;
    }

    public MutableDateTime roundHalfCeiling() {
        this.iInstant.setMillis(getField().roundHalfCeiling(this.iInstant.getMillis()));
        return this.iInstant;
    }

    public MutableDateTime roundHalfEven() {
        this.iInstant.setMillis(getField().roundHalfEven(this.iInstant.getMillis()));
        return this.iInstant;
    }
}
