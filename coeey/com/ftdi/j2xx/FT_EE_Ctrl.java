package com.ftdi.j2xx;

import android.util.Log;
import com.facebook.imageutils.JfifUtil;
import com.ftdi.j2xx.D2xxManager.D2xxException;

class FT_EE_Ctrl {
    private static final int BUS_POWERED = 128;
    private static final short EE_MAX_SIZE = (short) 1024;
    private static final int ENABLE_SERIAL_NUMBER = 8;
    private static final int PULL_DOWN_IN_USB_SUSPEND = 4;
    private static final int SELF_POWERED = 64;
    private static final int USB_REMOTE_WAKEUP = 32;
    private FT_Device mDevice;
    boolean mEepromBlank;
    int mEepromSize;
    short mEepromType;

    static final class EepromType {
        static final short INVALID = (short) 255;
        static final short TYPE_46 = (short) 70;
        static final short TYPE_52 = (short) 82;
        static final short TYPE_56 = (short) 86;
        static final short TYPE_66 = (short) 102;
        static final short TYPE_MTP = (short) 1;

        EepromType() {
        }
    }

    FT_EE_Ctrl(FT_Device dev) {
        this.mDevice = dev;
    }

    int readWord(short offset) {
        byte[] dataRead = new byte[2];
        Log.d("FT_EE_Ctrl", "Entered ReadWord.");
        if (offset >= (short) 1024) {
            return -1;
        }
        this.mDevice.getConnection().controlTransfer(-64, 144, 0, offset, dataRead, 2, 0);
        return ((dataRead[1] & 255) << 8) | (dataRead[0] & 255);
    }

    boolean writeWord(short offset, short value) {
        int wValue = value & 65535;
        int wIndex = offset & 65535;
        boolean rc = false;
        Log.d("FT_EE_Ctrl", "Entered WriteWord.");
        if (offset >= (short) 1024) {
            return 0;
        }
        if (this.mDevice.getConnection().controlTransfer(64, 145, wValue, wIndex, null, 0, 0) == 0) {
            rc = true;
        }
        return rc;
    }

    int eraseEeprom() {
        return this.mDevice.getConnection().controlTransfer(64, 146, 0, 0, null, 0, 0);
    }

    short programEeprom(FT_EEPROM eeprom) {
        return (short) 1;
    }

    boolean programEeprom(int[] dataToWrite, int ee_size) {
        int checksumLocation = ee_size - 1;
        int Checksum = 43690;
        for (int addressCounter = 0; addressCounter < checksumLocation; addressCounter++) {
            writeWord((short) addressCounter, (short) dataToWrite[addressCounter]);
            int TempChecksum = dataToWrite[addressCounter] ^ Checksum;
            Checksum = (TempChecksum << 1) | (TempChecksum >> 15);
        }
        writeWord((short) checksumLocation, (short) Checksum);
        return true;
    }

    FT_EEPROM readEeprom() {
        return null;
    }

    int setUSBConfig(Object ee) {
        FT_EEPROM ft = (FT_EEPROM) ee;
        int lowerbits = 0 | 128;
        if (ft.RemoteWakeup) {
            lowerbits |= 32;
        }
        if (ft.SelfPowered) {
            lowerbits |= 64;
        }
        return ((ft.MaxPower / 2) << 8) | lowerbits;
    }

    void getUSBConfig(FT_EEPROM ee, int dataRead) {
        ee.MaxPower = (short) (((byte) (dataRead >> 8)) * 2);
        byte P = (byte) dataRead;
        if ((P & 64) == 64 && (P & 128) == 128) {
            ee.SelfPowered = true;
        } else {
            ee.SelfPowered = false;
        }
        if ((P & 32) == 32) {
            ee.RemoteWakeup = true;
        } else {
            ee.RemoteWakeup = false;
        }
    }

    int setDeviceControl(Object ee) {
        int data;
        FT_EEPROM ft = (FT_EEPROM) ee;
        if (ft.PullDownEnable) {
            data = 0 | 4;
        } else {
            data = 0 & 251;
        }
        if (ft.SerNumEnable) {
            return data | 8;
        }
        return data & 247;
    }

