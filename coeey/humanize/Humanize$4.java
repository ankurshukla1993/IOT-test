package humanize;

import java.util.concurrent.Callable;

class Humanize$4 implements Callable<String> {
    final /* synthetic */ String val$text;

    Humanize$4(String str) {
        this.val$text = str;
    }

    public String call() throws Exception {
        return Humanize.capitalize(this.val$text);
    }
}
