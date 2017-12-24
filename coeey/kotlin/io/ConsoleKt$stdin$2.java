package kotlin.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Ljava/io/BufferedReader;", "invoke"}, k = 3, mv = {1, 1, 7})
/* compiled from: Console.kt */
final class ConsoleKt$stdin$2 extends Lambda implements Function0<BufferedReader> {
    public static final ConsoleKt$stdin$2 INSTANCE = new ConsoleKt$stdin$2();

    @Metadata(bv = {1, 0, 2}, d1 = {"\u00003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\u0010\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0004H\u0016J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0016J\b\u0010\u0010\u001a\u00020\u0006H\u0016J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¨\u0006\u0014"}, d2 = {"kotlin/io/ConsoleKt$stdin$2$1", "Ljava/io/InputStream;", "()V", "available", "", "close", "", "mark", "readlimit", "markSupported", "", "read", "b", "", "off", "len", "reset", "skip", "", "n", "kotlin-stdlib"}, k = 1, mv = {1, 1, 7})
    /* compiled from: Console.kt */
    public static final class C06681 extends InputStream {
        C06681() {
        }

        public int read() {
            return System.in.read();
        }

        public void reset() {
            System.in.reset();
        }

        public int read(@NotNull byte[] b) {
            Intrinsics.checkParameterIsNotNull(b, "b");
            return System.in.read(b);
        }

        public void close() {
            System.in.close();
        }

        public void mark(int readlimit) {
            System.in.mark(readlimit);
        }

        public long skip(long n) {
            return System.in.skip(n);
        }

        public int available() {
            return System.in.available();
        }

        public boolean markSupported() {
            return System.in.markSupported();
        }

        public int read(@NotNull byte[] b, int off, int len) {
            Intrinsics.checkParameterIsNotNull(b, "b");
            return System.in.read(b, off, len);
        }
    }

    ConsoleKt$stdin$2() {
        super(0);
    }

    @NotNull
    public final BufferedReader invoke() {
        return new BufferedReader(new InputStreamReader(new C06681()));
    }
}
