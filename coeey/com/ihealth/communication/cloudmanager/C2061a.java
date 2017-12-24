package com.ihealth.communication.cloudmanager;

class C2061a implements Runnable {
    final /* synthetic */ String f988a;
    final /* synthetic */ String f989b;
    final /* synthetic */ String f990c;
    final /* synthetic */ iHealthDeviceCloudManager f991d;

    C2061a(iHealthDeviceCloudManager com_ihealth_communication_cloudmanager_iHealthDeviceCloudManager, String str, String str2, String str3) {
        this.f991d = com_ihealth_communication_cloudmanager_iHealthDeviceCloudManager;
        this.f988a = str;
        this.f989b = str2;
        this.f990c = str3;
    }

    public void run() {
        this.f991d.m460a(this.f988a, this.f989b, this.f990c);
    }
}
