package org.joda.time.base;

import java.io.Serializable;
import org.joda.time.Chronology;
import org.joda.time.DateTimeUtils;
import org.joda.time.Duration;
import org.joda.time.DurationFieldType;
import org.joda.time.MutablePeriod;
import org.joda.time.PeriodType;
import org.joda.time.ReadWritablePeriod;
import org.joda.time.ReadableDuration;
import org.joda.time.ReadableInstant;
import org.joda.time.ReadablePartial;
import org.joda.time.ReadablePeriod;
import org.joda.time.chrono.ISOChronology;
import org.joda.time.convert.ConverterManager;
import org.joda.time.convert.PeriodConverter;
import org.joda.time.field.FieldUtils;

public abstract class BasePeriod extends AbstractPeriod implements ReadablePeriod, Serializable {
    private static final ReadablePeriod DUMMY_PERIOD = new C25551();
    private static final long serialVersionUID = -2110953284060001145L;
    private final PeriodType iType;
    private final int[] iValues;

    static class C25551 extends AbstractPeriod {
        public int getValue(int i) {
            return 0;
        }

        C25551() {
        }

        public PeriodType getPeriodType() {
            return PeriodType.time();
        }
    }

    protected BasePeriod(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, PeriodType periodType) {
        this.iType = checkPeriodType(periodType);
        this.iValues = setPeriodInternal(i, i2, i3, i4, i5, i6, i7, i8);
    }

    protected BasePeriod(long j, long j2, PeriodType periodType, Chronology chronology) {
        PeriodType checkPeriodType = checkPeriodType(periodType);
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        this.iType = checkPeriodType;
        this.iValues = chronology2.get(this, j, j2);
    }

    protected BasePeriod(ReadableInstant readableInstant, ReadableInstant readableInstant2, PeriodType periodType) {
        PeriodType checkPeriodType = checkPeriodType(periodType);
        if (readableInstant == null && readableInstant2 == null) {
            this.iType = checkPeriodType;
            this.iValues = new int[size()];
            return;
        }
        long instantMillis = DateTimeUtils.getInstantMillis(readableInstant);
        long instantMillis2 = DateTimeUtils.getInstantMillis(readableInstant2);
        Chronology intervalChronology = DateTimeUtils.getIntervalChronology(readableInstant, readableInstant2);
        this.iType = checkPeriodType;
        this.iValues = intervalChronology.get(this, instantMillis, instantMillis2);
    }

    protected BasePeriod(ReadablePartial readablePartial, ReadablePartial readablePartial2, PeriodType periodType) {
        if (readablePartial == null || readablePartial2 == null) {
            throw new IllegalArgumentException("ReadablePartial objects must not be null");
        } else if ((readablePartial instanceof BaseLocal) && (readablePartial2 instanceof BaseLocal) && readablePartial.getClass() == readablePartial2.getClass()) {
            PeriodType checkPeriodType = checkPeriodType(periodType);
            long localMillis = ((BaseLocal) readablePartial).getLocalMillis();
            long localMillis2 = ((BaseLocal) readablePartial2).getLocalMillis();
            r0 = DateTimeUtils.getChronology(readablePartial.getChronology());
            this.iType = checkPeriodType;
            this.iValues = r0.get(this, localMillis, localMillis2);
        } else if (readablePartial.size() != readablePartial2.size()) {
            throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
        } else {
            int size = readablePartial.size();
            for (int i = 0; i < size; i++) {
                if (readablePartial.getFieldType(i) != readablePartial2.getFieldType(i)) {
                    throw new IllegalArgumentException("ReadablePartial objects must have the same set of fields");
                }
            }
            if (DateTimeUtils.isContiguous(readablePartial)) {
                this.iType = checkPeriodType(periodType);
                r0 = DateTimeUtils.getChronology(readablePartial.getChronology()).withUTC();
                this.iValues = r0.get(this, r0.set(readablePartial, 0), r0.set(readablePartial2, 0));
                return;
            }
            throw new IllegalArgumentException("ReadablePartial objects must be contiguous");
        }
    }

