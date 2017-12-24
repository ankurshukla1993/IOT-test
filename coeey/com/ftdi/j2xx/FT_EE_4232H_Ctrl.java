package com.ftdi.j2xx;

import android.support.v4.view.MotionEventCompat;
import com.ftdi.j2xx.D2xxManager.D2xxException;

class FT_EE_4232H_Ctrl extends FT_EE_Ctrl {
    private static final int AH_DRIVE_CURRENT = 48;
    private static final int AH_FAST_SLEW = 64;
    private static final int AH_SCHMITT_INPUT = 128;
    private static final int AH_TXDEN = 8192;
    private static final int AL_DRIVE_CURRENT = 3;
    private static final int AL_FAST_SLEW = 4;
    private static final int AL_SCHMITT_INPUT = 8;
    private static final int AL_TXDEN = 4096;
    private static final int BH_DRIVE_CURRENT = 12288;
    private static final int BH_FAST_SLEW = 16384;
    private static final int BH_SCHMITT_INPUT = 32768;
    private static final int BH_TXDEN = 32768;
    private static final int BL_DRIVE_CURRENT = 768;
    private static final int BL_FAST_SLEW = 1024;
    private static final int BL_SCHMITT_INPUT = 2048;
    private static final int BL_TXDEN = 16384;
    private static final String DEFAULT_PID = "6011";
    private static final byte EEPROM_SIZE_LOCATION = (byte) 12;
    private static final int TPRDRV = 24;

    FT_EE_4232H_Ctrl(FT_Device usbC) throws D2xxException {
        super(usbC);
        getEepromSize((byte) 12);
    }

    short programEeprom(FT_EEPROM ee) {
        int[] dataToWrite = new int[this.mEepromSize];
        if (ee.getClass() != FT_EEPROM_4232H.class) {
            return (short) 1;
        }
        FT_EEPROM_4232H eeprom = (FT_EEPROM_4232H) ee;
        try {
            dataToWrite[0] = 0;
            if (eeprom.AL_LoadVCP) {
                dataToWrite[0] = dataToWrite[0] | 8;
            }
            if (eeprom.BL_LoadVCP) {
                dataToWrite[0] = dataToWrite[0] | 128;
            }
            if (eeprom.AH_LoadVCP) {
                dataToWrite[0] = dataToWrite[0] | 2048;
            }
            if (eeprom.BH_LoadVCP) {
                dataToWrite[0] = dataToWrite[0] | 32768;
            }
            dataToWrite[1] = eeprom.VendorId;
            dataToWrite[2] = eeprom.ProductId;
            dataToWrite[3] = 2048;
            dataToWrite[4] = setUSBConfig(ee);
            dataToWrite[5] = setDeviceControl(ee);
            if (eeprom.AL_LoadRI_RS485) {
                dataToWrite[5] = (short) (dataToWrite[5] | 4096);
            }
            if (eeprom.AH_LoadRI_RS485) {
                dataToWrite[5] = (short) (dataToWrite[5] | 8192);
            }
            if (eeprom.BL_LoadRI_RS485) {
                dataToWrite[5] = (short) (dataToWrite[5] | 16384);
            }
            if (eeprom.BH_LoadRI_RS485) {
                dataToWrite[5] = (short) (dataToWrite[5] | 32768);
            }
            dataToWrite[6] = 0;
            short driveA = eeprom.AL_DriveCurrent;
            if (driveA == (short) -1) {
                driveA = (short) 0;
            }
            dataToWrite[6] = dataToWrite[6] | driveA;
            if (eeprom.AL_SlowSlew) {
                dataToWrite[6] = dataToWrite[6] | 4;
            }
            if (eeprom.AL_SchmittInput) {
                dataToWrite[6] = dataToWrite[6] | 8;
            }
            short driveB = eeprom.AH_DriveCurrent;
            if (driveB == (short) -1) {
                driveB = (short) 0;
            }
            dataToWrite[6] = dataToWrite[6] | ((short) (driveB << 4));
            if (eeprom.AH_SlowSlew) {
                dataToWrite[6] = dataToWrite[6] | 64;
            }
            if (eeprom.AH_SchmittInput) {
                dataToWrite[6] = dataToWrite[6] | 128;
            }
            short driveC = eeprom.BL_DriveCurrent;
            if (driveC == (short) -1) {
                driveC = (short) 0;
            }
            dataToWrite[6] = dataToWrite[6] | ((short) (driveC << 8));
            if (eeprom.BL_SlowSlew) {
                dataToWrite[6] = dataToWrite[6] | 1024;
            }
            if (eeprom.BL_SchmittInput) {
                dataToWrite[6] = dataToWrite[6] | 2048;
            }
            dataToWrite[6] = dataToWrite[6] | ((short) (eeprom.BH_DriveCurrent << 12));
            if (eeprom.BH_SlowSlew) {
                dataToWrite[6] = dataToWrite[6] | 16384;
            }
            if (eeprom.BH_SchmittInput) {
                dataToWrite[6] = dataToWrite[6] | 32768;
            }
            boolean eeprom46 = false;
            int offset = 77;
            if (this.mEepromType == (short) 70) {
                offset = 13;
                eeprom46 = true;
            }
            offset = setStringDescriptor(eeprom.Product, dataToWrite, setStringDescriptor(eeprom.Manufacturer, dataToWrite, offset, 7, eeprom46), 8, eeprom46);
            if (eeprom.SerNumEnable) {
                offset = setStringDescriptor(eeprom.SerialNumber, dataToWrite, offset, 9, eeprom46);
            }
            switch (eeprom.TPRDRV) {
                case 0:
                    dataToWrite[11] = 0;
                    break;
                case 1:
                    dataToWrite[11] = 8;
                    break;
                case 2:
                    dataToWrite[11] = 16;
                    break;
                case 3:
                    dataToWrite[11] = 24;
                    break;
                default:
                    dataToWrite[11] = 0;
                    break;
            }
            dataToWrite[12] = this.mEepromType;
            if (dataToWrite[1] == 0 || dataToWrite[2] == 0) {
                return (short) 2;
            }
            if (programEeprom(dataToWrite, this.mEepromSize)) {
                return (short) 0;
            }
            return (short) 1;
        } catch (Exception e) {
            e.printStackTrace();
            return (short) 0;
        }
    }

