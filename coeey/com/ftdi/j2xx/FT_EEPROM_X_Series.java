package com.ftdi.j2xx;

public class FT_EEPROM_X_Series extends FT_EEPROM {
    public byte AC_DriveCurrent = (byte) 0;
    public boolean AC_SchmittInput = false;
    public boolean AC_SlowSlew = false;
    public byte AD_DriveCurrent = (byte) 0;
    public boolean AD_SchmittInput = false;
    public boolean AD_SlowSlew = false;
    public short A_DeviceTypeValue = (short) 0;
    public boolean A_LoadD2XX = false;
    public boolean A_LoadVCP = false;
    public boolean BCDDisableSleep = false;
    public boolean BCDEnable = false;
    public boolean BCDForceCBusPWREN = false;
    public byte CBus0 = (byte) 0;
    public byte CBus1 = (byte) 0;
    public byte CBus2 = (byte) 0;
    public byte CBus3 = (byte) 0;
    public byte CBus4 = (byte) 0;
    public byte CBus5 = (byte) 0;
    public byte CBus6 = (byte) 0;
    public boolean FT1248ClockPolarity = false;
    public boolean FT1248FlowControl = false;
    public boolean FT1248LSB = false;
    public int I2CDeviceID = 0;
    public boolean I2CDisableSchmitt = false;
    public int I2CSlaveAddress = 0;
    public boolean InvertCTS = false;
    public boolean InvertDCD = false;
    public boolean InvertDSR = false;
    public boolean InvertDTR = false;
    public boolean InvertRI = false;
    public boolean InvertRTS = false;
    public boolean InvertRXD = false;
    public boolean InvertTXD = false;
    public boolean PowerSaveEnable = false;
    public boolean RS485EchoSuppress = false;

    public static final class CBUS {
        static final int BCD_Charge1 = 13;
        static final int BCD_Charge2 = 14;
        static final int BitBang_RD = 19;
        static final int BitBang_WR = 18;
        static final int CLK12MHz = 11;
        static final int CLK24MHz = 10;
        static final int CLK6MHz = 12;
        static final int DRIVE_0 = 6;
        static final int DRIVE_1 = 7;
        static final int GPIO_MODE = 8;
        static final int I2C_RXF = 16;
        static final int I2C_TXE = 15;
        static final int Keep_Awake = 21;
        static final int PWREN = 4;
        static final int RXLED = 1;
        static final int SLEEP = 5;
        static final int TRISTATE = 0;
        static final int TXDEN = 9;
        static final int TXLED = 2;
        static final int TXRXLED = 3;
        static final int Time_Stamp = 20;
        static final int VBUS_Sense = 17;
    }

    public static final class DRIVE_STRENGTH {
        static final byte DRIVE_12mA = (byte) 2;
        static final byte DRIVE_16mA = (byte) 3;
        static final byte DRIVE_4mA = (byte) 0;
        static final byte DRIVE_8mA = (byte) 1;
    }
}
