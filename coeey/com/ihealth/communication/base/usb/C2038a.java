package com.ihealth.communication.base.usb;

import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;

class C2038a extends Thread {
    final /* synthetic */ FtdiUsb f452a;

    C2038a(FtdiUsb ftdiUsb) {
        this.f452a = ftdiUsb;
        setPriority(1);
    }

    public void run() {
        while (true == this.f452a.bReadThreadGoing) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            }
            synchronized (this.f452a.f428h) {
                this.f452a.iavailable = this.f452a.f428h.getQueueStatus();
                if (this.f452a.iavailable > 0) {
                    if (this.f452a.iavailable > 512) {
                        this.f452a.iavailable = 512;
                    }
                    this.f452a.f428h.read(this.f452a.f434n, this.f452a.iavailable);
                    Log.p("uartInterface", Level.VERBOSE, "Read", new Object[]{ByteBufferUtil.Bytes2HexString(this.f452a.f434n, this.f452a.iavailable)});
                    this.f452a.m328a(this.f452a.f434n, this.f452a.iavailable);
                }
            }
        }
    }
}
