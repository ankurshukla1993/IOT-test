package humanize.spi.context;

public class DefaultContextFactory implements ContextFactory {
    public Context createContext() {
        return new DefaultContext();
    }
}
