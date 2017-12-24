package humanize;

import java.util.concurrent.Callable;

class Humanize$25 implements Callable<String> {
    final /* synthetic */ Number val$value;

    Humanize$25(Number number) {
        this.val$value = number;
    }

    public String call() {
        return Humanize.ordinal(this.val$value);
    }
}
