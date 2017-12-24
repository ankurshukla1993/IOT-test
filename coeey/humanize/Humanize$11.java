package humanize;

import java.util.Date;
import java.util.concurrent.Callable;

class Humanize$11 implements Callable<String> {
    final /* synthetic */ Date val$value;

    Humanize$11(Date date) {
        this.val$value = date;
    }

    public String call() throws Exception {
        return Humanize.formatDate(this.val$value);
    }
}
