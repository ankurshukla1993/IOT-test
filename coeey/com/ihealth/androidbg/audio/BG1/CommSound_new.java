package com.ihealth.androidbg.audio.BG1;

import android.os.SystemClock;
import com.ihealth.androidbg.audio.AudioTrackManager;
import com.ihealth.androidbg.audio.TransToneData;
import com.ihealth.androidbg.audio.TunnerThread;
import com.ihealth.communication.utils.Log;

public class CommSound_new implements BG1_Command_Interface {
    private static final String f241a = CommSound_new.class.getSimpleName();
    private int f242b = 0;
    private byte[] f243c;

    public CommSound_new(TunnerThread tunner) {
        tunner.msgSubject.detach(this);
        tunner.msgSubject.attach(this);
    }

    public static String Bytes2HexString(byte[] b, int len) {
        String str = "";
        for (int i = 0; i < len; i++) {
            String toHexString = Integer.toHexString(b[i] & 255);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            str = str + toHexString.toUpperCase();
        }
        return str;
    }

    private byte[] m209a(byte b, byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[(bArr.length + 3)];
        bArr2[1] = b;
        bArr2[2] = (byte) this.f242b;
        this.f242b += 2;
        int i = bArr2[1] + bArr2[2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr2[i2 + 3] = bArr[i2];
            i += bArr2[i2 + 3];
        }
        bArr2[0] = (byte) (((byte) (((byte) (((byte) ((i & 240) >> 4)) + ((byte) (i & 15)))) << 4)) + ((byte) (bArr.length & 15)));
        return bArr2;
    }

    public void msgBytes(byte[] msg) {
        this.f243c = msg;
        Log.v(f241a, "Back Command <---------- " + Bytes2HexString(msg, msg.length));
    }

    public byte[] receiveCommand(int id) {
        if (this.f243c == null || this.f243c.length <= 3) {
            return null;
        }
        byte[] unPackageCommand = unPackageCommand(this.f243c, id);
        this.f243c = null;
        return unPackageCommand;
    }

    public void sendACK(byte stateID, byte commandID, int sequenceID) {
        byte[] bArr = new byte[4];
        bArr[1] = stateID;
        bArr[2] = (byte) (sequenceID + 1);
        bArr[3] = commandID;
        byte b = (byte) ((bArr[1] + bArr[2]) + bArr[3]);
        bArr[0] = (byte) (((byte) (((byte) (((byte) (b & 15)) + ((byte) ((b & 240) >> 4)))) << 4)) + 1);
        Log.v(f241a, "*********************************************************** ");
        Log.v(f241a, "* send ack ----------> " + Bytes2HexString(bArr, bArr.length));
        Log.v(f241a, "*********************************************************** ");
        AudioTrackManager.getInstance().play(TransToneData.transDataToTone(TransToneData.getDataByOrder(bArr)));
    }

    public void sendCommand() {
        AudioTrackManager.getInstance().play(new int[]{1000, 1000, 1000, 1000});
    }

    public byte[] sendCommand(byte stateID, byte[] command, int id) {
        int i = 5;
        byte[] a = m209a(stateID, command);
        byte[] bArr = null;
        int[] transDataToTone = TransToneData.transDataToTone(TransToneData.getDataByOrder(a));
        int length = (int) (((((double) (a.length + 1)) * 11.6d) + 440.8d) * 1.5d);
        if (a.length != 0) {
            while (AudioTrackManager.inCommunication) {
                int i2 = i - 1;
                if (i == 0) {
                    break;
                }
                Log.v(f241a, "------------------------------------------------------ >");
                Log.v(f241a, "| sendCommand ----------> " + Bytes2HexString(a, a.length));
                Log.v(f241a, "------------------------------------------------------ >");
                AudioTrackManager.getInstance().play(transDataToTone);
                byte[] bArr2 = bArr;
                int i3 = length;
                while (AudioTrackManager.inCommunication) {
                    i = i3 - 1;
                    if (i3 <= 0) {
                        break;
                    }
                    bArr = receiveCommand(id);
                    if (bArr != null) {
                        break;
                    }
                    SystemClock.sleep(1);
                    bArr2 = bArr;
                    i3 = i;
                }
                bArr = bArr2;
                if (bArr != null) {
                    break;
                }
                i = i2;
            }
        }
        return bArr;
    }

