package kotlin.text;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000>\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\u001a-\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0014\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003*\b\u0012\u0004\u0012\u0002H\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\b\u001a\u001e\u0010\u0007\u001a\u0004\u0018\u00010\b*\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a\u0016\u0010\r\u001a\u0004\u0018\u00010\b*\u00020\t2\u0006\u0010\u000b\u001a\u00020\fH\u0002\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u0010H\u0002\u001a\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0006H\u0002\u001a\u0012\u0010\u0012\u001a\u00020\u0006*\b\u0012\u0004\u0012\u00020\u00030\u0013H\u0002¨\u0006\u0014"}, d2 = {"fromInt", "", "T", "Lkotlin/text/FlagEnum;", "", "value", "", "findNext", "Lkotlin/text/MatchResult;", "Ljava/util/regex/Matcher;", "from", "input", "", "matchEntire", "range", "Lkotlin/ranges/IntRange;", "Ljava/util/regex/MatchResult;", "groupIndex", "toInt", "", "kotlin-stdlib"}, k = 2, mv = {1, 1, 7})
/* compiled from: Regex.kt */
public final class RegexKt {
    private static final int toInt(@NotNull Iterable<? extends FlagEnum> $receiver) {
        int accumulator$iv = 0;
        for (FlagEnum value : $receiver) {
            accumulator$iv |= value.getValue();
        }
        return accumulator$iv;
    }

    private static final <T extends Enum<T> & FlagEnum> Set<T> fromInt(int value) {
        Intrinsics.reifiedOperationMarker(4, "T");
        EnumSet $receiver = EnumSet.allOf(Enum.class);
        CollectionsKt__MutableCollectionsKt.retainAll((Iterable) $receiver, (Function1) new RegexKt$fromInt$$inlined$apply$lambda$1(value));
        Set<T> unmodifiableSet = Collections.unmodifiableSet($receiver);
        Intrinsics.checkExpressionValueIsNotNull(unmodifiableSet, "Collections.unmodifiable… == it.value }\n        })");
        return unmodifiableSet;
    }

    private static final MatchResult findNext(@NotNull Matcher $receiver, int from, CharSequence input) {
        return !$receiver.find(from) ? null : new MatcherMatchResult($receiver, input);
    }

    private static final MatchResult matchEntire(@NotNull Matcher $receiver, CharSequence input) {
        return !$receiver.matches() ? null : new MatcherMatchResult($receiver, input);
    }

    private static final IntRange range(@NotNull MatchResult $receiver) {
        return new IntRange($receiver.start(), $receiver.end() - 1);
    }

    private static final IntRange range(@NotNull MatchResult $receiver, int groupIndex) {
        return new IntRange($receiver.start(groupIndex), $receiver.end(groupIndex) - 1);
    }
}
