package humanize;

import java.util.concurrent.Callable;

class Humanize$34 implements Callable<String> {
    final /* synthetic */ Number val$value;

    Humanize$34(Number number) {
        this.val$value = number;
    }

    public String call() {
        return Humanize.spellDigit(this.val$value);
    }
}
