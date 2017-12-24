package com.lifesense.ble;

import com.lifesense.ble.p003a.C2221i;
import com.lifesense.ble.p003a.C2228p;
import com.lifesense.ble.p003a.ae;
import java.util.List;

class C2257d implements C2228p {
    final /* synthetic */ LsBleManager f2455a;

    C2257d(LsBleManager lsBleManager) {
        this.f2455a = lsBleManager;
    }

    public void mo5581a(String str, String str2, byte[] bArr) {
        List a = C2221i.m1605a(bArr);
        this.f2455a.tempProtocolType = C2221i.m1603a(a);
        if (this.f2455a.tempProtocolType != null && this.f2455a.tempProtocolType.length() > 0) {
            String a2 = C2221i.m1604a(bArr, this.f2455a.tempProtocolType);
            if (a2 != null && a2.length() > 1) {
                this.f2455a.addAddressToMap(a2, str2, this.f2455a.tempProtocolType);
                char charAt = a2.charAt(0);
                if (this.f2455a.getLsBleManagerStatus() == ManagerStatus.DEVICE_SEARCH) {
                    this.f2455a.handleScanResultsForSearchMode(charAt, a, a2, this.f2455a.tempProtocolType, bArr);
                } else if (this.f2455a.getLsBleManagerStatus() == ManagerStatus.DATA_RECEIVE && charAt == '0') {
                    this.f2455a.handleScanResultsForDataUploadMode(a2, this.f2455a.tempProtocolType);
                } else if (this.f2455a.getLsBleManagerStatus() == ManagerStatus.DATA_RECEIVE && this.f2455a.tempProtocolType.equals(ae.A4.toString())) {
                    this.f2455a.handleScanResultsForDataUploadMode(a2, this.f2455a.tempProtocolType);
                }
            }
        }
    }
}
