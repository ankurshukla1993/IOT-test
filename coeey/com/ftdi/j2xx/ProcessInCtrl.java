package com.ftdi.j2xx;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.ftdi.j2xx.D2xxManager.D2xxException;
import com.ftdi.j2xx.D2xxManager.DriverParameters;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;
import java.nio.channels.Pipe.SinkChannel;
import java.nio.channels.Pipe.SourceChannel;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: FT_Device */
class ProcessInCtrl {
    private static final byte FT_MODEM_STATUS_SIZE = (byte) 2;
    private static final byte FT_PACKET_SIZE = (byte) 64;
    private static final int FT_PACKET_SIZE_HI = 512;
    private static final int MAX_PACKETS = 256;
    private int mBufInCounter;
    private ByteBuffer[] mBuffers;
    private Object mCounterLock;
    private FT_Device mDevice;
    private Condition mFullCon;
    private Lock mInFullLock;
    private InBuffer[] mInputBufs;
    private ByteBuffer mMainBuf;
    private Pipe mMainPipe;
    private SinkChannel mMainSink;
    private SourceChannel mMainSource;
    private int mMaxPacketSize;
    private int mNrBuf = this.mParams.getBufferNumber();
    private DriverParameters mParams = this.mDevice.getDriverParameters();
    private Condition mReadInCon;
    private Lock mReadInLock;
    private Semaphore[] mReadable;
    private boolean mSinkFull;
    private Semaphore[] mWritable;

    public ProcessInCtrl(FT_Device dev) {
        this.mDevice = dev;
        int bufSize = this.mParams.getMaxBufferSize();
        this.mMaxPacketSize = this.mDevice.getMaxPacketSize();
        this.mWritable = new Semaphore[this.mNrBuf];
        this.mReadable = new Semaphore[this.mNrBuf];
        this.mInputBufs = new InBuffer[this.mNrBuf];
        this.mBuffers = new ByteBuffer[256];
        this.mInFullLock = new ReentrantLock();
        this.mFullCon = this.mInFullLock.newCondition();
        this.mSinkFull = false;
        this.mReadInLock = new ReentrantLock();
        this.mReadInCon = this.mReadInLock.newCondition();
        this.mCounterLock = new Object();
        resetBufCount();
        this.mMainBuf = ByteBuffer.allocateDirect(bufSize);
        try {
            this.mMainPipe = Pipe.open();
            this.mMainSink = this.mMainPipe.sink();
            this.mMainSource = this.mMainPipe.source();
        } catch (IOException ex) {
            ex.printStackTrace();
            Log.d("ProcessInCtrl", "Create mMainPipe failed!");
        }
        for (int i = 0; i < this.mNrBuf; i++) {
            this.mInputBufs[i] = new InBuffer(bufSize);
            this.mReadable[i] = new Semaphore(1);
            this.mWritable[i] = new Semaphore(1);
            try {
                acquireReadableBuffer(i);
            } catch (Exception ex2) {
                ex2.printStackTrace();
                Log.d("ProcessInCtrl", "Acquire read buffer " + i + " failed!");
            }
        }
    }

    boolean isSinkFull() {
        return this.mSinkFull;
    }

    DriverParameters getParams() {
        return this.mParams;
    }

    InBuffer getBuffer(int idx) {
        InBuffer buffer = null;
        synchronized (this.mInputBufs) {
            if (idx >= 0) {
                if (idx < this.mNrBuf) {
                    buffer = this.mInputBufs[idx];
                }
            }
        }
        return buffer;
    }

    InBuffer acquireWritableBuffer(int idx) throws InterruptedException {
        this.mWritable[idx].acquire();
        InBuffer buffer = getBuffer(idx);
        if (buffer.acquire(idx) == null) {
            return null;
        }
        return buffer;
    }

    InBuffer acquireReadableBuffer(int idx) throws InterruptedException {
        this.mReadable[idx].acquire();
        return getBuffer(idx);
    }

    public void releaseWritableBuffer(int idx) throws InterruptedException {
        synchronized (this.mInputBufs) {
            this.mInputBufs[idx].release(idx);
        }
        this.mWritable[idx].release();
    }

    public void releaseReadableBuffer(int idx) throws InterruptedException {
        this.mReadable[idx].release();
    }

