package com.cooey.api;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/* compiled from: ApiClient */
class DateTimeTypeAdapter extends TypeAdapter<DateTime> {
    private final DateTimeFormatter parseFormatter = ISODateTimeFormat.dateOptionalTimeParser();
    private final DateTimeFormatter printFormatter = ISODateTimeFormat.dateTime();

    DateTimeTypeAdapter() {
    }

    public void write(JsonWriter out, DateTime date) throws IOException {
        if (date == null) {
            out.nullValue();
        } else {
            out.value(this.printFormatter.print(date));
        }
    }

    public DateTime read(JsonReader in) throws IOException {
        switch (in.peek()) {
            case NULL:
                in.nextNull();
                return null;
            default:
                return this.parseFormatter.parseDateTime(in.nextString());
        }
    }
}
