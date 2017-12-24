package kotlin.collections;

import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000L\n\u0000\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\b\u0005\u001a\u0001\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052b\u0010\u0006\u001a^\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00030\u0007H\b\u001a´\u0001\u0010\u000f\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102b\u0010\u0006\u001a^\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0015\u0012\u0013\u0018\u0001H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\r¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00030\u0007H\b¢\u0006\u0002\u0010\u0013\u001a0\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00150\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u0005H\u0007\u001aI\u0010\u0016\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0016\b\u0002\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u00020\u00150\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u0010H\u0007¢\u0006\u0002\u0010\u0017\u001a¼\u0001\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u000526\u0010\u0019\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001a2K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001bH\b\u001a|\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u001c\u001a\u0002H\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001aH\b¢\u0006\u0002\u0010\u001d\u001aÕ\u0001\u0010\u001e\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u001026\u0010\u0019\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001a2K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001bH\b¢\u0006\u0002\u0010\u001f\u001a\u0001\u0010\u001e\u001a\u0002H\u0010\"\u0004\b\u0000\u0010\u0004\"\u0004\b\u0001\u0010\u0002\"\u0004\b\u0002\u0010\u0003\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102\u0006\u0010\u001c\u001a\u0002H\u000326\u0010\u0006\u001a2\u0012\u0013\u0012\u0011H\u0003¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H\u00030\u001aH\b¢\u0006\u0002\u0010 \u001aW\u0010!\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0011\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\"\"\u0004\b\u0002\u0010\u0003*\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\"0\u00112\u001e\u0010#\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\"0%\u0012\u0004\u0012\u0002H\u00030$H\b\u001a\u0001\u0010&\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H'0\u0001\"\u0004\b\u0000\u0010'\"\b\b\u0001\u0010\u0004*\u0002H'\"\u0004\b\u0002\u0010\u0002*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H'¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H'0\u001bH\b\u001a¡\u0001\u0010(\u001a\u0002H\u0010\"\u0004\b\u0000\u0010'\"\b\b\u0001\u0010\u0004*\u0002H'\"\u0004\b\u0002\u0010\u0002\"\u0016\b\u0003\u0010\u0010*\u0010\u0012\u0006\b\u0000\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H'0\u0011*\u000e\u0012\u0004\u0012\u0002H\u0004\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0012\u001a\u0002H\u00102K\u0010\u0006\u001aG\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\n\u0012\u0013\u0012\u0011H'¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u0011H\u0004¢\u0006\f\b\b\u0012\b\b\t\u0012\u0004\b\b(\f\u0012\u0004\u0012\u0002H'0\u001bH\b¢\u0006\u0002\u0010)¨\u0006*"}, d2 = {"aggregate", "", "K", "R", "T", "Lkotlin/collections/Grouping;", "operation", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "key", "accumulator", "element", "", "first", "aggregateTo", "M", "", "destination", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function4;)Ljava/util/Map;", "eachCount", "", "eachCountTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;)Ljava/util/Map;", "fold", "initialValueSelector", "Lkotlin/Function2;", "Lkotlin/Function3;", "initialValue", "(Lkotlin/collections/Grouping;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "foldTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "(Lkotlin/collections/Grouping;Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "mapValuesInPlace", "V", "f", "Lkotlin/Function1;", "", "reduce", "S", "reduceTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 7})
/* compiled from: Grouping.kt */
public final class GroupingKt {
    @org.jetbrains.annotations.NotNull
    @kotlin.SinceKotlin(version = "1.1")
    public static final <T, K> java.util.Map<K, java.lang.Integer> eachCount(@org.jetbrains.annotations.NotNull kotlin.collections.Grouping<T, ? extends K> r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.collections.GroupingKt.eachCount(kotlin.collections.Grouping):java.util.Map<K, java.lang.Integer>
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r6 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r6);
        r6 = new java.util.LinkedHashMap;
        r6.<init>();
        r6 = (java.util.Map) r6;
        r1 = r10;
        r8 = r1.sourceIterator();
        r7 = r8.hasNext();
        if (r7 == 0) goto L_0x0048;
    L_0x0018:
        r3 = r8.next();
        r5 = r1.keyOf(r3);
        r2 = r6.get(r5);
        if (r2 != 0) goto L_0x0043;
        r7 = r6.containsKey(r5);
        if (r7 != 0) goto L_0x0043;
        r7 = 1;
        r4 = r5;
        if (r7 == 0) goto L_0x0045;
        r10 = new kotlin.jvm.internal.Ref$IntRef;
        r10.<init>();
        r0 = r4;
        r10 = (kotlin.jvm.internal.Ref.IntRef) r10;
        r7 = r10.element;
        r7 = r7 + 1;
        r10.element = r7;
        r6.put(r5, r10);
        goto L_0x0012;
        r7 = 0;
        goto L_0x002d;
        r10 = r2;
        r0 = r4;
        goto L_0x0036;
        r7 = r6.entrySet();
        r7 = (java.lang.Iterable) r7;
        r8 = r7.iterator();
        r7 = r8.hasNext();
        if (r7 == 0) goto L_0x007d;
        r7 = r8.next();
        r7 = (java.util.Map.Entry) r7;
        if (r7 != 0) goto L_0x0069;
        r6 = new kotlin.TypeCastException;
        r7 = "null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>";
        r6.<init>(r7);
        throw r6;
        r9 = kotlin.jvm.internal.TypeIntrinsics.asMutableMapEntry(r7);
        r7 = r7.getValue();
        r7 = (kotlin.jvm.internal.Ref.IntRef) r7;
        r7 = r7.element;
        r7 = java.lang.Integer.valueOf(r7);
        r9.setValue(r7);
        goto L_0x0053;
        r6 = kotlin.jvm.internal.TypeIntrinsics.asMutableMap(r6);
        return r6;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.GroupingKt.eachCount(kotlin.collections.Grouping):java.util.Map<K, java.lang.Integer>");
    }

    @org.jetbrains.annotations.NotNull
    @kotlin.SinceKotlin(version = "1.1")
    public static final <T, K, M extends java.util.Map<? super K, java.lang.Integer>> M eachCountTo(@org.jetbrains.annotations.NotNull kotlin.collections.Grouping<T, ? extends K> r1, @org.jetbrains.annotations.NotNull M r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.collections.GroupingKt.eachCountTo(kotlin.collections.Grouping, java.util.Map):M
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r8 = 0;
        r7 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r7);
        r7 = "destination";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r7);
        r5 = java.lang.Integer.valueOf(r8);
        r1 = r10;
        r9 = r1.sourceIterator();
        r7 = r9.hasNext();
        if (r7 == 0) goto L_0x0047;
    L_0x001a:
        r4 = r9.next();
        r6 = r1.keyOf(r4);
        r3 = r11.get(r6);
        if (r3 != 0) goto L_0x0043;
        r7 = r11.containsKey(r6);
        if (r7 != 0) goto L_0x0043;
        r7 = 1;
        r0 = r6;
        if (r7 == 0) goto L_0x0045;
        r7 = r5;
        r7 = (java.lang.Number) r7;
        r2 = r7.intValue();
        r7 = r2 + 1;
        r7 = java.lang.Integer.valueOf(r7);
        r11.put(r6, r7);
        goto L_0x0014;
        r7 = r8;
        goto L_0x002f;
        r7 = r3;
        goto L_0x0033;
        return r11;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.GroupingKt.eachCountTo(kotlin.collections.Grouping, java.util.Map):M");
    }

    @org.jetbrains.annotations.NotNull
    @kotlin.SinceKotlin(version = "1.1")
    public static final <T, K, R> java.util.Map<K, R> fold(@org.jetbrains.annotations.NotNull kotlin.collections.Grouping<T, ? extends K> r1, R r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super R, ? super T, ? extends R> r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.collections.GroupingKt.fold(kotlin.collections.Grouping, java.lang.Object, kotlin.jvm.functions.Function2):java.util.Map<K, R>
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r5 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r5);
        r5 = "operation";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r5);
        r1 = r8;
        r5 = new java.util.LinkedHashMap;
        r5.<init>();
        r5 = (java.util.Map) r5;
        r7 = r1.sourceIterator();
        r6 = r7.hasNext();
        if (r6 == 0) goto L_0x0040;
    L_0x001d:
        r3 = r7.next();
        r4 = r1.keyOf(r3);
        r2 = r5.get(r4);
        if (r2 != 0) goto L_0x003e;
        r6 = r5.containsKey(r4);
        if (r6 != 0) goto L_0x003e;
        r6 = 1;
        r0 = r4;
        if (r6 == 0) goto L_0x0036;
        r2 = r9;
        r6 = r10.invoke(r2, r3);
        r5.put(r4, r6);
        goto L_0x0017;
        r6 = 0;
        goto L_0x0032;
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.GroupingKt.fold(kotlin.collections.Grouping, java.lang.Object, kotlin.jvm.functions.Function2):java.util.Map<K, R>");
    }

    @org.jetbrains.annotations.NotNull
    @kotlin.SinceKotlin(version = "1.1")
    public static final <T, K, R> java.util.Map<K, R> fold(@org.jetbrains.annotations.NotNull kotlin.collections.Grouping<T, ? extends K> r1, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function2<? super K, ? super T, ? extends R> r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function3<? super K, ? super R, ? super T, ? extends R> r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.collections.GroupingKt.fold(kotlin.collections.Grouping, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3):java.util.Map<K, R>
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r5 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r5);
        r5 = "initialValueSelector";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r5);
        r5 = "operation";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r5);
        r0 = r8;
        r5 = new java.util.LinkedHashMap;
        r5.<init>();
        r5 = (java.util.Map) r5;
        r7 = r0.sourceIterator();
        r6 = r7.hasNext();
        if (r6 == 0) goto L_0x0048;
    L_0x0022:
        r2 = r7.next();
        r4 = r0.keyOf(r2);
        r1 = r5.get(r4);
        if (r1 != 0) goto L_0x0046;
        r6 = r5.containsKey(r4);
        if (r6 != 0) goto L_0x0046;
        r6 = 1;
        r3 = r4;
        if (r6 == 0) goto L_0x003e;
        r1 = r9.invoke(r3, r2);
        r6 = r10.invoke(r3, r1, r2);
        r5.put(r4, r6);
        goto L_0x001c;
        r6 = 0;
        goto L_0x0037;
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.GroupingKt.fold(kotlin.collections.Grouping, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3):java.util.Map<K, R>");
    }

    @kotlin.PublishedApi
    @kotlin.internal.InlineOnly
    private static final <K, V, R> java.util.Map<K, R> mapValuesInPlace(@org.jetbrains.annotations.NotNull java.util.Map<K, V> r1, kotlin.jvm.functions.Function1<? super java.util.Map.Entry<? extends K, ? extends V>, ? extends R> r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.collections.GroupingKt.mapValuesInPlace(java.util.Map, kotlin.jvm.functions.Function1):java.util.Map<K, R>
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r0 = r6.entrySet();
        r0 = (java.lang.Iterable) r0;
        r3 = r0.iterator();
        r4 = r3.hasNext();
        if (r4 == 0) goto L_0x002e;
    L_0x0010:
        r1 = r3.next();
        r2 = r1;
        r2 = (java.util.Map.Entry) r2;
        if (r2 != 0) goto L_0x0021;
        r3 = new kotlin.TypeCastException;
        r4 = "null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>";
        r3.<init>(r4);
        throw r3;
        r4 = kotlin.jvm.internal.TypeIntrinsics.asMutableMapEntry(r2);
        r5 = r7.invoke(r2);
        r4.setValue(r5);
        goto L_0x000a;
        if (r6 != 0) goto L_0x0039;
        r3 = new kotlin.TypeCastException;
        r4 = "null cannot be cast to non-null type kotlin.collections.MutableMap<K, R>";
        r3.<init>(r4);
        throw r3;
        r3 = kotlin.jvm.internal.TypeIntrinsics.asMutableMap(r6);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.GroupingKt.mapValuesInPlace(java.util.Map, kotlin.jvm.functions.Function1):java.util.Map<K, R>");
    }

    @org.jetbrains.annotations.NotNull
    @kotlin.SinceKotlin(version = "1.1")
    public static final <S, T extends S, K> java.util.Map<K, S> reduce(@org.jetbrains.annotations.NotNull kotlin.collections.Grouping<T, ? extends K> r1, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function3<? super K, ? super S, ? super T, ? extends S> r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.collections.GroupingKt.reduce(kotlin.collections.Grouping, kotlin.jvm.functions.Function3):java.util.Map<K, S>
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r5 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r5);
        r5 = "operation";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r5);
        r0 = r8;
        r5 = new java.util.LinkedHashMap;
        r5.<init>();
        r5 = (java.util.Map) r5;
        r7 = r0.sourceIterator();
        r6 = r7.hasNext();
        if (r6 == 0) goto L_0x0040;
    L_0x001d:
        r2 = r7.next();
        r4 = r0.keyOf(r2);
        r1 = r5.get(r4);
        if (r1 != 0) goto L_0x0039;
        r6 = r5.containsKey(r4);
        if (r6 != 0) goto L_0x0039;
        r6 = 1;
        r3 = r4;
        if (r6 == 0) goto L_0x003b;
        r5.put(r4, r2);
        goto L_0x0017;
        r6 = 0;
        goto L_0x0032;
        r2 = r9.invoke(r3, r1, r2);
        goto L_0x0035;
        return r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.GroupingKt.reduce(kotlin.collections.Grouping, kotlin.jvm.functions.Function3):java.util.Map<K, S>");
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <T, K, R> Map<K, R> aggregate(@NotNull Grouping<T, ? extends K> $receiver, @NotNull Function4<? super K, ? super R, ? super T, ? super Boolean, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Map<K, R> linkedHashMap = new LinkedHashMap();
        Iterator sourceIterator = $receiver.sourceIterator();
        while (sourceIterator.hasNext()) {
            Object e$iv = sourceIterator.next();
            Object key$iv = $receiver.keyOf(e$iv);
            Object accumulator$iv = linkedHashMap.get(key$iv);
            boolean z = accumulator$iv == null && !linkedHashMap.containsKey(key$iv);
            linkedHashMap.put(key$iv, operation.invoke(key$iv, accumulator$iv, e$iv, Boolean.valueOf(z)));
        }
        return linkedHashMap;
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <T, K, R, M extends Map<? super K, R>> M aggregateTo(@NotNull Grouping<T, ? extends K> $receiver, @NotNull M destination, @NotNull Function4<? super K, ? super R, ? super T, ? super Boolean, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Iterator sourceIterator = $receiver.sourceIterator();
        while (sourceIterator.hasNext()) {
            Object e = sourceIterator.next();
            Object key = $receiver.keyOf(e);
            Object accumulator = destination.get(key);
            boolean z = accumulator == null && !destination.containsKey(key);
            destination.put(key, operation.invoke(key, accumulator, e, Boolean.valueOf(z)));
        }
        return destination;
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <T, K, R, M extends Map<? super K, R>> M foldTo(@NotNull Grouping<T, ? extends K> $receiver, @NotNull M destination, @NotNull Function2<? super K, ? super T, ? extends R> initialValueSelector, @NotNull Function3<? super K, ? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(initialValueSelector, "initialValueSelector");
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Grouping<T, ? extends K> $receiver$iv = $receiver;
        Iterator sourceIterator = $receiver$iv.sourceIterator();
        while (sourceIterator.hasNext()) {
            Object obj;
            Object e$iv = sourceIterator.next();
            Object key$iv = $receiver$iv.keyOf(e$iv);
            Object accumulator$iv = destination.get(key$iv);
            if (accumulator$iv != null || destination.containsKey(key$iv)) {
                obj = null;
            } else {
                obj = 1;
            }
            Object key = key$iv;
            if (obj != null) {
                accumulator$iv = initialValueSelector.invoke(key, e$iv);
            }
            destination.put(key$iv, operation.invoke(key, accumulator$iv, e$iv));
        }
        return destination;
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <T, K, R, M extends Map<? super K, R>> M foldTo(@NotNull Grouping<T, ? extends K> $receiver, @NotNull M destination, R initialValue, @NotNull Function2<? super R, ? super T, ? extends R> operation) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Grouping<T, ? extends K> $receiver$iv = $receiver;
        Iterator sourceIterator = $receiver$iv.sourceIterator();
        while (sourceIterator.hasNext()) {
            Object obj;
            Object e$iv = sourceIterator.next();
            Object key$iv = $receiver$iv.keyOf(e$iv);
            Object accumulator$iv = destination.get(key$iv);
            if (accumulator$iv != null || destination.containsKey(key$iv)) {
                obj = null;
            } else {
                obj = 1;
            }
            Object $noName_0 = key$iv;
            if (obj != null) {
                accumulator$iv = initialValue;
            }
            destination.put(key$iv, operation.invoke(accumulator$iv, e$iv));
        }
        return destination;
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <S, T extends S, K, M extends Map<? super K, S>> M reduceTo(@NotNull Grouping<T, ? extends K> $receiver, @NotNull M destination, @NotNull Function3<? super K, ? super S, ? super T, ? extends S> operation) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(operation, "operation");
        Grouping<T, ? extends K> $receiver$iv = $receiver;
        Iterator sourceIterator = $receiver$iv.sourceIterator();
        while (sourceIterator.hasNext()) {
            Object obj;
            Object e$iv = sourceIterator.next();
            Object key$iv = $receiver$iv.keyOf(e$iv);
            Object accumulator$iv = destination.get(key$iv);
            if (accumulator$iv != null || destination.containsKey(key$iv)) {
                obj = null;
            } else {
                obj = 1;
            }
            Object key = key$iv;
            if (obj == null) {
                e$iv = operation.invoke(key, accumulator$iv, e$iv);
            }
            destination.put(key$iv, e$iv);
        }
        return destination;
    }
}
