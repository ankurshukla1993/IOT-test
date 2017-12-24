package kotlin.collections;

import java.util.RandomAccess;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00060\u0003j\u0002`\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u0011\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J\u0016\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H\u0002¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002H\u0016J\b\u0010\u0010\u001a\u00020\nH\u0016J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u0014\u0010\u0006\u001a\u00020\u00028VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u0012"}, d2 = {"kotlin/collections/ArraysKt___ArraysKt$asList$3", "Lkotlin/collections/AbstractList;", "", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "([I)V", "size", "getSize", "()I", "contains", "", "element", "get", "index", "(I)Ljava/lang/Integer;", "indexOf", "isEmpty", "lastIndexOf", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
/* compiled from: _Arrays.kt */
public final class ArraysKt___ArraysKt$asList$3 extends AbstractList<Integer> implements RandomAccess {
    final /* synthetic */ int[] receiver$0;

    ArraysKt___ArraysKt$asList$3(int[] $receiver) {
        this.receiver$0 = $receiver;
    }

    public final /* bridge */ boolean contains(Object obj) {
        return obj instanceof Integer ? contains(((Number) obj).intValue()) : false;
    }

    public final /* bridge */ int indexOf(Object obj) {
        return obj instanceof Integer ? indexOf(((Number) obj).intValue()) : -1;
    }

    public final /* bridge */ int lastIndexOf(Object obj) {
        return obj instanceof Integer ? lastIndexOf(((Number) obj).intValue()) : -1;
    }

    public int getSize() {
        return this.receiver$0.length;
    }

    public boolean isEmpty() {
        return this.receiver$0.length == 0;
    }

    public boolean contains(int element) {
        return ArraysKt___ArraysKt.contains(this.receiver$0, element);
    }

    @NotNull
    public Integer get(int index) {
        return Integer.valueOf(this.receiver$0[index]);
    }

    public int indexOf(int element) {
        return ArraysKt___ArraysKt.indexOf(this.receiver$0, element);
    }

    public int lastIndexOf(int element) {
        return ArraysKt___ArraysKt.lastIndexOf(this.receiver$0, element);
    }
}
