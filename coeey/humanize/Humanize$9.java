package humanize;

import java.util.concurrent.Callable;

class Humanize$9 implements Callable<String> {
    final /* synthetic */ Object[] val$args;
    final /* synthetic */ String val$pattern;

    Humanize$9(String str, Object[] objArr) {
        this.val$pattern = str;
        this.val$args = objArr;
    }

    public String call() throws Exception {
        return Humanize.format(this.val$pattern, this.val$args);
    }
}
