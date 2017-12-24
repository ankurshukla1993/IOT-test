package humanize;

import humanize.util.Constants;
import java.util.concurrent.Callable;

class Humanize$21 implements Callable<String> {
    final /* synthetic */ Number val$value;

    Humanize$21(Number number) {
        this.val$value = number;
    }

    public String call() {
        return Humanize.access$100(this.val$value, 1000, Constants.nanoTimePrefixes);
    }
}