    public void processBulkInData(InBuffer inBuffer) throws D2xxException {
        try {
            int bufSize = inBuffer.getLength();
            if (bufSize < 2) {
                inBuffer.getInputBuffer().clear();
                return;
            }
            if (getFreeSpace() < bufSize - 2) {
                Log.d("ProcessBulkIn::", " Buffer is full, waiting for read....");
                processEventChars(false, (short) 0, (short) 0);
                this.mInFullLock.lock();
                this.mSinkFull = true;
                this.mFullCon.await();
                this.mInFullLock.unlock();
                processBulkInData(inBuffer);
            }
            extractReadData(inBuffer);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            this.mInFullLock.unlock();
            Log.e("ProcessInCtrl", "Exception in Full await!");
        } catch (Exception ex2) {
            ex2.printStackTrace();
            Log.e("ProcessInCtrl", "Exception in ProcessBulkIN");
            throw new D2xxException("Fatal error in BulkIn.");
        }
    }

    private void extractReadData(InBuffer inBuffer) throws InterruptedException {
        int totalData = 0;
        short signalEvents = (short) 0;
        short signalLineEvents = (short) 0;
        boolean signalRxChar = false;
        ByteBuffer buffer = inBuffer.getInputBuffer();
        int bufSize = inBuffer.getLength();
        if (bufSize > 0) {
            int nrPackets = (bufSize / this.mMaxPacketSize) + (bufSize % this.mMaxPacketSize > 0 ? 1 : 0);
            for (int i = 0; i < nrPackets; i++) {
                int lim;
                int pos;
                if (i == nrPackets - 1) {
                    lim = bufSize;
                    buffer.limit(lim);
                    pos = i * this.mMaxPacketSize;
                    buffer.position(pos);
                    byte b0 = buffer.get();
                    signalEvents = (short) (this.mDevice.mDeviceInfoNode.modemStatus ^ ((short) (b0 & 240)));
                    this.mDevice.mDeviceInfoNode.modemStatus = (short) (b0 & 240);
                    this.mDevice.mDeviceInfoNode.lineStatus = (short) (buffer.get() & 255);
                    pos += 2;
                    if (buffer.hasRemaining()) {
                        signalLineEvents = (short) (this.mDevice.mDeviceInfoNode.lineStatus & 30);
                    } else {
                        signalLineEvents = (short) 0;
                    }
                } else {
                    lim = (i + 1) * this.mMaxPacketSize;
                    buffer.limit(lim);
                    pos = (this.mMaxPacketSize * i) + 2;
                    buffer.position(pos);
                }
                totalData += lim - pos;
                this.mBuffers[i] = buffer.slice();
            }
            if (totalData != 0) {
                signalRxChar = true;
                try {
                    long written = this.mMainSink.write(this.mBuffers, 0, nrPackets);
                    if (written != ((long) totalData)) {
                        Log.d("extractReadData::", "written != totalData, written= " + written + " totalData=" + totalData);
                    }
                    incBufCount((int) written);
                    this.mReadInLock.lock();
                    this.mReadInCon.signalAll();
                    this.mReadInLock.unlock();
                } catch (Exception ex) {
                    Log.d("extractReadData::", "Write data to sink failed!!");
                    ex.printStackTrace();
                }
            }
            buffer.clear();
            processEventChars(signalRxChar, signalEvents, signalLineEvents);
        }
    }

    public int readBulkInData(byte[] data, int length, long timeout_ms) {
        int bufSize = this.mParams.getMaxBufferSize();
        long startTime = System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.wrap(data, 0, length);
        if (timeout_ms == 0) {
            timeout_ms = (long) this.mParams.getReadTimeout();
        }
        while (this.mDevice.isOpen()) {
            if (getBytesAvailable() >= length) {
                synchronized (this.mMainSource) {
                    try {
                        this.mMainSource.read(buffer);
                        decBufCount(length);
                    } catch (Exception e) {
                        Log.d("readBulkInData::", "Cannot read data from Source!!");
                    }
                }
                if (this.mSinkFull) {
                    Log.i("FTDI debug::", " buffer is full , and also re start buffer");
                    this.mInFullLock.lock();
                    this.mFullCon.signalAll();
                    this.mSinkFull = false;
                    this.mInFullLock.unlock();
                }
                return length;
            }
            try {
                this.mReadInLock.lock();
                this.mReadInCon.await(System.currentTimeMillis() - startTime, TimeUnit.MILLISECONDS);
                this.mReadInLock.unlock();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                Log.d("readBulkInData::", "Cannot wait to read data!!");
                this.mReadInLock.unlock();
            }
            if (System.currentTimeMillis() - startTime >= timeout_ms) {
                return 0;
            }
        }
        return 0;
    }

    private int incBufCount(int size) {
        int rc;
        synchronized (this.mCounterLock) {
            this.mBufInCounter += size;
            rc = this.mBufInCounter;
        }
        return rc;
    }

    private int decBufCount(int size) {
        int rc;
        synchronized (this.mCounterLock) {
            this.mBufInCounter -= size;
            rc = this.mBufInCounter;
        }
        return rc;
    }

