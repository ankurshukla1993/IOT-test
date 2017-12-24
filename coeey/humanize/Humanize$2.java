package humanize;

import java.util.concurrent.Callable;

class Humanize$2 implements Callable<String> {
    final /* synthetic */ Number val$value;

    Humanize$2(Number number) {
        this.val$value = number;
    }

    public String call() throws Exception {
        return Humanize.binaryPrefix(this.val$value);
    }
}
