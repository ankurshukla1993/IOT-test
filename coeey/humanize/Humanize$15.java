package humanize;

import java.util.Date;
import java.util.concurrent.Callable;

class Humanize$15 implements Callable<String> {
    final /* synthetic */ int val$dateStyle;
    final /* synthetic */ int val$timeStyle;
    final /* synthetic */ Date val$value;

    Humanize$15(int i, int i2, Date date) {
        this.val$dateStyle = i;
        this.val$timeStyle = i2;
        this.val$value = date;
    }

    public String call() throws Exception {
        return Humanize.formatDateTime(this.val$dateStyle, this.val$timeStyle, this.val$value);
    }
}
