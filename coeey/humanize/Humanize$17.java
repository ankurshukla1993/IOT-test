package humanize;

import java.util.concurrent.Callable;

class Humanize$17 implements Callable<String> {
    final /* synthetic */ Number val$value;

    Humanize$17(Number number) {
        this.val$value = number;
    }

    public String call() throws Exception {
        return Humanize.formatPercent(this.val$value);
    }
}
