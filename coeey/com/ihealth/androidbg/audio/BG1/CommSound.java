package com.ihealth.androidbg.audio.BG1;

import android.os.SystemClock;
import com.ihealth.androidbg.audio.AudioTrackManager;
import com.ihealth.androidbg.audio.TransToneData;
import com.ihealth.androidbg.audio.TunnerThread;
import com.ihealth.communication.utils.Log;

public class CommSound implements BG1_Command_Interface {
    private static final String f238a = CommSound.class.getSimpleName();
    private int f239b = 0;
    private byte[] f240c;

    public CommSound(TunnerThread tunner) {
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

    private byte[] m208a(byte b, byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            return null;
        }
        byte[] bArr2 = new byte[(bArr.length + 5)];
        bArr2[0] = (byte) -80;
        bArr2[1] = (byte) (bArr.length + 2);
        bArr2[2] = b;
        bArr2[3] = (byte) this.f239b;
        int i2 = bArr2[2] + bArr2[3];
        while (i < bArr.length) {
            bArr2[i + 4] = bArr[i];
            i2 += bArr2[i + 4];
            i++;
        }
        bArr2[bArr.length + 4] = (byte) i2;
        return bArr2;
    }

    public void msgBytes(byte[] msg) {
        this.f240c = msg;
        Log.v(f238a, "Back Command <---------- " + Bytes2HexString(msg, msg.length));
    }

    public byte[] receiveCommand(int id) {
        if (this.f240c == null) {
            return null;
        }
        byte[] unPackageCommand = unPackageCommand(this.f240c, id);
        this.f240c = null;
        return unPackageCommand;
    }

    public void sendACK(byte stateID, byte commandID) {
        byte[] bArr = new byte[]{(byte) -80, (byte) 4, stateID, (byte) (this.f239b + 1), (byte) -94, commandID, (byte) (((bArr[2] + bArr[3]) + bArr[4]) + bArr[5])};
        Log.v("send ack", Bytes2HexString(bArr, bArr.length));
        int[] transDataToTone = TransToneData.transDataToTone(TransToneData.getDataByOrder(bArr));
        SystemClock.sleep(300);
        AudioTrackManager.getInstance().play(transDataToTone);
    }

