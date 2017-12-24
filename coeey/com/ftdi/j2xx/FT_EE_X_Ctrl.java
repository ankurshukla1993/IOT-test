package com.ftdi.j2xx;

import android.support.v4.view.MotionEventCompat;

class FT_EE_X_Ctrl extends FT_EE_Ctrl {
    private static final int BCD_ENABLE = 1;
    private static final int CBUS_DRIVE = 48;
    private static final int CBUS_SCHMITT = 128;
    private static final int CBUS_SLEW = 64;
    private static final int DBUS_DRIVE = 3;
    private static final int DBUS_SCHMITT = 8;
    private static final int DBUS_SLEW = 4;
    private static final int DEACTIVATE_SLEEP = 4;
    private static final String DEFAULT_PID = "6015";
    private static final int DEVICE_TYPE_EE_LOC = 73;
    private static final byte FIFO = (byte) 1;
    private static final int FORCE_POWER_ENABLE = 2;
    private static final byte FT1248 = (byte) 2;
    private static final int FT1248_BIT_ORDER = 32;
    private static final int FT1248_CLK_POLARITY = 16;
    private static final int FT1248_FLOW_CTRL = 64;
    private static final byte I2C = (byte) 3;
    private static final int I2C_DISABLE_SCHMITT = 128;
    private static final int INVERT_CTS = 2048;
    private static final int INVERT_DCD = 16384;
    private static final int INVERT_DSR = 8192;
    private static final int INVERT_DTR = 4096;
    private static final int INVERT_RI = 32768;
    private static final int INVERT_RTS = 1024;
    private static final int INVERT_RXD = 512;
    private static final int INVERT_TXD = 256;
    private static final int LOAD_DRIVER = 128;
    private static final int RS485_ECHO = 8;
    private static final byte UART = (byte) 0;
    private static final int VBUS_SUSPEND = 64;

    FT_EE_X_Ctrl(FT_Device usbC) {
        super(usbC);
        this.mEepromSize = 128;
        this.mEepromType = (short) 1;
    }

