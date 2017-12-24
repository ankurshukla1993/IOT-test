package com.lifesense.ble.p003a.p004a;

import android.support.v4.view.InputDeviceCompat;
import java.text.DecimalFormat;

public class ab {
    public String f2173a = "";
    public String f2174b = "";
    public String f2175c = "";
    public float f2176d = 0.0f;
    public float f2177e = 0.0f;
    public int f2178f = 0;
    public int f2179g = 0;
    public boolean f2180h = false;
    DecimalFormat f2181i = new DecimalFormat("#.00");
    private String f2182j = null;

    public ab(String str) {
        try {
            this.f2182j = str;
            this.f2174b = ac.m1294a(this.f2182j, 0, 0);
            this.f2175c = ac.m1294a(this.f2182j, 1, 1);
            this.f2176d = (float) (Math.pow(10.0d, (double) (Integer.parseInt(ac.m1294a(this.f2182j, 2, 2), 16) + InputDeviceCompat.SOURCE_ANY)) * ((double) Integer.parseInt(ac.m1294a(this.f2182j, 3, 5), 16)));
            this.f2176d = (float) Double.parseDouble(this.f2181i.format((double) this.f2176d));
            this.f2177e = (float) (Math.pow(10.0d, (double) (Integer.parseInt(ac.m1294a(this.f2182j, 6, 6), 16) + InputDeviceCompat.SOURCE_ANY)) * ((double) Integer.parseInt(ac.m1294a(this.f2182j, 7, 9), 16)));
            this.f2177e = (float) Double.parseDouble(this.f2181i.format((double) this.f2177e));
            this.f2178f = Integer.parseInt(ac.m1294a(this.f2182j, 10, 10), 16);
            if (this.f2178f == 1) {
                this.f2179g = Integer.parseInt(ac.m1294a(this.f2182j, 11, 14), 16);
            }
        } catch (Exception e) {
            this.f2180h = true;
            e.printStackTrace();
        }
    }

    public String toString() {
        return "CmdPedometerCB [msg=" + this.f2182j + ", CMD=" + this.f2173a + ", PushCmd=" + this.f2174b + ", DeleteData=" + this.f2175c + ", Weight=" + this.f2176d + ", Height=" + this.f2177e + ", TargetState=" + this.f2178f + ", WeekTargetStep=" + this.f2179g + ", IsError=" + this.f2180h + ", df=" + this.f2181i + "]\n\n";
    }
}
