package humanize;

import java.util.Date;
import java.util.concurrent.Callable;

class Humanize$13 implements Callable<String> {
    final /* synthetic */ int val$style;
    final /* synthetic */ Date val$value;

    Humanize$13(int i, Date date) {
        this.val$style = i;
        this.val$value = date;
    }

    public String call() throws Exception {
        return Humanize.formatDate(this.val$style, this.val$value);
    }
}
