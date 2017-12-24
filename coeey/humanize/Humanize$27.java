package humanize;

import java.util.concurrent.Callable;

class Humanize$27 implements Callable<String> {
    final /* synthetic */ long val$interval;
    final /* synthetic */ Number val$value;

    Humanize$27(Number number, long j) {
        this.val$value = number;
        this.val$interval = j;
    }

    public String call() throws Exception {
        return Humanize.paceFormat(this.val$value, this.val$interval);
    }
}
