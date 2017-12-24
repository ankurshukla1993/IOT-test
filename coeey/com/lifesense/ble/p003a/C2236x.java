package com.lifesense.ble.p003a;

import android.util.Log;
import com.lifesense.ble.DeviceConnectState;
import com.lifesense.ble.bean.C2242b;
import com.lifesense.ble.bean.C2248h;
import com.lifesense.ble.bean.WeightUserInfo;
import com.lifesense.ble.commom.C2255a;
import java.util.UUID;

class C2236x implements C2222j {
    final /* synthetic */ C2235w f2403a;

    C2236x(C2235w c2235w) {
        this.f2403a = c2235w;
    }

    public void mo5575a() {
        if (this.f2403a.f2377a == ac.PAIR_DEVICE_STATUS && this.f2403a.f2401y == ag.OPERATING_RECEIVE_PASSWORD) {
            C2220h.m1596a((Object) this, "try to reconnect pair device......", 3);
            this.f2403a.f2377a = ac.FREE_STATUS;
            this.f2403a.m1802a(this.f2403a.f2390n, this.f2403a.f2387k, this.f2403a.f2360A);
        } else if (this.f2403a.f2401y == ag.OPERATING_PAIRED_RESULTS_PROCESS || this.f2403a.f2401y == ag.OPERATING_UPLOADED_RESULTS_PROCESS) {
            if (this.f2403a.f2379c != null) {
                this.f2403a.f2379c.mo5582a(DeviceConnectState.DISCONNECTED);
            }
            this.f2403a.m1723a(this.f2403a.f2401y);
        } else {
            C2220h.m1596a((Object) this, "Error!Abnormal disconnect..." + this.f2403a.m1791r(), 2);
            this.f2403a.m1780l();
        }
    }

    public void mo5576a(C2248h c2248h) {
        this.f2403a.f2391o = c2248h;
        if (this.f2403a.f2390n.getDeviceType() != null) {
            this.f2403a.f2388l = C2221i.m1606a(this.f2403a.f2390n.getDeviceType(), c2248h);
            if (this.f2403a.f2388l != null) {
                this.f2403a.f2396t = C2221i.m1602a(this.f2403a.f2388l);
                if (this.f2403a.f2396t != null) {
                    this.f2403a.f2390n.setProtocolType(this.f2403a.f2396t.toString());
                }
                C2220h.m1596a((Object) this, "current discovered service-" + this.f2403a.f2388l + " ;protocol type-" + this.f2403a.f2396t, 3);
                this.f2403a.m1723a(this.f2403a.m1773i());
                return;
            }
            this.f2403a.m1780l();
            return;
        }
        this.f2403a.m1780l();
    }

    public void mo5577a(UUID uuid, UUID uuid2, byte[] bArr) {
        if (bArr[0] == (byte) -96) {
            C2220h.m1596a((Object) this, "receive push data(0xa0) password—" + C2221i.m1614e(bArr), 3);
            this.f2403a.m1758c(bArr);
        } else if (bArr[0] == (byte) -95) {
            C2220h.m1596a((Object) this, "receive push data(0xa1) random—" + C2221i.m1614e(bArr), 3);
            this.f2403a.m1750b(bArr);
        } else if (bArr[0] == (byte) -125) {
            C2220h.m1596a((Object) this, "receive push data(0x83) user list—" + C2221i.m1614e(bArr), 3);
            this.f2403a.m1763d(bArr);
        } else if (bArr[0] == (byte) -64) {
            C2220h.m1596a((Object) this, "receive push data(0xc0) user info—" + C2221i.m1614e(bArr), 3);
            WeightUserInfo f = C2255a.m1909f(bArr);
            if (this.f2403a.f2379c != null) {
                this.f2403a.f2379c.mo5589a(f);
            }
        } else if (uuid.equals(C2242b.PEDOMETER_SERVICE_UUID_A4)) {
            C2220h.m1596a((Object) this, "a4 device command request is being uploaded.", 3);
            this.f2403a.m1766e(bArr);
        } else {
            C2220h.m1596a((Object) this, "measurement data is being uploaded.", 3);
            this.f2403a.m1749b(uuid, uuid2, bArr);
        }
    }

    public void mo5578b() {
        if (this.f2403a.f2362C < 3) {
            C2220h.m1596a((Object) this, "try to reconnect device with count:" + this.f2403a.f2362C, 3);
            if (this.f2403a.f2401y == ag.OPERATING_CONNECT_DEVICE) {
                this.f2403a.m1723a(this.f2403a.f2401y);
                return;
            } else {
                this.f2403a.m1780l();
                return;
            }
        }
        if (this.f2403a.f2379c != null) {
            this.f2403a.f2379c.mo5582a(DeviceConnectState.CONNECTED_FAILED);
        }
        this.f2403a.m1780l();
    }

    public void mo5579b(UUID uuid, UUID uuid2, byte[] bArr) {
        if (this.f2403a.f2401y == ag.OPERATING_READ_DEVICE_INFO) {
            this.f2403a.m1738a(uuid, uuid2, bArr);
            this.f2403a.m1790q();
        }
    }

    public void mo5580c(UUID uuid, UUID uuid2, byte[] bArr) {
        C2220h.m1596a((Object) this, "write success with status—" + this.f2403a.m1791r(), 3);
        if (!uuid.equals(C2242b.PEDOMETER_SERVICE_UUID_A4)) {
            this.f2403a.m1771h();
        } else if (this.f2403a.f2401y != ag.OPERATING_WRITE_INIT_RESPONSE) {
            Log.e("当前回复命令", this.f2403a.f2401y.toString());
        } else if (this.f2403a.f2364E) {
            this.f2403a.f2364E = false;
            this.f2403a.m1723a(this.f2403a.m1773i());
        }
    }
}
