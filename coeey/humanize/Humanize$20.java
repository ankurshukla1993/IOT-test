package humanize;

import java.util.concurrent.Callable;

class Humanize$20 implements Callable<String> {
    final /* synthetic */ Number val$value;

    Humanize$20(Number number) {
        this.val$value = number;
    }

    public String call() throws Exception {
        return Humanize.metricPrefix(this.val$value);
    }
}
