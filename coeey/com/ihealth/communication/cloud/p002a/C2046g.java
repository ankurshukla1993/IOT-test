package com.ihealth.communication.cloud.p002a;

import android.os.Environment;
import android.os.SystemClock;
import com.ihealth.communication.utils.Log;
import java.io.File;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.text.Typography;

public class C2046g {
    private String f527a;
    private C2047h f528b;

    private File m369a(int i) {
        File file;
        File file2 = new File(C2046g.m371a() + "/iHealth/");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        if (i == 0) {
            file = new File(file2, "AMDownload.txt");
            if (file.exists()) {
                file.delete();
            }
        } else {
            file = new File(file2, "AMIndex.txt");
            if (file.exists()) {
                file.delete();
            }
        }
        return file;
    }

    public static String m371a() {
        return Environment.getExternalStorageDirectory().toString();
    }

    private String m374a(String str, Map map, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        if (!(map == null || map.isEmpty())) {
            for (Entry entry : map.entrySet()) {
                stringBuilder.append((String) entry.getKey()).append('=');
                stringBuilder.append(URLEncoder.encode((String) entry.getValue(), str2));
                stringBuilder.append(Typography.amp);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        String str3 = str + stringBuilder.toString();
        Log.i("AMDownLoad", "http_url_GET" + str3);
        return str3;
    }

    public String m375a(String str, Map map, String str2, int i, C2048i c2048i) {
        this.f527a = "";
        this.f528b = new C2047h(this, map, str2, str, c2048i);
        if (this.f528b.m378a()) {
            this.f528b.start();
            int i2 = -1;
            while (this.f528b.m378a()) {
                SystemClock.sleep(1);
                i2++;
                if (i2 > 120000) {
                    break;
                }
            }
        }
        return this.f527a;
    }

    public void m376b() {
        if (this.f528b != null) {
            this.f528b.m379b();
        } else {
            Log.w("HttpClientGet", "releaseConnection");
        }
    }
}
