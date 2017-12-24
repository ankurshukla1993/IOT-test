package org.joda.time.format;

import org.joda.time.DateTimeZone;

class DateTimeParserBucket$SavedState {
    final Integer iOffset;
    final DateTimeParserBucket$SavedField[] iSavedFields;
    final int iSavedFieldsCount;
    final DateTimeZone iZone;
    final /* synthetic */ DateTimeParserBucket this$0;

    DateTimeParserBucket$SavedState(DateTimeParserBucket dateTimeParserBucket) {
        this.this$0 = dateTimeParserBucket;
        this.iZone = DateTimeParserBucket.access$000(dateTimeParserBucket);
        this.iOffset = DateTimeParserBucket.access$100(dateTimeParserBucket);
        this.iSavedFields = DateTimeParserBucket.access$200(dateTimeParserBucket);
        this.iSavedFieldsCount = DateTimeParserBucket.access$300(dateTimeParserBucket);
    }

    boolean restoreState(DateTimeParserBucket dateTimeParserBucket) {
        if (dateTimeParserBucket != this.this$0) {
            return false;
        }
        DateTimeParserBucket.access$002(dateTimeParserBucket, this.iZone);
        DateTimeParserBucket.access$102(dateTimeParserBucket, this.iOffset);
        DateTimeParserBucket.access$202(dateTimeParserBucket, this.iSavedFields);
        if (this.iSavedFieldsCount < DateTimeParserBucket.access$300(dateTimeParserBucket)) {
            DateTimeParserBucket.access$402(dateTimeParserBucket, true);
        }
        DateTimeParserBucket.access$302(dateTimeParserBucket, this.iSavedFieldsCount);
        return true;
    }
}
