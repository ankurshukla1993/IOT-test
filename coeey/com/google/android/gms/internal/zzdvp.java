package com.google.android.gms.internal;

import java.util.Locale;

public final class zzdvp {
    private static void zza(StringBuilder stringBuilder, Locale locale) {
        String language = locale.getLanguage();
        if (language != null) {
            stringBuilder.append(language);
            language = locale.getCountry();
            if (language != null) {
                stringBuilder.append("-");
                stringBuilder.append(language);
            }
        }
    }

    public static String zzbpz() {
        Locale locale = Locale.getDefault();
        StringBuilder stringBuilder = new StringBuilder();
        zza(stringBuilder, locale);
        if (!locale.equals(Locale.US)) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append(", ");
            }
            zza(stringBuilder, Locale.US);
        }
        return stringBuilder.toString();
    }
}
