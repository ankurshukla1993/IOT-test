package kotlin.collections;

import com.facebook.internal.NativeProtocol;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.HidesMembers;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000h\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010&\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u001f\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000f\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u001aG\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\b\u001a$\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001aG\u0010\b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\b\u001a9\u0010\t\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\n\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\b\u001a6\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001a'\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004H\b\u001aG\u0010\r\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\b\u001aY\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\n0\u0006H\b\u001ar\u0010\u0013\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011\"\u0010\b\u0003\u0010\u0014*\n\u0012\u0006\b\u0000\u0012\u0002H\u00110\u0015*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0016\u001a\u0002H\u00142$\u0010\u0012\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00110\n0\u0006H\b¢\u0006\u0002\u0010\u0017\u001aG\u0010\u0018\u001a\u00020\u0019\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u001a\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00190\u0006H\b\u001aS\u0010\u001b\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\b\u001aY\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0011*\u00020\u001d*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00110\u0006H\b\u001ar\u0010\u001e\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\b\b\u0002\u0010\u0011*\u00020\u001d\"\u0010\b\u0003\u0010\u0014*\n\u0012\u0006\b\u0000\u0012\u0002H\u00110\u0015*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0016\u001a\u0002H\u00142 \u0010\u0012\u001a\u001c\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0006\u0012\u0004\u0018\u0001H\u00110\u0006H\b¢\u0006\u0002\u0010\u0017\u001al\u0010\u001f\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u0011\"\u0010\b\u0003\u0010\u0014*\n\u0012\u0006\b\u0000\u0012\u0002H\u00110\u0015*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u0006\u0010\u0016\u001a\u0002H\u00142\u001e\u0010\u0012\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\b¢\u0006\u0002\u0010\u0017\u001ae\u0010 \u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110!*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\"\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\b\u001ai\u0010#\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u0010$\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070%j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`&H\b\u001ae\u0010'\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u000e\b\u0002\u0010\u0011*\b\u0012\u0004\u0012\u0002H\u00110!*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\"\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u0002H\u00110\u0006H\b\u001af\u0010(\u001a\u0010\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0003\u0018\u00010\u0007\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u000422\u0010$\u001a.\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00070%j\u0016\u0012\u0012\b\u0000\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007`&\u001a$\u0010)\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004\u001aG\u0010)\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00042\u001e\u0010\u0005\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00010\u0006H\b\u001aV\u0010*\u001a\u0002H+\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0016\b\u0002\u0010+*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004*\u0002H+2\u001e\u0010\u001a\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0007\u0012\u0004\u0012\u00020\u00190\u0006H\b¢\u0006\u0002\u0010,\u001a6\u0010-\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030.0\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\u0010\u0012\u0006\b\u0001\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0004¨\u0006/"}, d2 = {"all", "", "K", "V", "", "predicate", "Lkotlin/Function1;", "", "any", "asIterable", "", "asSequence", "Lkotlin/sequences/Sequence;", "count", "", "flatMap", "", "R", "transform", "flatMapTo", "C", "", "destination", "(Ljava/util/Map;Ljava/util/Collection;Lkotlin/jvm/functions/Function1;)Ljava/util/Collection;", "forEach", "", "action", "map", "mapNotNull", "", "mapNotNullTo", "mapTo", "maxBy", "", "selector", "maxWith", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "minBy", "minWith", "none", "onEach", "M", "(Ljava/util/Map;Lkotlin/jvm/functions/Function1;)Ljava/util/Map;", "toList", "Lkotlin/Pair;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 7}, xi = 1, xs = "kotlin/collections/MapsKt")
/* compiled from: _Maps.kt */
class MapsKt___MapsKt extends MapsKt__MapsKt {
    @org.jetbrains.annotations.NotNull
    public static final <K, V, R> java.util.List<R> mapNotNull(@org.jetbrains.annotations.NotNull java.util.Map<? extends K, ? extends V> r1, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.util.Map.Entry<? extends K, ? extends V>, ? extends R> r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.collections.MapsKt___MapsKt.mapNotNull(java.util.Map, kotlin.jvm.functions.Function1):java.util.List<R>
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
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r5);
        r5 = "transform";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r5);
        r1 = new java.util.ArrayList;
        r1.<init>();
        r1 = (java.util.Collection) r1;
        r0 = r7;
        r5 = r0.entrySet();
        r5 = r5.iterator();
        r6 = r5.hasNext();
        if (r6 == 0) goto L_0x0032;
    L_0x0020:
        r3 = r5.next();
        r3 = (java.util.Map.Entry) r3;
        r2 = r3;
        r4 = r8.invoke(r2);
        if (r4 == 0) goto L_0x0030;
        r1.add(r4);
        goto L_0x001a;
        r1 = (java.util.List) r1;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.MapsKt___MapsKt.mapNotNull(java.util.Map, kotlin.jvm.functions.Function1):java.util.List<R>");
    }

    @org.jetbrains.annotations.NotNull
    public static final <K, V, R, C extends java.util.Collection<? super R>> C mapNotNullTo(@org.jetbrains.annotations.NotNull java.util.Map<? extends K, ? extends V> r1, @org.jetbrains.annotations.NotNull C r2, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.util.Map.Entry<? extends K, ? extends V>, ? extends R> r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.collections.MapsKt___MapsKt.mapNotNullTo(java.util.Map, java.util.Collection, kotlin.jvm.functions.Function1):C
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r4 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r4);
        r4 = "destination";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r4);
        r4 = "transform";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r4);
        r0 = r6;
        r4 = r0.entrySet();
        r4 = r4.iterator();
        r5 = r4.hasNext();
        if (r5 == 0) goto L_0x0030;
    L_0x001e:
        r2 = r4.next();
        r2 = (java.util.Map.Entry) r2;
        r1 = r2;
        r3 = r8.invoke(r1);
        if (r3 == 0) goto L_0x002e;
        r7.add(r3);
        goto L_0x0018;
        return r7;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.collections.MapsKt___MapsKt.mapNotNullTo(java.util.Map, java.util.Collection, kotlin.jvm.functions.Function1):C");
    }

    @NotNull
    public static final <K, V> List<Pair<K, V>> toList(@NotNull Map<? extends K, ? extends V> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        if ($receiver.size() == 0) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        Iterator iterator = $receiver.entrySet().iterator();
        if (!iterator.hasNext()) {
            return CollectionsKt__CollectionsKt.emptyList();
        }
        Entry first = (Entry) iterator.next();
        if (!iterator.hasNext()) {
            return CollectionsKt__CollectionsKt.listOf((Object) new Pair(first.getKey(), first.getValue()));
        }
        ArrayList result = new ArrayList($receiver.size());
        result.add(new Pair(first.getKey(), first.getValue()));
        do {
            Entry entry = (Entry) iterator.next();
            result.add(new Pair(entry.getKey(), entry.getValue()));
        } while (iterator.hasNext());
        return result;
    }

    @NotNull
    public static final <K, V, R> List<R> flatMap(@NotNull Map<? extends K, ? extends V> $receiver, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        Collection destination$iv = new ArrayList();
        for (Entry element$iv : $receiver.entrySet()) {
            CollectionsKt__MutableCollectionsKt.addAll(destination$iv, (Iterable) transform.invoke(element$iv));
        }
        return (List) destination$iv;
    }

    @NotNull
    public static final <K, V, R, C extends Collection<? super R>> C flatMapTo(@NotNull Map<? extends K, ? extends V> $receiver, @NotNull C destination, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends Iterable<? extends R>> transform) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        for (Entry element : $receiver.entrySet()) {
            CollectionsKt__MutableCollectionsKt.addAll((Collection) destination, (Iterable) transform.invoke(element));
        }
        return destination;
    }

    @NotNull
    public static final <K, V, R> List<R> map(@NotNull Map<? extends K, ? extends V> $receiver, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        Collection destination$iv = new ArrayList($receiver.size());
        for (Entry item$iv : $receiver.entrySet()) {
            destination$iv.add(transform.invoke(item$iv));
        }
        return (List) destination$iv;
    }

    @NotNull
    public static final <K, V, R, C extends Collection<? super R>> C mapTo(@NotNull Map<? extends K, ? extends V> $receiver, @NotNull C destination, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends R> transform) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(destination, Param.DESTINATION);
        Intrinsics.checkParameterIsNotNull(transform, "transform");
        for (Entry item : $receiver.entrySet()) {
            destination.add(transform.invoke(item));
        }
        return destination;
    }

    public static final <K, V> boolean all(@NotNull Map<? extends K, ? extends V> $receiver, @NotNull Function1<? super Entry<? extends K, ? extends V>, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        if ($receiver.isEmpty()) {
            return true;
        }
        for (Entry element : $receiver.entrySet()) {
            if (!((Boolean) predicate.invoke(element)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    public static final <K, V> boolean any(@NotNull Map<? extends K, ? extends V> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return !$receiver.isEmpty();
    }

    public static final <K, V> boolean any(@NotNull Map<? extends K, ? extends V> $receiver, @NotNull Function1<? super Entry<? extends K, ? extends V>, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        if ($receiver.isEmpty()) {
            return false;
        }
        for (Entry element : $receiver.entrySet()) {
            if (((Boolean) predicate.invoke(element)).booleanValue()) {
                return true;
            }
        }
        return false;
    }

    @InlineOnly
    private static final <K, V> int count(@NotNull Map<? extends K, ? extends V> $receiver) {
        return $receiver.size();
    }

    public static final <K, V> int count(@NotNull Map<? extends K, ? extends V> $receiver, @NotNull Function1<? super Entry<? extends K, ? extends V>, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        if ($receiver.isEmpty()) {
            return 0;
        }
        int count = 0;
        for (Entry element : $receiver.entrySet()) {
            if (((Boolean) predicate.invoke(element)).booleanValue()) {
                count++;
            }
        }
        return count;
    }

    @HidesMembers
    public static final <K, V> void forEach(@NotNull Map<? extends K, ? extends V> $receiver, @NotNull Function1<? super Entry<? extends K, ? extends V>, Unit> action) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, NativeProtocol.WEB_DIALOG_ACTION);
        for (Entry element : $receiver.entrySet()) {
            action.invoke(element);
        }
    }

    @InlineOnly
    private static final <K, V, R extends Comparable<? super R>> Entry<K, V> maxBy(@NotNull Map<? extends K, ? extends V> $receiver, Function1<? super Entry<? extends K, ? extends V>, ? extends R> selector) {
        Object obj;
        Iterator iterator$iv = $receiver.entrySet().iterator();
        if (iterator$iv.hasNext()) {
            Object maxElem$iv = iterator$iv.next();
            Comparable maxValue$iv = (Comparable) selector.invoke(maxElem$iv);
            while (iterator$iv.hasNext()) {
                Object e$iv = iterator$iv.next();
                Comparable v$iv = (Comparable) selector.invoke(e$iv);
                if (maxValue$iv.compareTo(v$iv) < 0) {
                    maxElem$iv = e$iv;
                    maxValue$iv = v$iv;
                }
            }
            obj = maxElem$iv;
        } else {
            obj = null;
        }
        return (Entry) obj;
    }

    @InlineOnly
    private static final <K, V> Entry<K, V> maxWith(@NotNull Map<? extends K, ? extends V> $receiver, Comparator<? super Entry<? extends K, ? extends V>> comparator) {
        return (Entry) CollectionsKt___CollectionsKt.maxWith($receiver.entrySet(), comparator);
    }

    @Nullable
    public static final <K, V, R extends Comparable<? super R>> Entry<K, V> minBy(@NotNull Map<? extends K, ? extends V> $receiver, @NotNull Function1<? super Entry<? extends K, ? extends V>, ? extends R> selector) {
        Object obj;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(selector, "selector");
        Iterator iterator$iv = $receiver.entrySet().iterator();
        if (iterator$iv.hasNext()) {
            Object minElem$iv = iterator$iv.next();
            Comparable minValue$iv = (Comparable) selector.invoke(minElem$iv);
            while (iterator$iv.hasNext()) {
                Object e$iv = iterator$iv.next();
                Comparable v$iv = (Comparable) selector.invoke(e$iv);
                if (minValue$iv.compareTo(v$iv) > 0) {
                    minElem$iv = e$iv;
                    minValue$iv = v$iv;
                }
            }
            obj = minElem$iv;
        } else {
            obj = null;
        }
        return (Entry) obj;
    }

    @Nullable
    public static final <K, V> Entry<K, V> minWith(@NotNull Map<? extends K, ? extends V> $receiver, @NotNull Comparator<? super Entry<? extends K, ? extends V>> comparator) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(comparator, "comparator");
        return (Entry) CollectionsKt___CollectionsKt.minWith($receiver.entrySet(), comparator);
    }

    public static final <K, V> boolean none(@NotNull Map<? extends K, ? extends V> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return $receiver.isEmpty();
    }

    public static final <K, V> boolean none(@NotNull Map<? extends K, ? extends V> $receiver, @NotNull Function1<? super Entry<? extends K, ? extends V>, Boolean> predicate) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(predicate, "predicate");
        if ($receiver.isEmpty()) {
            return true;
        }
        for (Entry element : $receiver.entrySet()) {
            if (((Boolean) predicate.invoke(element)).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @NotNull
    @SinceKotlin(version = "1.1")
    public static final <K, V, M extends Map<? extends K, ? extends V>> M onEach(@NotNull M $receiver, @NotNull Function1<? super Entry<? extends K, ? extends V>, Unit> action) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, NativeProtocol.WEB_DIALOG_ACTION);
        for (Entry element : $receiver.entrySet()) {
            action.invoke(element);
        }
        return $receiver;
    }

    @InlineOnly
    private static final <K, V> Iterable<Entry<K, V>> asIterable(@NotNull Map<? extends K, ? extends V> $receiver) {
        return $receiver.entrySet();
    }

    @NotNull
    public static final <K, V> Sequence<Entry<K, V>> asSequence(@NotNull Map<? extends K, ? extends V> $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return CollectionsKt___CollectionsKt.asSequence($receiver.entrySet());
    }
}
