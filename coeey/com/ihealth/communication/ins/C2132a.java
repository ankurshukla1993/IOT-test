package com.ihealth.communication.ins;

import android.text.TextUtils;
import com.ihealth.communication.control.BpProfile;
import com.ihealth.communication.utils.ByteBufferUtil;
import org.json.JSONException;
import org.json.JSONObject;

class C2132a {
    private static byte m1032a(byte[] bArr) {
        int i = 0;
        for (int i2 = 3; i2 < bArr.length - 1; i2++) {
            i += bArr[i2];
        }
        return (byte) i;
    }

    static byte[] m1033a() {
        byte[] intToBytes4 = ByteBufferUtil.intToBytes4((int) (System.currentTimeMillis() / 1000));
        r1 = new byte[12];
        byte[] intToBytes2 = ByteBufferUtil.intToBytes2(8);
        r1[0] = (byte) -64;
        r1[1] = intToBytes2[0];
        r1[2] = intToBytes2[1];
        r1[3] = (byte) 0;
        r1[4] = (byte) 0;
        r1[5] = (byte) -1;
        r1[6] = (byte) -32;
        r1[7] = intToBytes4[0];
        r1[8] = intToBytes4[1];
        r1[9] = intToBytes4[2];
        r1[10] = intToBytes4[3];
        r1[11] = C2132a.m1032a(r1);
        return r1;
    }

    static byte[] m1034a(int i) {
        r0 = new byte[10];
        byte[] intToBytes2 = ByteBufferUtil.intToBytes2(6);
        r0[0] = (byte) -64;
        r0[1] = intToBytes2[0];
        r0[2] = intToBytes2[1];
        r0[3] = (byte) 0;
        r0[4] = (byte) 0;
        r0[5] = (byte) -1;
        r0[6] = (byte) -29;
        r0[7] = (byte) 1;
        r0[8] = (byte) i;
        r0[9] = C2132a.m1032a(r0);
        return r0;
    }

    static byte[] m1035a(JSONObject jSONObject) {
        int i = 0;
        try {
            Object bytes = jSONObject.getString(BpProfile.ROUTER_SSID_BPM1).getBytes();
            byte[] bytes2 = !TextUtils.isEmpty(jSONObject.getString(BpProfile.ROUTER_PWD_BPM1)) ? jSONObject.getString(BpProfile.ROUTER_PWD_BPM1).getBytes() : new byte[0];
            byte[] bytes3 = jSONObject.getString(BpProfile.ROUTER_CHANNEL_BPM1).getBytes();
            byte[] bytes4 = jSONObject.getString(BpProfile.ROUTER_SECURITY_BPM1).getBytes();
            byte[] bytes5 = jSONObject.getString(BpProfile.ROUTER_URL_BPM1).getBytes();
            byte[] bytes6 = jSONObject.getString(BpProfile.ROUTER_PID_BPM1).getBytes();
            int length = (((((((((((bytes.length + 1) + bytes2.length) + 1) + bytes3.length) + 1) + bytes4.length) + 1) + bytes5.length) + 1) + bytes6.length) + 1) + 4;
            byte[] bArr = new byte[(length + 4)];
            byte[] intToBytes2 = ByteBufferUtil.intToBytes2(length);
            bArr[0] = (byte) -64;
            bArr[1] = intToBytes2[0];
            bArr[2] = intToBytes2[1];
            bArr[3] = (byte) 0;
            bArr[4] = (byte) 0;
            bArr[5] = (byte) -1;
            bArr[6] = (byte) -30;
            System.arraycopy(bytes, 0, bArr, 7, bytes.length);
            bArr[bytes.length + 7] = (byte) 0;
            if (bytes2.length != 0) {
                for (length = 0; length < bytes2.length; length++) {
                    bArr[((length + 7) + bytes.length) + 1] = bytes2[length];
                }
            }
            bArr[(bytes.length + 8) + bytes2.length] = (byte) 0;
            for (length = 0; length < bytes3.length; length++) {
                bArr[(((length + 8) + bytes.length) + bytes2.length) + 1] = bytes3[length];
            }
            bArr[((bytes.length + 9) + bytes2.length) + bytes3.length] = (byte) 0;
            for (length = 0; length < bytes4.length; length++) {
                bArr[((((length + 9) + bytes.length) + bytes2.length) + bytes3.length) + 1] = bytes4[length];
            }
            bArr[(((bytes.length + 10) + bytes2.length) + bytes3.length) + bytes4.length] = (byte) 0;
            for (length = 0; length < bytes5.length; length++) {
                bArr[(((((length + 10) + bytes.length) + bytes2.length) + bytes3.length) + bytes4.length) + 1] = bytes5[length];
            }
            bArr[((((bytes.length + 11) + bytes2.length) + bytes3.length) + bytes4.length) + bytes5.length] = (byte) 0;
            while (i < bytes6.length) {
                bArr[((((((i + 11) + bytes.length) + bytes2.length) + bytes3.length) + bytes4.length) + bytes5.length) + 1] = bytes6[i];
                i++;
            }
            bArr[(((((bytes.length + 12) + bytes2.length) + bytes3.length) + bytes4.length) + bytes5.length) + bytes6.length] = (byte) 0;
            bArr[bArr.length - 1] = C2132a.m1032a(bArr);
            return bArr;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    static byte[] m1036b() {
        r0 = new byte[8];
        byte[] intToBytes2 = ByteBufferUtil.intToBytes2(4);
        r0[0] = (byte) -64;
        r0[1] = intToBytes2[0];
        r0[2] = intToBytes2[1];
        r0[3] = (byte) 0;
        r0[4] = (byte) 0;
        r0[5] = (byte) -1;
        r0[6] = (byte) -31;
        r0[7] = C2132a.m1032a(r0);
        return r0;
    }
}
