package com.ihealth.androidbg.audio.BG1;

public interface BG1_Command_Interface_Notify {
    void attach(BG1_Command_Interface bG1_Command_Interface);

    void detach(BG1_Command_Interface bG1_Command_Interface);

    void notifyBytes(byte[] bArr);
}
