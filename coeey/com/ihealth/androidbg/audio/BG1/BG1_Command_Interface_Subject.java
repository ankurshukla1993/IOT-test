package com.ihealth.androidbg.audio.BG1;

import java.util.Enumeration;
import java.util.Vector;

public class BG1_Command_Interface_Subject implements BG1_Command_Interface_Notify {
    private Vector f237a = new Vector();

    public void attach(BG1_Command_Interface observer) {
        this.f237a.add(observer);
    }

    public void detach(BG1_Command_Interface observer) {
        this.f237a.remove(observer);
    }

    public void notifyBytes(byte[] msg) {
        Enumeration observers = observers();
        while (observers.hasMoreElements()) {
            ((BG1_Command_Interface) observers.nextElement()).msgBytes(msg);
        }
    }

    public Enumeration observers() {
        return ((Vector) this.f237a.clone()).elements();
    }
}
