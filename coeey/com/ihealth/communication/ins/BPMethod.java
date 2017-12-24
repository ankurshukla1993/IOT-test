package com.ihealth.communication.ins;

import com.ihealth.communication.control.BpProfile;
import com.ihealth.communication.utils.ByteBufferUtil;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BPMethod {
    static byte[] m913a(byte[] bArr) {
        int i = (((bArr[1] & 255) << 8) | (bArr[2] & 255)) + 4;
        Object obj = new byte[i];
        System.arraycopy(bArr, 0, obj, 0, i);
        return obj;
    }

    static JSONObject m914b(byte[] bArr) {
        Exception e;
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        try {
            Object bytesCuttForProductProtocol = ByteBufferUtil.bytesCuttForProductProtocol(7, bArr);
            Object obj = new byte[16];
            Object obj2 = new byte[16];
            Object obj3 = new byte[3];
            Object obj4 = new byte[3];
            Object obj5 = new byte[16];
            Object obj6 = new byte[16];
            Object obj7 = new byte[16];
            Object obj8 = new byte[12];
            System.arraycopy(bytesCuttForProductProtocol, 0, obj, 0, obj.length);
            int i2 = 0;
            while (i2 < obj.length) {
                if (obj[i2] == (byte) 0) {
                    break;
                }
                i2++;
            }
            i2 = 0;
            jSONObject.put(BpProfile.PROTOCOL_VERSION_BPM1, new String(Arrays.copyOf(obj, i2), "UTF-8"));
            System.arraycopy(bytesCuttForProductProtocol, 16, obj2, 0, obj2.length);
            int i3 = 0;
            while (i3 < obj2.length) {
                if (obj2[i3] == (byte) 0) {
                    break;
                }
                i3++;
            }
            i3 = i2;
            jSONObject.put(BpProfile.ACCESSORY_NAME_BPM1, new String(Arrays.copyOf(obj2, i3), "UTF-8"));
            System.arraycopy(bytesCuttForProductProtocol, 32, obj3, 0, obj3.length);
            jSONObject.put(BpProfile.FIRMWARE_BPM1, Arrays.toString(obj3));
            System.arraycopy(bytesCuttForProductProtocol, 35, obj4, 0, obj4.length);
            jSONObject.put(BpProfile.HARDWARE_BPM1, Arrays.toString(obj4));
            System.arraycopy(bytesCuttForProductProtocol, 38, obj5, 0, obj5.length);
            i2 = 0;
            while (i2 < obj5.length) {
                if (obj5[i2] == (byte) 0) {
                    break;
                }
                i2++;
            }
            i2 = i3;
            jSONObject.put(BpProfile.MANUFACTURER_BPM1, new String(Arrays.copyOf(obj5, i2), "UTF-8"));
            System.arraycopy(bytesCuttForProductProtocol, 54, obj6, 0, obj6.length);
            i3 = 0;
            while (i3 < obj6.length) {
                if (obj6[i3] == (byte) 0) {
                    break;
                }
                i3++;
            }
            i3 = i2;
            jSONObject.put(BpProfile.MODEL_NUMBER_BPM1, new String(Arrays.copyOf(obj6, i3), "UTF-8"));
            System.arraycopy(bytesCuttForProductProtocol, 70, obj7, 0, obj7.length);
            while (i < obj7.length) {
                if (obj7[i] == (byte) 0) {
                    break;
                }
                i++;
            }
            i = i3;
            jSONObject.put(BpProfile.SERIAL_NUMBER_BPM1, new String(Arrays.copyOf(obj7, i), "UTF-8"));
            System.arraycopy(bytesCuttForProductProtocol, 86, obj8, 0, obj8.length);
            jSONObject.put(BpProfile.MAC_BPM1, new String(obj8, "UTF-8"));
            return jSONObject;
        } catch (UnsupportedEncodingException e2) {
            e = e2;
            e.printStackTrace();
            return jSONObject;
        } catch (JSONException e3) {
            e = e3;
            e.printStackTrace();
            return jSONObject;
        }
    }

    static JSONArray m915c(byte[] bArr) {
        byte[] bytesCuttForProductProtocol = ByteBufferUtil.bytesCuttForProductProtocol(7, bArr);
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject();
            List arrayList = new ArrayList();
            int i = 1;
            int i2 = 0;
            while (i < bytesCuttForProductProtocol.length) {
                JSONObject jSONObject2;
                byte b = bytesCuttForProductProtocol[i];
                if (b != (byte) 0) {
                    arrayList.add(Byte.valueOf(b));
                    jSONObject2 = jSONObject;
                } else {
                    JSONObject jSONObject3;
                    int i3 = i2 + 1;
                    byte[] bArr2 = new byte[arrayList.size()];
                    for (int i4 = 0; i4 < bArr2.length; i4++) {
                        bArr2[i4] = ((Byte) arrayList.get(i4)).byteValue();
                    }
                    if (i3 % 4 == 1) {
                        if (i3 > 1) {
                            jSONObject = new JSONObject();
                        }
                        jSONObject.put(BpProfile.ROUTER_SSID_BPM1, new String(bArr2, "UTF-8"));
                        jSONObject3 = jSONObject;
                    } else if (i3 % 4 == 2) {
                        jSONObject.put(BpProfile.ROUTER_CHANNEL_BPM1, new String(bArr2, "UTF-8"));
                        jSONObject3 = jSONObject;
                    } else if (i3 % 4 == 3) {
                        jSONObject.put(BpProfile.ROUTER_SECURITY_BPM1, new String(bArr2, "UTF-8"));
                        jSONObject3 = jSONObject;
                    } else {
                        if (i3 % 4 == 0) {
                            jSONObject.put(BpProfile.ROUTER_RSSI_BPM1, new String(bArr2, "UTF-8"));
                            jSONArray.put(jSONObject);
                        }
                        jSONObject3 = jSONObject;
                    }
                    arrayList.clear();
                    int i5 = i3;
                    jSONObject2 = jSONObject3;
                    i2 = i5;
                }
                i++;
                jSONObject = jSONObject2;
            }
            return jSONArray;
        } catch (Exception e) {
            e.printStackTrace();
            return jSONArray;
        }
    }

    static int m916d(byte[] bArr) {
        byte[] bytesCuttForProductProtocol = ByteBufferUtil.bytesCuttForProductProtocol(7, bArr);
        try {
            int i = bytesCuttForProductProtocol[0] & 255;
            int i2 = bytesCuttForProductProtocol[1] & 255;
            return i == 0 ? -1 : i2 == 0 ? 0 : i2 == 1 ? 1 : i2 == 2 ? 2 : i2 == 3 ? 3 : -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    static int m917e(byte[] bArr) {
        return ByteBufferUtil.bytesCuttForProductProtocol(7, bArr)[0] & 255;
    }
}
