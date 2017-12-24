package humanize;

import java.util.concurrent.Callable;

class Humanize$7 implements Callable<String> {
    final /* synthetic */ Number val$seconds;

    Humanize$7(Number number) {
        this.val$seconds = number;
    }

    public String call() throws Exception {
        return Humanize.duration(this.val$seconds);
    }
}