    FT_EEPROM readEeprom() {
        FT_EEPROM eeprom = new FT_EEPROM_4232H();
        int[] data = new int[this.mEepromSize];
        if (this.mEepromBlank) {
            return eeprom;
        }
        short i = (short) 0;
        while (true) {
            try {
                if (i >= this.mEepromSize) {
                    break;
                }
                data[i] = readWord(i);
                i = (short) (i + 1);
            } catch (Exception e) {
                return null;
            }
        }
        if (((short) ((data[0] & 8) >> 3)) == (short) 1) {
            eeprom.AL_LoadVCP = true;
            eeprom.AL_LoadD2XX = false;
        } else {
            eeprom.AL_LoadVCP = false;
            eeprom.AL_LoadD2XX = true;
        }
        if (((short) ((data[0] & 128) >> 7)) == (short) 1) {
            eeprom.BL_LoadVCP = true;
            eeprom.BL_LoadD2XX = false;
        } else {
            eeprom.BL_LoadVCP = false;
            eeprom.BL_LoadD2XX = true;
        }
        if (((short) ((data[0] & 2048) >> 11)) == (short) 1) {
            eeprom.AH_LoadVCP = true;
            eeprom.AH_LoadD2XX = false;
        } else {
            eeprom.AH_LoadVCP = false;
            eeprom.AH_LoadD2XX = true;
        }
        if (((short) ((data[0] & 32768) >> 15)) == (short) 1) {
            eeprom.BH_LoadVCP = true;
            eeprom.BH_LoadD2XX = false;
        } else {
            eeprom.BH_LoadVCP = false;
            eeprom.BH_LoadD2XX = true;
        }
        eeprom.VendorId = (short) data[1];
        eeprom.ProductId = (short) data[2];
        getUSBConfig(eeprom, data[4]);
        getDeviceControl(eeprom, data[5]);
        if ((data[5] & 4096) == 4096) {
            eeprom.AL_LoadRI_RS485 = true;
        }
        if ((data[5] & 8192) == 8192) {
            eeprom.AH_LoadRI_RS485 = true;
        }
        if ((data[5] & 16384) == 16384) {
            eeprom.AH_LoadRI_RS485 = true;
        }
        if ((data[5] & 32768) == 32768) {
            eeprom.AH_LoadRI_RS485 = true;
        }
        switch ((short) (data[6] & 3)) {
            case (short) 0:
                eeprom.AL_DriveCurrent = (byte) 0;
                break;
            case (short) 1:
                eeprom.AL_DriveCurrent = (byte) 1;
                break;
            case (short) 2:
                eeprom.AL_DriveCurrent = (byte) 2;
                break;
            case (short) 3:
                eeprom.AL_DriveCurrent = (byte) 3;
                break;
        }
        if (((short) (data[6] & 4)) == (short) 4) {
            eeprom.AL_SlowSlew = true;
        } else {
            eeprom.AL_SlowSlew = false;
        }
        if (((short) (data[6] & 8)) == (short) 8) {
            eeprom.AL_SchmittInput = true;
        } else {
            eeprom.AL_SchmittInput = false;
        }
        switch ((short) ((data[6] & 48) >> 4)) {
            case (short) 0:
                eeprom.AH_DriveCurrent = (byte) 0;
                break;
            case (short) 1:
                eeprom.AH_DriveCurrent = (byte) 1;
                break;
            case (short) 2:
                eeprom.AH_DriveCurrent = (byte) 2;
                break;
            case (short) 3:
                eeprom.AH_DriveCurrent = (byte) 3;
                break;
        }
        if (((short) (data[6] & 64)) == (short) 64) {
            eeprom.AH_SlowSlew = true;
        } else {
            eeprom.AH_SlowSlew = false;
        }
        if (((short) (data[6] & 128)) == (short) 128) {
            eeprom.AH_SchmittInput = true;
        } else {
            eeprom.AH_SchmittInput = false;
        }
        switch ((short) ((data[6] & BL_DRIVE_CURRENT) >> 8)) {
            case (short) 0:
                eeprom.BL_DriveCurrent = (byte) 0;
                break;
            case (short) 1:
                eeprom.BL_DriveCurrent = (byte) 1;
                break;
            case (short) 2:
                eeprom.BL_DriveCurrent = (byte) 2;
                break;
            case (short) 3:
                eeprom.BL_DriveCurrent = (byte) 3;
                break;
        }
        if (((short) (data[6] & 1024)) == (short) 1024) {
            eeprom.BL_SlowSlew = true;
        } else {
            eeprom.BL_SlowSlew = false;
        }
        if (((short) (data[6] & 2048)) == (short) 2048) {
            eeprom.BL_SchmittInput = true;
        } else {
            eeprom.BL_SchmittInput = false;
        }
        switch ((short) ((data[6] & BH_DRIVE_CURRENT) >> 12)) {
            case (short) 0:
                eeprom.BH_DriveCurrent = (byte) 0;
                break;
            case (short) 1:
                eeprom.BH_DriveCurrent = (byte) 1;
                break;
            case (short) 2:
                eeprom.BH_DriveCurrent = (byte) 2;
                break;
            case (short) 3:
                eeprom.BH_DriveCurrent = (byte) 3;
                break;
        }
        if (((short) (data[6] & 16384)) == (short) 16384) {
            eeprom.BH_SlowSlew = true;
        } else {
            eeprom.BH_SlowSlew = false;
        }
        if (((short) (data[6] & 32768)) == Short.MIN_VALUE) {
            eeprom.BH_SchmittInput = true;
        } else {
            eeprom.BH_SchmittInput = false;
        }
        short datax0B = (short) ((data[11] & 24) >> 3);
        if (datax0B < (short) 4) {
            eeprom.TPRDRV = datax0B;
        } else {
            eeprom.TPRDRV = 0;
        }
        int addr = data[7] & 255;
        if (this.mEepromType == (short) 70) {
            eeprom.Manufacturer = getStringDescriptor((addr - 128) / 2, data);
            eeprom.Product = getStringDescriptor(((data[8] & 255) - 128) / 2, data);
            eeprom.SerialNumber = getStringDescriptor(((data[9] & 255) - 128) / 2, data);
            return eeprom;
        }
        eeprom.Manufacturer = getStringDescriptor(addr / 2, data);
        eeprom.Product = getStringDescriptor((data[8] & 255) / 2, data);
        eeprom.SerialNumber = getStringDescriptor((data[9] & 255) / 2, data);
        return eeprom;
    }