    short programEeprom(FT_EEPROM ee) {
        int[] dataToWrite = new int[this.mEepromSize];
        short counter = (short) 0;
        if (ee.getClass() != FT_EEPROM_X_Series.class) {
            return (short) 1;
        }
        FT_EEPROM_X_Series eeprom = (FT_EEPROM_X_Series) ee;
        do {
            readWord(counter);
            counter = (short) (counter + 1);
        } while (counter < this.mEepromSize);
        try {
            dataToWrite[0] = 0;
            if (eeprom.BCDEnable) {
                dataToWrite[0] = dataToWrite[0] | 1;
            }
            if (eeprom.BCDForceCBusPWREN) {
                dataToWrite[0] = dataToWrite[0] | 2;
            }
            if (eeprom.BCDDisableSleep) {
                dataToWrite[0] = dataToWrite[0] | 4;
            }
            if (eeprom.RS485EchoSuppress) {
                dataToWrite[0] = dataToWrite[0] | 8;
            }
            if (eeprom.A_LoadVCP) {
                dataToWrite[0] = dataToWrite[0] | 128;
            }
            if (eeprom.PowerSaveEnable) {
                boolean found = false;
                if (eeprom.CBus0 == (byte) 17) {
                    found = true;
                }
                if (eeprom.CBus1 == (byte) 17) {
                    found = true;
                }
                if (eeprom.CBus2 == (byte) 17) {
                    found = true;
                }
                if (eeprom.CBus3 == (byte) 17) {
                    found = true;
                }
                if (eeprom.CBus4 == (byte) 17) {
                    found = true;
                }
                if (eeprom.CBus5 == (byte) 17) {
                    found = true;
                }
                if (eeprom.CBus6 == (byte) 17) {
                    found = true;
                }
                if (!found) {
                    return (short) 1;
                }
                dataToWrite[0] = dataToWrite[0] | 64;
            }
            dataToWrite[1] = eeprom.VendorId;
            dataToWrite[2] = eeprom.ProductId;
            dataToWrite[3] = 4096;
            dataToWrite[4] = setUSBConfig(ee);
            dataToWrite[5] = setDeviceControl(ee);
            if (eeprom.FT1248ClockPolarity) {
                dataToWrite[5] = dataToWrite[5] | 16;
            }
            if (eeprom.FT1248LSB) {
                dataToWrite[5] = dataToWrite[5] | 32;
            }
            if (eeprom.FT1248FlowControl) {
                dataToWrite[5] = dataToWrite[5] | 64;
            }
            if (eeprom.I2CDisableSchmitt) {
                dataToWrite[5] = dataToWrite[5] | 128;
            }
            if (eeprom.InvertTXD) {
                dataToWrite[5] = dataToWrite[5] | 256;
            }
            if (eeprom.InvertRXD) {
                dataToWrite[5] = dataToWrite[5] | 512;
            }
            if (eeprom.InvertRTS) {
                dataToWrite[5] = dataToWrite[5] | 1024;
            }
            if (eeprom.InvertCTS) {
                dataToWrite[5] = dataToWrite[5] | 2048;
            }
            if (eeprom.InvertDTR) {
                dataToWrite[5] = dataToWrite[5] | 4096;
            }
            if (eeprom.InvertDSR) {
                dataToWrite[5] = dataToWrite[5] | 8192;
            }
            if (eeprom.InvertDCD) {
                dataToWrite[5] = dataToWrite[5] | 16384;
            }
            if (eeprom.InvertRI) {
                dataToWrite[5] = dataToWrite[5] | 32768;
            }
            dataToWrite[6] = 0;
            short driveA = eeprom.AD_DriveCurrent;
            if (driveA == (short) -1) {
                driveA = (short) 0;
            }
            dataToWrite[6] = dataToWrite[6] | driveA;
            if (eeprom.AD_SlowSlew) {
                dataToWrite[6] = dataToWrite[6] | 4;
            }
            if (eeprom.AD_SchmittInput) {
                dataToWrite[6] = dataToWrite[6] | 8;
            }
            short driveC = eeprom.AC_DriveCurrent;
            if (driveC == (byte) -1) {
                driveC = (short) 0;
            }
            dataToWrite[6] = dataToWrite[6] | ((short) (driveC << 4));
            if (eeprom.AC_SlowSlew) {
                dataToWrite[6] = dataToWrite[6] | 64;
            }
            if (eeprom.AC_SchmittInput) {
                dataToWrite[6] = dataToWrite[6] | 128;
            }
            int offset = setStringDescriptor(eeprom.Product, dataToWrite, setStringDescriptor(eeprom.Manufacturer, dataToWrite, 80, 7, false), 8, false);
            if (eeprom.SerNumEnable) {
                offset = setStringDescriptor(eeprom.SerialNumber, dataToWrite, offset, 9, false);
            }
            dataToWrite[10] = eeprom.I2CSlaveAddress;
            dataToWrite[11] = eeprom.I2CDeviceID & 65535;
            dataToWrite[12] = eeprom.I2CDeviceID >> 16;
            int c0 = eeprom.CBus0;
            if (c0 == -1) {
                c0 = 0;
            }
            int c1 = eeprom.CBus1;
            if (c1 == -1) {
                c1 = 0;
            }
            dataToWrite[13] = (short) (c0 | (c1 << 8));
            int c2 = eeprom.CBus2;
            if (c2 == -1) {
                c2 = 0;
            }
            int c3 = eeprom.CBus3;
            if (c3 == -1) {
                c3 = 0;
            }
            dataToWrite[14] = (short) (c2 | (c3 << 8));
            int c4 = eeprom.CBus4;
            if (c4 == -1) {
                c4 = 0;
            }
            int c5 = eeprom.CBus5;
            if (c5 == -1) {
                c5 = 0;
            }
            dataToWrite[15] = (short) (c4 | (c5 << 8));
            int c6 = eeprom.CBus6;
            if (c6 == -1) {
                c6 = 0;
            }
            dataToWrite[16] = (short) c6;
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
        FT_EEPROM_X_Series eeprom = new FT_EEPROM_X_Series();
        int[] dataRead = new int[this.mEepromSize];
        short i = (short) 0;
        while (i < this.mEepromSize) {
            try {
                dataRead[i] = readWord(i);
                i = (short) (i + 1);
            } catch (Exception e) {
                return null;
            }
        }
        if ((dataRead[0] & 1) > 0) {
            eeprom.BCDEnable = true;
        } else {
            eeprom.BCDEnable = false;
        }
        if ((dataRead[0] & 2) > 0) {
            eeprom.BCDForceCBusPWREN = true;
        } else {
            eeprom.BCDForceCBusPWREN = false;
        }
        if ((dataRead[0] & 4) > 0) {
            eeprom.BCDDisableSleep = true;
        } else {
            eeprom.BCDDisableSleep = false;
        }
        if ((dataRead[0] & 8) > 0) {
            eeprom.RS485EchoSuppress = true;
        } else {
            eeprom.RS485EchoSuppress = false;
        }
        if ((dataRead[0] & 64) > 0) {
            eeprom.PowerSaveEnable = true;
        } else {
            eeprom.PowerSaveEnable = false;
        }
        if ((dataRead[0] & 128) > 0) {
            eeprom.A_LoadVCP = true;
            eeprom.A_LoadD2XX = false;
        } else {
            eeprom.A_LoadVCP = false;
            eeprom.A_LoadD2XX = true;
        }
        eeprom.VendorId = (short) dataRead[1];
        eeprom.ProductId = (short) dataRead[2];
        getUSBConfig(eeprom, dataRead[4]);
        getDeviceControl(eeprom, dataRead[5]);
        if ((dataRead[5] & 16) > 0) {
            eeprom.FT1248ClockPolarity = true;
        } else {
            eeprom.FT1248ClockPolarity = false;
        }
        if ((dataRead[5] & 32) > 0) {
            eeprom.FT1248LSB = true;
        } else {
            eeprom.FT1248LSB = false;
        }
        if ((dataRead[5] & 64) > 0) {
            eeprom.FT1248FlowControl = true;
        } else {
            eeprom.FT1248FlowControl = false;
        }
        if ((dataRead[5] & 128) > 0) {
            eeprom.I2CDisableSchmitt = true;
        } else {
            eeprom.I2CDisableSchmitt = false;
        }
        if ((dataRead[5] & 256) == 256) {
            eeprom.InvertTXD = true;
        } else {
            eeprom.InvertTXD = false;
        }
        if ((dataRead[5] & 512) == 512) {
            eeprom.InvertRXD = true;
        } else {
            eeprom.InvertRXD = false;
        }
        if ((dataRead[5] & 1024) == 1024) {
            eeprom.InvertRTS = true;
        } else {
            eeprom.InvertRTS = false;
        }
        if ((dataRead[5] & 2048) == 2048) {
            eeprom.InvertCTS = true;
        } else {
            eeprom.InvertCTS = false;
        }
        if ((dataRead[5] & 4096) == 4096) {
            eeprom.InvertDTR = true;
        } else {
            eeprom.InvertDTR = false;
        }
        if ((dataRead[5] & 8192) == 8192) {
            eeprom.InvertDSR = true;
        } else {
            eeprom.InvertDSR = false;
        }
        if ((dataRead[5] & 16384) == 16384) {
            eeprom.InvertDCD = true;
        } else {
            eeprom.InvertDCD = false;
        }
        if ((dataRead[5] & 32768) == 32768) {
            eeprom.InvertRI = true;
        } else {
            eeprom.InvertRI = false;
        }
        switch ((short) (dataRead[6] & 3)) {
            case (short) 0:
                eeprom.AD_DriveCurrent = (byte) 0;
                break;
            case (short) 1:
                eeprom.AD_DriveCurrent = (byte) 1;
                break;
            case (short) 2:
                eeprom.AD_DriveCurrent = (byte) 2;
                break;
            case (short) 3:
                eeprom.AD_DriveCurrent = (byte) 3;
                break;
        }
        if (((short) (dataRead[6] & 4)) == (short) 4) {
            eeprom.AD_SlowSlew = true;
        } else {
            eeprom.AD_SlowSlew = false;
        }
        if (((short) (dataRead[6] & 8)) == (short) 8) {
            eeprom.AD_SchmittInput = true;
        } else {
            eeprom.AD_SchmittInput = false;
        }
        switch ((short) ((dataRead[6] & 48) >> 4)) {
            case (short) 0:
                eeprom.AC_DriveCurrent = (byte) 0;
                break;
            case (short) 1:
                eeprom.AC_DriveCurrent = (byte) 1;
                break;
            case (short) 2:
                eeprom.AC_DriveCurrent = (byte) 2;
                break;
            case (short) 3:
                eeprom.AC_DriveCurrent = (byte) 3;
                break;
        }
        if (((short) (dataRead[6] & 64)) == (short) 64) {
            eeprom.AC_SlowSlew = true;
        } else {
            eeprom.AC_SlowSlew = false;
        }
        if (((short) (dataRead[6] & 128)) == (short) 128) {
            eeprom.AC_SchmittInput = true;
        } else {
            eeprom.AC_SchmittInput = false;
        }
        eeprom.I2CSlaveAddress = dataRead[10];
        eeprom.I2CDeviceID = dataRead[11];
        eeprom.I2CDeviceID |= (dataRead[12] & 255) << 16;
        eeprom.CBus0 = (byte) (dataRead[13] & 255);
        eeprom.CBus1 = (byte) ((dataRead[13] >> 8) & 255);
        eeprom.CBus2 = (byte) (dataRead[14] & 255);
        eeprom.CBus3 = (byte) ((dataRead[14] >> 8) & 255);
        eeprom.CBus4 = (byte) (dataRead[15] & 255);
        eeprom.CBus5 = (byte) ((dataRead[15] >> 8) & 255);
        eeprom.CBus6 = (byte) (dataRead[16] & 255);
        this.mEepromType = (short) (dataRead[73] >> 8);
        eeprom.Manufacturer = getStringDescriptor((dataRead[7] & 255) / 2, dataRead);
        eeprom.Product = getStringDescriptor((dataRead[8] & 255) / 2, dataRead);
        eeprom.SerialNumber = getStringDescriptor((dataRead[9] & 255) / 2, dataRead);
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
