package org.joda.time.format;

import java.util.Locale;
import org.joda.time.DateTimeField;

class DateTimeParserBucket$SavedField implements Comparable<DateTimeParserBucket$SavedField> {
    DateTimeField iField;
    Locale iLocale;
    String iText;
    int iValue;

    DateTimeParserBucket$SavedField() {
    }

    void init(DateTimeField dateTimeField, int i) {
        this.iField = dateTimeField;
        this.iValue = i;
        this.iText = null;
        this.iLocale = null;
    }

    void init(DateTimeField dateTimeField, String str, Locale locale) {
        this.iField = dateTimeField;
        this.iValue = 0;
        this.iText = str;
        this.iLocale = locale;
    }

    long set(long j, boolean z) {
        long extended;
        if (this.iText == null) {
            extended = this.iField.setExtended(j, this.iValue);
        } else {
            extended = this.iField.set(j, this.iText, this.iLocale);
        }
        if (z) {
            return this.iField.roundFloor(extended);
        }
        return extended;
    }

    public int compareTo(DateTimeParserBucket$SavedField dateTimeParserBucket$SavedField) {
        DateTimeField dateTimeField = dateTimeParserBucket$SavedField.iField;
        int compareReverse = DateTimeParserBucket.compareReverse(this.iField.getRangeDurationField(), dateTimeField.getRangeDurationField());
        return compareReverse != 0 ? compareReverse : DateTimeParserBucket.compareReverse(this.iField.getDurationField(), dateTimeField.getDurationField());
    }
}
