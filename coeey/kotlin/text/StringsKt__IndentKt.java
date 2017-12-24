package kotlin.text;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u000b\u001a!\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0002\b\u0004\u001a\u0011\u0010\u0005\u001a\u00020\u0006*\u00020\u0002H\u0002¢\u0006\u0002\b\u0007\u001a\u0014\u0010\b\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u001aJ\u0010\t\u001a\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\b¢\u0006\u0002\b\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u0002\u001a\u001e\u0010\u0011\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0010\u001a\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002\u001a\n\u0010\u0013\u001a\u00020\u0002*\u00020\u0002\u001a\u0014\u0010\u0014\u001a\u00020\u0002*\u00020\u00022\b\b\u0002\u0010\u0012\u001a\u00020\u0002¨\u0006\u0015"}, d2 = {"getIndentFunction", "Lkotlin/Function1;", "", "indent", "getIndentFunction$StringsKt__IndentKt", "indentWidth", "", "indentWidth$StringsKt__IndentKt", "prependIndent", "reindent", "", "resultSizeEstimate", "indentAddFunction", "indentCutFunction", "reindent$StringsKt__IndentKt", "replaceIndent", "newIndent", "replaceIndentByMargin", "marginPrefix", "trimIndent", "trimMargin", "kotlin-stdlib"}, k = 5, mv = {1, 1, 7}, xi = 1, xs = "kotlin/text/StringsKt")
/* compiled from: Indent.kt */
class StringsKt__IndentKt {
    private static final java.lang.String reindent$StringsKt__IndentKt(@org.jetbrains.annotations.NotNull java.util.List<java.lang.String> r1, int r2, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> r3, kotlin.jvm.functions.Function1<? super java.lang.String, java.lang.String> r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.text.StringsKt__IndentKt.reindent$StringsKt__IndentKt(java.util.List, int, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1):java.lang.String
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r21 = kotlin.collections.CollectionsKt__CollectionsKt.getLastIndex(r22);
        r12 = r22;
        r12 = (java.lang.Iterable) r12;
        r14 = new java.util.ArrayList;
        r14.<init>();
        r14 = (java.util.Collection) r14;
        r13 = r12;
        r17 = 0;
        r3 = r13.iterator();
        r2 = r3.hasNext();
        if (r2 == 0) goto L_0x0061;
    L_0x001c:
        r20 = r3.next();
        r18 = r17 + 1;
        r16 = r17;
        r20 = (java.lang.String) r20;
        r15 = r16;
        if (r15 == 0) goto L_0x002e;
        r0 = r21;
        if (r15 != r0) goto L_0x0045;
        r2 = r20;
        r2 = (java.lang.CharSequence) r2;
        r2 = kotlin.text.StringsKt__StringsJVMKt.isBlank(r2);
        if (r2 == 0) goto L_0x0045;
        r19 = 0;
        if (r19 == 0) goto L_0x0041;
        r0 = r19;
        r14.add(r0);
        r17 = r18;
        goto L_0x0016;
        r0 = r25;
        r1 = r20;
        r2 = r0.invoke(r1);
        r2 = (java.lang.String) r2;
        if (r2 == 0) goto L_0x005e;
        r0 = r24;
        r2 = r0.invoke(r2);
        r2 = (java.lang.String) r2;
        if (r2 == 0) goto L_0x005e;
        r19 = r2;
        goto L_0x003a;
        r19 = r20;
        goto L_0x003a;
        r14 = (java.util.List) r14;
        r2 = r14;
        r2 = (java.lang.Iterable) r2;
        r3 = new java.lang.StringBuilder;
        r0 = r23;
        r3.<init>(r0);
        r3 = (java.lang.Appendable) r3;
        r4 = "\n";
        r4 = (java.lang.CharSequence) r4;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        r11 = 0;
        r2 = kotlin.collections.CollectionsKt___CollectionsKt.joinTo$default(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
        r2 = (java.lang.StringBuilder) r2;
        r2 = r2.toString();
        r3 = "mapIndexedNotNull { inde…\"\\n\")\n        .toString()";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__IndentKt.reindent$StringsKt__IndentKt(java.util.List, int, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function1):java.lang.String");
    }

    @org.jetbrains.annotations.NotNull
    public static final java.lang.String replaceIndent(@org.jetbrains.annotations.NotNull java.lang.String r1, @org.jetbrains.annotations.NotNull java.lang.String r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.text.StringsKt__IndentKt.replaceIndent(java.lang.String, java.lang.String):java.lang.String
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r2 = "$receiver";
        r0 = r30;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r2);
        r2 = "newIndent";
        r0 = r31;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r2);
        r2 = r30;
        r2 = (java.lang.CharSequence) r2;
        r27 = kotlin.text.StringsKt__StringsKt.lines(r2);
        r2 = r27;
        r2 = (java.lang.Iterable) r2;
        r14 = new java.util.ArrayList;
        r14.<init>();
        r14 = (java.util.Collection) r14;
        r3 = r2.iterator();
    L_0x0026:
        r2 = r3.hasNext();
        if (r2 == 0) goto L_0x0047;
    L_0x002c:
        r16 = r3.next();
        r29 = r16;
        r29 = (java.lang.String) r29;
        r29 = (java.lang.CharSequence) r29;
        r2 = kotlin.text.StringsKt__StringsJVMKt.isBlank(r29);
        if (r2 != 0) goto L_0x0045;
    L_0x003c:
        r2 = 1;
    L_0x003d:
        if (r2 == 0) goto L_0x0026;
    L_0x003f:
        r0 = r16;
        r14.add(r0);
        goto L_0x0026;
    L_0x0045:
        r2 = 0;
        goto L_0x003d;
    L_0x0047:
        r14 = (java.util.List) r14;
        r2 = r14;
        r2 = (java.lang.Iterable) r2;
        r14 = new java.util.ArrayList;
        r3 = 10;
        r3 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r2, r3);
        r14.<init>(r3);
        r14 = (java.util.Collection) r14;
        r2 = r2.iterator();
    L_0x005e:
        r3 = r2.hasNext();
        if (r3 == 0) goto L_0x0076;
    L_0x0064:
        r23 = r2.next();
        r23 = (java.lang.String) r23;
        r3 = indentWidth$StringsKt__IndentKt(r23);
        r3 = java.lang.Integer.valueOf(r3);
        r14.add(r3);
        goto L_0x005e;
    L_0x0076:
        r14 = (java.util.List) r14;
        r14 = (java.lang.Iterable) r14;
        r2 = kotlin.collections.CollectionsKt___CollectionsKt.min(r14);
        r2 = (java.lang.Integer) r2;
        if (r2 == 0) goto L_0x00e2;
    L_0x0082:
        r28 = r2.intValue();
        r2 = r30.length();
        r3 = r31.length();
        r4 = r27.size();
        r3 = r3 * r4;
        r4 = r2 + r3;
        r17 = getIndentFunction$StringsKt__IndentKt(r31);
        r25 = kotlin.collections.CollectionsKt__CollectionsKt.getLastIndex(r27);
        r12 = r27;
        r12 = (java.lang.Iterable) r12;
        r15 = new java.util.ArrayList;
        r15.<init>();
        r15 = (java.util.Collection) r15;
        r13 = r12;
        r20 = 0;
        r3 = r13.iterator();
        r2 = r3.hasNext();
        if (r2 == 0) goto L_0x00ff;
    L_0x00b5:
        r24 = r3.next();
        r21 = r20 + 1;
        r19 = r20;
        r26 = r24;
        r26 = (java.lang.String) r26;
        r18 = r19;
        if (r18 == 0) goto L_0x00cb;
        r0 = r18;
        r1 = r25;
        if (r0 != r1) goto L_0x00e5;
        r2 = r26;
        r2 = (java.lang.CharSequence) r2;
        r2 = kotlin.text.StringsKt__StringsJVMKt.isBlank(r2);
        if (r2 == 0) goto L_0x00e5;
        r22 = 0;
        if (r22 == 0) goto L_0x00de;
        r0 = r22;
        r15.add(r0);
        r20 = r21;
        goto L_0x00af;
    L_0x00e2:
        r28 = 0;
        goto L_0x0086;
        r0 = r26;
        r1 = r28;
        r2 = kotlin.text.StringsKt___StringsKt.drop(r0, r1);
        if (r2 == 0) goto L_0x00fc;
        r0 = r17;
        r2 = r0.invoke(r2);
        r2 = (java.lang.String) r2;
        if (r2 == 0) goto L_0x00fc;
        r22 = r2;
        goto L_0x00d7;
        r22 = r26;
        goto L_0x00d7;
        r15 = (java.util.List) r15;
        r2 = r15;
        r2 = (java.lang.Iterable) r2;
        r3 = new java.lang.StringBuilder;
        r3.<init>(r4);
        r3 = (java.lang.Appendable) r3;
        r4 = "\n";
        r4 = (java.lang.CharSequence) r4;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        r11 = 0;
        r2 = kotlin.collections.CollectionsKt___CollectionsKt.joinTo$default(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11);
        r2 = (java.lang.StringBuilder) r2;
        r2 = r2.toString();
        r3 = "mapIndexedNotNull { inde…\"\\n\")\n        .toString()";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__IndentKt.replaceIndent(java.lang.String, java.lang.String):java.lang.String");
    }

    @org.jetbrains.annotations.NotNull
    public static final java.lang.String replaceIndentByMargin(@org.jetbrains.annotations.NotNull java.lang.String r1, @org.jetbrains.annotations.NotNull java.lang.String r2, @org.jetbrains.annotations.NotNull java.lang.String r3) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.text.StringsKt__IndentKt.replaceIndentByMargin(java.lang.String, java.lang.String, java.lang.String):java.lang.String
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r3 = "$receiver";
        r0 = r29;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r3);
        r3 = "newIndent";
        r0 = r30;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r3);
        r3 = "marginPrefix";
        r0 = r31;
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r0, r3);
        r3 = r31;
        r3 = (java.lang.CharSequence) r3;
        r3 = kotlin.text.StringsKt__StringsJVMKt.isBlank(r3);
        if (r3 != 0) goto L_0x0030;
    L_0x001f:
        r3 = 1;
    L_0x0020:
        if (r3 != 0) goto L_0x0032;
    L_0x0022:
        r5 = "marginPrefix must be non-blank string.";
        r3 = new java.lang.IllegalArgumentException;
        r5 = r5.toString();
        r3.<init>(r5);
        r3 = (java.lang.Throwable) r3;
        throw r3;
    L_0x0030:
        r3 = 0;
        goto L_0x0020;
    L_0x0032:
        r3 = r29;
        r3 = (java.lang.CharSequence) r3;
        r28 = kotlin.text.StringsKt__StringsKt.lines(r3);
        r3 = r29.length();
        r5 = r30.length();
        r6 = r28.size();
        r5 = r5 * r6;
        r8 = r3 + r5;
        r19 = getIndentFunction$StringsKt__IndentKt(r30);
        r27 = kotlin.collections.CollectionsKt__CollectionsKt.getLastIndex(r28);
        r16 = r28;
        r16 = (java.lang.Iterable) r16;
        r18 = new java.util.ArrayList;
        r18.<init>();
        r18 = (java.util.Collection) r18;
        r17 = r16;
        r22 = 0;
        r9 = r17.iterator();
        r3 = r9.hasNext();
        if (r3 == 0) goto L_0x00f5;
    L_0x006a:
        r26 = r9.next();
        r23 = r22 + 1;
        r21 = r22;
        r2 = r26;
        r2 = (java.lang.String) r2;
        r20 = r21;
        if (r20 == 0) goto L_0x0080;
        r0 = r20;
        r1 = r27;
        if (r0 != r1) goto L_0x0098;
        r3 = r2;
        r3 = (java.lang.CharSequence) r3;
        r3 = kotlin.text.StringsKt__StringsJVMKt.isBlank(r3);
        if (r3 == 0) goto L_0x0098;
        r25 = 0;
        if (r25 == 0) goto L_0x0094;
        r0 = r18;
        r1 = r25;
        r0.add(r1);
        r22 = r23;
        goto L_0x0064;
        r15 = r2;
        r15 = (java.lang.CharSequence) r15;
        r4 = 0;
        r5 = r15.length();
        if (r4 >= r5) goto L_0x00ca;
        r24 = r15.charAt(r4);
        r3 = kotlin.text.CharsKt__CharJVMKt.isWhitespace(r24);
        if (r3 != 0) goto L_0x00c3;
        r3 = 1;
        if (r3 == 0) goto L_0x00c5;
        r3 = -1;
        if (r4 != r3) goto L_0x00cc;
        r3 = 0;
        if (r3 == 0) goto L_0x00f2;
        r0 = r19;
        r3 = r0.invoke(r3);
        r3 = (java.lang.String) r3;
        if (r3 == 0) goto L_0x00f2;
        r25 = r3;
        goto L_0x008b;
        r3 = 0;
        goto L_0x00ad;
        r20 = r4 + 1;
        r4 = r20;
        goto L_0x00a0;
        r4 = -1;
        goto L_0x00af;
        r5 = 0;
        r6 = 4;
        r7 = 0;
        r3 = r31;
        r3 = kotlin.text.StringsKt__StringsJVMKt.startsWith$default(r2, r3, r4, r5, r6, r7);
        if (r3 == 0) goto L_0x00f0;
        r3 = r31.length();
        r3 = r3 + r4;
        if (r2 != 0) goto L_0x00e6;
        r3 = new kotlin.TypeCastException;
        r5 = "null cannot be cast to non-null type java.lang.String";
        r3.<init>(r5);
        throw r3;
        r3 = r2.substring(r3);
        r5 = "(this as java.lang.String).substring(startIndex)";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r5);
        goto L_0x00b3;
        r3 = 0;
        goto L_0x00b3;
        r25 = r2;
        goto L_0x008b;
        r18 = (java.util.List) r18;
        r5 = r18;
        r5 = (java.lang.Iterable) r5;
        r6 = new java.lang.StringBuilder;
        r6.<init>(r8);
        r6 = (java.lang.Appendable) r6;
        r7 = "\n";
        r7 = (java.lang.CharSequence) r7;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r11 = 0;
        r12 = 0;
        r13 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        r14 = 0;
        r3 = kotlin.collections.CollectionsKt___CollectionsKt.joinTo$default(r5, r6, r7, r8, r9, r10, r11, r12, r13, r14);
        r3 = (java.lang.StringBuilder) r3;
        r3 = r3.toString();
        r5 = "mapIndexedNotNull { inde…\"\\n\")\n        .toString()";
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r5);
        return r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.StringsKt__IndentKt.replaceIndentByMargin(java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String trimMargin$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "|";
        }
        return trimMargin(str, str2);
    }

    @NotNull
    public static final String trimMargin(@NotNull String $receiver, @NotNull String marginPrefix) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(marginPrefix, "marginPrefix");
        return replaceIndentByMargin($receiver, "", marginPrefix);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String replaceIndentByMargin$default(String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        if ((i & 2) != 0) {
            str3 = "|";
        }
        return replaceIndentByMargin(str, str2, str3);
    }

    @NotNull
    public static final String trimIndent(@NotNull String $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return replaceIndent($receiver, "");
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String replaceIndent$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "";
        }
        return replaceIndent(str, str2);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String prependIndent$default(String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str2 = "    ";
        }
        return prependIndent(str, str2);
    }

    @NotNull
    public static final String prependIndent(@NotNull String $receiver, @NotNull String indent) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(indent, "indent");
        return SequencesKt___SequencesKt.joinToString$default(SequencesKt___SequencesKt.map(StringsKt__StringsKt.lineSequence($receiver), new StringsKt__IndentKt$prependIndent$1(indent)), "\n", null, null, 0, null, null, 62, null);
    }

    private static final int indentWidth$StringsKt__IndentKt(@NotNull String $receiver) {
        char it;
        CharSequence $receiver$iv = $receiver;
        char length = $receiver$iv.length();
        for (char c = '\u0000'; c < length; c++) {
            if ((!CharsKt__CharJVMKt.isWhitespace($receiver$iv.charAt(c)) ? 1 : null) != null) {
                it = c;
                break;
            }
        }
        it = '￿';
        return it == '￿' ? $receiver.length() : it;
    }

    private static final Function1<String, String> getIndentFunction$StringsKt__IndentKt(String indent) {
        if ((((CharSequence) indent).length() == 0 ? 1 : null) != null) {
            return StringsKt__IndentKt$getIndentFunction$1.INSTANCE;
        }
        return new StringsKt__IndentKt$getIndentFunction$2(indent);
    }
}
