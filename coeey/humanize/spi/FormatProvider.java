package humanize.spi;

import humanize.text.FormatFactory;

public interface FormatProvider {
    FormatFactory getFactory();

    String getFormatName();
}
