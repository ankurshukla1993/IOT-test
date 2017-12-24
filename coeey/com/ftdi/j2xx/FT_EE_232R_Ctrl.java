package com.ftdi.j2xx;

import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewCompat;

class FT_EE_232R_Ctrl extends FT_EE_Ctrl {
    private static final short EEPROM_SIZE = (short) 80;
    private static final short EE_MAX_SIZE = (short) 1024;
    private static final short ENDOFUSERLOCATION = (short) 63;
    private static final int EXTERNAL_OSCILLATOR = 2;
    private static final int HIGH_CURRENT_IO = 4;
    private static final int INVERT_CTS = 2048;
    private static final int INVERT_DCD = 16384;
    private static final int INVERT_DSR = 8192;
    private static final int INVERT_DTR = 4096;
    private static final int INVERT_RI = 32768;
    private static final int INVERT_RTS = 1024;
    private static final int INVERT_RXD = 512;
    private static final int INVERT_TXD = 256;
    private static final int LOAD_D2XX_DRIVER = 8;
    private static FT_Device ft_device;

    FT_EE_232R_Ctrl(FT_Device usbC) {
        super(usbC);
        ft_device = usbC;
    }

    boolean writeWord(short offset, short value) {
        int wValue = value & 65535;
        int wIndex = offset & 65535;
        boolean rc = false;
        if (offset >= (short) 1024) {
            return 0;
        }
        byte latency = ft_device.getLatencyTimer();
        ft_device.setLatencyTimer((byte) 119);
        if (ft_device.getConnection().controlTransfer(64, 145, wValue, wIndex, null, 0, 0) == 0) {
            rc = true;
        }
        ft_device.setLatencyTimer(latency);
        return rc;
    }

    short programEeprom(FT_EEPROM ee) {
        if (ee.getClass() != FT_EEPROM_232R.class) {
            return (short) 1;
        }
        int[] data = new int[80];
        FT_EEPROM_232R eeprom = (FT_EEPROM_232R) ee;
        for (short i = (short) 0; i < EEPROM_SIZE; i = (short) (i + 1)) {
            data[i] = readWord(i);
        }
        try {
            int wordx00 = 0 | (data[0] & MotionEventCompat.ACTION_POINTER_INDEX_MASK);
            if (eeprom.HighIO) {
                wordx00 |= 4;
            }
            if (eeprom.LoadVCP) {
                wordx00 |= 8;
            }
            if (eeprom.ExternalOscillator) {
                wordx00 |= 2;
            } else {
                wordx00 &= 65533;
            }
            data[0] = wordx00;
            data[1] = eeprom.VendorId;
            data[2] = eeprom.ProductId;
            data[3] = 1536;
            data[4] = setUSBConfig(ee);
            int wordx05 = setDeviceControl(ee);
            if (eeprom.InvertTXD) {
                wordx05 |= 256;
            }
            if (eeprom.InvertRXD) {
                wordx05 |= 512;
            }
            if (eeprom.InvertRTS) {
                wordx05 |= 1024;
            }
            if (eeprom.InvertCTS) {
                wordx05 |= 2048;
            }
            if (eeprom.InvertDTR) {
                wordx05 |= 4096;
            }
            if (eeprom.InvertDSR) {
                wordx05 |= 8192;
            }
            if (eeprom.InvertDCD) {
                wordx05 |= 16384;
            }
            if (eeprom.InvertRI) {
                wordx05 |= 32768;
            }
            data[5] = wordx05;
            int c2 = eeprom.CBus2 << 8;
            data[10] = ((eeprom.CBus0 | (eeprom.CBus1 << 4)) | c2) | (eeprom.CBus3 << 12);
            data[11] = eeprom.CBus4;
            int saddr = setStringDescriptor(eeprom.Product, data, setStringDescriptor(eeprom.Manufacturer, data, 12, 7, true), 8, true);
            if (eeprom.SerNumEnable) {
                saddr = setStringDescriptor(eeprom.SerialNumber, data, saddr, 9, true);
            }
            if (data[1] == 0 || data[2] == 0) {
                return (short) 2;
            }
            byte latency = ft_device.getLatencyTimer();
            ft_device.setLatencyTimer((byte) 119);
            boolean returnCode = programEeprom(data, 80);
            ft_device.setLatencyTimer(latency);
            if (returnCode) {
                return (short) 0;
            }
            return (short) 1;
        } catch (Exception e) {
            e.printStackTrace();
            return (short) 0;
        }
    }

