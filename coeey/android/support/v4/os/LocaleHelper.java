package android.support.v4.os;

import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.Locale;

@RestrictTo({Scope.LIBRARY_GROUP})
final class LocaleHelper {
    LocaleHelper() {
    }

    static Locale forLanguageTag(String str) {
        String[] args;
        if (str.contains("-")) {
            args = str.split("-");
            if (args.length > 2) {
                return new Locale(args[0], args[1], args[2]);
            }
            if (args.length > 1) {
                return new Locale(args[0], args[1]);
            }
            if (args.length == 1) {
                return new Locale(args[0]);
            }
        } else if (!str.contains(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR)) {
            return new Locale(str);
        } else {
            args = str.split(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            if (args.length > 2) {
                return new Locale(args[0], args[1], args[2]);
            }
            if (args.length > 1) {
                return new Locale(args[0], args[1]);
            }
            if (args.length == 1) {
                return new Locale(args[0]);
            }
        }
        throw new IllegalArgumentException("Can not parse language tag: [" + str + "]");
    }

    static String toLanguageTag(Locale locale) {
        StringBuilder buf = new StringBuilder();
        buf.append(locale.getLanguage());
        String country = locale.getCountry();
        if (!(country == null || country.isEmpty())) {
            buf.append("-");
            buf.append(locale.getCountry());
        }
        return buf.toString();
    }
}