    protected BasePeriod(ReadableInstant readableInstant, ReadableDuration readableDuration, PeriodType periodType) {
        PeriodType checkPeriodType = checkPeriodType(periodType);
        long instantMillis = DateTimeUtils.getInstantMillis(readableInstant);
        long safeAdd = FieldUtils.safeAdd(instantMillis, DateTimeUtils.getDurationMillis(readableDuration));
        Chronology instantChronology = DateTimeUtils.getInstantChronology(readableInstant);
        this.iType = checkPeriodType;
        this.iValues = instantChronology.get(this, instantMillis, safeAdd);
    }

    protected BasePeriod(ReadableDuration readableDuration, ReadableInstant readableInstant, PeriodType periodType) {
        PeriodType checkPeriodType = checkPeriodType(periodType);
        long durationMillis = DateTimeUtils.getDurationMillis(readableDuration);
        long instantMillis = DateTimeUtils.getInstantMillis(readableInstant);
        durationMillis = FieldUtils.safeSubtract(instantMillis, durationMillis);
        Chronology instantChronology = DateTimeUtils.getInstantChronology(readableInstant);
        this.iType = checkPeriodType;
        this.iValues = instantChronology.get(this, durationMillis, instantMillis);
    }

    protected BasePeriod(long j) {
        this.iType = PeriodType.standard();
        Object obj = ISOChronology.getInstanceUTC().get(DUMMY_PERIOD, j);
        this.iValues = new int[8];
        System.arraycopy(obj, 0, this.iValues, 4, 4);
    }

    protected BasePeriod(long j, PeriodType periodType, Chronology chronology) {
        PeriodType checkPeriodType = checkPeriodType(periodType);
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        this.iType = checkPeriodType;
        this.iValues = chronology2.get((ReadablePeriod) this, j);
    }

    protected BasePeriod(Object obj, PeriodType periodType, Chronology chronology) {
        PeriodConverter periodConverter = ConverterManager.getInstance().getPeriodConverter(obj);
        if (periodType == null) {
            periodType = periodConverter.getPeriodType(obj);
        }
        PeriodType checkPeriodType = checkPeriodType(periodType);
        this.iType = checkPeriodType;
        if (this instanceof ReadWritablePeriod) {
            this.iValues = new int[size()];
            periodConverter.setInto((ReadWritablePeriod) this, obj, DateTimeUtils.getChronology(chronology));
            return;
        }
        this.iValues = new MutablePeriod(obj, checkPeriodType, chronology).getValues();
    }

    protected BasePeriod(int[] iArr, PeriodType periodType) {
        this.iType = periodType;
        this.iValues = iArr;
    }

    protected PeriodType checkPeriodType(PeriodType periodType) {
        return DateTimeUtils.getPeriodType(periodType);
    }

    public PeriodType getPeriodType() {
        return this.iType;
    }

    public int getValue(int i) {
        return this.iValues[i];
    }

    public Duration toDurationFrom(ReadableInstant readableInstant) {
        long instantMillis = DateTimeUtils.getInstantMillis(readableInstant);
        return new Duration(instantMillis, DateTimeUtils.getInstantChronology(readableInstant).add((ReadablePeriod) this, instantMillis, 1));
    }

    public Duration toDurationTo(ReadableInstant readableInstant) {
        long instantMillis = DateTimeUtils.getInstantMillis(readableInstant);
        return new Duration(DateTimeUtils.getInstantChronology(readableInstant).add((ReadablePeriod) this, instantMillis, -1), instantMillis);
    }

    private void checkAndUpdate(DurationFieldType durationFieldType, int[] iArr, int i) {
        int indexOf = indexOf(durationFieldType);
        if (indexOf != -1) {
            iArr[indexOf] = i;
        } else if (i != 0) {
            throw new IllegalArgumentException("Period does not support field '" + durationFieldType.getName() + "'");
        }
    }

