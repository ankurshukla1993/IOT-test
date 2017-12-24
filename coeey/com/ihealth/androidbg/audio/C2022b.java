package com.ihealth.androidbg.audio;

import com.ihealth.communication.utils.Log;

class C2022b extends Thread {
    final /* synthetic */ TunnerThread f262a;

    public C2022b(TunnerThread tunnerThread) {
        this.f262a = tunnerThread;
    }

    public void run() {
        super.run();
        try {
            if (this.f262a.f253e != null && this.f262a.f253e.getState() != 0) {
                Log.v(TunnerThread.f249a, "----------Start Recording ----------------- ");
                this.f262a.f253e.startRecording();
                while (this.f262a.f252d) {
                    Object obj = new byte[1024];
                    if (this.f262a.f253e.read(obj, 0, 1024) > 0) {
                        if (obj.length == 1024) {
                            System.arraycopy(this.f262a.f251c, 1024, this.f262a.f251c, 0, 1024);
                            System.arraycopy(obj, 0, this.f262a.f251c, 1024, 1024);
                            byte[] a = this.f262a.m217a(this.f262a.f251c);
                            if (a != null && a.length >= 3) {
                                Log.v(TunnerThread.f249a, "this command =" + this.f262a.m214a(a, a.length));
                                this.f262a.msgSubject.notifyBytes(a);
                                this.f262a.f255g = null;
                            }
                        } else {
                            Log.w(TunnerThread.f249a, "bufferRead.length = " + obj.length);
                        }
                    }
                    if (this.f262a.f258j) {
                        return;
                    }
                }
            }
        } catch (Exception e) {
            Log.w(TunnerThread.f249a, "record Exception = " + e);
        }
    }
}
