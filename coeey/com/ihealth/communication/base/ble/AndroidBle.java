package com.ihealth.communication.base.ble;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.ParcelUuid;
import android.util.SparseArray;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.manager.iHealthDevicesIDPS;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import humanize.util.Constants;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class AndroidBle implements BleCallback, BleComm, BaseComm {
    private Ble f274a;
    private BtleCallback f275b;
    private Context f276c;
    private Map f277d = new ConcurrentHashMap();
    private Map f278e = new ConcurrentHashMap();

    public AndroidBle(Context context, BtleCallback btleCallback) {
        this.f276c = context;
        this.f274a = new Ble(context, this);
        this.f275b = btleCallback;
    }

    private int m238a(byte[] bArr, byte b) {
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            if (bArr[i] == b) {
                return i;
            }
        }
        return bArr.length;
    }

    private String m239a(String str) {
        return str.length() == 12 ? str : ByteBufferUtil.mac2Address(ByteBufferUtil.hexStringToByte(str));
    }

    public void Obtain(UUID uuid) {
        this.f274a.readCharacteristic(uuid);
    }

    public boolean Obtain(String mac, UUID serviceUuid, UUID charactUuid) {
        return this.f274a.readCharacteristicExtra(mac, serviceUuid, charactUuid);
    }

    public void addCommContinueNotify(String mac, BaseCommProtocol dataCallBack) {
        this.f278e.put(mac, dataCallBack);
    }

    public void addCommNotify(BaseCommProtocol dataCallBack) {
    }

    public void addCommNotify(String mac, BaseCommProtocol dataCallBack) {
        this.f277d.put(mac, dataCallBack);
    }

    public boolean connectDevice(String address) {
        return this.f274a.connectDevice(address);
    }

    public void destory() {
    }

    public void disconnect() {
    }

    public void disconnect(String mac) {
        if (this.f274a != null) {
            this.f274a.disconnect(m239a(mac));
        }
    }

    public BaseComm getBaseComm() {
        return this;
    }

    public Context getContext() {
        return this.f276c;
    }

    public void getService(String mac, String comm, String trans, String rece, String idps, boolean needIndication) {
        Log.p("Runtime_AndroidBle", Level.VERBOSE, "getService", new Object[]{mac, comm, trans, rece, idps, Boolean.valueOf(needIndication)});
        if (comm == null || rece == null || idps == null) {
            this.f274a.disconnect(mac);
        } else if (trans == null) {
            this.f274a.m263a(mac, UUID.fromString(comm), null, UUID.fromString(rece), UUID.fromString(idps), needIndication);
        } else {
            this.f274a.m263a(mac, UUID.fromString(comm), UUID.fromString(trans), UUID.fromString(rece), UUID.fromString(idps), needIndication);
        }
    }

    public void onCharacteristicChanged(BluetoothDevice device, byte[] data, UUID uuid) {
        String replace = device.getAddress().replace(":", "");
        String uuid2 = uuid.toString();
        Log.p("Runtime_AndroidBle", Level.VERBOSE, "onCharacteristicChanged", new Object[]{replace, ByteBufferUtil.Bytes2HexString(data)});
        if (uuid2.equals(BleConfig.UUID_BTM_READ_DATA_CHARACTERISTIC) || uuid2.equals(BleConfig.UUID_BP_RECEIVE) || uuid2.equals("0000ff03-0000-1000-8000-00805f9b34fb") || uuid2.equals(BleConfig.UUID_BG_RECEIVE) || uuid2.equals(BleConfig.UUID_BG_RECEIVE_CONTENT) || uuid2.equals("00002a52-0000-1000-8000-00805f9b34fb") || uuid2.equals(BleConfig.UUID_PO_RECEIVE) || uuid2.equals(BleConfig.UUID_PO_RECEIVE_CONTINUOUS) || uuid2.equals("00002a52-0000-1000-8000-00805f9b34fb") || uuid2.equals(BleConfig.UUID_HS_RECEIVE) || uuid2.equals(BleConfig.UUID_HS_RECEIVE) || uuid2.equals(BleConfig.UUID_BS_RECEIVE) || uuid2.equals(BleConfig.UUID_BS_RECEIVE)) {
            ((BaseCommProtocol) this.f278e.get(replace)).unPackageDataUuid(uuid2, data);
        } else {
            ((BaseCommProtocol) this.f277d.get(replace)).unPackageData(data);
        }
    }

    public void onCharacteristicRead(BluetoothDevice device, byte[] arg0, UUID arg1, int arg2) {
        String replace = device.getAddress().replace(":", "");
        String uuid = arg1.toString();
        if (uuid.equals(BleConfig.UUID_BP_READ) || uuid.equals(BleConfig.UUID_BG_READ) || uuid.equals(BleConfig.UUID_PO_READ) || uuid.equals(BleConfig.UUID_HS_READ) || uuid.equals(BleConfig.UUID_BS_READ) || uuid.equals(BleConfig.UUID_BTM_BATTERY_LEVEL_CHARACTERISTIC)) {
            Log.p("Runtime_AndroidBle", Level.VERBOSE, "onCharacteristicRead", new Object[]{replace, ByteBufferUtil.Bytes2HexString(arg0)});
            ((BaseCommProtocol) this.f278e.get(replace)).unPackageDataUuid(uuid, arg0);
            return;
        }
        this.f275b.onCharacteristicRead(arg0, arg1, arg2);
    }

    public void onCharacteristicWrite(BluetoothDevice device, UUID uuid, int status) {
        String replace = device.getAddress().replace(":", "");
        if (this.f277d.get(replace) != null) {
            ((BaseCommProtocol) this.f277d.get(replace)).packageDataFinish();
        } else {
            Log.v("Runtime_AndroidBle", "onCharacteristicWrite success");
        }
    }

    public void onConnectionStateChange(BluetoothDevice arg0, int arg1, int arg2) {
        this.f275b.onConnectionStateChange(arg0, arg1, arg2);
    }

    public void onDescriptorRead(byte[] arg0, UUID arg1, int arg2) {
    }

    public void onDescriptorWrite() {
    }

    public void onRssi(int arg0) {
    }

    public void onScanResult(BluetoothDevice arg0, int arg1, byte[] arg2) {
        ScanRecord parseFromBytes = ScanRecord.parseFromBytes(arg2);
        List<ParcelUuid> serviceUuids = parseFromBytes.getServiceUuids();
        if (serviceUuids != null) {
            String str = "";
            for (ParcelUuid parcelUuid : serviceUuids) {
                str = str + Constants.SPACE + parcelUuid.toString();
            }
            Map hashMap = new HashMap();
            if (str.contains(BleConfig.UUID_BP_SERVICE)) {
                SparseArray manufacturerSpecificData = parseFromBytes.getManufacturerSpecificData();
                for (int i = 0; i < manufacturerSpecificData.size(); i++) {
                    byte[] bArr = (byte[]) manufacturerSpecificData.valueAt(i);
                    if (bArr.length >= 20) {
                        Object str2;
                        byte[] bufferCut = ByteBufferUtil.bufferCut(bArr, bArr.length - 20, 16);
                        try {
                            str2 = new String(ByteBufferUtil.bufferCut(bufferCut, 0, m238a(bufferCut, (byte) 0)), "UTF-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            str2 = "";
                        }
                        hashMap.put(iHealthDevicesIDPS.MODENUMBER, str2);
                        hashMap.put(iHealthDevicesIDPS.SERIALNUMBER, ByteBufferUtil.Bytes2HexString(bArr, bArr.length - 4, bArr.length));
                        break;
                    }
                }
            }
            this.f275b.onScanResult(arg0, arg1, str, hashMap);
        }
    }

    public void onServicesDiscovered(BluetoothDevice arg0, List arg1, int arg2) {
        this.f275b.onServicesDiscovered(arg0, arg1, arg2);
    }

    public void onServicesObtain() {
    }

    public void onServicesObtain(UUID uuid, BluetoothDevice device, String para) {
        Log.p("Runtime_AndroidBle", Level.VERBOSE, "onServicesObtain", new Object[]{uuid, device.getAddress().replace(":", ""), para});
        String uuid2 = uuid.toString();
        if (uuid2.equals(BleConfig.UUID_BP_RECEIVE) || uuid2.equals("0000ff03-0000-1000-8000-00805f9b34fb") || uuid2.equals(BleConfig.UUID_BG_RECEIVE) || uuid2.equals(BleConfig.UUID_BG_RECEIVE_CONTENT) || uuid2.equals("00002a52-0000-1000-8000-00805f9b34fb") || uuid2.equals(BleConfig.UUID_PO_RECEIVE) || uuid2.equals(BleConfig.UUID_PO_RECEIVE_CONTINUOUS) || uuid2.equals("00002a52-0000-1000-8000-00805f9b34fb") || uuid2.equals(BleConfig.UUID_HS_RECEIVE) || uuid2.equals(BleConfig.UUID_HS_RECEIVE) || uuid2.equals(BleConfig.UUID_BS_RECEIVE) || uuid2.equals(BleConfig.UUID_BS_RECEIVE)) {
            ((BaseCommProtocol) this.f278e.get(device.getAddress().replace(":", ""))).unPackageDataUuid(uuid2, null);
            return;
        }
        this.f275b.onServicesObtain();
    }

    public void refresh(String mac) {
        this.f274a.refresh(mac);
    }

    public void removeCommNotify(BaseCommProtocol dataCallBack) {
    }

    public void removeCommNotify(String mac) {
    }

    public void scan(boolean b) {
        try {
            this.f274a.scan(b);
        } catch (Exception e) {
            Log.e("Runtime_AndroidBle", "scan() -- Exception: " + e);
        }
    }

    public void sendData(String mac, String deviceIP, byte[] data) {
    }

    public void sendData(String mac, byte[] data) {
        Log.p("Runtime_AndroidBle", Level.VERBOSE, "_______________sendData", new Object[]{mac, ByteBufferUtil.Bytes2HexString(data)});
        this.f274a.sendData(m239a(mac), data);
    }

    public boolean writeDataExtra(String mac, UUID serviceUuid, UUID characteristicUUid, byte[] command) {
        Log.p("Runtime_AndroidBle", Level.VERBOSE, "_________writeDataExtra", new Object[]{mac, ByteBufferUtil.Bytes2HexString(command)});
        return this.f274a.writeCharacteristicExtra(mac, serviceUuid, characteristicUUid, command);
    }
}
