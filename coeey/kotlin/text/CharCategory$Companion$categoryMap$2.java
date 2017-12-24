package kotlin.text;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010$\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "", "Lkotlin/text/CharCategory;", "invoke"}, k = 3, mv = {1, 1, 7})
/* compiled from: CharCategory.kt */
final class CharCategory$Companion$categoryMap$2 extends Lambda implements Function0<Map<Integer, ? extends CharCategory>> {
    public static final CharCategory$Companion$categoryMap$2 INSTANCE = new CharCategory$Companion$categoryMap$2();

    CharCategory$Companion$categoryMap$2() {
        super(0);
    }

    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.Integer, kotlin.text.CharCategory> invoke() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.text.CharCategory$Companion$categoryMap$2.invoke():java.util.Map<java.lang.Integer, kotlin.text.CharCategory>
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r0 = this;
        r0 = kotlin.text.CharCategory.values();
        r0 = (java.lang.Object[]) r0;
        r4 = r0.length;
        r4 = kotlin.collections.MapsKt__MapsKt.mapCapacity(r4);
        r5 = 16;
        r1 = kotlin.ranges.RangesKt___RangesKt.coerceAtLeast(r4, r5);
        r2 = new java.util.LinkedHashMap;
        r2.<init>(r1);
        r2 = (java.util.Map) r2;
        r4 = 0;
        r5 = r4;
        r4 = r0.length;
        if (r5 >= r4) goto L_0x0031;
    L_0x001d:
        r3 = r0[r5];
        r4 = r3;
        r4 = (kotlin.text.CharCategory) r4;
        r4 = r4.getValue();
        r4 = java.lang.Integer.valueOf(r4);
        r2.put(r4, r3);
        r4 = r5 + 1;
        r5 = r4;
        goto L_0x001a;
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.CharCategory$Companion$categoryMap$2.invoke():java.util.Map<java.lang.Integer, kotlin.text.CharCategory>");
    }
}
