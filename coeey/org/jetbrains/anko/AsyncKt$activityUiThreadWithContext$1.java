package org.jetbrains.anko;

import android.app.Activity;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 0})
/* compiled from: Async.kt */
final class AsyncKt$activityUiThreadWithContext$1 implements Runnable {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ Function2 $f;

    AsyncKt$activityUiThreadWithContext$1(Function2 function2, Activity activity) {
        this.$f = function2;
        this.$activity = activity;
    }

    public final void run() {
        this.$f.invoke(this.$activity, this.$activity);
    }
}