    int getUserSize() {
        int data = readWord((short) 9);
        return ((this.mEepromSize - 1) - ((((data & 255) / 2) + (((MotionEventCompat.ACTION_POINTER_INDEX_MASK & data) >> 8) / 2)) + 1)) * 2;
    }

    int writeUserData(byte[] data) {
        if (data.length > getUserSize()) {
            return 0;
        }
        short offset = (short) ((this.mEepromSize - (getUserSize() / 2)) - 1);
        int i = 0;
        while (i < data.length) {
            int dataWrite;
            if (i + 1 < data.length) {
                dataWrite = data[i + 1] & 255;
            } else {
                dataWrite = 0;
            }
            short offset2 = (short) (offset + 1);
            writeWord(offset, (short) ((dataWrite << 8) | (data[i] & 255)));
            i += 2;
            offset = offset2;
        }
        return data.length;
    }

    byte[] readUserData(int length) {
        byte[] data = new byte[length];
        if (length == 0 || length > getUserSize()) {
            return null;
        }
        int i = 0;
        short offset = (short) ((this.mEepromSize - (getUserSize() / 2)) - 1);
        while (i < length) {
            short offset2 = (short) (offset + 1);
            int dataRead = readWord(offset);
            if (i + 1 < data.length) {
                data[i + 1] = (byte) (dataRead & 255);
            }
            data[i] = (byte) ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & dataRead) >> 8);
            i += 2;
            offset = offset2;
        }
        return data;
    }
}
