package humanize;

import java.util.Date;
import java.util.concurrent.Callable;

class Humanize$12 implements Callable<String> {
    final /* synthetic */ String val$pattern;
    final /* synthetic */ Date val$value;

    Humanize$12(Date date, String str) {
        this.val$value = date;
        this.val$pattern = str;
    }

    public String call() throws Exception {
        return Humanize.formatDate(this.val$value, this.val$pattern);
    }
}
