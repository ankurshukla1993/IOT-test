package org.jetbrains.anko;

import android.view.View;
import android.view.ViewGroup;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 0}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\u001a\u0010\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0002\u001a\u0010\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u0002\u001a!\u0010\u0004\u001a\u00020\u0002*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007H\b\u001a#\u0010\t\u001a\u0004\u0018\u00010\u0002*\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\b0\u0007H\b\u001a!\u0010\n\u001a\u00020\u000b*\u00020\u00052\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\u0007H\b\u001a'\u0010\r\u001a\u00020\u000b*\u00020\u00052\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u000b0\u000eH\b¨\u0006\u0010"}, d2 = {"childrenRecursiveSequence", "Lkotlin/sequences/Sequence;", "Landroid/view/View;", "childrenSequence", "firstChild", "Landroid/view/ViewGroup;", "predicate", "Lkotlin/Function1;", "", "firstChildOrNull", "forEachChild", "", "f", "forEachChildWithIndex", "Lkotlin/Function2;", "", "common-compileReleaseKotlin"}, k = 2, mv = {1, 1, 0})
/* compiled from: viewChildrenSequences.kt */
public final class ViewChildrenSequencesKt {
    public static final void forEachChild(ViewGroup $receiver, @NotNull Function1<? super View, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        int i = 0;
        int childCount = $receiver.getChildCount() - 1;
        if (0 <= childCount) {
            while (true) {
                View childAt = $receiver.getChildAt(i);
                Intrinsics.checkExpressionValueIsNotNull(childAt, "getChildAt(i)");
                f.invoke(childAt);
                if (i != childCount) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    public static final void forEachChildWithIndex(ViewGroup $receiver, @NotNull Function2<? super Integer, ? super View, Unit> f) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(f, "f");
        int i = 0;
        int childCount = $receiver.getChildCount() - 1;
        if (0 <= childCount) {
            while (true) {
                Integer valueOf = Integer.valueOf(i);
                View childAt = $receiver.getChildAt(i);
                Intrinsics.checkExpressionValueIsNotNull(childAt, "getChildAt(i)");
                f.invoke(valueOf, childAt);
                if (i != childCount) {
                    i++;
                } else {
                    return;
                }
            }
        }
    }

    @NotNull
    public static final View firstChild(ViewGroup $receiver, @NotNull Function1<? super View, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int i = 0;
        int childCount = $receiver.getChildCount() - 1;
        if (0 <= childCount) {
            while (true) {
                View child = $receiver.getChildAt(i);
                Intrinsics.checkExpressionValueIsNotNull(child, "child");
                if (!((Boolean) predicate.invoke(child)).booleanValue()) {
                    if (i == childCount) {
                        break;
                    }
                    i++;
                } else {
                    Intrinsics.checkExpressionValueIsNotNull(child, "child");
                    return child;
                }
            }
        }
        throw new NoSuchElementException("No element matching predicate was found.");
    }

    @Nullable
    public static final View firstChildOrNull(ViewGroup $receiver, @NotNull Function1<? super View, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        int i = 0;
        int childCount = $receiver.getChildCount() - 1;
        if (0 <= childCount) {
            while (true) {
                View child = $receiver.getChildAt(i);
                Intrinsics.checkExpressionValueIsNotNull(child, "child");
                if (!((Boolean) predicate.invoke(child)).booleanValue()) {
                    if (i == childCount) {
                        break;
                    }
                    i++;
                } else {
                    return child;
                }
            }
        }
        return (View) null;
    }

    @NotNull
    public static final Sequence<View> childrenSequence(View $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new ViewChildrenSequence($receiver);
    }

    @NotNull
    public static final Sequence<View> childrenRecursiveSequence(View $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new ViewChildrenRecursiveSequence($receiver);
    }
}