    public byte[] unPackageCommand(byte[] receiveCommand, int id) {
        if (receiveCommand == null) {
            Log.v(f241a, "command null");
            return null;
        }
        Log.v(f241a, "Unpackage start = " + Bytes2HexString(receiveCommand, receiveCommand.length));
        int length = receiveCommand.length;
        if (length < 3) {
            return null;
        }
        if ((receiveCommand[0] & 15) + 3 != length) {
            Log.v(f241a, "Unpackage length error");
            if (!AudioTrackManager.isR2017) {
                return null;
            }
        }
        switch (id) {
            case 2:
                if (!(length == 13 && receiveCommand[3] == (byte) -48)) {
                    Log.v(f241a, "APP Id back error");
                    if (!AudioTrackManager.isR2017) {
                        return null;
                    }
                }
                break;
            case 3:
                if (length != 4) {
                    Log.v(f241a, "bottle Id back error");
                    return null;
                }
                break;
            case 40:
                if (length == 4 && (receiveCommand[3] == (byte) -3 || receiveCommand[3] == (byte) -2)) {
                    Log.v(f241a, "iden success ------");
                    break;
                }
                Log.v(f241a, "iden-3 back error");
                return null;
                break;
            case 41:
                if (length != 13) {
                    Log.v(f241a, "iden-2 back error");
                    return null;
                }
                break;
            case 42:
                if (!(length == 18 && receiveCommand[3] == (byte) -5)) {
                    Log.v(f241a, "iden-1 back error");
                    return null;
                }
            case 50:
                if (!(length == 4 && receiveCommand[3] == (byte) 80)) {
                    Log.v(f241a, "3-3 back error");
                    return null;
                }
            case 51:
                if (!(length == 4 && receiveCommand[3] == (byte) 80)) {
                    Log.v(f241a, "3-2 back error");
                    return null;
                }
            case 52:
                if (!(length == 4 && receiveCommand[3] == (byte) 80)) {
                    Log.v(f241a, "3-1 back error");
                    return null;
                }
            case 60:
                if (!(length == 4 && receiveCommand[3] == (byte) 37)) {
                    Log.v(f241a, "7-7 back error");
                    return null;
                }
            case 61:
                if (!(length == 4 && receiveCommand[3] == (byte) 37)) {
                    Log.v(f241a, "7-6 back error");
                    if (!AudioTrackManager.isR2017) {
                        return null;
                    }
                }
                break;
            case 62:
                if (!(length == 4 && receiveCommand[3] == (byte) 37)) {
                    Log.v(f241a, "7-5 back error");
                    return null;
                }
            case 63:
                if (length != 4) {
                    Log.v(f241a, "7-4 back error");
                    if (!AudioTrackManager.isR2017) {
                        return null;
                    }
                }
                break;
            case 64:
                if (!(length == 4 && receiveCommand[3] == (byte) 37)) {
                    Log.v(f241a, "7-3 back error");
                    return null;
                }
            case 65:
                if (!(length == 4 && receiveCommand[3] == (byte) 37)) {
                    Log.v(f241a, "7-2 back error");
                    return null;
                }
            case 66:
                if (!(length == 4 && receiveCommand[3] == (byte) 37)) {
                    Log.v(f241a, "7-1 back error");
                    return null;
                }
        }
        byte b = (byte) ((receiveCommand[0] >> 4) & 15);
        byte[] bArr = new byte[(receiveCommand.length - 3)];
        byte b2 = (byte) (receiveCommand[1] + receiveCommand[2]);
        for (length = 0; length < receiveCommand.length - 3; length++) {
            bArr[length] = receiveCommand[length + 3];
            b2 = (byte) (b2 + bArr[length]);
        }
        Log.v(f241a, "total = " + Bytes2HexString(new byte[]{b2}, 1));
        byte b3 = (byte) (((byte) (((byte) ((b2 & 240) >> 4)) + ((byte) (b2 & 15)))) & 15);
        Log.v(f241a, "sum = " + Bytes2HexString(new byte[]{b3}, 1));
        if (b3 != b) {
            Log.v(f241a, "Unpackage sum error id = " + id);
            if (!AudioTrackManager.isR2017) {
                return null;
            }
        }
        Log.v(f241a, "Unpackage success");
        this.f242b = receiveCommand[2] + 1;
        return bArr;
    }
}
