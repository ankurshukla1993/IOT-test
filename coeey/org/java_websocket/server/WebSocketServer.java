package org.java_websocket.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.java_websocket.AbstractWebSocket;
import org.java_websocket.WebSocket;
import org.java_websocket.WebSocketFactory;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.drafts.Draft;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.Handshakedata;
import org.java_websocket.handshake.ServerHandshakeBuilder;

public abstract class WebSocketServer extends AbstractWebSocket implements Runnable {
    static final /* synthetic */ boolean $assertionsDisabled = (!WebSocketServer.class.desiredAssertionStatus());
    public static int DECODERS = Runtime.getRuntime().availableProcessors();
    private final InetSocketAddress address;
    private BlockingQueue<ByteBuffer> buffers;
    private final Collection<WebSocket> connections;
    protected List<WebSocketWorker> decoders;
    private List<Draft> drafts;
    private List<WebSocketImpl> iqueue;
    private final AtomicBoolean isclosed;
    private int queueinvokes;
    private final AtomicInteger queuesize;
    private Selector selector;
    private Thread selectorthread;
    private ServerSocketChannel server;
    private WebSocketServerFactory wsf;

    public abstract void onClose(WebSocket webSocket, int i, String str, boolean z);

    public abstract void onError(WebSocket webSocket, Exception exception);

    public abstract void onMessage(WebSocket webSocket, String str);

    public abstract void onOpen(WebSocket webSocket, ClientHandshake clientHandshake);

    public abstract void onStart();

    public WebSocketServer() {
        this(new InetSocketAddress(80), DECODERS, null);
    }

    public WebSocketServer(InetSocketAddress address) {
        this(address, DECODERS, null);
    }

    public WebSocketServer(InetSocketAddress address, int decodercount) {
        this(address, decodercount, null);
    }

    public WebSocketServer(InetSocketAddress address, List<Draft> drafts) {
        this(address, DECODERS, drafts);
    }

    public WebSocketServer(InetSocketAddress address, int decodercount, List<Draft> drafts) {
        this(address, decodercount, drafts, new HashSet());
    }

    public WebSocketServer(InetSocketAddress address, int decodercount, List<Draft> drafts, Collection<WebSocket> connectionscontainer) {
        this.isclosed = new AtomicBoolean(false);
        this.queueinvokes = 0;
        this.queuesize = new AtomicInteger(0);
        this.wsf = new DefaultWebSocketServerFactory();
        if (address == null || decodercount < 1 || connectionscontainer == null) {
            throw new IllegalArgumentException("address and connectionscontainer must not be null and you need at least 1 decoder");
        }
        if (drafts == null) {
            this.drafts = Collections.emptyList();
        } else {
            this.drafts = drafts;
        }
        this.address = address;
        this.connections = connectionscontainer;
        setTcpNoDelay(false);
        this.iqueue = new LinkedList();
        this.decoders = new ArrayList(decodercount);
        this.buffers = new LinkedBlockingQueue();
        for (int i = 0; i < decodercount; i++) {
            WebSocketWorker ex = new WebSocketWorker(this);
            this.decoders.add(ex);
            ex.start();
        }
    }

    public void start() {
        if (this.selectorthread != null) {
            throw new IllegalStateException(getClass().getName() + " can only be started once.");
        }
        new Thread(this).start();
    }

    public void stop(int timeout) throws InterruptedException {
        if (this.isclosed.compareAndSet(false, true)) {
            synchronized (this.connections) {
                List<WebSocket> socketsToClose = new ArrayList(this.connections);
            }
            for (WebSocket ws : socketsToClose) {
                ws.close(1001);
            }
            this.wsf.close();
            synchronized (this) {
                if (!(this.selectorthread == null || this.selectorthread == Thread.currentThread())) {
                    this.selectorthread.interrupt();
                    this.selector.wakeup();
                    this.selectorthread.join((long) timeout);
                }
            }
        }
    }

    public void stop() throws IOException, InterruptedException {
        stop(0);
    }

    public Collection<WebSocket> connections() {
        return this.connections;
    }

    public InetSocketAddress getAddress() {
        return this.address;
    }

    public int getPort() {
        int port = getAddress().getPort();
        if (port != 0 || this.server == null) {
            return port;
        }
        return this.server.socket().getLocalPort();
    }

