package com.ihealth.androidbg.audio;

import android.media.AudioRecord;
import com.ihealth.androidbg.audio.BG1.BG1_Command_Interface_Subject;
import com.ihealth.communication.ins.GenerateKap;
import com.ihealth.communication.utils.Log;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class TunnerThread {
    private static final String f249a = TunnerThread.class.getSimpleName();
    private static int f250b = 44100;
    private byte[] f251c = new byte[2048];
    private boolean f252d = true;
    private AudioRecord f253e = null;
    private ArrayList f254f = null;
    private byte[] f255g = null;
    private int[] f256h = null;
    private LinkedHashMap f257i = null;
    private boolean f258j = false;
    private boolean f259k = false;
    private GenerateKap f260l = new GenerateKap();
    public BG1_Command_Interface_Subject msgSubject;

    public TunnerThread() {
        m215a(3);
        this.f251c = new byte[2048];
        this.msgSubject = new BG1_Command_Interface_Subject();
        new C2022b(this).start();
    }

    private int m210a(double d) {
        return this.f259k ? (7500.0d <= d || d < 6597.0d) ? (6597.0d <= d || d < 5966.0d) ? (5966.0d <= d || d < 5445.0d) ? (5445.0d <= d || d < 5008.0d) ? (5008.0d <= d || d < 4636.0d) ? (4636.0d <= d || d < 4315.0d) ? (4315.0d <= d || d < 4020.0d) ? (4020.0d <= d || d < 3840.0d) ? (3840.0d <= d || d < 3550.0d) ? (3550.0d <= d || d < 3268.0d) ? (3268.0d <= d || d < 2951.0d) ? (2951.0d <= d || d < 2639.0d) ? (2639.0d <= d || d < 2408.0d) ? (2408.0d <= d || d < 2098.0d) ? (2098.0d <= d || d < 1806.0d) ? (1806.0d <= d || d < 1482.0d) ? (1482.0d <= d || d < 1091.0d) ? (1091.0d <= d || d < 753.0d) ? (753.0d <= d || d < 500.0d) ? 0 : 601 : 906 : 1276 : 1689 : 1923 : 2273 : 2500 : 2778 : 3125 : 3378 : 3676 : 3906 : 4167 : 4464 : 4808 : 5208 : 5682 : 6250 : 6944 : (10000.0d <= d || d < 8371.0d) ? (8371.0d <= d || d < 7378.0d) ? (7378.0d <= d || d < 6597.0d) ? (6597.0d <= d || d < 5966.0d) ? (5966.0d <= d || d < 5445.0d) ? (5445.0d <= d || d < 5008.0d) ? (5008.0d <= d || d < 4636.0d) ? (4636.0d <= d || d < 4315.0d) ? (4315.0d <= d || d < 3921.0d) ? (3921.0d <= d || d < 3482.0d) ? (3482.0d <= d || d < 3065.0d) ? (3065.0d <= d || d < 2670.0d) ? (2670.0d <= d || d < 2327.0d) ? (2327.0d <= d || d < 1996.0d) ? (1996.0d <= d || d < 1681.0d) ? (1681.0d <= d || d < 1363.0d) ? (1363.0d <= d || d < 1054.0d) ? (1054.0d <= d || d < 753.0d) ? (753.0d <= d || d < 500.0d) ? 0 : 601 : 906 : 1202 : 1524 : 1838 : 2155 : 2500 : 2841 : 3289 : 3676 : 4167 : 4464 : 4808 : 5208 : 5682 : 6250 : 6944 : 7813 : 8929;
    }

    private String m214a(byte[] bArr, int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            String toHexString = Integer.toHexString(bArr[i2] & 255);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            str = str + toHexString.toUpperCase();
        }
        return str;
    }

    private boolean m215a(int i) {
        int minBufferSize;
        Log.v(f249a, "initAudioRecord count ---> " + (4 - i));
        try {
            if (this.f253e != null) {
                Log.v(f249a, "mAudioRecord.getState() ---> " + this.f253e.getState());
                if (this.f253e.getState() == 1 || this.f253e.getState() == 3) {
                    return true;
                }
                AudioRecord audioRecord;
                try {
                    this.f253e.release();
                    audioRecord = null;
                    this.f253e = null;
                    this.f253e = audioRecord;
                } catch (Exception e) {
                    String str = f249a;
                    audioRecord = "AudioRecord Exception ---> " + e;
                    Log.w(str, audioRecord);
                    this.f253e = audioRecord;
                    minBufferSize = AudioRecord.getMinBufferSize(f250b, 16, 2);
                    Log.v(f249a, "AudioRecord min_buffer_size ---> " + minBufferSize);
                    this.f253e = new AudioRecord(1, f250b, 16, 2, minBufferSize * 2);
                    if (this.f253e.getState() != 1) {
                        if (i > 1) {
                            return m215a(i - 1);
                        }
                        return false;
                    }
                    Log.v(f249a, "STATE_INITIALIZED ---> ");
                    return true;
                } finally {
                    this.f253e = null;
                }
            }
            minBufferSize = AudioRecord.getMinBufferSize(f250b, 16, 2);
            Log.v(f249a, "AudioRecord min_buffer_size ---> " + minBufferSize);
            this.f253e = new AudioRecord(1, f250b, 16, 2, minBufferSize * 2);
            if (this.f253e.getState() != 1) {
                Log.v(f249a, "STATE_INITIALIZED ---> ");
                return true;
            }
            if (i > 1) {
                return m215a(i - 1);
            }
            return false;
        } catch (Exception e2) {
            Log.w(f249a, "initAudioRecord Exception ---> " + e2);
            if (i > 1) {
                return m215a(i - 1);
            }
        }
    }

    private byte[] m217a(byte[] bArr) {
        Object obj = new int[4];
        this.f257i = new LinkedHashMap();
        int i = 0;
        for (int i2 = 256; i2 < (bArr.length - 1024) + 256; i2 += 256) {
            byte[] bArr2 = new byte[1024];
            System.arraycopy(bArr, i2, bArr2, 0, 512);
            obj[i] = m220b(bArr2);
            i++;
        }
        if (this.f256h == null) {
            this.f256h = new int[5];
            System.arraycopy(obj, 0, this.f256h, 0, 4);
        } else {
            System.arraycopy(this.f256h, 4, this.f256h, 0, 1);
            System.arraycopy(obj, 0, this.f256h, 1, 4);
        }
        for (int i22 : this.f256h) {
            this.f257i.put(Integer.valueOf(i22), Integer.valueOf(this.f257i.containsKey(Integer.valueOf(i22)) ? ((Integer) this.f257i.get(Integer.valueOf(i22))).intValue() + 1 : 1));
        }
        for (Entry entry : this.f257i.entrySet()) {
            if (((Integer) entry.getValue()).intValue() >= 2 && ((Integer) entry.getKey()).intValue() > 500) {
                if (((Integer) entry.getKey()).intValue() == 906) {
                    this.f254f = new ArrayList();
                    this.f254f.add(entry.getKey());
                    this.f255g = null;
                } else if (this.f254f != null && this.f254f.size() > 0 && ((Integer) this.f254f.get(0)).intValue() == 906 && ((Integer) entry.getKey()).intValue() == 601) {
                    this.f254f.add(entry.getKey());
                    this.f255g = m218a(m219a(this.f254f));
                    this.f254f.clear();
                    this.f256h = null;
                    return this.f255g;
                } else if (this.f254f != null && this.f254f.size() > 0 && ((Integer) this.f254f.get(0)).intValue() == 906 && ((Integer) entry.getKey()).intValue() != ((Integer) this.f254f.get(this.f254f.size() - 1)).intValue()) {
                    this.f254f.add(entry.getKey());
                }
            }
        }
        return null;
    }

    private byte[] m218a(int[] iArr) {
        int i = 1;
        int i2 = -1;
        byte[] bArr;
        int i3;
        int i4;
        int i5;
        if (iArr.length % 2 == 0) {
            bArr = new byte[((iArr.length - 2) / 2)];
            while (i < iArr.length - 1) {
                if (iArr[i] == 16) {
                    i3 = i2;
                } else {
                    i4 = iArr[i];
                    i2 = iArr[i];
                    i3 = i4;
                }
                if (iArr[i + 1] == 16) {
                    i5 = i2;
                } else {
                    i4 = iArr[i + 1];
                    i2 = iArr[i + 1];
                    i5 = i4;
                }
                bArr[(i - 1) / 2] = (byte) ((i3 * 16) + i5);
                i += 2;
            }
            return bArr;
        }
        bArr = new byte[(((iArr.length - 2) / 2) + 1)];
        while (i < iArr.length - 1) {
            if (iArr[i] == 16) {
                i3 = i2;
            } else {
                i4 = iArr[i];
                i2 = iArr[i];
                i3 = i4;
            }
            if (i == iArr.length - 2) {
                i5 = 0;
            } else if (iArr[i + 1] == 16) {
                i5 = i2;
            } else {
                i4 = iArr[i + 1];
                i2 = iArr[i + 1];
                i5 = i4;
            }
            bArr[(i - 1) / 2] = (byte) ((i3 * 16) + i5);
            i += 2;
        }
        return bArr;
    }

    private int[] m219a(ArrayList arrayList) {
        int[] iArr = new int[arrayList.size()];
        int i;
        if (this.f259k) {
            for (i = 0; i < arrayList.size(); i++) {
                if (((Integer) arrayList.get(i)).intValue() == 6944) {
                    iArr[i] = 0;
                } else if (((Integer) arrayList.get(i)).intValue() == 6250) {
                    iArr[i] = 1;
                } else if (((Integer) arrayList.get(i)).intValue() == 5682) {
                    iArr[i] = 2;
                } else if (((Integer) arrayList.get(i)).intValue() == 5208) {
                    iArr[i] = 3;
                } else if (((Integer) arrayList.get(i)).intValue() == 4808) {
                    iArr[i] = 4;
                } else if (((Integer) arrayList.get(i)).intValue() == 4464) {
                    iArr[i] = 5;
                } else if (((Integer) arrayList.get(i)).intValue() == 4167) {
                    iArr[i] = 6;
                } else if (((Integer) arrayList.get(i)).intValue() == 3906) {
                    iArr[i] = 7;
                } else if (((Integer) arrayList.get(i)).intValue() == 3676) {
                    iArr[i] = 8;
                } else if (((Integer) arrayList.get(i)).intValue() == 3378) {
                    iArr[i] = 9;
                } else if (((Integer) arrayList.get(i)).intValue() == 3125) {
                    iArr[i] = 10;
                } else if (((Integer) arrayList.get(i)).intValue() == 2778) {
                    iArr[i] = 11;
                } else if (((Integer) arrayList.get(i)).intValue() == 2500) {
                    iArr[i] = 12;
                } else if (((Integer) arrayList.get(i)).intValue() == 2273) {
                    iArr[i] = 13;
                } else if (((Integer) arrayList.get(i)).intValue() == 1923) {
                    iArr[i] = 14;
                } else if (((Integer) arrayList.get(i)).intValue() == 1689) {
                    iArr[i] = 15;
                } else if (((Integer) arrayList.get(i)).intValue() == 1276) {
                    iArr[i] = 16;
                } else if (((Integer) arrayList.get(i)).intValue() == 906) {
                    iArr[i] = 17;
                } else if (((Integer) arrayList.get(i)).intValue() == 601) {
                    iArr[i] = 18;
                }
            }
        } else {
            for (i = 0; i < arrayList.size(); i++) {
                if (((Integer) arrayList.get(i)).intValue() == 8929) {
                    iArr[i] = 0;
                } else if (((Integer) arrayList.get(i)).intValue() == 7813) {
                    iArr[i] = 1;
                } else if (((Integer) arrayList.get(i)).intValue() == 6944) {
                    iArr[i] = 2;
                } else if (((Integer) arrayList.get(i)).intValue() == 6250) {
                    iArr[i] = 3;
                } else if (((Integer) arrayList.get(i)).intValue() == 5682) {
                    iArr[i] = 4;
                } else if (((Integer) arrayList.get(i)).intValue() == 5208) {
                    iArr[i] = 5;
                } else if (((Integer) arrayList.get(i)).intValue() == 4808) {
                    iArr[i] = 6;
                } else if (((Integer) arrayList.get(i)).intValue() == 4464) {
                    iArr[i] = 7;
                } else if (((Integer) arrayList.get(i)).intValue() == 4167) {
                    iArr[i] = 8;
                } else if (((Integer) arrayList.get(i)).intValue() == 3676) {
                    iArr[i] = 9;
                } else if (((Integer) arrayList.get(i)).intValue() == 3289) {
                    iArr[i] = 10;
                } else if (((Integer) arrayList.get(i)).intValue() == 2841) {
                    iArr[i] = 11;
                } else if (((Integer) arrayList.get(i)).intValue() == 2500) {
                    iArr[i] = 12;
                } else if (((Integer) arrayList.get(i)).intValue() == 2155) {
                    iArr[i] = 13;
                } else if (((Integer) arrayList.get(i)).intValue() == 1838) {
                    iArr[i] = 14;
                } else if (((Integer) arrayList.get(i)).intValue() == 1524) {
                    iArr[i] = 15;
                } else if (((Integer) arrayList.get(i)).intValue() == 1202) {
                    iArr[i] = 16;
                } else if (((Integer) arrayList.get(i)).intValue() == 906) {
                    iArr[i] = 17;
                } else if (((Integer) arrayList.get(i)).intValue() == 601) {
                    iArr[i] = 18;
                }
            }
        }
        return iArr;
    }

    private synchronized int m220b(byte[] bArr) {
        return m210a(this.f260l.getDataFromByteArray(bArr, f250b) / 2.0d);
    }

    public void close() {
        this.f259k = false;
        this.f258j = true;
        this.f252d = false;
        if (this.f253e != null) {
            Log.v(f249a, "stop current communication");
            try {
                this.f253e.release();
                this.f253e = null;
            } catch (Exception e) {
                Log.w(f249a, "Exception ---> " + e);
            } finally {
                this.f253e = null;
            }
        }
    }

    public void set1307(boolean b) {
        this.f259k = b;
    }
}
