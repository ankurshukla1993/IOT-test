package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0010\u0010\u0003\u001a\u00020\u00048\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lkotlin/text/ScreenFloatValueRegEx;", "", "()V", "value", "Lkotlin/text/Regex;", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
/* compiled from: StringNumberConversions.kt */
final class ScreenFloatValueRegEx {
    public static final ScreenFloatValueRegEx INSTANCE = null;
    @NotNull
    @JvmField
    public static final Regex value = null;

    static {
        ScreenFloatValueRegEx screenFloatValueRegEx = new ScreenFloatValueRegEx();
    }

    private ScreenFloatValueRegEx() {
        INSTANCE = this;
        ScreenFloatValueRegEx $receiver = this;
        String Digits = "(\\p{Digit}+)";
        String HexDigits = "(\\p{XDigit}+)";
        String Exp = "[eE][+-]?" + Digits;
        value = new Regex("[\\x00-\\x20]*[+-]?(NaN|Infinity|((" + ('(' + Digits + "(\\.)?(" + Digits + "?)(" + Exp + ")?)|" + "(\\.(" + Digits + ")(" + Exp + ")?)|" + "((" + ("(0[xX]" + HexDigits + "(\\.)?)|" + "(0[xX]" + HexDigits + "?(\\.)" + HexDigits + ')') + ")[pP][+-]?" + Digits + ')') + ")[fFdD]?))[\\x00-\\x20]*");
    }
}
