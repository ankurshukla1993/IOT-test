package humanize;

import humanize.util.Parameters.PluralizeParams;
import java.util.concurrent.Callable;

class Humanize$29 implements Callable<String> {
    final /* synthetic */ Number val$number;
    final /* synthetic */ PluralizeParams val$params;

    Humanize$29(Number number, PluralizeParams pluralizeParams) {
        this.val$number = number;
        this.val$params = pluralizeParams;
    }

    public String call() throws Exception {
        return Humanize.pluralize(this.val$number, this.val$params);
    }
}
