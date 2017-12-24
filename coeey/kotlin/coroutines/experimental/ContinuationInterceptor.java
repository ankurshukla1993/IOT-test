package kotlin.coroutines.experimental;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.experimental.CoroutineContext.Element;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006J\"\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003H&¨\u0006\u0007"}, d2 = {"Lkotlin/coroutines/experimental/ContinuationInterceptor;", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "interceptContinuation", "Lkotlin/coroutines/experimental/Continuation;", "T", "continuation", "Key", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
@SinceKotlin(version = "1.1")
/* compiled from: ContinuationInterceptor.kt */
public interface ContinuationInterceptor extends Element {
    public static final Key Key = new Key();

    @Metadata(bv = {1, 0, 2}, k = 3, mv = {1, 1, 7})
    /* compiled from: ContinuationInterceptor.kt */
    public static final class DefaultImpls {
        public static <R> R fold(ContinuationInterceptor $this, @NotNull R initial, Function2<? super R, ? super Element, ? extends R> operation) {
            Intrinsics.checkParameterIsNotNull(operation, "operation");
            return kotlin.coroutines.experimental.CoroutineContext.Element.DefaultImpls.fold($this, initial, operation);
        }

        @Nullable
        public static <E extends Element> E get(@NotNull ContinuationInterceptor $this, kotlin.coroutines.experimental.CoroutineContext.Key<E> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlin.coroutines.experimental.CoroutineContext.Element.DefaultImpls.get($this, key);
        }

        @NotNull
        public static CoroutineContext minusKey(@NotNull ContinuationInterceptor $this, kotlin.coroutines.experimental.CoroutineContext.Key<?> key) {
            Intrinsics.checkParameterIsNotNull(key, "key");
            return kotlin.coroutines.experimental.CoroutineContext.Element.DefaultImpls.minusKey($this, key);
        }

        @NotNull
        public static CoroutineContext plus(@NotNull ContinuationInterceptor $this, CoroutineContext context) {
            Intrinsics.checkParameterIsNotNull(context, "context");
            return kotlin.coroutines.experimental.CoroutineContext.Element.DefaultImpls.plus($this, context);
        }
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lkotlin/coroutines/experimental/ContinuationInterceptor$Key;", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "Lkotlin/coroutines/experimental/ContinuationInterceptor;", "()V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
    /* compiled from: ContinuationInterceptor.kt */
    public static final class Key implements kotlin.coroutines.experimental.CoroutineContext.Key<ContinuationInterceptor> {
        private Key() {
        }
    }

    @NotNull
    <T> Continuation<T> interceptContinuation(@NotNull Continuation<? super T> continuation);
}
