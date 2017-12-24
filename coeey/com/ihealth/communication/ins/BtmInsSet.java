package com.ihealth.communication.ins;

import android.content.Context;
import android.content.SharedPreferences;
import com.facebook.imageutils.JfifUtil;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.ihealth.communication.base.ble.BleConfig;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.protocol.BleCommContinueProtocol;
import com.ihealth.communication.control.AmProfile;
import com.ihealth.communication.control.BtmControl;
import com.ihealth.communication.control.BtmProfile;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import com.lifesense.ble.bean.DeviceTypeConstants;
import humanize.util.Constants;
import java.util.Calendar;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BtmInsSet implements NewDataCallback {
    private final BaseCommCallback f1811a;
    private BleCommContinueProtocol f1812b;
    private Context f1813c;
    private BaseComm f1814d;
    private String f1815e;
    private String f1816f;
    private InsCallback f1817g;
    private boolean f1818h;
    private int f1819i;
    private SharedPreferences f1820j;
    private int f1821k;
    private int f1822l;
    private int f1823m;
    private JSONArray f1824n = new JSONArray();
    private int f1825o;
    private int f1826p;

    public BtmInsSet(Context context, BaseComm com, String userName, String mac, String type, InsCallback insCallback, BaseCommCallback baseCommCallback) {
        Log.p("BtmInsSet", Level.INFO, "BtmInsSet_Constructor", new Object[]{userName, mac, type});
        this.f1813c = context;
        this.f1814d = com;
        this.f1815e = mac;
        this.f1816f = type;
        this.f1817g = insCallback;
        this.f1811a = baseCommCallback;
        this.f1812b = new BleCommContinueProtocol(com, mac, this);
        m976a();
    }

    private JSONObject m975a(byte[] bArr) {
        JSONObject jSONObject = new JSONObject();
        if (!(bArr[7] == (byte) 0 && bArr[8] == (byte) 0)) {
            int i = bArr[3] & 255;
            int i2 = bArr[4] & 255;
            int i3 = bArr[5] & 255;
            int i4 = bArr[6] & 255;
            int i5 = bArr[7] & 255;
            String str = ((bArr[2] & 255) + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE) + "-" + i + "-" + i2 + Constants.SPACE + i3 + ":" + i4 + ":" + i5;
            if (("" + ((byte) ((bArr[8] >> 7) & 1)) + ((byte) ((bArr[8] >> 6) & 1))).equals(DeviceTypeConstants.WEIGHT_SCALE)) {
                jSONObject.put(BtmProfile.BTM_TEMPERATURE_TARGET, "body");
            } else {
                jSONObject.put(BtmProfile.BTM_TEMPERATURE_TARGET, "object");
            }
            float f = (float) (((double) ((((bArr[8] & 255) << 8) + (bArr[9] & 255)) & 4095)) / 100.0d);
            jSONObject.put("measure_time", str);
            jSONObject.put(BtmProfile.BTM_TEMPERATURE, (double) f);
        }
        return jSONObject;
    }

    private void m976a() {
        this.f1820j = this.f1813c.getSharedPreferences("btm_settings", 0);
    }

    private byte m977b(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i < bArr.length) {
            i2 += bArr[i];
            i++;
        }
        return (byte) i2;
    }

    private void m978b() {
        this.f1814d.sendData(this.f1815e, new byte[]{(byte) -48, (byte) -86, (byte) this.f1820j.getInt(AmProfile.GET_USER_UNIT_AM, BtmControl.TEMPERATURE_UNIT_C.byteValue()), (byte) this.f1820j.getInt("measure", BtmControl.MEASURING_TARGET_BODY.byteValue()), (byte) this.f1820j.getInt("function", BtmControl.FUNCTION_TARGET_ONLINE.byteValue()), (byte) this.f1820j.getInt("hour", 0), (byte) this.f1820j.getInt("minute", 1), (byte) this.f1820j.getInt("second", 0), m977b(r0)});
        this.f1818h = false;
    }

    private void m979c() {
        this.f1814d.sendData(this.f1815e, new byte[]{(byte) -48, (byte) -90, m977b(r0)});
    }

    private void m980d() {
        this.f1814d.sendData(this.f1815e, new byte[]{(byte) -48, (byte) -89, m977b(r0)});
    }

    public void destroy() {
        Log.p("BtmInsSet", Level.INFO, "destroy", new Object[0]);
        this.f1814d = null;
        this.f1817g = null;
        this.f1813c = null;
        if (this.f1812b != null) {
            this.f1812b.destroy();
        }
        this.f1812b = null;
    }

    public void getBattery() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("battery", this.f1819i);
            this.f1817g.onNotify(this.f1815e, this.f1816f, BtmProfile.ACTION_BTM_BATTERY, jSONObject.toString());
        } catch (JSONException e) {
            Log.p("BtmInsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
        }
    }

    public void getMemoryData() {
        Log.p("BtmInsSet", Level.INFO, "getMemoryData", new Object[0]);
        this.f1821k = 6;
        this.f1814d.sendData(this.f1815e, new byte[]{(byte) -48, (byte) -91, m977b(r0)});
    }

    public void haveNewData(int what, int stateId, byte[] command) {
        Log.p("BtmInsSet", Level.DEBUG, "haveNewData", new Object[]{Integer.valueOf(what), Integer.valueOf(stateId), ByteBufferUtil.Bytes2HexString(command)});
    }

    public void haveNewDataUuid(String uuid, byte[] command) {
        Log.p("BtmInsSet", Level.DEBUG, "haveNewData", new Object[]{uuid, ByteBufferUtil.Bytes2HexString(command)});
        if (!uuid.equals(BleConfig.UUID_BTM_READ_DATA_CHARACTERISTIC)) {
            return;
        }
        if ((command[0] & 255) != JfifUtil.MARKER_RST0) {
            this.f1825o++;
            if (this.f1825o > 3) {
                m979c();
                return;
            }
            m980d();
            this.f1825o = 0;
            return;
        }
        JSONObject jSONObject;
        switch (command[1] & 255) {
            case 176:
                m980d();
                if (((command[2] & 255) == 196 ? 1 : 0) != 0) {
                    this.f1819i = 0;
                } else {
                    this.f1819i = 1;
                }
                this.f1811a.onConnectionStateChange(this.f1815e, this.f1816f, 1, 0, null);
                return;
            case 177:
                m980d();
                try {
                    jSONObject = new JSONObject();
                    if (("" + ((byte) ((command[2] >> 7) & 1)) + ((byte) ((command[2] >> 6) & 1))).equals(DeviceTypeConstants.WEIGHT_SCALE)) {
                        jSONObject.put(BtmProfile.BTM_TEMPERATURE_TARGET, "body");
                    } else {
                        jSONObject.put(BtmProfile.BTM_TEMPERATURE_TARGET, "object");
                    }
                    jSONObject.put(BtmProfile.BTM_TEMPERATURE, (double) ((float) (((double) ((((command[2] & 255) << 8) + (command[3] & 255)) & 4095)) / 100.0d)));
                    this.f1817g.onNotify(this.f1815e, this.f1816f, BtmProfile.ACTION_BTM_MEASURE, jSONObject.toString());
                    return;
                } catch (JSONException e) {
                    Log.p("BtmInsSet", Level.WARN, "Exception", new Object[]{e.getMessage()});
                    return;
                }
            case 178:
                this.f1822l = command[2] & 255;
                this.f1824n = null;
                this.f1824n = new JSONArray();
                this.f1823m = 0;
                return;
            case 179:
                this.f1823m++;
                if (this.f1823m < this.f1822l) {
                    try {
                        this.f1824n.put(m975a(command));
                        return;
                    } catch (Exception e2) {
                        Log.p("BtmInsSet", Level.WARN, "Exception", new Object[]{e2.getMessage()});
                        return;
                    }
                }
                m980d();
                try {
                    jSONObject = new JSONObject();
                    jSONObject.put(BtmProfile.MEMORY_COUNT, this.f1822l);
                    jSONObject.put(BtmProfile.BTM_TEMPERATURE_ARRAY, this.f1824n);
                    this.f1817g.onNotify(this.f1815e, this.f1816f, BtmProfile.ACTION_BTM_MEMORY, jSONObject.toString());
                    return;
                } catch (Exception e22) {
                    Log.p("BtmInsSet", Level.WARN, "Exception", new Object[]{e22.getMessage()});
                    return;
                }
            case 180:
                m980d();
                switch (command[2] & 255) {
                    case 1:
                    case 2:
                    case 3:
                        return;
                    case 4:
                        this.f1820j.edit().putInt(AmProfile.GET_USER_UNIT_AM, command[3] & 255).apply();
                        return;
                    default:
                        return;
                }
            case 182:
                this.f1826p++;
                if (this.f1826p <= 3) {
                    switch (this.f1821k) {
                        case 1:
                            identify();
                            return;
                        case 2:
                            setStandbyTime(this.f1820j.getInt("hour", 0), this.f1820j.getInt("minute", 1), this.f1820j.getInt("second", 0));
                            return;
                        case 3:
                            setTemperatureUnit((byte) this.f1820j.getInt(AmProfile.GET_USER_UNIT_AM, BtmControl.TEMPERATURE_UNIT_C.byteValue()));
                            return;
                        case 4:
                            setMeasuringTarget((byte) this.f1820j.getInt("measure", BtmControl.MEASURING_TARGET_BODY.byteValue()));
                            return;
                        case 5:
                            setOfflineTarget((byte) this.f1820j.getInt("function", BtmControl.FUNCTION_TARGET_OFFLINE.byteValue()));
                            return;
                        case 6:
                            getMemoryData();
                            return;
                        default:
                            return;
                    }
                }
                this.f1826p = 0;
                return;
            case 183:
                Log.i("BtmInsSet", ByteBufferUtil.Bytes2HexString(command));
                this.f1817g.onNotify(this.f1815e, this.f1816f, BtmProfile.ACTION_BTM_CALLBACK, "收到响应");
                if (this.f1818h) {
                    m978b();
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void identify() {
        Log.p("BtmInsSet", Level.INFO, "identify", new Object[0]);
        this.f1821k = 1;
        Calendar instance = Calendar.getInstance();
        instance.setTimeZone(TimeZone.getDefault());
        int i = instance.get(1) - 2000;
        int i2 = instance.get(2) + 1;
        int i3 = instance.get(5);
        int i4 = instance.get(11);
        int i5 = instance.get(12);
        this.f1814d.sendData(this.f1815e, new byte[]{(byte) -48, (byte) -96, (byte) i, (byte) i2, (byte) i3, (byte) i4, (byte) i5, m977b(r5)});
        this.f1818h = true;
    }

    public void setMeasuringTarget(byte target) {
        Log.p("BtmInsSet", Level.INFO, "setMeasuringTarget", new Object[]{Byte.valueOf(target)});
        this.f1821k = 4;
        this.f1820j.edit().putInt("measure", target).apply();
        this.f1814d.sendData(this.f1815e, new byte[]{(byte) -48, (byte) -93, target, m977b(r0)});
    }

    public void setOfflineTarget(byte target) {
        Log.p("BtmInsSet", Level.INFO, "setOfflineTarget", new Object[]{Byte.valueOf(target)});
        this.f1821k = 5;
        this.f1820j.edit().putInt("function", target).apply();
        this.f1814d.sendData(this.f1815e, new byte[]{(byte) -48, (byte) -92, target, m977b(r0)});
    }

    public void setStandbyTime(int hour, int minute, int second) {
        Log.p("BtmInsSet", Level.INFO, "setStandbyTime", new Object[]{Integer.valueOf(hour), Integer.valueOf(minute), Integer.valueOf(second)});
        this.f1821k = 2;
        this.f1820j.edit().putInt("hour", hour).apply();
        this.f1820j.edit().putInt("minute", minute).apply();
        this.f1820j.edit().putInt("second", second).apply();
        this.f1814d.sendData(this.f1815e, new byte[]{(byte) -48, (byte) -95, (byte) hour, (byte) minute, (byte) second, m977b(r0)});
    }

    public void setTemperatureUnit(byte unit) {
        Log.p("BtmInsSet", Level.INFO, "setTemperatureUnit", new Object[]{Byte.valueOf(unit)});
        this.f1821k = 3;
        this.f1820j.edit().putInt(AmProfile.GET_USER_UNIT_AM, unit).apply();
        this.f1814d.sendData(this.f1815e, new byte[]{(byte) -48, (byte) -94, unit, m977b(r0)});
    }
}
