package humanize;

import java.util.Date;
import java.util.concurrent.Callable;

class Humanize$23 implements Callable<String> {
    final /* synthetic */ Date val$duration;
    final /* synthetic */ Date val$reference;

    Humanize$23(Date date, Date date2) {
        this.val$reference = date;
        this.val$duration = date2;
    }

    public String call() {
        return Humanize.naturalTime(this.val$reference, this.val$duration);
    }
}
