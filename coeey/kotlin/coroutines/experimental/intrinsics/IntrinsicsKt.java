package kotlin.coroutines.experimental.intrinsics;

import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a5\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\"\u0004\b\u0000\u0010\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u00072\u0010\b\u0004\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\fH\b\u001a5\u0010\r\u001a\u0002H\t\"\u0004\b\u0000\u0010\t2\u001c\b\u0004\u0010\u000b\u001a\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000eHHø\u0001\u0000¢\u0006\u0002\u0010\u000f\u001aD\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\"\u0004\b\u0000\u0010\t*\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000e2\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a]\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\"\u0004\b\u0000\u0010\u0012\"\u0004\b\u0001\u0010\t*#\b\u0001\u0012\u0004\u0012\u0002H\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u0013¢\u0006\u0002\b\u00142\u0006\u0010\u0015\u001a\u0002H\u00122\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\t0\u0007H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\"\u001c\u0010\u0000\u001a\u00020\u00018\u0006X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\u0002\u0004\n\u0002\b\t¨\u0006\u0017"}, d2 = {"COROUTINE_SUSPENDED", "", "COROUTINE_SUSPENDED$annotations", "()V", "getCOROUTINE_SUSPENDED", "()Ljava/lang/Object;", "buildContinuationByInvokeCall", "Lkotlin/coroutines/experimental/Continuation;", "", "T", "completion", "block", "Lkotlin/Function0;", "suspendCoroutineOrReturn", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "createCoroutineUnchecked", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 7})
@JvmName(name = "IntrinsicsKt")
/* compiled from: Intrinsics.kt */
public final class IntrinsicsKt {
    @NotNull
    private static final Object COROUTINE_SUSPENDED = new Object();

    @SinceKotlin(version = "1.1")
    public static /* synthetic */ void COROUTINE_SUSPENDED$annotations() {
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final <T> Object suspendCoroutineOrReturn(Function1<? super Continuation<? super T>, ? extends Object> block, Continuation<? super T> continuation) {
        throw new NotImplementedError("Implementation is intrinsic");
    }

    @NotNull
    public static final Object getCOROUTINE_SUSPENDED() {
        return COROUTINE_SUSPENDED;
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <T> Continuation<Unit> createCoroutineUnchecked(@NotNull Function1<? super Continuation<? super T>, ? extends Object> $receiver, @NotNull Continuation<? super T> completion) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        if ($receiver instanceof CoroutineImpl) {
            Continuation create = ((CoroutineImpl) $receiver).create(completion);
            if (create != null) {
                return ((CoroutineImpl) create).getFacade();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.jvm.internal.CoroutineImpl");
        }
        return CoroutineIntrinsics.interceptContinuationIfNeeded(completion.getContext(), new C0666xe6b592e7(completion, $receiver, completion));
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <R, T> Continuation<Unit> createCoroutineUnchecked(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> $receiver, R receiver, @NotNull Continuation<? super T> completion) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        if ($receiver instanceof CoroutineImpl) {
            Continuation create = ((CoroutineImpl) $receiver).create(receiver, completion);
            if (create != null) {
                return ((CoroutineImpl) create).getFacade();
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.jvm.internal.CoroutineImpl");
        }
        return CoroutineIntrinsics.interceptContinuationIfNeeded(completion.getContext(), new C0667xe6b592e8(completion, $receiver, receiver, completion));
    }

    private static final <T> Continuation<Unit> buildContinuationByInvokeCall(Continuation<? super T> completion, Function0<? extends Object> block) {
        return CoroutineIntrinsics.interceptContinuationIfNeeded(completion.getContext(), new IntrinsicsKt$buildContinuationByInvokeCall$continuation$1(completion, block));
    }
}