    private void resetBufCount() {
        synchronized (this.mCounterLock) {
            this.mBufInCounter = 0;
        }
    }

    public int getBytesAvailable() {
        int rc;
        synchronized (this.mCounterLock) {
            rc = this.mBufInCounter;
        }
        return rc;
    }

    public int getFreeSpace() {
        return (this.mParams.getMaxBufferSize() - getBytesAvailable()) - 1;
    }

    public int purgeINData() {
        int nrBuf = this.mParams.getBufferNumber();
        synchronized (this.mMainBuf) {
            int read;
            do {
                try {
                    this.mMainSource.configureBlocking(false);
                    read = this.mMainSource.read(this.mMainBuf);
                    this.mMainBuf.clear();
                } catch (Exception e) {
                }
            } while (read != 0);
            resetBufCount();
            for (int i = 0; i < nrBuf; i++) {
                InBuffer inBuf = getBuffer(i);
                if (inBuf.acquired() && inBuf.getLength() > 2) {
                    inBuf.purge();
                }
            }
        }
        return 0;
    }

    public int processEventChars(boolean fRxChar, short sEvents, short slEvents) throws InterruptedException {
        boolean signalRxChar = fRxChar;
        short signalEvents = sEvents;
        short signalLineEvents = slEvents;
        TFtEventNotify Events = new TFtEventNotify();
        Events.Mask = this.mDevice.mEventNotification.Mask;
        if (signalRxChar && (Events.Mask & 1) != 0 && (this.mDevice.mEventMask ^ 1) == 1) {
            FT_Device fT_Device = this.mDevice;
            fT_Device.mEventMask |= 1;
            Intent intent = new Intent("FT_EVENT_RXCHAR");
            intent.putExtra("message", "FT_EVENT_RXCHAR");
            LocalBroadcastManager.getInstance(this.mDevice.mContext).sendBroadcast(intent);
        }
        if (!(signalEvents == (short) 0 || (Events.Mask & 2) == 0 || (this.mDevice.mEventMask ^ 2) != 2)) {
            fT_Device = this.mDevice;
            fT_Device.mEventMask |= 2;
            intent = new Intent("FT_EVENT_MODEM_STATUS");
            intent.putExtra("message", "FT_EVENT_MODEM_STATUS");
            LocalBroadcastManager.getInstance(this.mDevice.mContext).sendBroadcast(intent);
        }
        if (!(signalLineEvents == (short) 0 || (Events.Mask & 4) == 0 || (this.mDevice.mEventMask ^ 4) != 4)) {
            fT_Device = this.mDevice;
            fT_Device.mEventMask |= 4;
            intent = new Intent("FT_EVENT_LINE_STATUS");
            intent.putExtra("message", "FT_EVENT_LINE_STATUS");
            LocalBroadcastManager.getInstance(this.mDevice.mContext).sendBroadcast(intent);
        }
        return 0;
    }

    public void releaseWritableBuffers() throws InterruptedException {
        int nrBuf = this.mParams.getBufferNumber();
        for (int i = 0; i < nrBuf; i++) {
            if (getBuffer(i).acquired()) {
                releaseWritableBuffer(i);
            }
        }
    }

    void close() {
        int i;
        for (i = 0; i < this.mNrBuf; i++) {
            try {
                releaseReadableBuffer(i);
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.d("ProcessInCtrl", "Acquire read buffer " + i + " failed!");
            }
            this.mInputBufs[i] = null;
            this.mReadable[i] = null;
            this.mWritable[i] = null;
        }
        for (i = 0; i < 256; i++) {
            this.mBuffers[i] = null;
        }
        this.mWritable = null;
        this.mReadable = null;
        this.mInputBufs = null;
        this.mBuffers = null;
        this.mMainBuf = null;
        if (this.mSinkFull) {
            this.mInFullLock.lock();
            this.mFullCon.signalAll();
            this.mInFullLock.unlock();
        }
        this.mReadInLock.lock();
        this.mReadInCon.signalAll();
        this.mReadInLock.unlock();
        this.mInFullLock = null;
        this.mFullCon = null;
        this.mCounterLock = null;
        this.mReadInLock = null;
        this.mReadInCon = null;
        try {
            this.mMainSink.close();
            this.mMainSink = null;
            this.mMainSource.close();
            this.mMainSource = null;
            this.mMainPipe = null;
        } catch (IOException ex2) {
            ex2.printStackTrace();
            Log.d("ProcessInCtrl", "Close mMainPipe failed!");
        }
        this.mDevice = null;
        this.mParams = null;
    }
}
