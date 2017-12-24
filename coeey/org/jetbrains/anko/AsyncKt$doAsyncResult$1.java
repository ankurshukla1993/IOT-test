package org.jetbrains.anko;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0004\n\u0002\b\u0003\u0010\u0000\u001a\u00028\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"<anonymous>", "invoke", "()Ljava/lang/Object;"}, k = 3, mv = {1, 1, 0})
/* compiled from: Async.kt */
final class AsyncKt$doAsyncResult$1 extends Lambda implements Function0<R> {
    final /* synthetic */ AnkoAsyncContext $context;
    final /* synthetic */ Function1 $exceptionHandler;
    final /* synthetic */ Function1 $task;

    AsyncKt$doAsyncResult$1(Function1 function1, AnkoAsyncContext ankoAsyncContext, Function1 function12) {
        this.$task = function1;
        this.$context = ankoAsyncContext;
        this.$exceptionHandler = function12;
        super(0);
    }

    public final R invoke() {
        try {
            return this.$task.invoke(this.$context);
        } catch (Throwable thr) {
            Function1 function1 = this.$exceptionHandler;
            if (function1 != null) {
                Unit unit = (Unit) function1.invoke(thr);
            }
        }
    }
}
