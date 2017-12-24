package humanize;

import java.util.concurrent.Callable;

class Humanize$26 implements Callable<String> {
    final /* synthetic */ Object[] val$items;
    final /* synthetic */ int val$limit;
    final /* synthetic */ String val$limitStr;

    Humanize$26(Object[] objArr, int i, String str) {
        this.val$items = objArr;
        this.val$limit = i;
        this.val$limitStr = str;
    }

    public String call() throws Exception {
        return Humanize.oxford(this.val$items, this.val$limit, this.val$limitStr);
    }
}
