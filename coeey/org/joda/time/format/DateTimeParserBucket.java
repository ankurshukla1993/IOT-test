package org.joda.time.format;

import com.google.android.gms.auth.api.credentials.CredentialsApi;
import java.util.Arrays;
import java.util.Locale;
import kotlin.text.Typography;
import org.joda.time.Chronology;
import org.joda.time.DateTimeField;
import org.joda.time.DateTimeFieldType;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.DurationField;
import org.joda.time.DurationFieldType;
import org.joda.time.IllegalFieldValueException;
import org.joda.time.IllegalInstantException;

public class DateTimeParserBucket {
    private final Chronology iChrono;
    private final Integer iDefaultPivotYear;
    private final int iDefaultYear;
    private final DateTimeZone iDefaultZone;
    private final Locale iLocale;
    private final long iMillis;
    private Integer iOffset;
    private Integer iPivotYear;
    private SavedField[] iSavedFields;
    private int iSavedFieldsCount;
    private boolean iSavedFieldsShared;
    private Object iSavedState;
    private DateTimeZone iZone;

    @Deprecated
    public DateTimeParserBucket(long j, Chronology chronology, Locale locale) {
        this(j, chronology, locale, null, CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE);
    }

    @Deprecated
    public DateTimeParserBucket(long j, Chronology chronology, Locale locale, Integer num) {
        this(j, chronology, locale, num, CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE);
    }

    public DateTimeParserBucket(long j, Chronology chronology, Locale locale, Integer num, int i) {
        Chronology chronology2 = DateTimeUtils.getChronology(chronology);
        this.iMillis = j;
        this.iDefaultZone = chronology2.getZone();
        this.iChrono = chronology2.withUTC();
        if (locale == null) {
            locale = Locale.getDefault();
        }
        this.iLocale = locale;
        this.iDefaultYear = i;
        this.iDefaultPivotYear = num;
        this.iZone = this.iDefaultZone;
        this.iPivotYear = this.iDefaultPivotYear;
        this.iSavedFields = new SavedField[8];
    }

    public void reset() {
        this.iZone = this.iDefaultZone;
        this.iOffset = null;
        this.iPivotYear = this.iDefaultPivotYear;
        this.iSavedFieldsCount = 0;
        this.iSavedFieldsShared = false;
        this.iSavedState = null;
    }

    public long parseMillis(DateTimeParser dateTimeParser, CharSequence charSequence) {
        reset();
        return doParseMillis(DateTimeParserInternalParser.of(dateTimeParser), charSequence);
    }

    long doParseMillis(InternalParser internalParser, CharSequence charSequence) {
        int parseInto = internalParser.parseInto(this, charSequence, 0);
        if (parseInto < 0) {
            parseInto ^= -1;
        } else if (parseInto >= charSequence.length()) {
            return computeMillis(true, charSequence);
        }
        throw new IllegalArgumentException(FormatUtils.createErrorMessage(charSequence.toString(), parseInto));
    }

    public Chronology getChronology() {
        return this.iChrono;
    }

    public Locale getLocale() {
        return this.iLocale;
    }

    public DateTimeZone getZone() {
        return this.iZone;
    }

    public void setZone(DateTimeZone dateTimeZone) {
        this.iSavedState = null;
        this.iZone = dateTimeZone;
    }

    @Deprecated
    public int getOffset() {
        return this.iOffset != null ? this.iOffset.intValue() : 0;
    }

    public Integer getOffsetInteger() {
        return this.iOffset;
    }

    @Deprecated
    public void setOffset(int i) {
        this.iSavedState = null;
        this.iOffset = Integer.valueOf(i);
    }

    public void setOffset(Integer num) {
        this.iSavedState = null;
        this.iOffset = num;
    }

    public Integer getPivotYear() {
        return this.iPivotYear;
    }

    @Deprecated
    public void setPivotYear(Integer num) {
        this.iPivotYear = num;
    }

    public void saveField(DateTimeField dateTimeField, int i) {
        obtainSaveField().init(dateTimeField, i);
    }

    public void saveField(DateTimeFieldType dateTimeFieldType, int i) {
        obtainSaveField().init(dateTimeFieldType.getField(this.iChrono), i);
    }

    public void saveField(DateTimeFieldType dateTimeFieldType, String str, Locale locale) {
        obtainSaveField().init(dateTimeFieldType.getField(this.iChrono), str, locale);
    }

