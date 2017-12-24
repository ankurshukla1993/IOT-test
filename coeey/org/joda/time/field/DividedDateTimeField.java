package org.joda.time.field;

import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DurationField;

public class DividedDateTimeField extends DecoratedDateTimeField {
    private static final long serialVersionUID = 8318475124230605365L;
    final int iDivisor;
    final DurationField iDurationField;
    private final int iMax;
    private final int iMin;
    final DurationField iRangeDurationField;

    public DividedDateTimeField(DateTimeField dateTimeField, DateTimeFieldType dateTimeFieldType, int i) {
        this(dateTimeField, dateTimeField.getRangeDurationField(), dateTimeFieldType, i);
    }

    public DividedDateTimeField(DateTimeField dateTimeField, DurationField durationField, DateTimeFieldType dateTimeFieldType, int i) {
        super(dateTimeField, dateTimeFieldType);
        if (i < 2) {
            throw new IllegalArgumentException("The divisor must be at least 2");
        }
        DurationField durationField2 = dateTimeField.getDurationField();
        if (durationField2 == null) {
            this.iDurationField = null;
        } else {
            this.iDurationField = new ScaledDurationField(durationField2, dateTimeFieldType.getDurationType(), i);
        }
        this.iRangeDurationField = durationField;
        this.iDivisor = i;
        int minimumValue = dateTimeField.getMinimumValue();
        minimumValue = minimumValue >= 0 ? minimumValue / i : ((minimumValue + 1) / i) - 1;
        int maximumValue = dateTimeField.getMaximumValue();
        maximumValue = maximumValue >= 0 ? maximumValue / i : ((maximumValue + 1) / i) - 1;
        this.iMin = minimumValue;
        this.iMax = maximumValue;
    }

    public DividedDateTimeField(RemainderDateTimeField remainderDateTimeField, DateTimeFieldType dateTimeFieldType) {
        this(remainderDateTimeField, null, dateTimeFieldType);
    }

    public DividedDateTimeField(RemainderDateTimeField remainderDateTimeField, DurationField durationField, DateTimeFieldType dateTimeFieldType) {
        super(remainderDateTimeField.getWrappedField(), dateTimeFieldType);
        int i = remainderDateTimeField.iDivisor;
        this.iDivisor = i;
        this.iDurationField = remainderDateTimeField.iRangeField;
        this.iRangeDurationField = durationField;
        DateTimeField wrappedField = getWrappedField();
        int minimumValue = wrappedField.getMinimumValue();
        minimumValue = minimumValue >= 0 ? minimumValue / i : ((minimumValue + 1) / i) - 1;
        int maximumValue = wrappedField.getMaximumValue();
        i = maximumValue >= 0 ? maximumValue / i : ((maximumValue + 1) / i) - 1;
        this.iMin = minimumValue;
        this.iMax = i;
    }

    public DurationField getRangeDurationField() {
        if (this.iRangeDurationField != null) {
            return this.iRangeDurationField;
        }
        return super.getRangeDurationField();
    }

    public int get(long j) {
        int i = getWrappedField().get(j);
        if (i >= 0) {
            return i / this.iDivisor;
        }
        return ((i + 1) / this.iDivisor) - 1;
    }

    public long add(long j, int i) {
        return getWrappedField().add(j, this.iDivisor * i);
    }

    public long add(long j, long j2) {
        return getWrappedField().add(j, ((long) this.iDivisor) * j2);
    }

    public long addWrapField(long j, int i) {
        return set(j, FieldUtils.getWrappedValue(get(j), i, this.iMin, this.iMax));
    }

    public int getDifference(long j, long j2) {
        return getWrappedField().getDifference(j, j2) / this.iDivisor;
    }

    public long getDifferenceAsLong(long j, long j2) {
        return getWrappedField().getDifferenceAsLong(j, j2) / ((long) this.iDivisor);
    }

    public long set(long j, int i) {
        FieldUtils.verifyValueBounds((DateTimeField) this, i, this.iMin, this.iMax);
        return getWrappedField().set(j, getRemainder(getWrappedField().get(j)) + (this.iDivisor * i));
    }

    public DurationField getDurationField() {
        return this.iDurationField;
    }

    public int getMinimumValue() {
        return this.iMin;
    }

    public int getMaximumValue() {
        return this.iMax;
    }

    public long roundFloor(long j) {
        DateTimeField wrappedField = getWrappedField();
        return wrappedField.roundFloor(wrappedField.set(j, get(j) * this.iDivisor));
    }

    public long remainder(long j) {
        return set(j, get(getWrappedField().remainder(j)));
    }

    public int getDivisor() {
        return this.iDivisor;
    }

    private int getRemainder(int i) {
        if (i >= 0) {
            return i % this.iDivisor;
        }
        return (this.iDivisor - 1) + ((i + 1) % this.iDivisor);
    }
}
