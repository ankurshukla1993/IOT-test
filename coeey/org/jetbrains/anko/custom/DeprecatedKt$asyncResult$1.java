package org.jetbrains.anko.custom;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.anko.AnkoAsyncContext;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0004\n\u0002\b\u0003\u0010\u0000\u001a\u00028\u0000H\n¢\u0006\u0004\b\u0001\u0010\u0002"}, d2 = {"<anonymous>", "invoke", "()Ljava/lang/Object;"}, k = 3, mv = {1, 1, 0})
/* compiled from: Deprecated.kt */
final class DeprecatedKt$asyncResult$1 extends Lambda implements Function0<R> {
    final /* synthetic */ AnkoAsyncContext $context;
    final /* synthetic */ Function1 $task;

    DeprecatedKt$asyncResult$1(Function1 function1, AnkoAsyncContext ankoAsyncContext) {
        this.$task = function1;
        this.$context = ankoAsyncContext;
        super(0);
    }

    public final R invoke() {
        return this.$task.invoke(this.$context);
    }
}
