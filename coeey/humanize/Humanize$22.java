package humanize;

import java.util.Date;
import java.util.concurrent.Callable;

class Humanize$22 implements Callable<String> {
    final /* synthetic */ int val$style;
    final /* synthetic */ Date val$then;

    Humanize$22(int i, Date date) {
        this.val$style = i;
        this.val$then = date;
    }

    public String call() throws Exception {
        return Humanize.naturalDay(this.val$style, this.val$then);
    }
}
