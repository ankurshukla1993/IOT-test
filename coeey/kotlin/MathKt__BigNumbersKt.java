package kotlin;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0000\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\n\u001a\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0004\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\n\u001a\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0006\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\n\u001a\u0015\u0010\u0007\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\u0007\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\n\u001a\u0015\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n\u001a\u0015\u0010\b\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u0003H\n\u001a\r\u0010\t\u001a\u00020\u0001*\u00020\u0001H\n\u001a\r\u0010\t\u001a\u00020\u0003*\u00020\u0003H\n¨\u0006\n"}, d2 = {"div", "Ljava/math/BigDecimal;", "other", "Ljava/math/BigInteger;", "minus", "mod", "plus", "rem", "times", "unaryMinus", "kotlin-stdlib"}, k = 5, mv = {1, 1, 7}, xi = 1, xs = "kotlin/MathKt")
/* compiled from: BigNumbers.kt */
class MathKt__BigNumbersKt {
    @InlineOnly
    private static final BigInteger plus(@NotNull BigInteger $receiver, BigInteger other) {
        BigInteger add = $receiver.add(other);
        Intrinsics.checkExpressionValueIsNotNull(add, "this.add(other)");
        return add;
    }

    @InlineOnly
    private static final BigInteger minus(@NotNull BigInteger $receiver, BigInteger other) {
        BigInteger subtract = $receiver.subtract(other);
        Intrinsics.checkExpressionValueIsNotNull(subtract, "this.subtract(other)");
        return subtract;
    }

    @InlineOnly
    private static final BigInteger times(@NotNull BigInteger $receiver, BigInteger other) {
        BigInteger multiply = $receiver.multiply(other);
        Intrinsics.checkExpressionValueIsNotNull(multiply, "this.multiply(other)");
        return multiply;
    }

    @InlineOnly
    private static final BigInteger div(@NotNull BigInteger $receiver, BigInteger other) {
        BigInteger divide = $receiver.divide(other);
        Intrinsics.checkExpressionValueIsNotNull(divide, "this.divide(other)");
        return divide;
    }

    @SinceKotlin(version = "1.1")
    @InlineOnly
    private static final BigInteger rem(@NotNull BigInteger $receiver, BigInteger other) {
        BigInteger remainder = $receiver.remainder(other);
        Intrinsics.checkExpressionValueIsNotNull(remainder, "this.remainder(other)");
        return remainder;
    }

    @InlineOnly
    private static final BigInteger unaryMinus(@NotNull BigInteger $receiver) {
        BigInteger negate = $receiver.negate();
        Intrinsics.checkExpressionValueIsNotNull(negate, "this.negate()");
        return negate;
    }

    @InlineOnly
    private static final BigDecimal plus(@NotNull BigDecimal $receiver, BigDecimal other) {
        BigDecimal add = $receiver.add(other);
        Intrinsics.checkExpressionValueIsNotNull(add, "this.add(other)");
        return add;
    }

    @InlineOnly
    private static final BigDecimal minus(@NotNull BigDecimal $receiver, BigDecimal other) {
        BigDecimal subtract = $receiver.subtract(other);
        Intrinsics.checkExpressionValueIsNotNull(subtract, "this.subtract(other)");
        return subtract;
    }

    @InlineOnly
    private static final BigDecimal times(@NotNull BigDecimal $receiver, BigDecimal other) {
        BigDecimal multiply = $receiver.multiply(other);
        Intrinsics.checkExpressionValueIsNotNull(multiply, "this.multiply(other)");
        return multiply;
    }

    @InlineOnly
    private static final BigDecimal div(@NotNull BigDecimal $receiver, BigDecimal other) {
        BigDecimal divide = $receiver.divide(other, RoundingMode.HALF_EVEN);
        Intrinsics.checkExpressionValueIsNotNull(divide, "this.divide(other, RoundingMode.HALF_EVEN)");
        return divide;
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Use rem(other) instead", replaceWith = @ReplaceWith(expression = "rem(other)", imports = {}))
    @InlineOnly
    private static final BigDecimal mod(@NotNull BigDecimal $receiver, BigDecimal other) {
        BigDecimal remainder = $receiver.remainder(other);
        Intrinsics.checkExpressionValueIsNotNull(remainder, "this.remainder(other)");
        return remainder;
    }

    @InlineOnly
    private static final BigDecimal rem(@NotNull BigDecimal $receiver, BigDecimal other) {
        BigDecimal remainder = $receiver.remainder(other);
        Intrinsics.checkExpressionValueIsNotNull(remainder, "this.remainder(other)");
        return remainder;
    }

    @InlineOnly
    private static final BigDecimal unaryMinus(@NotNull BigDecimal $receiver) {
        BigDecimal negate = $receiver.negate();
        Intrinsics.checkExpressionValueIsNotNull(negate, "this.negate()");
        return negate;
    }
}