    FT_EEPROM readEeprom() {
        FT_EEPROM_232R eeprom = new FT_EEPROM_232R();
        int[] data = new int[80];
        for (int i = 0; i < 80; i++) {
            data[i] = readWord((short) i);
        }
        try {
            if ((data[0] & 4) == 4) {
                eeprom.HighIO = true;
            } else {
                eeprom.HighIO = false;
            }
            if ((data[0] & 8) == 8) {
                eeprom.LoadVCP = true;
            } else {
                eeprom.LoadVCP = false;
            }
            if ((data[0] & 2) == 2) {
                eeprom.ExternalOscillator = true;
            } else {
                eeprom.ExternalOscillator = false;
            }
            eeprom.VendorId = (short) data[1];
            eeprom.ProductId = (short) data[2];
            getUSBConfig(eeprom, data[4]);
            getDeviceControl(eeprom, data[5]);
            if ((data[5] & 256) == 256) {
                eeprom.InvertTXD = true;
            } else {
                eeprom.InvertTXD = false;
            }
            if ((data[5] & 512) == 512) {
                eeprom.InvertRXD = true;
            } else {
                eeprom.InvertRXD = false;
            }
            if ((data[5] & 1024) == 1024) {
                eeprom.InvertRTS = true;
            } else {
                eeprom.InvertRTS = false;
            }
            if ((data[5] & 2048) == 2048) {
                eeprom.InvertCTS = true;
            } else {
                eeprom.InvertCTS = false;
            }
            if ((data[5] & 4096) == 4096) {
                eeprom.InvertDTR = true;
            } else {
                eeprom.InvertDTR = false;
            }
            if ((data[5] & 8192) == 8192) {
                eeprom.InvertDSR = true;
            } else {
                eeprom.InvertDSR = false;
            }
            if ((data[5] & 16384) == 16384) {
                eeprom.InvertDCD = true;
            } else {
                eeprom.InvertDCD = false;
            }
            if ((data[5] & 32768) == 32768) {
                eeprom.InvertRI = true;
            } else {
                eeprom.InvertRI = false;
            }
            eeprom.CBus0 = (byte) (data[10] & 255);
            eeprom.CBus1 = (byte) ((data[10] & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 4);
            eeprom.CBus2 = (byte) ((data[10] & 16711680) >> 8);
            eeprom.CBus3 = (byte) ((data[10] & ViewCompat.MEASURED_STATE_MASK) >> 12);
            eeprom.CBus4 = (byte) (data[11] & 255);
            eeprom.Manufacturer = getStringDescriptor(((data[7] & 255) - 128) / 2, data);
            eeprom.Product = getStringDescriptor(((data[8] & 255) - 128) / 2, data);
            eeprom.SerialNumber = getStringDescriptor(((data[9] & 255) - 128) / 2, data);
            return eeprom;
        } catch (Exception e) {
            return null;
        }
    }

    int getUserSize() {
        return (((63 - ((((((readWord((short) 7) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8) / 2) + 12) + (((readWord((short) 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8) / 2)) + 1)) - (((readWord((short) 9) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) >> 8) / 2)) - 1) * 2;
    }

    int writeUserData(byte[] data) {
        if (data.length > getUserSize()) {
            return 0;
        }
        short offset = (short) (65535 & ((short) ((63 - (getUserSize() / 2)) - 1)));
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
        short offset = (short) (65535 & ((short) ((63 - (getUserSize() / 2)) - 1)));
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
