package humanize;

import java.util.Date;
import java.util.concurrent.Callable;

class Humanize$14 implements Callable<String> {
    final /* synthetic */ Date val$value;

    Humanize$14(Date date) {
        this.val$value = date;
    }

    public String call() throws Exception {
        return Humanize.formatDateTime(this.val$value);
    }
}