    void getDeviceControl(Object ee, int dataRead) {
        FT_EEPROM ft = (FT_EEPROM) ee;
        if ((dataRead & 4) > 0) {
            ft.PullDownEnable = true;
        } else {
            ft.PullDownEnable = false;
        }
        if ((dataRead & 8) > 0) {
            ft.SerNumEnable = true;
        } else {
            ft.SerNumEnable = false;
        }
    }

    int setStringDescriptor(String s, int[] data, int addrs, int pointer, boolean rdevice) {
        int i = 0;
        int strLength = (s.length() * 2) + 2;
        data[pointer] = (strLength << 8) | (addrs * 2);
        if (rdevice) {
            data[pointer] = data[pointer] + 128;
        }
        char[] strchar = s.toCharArray();
        int addrs2 = addrs + 1;
        data[addrs] = strLength | 768;
        strLength = (strLength - 2) / 2;
        addrs = addrs2;
        while (true) {
            addrs2 = addrs + 1;
            data[addrs] = strchar[i];
            i++;
            if (i >= strLength) {
                return addrs2;
            }
            addrs = addrs2;
        }
    }

    String getStringDescriptor(int addr, int[] dataRead) {
        String descriptor = "";
        addr++;
        int endaddr = addr + (((dataRead[addr] & 255) / 2) - 1);
        for (int i = addr; i < endaddr; i++) {
            descriptor = new StringBuilder(String.valueOf(descriptor)).append((char) dataRead[i]).toString();
        }
        return descriptor;
    }

    void clearUserDataArea(int saddr, int eeprom_size, int[] data) {
        int i = saddr;
        while (i < eeprom_size) {
            saddr = i + 1;
            data[i] = 0;
            i = saddr;
        }
    }

    int getEepromSize(byte location) throws D2xxException {
        int[] dataRead = new int[3];
        int eeData = (short) readWord((short) (location & -1));
        if (eeData != 65535) {
            switch (eeData) {
                case 70:
                    this.mEepromType = (short) 70;
                    this.mEepromSize = 64;
                    this.mEepromBlank = false;
                    return 64;
                case 82:
                    this.mEepromType = (short) 82;
                    this.mEepromSize = 1024;
                    this.mEepromBlank = false;
                    return 1024;
                case 86:
                    this.mEepromType = (short) 86;
                    this.mEepromSize = 128;
                    this.mEepromBlank = false;
                    return 128;
                case 102:
                    this.mEepromType = (short) 102;
                    this.mEepromSize = 128;
                    this.mEepromBlank = false;
                    return 256;
                default:
                    return 0;
            }
        }
        boolean rc = writeWord((short) 192, (short) JfifUtil.MARKER_SOFn);
        dataRead[0] = readWord((short) 192);
        dataRead[1] = readWord((short) 64);
        dataRead[2] = readWord((short) 0);
        if (rc) {
            this.mEepromBlank = true;
            if ((readWord((short) 0) & 255) == JfifUtil.MARKER_SOFn) {
                eraseEeprom();
                this.mEepromType = (short) 70;
                this.mEepromSize = 64;
                return 64;
            } else if ((readWord((short) 64) & 255) == JfifUtil.MARKER_SOFn) {
                eraseEeprom();
                this.mEepromType = (short) 86;
                this.mEepromSize = 128;
                return 128;
            } else if ((readWord((short) 192) & 255) == JfifUtil.MARKER_SOFn) {
                eraseEeprom();
                this.mEepromType = (short) 102;
                this.mEepromSize = 128;
                return 256;
            } else {
                eraseEeprom();
                return 0;
            }
        }
        this.mEepromType = (short) 255;
        this.mEepromSize = 0;
        return 0;
    }

    int writeUserData(byte[] data) {
        return 0;
    }

    byte[] readUserData(int length) {
        return null;
    }

    int getUserSize() {
        return 0;
    }
}
