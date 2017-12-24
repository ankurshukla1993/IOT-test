package humanize;

import java.util.Date;
import java.util.concurrent.Callable;

class Humanize$24 implements Callable<String> {
    final /* synthetic */ Date val$duration;
    final /* synthetic */ long val$precision;
    final /* synthetic */ Date val$reference;

    Humanize$24(Date date, Date date2, long j) {
        this.val$reference = date;
        this.val$duration = date2;
        this.val$precision = j;
    }

    public String call() {
        return Humanize.naturalTime(this.val$reference, this.val$duration, this.val$precision);
    }
}