    protected void setPeriod(ReadablePeriod readablePeriod) {
        if (readablePeriod == null) {
            setValues(new int[size()]);
        } else {
            setPeriodInternal(readablePeriod);
        }
    }

    private void setPeriodInternal(ReadablePeriod readablePeriod) {
        int[] iArr = new int[size()];
        int size = readablePeriod.size();
        for (int i = 0; i < size; i++) {
            checkAndUpdate(readablePeriod.getFieldType(i), iArr, readablePeriod.getValue(i));
        }
        setValues(iArr);
    }

    protected void setPeriod(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        setValues(setPeriodInternal(i, i2, i3, i4, i5, i6, i7, i8));
    }

    private int[] setPeriodInternal(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        int[] iArr = new int[size()];
        checkAndUpdate(DurationFieldType.years(), iArr, i);
        checkAndUpdate(DurationFieldType.months(), iArr, i2);
        checkAndUpdate(DurationFieldType.weeks(), iArr, i3);
        checkAndUpdate(DurationFieldType.days(), iArr, i4);
        checkAndUpdate(DurationFieldType.hours(), iArr, i5);
        checkAndUpdate(DurationFieldType.minutes(), iArr, i6);
        checkAndUpdate(DurationFieldType.seconds(), iArr, i7);
        checkAndUpdate(DurationFieldType.millis(), iArr, i8);
        return iArr;
    }

    protected void setField(DurationFieldType durationFieldType, int i) {
        setFieldInto(this.iValues, durationFieldType, i);
    }

    protected void setFieldInto(int[] iArr, DurationFieldType durationFieldType, int i) {
        int indexOf = indexOf(durationFieldType);
        if (indexOf != -1) {
            iArr[indexOf] = i;
        } else if (i != 0 || durationFieldType == null) {
            throw new IllegalArgumentException("Period does not support field '" + durationFieldType + "'");
        }
    }

    protected void addField(DurationFieldType durationFieldType, int i) {
        addFieldInto(this.iValues, durationFieldType, i);
    }

    protected void addFieldInto(int[] iArr, DurationFieldType durationFieldType, int i) {
        int indexOf = indexOf(durationFieldType);
        if (indexOf != -1) {
            iArr[indexOf] = FieldUtils.safeAdd(iArr[indexOf], i);
        } else if (i != 0 || durationFieldType == null) {
            throw new IllegalArgumentException("Period does not support field '" + durationFieldType + "'");
        }
    }

    protected void mergePeriod(ReadablePeriod readablePeriod) {
        if (readablePeriod != null) {
            setValues(mergePeriodInto(getValues(), readablePeriod));
        }
    }

    protected int[] mergePeriodInto(int[] iArr, ReadablePeriod readablePeriod) {
        int size = readablePeriod.size();
        for (int i = 0; i < size; i++) {
            checkAndUpdate(readablePeriod.getFieldType(i), iArr, readablePeriod.getValue(i));
        }
        return iArr;
    }

    protected void addPeriod(ReadablePeriod readablePeriod) {
        if (readablePeriod != null) {
            setValues(addPeriodInto(getValues(), readablePeriod));
        }
    }

    protected int[] addPeriodInto(int[] iArr, ReadablePeriod readablePeriod) {
        int size = readablePeriod.size();
        for (int i = 0; i < size; i++) {
            DurationFieldType fieldType = readablePeriod.getFieldType(i);
            int value = readablePeriod.getValue(i);
            if (value != 0) {
                int indexOf = indexOf(fieldType);
                if (indexOf == -1) {
                    throw new IllegalArgumentException("Period does not support field '" + fieldType.getName() + "'");
                }
                iArr[indexOf] = FieldUtils.safeAdd(getValue(indexOf), value);
            }
        }
        return iArr;
    }

    protected void setValue(int i, int i2) {
        this.iValues[i] = i2;
    }

    protected void setValues(int[] iArr) {
        System.arraycopy(iArr, 0, this.iValues, 0, this.iValues.length);
    }
}
