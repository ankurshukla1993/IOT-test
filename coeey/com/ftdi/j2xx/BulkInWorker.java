package com.ftdi.j2xx;

import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbRequest;
import android.util.Log;
import java.nio.ByteBuffer;
import java.util.concurrent.Semaphore;

/* compiled from: FT_Device */
class BulkInWorker implements Runnable {
    UsbEndpoint mBulkIn;
    UsbDeviceConnection mConnection;
    FT_Device mDevice;
    ProcessInCtrl mInCtrl;
    int mNrBuf = this.mInCtrl.getParams().getBufferNumber();
    Semaphore mPauseLock = new Semaphore(1);
    boolean mPaused = false;
    int mReadTimeout = this.mDevice.getDriverParameters().getReadTimeout();
    UsbRequest mRequest;
    int mTransSize = this.mInCtrl.getParams().getMaxTransferSize();

    BulkInWorker(FT_Device dev, ProcessInCtrl inCtrl, UsbDeviceConnection connection, UsbEndpoint endpoint) {
        this.mDevice = dev;
        this.mBulkIn = endpoint;
        this.mConnection = connection;
        this.mInCtrl = inCtrl;
    }

    void pause() throws InterruptedException {
        this.mPauseLock.acquire();
        this.mPaused = true;
    }

    void restart() {
        this.mPaused = false;
        this.mPauseLock.release();
    }

    boolean paused() {
        return this.mPaused;
    }

    public void run() {
        int bufferIndex = 0;
        do {
            try {
                if (this.mPaused) {
                    this.mPauseLock.acquire();
                    this.mPauseLock.release();
                }
                InBuffer inBuf = this.mInCtrl.acquireWritableBuffer(bufferIndex);
                if (inBuf.getLength() == 0) {
                    ByteBuffer buffer = inBuf.getInputBuffer();
                    buffer.clear();
                    inBuf.setBufferId(bufferIndex);
                    int totalBytesRead = this.mConnection.bulkTransfer(this.mBulkIn, buffer.array(), this.mTransSize, this.mReadTimeout);
                    if (totalBytesRead > 0) {
                        buffer.position(totalBytesRead);
                        buffer.flip();
                        inBuf.setLength(totalBytesRead);
                        this.mInCtrl.releaseReadableBuffer(bufferIndex);
                    }
                }
                bufferIndex = (bufferIndex + 1) % this.mNrBuf;
            } catch (InterruptedException e) {
                try {
                    this.mInCtrl.releaseWritableBuffers();
                    this.mInCtrl.purgeINData();
                    return;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                Log.e("BulkIn::", "Fatal error in BulkIn thread");
                return;
            }
        } while (!Thread.interrupted());
        throw new InterruptedException();
    }
}
