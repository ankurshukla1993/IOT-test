package kotlin;

import kotlin.internal.InlineOnly;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\b\u0003\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\u0004\u001a\u00020\u0001*\u00020\u0003H\b\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\b\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0003H\b¨\u0006\u0006"}, d2 = {"isFinite", "", "", "", "isInfinite", "isNaN", "kotlin-stdlib"}, k = 5, mv = {1, 1, 7}, xi = 1, xs = "kotlin/MathKt")
/* compiled from: Numbers.kt */
class MathKt__NumbersKt extends MathKt__BigNumbersKt {
    @InlineOnly
    private static final boolean isNaN(double $receiver) {
        return Double.isNaN($receiver);
    }

    @InlineOnly
    private static final boolean isNaN(float $receiver) {
        return Float.isNaN($receiver);
    }

    @InlineOnly
    private static final boolean isInfinite(double $receiver) {
        return Double.isInfinite($receiver);
    }

    @InlineOnly
    private static final boolean isInfinite(float $receiver) {
        return Float.isInfinite($receiver);
    }

    @InlineOnly
    private static final boolean isFinite(double $receiver) {
        return (Double.isInfinite($receiver) || Double.isNaN($receiver)) ? false : true;
    }

    @InlineOnly
    private static final boolean isFinite(float $receiver) {
        return (Float.isInfinite($receiver) || Float.isNaN($receiver)) ? false : true;
    }
}
