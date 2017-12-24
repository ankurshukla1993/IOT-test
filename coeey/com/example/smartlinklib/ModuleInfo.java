package com.example.smartlinklib;

public class ModuleInfo {
    private String ModuleIP;
    private String mac;
    private String mid;

    public String getMac() {
        return this.mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getModuleIP() {
        return this.ModuleIP;
    }

    public void setModuleIP(String moduleIP) {
        this.ModuleIP = moduleIP;
    }

    public void setMid(String string) {
        this.mid = string;
    }

    public String getMid() {
        return this.mid;
    }
}
