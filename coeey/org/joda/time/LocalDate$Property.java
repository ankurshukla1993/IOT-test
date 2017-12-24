package org.joda.time;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.joda.time.field.AbstractReadableInstantFieldProperty;

public final class LocalDate$Property extends AbstractReadableInstantFieldProperty {
    private static final long serialVersionUID = -3193829732634L;
    private transient DateTimeField iField;
    private transient LocalDate iInstant;

    LocalDate$Property(LocalDate localDate, DateTimeField dateTimeField) {
        this.iInstant = localDate;
        this.iField = dateTimeField;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.iInstant);
        objectOutputStream.writeObject(this.iField.getType());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        this.iInstant = (LocalDate) objectInputStream.readObject();
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

    public LocalDate getLocalDate() {
        return this.iInstant;
    }

    public LocalDate addToCopy(int i) {
        return this.iInstant.withLocalMillis(this.iField.add(this.iInstant.getLocalMillis(), i));
    }

    public LocalDate addWrapFieldToCopy(int i) {
        return this.iInstant.withLocalMillis(this.iField.addWrapField(this.iInstant.getLocalMillis(), i));
    }

    public LocalDate setCopy(int i) {
        return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), i));
    }

    public LocalDate setCopy(String str, Locale locale) {
        return this.iInstant.withLocalMillis(this.iField.set(this.iInstant.getLocalMillis(), str, locale));
    }

    public LocalDate setCopy(String str) {
        return setCopy(str, null);
    }

    public LocalDate withMaximumValue() {
        return setCopy(getMaximumValue());
    }

    public LocalDate withMinimumValue() {
        return setCopy(getMinimumValue());
    }

    public LocalDate roundFloorCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundFloor(this.iInstant.getLocalMillis()));
    }

    public LocalDate roundCeilingCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundCeiling(this.iInstant.getLocalMillis()));
    }

    public LocalDate roundHalfFloorCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundHalfFloor(this.iInstant.getLocalMillis()));
    }

    public LocalDate roundHalfCeilingCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundHalfCeiling(this.iInstant.getLocalMillis()));
    }

    public LocalDate roundHalfEvenCopy() {
        return this.iInstant.withLocalMillis(this.iField.roundHalfEven(this.iInstant.getLocalMillis()));
    }
}
