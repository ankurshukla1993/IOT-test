package humanize.spi.context;

import humanize.text.MaskFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public interface Context {
    String digitStrings(int i);

    String formatDate(int i, Date date);

    String formatDateTime(int i, int i2, Date date);

    String formatDateTime(Date date);

    String formatDecimal(Number number);

    String formatMessage(String str, Object... objArr);

    ResourceBundle getBundle();

    Locale getLocale();

    MaskFormat getMaskFormat();

    String getMessage(String str);

    void setLocale(Locale locale);
}
