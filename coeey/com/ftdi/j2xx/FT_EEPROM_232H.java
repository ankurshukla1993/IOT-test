package com.ftdi.j2xx;

public class FT_EEPROM_232H extends FT_EEPROM {
    public byte AL_DriveCurrent = (byte) 0;
    public boolean AL_SchmittInput = false;
    public boolean AL_SlowSlew = false;
    public byte BL_DriveCurrent = (byte) 0;
    public boolean BL_SchmittInput = false;
    public boolean BL_SlowSlew = false;
    public byte CBus0 = (byte) 0;
    public byte CBus1 = (byte) 0;
    public byte CBus2 = (byte) 0;
    public byte CBus3 = (byte) 0;
    public byte CBus4 = (byte) 0;
    public byte CBus5 = (byte) 0;
    public byte CBus6 = (byte) 0;
    public byte CBus7 = (byte) 0;
    public byte CBus8 = (byte) 0;
    public byte CBus9 = (byte) 0;
    public boolean FIFO = false;
    public boolean FIFOTarget = false;
    public boolean FT1248 = false;
    public boolean FT1248ClockPolarity = false;
    public boolean FT1248FlowControl = false;
    public boolean FT1248LSB = false;
    public boolean FastSerial = false;
    public boolean LoadD2XX = false;
    public boolean LoadVCP = false;
    public boolean PowerSaveEnable = false;
    public boolean UART = false;

    public static final class CBUS {
        static final int CLK15MHz = 11;
        static final int CLK30MHz = 10;
        static final int CLK7_5MHz = 12;
        static final int DRIVE_0 = 6;
        static final int DRIVE_1 = 7;
        static final int GPIO_MODE = 8;
        static final int PWREN = 4;
        static final int RXLED = 2;
        static final int SLEEP = 5;
        static final int TRISTATE = 0;
        static final int TXDEN = 9;
        static final int TXLED = 1;
        static final int TXRXLED = 3;
    }

    public static final class DRIVE_STRENGTH {
        static final byte DRIVE_12mA = (byte) 2;
        static final byte DRIVE_16mA = (byte) 3;
        static final byte DRIVE_4mA = (byte) 0;
        static final byte DRIVE_8mA = (byte) 1;
    }
}
