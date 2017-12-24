package org.jetbrains.anko;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import com.facebook.react.views.text.ReactTextShadowNode;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\"\u0010\u000f\u001a\u00020\u00102\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\b\u0014H\b\u001a.\u0010\u0015\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00172\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0002\b\u0014H\b\u001a\u001a\u0010\u0015\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017\u001a+\u0010\u0015\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u001b\"\u00020\u0017¢\u0006\u0002\u0010\u001c\u001a\u001a\u0010\u001d\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0017\u001a+\u0010\u001d\u001a\u00020\u0013*\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u001a\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00170\u001b\"\u00020\u0017¢\u0006\u0002\u0010\u001c\u001a\u0012\u0010\u001e\u001a\u00020\u001f*\u00020\u00022\u0006\u0010 \u001a\u00020!\u001a\u0012\u0010\"\u001a\u00020#*\u00020\u00022\u0006\u0010 \u001a\u00020!\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0004\"\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u0015\u0010\u000b\u001a\u00020\f*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006$"}, d2 = {"Bold", "Landroid/text/style/StyleSpan;", "Landroid/text/SpannableStringBuilder;", "getBold", "(Landroid/text/SpannableStringBuilder;)Landroid/text/style/StyleSpan;", "Italic", "getItalic", "Strikethrough", "Landroid/text/style/StrikethroughSpan;", "getStrikethrough", "(Landroid/text/SpannableStringBuilder;)Landroid/text/style/StrikethroughSpan;", "Underline", "Landroid/text/style/UnderlineSpan;", "getUnderline", "(Landroid/text/SpannableStringBuilder;)Landroid/text/style/UnderlineSpan;", "buildSpanned", "Landroid/text/Spanned;", "f", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "append", "span", "", "text", "", "spans", "", "(Landroid/text/SpannableStringBuilder;Ljava/lang/CharSequence;[Ljava/lang/Object;)V", "appendln", "backgroundColor", "Landroid/text/style/BackgroundColorSpan;", "color", "", "foregroundColor", "Landroid/text/style/ForegroundColorSpan;", "common-compileReleaseKotlin"}, k = 2, mv = {1, 1, 0})
/* compiled from: buildSpanned.kt */
public final class BuildSpannedKt {
    @NotNull
    public static final Spanned buildSpanned(@NotNull Function1<? super SpannableStringBuilder, Unit> f) {
        Intrinsics.checkParameterIsNotNull(f, "f");
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        f.invoke(spannableStringBuilder);
        return spannableStringBuilder;
    }

    @NotNull
    public static final StyleSpan getBold(SpannableStringBuilder $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new StyleSpan(1);
    }

    @NotNull
    public static final StyleSpan getItalic(SpannableStringBuilder $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new StyleSpan(2);
    }

    @NotNull
    public static final UnderlineSpan getUnderline(SpannableStringBuilder $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new UnderlineSpan();
    }

    @NotNull
    public static final StrikethroughSpan getStrikethrough(SpannableStringBuilder $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new StrikethroughSpan();
    }

    @NotNull
    public static final ForegroundColorSpan foregroundColor(SpannableStringBuilder $receiver, int color) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new ForegroundColorSpan(color);
    }

    @NotNull
    public static final BackgroundColorSpan backgroundColor(SpannableStringBuilder $receiver, int color) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new BackgroundColorSpan(color);
    }

    public static final void append(SpannableStringBuilder $receiver, @NotNull CharSequence text, @NotNull Object... spans) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        Intrinsics.checkParameterIsNotNull(spans, "spans");
        int textLength = text.length();
        $receiver.append(text);
        int lastIndex$iv = spans.length - 1;
        int i$iv = 0;
        if (0 <= lastIndex$iv) {
            while (true) {
                $receiver.setSpan(spans[i$iv], $receiver.length() - textLength, $receiver.length(), 17);
                Unit unit = Unit.INSTANCE;
                if (i$iv != lastIndex$iv) {
                    i$iv++;
                } else {
                    return;
                }
            }
        }
    }

    public static final void append(SpannableStringBuilder $receiver, @NotNull CharSequence text, @NotNull Object span) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        Intrinsics.checkParameterIsNotNull(span, "span");
        int textLength = text.length();
        $receiver.append(text);
        $receiver.setSpan(span, $receiver.length() - textLength, $receiver.length(), 17);
    }

    @NotNull
    public static final SpannableStringBuilder append(SpannableStringBuilder $receiver, @NotNull Object span, @NotNull Function1<? super SpannableStringBuilder, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(span, "span");
        Intrinsics.checkParameterIsNotNull(f, "f");
        SpannableStringBuilder $receiver2 = $receiver;
        int start = $receiver2.length();
        f.invoke($receiver2);
        $receiver2.setSpan(span, start, $receiver2.length(), 17);
        Unit unit = Unit.INSTANCE;
        return $receiver;
    }

    public static final void appendln(SpannableStringBuilder $receiver, @NotNull CharSequence text, @NotNull Object... spans) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        Intrinsics.checkParameterIsNotNull(spans, "spans");
        append($receiver, text, Arrays.copyOf(spans, spans.length));
        StringsKt__StringBuilderJVMKt.appendln((Appendable) $receiver);
    }

    public static final void appendln(SpannableStringBuilder $receiver, @NotNull CharSequence text, @NotNull Object span) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        Intrinsics.checkParameterIsNotNull(span, "span");
        append($receiver, text, span);
        StringsKt__StringBuilderJVMKt.appendln((Appendable) $receiver);
    }
}
