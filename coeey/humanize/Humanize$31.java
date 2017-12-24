package humanize;

import humanize.spi.context.DefaultContext;
import humanize.time.PrettyTimeFormat;
import java.util.concurrent.Callable;

class Humanize$31 implements Callable<PrettyTimeFormat> {
    Humanize$31() {
    }

    public PrettyTimeFormat call() throws Exception {
        return ((DefaultContext) Humanize.access$200().get()).getPrettyTimeFormat();
    }
}
