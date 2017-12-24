package com.ftdi.j2xx;

import android.support.v4.view.MotionEventCompat;
import com.ftdi.j2xx.D2xxManager.D2xxException;

class FT_EE_232H_Ctrl extends FT_EE_Ctrl {
    private static final int AL_DRIVE_CURRENT = 3;
    private static final int AL_FAST_SLEW = 4;
    private static final int AL_SCHMITT_INPUT = 8;
    private static final int BL_DRIVE_CURRENT = 768;
    private static final int BL_FAST_SLEW = 1024;
    private static final int BL_SCHMITT_INPUT = 2048;
    private static final String DEFAULT_PID = "6014";
    private static final byte EEPROM_SIZE_LOCATION = (byte) 15;
    private static FT_Device ft_device;

    FT_EE_232H_Ctrl(FT_Device usbc) throws D2xxException {
        super(usbc);
        getEepromSize((byte) 15);
    }

    short programEeprom(FT_EEPROM ee) {
        int[] dataToWrite = new int[this.mEepromSize];
        if (ee.getClass() != FT_EEPROM_232H.class) {
            return (short) 1;
        }
        FT_EEPROM_232H eeprom = (FT_EEPROM_232H) ee;
        try {
            if (eeprom.FIFO) {
                dataToWrite[0] = dataToWrite[0] | 1;
            } else if (eeprom.FIFOTarget) {
                dataToWrite[0] = dataToWrite[0] | 2;
            } else if (eeprom.FastSerial) {
                dataToWrite[0] = dataToWrite[0] | 4;
            }
            if (eeprom.FT1248) {
                dataToWrite[0] = dataToWrite[0] | 8;
            }
            if (eeprom.LoadVCP) {
                dataToWrite[0] = dataToWrite[0] | 16;
            }
            if (eeprom.FT1248ClockPolarity) {
                dataToWrite[0] = dataToWrite[0] | 256;
            }
            if (eeprom.FT1248LSB) {
                dataToWrite[0] = dataToWrite[0] | 512;
            }
            if (eeprom.FT1248FlowControl) {
                dataToWrite[0] = dataToWrite[0] | 1024;
            }
            if (eeprom.PowerSaveEnable) {
                dataToWrite[0] = dataToWrite[0] | 32768;
            }
            dataToWrite[1] = eeprom.VendorId;
            dataToWrite[2] = eeprom.ProductId;
            dataToWrite[3] = 2304;
            dataToWrite[4] = setUSBConfig(ee);
            dataToWrite[5] = setDeviceControl(ee);
            short driveA = eeprom.AL_DriveCurrent;
            if (driveA == (byte) -1) {
                driveA = (short) 0;
            }
            dataToWrite[6] = dataToWrite[6] | driveA;
            if (eeprom.AL_SlowSlew) {
                dataToWrite[6] = dataToWrite[6] | 4;
            }
            if (eeprom.AL_SchmittInput) {
                dataToWrite[6] = dataToWrite[6] | 8;
            }
            short driveC = eeprom.BL_DriveCurrent;
            if (driveC == (byte) -1) {
                driveC = (short) 0;
            }
            dataToWrite[6] = dataToWrite[6] | ((short) (driveC << 8));
            if (eeprom.BL_SlowSlew) {
                dataToWrite[6] = dataToWrite[6] | 1024;
            }
            if (eeprom.BL_SchmittInput) {
                dataToWrite[6] = dataToWrite[6] | 2048;
            }
            int offset = setStringDescriptor(eeprom.Product, dataToWrite, setStringDescriptor(eeprom.Manufacturer, dataToWrite, 80, 7, false), 8, false);
            if (eeprom.SerNumEnable) {
                offset = setStringDescriptor(eeprom.SerialNumber, dataToWrite, offset, 9, false);
            }
            dataToWrite[10] = 0;
            dataToWrite[11] = 0;
            dataToWrite[12] = 0;
            int c2 = eeprom.CBus2 << 8;
            dataToWrite[12] = ((eeprom.CBus0 | (eeprom.CBus1 << 4)) | c2) | (eeprom.CBus3 << 12);
            dataToWrite[13] = 0;
            int c6 = eeprom.CBus6 << 8;
            dataToWrite[13] = ((eeprom.CBus4 | (eeprom.CBus5 << 4)) | c6) | (eeprom.CBus7 << 12);
            dataToWrite[14] = 0;
            dataToWrite[14] = eeprom.CBus8 | (eeprom.CBus9 << 4);
            dataToWrite[15] = this.mEepromType;
            dataToWrite[69] = 72;
            if (this.mEepromType == (short) 70) {
                return (short) 1;
            }
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
        FT_EEPROM eeprom = new FT_EEPROM_232H();
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
        eeprom.UART = false;
        switch (data[0] & 15) {
            case 0:
                eeprom.UART = true;
                break;
            case 1:
                eeprom.FIFO = true;
                break;
            case 2:
                eeprom.FIFOTarget = true;
                break;
            case 4:
                eeprom.FastSerial = true;
                break;
            case 8:
                eeprom.FT1248 = true;
                break;
            default:
                eeprom.UART = true;
                break;
        }
        if ((data[0] & 16) > 0) {
            eeprom.LoadVCP = true;
            eeprom.LoadD2XX = false;
        } else {
            eeprom.LoadVCP = false;
            eeprom.LoadD2XX = true;
        }
        if ((data[0] & 256) > 0) {
            eeprom.FT1248ClockPolarity = true;
        } else {
            eeprom.FT1248ClockPolarity = false;
        }
        if ((data[0] & 512) > 0) {
            eeprom.FT1248LSB = true;
        } else {
            eeprom.FT1248LSB = false;
        }
        if ((data[0] & 1024) > 0) {
            eeprom.FT1248FlowControl = true;
        } else {
            eeprom.FT1248FlowControl = false;
        }
        if ((data[0] & 32768) > 0) {
            eeprom.PowerSaveEnable = true;
        }
        eeprom.VendorId = (short) data[1];
        eeprom.ProductId = (short) data[2];
        getUSBConfig(eeprom, data[4]);
        getDeviceControl(eeprom, data[5]);
        switch (data[6] & 3) {
            case 0:
                eeprom.AL_DriveCurrent = (byte) 0;
                break;
            case 1:
                eeprom.AL_DriveCurrent = (byte) 1;
                break;
            case 2:
                eeprom.AL_DriveCurrent = (byte) 2;
                break;
            case 3:
                eeprom.AL_DriveCurrent = (byte) 3;
                break;
        }
        if ((data[6] & 4) > 0) {
            eeprom.AL_SlowSlew = true;
        } else {
            eeprom.AL_SlowSlew = false;
        }
        if ((data[6] & 8) > 0) {
            eeprom.AL_SchmittInput = true;
        } else {
            eeprom.AL_SchmittInput = false;
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
        if ((data[6] & 1024) > 0) {
            eeprom.BL_SlowSlew = true;
        } else {
            eeprom.BL_SlowSlew = false;
        }
        if ((data[6] & 2048) > 0) {
            eeprom.BL_SchmittInput = true;
        } else {
            eeprom.BL_SchmittInput = false;
        }
        eeprom.CBus0 = (byte) ((short) ((data[12] >> 0) & 15));
        eeprom.CBus1 = (byte) ((short) ((data[12] >> 4) & 15));
        eeprom.CBus2 = (byte) ((short) ((data[12] >> 8) & 15));
        eeprom.CBus3 = (byte) ((short) ((data[12] >> 12) & 15));
        eeprom.CBus4 = (byte) ((short) ((data[13] >> 0) & 15));
        eeprom.CBus5 = (byte) ((short) ((data[13] >> 4) & 15));
        eeprom.CBus6 = (byte) ((short) ((data[13] >> 8) & 15));
        eeprom.CBus7 = (byte) ((short) ((data[13] >> 12) & 15));
        eeprom.CBus8 = (byte) ((short) ((data[14] >> 0) & 15));
        eeprom.CBus9 = (byte) ((short) ((data[14] >> 4) & 15));
        eeprom.Manufacturer = getStringDescriptor((data[7] & 255) / 2, data);
        eeprom.Product = getStringDescriptor((data[8] & 255) / 2, data);
        eeprom.SerialNumber = getStringDescriptor((data[9] & 255) / 2, data);
        return eeprom;
    }

    int getUserSize() {
        int data = readWord((short) 9);
        return ((this.mEepromSize - (((data & 255) / 2) + 1)) - ((((MotionEventCompat.ACTION_POINTER_INDEX_MASK & data) >> 8) / 2) + 1)) * 2;
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
