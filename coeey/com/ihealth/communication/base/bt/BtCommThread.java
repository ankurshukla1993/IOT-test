package com.ihealth.communication.base.bt;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BtCommThread extends Thread implements BaseComm {
    public static boolean ioException = false;
    private BluetoothDevice f323a = null;
    private BluetoothSocket f324b = null;
    private InputStream f325c = null;
    private OutputStream f326d = null;
    private int f327e;
    private byte[] f328f;
    private Context f329g;
    private BtUnpackageData f330h;
    private BaseCommCallback f331i;
    private String f332j;

    public BtCommThread(Context context, BluetoothDevice device, String type, BluetoothSocket socket, BaseCommCallback baseCommCallback) {
        this.f329g = context;
        this.f323a = device;
        this.f324b = socket;
        this.f332j = type;
        this.f331i = baseCommCallback;
        this.f325c = socket.getInputStream();
        this.f326d = socket.getOutputStream();
        this.f328f = new byte[256];
        this.f330h = new BtUnpackageData();
    }

    private void m271a() {
        try {
            if (this.f324b != null) {
                this.f324b.close();
            }
            this.f325c = null;
            this.f326d = null;
            this.f324b = null;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            this.f325c = null;
            this.f326d = null;
            this.f324b = null;
        }
    }

    public void addCommContinueNotify(String mac, BaseCommProtocol dataCallBack) {
    }

    public void addCommNotify(BaseCommProtocol dataCallBack) {
        this.f330h.addBtCommProtocol(dataCallBack);
    }

    public void addCommNotify(String mac, BaseCommProtocol dataCallBack) {
    }

    public void disconnect() {
        m271a();
    }

    public void disconnect(String mac) {
        m271a();
    }

    public Context getContext() {
        return this.f329g;
    }

    public void removeCommNotify(BaseCommProtocol dataCallBack) {
    }

    public void removeCommNotify(String mac) {
    }

    public void run() {
        while (this.f325c != null) {
            try {
                this.f327e = this.f325c.read(this.f328f);
                if (this.f327e > 0) {
                    Log.p("Runtime_BtCommThread", Level.VERBOSE, "Read", new Object[]{ByteBufferUtil.Bytes2HexString(this.f328f, this.f327e)});
                    this.f330h.addReadUsbData(this.f328f, this.f327e);
                }
                ioException = false;
            } catch (Exception e) {
                Exception exception = e;
                ioException = true;
                this.f331i.onConnectionStateChange(this.f323a.getAddress().replace(":", ""), this.f332j, 2, 0, null);
                Log.w("Runtime_BtCommThread", "Read() -- Exception: " + exception);
                m271a();
                return;
            }
        }
    }

    public void sendData(String mac, String deviceIP, byte[] data) {
    }

    public void sendData(String mac, byte[] data) {
        try {
            Log.p("Runtime_BtCommThread", Level.VERBOSE, "sendData", new Object[]{mac, ByteBufferUtil.Bytes2HexString(data, data.length)});
            if (this.f326d != null) {
                this.f326d.write(data);
                this.f326d.flush();
            }
        } catch (IOException e) {
            Log.w("Runtime_BtCommThread", "sendData() -- IOException: " + e);
            m271a();
        }
    }
}
