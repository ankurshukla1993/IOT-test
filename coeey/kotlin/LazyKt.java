package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\"\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0007\u001a,\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0007\u001a*\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u0007\u001a\u001f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\n\u001a\u0002H\u0002¢\u0006\u0002\u0010\u000b\u001a4\u0010\f\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u00062\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000fH\n¢\u0006\u0002\u0010\u0010¨\u0006\u0011"}, d2 = {"lazy", "Lkotlin/Lazy;", "T", "initializer", "Lkotlin/Function0;", "lock", "", "mode", "Lkotlin/LazyThreadSafetyMode;", "lazyOf", "value", "(Ljava/lang/Object;)Lkotlin/Lazy;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Lkotlin/Lazy;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 7})
@JvmName(name = "LazyKt")
/* compiled from: Lazy.kt */
public final class LazyKt {
    @NotNull
    public static final <T> Lazy<T> lazyOf(T value) {
        return new InitializedLazyImpl(value);
    }

    @NotNull
    public static final <T> Lazy<T> lazy(@NotNull Function0<? extends T> initializer) {
        Intrinsics.checkParameterIsNotNull(initializer, "initializer");
        return new SynchronizedLazyImpl(initializer, null, 2, null);
    }

    @NotNull
    public static final <T> Lazy<T> lazy(@NotNull LazyThreadSafetyMode mode, @NotNull Function0<? extends T> initializer) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        Intrinsics.checkParameterIsNotNull(initializer, "initializer");
        switch (mode) {
            case SYNCHRONIZED:
                return new SynchronizedLazyImpl(initializer, null, 2, null);
            case PUBLICATION:
                return new SafePublicationLazyImpl(initializer);
            case NONE:
                return new UnsafeLazyImpl(initializer);
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    @NotNull
    public static final <T> Lazy<T> lazy(@Nullable Object lock, @NotNull Function0<? extends T> initializer) {
        Intrinsics.checkParameterIsNotNull(initializer, "initializer");
        return new SynchronizedLazyImpl(initializer, lock);
    }

    @InlineOnly
    private static final <T> T getValue(@NotNull Lazy<? extends T> $receiver, Object thisRef, KProperty<?> property) {
        return $receiver.getValue();
    }
}
