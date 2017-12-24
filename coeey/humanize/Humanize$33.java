package humanize;

import java.util.concurrent.Callable;

class Humanize$33 implements Callable<String> {
    final /* synthetic */ Number val$value;

    Humanize$33(Number number) {
        this.val$value = number;
    }

    public String call() {
        return Humanize.spellBigNumber(this.val$value);
    }
}
