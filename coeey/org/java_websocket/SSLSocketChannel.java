package org.java_websocket;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.net.ssl.SSLEngineResult.Status;
import javax.net.ssl.SSLException;
import org.java_websocket.util.ByteBufferUtils;

public class SSLSocketChannel implements WrappedByteChannel, ByteChannel {
    private final SSLEngine engine;
    private ExecutorService executor;
    private ByteBuffer myAppData;
    private ByteBuffer myNetData;
    private ByteBuffer peerAppData;
    private ByteBuffer peerNetData;
    private SelectionKey selectionKey;
    private final SocketChannel socketChannel;

    static /* synthetic */ class C25441 {
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus = new int[HandshakeStatus.values().length];
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$Status = new int[Status.values().length];

        static {
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.NEED_UNWRAP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.NEED_WRAP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.NEED_TASK.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.NOT_HANDSHAKING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[Status.OK.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[Status.BUFFER_UNDERFLOW.ordinal()] = 2;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[Status.BUFFER_OVERFLOW.ordinal()] = 3;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[Status.CLOSED.ordinal()] = 4;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    public SSLSocketChannel(SocketChannel inputSocketChannel, SSLEngine inputEngine, ExecutorService inputExecutor, SelectionKey key) throws IOException {
        if (inputSocketChannel == null || inputEngine == null || this.executor == inputExecutor) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.socketChannel = inputSocketChannel;
        this.engine = inputEngine;
        this.executor = inputExecutor;
        this.myNetData = ByteBuffer.allocate(this.engine.getSession().getPacketBufferSize());
        this.peerNetData = ByteBuffer.allocate(this.engine.getSession().getPacketBufferSize());
        this.engine.beginHandshake();
        if (!doHandshake()) {
            try {
                this.socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (key != null) {
            key.interestOps(key.interestOps() | 4);
            this.selectionKey = key;
        }
    }

    public synchronized int read(ByteBuffer dst) throws IOException {
        int i;
        if (!dst.hasRemaining()) {
            i = 0;
        } else if (this.peerAppData.hasRemaining()) {
            this.peerAppData.flip();
            i = ByteBufferUtils.transferByteBuffer(this.peerAppData, dst);
        } else {
            this.peerNetData.compact();
            i = this.socketChannel.read(this.peerNetData);
            if (i > 0 || this.peerNetData.hasRemaining()) {
                this.peerNetData.flip();
                while (this.peerNetData.hasRemaining()) {
                    this.peerAppData.compact();
                    try {
                        SSLEngineResult result = this.engine.unwrap(this.peerNetData, this.peerAppData);
                        switch (C25441.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[result.getStatus().ordinal()]) {
                            case 1:
                                this.peerAppData.flip();
                                i = ByteBufferUtils.transferByteBuffer(this.peerAppData, dst);
                                break;
                            case 2:
                                this.peerAppData.flip();
                                i = ByteBufferUtils.transferByteBuffer(this.peerAppData, dst);
                                break;
                            case 3:
                                this.peerAppData = enlargeApplicationBuffer(this.peerAppData);
                            case 4:
                                closeConnection();
                                dst.clear();
                                i = -1;
                                break;
                            default:
                                throw new IllegalStateException("Invalid SSL status: " + result.getStatus());
                        }
                    } catch (SSLException e) {
                        e.printStackTrace();
                        throw e;
                    }
                }
            } else if (i < 0) {
                handleEndOfStream();
            }
            ByteBufferUtils.transferByteBuffer(this.peerAppData, dst);
        }
        return i;
    }

    public synchronized int write(ByteBuffer output) throws IOException {
        int num;
        num = 0;
        while (output.hasRemaining()) {
            this.myNetData.clear();
            SSLEngineResult result = this.engine.wrap(output, this.myNetData);
            switch (C25441.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[result.getStatus().ordinal()]) {
                case 1:
                    this.myNetData.flip();
                    while (this.myNetData.hasRemaining()) {
                        num += this.socketChannel.write(this.myNetData);
                    }
                    break;
                case 2:
                    throw new SSLException("Buffer underflow occured after a wrap. I don't think we should ever get here.");
                case 3:
                    this.myNetData = enlargePacketBuffer(this.myNetData);
                    break;
                case 4:
                    closeConnection();
                    num = 0;
                    break;
                default:
                    throw new IllegalStateException("Invalid SSL status: " + result.getStatus());
            }
        }
        return num;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean doHandshake() throws java.io.IOException {
        /*
        r10 = this;
        r6 = 0;
        r7 = r10.engine;
        r7 = r7.getSession();
        r0 = r7.getApplicationBufferSize();
        r7 = java.nio.ByteBuffer.allocate(r0);
        r10.myAppData = r7;
        r7 = java.nio.ByteBuffer.allocate(r0);
        r10.peerAppData = r7;
        r7 = r10.myNetData;
        r7.clear();
        r7 = r10.peerNetData;
        r7.clear();
        r7 = r10.engine;
        r2 = r7.getHandshakeStatus();
    L_0x0027:
        r7 = javax.net.ssl.SSLEngineResult.HandshakeStatus.FINISHED;
        if (r2 == r7) goto L_0x01a9;
    L_0x002b:
        r7 = javax.net.ssl.SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
        if (r2 == r7) goto L_0x01a9;
    L_0x002f:
        r7 = org.java_websocket.SSLSocketChannel.C25441.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus;
        r8 = r2.ordinal();
        r7 = r7[r8];
        switch(r7) {
            case 1: goto L_0x0053;
            case 2: goto L_0x00fa;
            case 3: goto L_0x0193;
            case 4: goto L_0x0027;
            case 5: goto L_0x0027;
            default: goto L_0x003a;
        };
    L_0x003a:
        r6 = new java.lang.IllegalStateException;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = "Invalid SSL status: ";
        r7 = r7.append(r8);
        r7 = r7.append(r2);
        r7 = r7.toString();
        r6.<init>(r7);
        throw r6;
    L_0x0053:
        r7 = r10.socketChannel;
        r8 = r10.peerNetData;
        r7 = r7.read(r8);
        if (r7 >= 0) goto L_0x007f;
    L_0x005d:
        r7 = r10.engine;
        r7 = r7.isInboundDone();
        if (r7 == 0) goto L_0x006e;
    L_0x0065:
        r7 = r10.engine;
        r7 = r7.isOutboundDone();
        if (r7 == 0) goto L_0x006e;
    L_0x006d:
        return r6;
    L_0x006e:
        r7 = r10.engine;	 Catch:{ SSLException -> 0x01ac }
        r7.closeInbound();	 Catch:{ SSLException -> 0x01ac }
    L_0x0073:
        r7 = r10.engine;
        r7.closeOutbound();
        r7 = r10.engine;
        r2 = r7.getHandshakeStatus();
        goto L_0x0027;
    L_0x007f:
        r7 = r10.peerNetData;
        r7.flip();
        r7 = r10.engine;	 Catch:{ SSLException -> 0x00c3 }
        r8 = r10.peerNetData;	 Catch:{ SSLException -> 0x00c3 }
        r9 = r10.peerAppData;	 Catch:{ SSLException -> 0x00c3 }
        r3 = r7.unwrap(r8, r9);	 Catch:{ SSLException -> 0x00c3 }
        r7 = r10.peerNetData;	 Catch:{ SSLException -> 0x00c3 }
        r7.compact();	 Catch:{ SSLException -> 0x00c3 }
        r2 = r3.getHandshakeStatus();	 Catch:{ SSLException -> 0x00c3 }
        r7 = org.java_websocket.SSLSocketChannel.C25441.$SwitchMap$javax$net$ssl$SSLEngineResult$Status;
        r8 = r3.getStatus();
        r8 = r8.ordinal();
        r7 = r7[r8];
        switch(r7) {
            case 1: goto L_0x0027;
            case 2: goto L_0x00db;
            case 3: goto L_0x00d1;
            case 4: goto L_0x00e5;
            default: goto L_0x00a6;
        };
    L_0x00a6:
        r6 = new java.lang.IllegalStateException;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = "Invalid SSL status: ";
        r7 = r7.append(r8);
        r8 = r3.getStatus();
        r7 = r7.append(r8);
        r7 = r7.toString();
        r6.<init>(r7);
        throw r6;
    L_0x00c3:
        r4 = move-exception;
        r7 = r10.engine;
        r7.closeOutbound();
        r7 = r10.engine;
        r2 = r7.getHandshakeStatus();
        goto L_0x0027;
    L_0x00d1:
        r7 = r10.peerAppData;
        r7 = r10.enlargeApplicationBuffer(r7);
        r10.peerAppData = r7;
        goto L_0x0027;
    L_0x00db:
        r7 = r10.peerNetData;
        r7 = r10.handleBufferUnderflow(r7);
        r10.peerNetData = r7;
        goto L_0x0027;
    L_0x00e5:
        r7 = r10.engine;
        r7 = r7.isOutboundDone();
        if (r7 != 0) goto L_0x006d;
    L_0x00ed:
        r7 = r10.engine;
        r7.closeOutbound();
        r7 = r10.engine;
        r2 = r7.getHandshakeStatus();
        goto L_0x0027;
    L_0x00fa:
        r7 = r10.myNetData;
        r7.clear();
        r7 = r10.engine;	 Catch:{ SSLException -> 0x0139 }
        r8 = r10.myAppData;	 Catch:{ SSLException -> 0x0139 }
        r9 = r10.myNetData;	 Catch:{ SSLException -> 0x0139 }
        r3 = r7.wrap(r8, r9);	 Catch:{ SSLException -> 0x0139 }
        r2 = r3.getHandshakeStatus();	 Catch:{ SSLException -> 0x0139 }
        r7 = org.java_websocket.SSLSocketChannel.C25441.$SwitchMap$javax$net$ssl$SSLEngineResult$Status;
        r8 = r3.getStatus();
        r8 = r8.ordinal();
        r7 = r7[r8];
        switch(r7) {
            case 1: goto L_0x0147;
            case 2: goto L_0x0166;
            case 3: goto L_0x015c;
            case 4: goto L_0x016e;
            default: goto L_0x011c;
        };
    L_0x011c:
        r6 = new java.lang.IllegalStateException;
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r8 = "Invalid SSL status: ";
        r7 = r7.append(r8);
        r8 = r3.getStatus();
        r7 = r7.append(r8);
        r7 = r7.toString();
        r6.<init>(r7);
        throw r6;
    L_0x0139:
        r4 = move-exception;
        r7 = r10.engine;
        r7.closeOutbound();
        r7 = r10.engine;
        r2 = r7.getHandshakeStatus();
        goto L_0x0027;
    L_0x0147:
        r7 = r10.myNetData;
        r7.flip();
    L_0x014c:
        r7 = r10.myNetData;
        r7 = r7.hasRemaining();
        if (r7 == 0) goto L_0x0027;
    L_0x0154:
        r7 = r10.socketChannel;
        r8 = r10.myNetData;
        r7.write(r8);
        goto L_0x014c;
    L_0x015c:
        r7 = r10.myNetData;
        r7 = r10.enlargePacketBuffer(r7);
        r10.myNetData = r7;
        goto L_0x0027;
    L_0x0166:
        r6 = new javax.net.ssl.SSLException;
        r7 = "Buffer underflow occured after a wrap. I don't think we should ever get here.";
        r6.<init>(r7);
        throw r6;
    L_0x016e:
        r7 = r10.myNetData;	 Catch:{ Exception -> 0x0183 }
        r7.flip();	 Catch:{ Exception -> 0x0183 }
    L_0x0173:
        r7 = r10.myNetData;	 Catch:{ Exception -> 0x0183 }
        r7 = r7.hasRemaining();	 Catch:{ Exception -> 0x0183 }
        if (r7 == 0) goto L_0x018c;
    L_0x017b:
        r7 = r10.socketChannel;	 Catch:{ Exception -> 0x0183 }
        r8 = r10.myNetData;	 Catch:{ Exception -> 0x0183 }
        r7.write(r8);	 Catch:{ Exception -> 0x0183 }
        goto L_0x0173;
    L_0x0183:
        r1 = move-exception;
        r7 = r10.engine;
        r2 = r7.getHandshakeStatus();
        goto L_0x0027;
    L_0x018c:
        r7 = r10.peerNetData;	 Catch:{ Exception -> 0x0183 }
        r7.clear();	 Catch:{ Exception -> 0x0183 }
        goto L_0x0027;
    L_0x0193:
        r7 = r10.engine;
        r5 = r7.getDelegatedTask();
        if (r5 == 0) goto L_0x01a1;
    L_0x019b:
        r7 = r10.executor;
        r7.execute(r5);
        goto L_0x0193;
    L_0x01a1:
        r7 = r10.engine;
        r2 = r7.getHandshakeStatus();
        goto L_0x0027;
    L_0x01a9:
        r6 = 1;
        goto L_0x006d;
    L_0x01ac:
        r7 = move-exception;
        goto L_0x0073;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.java_websocket.SSLSocketChannel.doHandshake():boolean");
    }

    private ByteBuffer enlargePacketBuffer(ByteBuffer buffer) {
        return enlargeBuffer(buffer, this.engine.getSession().getPacketBufferSize());
    }

    private ByteBuffer enlargeApplicationBuffer(ByteBuffer buffer) {
        return enlargeBuffer(buffer, this.engine.getSession().getApplicationBufferSize());
    }

    private ByteBuffer enlargeBuffer(ByteBuffer buffer, int sessionProposedCapacity) {
        if (sessionProposedCapacity > buffer.capacity()) {
            return ByteBuffer.allocate(sessionProposedCapacity);
        }
        return ByteBuffer.allocate(buffer.capacity() * 2);
    }

    private ByteBuffer handleBufferUnderflow(ByteBuffer buffer) {
        if (this.engine.getSession().getPacketBufferSize() < buffer.limit()) {
            return buffer;
        }
        ByteBuffer replaceBuffer = enlargePacketBuffer(buffer);
        buffer.flip();
        replaceBuffer.put(buffer);
        return replaceBuffer;
    }

    private void closeConnection() throws IOException {
        this.engine.closeOutbound();
        try {
            doHandshake();
        } catch (IOException e) {
        }
        this.socketChannel.close();
    }

    private void handleEndOfStream() throws IOException {
        try {
            this.engine.closeInbound();
        } catch (Exception e) {
            System.err.println("This engine was forced to close inbound, without having received the proper SSL/TLS close notification message from the peer, due to end of stream.");
        }
        closeConnection();
    }

    public boolean isNeedWrite() {
        return false;
    }

    public void writeMore() throws IOException {
    }

    public boolean isNeedRead() {
        return this.peerNetData.hasRemaining() || this.peerAppData.hasRemaining();
    }

    public int readMore(ByteBuffer dst) throws IOException {
        return read(dst);
    }

    public boolean isBlocking() {
        return this.socketChannel.isBlocking();
    }

    public boolean isOpen() {
        return this.socketChannel.isOpen();
    }

    public void close() throws IOException {
        closeConnection();
    }
}
