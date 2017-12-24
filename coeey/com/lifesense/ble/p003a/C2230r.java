package com.lifesense.ble.p003a;

import com.lifesense.ble.DeviceConnectState;
import com.lifesense.ble.bean.C2242b;
import com.lifesense.ble.bean.C2248h;
import java.util.UUID;

class C2230r implements C2222j {
    final /* synthetic */ C2229q f2350a;

    C2230r(C2229q c2229q) {
        this.f2350a = c2229q;
    }

    public void mo5575a() {
        if (this.f2350a.f2325b == C2233u.DATA_UPLOAD_STATUS && this.f2350a.f2337n == ag.OPERATING_SET_NOTIFY_FOR_CHARACTERISTICS) {
            C2220h.m1596a((Object) this, "try to reconnect upload device......", 3);
            this.f2350a.f2325b = C2233u.FREE_STATUS;
            this.f2350a.m1699a(this.f2350a.f2331h, this.f2350a.f2328e, this.f2350a.f2339p);
        } else if (this.f2350a.f2337n == ag.OPERATING_UPLOADED_RESULTS_PROCESS) {
            if (this.f2350a.f2324a != null) {
                this.f2350a.f2324a.mo5593a(DeviceConnectState.DISCONNECTED, this.f2350a.f2331h.getBroadcastID());
            }
            this.f2350a.m1639a(this.f2350a.f2337n);
        } else {
            C2220h.m1596a((Object) this, "Error!Abnormal disconnect..." + this.f2350a.m1679i(), 2);
            this.f2350a.m1672e();
        }
    }

    public void mo5576a(C2248h c2248h) {
        this.f2350a.f2332i = c2248h;
        if (this.f2350a.f2331h.getDeviceType() != null) {
            this.f2350a.f2329f = C2221i.m1606a(this.f2350a.f2331h.getDeviceType(), c2248h);
            if (this.f2350a.f2329f != null) {
                this.f2350a.f2335l = C2221i.m1602a(this.f2350a.f2329f);
                if (this.f2350a.f2335l != null) {
                    this.f2350a.f2331h.setProtocolType(this.f2350a.f2335l.toString());
                }
                C2220h.m1596a((Object) this, "current discovered service-" + this.f2350a.f2329f + " ;protocol type-" + this.f2350a.f2335l, 3);
                this.f2350a.m1639a(this.f2350a.m1667d());
                return;
            }
            this.f2350a.m1672e();
            return;
        }
        this.f2350a.m1672e();
    }

    public void mo5577a(UUID uuid, UUID uuid2, byte[] bArr) {
        if (uuid.equals(C2242b.PEDOMETER_SERVICE_UUID_A4)) {
            this.f2350a.f2320F = DeviceConnectState.CONNECTED_SUCCESS;
            C2220h.m1596a((Object) this, "a4 device command request is being uploaded.", 3);
            this.f2350a.m1661b(bArr);
        }
    }

    public void mo5578b() {
        if (this.f2350a.f2341r < 3) {
            C2220h.m1596a((Object) this, "try to reconnect device with count:" + this.f2350a.f2341r, 3);
            if (this.f2350a.f2337n == ag.OPERATING_CONNECT_DEVICE) {
                this.f2350a.m1639a(this.f2350a.f2337n);
                return;
            } else {
                this.f2350a.m1672e();
                return;
            }
        }
        if (this.f2350a.f2324a != null) {
            this.f2350a.f2324a.mo5593a(DeviceConnectState.CONNECTED_FAILED, this.f2350a.f2331h.getBroadcastID());
        }
        this.f2350a.m1672e();
    }

    public void mo5579b(UUID uuid, UUID uuid2, byte[] bArr) {
        if (this.f2350a.f2337n == ag.OPERATING_READ_DEVICE_INFO) {
            this.f2350a.m1654a(uuid, uuid2, bArr);
            this.f2350a.m1677h();
        }
    }

    public void mo5580c(UUID uuid, UUID uuid2, byte[] bArr) {
        C2220h.m1596a((Object) this, "write success with status—" + this.f2350a.m1679i(), 3);
        if (uuid.equals(C2242b.PEDOMETER_SERVICE_UUID_A4) && this.f2350a.f2337n == ag.OPERATING_WRITE_INIT_RESPONSE && this.f2350a.f2343t) {
            this.f2350a.f2343t = false;
            this.f2350a.m1639a(this.f2350a.m1667d());
        }
    }
}
