package kotlin.internal;

import com.facebook.internal.NativeProtocol;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u001a\b\u0010\u0002\u001a\u00020\u0003H\u0002\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0004"}, d2 = {"IMPLEMENTATIONS", "Lkotlin/internal/PlatformImplementations;", "getJavaVersion", "", "kotlin-stdlib"}, k = 2, mv = {1, 1, 7})
/* compiled from: PlatformImplementations.kt */
public final class PlatformImplementationsKt {
    @NotNull
    @JvmField
    public static final PlatformImplementations IMPLEMENTATIONS = null;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.internal.PlatformImplementationsKt.<clinit>():void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r0 = getJavaVersion();
        r1 = 65544; // 0x10008 float:9.1847E-41 double:3.2383E-319;
        if (r0 < r1) goto L_0x0020;
    L_0x000b:
        r1 = "kotlin.internal.JRE8PlatformImplementations";	 Catch:{ ClassNotFoundException -> 0x001f }
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x001f }
        r1 = r1.newInstance();	 Catch:{ ClassNotFoundException -> 0x001f }
        if (r1 != 0) goto L_0x0044;	 Catch:{ ClassNotFoundException -> 0x001f }
        r1 = new kotlin.TypeCastException;	 Catch:{ ClassNotFoundException -> 0x001f }
        r2 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations";	 Catch:{ ClassNotFoundException -> 0x001f }
        r1.<init>(r2);	 Catch:{ ClassNotFoundException -> 0x001f }
        throw r1;	 Catch:{ ClassNotFoundException -> 0x001f }
    L_0x001f:
        r1 = move-exception;
        r1 = 65543; // 0x10007 float:9.1845E-41 double:3.23825E-319;
        if (r0 < r1) goto L_0x003b;
        r1 = "kotlin.internal.JRE7PlatformImplementations";	 Catch:{ ClassNotFoundException -> 0x003a }
        r1 = java.lang.Class.forName(r1);	 Catch:{ ClassNotFoundException -> 0x003a }
        r1 = r1.newInstance();	 Catch:{ ClassNotFoundException -> 0x003a }
        if (r1 != 0) goto L_0x0047;	 Catch:{ ClassNotFoundException -> 0x003a }
        r1 = new kotlin.TypeCastException;	 Catch:{ ClassNotFoundException -> 0x003a }
        r2 = "null cannot be cast to non-null type kotlin.internal.PlatformImplementations";	 Catch:{ ClassNotFoundException -> 0x003a }
        r1.<init>(r2);	 Catch:{ ClassNotFoundException -> 0x003a }
        throw r1;	 Catch:{ ClassNotFoundException -> 0x003a }
    L_0x003a:
        r1 = move-exception;
        r1 = new kotlin.internal.PlatformImplementations;
        r1.<init>();
        IMPLEMENTATIONS = r1;
        return;
        r1 = (kotlin.internal.PlatformImplementations) r1;	 Catch:{ ClassNotFoundException -> 0x001f }
        goto L_0x0041;
        r1 = (kotlin.internal.PlatformImplementations) r1;	 Catch:{ ClassNotFoundException -> 0x003a }
        goto L_0x0041;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.internal.PlatformImplementationsKt.<clinit>():void");
    }

    private static final int getJavaVersion() {
        String version = System.getProperty("java.specification.version");
        if (version == null) {
            return NativeProtocol.MESSAGE_GET_LIKE_STATUS_REQUEST;
        }
        int firstDot = StringsKt__StringsKt.indexOf$default((CharSequence) version, '.', 0, false, 6, null);
        if (firstDot < 0) {
            try {
                return Integer.parseInt(version) * 65536;
            } catch (NumberFormatException e) {
                return NativeProtocol.MESSAGE_GET_LIKE_STATUS_REQUEST;
            }
        }
        int secondDot = StringsKt__StringsKt.indexOf$default((CharSequence) version, '.', firstDot + 1, false, 4, null);
        if (secondDot < 0) {
            secondDot = version.length();
        }
        if (version == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String firstPart = version.substring(0, firstDot);
        Intrinsics.checkExpressionValueIsNotNull(firstPart, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        int i = firstDot + 1;
        if (version == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String secondPart = version.substring(i, secondDot);
        Intrinsics.checkExpressionValueIsNotNull(secondPart, "(this as java.lang.Strin…ing(startIndex, endIndex)");
        try {
            return (Integer.parseInt(firstPart) * 65536) + Integer.parseInt(secondPart);
        } catch (NumberFormatException e2) {
            return NativeProtocol.MESSAGE_GET_LIKE_STATUS_REQUEST;
        }
    }
}
