package com.dnurse.exttestlib;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.util.Log;
import android.util.SparseArray;
import com.dnurse.DnurseDevTestDef.DnurseConstant;
import com.dnurse.DnurseDevTestDef.IMeasureDataResultCallback;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.common.ConnectionResult;
import com.google.common.primitives.Shorts;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Timer;
import kotlin.jvm.internal.ShortCompanionObject;
import tw.com.prolific.driver.pl2303.PL2303Driver;

public class DnurseDeviceTest {
    private static SimpleDateFormat f115R = null;
    private byte f116A = (byte) 0;
    private boolean f117B = true;
    private byte f118C;
    private byte f119D;
    private int f120E;
    private String f121F = "";
    private boolean f122G = false;
    private boolean f123H = false;
    private byte f124I;
    private byte f125J;
    private byte f126K;
    private C1050b f127L;
    private C1048a f128M;
    private Context f129N;
    private IMeasureDataResultCallback f130O;
    private Handler f131P = new Handler();
    private Timer f132Q;
    private float f133S = 0.0f;
    private float f134T = 0.0f;
    private BroadcastReceiver f135U = new C1051a(this);
    Runnable f136a = new C1052b(this);
    Runnable f137b = new C1053c(this);
    Runnable f138c = new C1054d(this);
    Runnable f139d = new C1055e(this);
    private final byte f140e = (byte) 0;
    private final byte f141f = (byte) 1;
    private final byte f142g = (byte) 2;
    private final byte f143h = (byte) 3;
    private final byte f144i = (byte) 0;
    private final byte f145j = (byte) 1;
    private int f146k = 0;
    private float f147l;
    private int[][] f148m = new int[][]{new int[]{4, 7, 11, 16, 11, 20, 1}, new int[]{7, 12, 17, 22, 19, 20, 2}, new int[]{10, 16, 23, 30, 19, 20, 3}};
    private int f149n = 0;
    private byte f150o = (byte) 0;
    private int f151p = 20;
    private int f152q = 0;
    private int f153r = 10;
    private int f154s = PL2303Driver.BAUD1800;
    private int f155t = 20;
    private int f156u = 10;
    private int f157v = 10;
    private boolean f158w = false;
    private boolean f159x = false;
    private boolean f160y = true;
    private boolean f161z = true;

