package humanize;

import java.util.concurrent.Callable;

class Humanize$18 implements Callable<Boolean> {
    final /* synthetic */ String val$source;
    final /* synthetic */ String val$target;

    Humanize$18(String str, String str2) {
        this.val$source = str;
        this.val$target = str2;
    }

    public Boolean call() throws Exception {
        return Boolean.valueOf(Humanize.lossyEquals(this.val$source, this.val$target));
    }
}
