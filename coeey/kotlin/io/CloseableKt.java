package kotlin.io;

import java.io.Closeable;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u0004\u0018\u00010\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0001\u001a8\u0010\u0005\u001a\u0002H\u0006\"\n\b\u0000\u0010\u0007*\u0004\u0018\u00010\u0002\"\u0004\b\u0001\u0010\u0006*\u0002H\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u0002H\u00060\tH\b¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"closeFinally", "", "Ljava/io/Closeable;", "cause", "", "use", "R", "T", "block", "Lkotlin/Function1;", "(Ljava/io/Closeable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 2, mv = {1, 1, 7})
@JvmName(name = "CloseableKt")
/* compiled from: Closeable.kt */
public final class CloseableKt {
    @kotlin.SinceKotlin(version = "1.1")
    @kotlin.PublishedApi
    public static final void closeFinally(@org.jetbrains.annotations.Nullable java.io.Closeable r1, @org.jetbrains.annotations.Nullable java.lang.Throwable r2) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.DecodeException: Load method exception in method: kotlin.io.CloseableKt.closeFinally(java.io.Closeable, java.lang.Throwable):void
	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:116)
	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:249)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: java.lang.NullPointerException
*/
        /*
        if (r1 != 0) goto L_0x0003;
    L_0x0002:
        return;
    L_0x0003:
        if (r2 != 0) goto L_0x0009;
    L_0x0005:
        r1.close();
        goto L_0x0002;
        r1.close();	 Catch:{ Throwable -> 0x000e }
        goto L_0x0002;
    L_0x000e:
        r0 = move-exception;
        kotlin.ExceptionsKt__ExceptionsKt.addSuppressed(r2, r0);
        goto L_0x0002;
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.io.CloseableKt.closeFinally(java.io.Closeable, java.lang.Throwable):void");
    }

    @InlineOnly
    private static final <T extends Closeable, R> R use(T $receiver, Function1<? super T, ? extends R> block) {
        try {
            R invoke = block.invoke($receiver);
            InlineMarker.finallyStart(1);
            if ($receiver != null) {
                $receiver.close();
            }
            InlineMarker.finallyEnd(1);
            return invoke;
        } catch (Exception e) {
            if ($receiver != null) {
                try {
                    $receiver.close();
                } catch (Exception e2) {
                }
            }
            throw e;
        } catch (Throwable th) {
            InlineMarker.finallyStart(1);
            if (!(true || $receiver == null)) {
                $receiver.close();
            }
            InlineMarker.finallyEnd(1);
        }
    }
}
