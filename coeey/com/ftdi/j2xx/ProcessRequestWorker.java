package com.ftdi.j2xx;

import android.util.Log;

/* compiled from: FT_Device */
class ProcessRequestWorker implements Runnable {
    private ProcessInCtrl mInCtrl;
    int mNrBuf = this.mInCtrl.getParams().getBufferNumber();

    ProcessRequestWorker(ProcessInCtrl inCtrl) {
        this.mInCtrl = inCtrl;
    }

    public void run() {
        int bufferIndex = 0;
        do {
            try {
                InBuffer inBuf = this.mInCtrl.acquireReadableBuffer(bufferIndex);
                if (inBuf.getLength() > 0) {
                    this.mInCtrl.processBulkInData(inBuf);
                    inBuf.purge();
                }
                this.mInCtrl.releaseWritableBuffer(bufferIndex);
                bufferIndex = (bufferIndex + 1) % this.mNrBuf;
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                Log.d("ProcessRequestThread::", "Device has been closed.");
                return;
            } catch (Exception ex2) {
                ex2.printStackTrace();
                Log.e("ProcessRequestThread::", "Fatal error!");
                return;
            }
        } while (!Thread.interrupted());
        throw new InterruptedException();
    }
}
