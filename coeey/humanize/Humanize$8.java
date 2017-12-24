package humanize;

import humanize.util.Constants.TimeStyle;
import java.util.concurrent.Callable;

class Humanize$8 implements Callable<String> {
    final /* synthetic */ Number val$seconds;
    final /* synthetic */ TimeStyle val$style;

    Humanize$8(Number number, TimeStyle timeStyle) {
        this.val$seconds = number;
        this.val$style = timeStyle;
    }

    public String call() throws Exception {
        return Humanize.duration(this.val$seconds, this.val$style);
    }
}