    private SavedField obtainSaveField() {
        Object obj;
        SavedField savedField;
        Object obj2 = this.iSavedFields;
        int i = this.iSavedFieldsCount;
        if (i == obj2.length || this.iSavedFieldsShared) {
            obj = new SavedField[(i == obj2.length ? i * 2 : obj2.length)];
            System.arraycopy(obj2, 0, obj, 0, i);
            this.iSavedFields = obj;
            this.iSavedFieldsShared = false;
        } else {
            obj = obj2;
        }
        this.iSavedState = null;
        SavedField savedField2 = obj[i];
        if (savedField2 == null) {
            savedField2 = new SavedField();
            obj[i] = savedField2;
            savedField = savedField2;
        } else {
            savedField = savedField2;
        }
        this.iSavedFieldsCount = i + 1;
        return savedField;
    }

    public Object saveState() {
        if (this.iSavedState == null) {
            this.iSavedState = new SavedState(this);
        }
        return this.iSavedState;
    }

    public boolean restoreState(Object obj) {
        if (!(obj instanceof SavedState) || !((SavedState) obj).restoreState(this)) {
            return false;
        }
        this.iSavedState = obj;
        return true;
    }

    public long computeMillis() {
        return computeMillis(false, (CharSequence) null);
    }

    public long computeMillis(boolean z) {
        return computeMillis(z, (CharSequence) null);
    }

    public long computeMillis(boolean z, String str) {
        return computeMillis(z, (CharSequence) str);
    }

    public long computeMillis(boolean z, CharSequence charSequence) {
        SavedField[] savedFieldArr = this.iSavedFields;
        int i = this.iSavedFieldsCount;
        if (this.iSavedFieldsShared) {
            savedFieldArr = (SavedField[]) this.iSavedFields.clone();
            this.iSavedFields = savedFieldArr;
            this.iSavedFieldsShared = false;
        }
        sort(savedFieldArr, i);
        if (i > 0) {
            DurationField field = DurationFieldType.months().getField(this.iChrono);
            DurationField field2 = DurationFieldType.days().getField(this.iChrono);
            DurationField durationField = savedFieldArr[0].iField.getDurationField();
            if (compareReverse(durationField, field) >= 0 && compareReverse(durationField, field2) <= 0) {
                saveField(DateTimeFieldType.year(), this.iDefaultYear);
                return computeMillis(z, charSequence);
            }
        }
        long j = this.iMillis;
        int i2 = 0;
        while (i2 < i) {
            try {
                j = savedFieldArr[i2].set(j, z);
                i2++;
            } catch (IllegalFieldValueException e) {
                if (charSequence != null) {
                    e.prependMessage("Cannot parse \"" + charSequence + Typography.quote);
                }
                throw e;
            }
        }
        if (z) {
            for (int i3 = 0; i3 < i; i3++) {
                boolean z2;
                SavedField savedField = savedFieldArr[i3];
                if (i3 == i - 1) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                j = savedField.set(j, z2);
            }
        }
        long j2 = j;
        if (this.iOffset != null) {
            return j2 - ((long) this.iOffset.intValue());
        }
        if (this.iZone == null) {
            return j2;
        }
        int offsetFromLocal = this.iZone.getOffsetFromLocal(j2);
        j2 -= (long) offsetFromLocal;
        if (offsetFromLocal == this.iZone.getOffset(j2)) {
            return j2;
        }
        String str = "Illegal instant due to time zone offset transition (" + this.iZone + ')';
        if (charSequence != null) {
            str = "Cannot parse \"" + charSequence + "\": " + str;
        }
        throw new IllegalInstantException(str);
    }

    private static void sort(SavedField[] savedFieldArr, int i) {
        int i2 = 0;
        if (i > 10) {
            Arrays.sort(savedFieldArr, 0, i);
            return;
        }
        while (i2 < i) {
            int i3 = i2;
            while (i3 > 0 && savedFieldArr[i3 - 1].compareTo(savedFieldArr[i3]) > 0) {
                SavedField savedField = savedFieldArr[i3];
                savedFieldArr[i3] = savedFieldArr[i3 - 1];
                savedFieldArr[i3 - 1] = savedField;
                i3--;
            }
            i2++;
        }
    }

    static int compareReverse(DurationField durationField, DurationField durationField2) {
        if (durationField == null || !durationField.isSupported()) {
            if (durationField2 == null || !durationField2.isSupported()) {
                return 0;
            }
            return -1;
        } else if (durationField2 == null || !durationField2.isSupported()) {
            return 1;
        } else {
            return -durationField.compareTo(durationField2);
        }
    }
}
