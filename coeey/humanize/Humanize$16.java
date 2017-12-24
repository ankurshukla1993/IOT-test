package humanize;

import java.util.concurrent.Callable;

class Humanize$16 implements Callable<String> {
    final /* synthetic */ Number val$value;

    Humanize$16(Number number) {
        this.val$value = number;
    }

    public String call() {
        return Humanize.formatDecimal(this.val$value);
    }
}
