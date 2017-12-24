package humanize;

import java.util.concurrent.Callable;

class Humanize$10 implements Callable<String> {
    final /* synthetic */ Number val$value;

    Humanize$10(Number number) {
        this.val$value = number;
    }

    public String call() {
        return Humanize.formatCurrency(this.val$value);
    }
}