    private class C1048a {
        public boolean f53a;
        final /* synthetic */ DnurseDeviceTest f54b;
        private final int f55c = 4000;
        private int f56d = -1;
        private int f57e = 0;
        private AudioManager f58f;
        private AudioTrack f59g;
        private final short[] f60h = new short[]{(short) 0, (short) 0, Shorts.MAX_POWER_OF_TWO, (short) -16384, (short) 26624, (short) -26624, (short) 31744, (short) -31744, ShortCompanionObject.MAX_VALUE, ShortCompanionObject.MIN_VALUE, (short) 31744, (short) -31744, (short) 26624, (short) -26624, Shorts.MAX_POWER_OF_TWO, (short) -16384, (short) 0, (short) 0, (short) -16384, Shorts.MAX_POWER_OF_TWO, (short) -26624, (short) 26624, (short) -31744, (short) 31744, ShortCompanionObject.MIN_VALUE, ShortCompanionObject.MAX_VALUE, (short) -31744, (short) 31744, (short) -26624, (short) 26624, (short) -16384, Shorts.MAX_POWER_OF_TWO};
        private final short[] f61i = new short[]{(short) 0, (short) 0, (short) 31744, (short) -31744, ShortCompanionObject.MAX_VALUE, ShortCompanionObject.MIN_VALUE, (short) 31744, (short) -31744, (short) 0, (short) 0, (short) -31744, (short) 31744, ShortCompanionObject.MIN_VALUE, ShortCompanionObject.MAX_VALUE, (short) -31744, (short) 31744, (short) 0, (short) 0, (short) 31744, (short) -31744, ShortCompanionObject.MAX_VALUE, ShortCompanionObject.MIN_VALUE, (short) 31744, (short) -31744, (short) 0, (short) 0, (short) -31744, (short) 31744, ShortCompanionObject.MIN_VALUE, ShortCompanionObject.MAX_VALUE, (short) -31744, (short) 31744};
        private final short[] f62j = new short[]{(short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0, (short) 0};

        C1048a(DnurseDeviceTest dnurseDeviceTest) {
            this.f54b = dnurseDeviceTest;
            this.f58f = (AudioManager) dnurseDeviceTest.f129N.getSystemService("audio");
            this.f57e = AudioTrack.getMinBufferSize(4000, 12, 2);
            Log.i("YinDuTest", "AudPly: min buffer=" + this.f57e);
            this.f59g = new AudioTrack(3, 4000, 12, 2, this.f57e, 1);
            if (this.f59g.getState() == 1) {
                this.f59g.setStereoVolume(AudioTrack.getMaxVolume(), AudioTrack.getMaxVolume());
                return;
            }
            this.f59g.release();
            this.f59g = null;
        }

        private boolean m12a(short[] sArr, int i, int i2) {
            if (this.f59g == null) {
                return false;
            }
            int length = ((this.f57e / this.f62j.length) / 2) + 2;
            if (this.f59g != null) {
                boolean z;
                int i3;
                boolean z2;
                short[] sArr2 = new short[(((sArr.length * i2) + (this.f62j.length * i)) + (this.f62j.length * length))];
                int i4 = 0;
                boolean z3 = false;
                while (i4 < i) {
                    z = z3;
                    i3 = 0;
                    while (i3 < this.f62j.length) {
                        z2 = z + 1;
                        sArr2[z] = this.f62j[i3];
                        i3++;
                        z = z2;
                    }
                    i4++;
                    z3 = z;
                }
                i4 = 0;
                while (i4 < i2) {
                    z = z3;
                    i3 = 0;
                    while (i3 < sArr.length) {
                        z2 = z + 1;
                        sArr2[z] = sArr[i3];
                        i3++;
                        z = z2;
                    }
                    i4++;
                    z3 = z;
                }
                i4 = 0;
                while (i4 < length) {
                    z = z3;
                    i3 = 0;
                    while (i3 < this.f62j.length) {
                        z2 = z + 1;
                        sArr2[z] = this.f62j[i3];
                        i3++;
                        z = z2;
                    }
                    i4++;
                    z3 = z;
                }
                this.f59g.flush();
                this.f59g.play();
                this.f59g.write(sArr2, 0, sArr2.length);
                if (!this.f54b.f161z) {
                    this.f59g.stop();
                }
                Log.i("YinDuTest", "AudPly: Play F:" + i + " B:" + length + " T:" + i2 + " finished!");
            }
            return true;
        }

        public void m13a(boolean z) {
            int s = this.f54b.f153r + this.f54b.f148m[this.f54b.f149n][5];
            if (z) {
                m12a(this.f61i, this.f54b.f152q, s);
            } else {
                m12a(this.f60h, this.f54b.f152q, s);
            }
        }

        public boolean m14a() {
            int i;
            int streamVolume = this.f58f.getStreamVolume(3);
            Log.i("YinDuTest", "AudPly: setMaxVolume Old=" + streamVolume);
            if (this.f56d == -1) {
                this.f56d = streamVolume;
            }
            int streamMaxVolume = this.f58f.getStreamMaxVolume(3);
            this.f58f.setStreamVolume(3, streamMaxVolume, 0);
            if (this.f54b.f160y) {
                if (streamMaxVolume != this.f58f.getStreamVolume(3)) {
                    try {
                        Thread.sleep(1000);
                        i = streamMaxVolume;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    while (i > (streamMaxVolume * 2) / 3 && i != this.f58f.getStreamVolume(3)) {
                        i--;
                        this.f58f.setStreamVolume(3, i, 0);
                    }
                    if (VERSION.RELEASE.compareTo("4.3") >= 0 && i < streamMaxVolume) {
                        this.f58f.setStreamVolume(3, streamMaxVolume, 9);
                    }
                    if (i != this.f58f.getStreamVolume(3)) {
                        return false;
                    }
                }
                i = streamMaxVolume;
                while (i > (streamMaxVolume * 2) / 3) {
                    i--;
                    this.f58f.setStreamVolume(3, i, 0);
                }
                this.f58f.setStreamVolume(3, streamMaxVolume, 9);
                if (i != this.f58f.getStreamVolume(3)) {
                    return false;
                }
            }
            i = streamMaxVolume;
            Log.i("YinDuTest", "AudPly: Vol change from " + streamVolume + " to " + i);
            this.f53a = i == streamMaxVolume;
            return m12a(this.f60h, this.f54b.f151p, 0);
        }

        public void m15b() {
            if (this.f56d != -1) {
                this.f58f.setStreamVolume(3, this.f56d, 0);
                Log.i("YinDuTest", "AudPly: Vol change to " + this.f56d);
                this.f56d = -1;
            }
        }

        public void m16c() {
            int s = this.f54b.f153r + 4;
            if (this.f54b.f150o == (byte) 1) {
                m12a(this.f61i, this.f54b.f152q, s);
            } else {
                m12a(this.f60h, this.f54b.f152q, s);
            }
        }

        public void m17d() {
            m12a(this.f60h, this.f54b.f152q, this.f54b.f153r);
        }

        public void m18e() {
            if (this.f59g != null) {
                this.f59g.flush();
                this.f59g.stop();
                Log.i("YinDuTest", "AudPly: Stop playing!");
            }
        }
    }

    private class C1050b {
        final /* synthetic */ DnurseDeviceTest f110a;
        private final int f111b = 44100;
        private AudioRecord f112c;
        private int f113d;
        private short f114e = (short) 0;

        class C1049a implements Runnable {
            private final byte f63A = (byte) 5;
            private final byte f64B = (byte) 6;
            private final byte f65C = (byte) 5;
            private final byte f66D = (byte) 6;
            private final byte f67E = (byte) 8;
            private byte f68F = (byte) 8;
            private byte[] f69G = new byte[255];
            private byte f70H = (byte) 0;
            private byte f71I = (byte) 0;
            private byte f72J = (byte) 0;
            private byte f73K = (byte) 0;
            private byte f74L = (byte) 0;
            private byte f75M = (byte) 0;
            private short f76N = (short) 0;
            private boolean f77O = false;
            private boolean f78P = false;
            private int[] f79Q = new int[9];
            private int f80R = 0;
            private int f81S = 0;
            private int f82T = 0;
            private int f83U = 0;
            final /* synthetic */ C1050b f84a;
            private short[] f85b;
            private byte[] f86c;
            private int f87d;
            private byte f88e = (byte) 0;
            private final byte[] f89f = new byte[]{Byte.MIN_VALUE, (byte) 8, (byte) -120, (byte) 0};
            private final byte[] f90g = new byte[]{Byte.MIN_VALUE, (byte) 8, (byte) -86, (byte) 85};
            private final byte[] f91h = new byte[]{(byte) -61, (byte) 60, (byte) -52, (byte) 51};
            private final byte[] f92i = new byte[]{(byte) -61, (byte) 60, (byte) -91, (byte) 90};
            private final byte f93j = (byte) 4;
            private final byte f94k = (byte) -1;
            private final byte f95l = (byte) -1;
            private final byte f96m = (byte) 0;
            private final byte f97n = (byte) 16;
            private final byte f98o = (byte) 2;
            private final byte f99p = (byte) 1;
            private final byte f100q = (byte) 3;
            private final byte f101r = (byte) 4;
            private final short f102s = (short) 6;
            private final short f103t = (short) 7;
            private final short f104u = (short) 8;
            private final byte f105v = (byte) 2;
            private final byte f106w = (byte) 4;
            private final byte f107x = (byte) 5;
            private final byte f108y = (byte) 6;
            private final byte f109z = (byte) 4;

            public C1049a(C1050b c1050b, int i) {
                this.f84a = c1050b;
                if (c1050b.f110a.f150o == (byte) 1) {
                    c1050b.f110a.f149n = 0;
                } else if (c1050b.f110a.f150o == (byte) 2) {
                    c1050b.f110a.f149n = 1;
                } else if (c1050b.f110a.f150o == (byte) 3) {
                    c1050b.f110a.f149n = 2;
                } else {
                    c1050b.f110a.f149n = 1;
                }
                this.f88e = (byte) c1050b.f110a.f148m[c1050b.f110a.f149n][4];
                if (c1050b.f110a.f116A == (byte) 0 || c1050b.f110a.f116A == (byte) 1) {
                    c1050b.f110a.f117B = true;
                } else {
                    c1050b.f110a.f117B = false;
                }
                this.f87d = i / 2;
                this.f85b = new short[this.f87d];
                this.f86c = new byte[(this.f87d * 2)];
                if (c1050b.f112c != null && c1050b.f112c.getRecordingState() == 3) {
                    c1050b.f112c.read(this.f85b, 0, this.f87d);
                }
            }

            private int m19a(short s) {
                int i = 0;
                if ((s <= (short) 0 || this.f80R <= 0) && (!(s == (short) 0 && this.f80R == 0) && (s >= (short) 0 || this.f80R >= 0))) {
                    if (this.f80R == 0 || this.f81S <= this.f84a.f110a.f148m[this.f84a.f110a.f149n][6]) {
                        this.f83U += this.f81S;
                    } else if ((this.f80R <= 0 || this.f82T <= 0) && this.f80R >= 0) {
                        i = this.f83U;
                        this.f83U = this.f81S;
                        this.f82T = this.f80R;
                    } else {
                        this.f83U += this.f81S;
                        this.f82T = this.f80R;
                    }
                    this.f80R = s;
                    this.f81S = 1;
                } else {
                    this.f81S++;
                }
                return i;
            }

            private void m20a() {
                for (int i = 0; i < this.f74L - 1; i++) {
                    this.f69G[i] = this.f69G[i + 1];
                }
                this.f74L = (byte) (this.f74L - 1);
            }

            private void m21a(int i) {
                if (this.f78P) {
                    this.f79Q[this.f70H] = i;
                    this.f76N = (short) (this.f76N >> 1);
                    if (i > this.f84a.f110a.f148m[this.f84a.f110a.f149n][1] && i <= this.f84a.f110a.f148m[this.f84a.f110a.f149n][2]) {
                        this.f76N = (short) (this.f76N | 256);
                        this.f75M = (byte) (this.f75M ^ 1);
                    } else if (i < this.f84a.f110a.f148m[this.f84a.f110a.f149n][0] || i > this.f84a.f110a.f148m[this.f84a.f110a.f149n][2]) {
                        Log.i("YinDuTest", "R: EN:" + i);
                        m25d();
                        this.f72J = (byte) 0;
                        this.f71I = (byte) 0;
                        this.f73K = (byte) 0;
                        this.f77O = false;
                        return;
                    }
                    Log.i("YinDuTest", "RN:" + i);
                    if (this.f70H >= (byte) 8) {
                        m25d();
                        if (this.f75M != (byte) 0) {
                            Log.i("YinDuTest", "R: Bit Check error!");
                            m24c();
                            return;
                        }
                        this.f76N = (short) (this.f76N & 255);
                        Log.i("YinDuTest", "R: D=" + String.format("%02X", new Object[]{Short.valueOf(this.f76N)}));
                        if (this.f84a.f110a.f146k == 1) {
                            m26e();
                            return;
                        } else {
                            m27f();
                            return;
                        }
                    }
                    this.f70H = (byte) (this.f70H + 1);
                } else if (this.f84a.f110a.f146k == 1 && this.f84a.f110a.f150o == (byte) 0 && !this.f77O) {
                    if (i > this.f84a.f110a.f148m[1][1] && i <= this.f84a.f110a.f148m[1][2]) {
                        Log.i("YinDuTest", "RB:" + i);
                        this.f72J = (byte) (this.f72J + 1);
                        this.f71I = (byte) 0;
                        this.f73K = (byte) 0;
                        if (this.f72J >= (byte) 10) {
                            Log.i("YinDuTest", "RB X");
                            this.f77O = true;
                            this.f84a.f110a.f149n = 1;
                        }
                    } else if (i > this.f84a.f110a.f148m[1][2] && i <= this.f84a.f110a.f148m[1][3]) {
                        Log.i("YinDuTest", "RB:" + i);
                        this.f71I = (byte) (this.f71I + 1);
                        this.f72J = (byte) 0;
                        this.f73K = (byte) 0;
                        if (this.f71I >= (byte) 4) {
                            Log.i("YinDuTest", "RB M");
                            this.f77O = true;
                            this.f84a.f110a.f149n = 1;
                        }
                    } else if (i <= this.f84a.f110a.f148m[2][2] || i > this.f84a.f110a.f148m[2][3]) {
                        this.f72J = (byte) 0;
                        this.f71I = (byte) 0;
                        this.f73K = (byte) 0;
                    } else {
                        Log.i("YinDuTest", "RB:" + i);
                        this.f73K = (byte) (this.f73K + 1);
                        this.f72J = (byte) 0;
                        this.f71I = (byte) 0;
                        if (this.f73K >= (byte) 4) {
                            Log.i("YinDuTest", "RB S");
                            this.f77O = true;
                            this.f84a.f110a.f149n = 2;
                        }
                    }
                    if (this.f77O) {
                        this.f70H = (byte) 0;
                    }
                } else if (i >= this.f84a.f110a.f148m[this.f84a.f110a.f149n][0] && i <= this.f84a.f110a.f148m[this.f84a.f110a.f149n][1]) {
                    this.f70H = (byte) (this.f70H + 1);
                } else if (i <= this.f84a.f110a.f148m[this.f84a.f110a.f149n][1] || i > this.f84a.f110a.f148m[this.f84a.f110a.f149n][2] || this.f70H < (byte) 1 || this.f70H > (byte) 5) {
                    this.f70H = (byte) 0;
                    if (i < this.f84a.f110a.f148m[this.f84a.f110a.f149n][0] || i > this.f84a.f110a.f148m[this.f84a.f110a.f149n][3]) {
                        this.f72J = (byte) 0;
                        this.f71I = (byte) 0;
                        this.f73K = (byte) 0;
                        this.f77O = false;
                    }
                } else {
                    Log.i("YinDuTest", "RS:" + i);
                    this.f78P = true;
                    this.f70H = (byte) 0;
                    this.f76N = (short) 0;
                    this.f75M = (byte) 0;
                }
            }

            private void m22b() {
                byte b = (byte) 1;
                while (b < this.f74L && this.f69G[b] != (byte) -1) {
                    b++;
                }
                byte b2 = b;
                int i = 0;
                while (b2 < this.f74L) {
                    int i2 = i + 1;
                    this.f69G[i] = this.f69G[b2];
                    b2++;
                    i = i2;
                }
                this.f74L = (byte) i;
            }

            private void m23b(int i) {
                for (int i2 = 0; i2 < i; i2 += 2) {
                    int i3 = (short) ((this.f85b[i2] + this.f85b[i2 + 1]) / 2);
                    this.f84a.f114e = (short) (this.f84a.f114e | i3);
                    this.f86c[i2] = (byte) (i3 & 255);
                    this.f86c[i2 + 1] = (byte) (i3 >> 8);
                    if (this.f84a.f110a.f158w && i3 >= -2000) {
                        i3 = (short) (i3 - 2000);
                    } else if (this.f84a.f110a.f159x && i3 <= CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE) {
                        i3 = (short) (i3 + CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE);
                    }
                    i3 = m19a(!this.f84a.f110a.f117B ? (short) (i3 / -4) : (short) (i3 / 4));
                    if (i3 != 0) {
                        m21a(i3);
                    }
                }
            }

            private void m24c() {
                this.f74L = (byte) 0;
            }

            private void m25d() {
                this.f78P = false;
                this.f70H = (byte) 0;
            }

            private void m26e() {
                int i;
                boolean z;
                if (this.f84a.f110a.f116A == (byte) 0) {
                    int i2 = this.f84a.f110a.f148m[this.f84a.f110a.f149n][0];
                    i = this.f84a.f110a.f148m[this.f84a.f110a.f149n][2];
                    for (int i3 = 0; i3 < 9; i3++) {
                        if (this.f79Q[i3] > this.f84a.f110a.f148m[this.f84a.f110a.f149n][1]) {
                            if (this.f79Q[i3] < i) {
                                i = this.f79Q[i3];
                            }
                        } else if (this.f79Q[i3] > i2) {
                            i2 = this.f79Q[i3];
                        }
                    }
                    if (i - i2 < this.f88e / 4) {
                        this.f88e = (byte) (this.f88e - 1);
                        Log.i("YinDuTest", "RECV Diff: changed to " + this.f88e);
                        return;
                    }
                }
                byte[] bArr = this.f69G;
                byte b = this.f74L;
                this.f74L = (byte) (b + 1);
                bArr[b] = (byte) (this.f76N & 255);
                if (this.f74L == (byte) 4) {
                    boolean z2;
                    i = 0;
                    while (i < 4 && this.f69G[i] == this.f89f[i]) {
                        i++;
                    }
                    if (i == 4) {
                        Log.i("YinDuTest", "R: Wave HIGH first");
                        if (this.f84a.f110a.f116A == (byte) 0) {
                            this.f84a.f110a.f117B = true;
                            z = false;
                            z2 = true;
                        } else {
                            z = false;
                            z2 = true;
                        }
                    } else {
                        z = false;
                        while (z < true && this.f69G[z] == this.f90g[z]) {
                            z++;
                        }
                        z2 = false;
                    }
                    if (z) {
                        Log.i("YinDuTest", "R: Wave HIGH(2) first");
                        if (this.f84a.f110a.f116A == (byte) 0) {
                            this.f84a.f110a.f117B = true;
                            z = false;
                            z2 = true;
                        } else {
                            z = false;
                            z2 = true;
                        }
                    } else if (this.f84a.f110a.f116A == (byte) 0) {
                        z = false;
                        while (z < true && this.f69G[z] == this.f91h[z]) {
                            z++;
                        }
                    }
                    if (z) {
                        Log.i("YinDuTest", "R: Wave LOW first");
                        this.f84a.f110a.f117B = false;
                        z2 = false;
                        z = true;
                    } else {
                        if (this.f84a.f110a.f116A == (byte) 0) {
                            z = false;
                            while (z < true && this.f69G[z] == this.f92i[z]) {
                                z++;
                            }
                        }
                        boolean z3 = z;
                        z = z2;
                        z2 = z3;
                    }
                    if (z2) {
                        Log.i("YinDuTest", "R: Wave LOW(2) first");
                        this.f84a.f110a.f117B = false;
                        z = true;
                    }
                    if (!z) {
                        m20a();
                    }
                } else {
                    z = false;
                }
                if (z) {
                    this.f84a.f110a.m58b(2);
                    m24c();
                }
            }

            private void m27f() {
                int i = 0;
                byte[] bArr;
                if (this.f74L != (byte) 0) {
                    Log.i("YinDuTest", "F:" + this.f74L);
                    bArr = this.f69G;
                    byte b = this.f74L;
                    this.f74L = (byte) (b + 1);
                    bArr[b] = (byte) (this.f76N & 255);
                    if (this.f74L == (byte) 8 && this.f76N < (short) 255 && this.f76N > (short) 8) {
                        this.f68F = (byte) (this.f76N & 255);
                    }
                    if (this.f74L < this.f68F) {
                        return;
                    }
                    if (((byte) this.f76N) != (byte) -1) {
                        m22b();
                        return;
                    }
                    for (byte b2 = (byte) 0; b2 < this.f68F; b2++) {
                        i = (byte) (i ^ this.f69G[b2]);
                    }
                    if (i != 0) {
                        Log.i("YinDuTest", "F XOR ERR! CRC=" + i);
                        m22b();
                    } else if (this.f84a.f110a.f123H || this.f69G[2] == (byte) 0 || this.f69G[2] == (byte) 16) {
                        int i2;
                        if ((this.f84a.f110a.f123H && this.f84a.f110a.f118C != (byte) -1) || !(this.f84a.f110a.f123H || this.f69G[4] == (byte) -1)) {
                            i = (byte) (this.f69G[1] + this.f69G[2]);
                            for (i2 = 4; i2 < this.f68F - 1; i2++) {
                                i = (byte) (i + this.f69G[i2]);
                            }
                        }
                        if (i != 0) {
                            Log.i("YinDuTest", "F SUM ERR! CRC=" + i);
                            m22b();
                            return;
                        }
                        short s;
                        switch (this.f69G[2]) {
                            case (byte) 0:
                                if (this.f68F != (byte) 8) {
                                    this.f84a.f110a.f118C = this.f69G[4];
                                    Log.i("YinDuTest", "F: dev ver=" + this.f84a.f110a.f118C);
                                    this.f84a.f110a.f119D = this.f69G[5];
                                    Log.i("YinDuTest", "F: Protocol ver=" + this.f84a.f110a.f119D);
                                    this.f84a.f110a.f133S = ((float) (((short) (this.f69G[8] & 255)) + 100)) / 100.0f;
                                    Log.i("YinDuTest", "F: V=" + this.f84a.f110a.f133S);
                                    this.f84a.f110a.f134T = ((float) ((short) (((short) ((this.f69G[6] & 1) << 8)) | (this.f69G[9] & 255)))) / 10.0f;
                                    Log.i("YinDuTest", "F: T=" + this.f84a.f110a.f134T);
                                    this.f84a.f110a.f120E = ((this.f69G[10] & 255) | ((this.f69G[11] & 255) << 8)) | ((this.f69G[12] & 255) << 16);
                                    Log.i("YinDuTest", "F: swVer=" + this.f84a.f110a.f120E);
                                    char[] toCharArray = "0123456789ABCDEF".toCharArray();
                                    StringBuilder stringBuilder = new StringBuilder("");
                                    for (i2 = 0; i2 < 10; i2++) {
                                        stringBuilder.append(toCharArray[(this.f69G[22 - i2] & 240) >> 4]);
                                        stringBuilder.append(toCharArray[this.f69G[22 - i2] & 15]);
                                    }
                                    this.f84a.f110a.f121F = stringBuilder.toString();
                                    Log.i("YinDuTest", "F: SN=" + this.f84a.f110a.f121F);
                                    if ((this.f69G[6] & 128) == 0) {
                                        if ((this.f69G[6] & 64) == 0) {
                                            if (this.f84a.f110a.f146k == 2) {
                                                this.f84a.f110a.m58b(3);
                                                break;
                                            }
                                        }
                                        this.f84a.f110a.m58b(11);
                                        break;
                                    }
                                    this.f84a.f110a.m58b(10);
                                    break;
                                }
                                if ((this.f69G[6] & 32) != 0) {
                                    s = (short) (this.f69G[4] & 255);
                                    if (s > (short) 0) {
                                        this.f84a.f110a.f133S = ((float) (s + 100)) / 100.0f;
                                        Log.i("YinDuTest", "F: V=" + this.f84a.f110a.f133S);
                                    }
                                } else if (!(this.f84a.f110a.f123H || this.f69G[4] == (byte) -1)) {
                                    this.f84a.f110a.f118C = this.f69G[4];
                                    Log.i("YinDuTest", "F: dev ver=" + this.f84a.f110a.f118C);
                                }
                                if ((this.f69G[6] & 16) != 0) {
                                    s = (short) (((((short) this.f69G[6]) << 8) & 768) | (((short) this.f69G[5]) & 255));
                                    if (s > (short) 0) {
                                        this.f84a.f110a.f134T = ((float) (s - 1)) / 10.0f;
                                        Log.i("YinDuTest", "F: T=" + this.f84a.f110a.f134T);
                                    }
                                } else if (!(this.f84a.f110a.f123H || this.f69G[4] == (byte) -1)) {
                                    this.f84a.f110a.f119D = this.f69G[5];
                                    Log.i("YinDuTest", "F: Protocol ver=" + this.f84a.f110a.f119D);
                                }
                                if ((this.f69G[6] & 128) == 0) {
                                    if ((this.f69G[6] & 64) == 0) {
                                        if (this.f84a.f110a.f146k == 2) {
                                            this.f84a.f110a.m58b(3);
                                            break;
                                        }
                                    }
                                    this.f84a.f110a.m58b(11);
                                    break;
                                }
                                this.f84a.f110a.m58b(10);
                                break;
                                break;
                            case (byte) 1:
                                this.f84a.f110a.f147l = ((float) (((short) ((((short) this.f69G[5]) << 8) | (((short) this.f69G[4]) & 255))) & 32767)) / 10.0f;
                                Log.i("YinDuTest", "F: Glucose=" + this.f84a.f110a.f147l);
                                this.f84a.f110a.f126K = this.f69G[6];
                                this.f84a.f110a.m58b(8);
                                break;
                            case (byte) 2:
                                if (this.f69G[4] != (byte) 0) {
                                    this.f84a.f110a.m58b(5);
                                    break;
                                }
                                s = (short) (this.f69G[5] & 255);
                                if (this.f84a.f110a.f118C != (byte) -1) {
                                    this.f84a.f110a.f133S = ((float) (s + 100)) / 100.0f;
                                    Log.i("YinDuTest", "Frame: Voltage=" + this.f84a.f110a.f133S);
                                    this.f84a.f110a.f134T = ((float) (((short) (this.f69G[6] & 255)) * 2)) / 10.0f;
                                    Log.i("YinDuTest", "F: T=" + this.f84a.f110a.f134T);
                                }
                                this.f84a.f110a.m58b(4);
                                break;
                            case (byte) 3:
                                this.f84a.f110a.m58b(7);
                                break;
                            case (byte) 4:
                                this.f84a.f110a.m58b(6);
                                break;
                            case (byte) 6:
                                if (this.f69G[4] != (byte) 0) {
                                    if (this.f69G[4] != (byte) 1) {
                                        if (this.f69G[4] != (byte) 2) {
                                            if (this.f69G[4] == (byte) 3) {
                                                if (((short) ((((short) this.f69G[6]) << 8) | (((short) this.f69G[5]) & 255))) != (short) 0) {
                                                    this.f84a.f110a.m58b(13);
                                                    break;
                                                } else {
                                                    this.f84a.f110a.m58b(12);
                                                    break;
                                                }
                                            }
                                        }
                                        this.f84a.f110a.m58b(10);
                                        break;
                                    }
                                    this.f84a.f110a.m58b(9);
                                    break;
                                }
                                break;
                            case (byte) 7:
                                i2 = ((this.f69G[4] & 255) | ((this.f69G[5] & 255) << 8)) | ((this.f69G[6] & 255) << 16);
                                this.f84a.f110a.f121F = String.format("%07d", new Object[]{Integer.valueOf(i2)});
                                Log.i("YinDuTest", "F: SN=" + this.f84a.f110a.f121F);
                                break;
                            case (byte) 8:
                                this.f84a.f110a.f120E = ((this.f69G[4] & 255) | ((this.f69G[5] & 255) << 8)) | ((this.f69G[6] & 255) << 16);
                                Log.i("YinDuTest", "F: swVer=" + this.f84a.f110a.f120E);
                                break;
                        }
                        m24c();
                    } else {
                        Log.i("YinDuTest", "F sequence ERR!");
                        m24c();
                    }
                } else if (((byte) this.f76N) == (byte) -1) {
                    Log.i("YinDuTest", "F: S");
                    bArr = this.f69G;
                    byte b3 = this.f74L;
                    this.f74L = (byte) (b3 + 1);
                    bArr[b3] = (byte) this.f76N;
                    this.f68F = (byte) 8;
                }
            }

            public void run() {
                Log.d("YinDuTest", "AudRec: thread started!");
                if (!(this.f84a.f112c == null || this.f84a.f112c.getRecordingState() == 3)) {
                    Log.i("YinDuTest", "AudRec: can't record, released");
                    this.f84a.f112c.release();
                    this.f84a.f112c = null;
                    this.f84a.f110a.m58b(15);
                }
                while (this.f84a.f112c != null && this.f84a.f112c.getRecordingState() == 3) {
                    int read = this.f84a.f112c.read(this.f85b, 0, this.f87d);
                    if (-3 != read) {
                        m23b(read);
                    }
                }
                if (this.f84a.f112c != null) {
                    Log.i("YinDuTest", "AudRec: released");
                    this.f84a.f112c.release();
                    this.f84a.f112c = null;
                }
            }
        }

        public C1050b(DnurseDeviceTest dnurseDeviceTest) {
            this.f110a = dnurseDeviceTest;
            int minBufferSize = AudioRecord.getMinBufferSize(44100, 16, 2);
            Log.i("YinDuTest", "AudRec: min buffer=" + minBufferSize);
            if (minBufferSize < 0) {
                Log.i("YinDuTest", "AudRec: Error to get min buffer!");
                this.f113d = 0;
                return;
            }
            this.f113d = 16384;
            if (this.f113d < minBufferSize) {
                this.f113d = ((minBufferSize * 8) + 7) / 8;
            }
        }

        private void m33d() {
            if (this.f112c != null && this.f112c.getRecordingState() == 3) {
                Log.i("YinDuTest", "AudRec: stopped");
                this.f112c.stop();
            }
        }

        public Boolean m34a() {
            return Boolean.valueOf(this.f112c == null);
        }

        public boolean m35b() {
            return this.f114e == (short) 0;
        }

        public Boolean m36c() {
            if (this.f113d <= 0) {
                return Boolean.valueOf(false);
            }
            this.f112c = new AudioRecord(1, 44100, 16, 2, this.f113d);
            if (this.f112c == null || this.f112c.getState() != 1) {
                Log.i("YinDuTest", "AudRec: Create failed!");
                if (this.f112c != null) {
                    Log.i("YinDuTest", "AudRec: released");
                    this.f112c.release();
                    this.f112c = null;
                }
                return Boolean.valueOf(false);
            }
            this.f112c.startRecording();
            new Thread(new C1049a(this, this.f113d / 2)).start();
            Log.i("YinDuTest", "AudRec: Recording started!");
            return Boolean.valueOf(true);
        }
    }

    public DnurseDeviceTest(Context context) {
        this.f129N = context;
        if (Build.MODEL.equalsIgnoreCase("K-Touch T91") || Build.MODEL.equalsIgnoreCase("K-Touch C980t") || Build.MODEL.equalsIgnoreCase("K-Touch T789") || Build.MODEL.equalsIgnoreCase("K-Touch S5t") || Build.MODEL.equalsIgnoreCase("Lenovo A658t") || Build.MODEL.equalsIgnoreCase("Lenovo S868t") || Build.MODEL.equalsIgnoreCase("8295") || Build.MODEL.equalsIgnoreCase("ZTE U930HD") || Build.MODEL.equalsIgnoreCase("HUAWEI G606-T00") || Build.MODEL.equalsIgnoreCase("K-Touch T6")) {
            this.f158w = true;
        } else if (Build.MODEL.equalsIgnoreCase("8085") || Build.MODEL.equalsIgnoreCase("8085N") || Build.MODEL.equalsIgnoreCase("8190") || Build.MODEL.equalsIgnoreCase("8720")) {
            this.f151p = 200;
            this.f158w = true;
        } else if (Build.MODEL.equalsIgnoreCase("Coolpad 8703")) {
            this.f152q = 800;
        } else if (Build.MODEL.equalsIgnoreCase("Lenovo A330t")) {
            this.f152q = 500;
            this.f154s = 3000;
        } else if (Build.MODEL.equalsIgnoreCase("GT-I9200") || Build.MODEL.equalsIgnoreCase("SM-G3508I")) {
            this.f156u = 300;
        } else if (Build.MODEL.equalsIgnoreCase("MX4 Pro")) {
            this.f156u = 500;
            this.f154s = CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE;
        } else if (Build.MODEL.equalsIgnoreCase("R801")) {
            this.f152q = 50;
        } else if (Build.MODEL.equalsIgnoreCase("HTC One X") || Build.MODEL.equalsIgnoreCase("HUAWEI U8825-1")) {
            this.f151p = 200;
        } else if (Build.MODEL.equalsIgnoreCase("8150")) {
            this.f160y = false;
        } else if (Build.MODEL.equalsIgnoreCase("C1505")) {
            this.f157v = PL2303Driver.BAUD1800;
        } else if (Build.MODEL.equalsIgnoreCase("GT-I9103")) {
            this.f157v = 200;
        } else if (Build.MODEL.equalsIgnoreCase("M040")) {
            this.f148m[0][6] = 0;
            this.f148m[1][6] = 0;
        } else if (Build.MODEL.startsWith("HTC T528") || Build.MODEL.equalsIgnoreCase("Coolpad8750")) {
            this.f150o = (byte) 1;
        } else if (Build.MODEL.equalsIgnoreCase("SCH-P709")) {
            this.f150o = (byte) 2;
            this.f116A = (byte) 1;
        } else if (Build.MODEL.equalsIgnoreCase("GT-S6352") || Build.MODEL.equalsIgnoreCase("GT-I9070")) {
            this.f155t = 500;
        } else if (Build.MODEL.equalsIgnoreCase("N5207")) {
            this.f155t = ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
            this.f161z = false;
        } else if (Build.MODEL.equalsIgnoreCase("8195") || Build.MODEL.equalsIgnoreCase("8150D")) {
            this.f156u = 300;
            this.f158w = true;
        } else if (Build.MODEL.endsWith("Lenovo A780e") || Build.MODEL.endsWith("Lenovo A385e") || Build.MODEL.endsWith("HTC T320e")) {
            this.f155t = 100;
        } else if (Build.MODEL.equalsIgnoreCase("ST25i") || Build.MODEL.equalsIgnoreCase("LT22i") || Build.MODEL.equalsIgnoreCase("HTC T329t")) {
            this.f116A = (byte) 1;
        }
    }

    public static String MD5(String str) {
        int i = 0;
        try {
            MessageDigest instance = MessageDigest.getInstance(CommonUtils.MD5_INSTANCE);
            if (str == null || "".equals(str)) {
                return "";
            }
            char[] toCharArray = str.toCharArray();
            byte[] bArr = new byte[toCharArray.length];
            for (int i2 = 0; i2 < toCharArray.length; i2++) {
                bArr[i2] = (byte) toCharArray[i2];
            }
            byte[] digest = instance.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            while (i < digest.length) {
                int i3 = digest[i] & 255;
                if (i3 < 16) {
                    stringBuffer.append(AppEventsConstants.EVENT_PARAM_VALUE_NO);
                }
                stringBuffer.append(Integer.toHexString(i3));
                i++;
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    private void m49a() {
        Date date = new Date(System.currentTimeMillis() + 1000);
        this.f124I = (byte) 10;
        this.f131P.removeCallbacks(this.f139d);
        if (this.f132Q != null) {
            this.f132Q.cancel();
            this.f132Q.purge();
        }
        this.f132Q = new Timer(true);
        this.f132Q.scheduleAtFixedRate(new C1056f(this), date, 1000);
    }

    private void m50a(int i) {
        this.f131P.removeCallbacks(this.f139d);
        if (this.f132Q != null) {
            this.f132Q.cancel();
        }
        this.f131P.postDelayed(this.f139d, (long) i);
    }

    private void m57b() {
        m58b(1);
        if (!this.f128M.m14a()) {
            Log.i("YinDuTest", "AudPly: start failed!");
            m58b(14);
        } else if (this.f127L.m36c().booleanValue()) {
            m50a(this.f154s);
            this.f131P.postDelayed(this.f138c, (long) this.f156u);
        } else {
            Log.i("YinDuTest", "AudRec: start failed!");
            m58b(15);
        }
    }

    private void m58b(int i) {
        if (i != this.f146k) {
            Log.i("YinDuTest", "notifyChange,state=" + i);
            switch (i) {
                case 0:
                    this.f118C = (byte) -1;
                    this.f119D = (byte) -1;
                    this.f120E = 0;
                    this.f121F = "";
                    break;
                case 2:
                    if (this.f146k == 1) {
                        if (this.f149n <= 1) {
                            this.f128M.m13a(true);
                        } else {
                            this.f128M.m13a(false);
                        }
                        m50a(this.f154s);
                        this.f125J = (byte) -1;
                        break;
                    }
                    return;
                case 3:
                    this.f123H = true;
                    break;
                case 4:
                case 5:
                case 6:
                    break;
                case 7:
                    m49a();
                    break;
                case 8:
                    m50a(195000);
                    this.f128M.m17d();
                    if (this.f118C == (byte) -1 || this.f125J != this.f126K) {
                        this.f125J = this.f126K;
                        Calendar instance = Calendar.getInstance();
                        SparseArray sparseArray = new SparseArray();
                        sparseArray.put(1, Float.valueOf(this.f147l));
                        sparseArray.put(2, instance);
                        sparseArray.put(3, this.f121F);
                        this.f130O.onSuccess(sparseArray);
                        break;
                    }
                    return;
                    break;
                case 9:
                case 10:
                case 11:
                case 12:
                case 13:
                case 14:
                case 15:
                case 16:
                case 17:
                    m64c();
                    break;
            }
            m50a(195000);
            this.f146k = i;
            if (this.f146k == 7) {
                this.f130O.onMeasuring(this.f146k, this.f124I);
            } else {
                this.f130O.onMeasuring(this.f146k, 0);
            }
        }
    }

    private void m64c() {
        this.f131P.removeCallbacks(this.f136a);
        this.f131P.removeCallbacks(this.f138c);
        this.f131P.removeCallbacks(this.f139d);
        if (this.f128M != null) {
            this.f128M.m18e();
        }
        if (this.f122G && this.f128M != null) {
            this.f128M.m15b();
        }
        if (this.f127L != null) {
            this.f127L.m33d();
        }
        this.f123H = false;
    }

    private static SimpleDateFormat m68d() {
        if (f115R == null) {
            synchronized (DnurseDeviceTest.class) {
                if (f115R == null) {
                    f115R = new SimpleDateFormat("yyyyMMddHHmmss", Locale.US);
                }
            }
        }
        return f115R;
    }

    public static String newIdWithTag(String str) {
        String format = m68d().format(Calendar.getInstance().getTime());
        int nextInt = new Random().nextInt(1000000);
        String format2 = String.format(Locale.US, "%06d", new Object[]{Integer.valueOf(nextInt)});
        StringBuilder stringBuilder = new StringBuilder();
        if (str != null) {
            stringBuilder.append(str);
        }
        stringBuilder.append(format);
        stringBuilder.append(format2);
        return stringBuilder.toString();
    }

    public void startTest(IMeasureDataResultCallback iMeasureDataResultCallback) {
        this.f130O = iMeasureDataResultCallback;
        if (this.f128M == null) {
            this.f128M = new C1048a(this);
        }
        if (this.f127L == null) {
            this.f127L = new C1050b(this);
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        this.f129N.registerReceiver(this.f135U, intentFilter);
        this.f130O.onMeasuring(this.f146k, 0);
    }

    public void stopTest() {
        try {
            this.f129N.unregisterReceiver(this.f135U);
        } catch (IllegalArgumentException e) {
        }
        m64c();
        if (this.f128M != null) {
            this.f128M.m18e();
            this.f128M = null;
        }
    }

    public void wakeupDevice() {
        if (this.f122G && !DnurseConstant.isWorking(this.f146k)) {
            this.f131P.postDelayed(this.f136a, (long) this.f155t);
        }
    }
}
