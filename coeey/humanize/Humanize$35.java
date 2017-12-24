package humanize;

import java.util.concurrent.Callable;

class Humanize$35 implements Callable<String> {
    final /* synthetic */ Number val$num;

    Humanize$35(Number number) {
        this.val$num = number;
    }

    public String call() throws Exception {
        return Humanize.times(this.val$num);
    }
}
