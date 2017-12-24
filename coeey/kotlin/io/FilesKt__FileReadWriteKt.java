package kotlin.io;

import com.facebook.internal.NativeProtocol;
import com.facebook.react.views.text.ReactTextShadowNode;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000z\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a!\u0010\n\u001a\u00020\u000b*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\b\u001a!\u0010\u000e\u001a\u00020\u000f*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\rH\b\u001aB\u0010\u0010\u001a\u00020\u0001*\u00020\u000226\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001aJ\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0017\u001a\u00020\r26\u0010\u0011\u001a2\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00010\u0012\u001a7\u0010\u0018\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2!\u0010\u0011\u001a\u001d\u0012\u0013\u0012\u00110\u0007¢\u0006\f\b\u0013\u0012\b\b\u0014\u0012\u0004\b\b(\u001a\u0012\u0004\u0012\u00020\u00010\u0019\u001a\r\u0010\u001b\u001a\u00020\u001c*\u00020\u0002H\b\u001a\r\u0010\u001d\u001a\u00020\u001e*\u00020\u0002H\b\u001a\u0017\u0010\u001f\u001a\u00020 *\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\b\u001a\n\u0010!\u001a\u00020\u0004*\u00020\u0002\u001a\u001a\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00070#*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0014\u0010$\u001a\u00020\u0007*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010%\u001a\u00020&*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\b\u001a<\u0010'\u001a\u0002H(\"\u0004\b\u0000\u0010(*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\t2\u0018\u0010)\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070*\u0012\u0004\u0012\u0002H(0\u0019H\b¢\u0006\u0002\u0010+\u001a\u0012\u0010,\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u001c\u0010-\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t\u001a\u0017\u0010.\u001a\u00020/*\u00020\u00022\b\b\u0002\u0010\b\u001a\u00020\tH\b¨\u00060"}, d2 = {"appendBytes", "", "Ljava/io/File;", "array", "", "appendText", "text", "", "charset", "Ljava/nio/charset/Charset;", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "bufferedWriter", "Ljava/io/BufferedWriter;", "forEachBlock", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "blockSize", "forEachLine", "Lkotlin/Function1;", "line", "inputStream", "Ljava/io/FileInputStream;", "outputStream", "Ljava/io/FileOutputStream;", "printWriter", "Ljava/io/PrintWriter;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "useLines", "T", "block", "Lkotlin/sequences/Sequence;", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "writeText", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, k = 5, mv = {1, 1, 7}, xi = 1, xs = "kotlin/io/FilesKt")
/* compiled from: FileReadWrite.kt */
class FilesKt__FileReadWriteKt extends FilesKt__FilePathComponentsKt {
    @org.jetbrains.annotations.NotNull
    public static final byte[] readBytes(@org.jetbrains.annotations.NotNull java.io.File r1) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.io.FilesKt__FileReadWriteKt.readBytes(java.io.File):byte[]
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        r9 = "$receiver";
        kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r9);
        r9 = new java.io.FileInputStream;
        r9.<init>(r14);
        r9 = (java.io.Closeable) r9;
        r11 = 0;
        r0 = r9;	 Catch:{ Exception -> 0x004a }
        r0 = (java.io.FileInputStream) r0;	 Catch:{ Exception -> 0x004a }
        r2 = r0;	 Catch:{ Exception -> 0x004a }
        r3 = 0;	 Catch:{ Exception -> 0x004a }
        r4 = r14.length();	 Catch:{ Exception -> 0x004a }
        r10 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;	 Catch:{ Exception -> 0x004a }
        r12 = (long) r10;	 Catch:{ Exception -> 0x004a }
        r10 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1));	 Catch:{ Exception -> 0x004a }
        if (r10 <= 0) goto L_0x005a;	 Catch:{ Exception -> 0x004a }
    L_0x001f:
        r10 = new java.lang.OutOfMemoryError;	 Catch:{ Exception -> 0x004a }
        r12 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x004a }
        r12.<init>();	 Catch:{ Exception -> 0x004a }
        r13 = "File ";	 Catch:{ Exception -> 0x004a }
        r12 = r12.append(r13);	 Catch:{ Exception -> 0x004a }
        r12 = r12.append(r14);	 Catch:{ Exception -> 0x004a }
        r13 = " is too big (";	 Catch:{ Exception -> 0x004a }
        r12 = r12.append(r13);	 Catch:{ Exception -> 0x004a }
        r12 = r12.append(r4);	 Catch:{ Exception -> 0x004a }
        r13 = " bytes) to fit in memory.";	 Catch:{ Exception -> 0x004a }
        r12 = r12.append(r13);	 Catch:{ Exception -> 0x004a }
        r12 = r12.toString();	 Catch:{ Exception -> 0x004a }
        r10.<init>(r12);	 Catch:{ Exception -> 0x004a }
        r10 = (java.lang.Throwable) r10;	 Catch:{ Exception -> 0x004a }
        throw r10;	 Catch:{ Exception -> 0x004a }
    L_0x004a:
        r10 = move-exception;
        r11 = 1;
        r9.close();	 Catch:{ Exception -> 0x007a }
        r10 = (java.lang.Throwable) r10;	 Catch:{ all -> 0x0053 }
        throw r10;	 Catch:{ all -> 0x0053 }
    L_0x0053:
        r10 = move-exception;
        if (r11 != 0) goto L_0x0059;
        r9.close();
        throw r10;
        r7 = (int) r4;
        r8 = new byte[r7];	 Catch:{ Exception -> 0x004a }
        if (r7 <= 0) goto L_0x0066;	 Catch:{ Exception -> 0x004a }
        r6 = r2.read(r8, r3, r7);	 Catch:{ Exception -> 0x004a }
        if (r6 >= 0) goto L_0x006d;
        if (r7 != 0) goto L_0x0070;
        r9.close();
        return r8;
        r7 = r7 - r6;
        r3 = r3 + r6;
        goto L_0x005e;
        r8 = java.util.Arrays.copyOf(r8, r3);	 Catch:{ Exception -> 0x004a }
        r10 = "java.util.Arrays.copyOf(this, newSize)";	 Catch:{ Exception -> 0x004a }
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r8, r10);	 Catch:{ Exception -> 0x004a }
        goto L_0x0068;
    L_0x007a:
        r12 = move-exception;
        goto L_0x0050;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.FilesKt__FileReadWriteKt.readBytes(java.io.File):byte[]");
    }

    @InlineOnly
    private static final InputStreamReader reader(@NotNull File $receiver, Charset charset) {
        return new InputStreamReader(new FileInputStream($receiver), charset);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ InputStreamReader reader$default(File $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new InputStreamReader(new FileInputStream($receiver), charset);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(@NotNull File $receiver, Charset charset, int bufferSize) {
        Reader inputStreamReader = new InputStreamReader(new FileInputStream($receiver), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, bufferSize);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ BufferedReader bufferedReader$default(File $receiver, Charset charset, int bufferSize, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i & 2) != 0) {
            bufferSize = 8192;
        }
        Reader inputStreamReader = new InputStreamReader(new FileInputStream($receiver), charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, bufferSize);
    }

    @InlineOnly
    private static final OutputStreamWriter writer(@NotNull File $receiver, Charset charset) {
        return new OutputStreamWriter(new FileOutputStream($receiver), charset);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ OutputStreamWriter writer$default(File $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new OutputStreamWriter(new FileOutputStream($receiver), charset);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(@NotNull File $receiver, Charset charset, int bufferSize) {
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, bufferSize);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ BufferedWriter bufferedWriter$default(File $receiver, Charset charset, int bufferSize, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ((i & 2) != 0) {
            bufferSize = 8192;
        }
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, bufferSize);
    }

    @InlineOnly
    private static final PrintWriter printWriter(@NotNull File $receiver, Charset charset) {
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ PrintWriter printWriter$default(File $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Writer outputStreamWriter = new OutputStreamWriter(new FileOutputStream($receiver), charset);
        return new PrintWriter(outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192));
    }

    public static final void writeBytes(@NotNull File $receiver, @NotNull byte[] array) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(array, "array");
        Closeable fileOutputStream = new FileOutputStream($receiver);
        try {
            ((FileOutputStream) fileOutputStream).write(array);
            Unit unit = Unit.INSTANCE;
            fileOutputStream.close();
        } catch (Exception e) {
            try {
                fileOutputStream.close();
            } catch (Exception e2) {
            }
            throw e;
        } catch (Throwable th) {
            if (1 == 0) {
                fileOutputStream.close();
            }
        }
    }

    public static final void appendBytes(@NotNull File $receiver, @NotNull byte[] array) {
        Throwable th;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(array, "array");
        Closeable fileOutputStream = new FileOutputStream($receiver, true);
        boolean z = false;
        try {
            ((FileOutputStream) fileOutputStream).write(array);
            Unit unit = Unit.INSTANCE;
            fileOutputStream.close();
        } catch (Exception e) {
            try {
                fileOutputStream.close();
            } catch (Exception e2) {
            }
            throw e;
        } catch (Throwable th2) {
            th = th2;
            z = true;
            if (z) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    @NotNull
    public static final String readText(@NotNull File $receiver, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, HttpRequest.PARAM_CHARSET);
        return new String(readBytes($receiver), charset);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ String readText$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readText(file, charset);
    }

    public static final void writeText(@NotNull File $receiver, @NotNull String text, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        Intrinsics.checkParameterIsNotNull(charset, HttpRequest.PARAM_CHARSET);
        Object bytes = text.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        writeBytes($receiver, bytes);
    }

    public static /* bridge */ /* synthetic */ void writeText$default(File file, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        writeText(file, str, charset);
    }

    public static final void appendText(@NotNull File $receiver, @NotNull String text, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(text, ReactTextShadowNode.PROP_TEXT);
        Intrinsics.checkParameterIsNotNull(charset, HttpRequest.PARAM_CHARSET);
        Object bytes = text.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        appendBytes($receiver, bytes);
    }

    public static /* bridge */ /* synthetic */ void appendText$default(File file, String str, Charset charset, int i, Object obj) {
        if ((i & 2) != 0) {
            charset = Charsets.UTF_8;
        }
        appendText(file, str, charset);
    }

    public static final void forEachBlock(@NotNull File $receiver, @NotNull Function2<? super byte[], ? super Integer, Unit> action) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, NativeProtocol.WEB_DIALOG_ACTION);
        forEachBlock($receiver, 4096, action);
    }

    public static final void forEachBlock(@NotNull File $receiver, int blockSize, @NotNull Function2<? super byte[], ? super Integer, Unit> action) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(action, NativeProtocol.WEB_DIALOG_ACTION);
        byte[] arr = new byte[RangesKt___RangesKt.coerceAtLeast(blockSize, 512)];
        FileInputStream fis = new FileInputStream($receiver);
        while (true) {
            int size = fis.read(arr);
            if (size <= 0) {
                break;
            }
            try {
                action.invoke(arr, Integer.valueOf(size));
            } finally {
                fis.close();
            }
        }
    }

    public static /* bridge */ /* synthetic */ void forEachLine$default(File file, Charset charset, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        forEachLine(file, charset, function1);
    }

    public static final void forEachLine(@NotNull File $receiver, @NotNull Charset charset, @NotNull Function1<? super String, Unit> action) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, HttpRequest.PARAM_CHARSET);
        Intrinsics.checkParameterIsNotNull(action, NativeProtocol.WEB_DIALOG_ACTION);
        TextStreamsKt.forEachLine(new BufferedReader(new InputStreamReader(new FileInputStream($receiver), charset)), action);
    }

    @InlineOnly
    private static final FileInputStream inputStream(@NotNull File $receiver) {
        return new FileInputStream($receiver);
    }

    @InlineOnly
    private static final FileOutputStream outputStream(@NotNull File $receiver) {
        return new FileOutputStream($receiver);
    }

    @NotNull
    public static /* bridge */ /* synthetic */ List readLines$default(File file, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return readLines(file, charset);
    }

    @NotNull
    public static final List<String> readLines(@NotNull File $receiver, @NotNull Charset charset) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, HttpRequest.PARAM_CHARSET);
        ArrayList result = new ArrayList();
        forEachLine($receiver, charset, new FilesKt__FileReadWriteKt$readLines$1(result));
        return result;
    }

    public static /* bridge */ /* synthetic */ Object useLines$default(File $receiver, Charset charset, Function1 block, int i, Object obj) {
        Throwable th;
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, HttpRequest.PARAM_CHARSET);
        Intrinsics.checkParameterIsNotNull(block, "block");
        Reader inputStreamReader = new InputStreamReader(new FileInputStream($receiver), charset);
        Closeable bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        int i2 = 0;
        try {
            Object invoke = block.invoke(TextStreamsKt.lineSequence((BufferedReader) bufferedReader));
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
            i2 = 1;
            InlineMarker.finallyStart(1);
            if (i2 == 0) {
                bufferedReader.close();
            }
            InlineMarker.finallyEnd(1);
            throw th;
        }
    }

    public static final <T> T useLines(@NotNull File $receiver, @NotNull Charset charset, @NotNull Function1<? super Sequence<String>, ? extends T> block) {
        Throwable th;
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(charset, HttpRequest.PARAM_CHARSET);
        Intrinsics.checkParameterIsNotNull(block, "block");
        Reader inputStreamReader = new InputStreamReader(new FileInputStream($receiver), charset);
        Closeable bufferedReader = inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
        int i = 0;
        try {
            T invoke = block.invoke(TextStreamsKt.lineSequence((BufferedReader) bufferedReader));
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
}
