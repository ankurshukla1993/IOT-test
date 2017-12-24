package org.jetbrains.anko;

import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 0}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0007B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\t\u0010\u0005\u001a\u00020\u0006H\u0002R\u000e\u0010\u0003\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lorg/jetbrains/anko/ViewChildrenRecursiveSequence;", "Lkotlin/sequences/Sequence;", "Landroid/view/View;", "view", "(Landroid/view/View;)V", "iterator", "Lorg/jetbrains/anko/ViewChildrenRecursiveSequence$RecursiveViewIterator;", "RecursiveViewIterator", "common-compileReleaseKotlin"}, k = 1, mv = {1, 1, 0})
/* compiled from: viewChildrenSequences.kt */
final class ViewChildrenRecursiveSequence implements Sequence<View> {
    private final View view;

    @Metadata(bv = {1, 0, 0}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\u0010!\n\u0002\b\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0004J\t\u0010\t\u001a\u00020\nH\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\t\u0010\r\u001a\u00020\u0002H\u0002J$\u0010\u000e\u001a\u0004\u0018\u0001H\u000f\"\b\b\u0000\u0010\u000f*\u00020\u0010*\b\u0012\u0004\u0012\u0002H\u000f0\u0011H\b¢\u0006\u0002\u0010\u0012R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/anko/ViewChildrenRecursiveSequence$RecursiveViewIterator;", "", "Landroid/view/View;", "view", "(Landroid/view/View;)V", "itemIterator", "sequences", "Ljava/util/ArrayList;", "Lkotlin/sequences/Sequence;", "hasNext", "", "initItemIterator", "", "next", "removeLast", "T", "", "", "(Ljava/util/List;)Ljava/lang/Object;", "common-compileReleaseKotlin"}, k = 1, mv = {1, 1, 0})
    /* compiled from: viewChildrenSequences.kt */
    private static final class RecursiveViewIterator implements Iterator<View>, KMappedMarker {
        private Iterator<? extends View> itemIterator;
        private final ArrayList<Sequence<View>> sequences;
        private final View view;

        public void remove() {
            throw new UnsupportedOperationException("Mutating immutable collection");
        }

        public RecursiveViewIterator(@NotNull View view) {
            Intrinsics.checkParameterIsNotNull(view, "view");
            this.view = view;
            Sequence[] sequenceArr = new Sequence[1];
            sequenceArr[0] = SequencesKt__SequencesKt.sequenceOf(this.view);
            this.sequences = CollectionsKt__CollectionsKt.arrayListOf(sequenceArr);
        }

        @NotNull
        public View next() {
            initItemIterator();
            Iterator iterator = this.itemIterator;
            if (iterator != null) {
                View view = (View) iterator.next();
                if ((view instanceof ViewGroup) && ((ViewGroup) view).getChildCount() > 0) {
                    this.sequences.add(ViewChildrenSequencesKt.childrenSequence(view));
                }
                return view;
            }
            throw new NoSuchElementException();
        }

        public boolean hasNext() {
            initItemIterator();
            Iterator iterator = this.itemIterator;
            if (iterator != null) {
                return iterator.hasNext();
            }
            return false;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final void initItemIterator() {
            /*
            r4 = this;
            r3 = 0;
            r1 = r4.sequences;
            r0 = r4.itemIterator;
            if (r0 == 0) goto L_0x001a;
        L_0x0007:
            r2 = r0.hasNext();
            if (r2 != 0) goto L_0x003c;
        L_0x000d:
            r2 = r1;
            r2 = (java.util.Collection) r2;
            r2 = r2.isEmpty();
            if (r2 != 0) goto L_0x002f;
        L_0x0017:
            r2 = 1;
        L_0x0018:
            if (r2 == 0) goto L_0x003c;
        L_0x001a:
            r1 = (java.util.List) r1;
            r2 = r1.isEmpty();
            if (r2 == 0) goto L_0x0031;
        L_0x0023:
            r2 = r3;
        L_0x0024:
            r2 = (kotlin.sequences.Sequence) r2;
            if (r2 == 0) goto L_0x002c;
        L_0x0028:
            r3 = r2.iterator();
        L_0x002c:
            r4.itemIterator = r3;
        L_0x002e:
            return;
        L_0x002f:
            r2 = 0;
            goto L_0x0018;
        L_0x0031:
            r2 = r1.size();
            r2 = r2 + -1;
            r2 = r1.remove(r2);
            goto L_0x0024;
        L_0x003c:
            r2 = r3;
            r2 = (java.util.Iterator) r2;
            r4.itemIterator = r2;
            goto L_0x002e;
            */
            throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.anko.ViewChildrenRecursiveSequence.RecursiveViewIterator.initItemIterator():void");
        }

        private final <T> T removeLast(List<T> $receiver) {
            if ($receiver.isEmpty()) {
                return null;
            }
            return $receiver.remove($receiver.size() - 1);
        }
    }

    public ViewChildrenRecursiveSequence(@NotNull View view) {
        Intrinsics.checkParameterIsNotNull(view, "view");
        this.view = view;
    }

    @NotNull
    public RecursiveViewIterator iterator() {
        return new RecursiveViewIterator(this.view);
    }
}
