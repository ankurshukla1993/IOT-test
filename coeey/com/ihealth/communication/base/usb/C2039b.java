package com.ihealth.communication.base.usb;

import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;

class C2039b extends Thread {
    final /* synthetic */ Pl2303Usb f453a;

    C2039b(Pl2303Usb pl2303Usb) {
        this.f453a = pl2303Usb;
    }

    public void run() {
        if (this.f453a.f442f == null) {
            Log.w("PL2303Interface", "mSerial == null");
        }
        while (this.f453a.f442f != null && this.f453a.f442f.isConnected()) {
            this.f453a.f448l = this.f453a.f442f.read(this.f453a.mReadBuffer);
            if (this.f453a.f448l > 0 && !(this.f453a.f448l == 1 && this.f453a.mReadBuffer[0] == (byte) 0)) {
                Log.p("PL2303Interface", Level.VERBOSE, "Read", new Object[]{ByteBufferUtil.Bytes2HexString(this.f453a.mReadBuffer, this.f453a.f448l)});
                this.f453a.m335a(this.f453a.mReadBuffer, this.f453a.f448l);
            }
        }
    }
}
