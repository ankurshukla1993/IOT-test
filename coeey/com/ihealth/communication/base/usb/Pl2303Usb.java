package com.ihealth.communication.base.usb;

import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbManager;
import android.widget.Toast;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import tw.com.prolific.driver.pl2303.PL2303Driver;
import tw.com.prolific.driver.pl2303.PL2303Driver.BaudRate;
import tw.com.prolific.driver.pl2303.PL2303Driver.DataBits;
import tw.com.prolific.driver.pl2303.PL2303Driver.FlowControl;
import tw.com.prolific.driver.pl2303.PL2303Driver.Parity;
import tw.com.prolific.driver.pl2303.PL2303Driver.StopBits;

public class Pl2303Usb implements BaseComm {
    private BaudRate f437a = BaudRate.B57600;
    private DataBits f438b = DataBits.D8;
    private Parity f439c = Parity.NONE;
    private StopBits f440d = StopBits.S1;
    private FlowControl f441e = FlowControl.OFF;
    private PL2303Driver f442f;
    private Context f443g;
    private BaseCommProtocol f444h;
    private UsbDevice f445i;
    private UsbManager f446j;
    private UsbDeviceConnection f447k;
    private int f448l = -1;
    private Queue f449m = new LinkedList();
    public byte[] mReadBuffer = new byte[4096];
    public int mReadBufferLength = -1;
    private int f450n;
    private boolean f451o = false;
    public byte[] readBuffer;

    public Pl2303Usb(Context context) {
        this.f443g = context;
        this.f442f = new PL2303Driver((UsbManager) this.f443g.getSystemService("usb"), this.f443g, "com.prolific.pl2303hxdsimpletest.USB_PERMISSION");
        if (!this.f442f.PL2303USBFeatureSupported()) {
            Toast.makeText(this.f443g, "No Support USB host API", 0).show();
            this.f442f = null;
        }
    }

    private void m333a() {
        int i = 1;
        if (this.f449m.size() < 6) {
            Log.w("PL2303Interface", "command length is not wrong");
            return;
        }
        if (160 == (((Byte) this.f449m.peek()).byteValue() & 255)) {
            this.f451o = true;
            this.f449m.poll();
        }
        if (this.f451o) {
            int byteValue = ((Byte) this.f449m.peek()).byteValue() & 255;
            int i2 = byteValue + 3;
            if (this.f449m.size() >= byteValue + 2) {
                byte[] bArr = new byte[i2];
                bArr[0] = (byte) -96;
                while (i < i2) {
                    Byte valueOf = Byte.valueOf(((Byte) this.f449m.poll()).byteValue());
                    if (valueOf != null) {
                        bArr[i] = valueOf.byteValue();
                    }
                    i++;
                }
                if (bArr.length > 3) {
                    byteValue = bArr[3] & 255;
                    if (this.f450n != byteValue) {
                        this.f450n = byteValue;
                        this.readBuffer = new byte[bArr.length];
                        this.mReadBufferLength = bArr.length;
                        for (byteValue = 0; byteValue < bArr.length; byteValue++) {
                            this.readBuffer[byteValue] = bArr[byteValue];
                        }
                        this.f451o = false;
                        this.f444h.unPackageData(this.readBuffer);
                        return;
                    }
                    Log.v("PL2303Interface", "Duplicate command");
                    return;
                }
                return;
            }
            Log.w("PL2303Interface", "This is not full command");
            return;
        }
        this.f449m.poll();
    }

    private void m335a(byte[] bArr, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.f449m.offer(Byte.valueOf(bArr[i2]));
        }
        m333a();
        m333a();
        m333a();
    }

    public void addCommContinueNotify(String mac, BaseCommProtocol dataCallBack) {
    }

    public void addCommNotify(BaseCommProtocol dataCallBack) {
        this.f444h = dataCallBack;
    }

    public void addCommNotify(String mac, BaseCommProtocol dataCallBack) {
    }

    public void disConnectFunction() {
        if (this.f442f != null) {
            this.f442f.end();
            this.f442f = null;
        }
    }

    public void disconnect() {
        Log.w("PL2303Interface", "disconnect--Pl2303Usb");
        disConnectFunction();
    }

    public void disconnect(String mac) {
    }

    public Context getContext() {
        return this.f443g;
    }

    public void initSerialPort() {
        if (!this.f442f.isConnected()) {
            if (this.f442f.enumerate()) {
                Log.v("PL2303Interface", "onResume:enumerate succeeded!");
            } else {
                Toast.makeText(this.f443g, "no more devices found", 0).show();
            }
        }
    }

    public void openUsbSerial() {
        if (this.f442f == null) {
            Log.w("PL2303Interface", "mSerial is null");
        } else if (this.f442f.isConnected()) {
            this.f437a = BaudRate.B57600;
            this.f445i = PL2303Driver.sDevice;
            this.f446j = (UsbManager) this.f443g.getSystemService("usb");
            this.f447k = this.f446j.openDevice(this.f445i);
            if (this.f442f.InitByBaudRate(this.f437a, 700)) {
                Log.v("PL2303Interface", "mContext is connected");
                return;
            }
            if (!this.f442f.PL2303Device_IsHasPermission()) {
                Toast.makeText(this.f443g, "cannot open, maybe no permission", 0).show();
            }
            if (this.f442f.PL2303Device_IsHasPermission() && !this.f442f.PL2303Device_IsSupportChip()) {
                Toast.makeText(this.f443g, "cannot open, maybe this chip has no support, please use PL2303HXD / RA / EA chip.", 0).show();
            }
        }
    }

    public void readUsbSerialThread() {
        new C2039b(this).start();
    }

    public void removeCommNotify(BaseCommProtocol dataCallBack) {
    }

    public void removeCommNotify(String mac) {
    }

    public void sendData(String mac, String deviceIP, byte[] data) {
    }

    public void sendData(String mac, byte[] data) {
        Log.p("PL2303Interface", Level.VERBOSE, "sendData", new Object[]{mac, ByteBufferUtil.Bytes2HexString(data, data.length)});
        if (this.f442f == null) {
            Log.w("PL2303Interface", "mSerial is null");
        } else if (this.f442f.isConnected()) {
            int write = this.f442f.write(data, data.length);
            if (write < 0) {
                Log.w("PL2303Interface", "sendData() -- failed:" + write);
            }
        } else {
            Log.w("PL2303Interface", "mSerial is not connected");
        }
    }

    public int setSerialPort() {
        int i = -1;
        if (this.f442f == null) {
            Log.w("PL2303Interface", "setSerialPort null == mSerial");
        } else if (this.f442f.isConnected()) {
            try {
                i = this.f442f.setup(this.f437a, this.f438b, this.f440d, this.f439c, this.f441e);
            } catch (IOException e) {
                e.printStackTrace();
                i = 0;
            }
            if (i < 0) {
                Log.w("PL2303Interface", "fail to setup");
            }
        } else {
            Log.w("PL2303Interface", "setSerialPort !mSerial.isConnected()");
        }
        return i;
    }
}
