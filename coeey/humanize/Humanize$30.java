package humanize;

import humanize.spi.MessageFormat;
import java.util.concurrent.Callable;

class Humanize$30 implements Callable<MessageFormat> {
    final /* synthetic */ String val$template;

    Humanize$30(String str) {
        this.val$template = str;
    }

    public MessageFormat call() throws Exception {
        return Humanize.pluralizeFormat(this.val$template);
    }
}
