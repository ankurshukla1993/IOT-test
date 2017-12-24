package kotlin.coroutines.experimental.intrinsics;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0015\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0002H\u0016¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0016R\u0014\u0010\u0004\u001a\u00020\u00058VX\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000e"}, d2 = {"kotlin/coroutines/experimental/intrinsics/IntrinsicsKt$buildContinuationByInvokeCall$continuation$1", "Lkotlin/coroutines/experimental/Continuation;", "", "(Lkotlin/coroutines/experimental/Continuation;Lkotlin/jvm/functions/Function0;)V", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "resume", "value", "(Lkotlin/Unit;)V", "resumeWithException", "exception", "", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
/* compiled from: Intrinsics.kt */
public final class C0667xe6b592e8 implements Continuation<Unit> {
    final /* synthetic */ Continuation $completion;
    final /* synthetic */ Continuation $completion$inlined;
    final /* synthetic */ Object $receiver$inlined;
    final /* synthetic */ Function2 receiver$0$inlined;

    public C0667xe6b592e8(Continuation $captured_local_variable$0, Function2 function2, Object obj, Continuation continuation) {
        this.$completion = $captured_local_variable$0;
        this.receiver$0$inlined = function2;
        this.$receiver$inlined = obj;
        this.$completion$inlined = continuation;
    }

    @NotNull
    public CoroutineContext getContext() {
        return this.$completion.getContext();
    }

    public void resume(@NotNull Unit value) {
        Intrinsics.checkParameterIsNotNull(value, "value");
        Continuation continuation = this.$completion;
        try {
            Function2 function2 = this.receiver$0$inlined;
            if (function2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type (R, kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
            }
            Object invoke = ((Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(function2, 2)).invoke(this.$receiver$inlined, this.$completion$inlined);
            if (invoke == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                return;
            }
            if (continuation == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
            }
            continuation.resume(invoke);
        } catch (Throwable th) {
            continuation.resumeWithException(th);
        }
    }

    public void resumeWithException(@NotNull Throwable exception) {
        Intrinsics.checkParameterIsNotNull(exception, "exception");
        this.$completion.resumeWithException(exception);
    }
}
