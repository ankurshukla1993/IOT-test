package org.jetbrains.anko.custom;

import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.anko.AnkoAsyncContext;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0004\n\u0002\b\u0003\u0010\u0000\u001a\u00028\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"<anonymous>", "call", "()Ljava/lang/Object;"}, k = 3, mv = {1, 1, 0})
/* compiled from: Deprecated.kt */
final class DeprecatedKt$asyncResult$2<V> implements Callable<T> {
    final /* synthetic */ AnkoAsyncContext $context;
    final /* synthetic */ Function1 $task;

    DeprecatedKt$asyncResult$2(Function1 function1, AnkoAsyncContext ankoAsyncContext) {
        this.$task = function1;
        this.$context = ankoAsyncContext;
    }

    public final R call() {
        return this.$task.invoke(this.$context);
    }
}