    public List<Draft> getDraft() {
        return Collections.unmodifiableList(this.drafts);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r18 = this;
        monitor-enter(r18);
        r0 = r18;
        r13 = r0.selectorthread;	 Catch:{ all -> 0x0028 }
        if (r13 == 0) goto L_0x002b;
    L_0x0007:
        r13 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x0028 }
        r14 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0028 }
        r14.<init>();	 Catch:{ all -> 0x0028 }
        r15 = r18.getClass();	 Catch:{ all -> 0x0028 }
        r15 = r15.getName();	 Catch:{ all -> 0x0028 }
        r14 = r14.append(r15);	 Catch:{ all -> 0x0028 }
        r15 = " can only be started once.";
        r14 = r14.append(r15);	 Catch:{ all -> 0x0028 }
        r14 = r14.toString();	 Catch:{ all -> 0x0028 }
        r13.<init>(r14);	 Catch:{ all -> 0x0028 }
        throw r13;	 Catch:{ all -> 0x0028 }
    L_0x0028:
        r13 = move-exception;
        monitor-exit(r18);	 Catch:{ all -> 0x0028 }
        throw r13;
    L_0x002b:
        r13 = java.lang.Thread.currentThread();	 Catch:{ all -> 0x0028 }
        r0 = r18;
        r0.selectorthread = r13;	 Catch:{ all -> 0x0028 }
        r0 = r18;
        r13 = r0.isclosed;	 Catch:{ all -> 0x0028 }
        r13 = r13.get();	 Catch:{ all -> 0x0028 }
        if (r13 == 0) goto L_0x003f;
    L_0x003d:
        monitor-exit(r18);	 Catch:{ all -> 0x0028 }
    L_0x003e:
        return;
    L_0x003f:
        monitor-exit(r18);	 Catch:{ all -> 0x0028 }
        r0 = r18;
        r13 = r0.selectorthread;
        r14 = new java.lang.StringBuilder;
        r14.<init>();
        r15 = "WebsocketSelector";
        r14 = r14.append(r15);
        r0 = r18;
        r15 = r0.selectorthread;
        r16 = r15.getId();
        r0 = r16;
        r14 = r14.append(r0);
        r14 = r14.toString();
        r13.setName(r14);
        r13 = java.nio.channels.ServerSocketChannel.open();	 Catch:{ IOException -> 0x00f1 }
        r0 = r18;
        r0.server = r13;	 Catch:{ IOException -> 0x00f1 }
        r0 = r18;
        r13 = r0.server;	 Catch:{ IOException -> 0x00f1 }
        r14 = 0;
        r13.configureBlocking(r14);	 Catch:{ IOException -> 0x00f1 }
        r0 = r18;
        r13 = r0.server;	 Catch:{ IOException -> 0x00f1 }
        r11 = r13.socket();	 Catch:{ IOException -> 0x00f1 }
        r13 = org.java_websocket.WebSocketImpl.RCVBUF;	 Catch:{ IOException -> 0x00f1 }
        r11.setReceiveBufferSize(r13);	 Catch:{ IOException -> 0x00f1 }
        r0 = r18;
        r13 = r0.address;	 Catch:{ IOException -> 0x00f1 }
        r11.bind(r13);	 Catch:{ IOException -> 0x00f1 }
        r13 = java.nio.channels.Selector.open();	 Catch:{ IOException -> 0x00f1 }
        r0 = r18;
        r0.selector = r13;	 Catch:{ IOException -> 0x00f1 }
        r0 = r18;
        r13 = r0.server;	 Catch:{ IOException -> 0x00f1 }
        r0 = r18;
        r14 = r0.selector;	 Catch:{ IOException -> 0x00f1 }
        r0 = r18;
        r15 = r0.server;	 Catch:{ IOException -> 0x00f1 }
        r15 = r15.validOps();	 Catch:{ IOException -> 0x00f1 }
        r13.register(r14, r15);	 Catch:{ IOException -> 0x00f1 }
        r18.startConnectionLostTimer();	 Catch:{ IOException -> 0x00f1 }
        r18.onStart();	 Catch:{ IOException -> 0x00f1 }
    L_0x00a9:
        r0 = r18;
        r13 = r0.selectorthread;	 Catch:{ RuntimeException -> 0x01cc }
        r13 = r13.isInterrupted();	 Catch:{ RuntimeException -> 0x01cc }
        if (r13 != 0) goto L_0x038d;
    L_0x00b3:
        r9 = 0;
        r5 = 0;
        r0 = r18;
        r13 = r0.selector;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r13.select();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r0 = r18;
        r13 = r0.selector;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r10 = r13.selectedKeys();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r8 = r10.iterator();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
    L_0x00c8:
        r13 = r8.hasNext();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        if (r13 == 0) goto L_0x029f;
    L_0x00ce:
        r13 = r8.next();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r0 = r13;
        r0 = (java.nio.channels.SelectionKey) r0;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r9 = r0;
        r5 = 0;
        r13 = r9.isValid();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        if (r13 == 0) goto L_0x00c8;
    L_0x00dd:
        r13 = r9.isAcceptable();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        if (r13 == 0) goto L_0x0198;
    L_0x00e3:
        r0 = r18;
        r13 = r0.onConnect(r9);	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        if (r13 != 0) goto L_0x0116;
    L_0x00eb:
        r9.cancel();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        goto L_0x00c8;
    L_0x00ef:
        r13 = move-exception;
        goto L_0x00a9;
    L_0x00f1:
        r7 = move-exception;
        r13 = 0;
        r0 = r18;
        r0.handleFatal(r13, r7);
        r0 = r18;
        r13 = r0.decoders;
        if (r13 == 0) goto L_0x003e;
    L_0x00fe:
        r0 = r18;
        r13 = r0.decoders;
        r13 = r13.iterator();
    L_0x0106:
        r14 = r13.hasNext();
        if (r14 == 0) goto L_0x003e;
    L_0x010c:
        r12 = r13.next();
        r12 = (org.java_websocket.server.WebSocketServer.WebSocketWorker) r12;
        r12.interrupt();
        goto L_0x0106;
    L_0x0116:
        r0 = r18;
        r13 = r0.server;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r4 = r13.accept();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        if (r4 == 0) goto L_0x00c8;
    L_0x0120:
        r13 = 0;
        r4.configureBlocking(r13);	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r11 = r4.socket();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r13 = r18.isTcpNoDelay();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r11.setTcpNoDelay(r13);	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r13 = 1;
        r11.setKeepAlive(r13);	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r0 = r18;
        r13 = r0.wsf;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r0 = r18;
        r14 = r0.drafts;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r0 = r18;
        r12 = r13.createWebSocket(r0, r14);	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r0 = r18;
        r13 = r0.selector;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r14 = 1;
        r13 = r4.register(r13, r14, r12);	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r12.key = r13;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r0 = r18;
        r13 = r0.wsf;	 Catch:{ IOException -> 0x0162, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r14 = r12.key;	 Catch:{ IOException -> 0x0162, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r13 = r13.wrapChannel(r4, r14);	 Catch:{ IOException -> 0x0162, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r12.channel = r13;	 Catch:{ IOException -> 0x0162, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r8.remove();	 Catch:{ IOException -> 0x0162, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r0 = r18;
        r0.allocateBuffers(r12);	 Catch:{ IOException -> 0x0162, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        goto L_0x00c8;
    L_0x0162:
        r7 = move-exception;
        r13 = r12.key;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        if (r13 == 0) goto L_0x016c;
    L_0x0167:
        r13 = r12.key;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r13.cancel();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
    L_0x016c:
        r13 = r12.key;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r14 = 0;
        r0 = r18;
        r0.handleIOException(r13, r14, r7);	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        goto L_0x00c8;
    L_0x0176:
        r6 = move-exception;
        r18.stopConnectionLostTimer();
        r0 = r18;
        r13 = r0.decoders;
        if (r13 == 0) goto L_0x0341;
    L_0x0180:
        r0 = r18;
        r13 = r0.decoders;
        r13 = r13.iterator();
    L_0x0188:
        r14 = r13.hasNext();
        if (r14 == 0) goto L_0x0341;
    L_0x018e:
        r12 = r13.next();
        r12 = (org.java_websocket.server.WebSocketServer.WebSocketWorker) r12;
        r12.interrupt();
        goto L_0x0188;
    L_0x0198:
        r13 = r9.isReadable();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        if (r13 == 0) goto L_0x0226;
    L_0x019e:
        r13 = r9.attachment();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r0 = r13;
        r0 = (org.java_websocket.WebSocketImpl) r0;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r5 = r0;
        r2 = r18.takeBuffer();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r13 = r5.channel;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        if (r13 != 0) goto L_0x01f4;
    L_0x01ae:
        if (r9 == 0) goto L_0x01b3;
    L_0x01b0:
        r9.cancel();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
    L_0x01b3:
        r13 = new java.io.IOException;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r13.<init>();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r0 = r18;
        r0.handleIOException(r9, r5, r13);	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        goto L_0x00c8;
    L_0x01bf:
        r7 = move-exception;
        if (r9 == 0) goto L_0x01c5;
    L_0x01c2:
        r9.cancel();	 Catch:{ RuntimeException -> 0x01cc }
    L_0x01c5:
        r0 = r18;
        r0.handleIOException(r9, r5, r7);	 Catch:{ RuntimeException -> 0x01cc }
        goto L_0x00a9;
    L_0x01cc:
        r6 = move-exception;
        r13 = 0;
        r0 = r18;
        r0.handleFatal(r13, r6);	 Catch:{ all -> 0x0277 }
        r18.stopConnectionLostTimer();
        r0 = r18;
        r13 = r0.decoders;
        if (r13 == 0) goto L_0x0314;
    L_0x01dc:
        r0 = r18;
        r13 = r0.decoders;
        r13 = r13.iterator();
    L_0x01e4:
        r14 = r13.hasNext();
        if (r14 == 0) goto L_0x0314;
    L_0x01ea:
        r12 = r13.next();
        r12 = (org.java_websocket.server.WebSocketServer.WebSocketWorker) r12;
        r12.interrupt();
        goto L_0x01e4;
    L_0x01f4:
        r13 = r5.channel;	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r13 = org.java_websocket.SocketChannelIOHelper.read(r2, r5, r13);	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        if (r13 == 0) goto L_0x0299;
    L_0x01fc:
        r13 = r2.hasRemaining();	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        if (r13 == 0) goto L_0x026a;
    L_0x0202:
        r13 = r5.inQueue;	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r13.put(r2);	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r0 = r18;
        r0.queue(r5);	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r8.remove();	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r13 = r5.channel;	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r13 = r13 instanceof org.java_websocket.WrappedByteChannel;	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        if (r13 == 0) goto L_0x0226;
    L_0x0215:
        r13 = r5.channel;	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r13 = (org.java_websocket.WrappedByteChannel) r13;	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r13 = r13.isNeedRead();	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        if (r13 == 0) goto L_0x0226;
    L_0x021f:
        r0 = r18;
        r13 = r0.iqueue;	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r13.add(r5);	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
    L_0x0226:
        r13 = r9.isWritable();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        if (r13 == 0) goto L_0x00c8;
    L_0x022c:
        r13 = r9.attachment();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r0 = r13;
        r0 = (org.java_websocket.WebSocketImpl) r0;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r5 = r0;
        r13 = r5.channel;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r13 = org.java_websocket.SocketChannelIOHelper.batch(r5, r13);	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        if (r13 == 0) goto L_0x00c8;
    L_0x023c:
        r13 = r9.isValid();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        if (r13 == 0) goto L_0x00c8;
    L_0x0242:
        r13 = 1;
        r9.interestOps(r13);	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        goto L_0x00c8;
    L_0x0248:
        r6 = move-exception;
        r18.stopConnectionLostTimer();
        r0 = r18;
        r13 = r0.decoders;
        if (r13 == 0) goto L_0x0367;
    L_0x0252:
        r0 = r18;
        r13 = r0.decoders;
        r13 = r13.iterator();
    L_0x025a:
        r14 = r13.hasNext();
        if (r14 == 0) goto L_0x0367;
    L_0x0260:
        r12 = r13.next();
        r12 = (org.java_websocket.server.WebSocketServer.WebSocketWorker) r12;
        r12.interrupt();
        goto L_0x025a;
    L_0x026a:
        r0 = r18;
        r0.pushBuffer(r2);	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        goto L_0x0226;
    L_0x0270:
        r6 = move-exception;
        r0 = r18;
        r0.pushBuffer(r2);	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        throw r6;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
    L_0x0277:
        r13 = move-exception;
        r18.stopConnectionLostTimer();
        r0 = r18;
        r14 = r0.decoders;
        if (r14 == 0) goto L_0x02f1;
    L_0x0281:
        r0 = r18;
        r14 = r0.decoders;
        r14 = r14.iterator();
    L_0x0289:
        r15 = r14.hasNext();
        if (r15 == 0) goto L_0x02f1;
    L_0x028f:
        r12 = r14.next();
        r12 = (org.java_websocket.server.WebSocketServer.WebSocketWorker) r12;
        r12.interrupt();
        goto L_0x0289;
    L_0x0299:
        r0 = r18;
        r0.pushBuffer(r2);	 Catch:{ IOException -> 0x0270, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        goto L_0x0226;
    L_0x029f:
        r0 = r18;
        r13 = r0.iqueue;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r13 = r13.isEmpty();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        if (r13 != 0) goto L_0x00a9;
    L_0x02a9:
        r0 = r18;
        r13 = r0.iqueue;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r14 = 0;
        r13 = r13.remove(r14);	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r0 = r13;
        r0 = (org.java_websocket.WebSocketImpl) r0;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r5 = r0;
        r3 = r5.channel;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r3 = (org.java_websocket.WrappedByteChannel) r3;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r2 = r18.takeBuffer();	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        r13 = org.java_websocket.SocketChannelIOHelper.readMore(r2, r5, r3);	 Catch:{ IOException -> 0x02dc, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        if (r13 == 0) goto L_0x02cb;
    L_0x02c4:
        r0 = r18;
        r13 = r0.iqueue;	 Catch:{ IOException -> 0x02dc, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r13.add(r5);	 Catch:{ IOException -> 0x02dc, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
    L_0x02cb:
        r13 = r2.hasRemaining();	 Catch:{ IOException -> 0x02dc, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        if (r13 == 0) goto L_0x02e3;
    L_0x02d1:
        r13 = r5.inQueue;	 Catch:{ IOException -> 0x02dc, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r13.put(r2);	 Catch:{ IOException -> 0x02dc, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        r0 = r18;
        r0.queue(r5);	 Catch:{ IOException -> 0x02dc, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        goto L_0x029f;
    L_0x02dc:
        r6 = move-exception;
        r0 = r18;
        r0.pushBuffer(r2);	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
        throw r6;	 Catch:{ CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, IOException -> 0x01bf, InterruptedException -> 0x0248 }
    L_0x02e3:
        r0 = r18;
        r0.pushBuffer(r2);	 Catch:{ IOException -> 0x02dc, CancelledKeyException -> 0x00ef, ClosedByInterruptException -> 0x0176, InterruptedException -> 0x0248 }
        goto L_0x029f;
    L_0x02e9:
        r6 = move-exception;
        r14 = 0;
        r0 = r18;
        r0.onError(r14, r6);
    L_0x02f0:
        throw r13;
    L_0x02f1:
        r0 = r18;
        r14 = r0.selector;
        if (r14 == 0) goto L_0x02fe;
    L_0x02f7:
        r0 = r18;
        r14 = r0.selector;	 Catch:{ IOException -> 0x030c }
        r14.close();	 Catch:{ IOException -> 0x030c }
    L_0x02fe:
        r0 = r18;
        r14 = r0.server;
        if (r14 == 0) goto L_0x02f0;
    L_0x0304:
        r0 = r18;
        r14 = r0.server;	 Catch:{ IOException -> 0x02e9 }
        r14.close();	 Catch:{ IOException -> 0x02e9 }
        goto L_0x02f0;
    L_0x030c:
        r6 = move-exception;
        r14 = 0;
        r0 = r18;
        r0.onError(r14, r6);
        goto L_0x02fe;
    L_0x0314:
        r0 = r18;
        r13 = r0.selector;
        if (r13 == 0) goto L_0x0321;
    L_0x031a:
        r0 = r18;
        r13 = r0.selector;	 Catch:{ IOException -> 0x0339 }
        r13.close();	 Catch:{ IOException -> 0x0339 }
    L_0x0321:
        r0 = r18;
        r13 = r0.server;
        if (r13 == 0) goto L_0x003e;
    L_0x0327:
        r0 = r18;
        r13 = r0.server;	 Catch:{ IOException -> 0x0330 }
        r13.close();	 Catch:{ IOException -> 0x0330 }
        goto L_0x003e;
    L_0x0330:
        r6 = move-exception;
    L_0x0331:
        r13 = 0;
        r0 = r18;
        r0.onError(r13, r6);
        goto L_0x003e;
    L_0x0339:
        r6 = move-exception;
        r13 = 0;
        r0 = r18;
        r0.onError(r13, r6);
        goto L_0x0321;
    L_0x0341:
        r0 = r18;
        r13 = r0.selector;
        if (r13 == 0) goto L_0x034e;
    L_0x0347:
        r0 = r18;
        r13 = r0.selector;	 Catch:{ IOException -> 0x035f }
        r13.close();	 Catch:{ IOException -> 0x035f }
    L_0x034e:
        r0 = r18;
        r13 = r0.server;
        if (r13 == 0) goto L_0x003e;
    L_0x0354:
        r0 = r18;
        r13 = r0.server;	 Catch:{ IOException -> 0x03db }
        r13.close();	 Catch:{ IOException -> 0x035d }
        goto L_0x003e;
    L_0x035d:
        r6 = move-exception;
        goto L_0x0331;
    L_0x035f:
        r6 = move-exception;
        r13 = 0;
        r0 = r18;
        r0.onError(r13, r6);
        goto L_0x034e;
    L_0x0367:
        r0 = r18;
        r13 = r0.selector;
        if (r13 == 0) goto L_0x0374;
    L_0x036d:
        r0 = r18;
        r13 = r0.selector;	 Catch:{ IOException -> 0x0385 }
        r13.close();	 Catch:{ IOException -> 0x0385 }
    L_0x0374:
        r0 = r18;
        r13 = r0.server;
        if (r13 == 0) goto L_0x003e;
    L_0x037a:
        r0 = r18;
        r13 = r0.server;	 Catch:{ IOException -> 0x03d8 }
        r13.close();	 Catch:{ IOException -> 0x0383 }
        goto L_0x003e;
    L_0x0383:
        r6 = move-exception;
        goto L_0x0331;
    L_0x0385:
        r6 = move-exception;
        r13 = 0;
        r0 = r18;
        r0.onError(r13, r6);
        goto L_0x0374;
    L_0x038d:
        r18.stopConnectionLostTimer();
        r0 = r18;
        r13 = r0.decoders;
        if (r13 == 0) goto L_0x03ae;
    L_0x0396:
        r0 = r18;
        r13 = r0.decoders;
        r13 = r13.iterator();
    L_0x039e:
        r14 = r13.hasNext();
        if (r14 == 0) goto L_0x03ae;
    L_0x03a4:
        r12 = r13.next();
        r12 = (org.java_websocket.server.WebSocketServer.WebSocketWorker) r12;
        r12.interrupt();
        goto L_0x039e;
    L_0x03ae:
        r0 = r18;
        r13 = r0.selector;
        if (r13 == 0) goto L_0x03bb;
    L_0x03b4:
        r0 = r18;
        r13 = r0.selector;	 Catch:{ IOException -> 0x03cd }
        r13.close();	 Catch:{ IOException -> 0x03cd }
    L_0x03bb:
        r0 = r18;
        r13 = r0.server;
        if (r13 == 0) goto L_0x003e;
    L_0x03c1:
        r0 = r18;
        r13 = r0.server;	 Catch:{ IOException -> 0x03d5 }
        r13.close();	 Catch:{ IOException -> 0x03ca }
        goto L_0x003e;
    L_0x03ca:
        r6 = move-exception;
        goto L_0x0331;
    L_0x03cd:
        r6 = move-exception;
        r13 = 0;
        r0 = r18;
        r0.onError(r13, r6);
        goto L_0x03bb;
    L_0x03d5:
        r6 = move-exception;
        goto L_0x0331;
    L_0x03d8:
        r6 = move-exception;
        goto L_0x0331;
    L_0x03db:
        r6 = move-exception;
        goto L_0x0331;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.server.WebSocketServer.run():void");
    }

    protected void allocateBuffers(WebSocket c) throws InterruptedException {
        if (this.queuesize.get() < (this.decoders.size() * 2) + 1) {
            this.queuesize.incrementAndGet();
            this.buffers.put(createBuffer());
        }
    }

    protected void releaseBuffers(WebSocket c) throws InterruptedException {
    }

    public ByteBuffer createBuffer() {
        return ByteBuffer.allocate(WebSocketImpl.RCVBUF);
    }

    protected void queue(WebSocketImpl ws) throws InterruptedException {
        if (ws.workerThread == null) {
            ws.workerThread = (WebSocketWorker) this.decoders.get(this.queueinvokes % this.decoders.size());
            this.queueinvokes++;
        }
        ws.workerThread.put(ws);
    }

    private ByteBuffer takeBuffer() throws InterruptedException {
        return (ByteBuffer) this.buffers.take();
    }

    private void pushBuffer(ByteBuffer buf) throws InterruptedException {
        if (this.buffers.size() <= this.queuesize.intValue()) {
            this.buffers.put(buf);
        }
    }

    private void handleIOException(SelectionKey key, WebSocket conn, IOException ex) {
        if (conn != null) {
            conn.closeConnection(1006, ex.getMessage());
        } else if (key != null) {
            SelectableChannel channel = key.channel();
            if (channel != null && channel.isOpen()) {
                try {
                    channel.close();
                } catch (IOException e) {
                }
                if (WebSocketImpl.DEBUG) {
                    System.out.println("Connection closed because of " + ex);
                }
            }
        }
    }

    private void handleFatal(WebSocket conn, Exception e) {
        onError(conn, e);
        try {
            stop();
        } catch (IOException e1) {
            onError(null, e1);
        } catch (InterruptedException e12) {
            Thread.currentThread().interrupt();
            onError(null, e12);
        }
    }

    public final void onWebsocketMessage(WebSocket conn, String message) {
        onMessage(conn, message);
    }

    @Deprecated
    public void onWebsocketMessageFragment(WebSocket conn, Framedata frame) {
        onFragment(conn, frame);
    }

    public final void onWebsocketMessage(WebSocket conn, ByteBuffer blob) {
        onMessage(conn, blob);
    }

    public final void onWebsocketOpen(WebSocket conn, Handshakedata handshake) {
        if (addConnection(conn)) {
            onOpen(conn, (ClientHandshake) handshake);
        }
    }

    public final void onWebsocketClose(WebSocket conn, int code, String reason, boolean remote) {
        this.selector.wakeup();
        try {
            if (removeConnection(conn)) {
                onClose(conn, code, reason, remote);
            }
        } finally {
            try {
                releaseBuffers(conn);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    protected boolean removeConnection(WebSocket ws) {
        boolean removed;
        synchronized (this.connections) {
            removed = this.connections.remove(ws);
            if ($assertionsDisabled || removed) {
            } else {
                throw new AssertionError();
            }
        }
        if (this.isclosed.get() && this.connections.size() == 0) {
            this.selectorthread.interrupt();
        }
        return removed;
    }

    public ServerHandshakeBuilder onWebsocketHandshakeReceivedAsServer(WebSocket conn, Draft draft, ClientHandshake request) throws InvalidDataException {
        return super.onWebsocketHandshakeReceivedAsServer(conn, draft, request);
    }

    protected boolean addConnection(WebSocket ws) {
        if (this.isclosed.get()) {
            ws.close(1001);
            return true;
        }
        boolean succ;
        synchronized (this.connections) {
            succ = this.connections.add(ws);
            if ($assertionsDisabled || succ) {
            } else {
                throw new AssertionError();
            }
        }
        return succ;
    }

    public final void onWebsocketError(WebSocket conn, Exception ex) {
        onError(conn, ex);
    }

    public final void onWriteDemand(WebSocket w) {
        WebSocketImpl conn = (WebSocketImpl) w;
        try {
            conn.key.interestOps(5);
        } catch (CancelledKeyException e) {
            conn.outQueue.clear();
        }
        this.selector.wakeup();
    }

    public void onWebsocketCloseInitiated(WebSocket conn, int code, String reason) {
        onCloseInitiated(conn, code, reason);
    }

    public void onWebsocketClosing(WebSocket conn, int code, String reason, boolean remote) {
        onClosing(conn, code, reason, remote);
    }

    public void onCloseInitiated(WebSocket conn, int code, String reason) {
    }

    public void onClosing(WebSocket conn, int code, String reason, boolean remote) {
    }

    public final void setWebSocketFactory(WebSocketServerFactory wsf) {
        this.wsf = wsf;
    }

    public final WebSocketFactory getWebSocketFactory() {
        return this.wsf;
    }

    protected boolean onConnect(SelectionKey key) {
        return true;
    }

    private Socket getSocket(WebSocket conn) {
        return ((SocketChannel) ((WebSocketImpl) conn).key.channel()).socket();
    }

    public InetSocketAddress getLocalSocketAddress(WebSocket conn) {
        return (InetSocketAddress) getSocket(conn).getLocalSocketAddress();
    }

    public InetSocketAddress getRemoteSocketAddress(WebSocket conn) {
        return (InetSocketAddress) getSocket(conn).getRemoteSocketAddress();
    }

    public void onMessage(WebSocket conn, ByteBuffer message) {
    }

    public void onFragment(WebSocket conn, Framedata fragment) {
    }
}
