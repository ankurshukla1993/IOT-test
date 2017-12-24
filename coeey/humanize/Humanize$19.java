package humanize;

import humanize.spi.MessageFormat;
import java.util.concurrent.Callable;

class Humanize$19 implements Callable<MessageFormat> {
    final /* synthetic */ String val$pattern;

    Humanize$19(String str) {
        this.val$pattern = str;
    }

    public MessageFormat call() throws Exception {
        return Humanize.messageFormat(this.val$pattern);
    }
}
