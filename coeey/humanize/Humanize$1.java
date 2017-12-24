package humanize;

import humanize.spi.context.DefaultContext;

class Humanize$1 extends ThreadLocal<DefaultContext> {
    Humanize$1() {
    }

    protected DefaultContext initialValue() {
        return (DefaultContext) Humanize.access$000().createContext();
    }
}
