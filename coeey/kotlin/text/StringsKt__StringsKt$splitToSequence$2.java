package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lkotlin/ranges/IntRange;", "invoke"}, k = 3, mv = {1, 1, 7})
/* compiled from: Strings.kt */
final class StringsKt__StringsKt$splitToSequence$2 extends Lambda implements Function1<IntRange, String> {
    final /* synthetic */ CharSequence receiver$0;

    StringsKt__StringsKt$splitToSequence$2(CharSequence charSequence) {
        this.receiver$0 = charSequence;
        super(1);
    }

    @NotNull
    public final String invoke(@NotNull IntRange it) {
        Intrinsics.checkParameterIsNotNull(it, "it");
        return StringsKt__StringsKt.substring(this.receiver$0, it);
    }
}