    public byte[] sendCommand(byte stateID, byte[] command, int id) {
        int i = 5;
        byte[] a = m208a(stateID, command);
        byte[] bArr = null;
        int[] transDataToTone = TransToneData.transDataToTone(TransToneData.getDataByOrder(a));
        if (a.length != 0) {
            while (AudioTrackManager.inCommunication) {
                int i2 = i - 1;
                if (i == 0) {
                    break;
                }
                Log.v(f238a, "------------------------------------------------------ >");
                Log.v(f238a, "| sendCommand -----------> " + Bytes2HexString(a, a.length));
                Log.v(f238a, "------------------------------------------------------ >");
                AudioTrackManager.getInstance().play(transDataToTone);
                i = 1000;
                byte[] bArr2 = bArr;
                while (AudioTrackManager.inCommunication) {
                    int i3 = i - 1;
                    if (i <= 0) {
                        break;
                    }
                    byte[] receiveCommand = receiveCommand(id);
                    if (receiveCommand != null) {
                        bArr = receiveCommand;
                        break;
                    }
                    SystemClock.sleep(1);
                    bArr2 = receiveCommand;
                    i = i3;
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

    public byte[] sendCommand(int id) {
        int i = 3;
        byte[] bArr = null;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                break;
            }
            AudioTrackManager.getInstance().play(new int[]{16600, 16600, 16600, 16600, 16600, 16600, 16600, 16600});
            i = 1000;
            while (true) {
                int i3 = i - 1;
                if (i <= 0) {
                    break;
                }
                bArr = receiveCommand(id);
                if (bArr != null) {
                    break;
                }
                SystemClock.sleep(1);
                i = i3;
            }
            if (bArr != null) {
                break;
            }
            i = i2;
        }
        return bArr;
    }

    public byte[] unPackageCommand(byte[] receiveCommand, int id) {
        if (receiveCommand == null) {
            return null;
        }
        int length = receiveCommand.length;
        if (length < 5) {
            return null;
        }
        Log.v(f238a, "Unpackage command = " + Bytes2HexString(receiveCommand, length));
        if (receiveCommand[0] != (byte) -96) {
            Log.v(f238a, "Unpackage header error");
            return null;
        } else if (receiveCommand[1] + 3 != length) {
            Log.v(f238a, "Unpackage length error");
            return null;
        } else if (receiveCommand[4] != (byte) -94) {
            Log.v(f238a, "Unpackage product id error");
            return null;
        } else {
            byte b = (byte) ((receiveCommand[2] + receiveCommand[3]) + receiveCommand[4]);
            if (id == 0 && (length != 7 || receiveCommand[5] != (byte) 1)) {
                Log.v(f238a, "handshake no match");
                return null;
            } else if (id == 1 && (length != 22 || receiveCommand[5] != (byte) 32)) {
                Log.v(f238a, "model no match");
                return null;
            } else if (id == 2 && (length != 7 || (receiveCommand[5] != Framer.STDIN_FRAME_PREFIX && receiveCommand[5] != (byte) 46))) {
                Log.v(f238a, "APP Id no match");
                return null;
            } else if (id == 32 && (length != 22 || receiveCommand[2] != Framer.STDERR_FRAME_PREFIX || receiveCommand[5] != (byte) 63)) {
                Log.v(f238a, "idps 3-1 no match");
                return null;
            } else if (id == 31 && (length != 16 || receiveCommand[2] != Framer.STDOUT_FRAME_PREFIX || receiveCommand[5] != (byte) 63)) {
                Log.v(f238a, "idps 3-2  no match");
                return null;
            } else if (id == 30 && (length != 28 || receiveCommand[2] != (byte) 48 || receiveCommand[5] != (byte) 63)) {
                Log.v(f238a, "idps 3-3 no match");
                return null;
            } else if (id == 43 && (length != 23 || receiveCommand[2] != (byte) 34 || receiveCommand[5] != (byte) -5)) {
                Log.v(f238a, "identify 4-1 no match");
                return null;
            } else if (id == 42 && (length != 23 || receiveCommand[2] != (byte) 33 || receiveCommand[5] != (byte) -5)) {
                Log.v(f238a, "identify 4-2 no match");
                return null;
            } else if (id == 41 && (length != 23 || receiveCommand[2] != (byte) 32 || receiveCommand[5] != (byte) -5)) {
                Log.v(f238a, "identify 4-3 no match");
                return null;
            } else if (id == 40 && (length != 7 || (receiveCommand[5] != (byte) -3 && receiveCommand[5] != (byte) -2))) {
                Log.v(f238a, "identify 4-4 no match");
                return null;
            } else if (id == 51 && (length != 7 || receiveCommand[5] != (byte) 80)) {
                Log.v(f238a, "2-1 no match");
                return null;
            } else if (id == 50 && (length != 7 || receiveCommand[5] != (byte) 80)) {
                Log.v(f238a, "2-2 no match");
                return null;
            } else if (id == 65 && (length != 7 || receiveCommand[2] != (byte) -91 || receiveCommand[5] != (byte) 37)) {
                Log.v(f238a, "6-1 no match");
                return null;
            } else if (id == 64 && (length != 7 || receiveCommand[2] != (byte) -92 || receiveCommand[5] != (byte) 37)) {
                Log.v(f238a, "6-2 no match");
                return null;
            } else if (id == 63 && (length != 7 || receiveCommand[2] != (byte) -93 || receiveCommand[5] != (byte) 37)) {
                Log.v(f238a, "6-3 no match");
                return null;
            } else if (id == 62 && (length != 7 || receiveCommand[2] != (byte) -94 || receiveCommand[5] != (byte) 37)) {
                Log.v(f238a, "6-4 no match");
                return null;
            } else if (id == 61 && (length != 7 || receiveCommand[2] != (byte) -95 || receiveCommand[5] != (byte) 37)) {
                Log.v(f238a, "6-5 no match");
                return null;
            } else if (id != 60 || (length == 7 && receiveCommand[2] == (byte) -96 && receiveCommand[5] == (byte) 37)) {
                byte[] bArr = new byte[(receiveCommand.length - 5)];
                for (length = 0; length < receiveCommand.length - 6; length++) {
                    bArr[length] = receiveCommand[length + 5];
                    b = (byte) (b + bArr[length]);
                }
                Log.v(f238a, "sum = " + Bytes2HexString(new byte[]{b}, 1));
                if (b != receiveCommand[receiveCommand.length - 1]) {
                    Log.v(f238a, "Unpackage sum error");
                    return null;
                }
                Log.v(f238a, "Unpackage success");
                return bArr;
            } else {
                Log.v(f238a, "6-6 no match");
                return null;
            }
        }
    }
}
