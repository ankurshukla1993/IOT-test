package humanize;

import java.text.DecimalFormat;
import java.util.concurrent.Callable;

class Humanize$6 implements Callable<DecimalFormat> {
    final /* synthetic */ String val$pattern;

    Humanize$6(String str) {
        this.val$pattern = str;
    }

    public DecimalFormat call() throws Exception {
        return Humanize.decimalFormat(this.val$pattern);
    }
}
