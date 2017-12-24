package kotlin;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lkotlin/UNINITIALIZED_VALUE;", "", "()V", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
/* compiled from: Lazy.kt */
final class UNINITIALIZED_VALUE {
    public static final UNINITIALIZED_VALUE INSTANCE = null;

    static {
        UNINITIALIZED_VALUE uninitialized_value = new UNINITIALIZED_VALUE();
    }

    private UNINITIALIZED_VALUE() {
        INSTANCE = this;
    }
}
