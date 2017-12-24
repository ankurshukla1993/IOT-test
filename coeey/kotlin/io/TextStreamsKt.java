package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\b\u001a\u001c\u0010\u0007\u001a\u00020\b*\u00020\u00022\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\u001e\u0010\n\u001a\u00020\u000b*\u00020\u00022\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000b0\r\u001a\u0010\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0010*\u00020\u0001\u001a\n\u0010\u0011\u001a\u00020\u0012*\u00020\u0013\u001a\u0010\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0015*\u00020\u0002\u001a\n\u0010\u0016\u001a\u00020\u000e*\u00020\u0002\u001a\u0017\u0010\u0016\u001a\u00020\u000e*\u00020\u00132\b\b\u0002\u0010\u0017\u001a\u00020\u0018H\b\u001a\r\u0010\u0019\u001a\u00020\u001a*\u00020\u000eH\b\u001a2\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c*\u00020\u00022\u0018\u0010\u001d\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u0010\u0012\u0004\u0012\u0002H\u001c0\rH\b¢\u0006\u0002\u0010\u001e¨\u0006\u001f"}, d2 = {"buffered", "Ljava/io/BufferedReader;", "Ljava/io/Reader;", "bufferSize", "", "Ljava/io/BufferedWriter;", "Ljava/io/Writer;", "copyTo", "", "out", "forEachLine", "", "action", "Lkotlin/Function1;", "", "lineSequence", "Lkotlin/sequences/Sequence;", "readBytes", "", "Ljava/net/URL;", "readLines", "", "readText", "charset", "Ljava/nio/charset/Charset;", "reader", "Ljava/io/StringReader;", "useLines", "T", "block", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 7})
@JvmName(name = "TextStreamsKt")
/* compiled from: ReadWrite.kt */
public final class TextStreamsKt {
    public static final void forEachLine(@org.jetbrains.annotations.NotNull java.io.Reader r1, @org.jetbrains.annotations.NotNull kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.io.TextStreamsKt.forEachLine(java.io.Reader, kotlin.jvm.functions.Function1):void
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
        r6 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r10, r6);
        r6 = "action";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r6);
        r1 = r10;
        r7 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r6 = r1 instanceof java.io.BufferedReader;
        if (r6 == 0) goto L_0x0043;
    L_0x0011:
        r1 = (java.io.BufferedReader) r1;
        r6 = r1;
        r6 = (java.io.Closeable) r6;
        r8 = 0;
        r0 = r6;	 Catch:{ Exception -> 0x0033 }
        r0 = (java.io.BufferedReader) r0;	 Catch:{ Exception -> 0x0033 }
        r5 = r0;	 Catch:{ Exception -> 0x0033 }
        r4 = lineSequence(r5);	 Catch:{ Exception -> 0x0033 }
        r2 = r11;	 Catch:{ Exception -> 0x0033 }
        r7 = r4.iterator();	 Catch:{ Exception -> 0x0033 }
        r9 = r7.hasNext();	 Catch:{ Exception -> 0x0033 }
        if (r9 == 0) goto L_0x0049;	 Catch:{ Exception -> 0x0033 }
    L_0x002b:
        r3 = r7.next();	 Catch:{ Exception -> 0x0033 }
        r2.invoke(r3);	 Catch:{ Exception -> 0x0033 }
        goto L_0x0025;
    L_0x0033:
        r7 = move-exception;
        r8 = 1;
        r6.close();	 Catch:{ Exception -> 0x0052 }
        r7 = (java.lang.Throwable) r7;	 Catch:{ all -> 0x003c }
        throw r7;	 Catch:{ all -> 0x003c }
    L_0x003c:
        r7 = move-exception;
        if (r8 != 0) goto L_0x0042;
        r6.close();
        throw r7;
    L_0x0043:
        r6 = new java.io.BufferedReader;
        r6.<init>(r1, r7);
        goto L_0x0014;
        r7 = kotlin.Unit.INSTANCE;	 Catch:{ Exception -> 0x0033 }
        r6.close();
        return;
    L_0x0052:
        r9 = move-exception;
        goto L_0x0039;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.TextStreamsKt.forEachLine(java.io.Reader, kotlin.jvm.functions.Function1):void");
    }

    @InlineOnly
    private static final BufferedReader buffered(@NotNull Reader $receiver, int bufferSize) {
        return $receiver instanceof BufferedReader ? (BufferedReader) $receiver : new BufferedReader($receiver, bufferSize);
    }

    @InlineOnly
    private static final BufferedWriter buffered(@NotNull Writer $receiver, int bufferSize) {
        return $receiver instanceof BufferedWriter ? (BufferedWriter) $receiver : new BufferedWriter($receiver, bufferSize);
    }

    @NotNull
    public static final List<String> readLines(@NotNull Reader $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        ArrayList result = new ArrayList();
        forEachLine($receiver, new TextStreamsKt$readLines$1(result));
        return result;
    }

    public static final <T> T useLines(@NotNull Reader $receiver, @NotNull Function1<? super Sequence<String>, ? extends T> block) {
        Throwable th;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(block, "block");
        Closeable bufferedReader = $receiver instanceof BufferedReader ? (BufferedReader) $receiver : new BufferedReader($receiver, 8192);
        int i = 0;
        try {
            T invoke = block.invoke(lineSequence((BufferedReader) bufferedReader));
            InlineMarker.finallyStart(1);
            bufferedReader.close();
            InlineMarker.finallyEnd(1);
            return invoke;
        } catch (Exception e) {
            try {
                bufferedReader.close();
            } catch (Exception e2) {
            }
            throw e;
        } catch (Throwable th2) {
            th = th2;
            i = 1;
            InlineMarker.finallyStart(1);
            if (i == 0) {
                bufferedReader.close();
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    @InlineOnly
    private static final StringReader reader(@NotNull String $receiver) {
        return new StringReader($receiver);
    }

    @NotNull
    public static final Sequence<String> lineSequence(@NotNull BufferedReader $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return SequencesKt__SequencesKt.constrainOnce(new LinesSequence($receiver));
    }

    @NotNull
    public static final String readText(@NotNull Reader $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        StringWriter buffer = new StringWriter();
        copyTo$default($receiver, buffer, 0, 2, null);
        String stringWriter = buffer.toString();
        Intrinsics.checkExpressionValueIsNotNull(stringWriter, "buffer.toString()");
        return stringWriter;
    }

    public static /* bridge */ /* synthetic */ long copyTo$default(Reader reader, Writer writer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return copyTo(reader, writer, i);
    }

    public static final long copyTo(@NotNull Reader $receiver, @NotNull Writer out, int bufferSize) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(out, "out");
        long charsCopied = 0;
        char[] buffer = new char[bufferSize];
        int chars = $receiver.read(buffer);
        while (chars >= 0) {
            out.write(buffer, 0, chars);
            charsCopied += (long) chars;
            chars = $receiver.read(buffer);
        }
        return charsCopied;
    }

    @InlineOnly
    private static final String readText(@NotNull URL $receiver, Charset charset) {
        return new String(readBytes($receiver), charset);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ String readText$default(URL $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new String(readBytes($receiver), charset);
    }

    @NotNull
    public static final byte[] readBytes(@NotNull URL $receiver) {
        Throwable th;
        Object obj = null;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Closeable openStream = $receiver.openStream();
        try {
            byte[] readBytes$default = ByteStreamsKt.readBytes$default((InputStream) openStream, 0, 1, null);
            if (openStream != null) {
                openStream.close();
            }
            return readBytes$default;
        } catch (Exception e) {
            if (openStream != null) {
                try {
                    openStream.close();
                } catch (Exception e2) {
                }
            }
            throw e;
        } catch (Throwable th2) {
            th = th2;
            obj = 1;
            openStream.close();
            throw th;
        }
    }
}
