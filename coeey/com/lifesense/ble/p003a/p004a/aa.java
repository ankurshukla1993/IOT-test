package com.lifesense.ble.p003a.p004a;

public class aa {
    public String f2165a = "";
    public String f2166b = "";
    public String f2167c = "";
    public String f2168d = "";
    public String f2169e = "";
    public String f2170f = "";
    public boolean f2171g = false;
    private String f2172h = null;

    public aa(String str) {
        try {
            this.f2172h = new StringBuilder(String.valueOf(str)).append("C7").toString();
            this.f2165a = ac.m1294a(this.f2172h, 0, 0);
            this.f2166b = ac.m1294a(this.f2172h, 1, 6);
            this.f2167c = m1290a(ac.m1294a(this.f2172h, 7, 11));
            this.f2168d = m1290a(ac.m1294a(this.f2172h, 12, 15));
            this.f2169e = m1290a(ac.m1294a(this.f2172h, 16, 19));
            this.f2170f = ac.m1294a(this.f2172h, 20, 20);
        } catch (Exception e) {
            this.f2171g = true;
        }
    }

    public String m1290a(String str) {
        String str2 = "";
        for (int i = 0; i < str.length(); i += 2) {
            str2 = new StringBuilder(String.valueOf(str2)).append((char) Integer.parseInt(str.substring(i, i + 2), 16)).toString();
        }
        return str2;
    }

    public String toString() {
        return "CmdPedometerC7 [msg=" + this.f2172h + ", CMD=" + this.f2165a + ", MAC=" + this.f2166b + ", Model=" + this.f2167c + ", SoftwareVer=" + this.f2168d + ", HardwareVer=" + this.f2169e + ", TimeZone=" + this.f2170f + ", IsError=" + this.f2171g + "]\n\n";
    }
}
