package kotlin.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ByteIterator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000Z\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0000\u001a\u00020\u0005*\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004H\b\u001a\u0017\u0010\u0007\u001a\u00020\b*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\b\u001a\u0017\u0010\u000b\u001a\u00020\f*\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\b\u001a\u0017\u0010\r\u001a\u00020\u000e*\u00020\u000f2\b\b\u0002\u0010\t\u001a\u00020\nH\b\u001a\u001c\u0010\u0010\u001a\u00020\u0011*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00062\b\b\u0002\u0010\u0003\u001a\u00020\u0004\u001a\r\u0010\u0013\u001a\u00020\u000e*\u00020\u0014H\b\u001a\u001d\u0010\u0013\u001a\u00020\u000e*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0004H\b\u001a\r\u0010\u0017\u001a\u00020\u0018*\u00020\u0001H\u0002\u001a\u0014\u0010\u0019\u001a\u00020\u0014*\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u0004\u001a\u0017\u0010\u001b\u001a\u00020\u001c*\u00020\u00022\b\b\u0002\u0010\t\u001a\u00020\nH\b\u001a\u0017\u0010\u001d\u001a\u00020\u001e*\u00020\u00062\b\b\u0002\u0010\t\u001a\u00020\nH\b¨\u0006\u001f"}, d2 = {"buffered", "Ljava/io/BufferedInputStream;", "Ljava/io/InputStream;", "bufferSize", "", "Ljava/io/BufferedOutputStream;", "Ljava/io/OutputStream;", "bufferedReader", "Ljava/io/BufferedReader;", "charset", "Ljava/nio/charset/Charset;", "bufferedWriter", "Ljava/io/BufferedWriter;", "byteInputStream", "Ljava/io/ByteArrayInputStream;", "", "copyTo", "", "out", "inputStream", "", "offset", "length", "iterator", "Lkotlin/collections/ByteIterator;", "readBytes", "estimatedSize", "reader", "Ljava/io/InputStreamReader;", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 7})
@JvmName(name = "ByteStreamsKt")
/* compiled from: IOStreams.kt */
public final class ByteStreamsKt {
    @NotNull
    public static final ByteIterator iterator(@NotNull BufferedInputStream $receiver) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        return new ByteStreamsKt$iterator$1($receiver);
    }

    @InlineOnly
    private static final ByteArrayInputStream byteInputStream(@NotNull String $receiver, Charset charset) {
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        Object bytes = $receiver.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return new ByteArrayInputStream(bytes);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ ByteArrayInputStream byteInputStream$default(String $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        if ($receiver == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        Object bytes = $receiver.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return new ByteArrayInputStream(bytes);
    }

    @InlineOnly
    private static final ByteArrayInputStream inputStream(@NotNull byte[] $receiver) {
        return new ByteArrayInputStream($receiver);
    }

    @InlineOnly
    private static final ByteArrayInputStream inputStream(@NotNull byte[] $receiver, int offset, int length) {
        return new ByteArrayInputStream($receiver, offset, length);
    }

    @InlineOnly
    private static final BufferedInputStream buffered(@NotNull InputStream $receiver, int bufferSize) {
        return $receiver instanceof BufferedInputStream ? (BufferedInputStream) $receiver : new BufferedInputStream($receiver, bufferSize);
    }

    @InlineOnly
    private static final InputStreamReader reader(@NotNull InputStream $receiver, Charset charset) {
        return new InputStreamReader($receiver, charset);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ InputStreamReader reader$default(InputStream $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new InputStreamReader($receiver, charset);
    }

    @InlineOnly
    private static final BufferedReader bufferedReader(@NotNull InputStream $receiver, Charset charset) {
        Reader inputStreamReader = new InputStreamReader($receiver, charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ BufferedReader bufferedReader$default(InputStream $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Reader inputStreamReader = new InputStreamReader($receiver, charset);
        return inputStreamReader instanceof BufferedReader ? (BufferedReader) inputStreamReader : new BufferedReader(inputStreamReader, 8192);
    }

    @InlineOnly
    private static final BufferedOutputStream buffered(@NotNull OutputStream $receiver, int bufferSize) {
        return $receiver instanceof BufferedOutputStream ? (BufferedOutputStream) $receiver : new BufferedOutputStream($receiver, bufferSize);
    }

    @InlineOnly
    private static final OutputStreamWriter writer(@NotNull OutputStream $receiver, Charset charset) {
        return new OutputStreamWriter($receiver, charset);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ OutputStreamWriter writer$default(OutputStream $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        return new OutputStreamWriter($receiver, charset);
    }

    @InlineOnly
    private static final BufferedWriter bufferedWriter(@NotNull OutputStream $receiver, Charset charset) {
        Writer outputStreamWriter = new OutputStreamWriter($receiver, charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192);
    }

    @InlineOnly
    static /* bridge */ /* synthetic */ BufferedWriter bufferedWriter$default(OutputStream $receiver, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        Writer outputStreamWriter = new OutputStreamWriter($receiver, charset);
        return outputStreamWriter instanceof BufferedWriter ? (BufferedWriter) outputStreamWriter : new BufferedWriter(outputStreamWriter, 8192);
    }

    public static /* bridge */ /* synthetic */ long copyTo$default(InputStream inputStream, OutputStream outputStream, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return copyTo(inputStream, outputStream, i);
    }

    public static final long copyTo(@NotNull InputStream $receiver, @NotNull OutputStream out, int bufferSize) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        Intrinsics.checkParameterIsNotNull(out, "out");
        long bytesCopied = 0;
        byte[] buffer = new byte[bufferSize];
        int bytes = $receiver.read(buffer);
        while (bytes >= 0) {
            out.write(buffer, 0, bytes);
            bytesCopied += (long) bytes;
            bytes = $receiver.read(buffer);
        }
        return bytesCopied;
    }

    @NotNull
    public static /* bridge */ /* synthetic */ byte[] readBytes$default(InputStream inputStream, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return readBytes(inputStream, i);
    }

    @NotNull
    public static final byte[] readBytes(@NotNull InputStream $receiver, int estimatedSize) {
        Intrinsics.checkParameterIsNotNull($receiver, "$receiver");
        ByteArrayOutputStream buffer = new ByteArrayOutputStream(Math.max(estimatedSize, $receiver.available()));
        copyTo$default($receiver, buffer, 0, 2, null);
        Object toByteArray = buffer.toByteArray();
        Intrinsics.checkExpressionValueIsNotNull(toByteArray, "buffer.toByteArray()");
        return toByteArray;
    }
}
