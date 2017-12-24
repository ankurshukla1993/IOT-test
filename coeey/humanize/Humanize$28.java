package humanize;

import humanize.util.Parameters.PaceParameters;
import java.util.concurrent.Callable;

class Humanize$28 implements Callable<String> {
    final /* synthetic */ PaceParameters val$params;
    final /* synthetic */ Number val$value;

    Humanize$28(Number number, PaceParameters paceParameters) {
        this.val$value = number;
        this.val$params = paceParameters;
    }

    public String call() throws Exception {
        return Humanize.paceFormat(this.val$value, this.val$params);
    }
}
