package humanize;

import java.text.DateFormat;
import java.util.concurrent.Callable;

class Humanize$5 implements Callable<DateFormat> {
    final /* synthetic */ String val$pattern;

    Humanize$5(String str) {
        this.val$pattern = str;
    }

    public DateFormat call() throws Exception {
        return Humanize.dateFormat(this.val$pattern);
    }
}
