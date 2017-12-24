package com.ihealth.communication.cloudmanager;

import com.ihealth.communication.cloud.CommCloudCenter;
import com.ihealth.communication.cloud.UserCheckSDK;

class C2065e extends Thread {
    final /* synthetic */ C2064d f1000a;

    C2065e(C2064d c2064d) {
        this.f1000a = c2064d;
    }

    public void run() {
        try {
            int entry = UserCheckSDK.entry(this.f1000a.f999b.f993b, this.f1000a.f999b.f994c, this.f1000a.f999b.f995d, this.f1000a.f998a, "OpenApiWeight", new CommCloudCenter(this.f1000a.f999b.f993b).ServiceHostListByCountry_get());
            new C2066f(this, entry).start();
            if (entry != 1 && entry != 2 && entry != 3) {
            }
        } catch (Exception e) {
            this.f1000a.f999b.f996e.f1004b.onUserStatus(this.f1000a.f999b.f992a, 8);
        }
    }
}
