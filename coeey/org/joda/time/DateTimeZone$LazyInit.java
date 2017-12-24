package org.joda.time;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.chrono.BaseChronology;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;

final class DateTimeZone$LazyInit {
    static final Map<String, String> CONVERSION_MAP = buildMap();
    static final DateTimeFormatter OFFSET_FORMATTER = buildFormatter();

    static class C25541 extends BaseChronology {
        private static final long serialVersionUID = -3128740902654445468L;

        public DateTimeZone getZone() {
            return null;
        }

        public Chronology withUTC() {
            return this;
        }

        public Chronology withZone(DateTimeZone dateTimeZone) {
            return this;
        }

        C25541() {
        }

        public String toString() {
            return getClass().getName();
        }
    }

    DateTimeZone$LazyInit() {
    }

    private static DateTimeFormatter buildFormatter() {
        return new DateTimeFormatterBuilder().appendTimeZoneOffset(null, true, 2, 4).toFormatter().withChronology(new C25541());
    }

    private static Map<String, String> buildMap() {
        Map hashMap = new HashMap();
        hashMap.put("GMT", "UTC");
        hashMap.put("WET", "WET");
        hashMap.put("CET", "CET");
        hashMap.put("MET", "CET");
        hashMap.put("ECT", "CET");
        hashMap.put("EET", "EET");
        hashMap.put("MIT", "Pacific/Apia");
        hashMap.put("HST", "Pacific/Honolulu");
        hashMap.put("AST", "America/Anchorage");
        hashMap.put("PST", "America/Los_Angeles");
        hashMap.put("MST", "America/Denver");
        hashMap.put("PNT", "America/Phoenix");
        hashMap.put("CST", "America/Chicago");
        hashMap.put("EST", "America/New_York");
        hashMap.put("IET", "America/Indiana/Indianapolis");
        hashMap.put("PRT", "America/Puerto_Rico");
        hashMap.put("CNT", "America/St_Johns");
        hashMap.put("AGT", "America/Argentina/Buenos_Aires");
        hashMap.put("BET", "America/Sao_Paulo");
        hashMap.put("ART", "Africa/Cairo");
        hashMap.put("CAT", "Africa/Harare");
        hashMap.put("EAT", "Africa/Addis_Ababa");
        hashMap.put("NET", "Asia/Yerevan");
        hashMap.put("PLT", "Asia/Karachi");
        hashMap.put("IST", "Asia/Kolkata");
        hashMap.put("BST", "Asia/Dhaka");
        hashMap.put("VST", "Asia/Ho_Chi_Minh");
        hashMap.put("CTT", "Asia/Shanghai");
        hashMap.put("JST", "Asia/Tokyo");
        hashMap.put("ACT", "Australia/Darwin");
        hashMap.put("AET", "Australia/Sydney");
        hashMap.put("SST", "Pacific/Guadalcanal");
        hashMap.put("NST", "Pacific/Auckland");
        return Collections.unmodifiableMap(hashMap);
    }
}
