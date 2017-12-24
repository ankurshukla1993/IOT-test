package humanize.spi.context;

import humanize.spi.MessageFormat;
import humanize.time.PrettyTimeFormat;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

public interface StandardContext {
    String formatRelativeDate(Date date, Date date2);

    String formatRelativeDate(Date date, Date date2, long j);

    DecimalFormat getCurrencyFormat();

    DateFormat getDateFormat(int i);

    DateFormat getDateFormat(String str);

    DateFormat getDateTimeFormat();

    DateFormat getDateTimeFormat(int i, int i2);

    DecimalFormat getDecimalFormat();

    MessageFormat getMessageFormat();

    NumberFormat getNumberFormat();

    DecimalFormat getPercentFormat();

    PrettyTimeFormat getPrettyTimeFormat();

    String ordinalSuffix(int i);

    String timeSuffix(int i);
}
