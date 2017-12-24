package org.java_websocket.server;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.java_websocket.WebSocketImpl;

public class WebSocketServer$WebSocketWorker extends Thread {
    static final /* synthetic */ boolean $assertionsDisabled = (!WebSocketServer.class.desiredAssertionStatus());
    private BlockingQueue<WebSocketImpl> iqueue = new LinkedBlockingQueue();
    final /* synthetic */ WebSocketServer this$0;

    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:22:0x001f
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.modifyBlocksTree(BlockProcessor.java:248)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:52)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.rerun(BlockProcessor.java:44)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:57)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r7 = this;
        r3 = 0;
    L_0x0001:
        r4 = r7.iqueue;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        r4 = r4.take();	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        r0 = r4;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        r0 = (org.java_websocket.WebSocketImpl) r0;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        r3 = r0;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        r4 = r3.inQueue;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        r1 = r4.poll();	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        r1 = (java.nio.ByteBuffer) r1;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        r4 = $assertionsDisabled;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        if (r4 != 0) goto L_0x0021;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
    L_0x0017:
        if (r1 != 0) goto L_0x0021;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
    L_0x0019:
        r4 = new java.lang.AssertionError;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        r4.<init>();	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        throw r4;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
    L_0x001f:
        r4 = move-exception;
    L_0x0020:
        return;
    L_0x0021:
        r3.decode(r1);	 Catch:{ Exception -> 0x0031, all -> 0x0053 }
        r4 = r7.this$0;
        org.java_websocket.server.WebSocketServer.access$000(r4, r1);
        goto L_0x0001;
    L_0x002a:
        r2 = move-exception;
        r4 = r7.this$0;
        org.java_websocket.server.WebSocketServer.access$100(r4, r3, r2);
        goto L_0x0020;
    L_0x0031:
        r2 = move-exception;
        r4 = java.lang.System.err;	 Catch:{ Exception -> 0x0031, all -> 0x0053 }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0031, all -> 0x0053 }
        r5.<init>();	 Catch:{ Exception -> 0x0031, all -> 0x0053 }
        r6 = "Error while reading from remote connection: ";	 Catch:{ Exception -> 0x0031, all -> 0x0053 }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0031, all -> 0x0053 }
        r5 = r5.append(r2);	 Catch:{ Exception -> 0x0031, all -> 0x0053 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x0031, all -> 0x0053 }
        r4.println(r5);	 Catch:{ Exception -> 0x0031, all -> 0x0053 }
        r2.printStackTrace();	 Catch:{ Exception -> 0x0031, all -> 0x0053 }
        r4 = r7.this$0;
        org.java_websocket.server.WebSocketServer.access$000(r4, r1);
        goto L_0x0001;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
    L_0x0053:
        r4 = move-exception;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        r5 = r7.this$0;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        org.java_websocket.server.WebSocketServer.access$000(r5, r1);	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        throw r4;	 Catch:{ InterruptedException -> 0x001f, RuntimeException -> 0x002a }
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.server.WebSocketServer$WebSocketWorker.run():void");
    }

    public WebSocketServer$WebSocketWorker(final WebSocketServer this$0) {
        this.this$0 = this$0;
        setName("WebSocketWorker-" + getId());
        setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
            public void uncaughtException(Thread t, Throwable e) {
                System.err.print("Uncaught exception in thread \"" + t.getName() + "\":");
                e.printStackTrace(System.err);
            }
        });
    }

    public void put(WebSocketImpl ws) throws InterruptedException {
        this.iqueue.put(ws);
    }
}
