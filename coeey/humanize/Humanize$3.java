package humanize;

import java.util.concurrent.Callable;

class Humanize$3 implements Callable<String> {
    final /* synthetic */ boolean val$capitalizeFirstChar;
    final /* synthetic */ String val$text;

    Humanize$3(String str, boolean z) {
        this.val$text = str;
        this.val$capitalizeFirstChar = z;
    }

    public String call() throws Exception {
        return Humanize.camelize(this.val$text, this.val$capitalizeFirstChar);
    }
}
