package io.realm.internal.android;

import android.util.Base64;
import io.realm.exceptions.RealmException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

public class JsonUtils {
    private static Pattern jsonDate = Pattern.compile("/Date\\((\\d*)(?:[+-]\\d*)?\\)/");
    private static Pattern numericOnly = Pattern.compile("-?\\d+");
    private static ParsePosition parsePosition = new ParsePosition(0);

    @Nullable
    public static Date stringToDate(String date) {
        if (date == null || date.length() == 0) {
            return null;
        }
        Matcher matcher = jsonDate.matcher(date);
        if (matcher.find()) {
            return new Date(Long.parseLong(matcher.group(1)));
        }
        if (numericOnly.matcher(date).matches()) {
            try {
                return new Date(Long.parseLong(date));
            } catch (NumberFormatException e) {
                throw new RealmException(e.getMessage(), e);
            }
        }
        try {
            parsePosition.setIndex(0);
            return ISO8601Utils.parse(date, parsePosition);
        } catch (ParseException e2) {
            throw new RealmException(e2.getMessage(), e2);
        }
    }

    public static byte[] stringToBytes(String str) {
        if (str == null || str.length() == 0) {
            return new byte[0];
        }
        return Base64.decode(str, 0);
    }
}
