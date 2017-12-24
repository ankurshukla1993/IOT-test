package com.ihealth.communication.cloud.p002a;

import com.ihealth.communication.utils.Log;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.util.Map;

class C2047h extends Thread {
    final /* synthetic */ C2046g f529a;
    private Map f530b;
    private String f531c;
    private String f532d;
    private C2048i f533e;
    private HttpURLConnection f534f;
    private FileOutputStream f535g;
    private boolean f536h = true;

    public C2047h(C2046g c2046g, Map map, String str, String str2, C2048i c2048i) {
        this.f529a = c2046g;
        this.f530b = map;
        this.f531c = str;
        this.f532d = str2;
        this.f533e = c2048i;
    }

    public void m377a(boolean z) {
        this.f536h = z;
    }

    public boolean m378a() {
        return this.f536h;
    }

    public void m379b() {
        Log.w("HttpClientGet", "releaseConnection");
        this.f529a.f528b.m377a(false);
        try {
            if (this.f534f != null) {
                this.f534f.disconnect();
            }
        } catch (Exception e) {
        }
    }

    public void run() {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Not initialized variable reg: 6, insn: 0x00b5: INVOKE  (r1 int) = (r2_10 java.io.InputStream), (r6 byte[]) java.io.InputStream.read(byte[]):int type: VIRTUAL, block:B:15:0x00b5, method: com.ihealth.communication.cloud.a.h.run():void
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:168)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVar(SSATransform.java:197)
	at jadx.core.dex.visitors.ssa.SSATransform.renameVariables(SSATransform.java:132)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:52)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r11 = this;
        r10 = 1;
        r1 = 0;
        r0 = new java.net.URL;	 Catch:{ Exception -> 0x0119 }
        r2 = r11.f529a;	 Catch:{ Exception -> 0x0119 }
        r3 = r11.f532d;	 Catch:{ Exception -> 0x0119 }
        r4 = r11.f530b;	 Catch:{ Exception -> 0x0119 }
        r5 = r11.f531c;	 Catch:{ Exception -> 0x0119 }
        r2 = r2.m374a(r3, r4, r5);	 Catch:{ Exception -> 0x0119 }
        r0.<init>(r2);	 Catch:{ Exception -> 0x0119 }
        r0 = r0.openConnection();	 Catch:{ Exception -> 0x0119 }
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ Exception -> 0x0119 }
        r11.f534f = r0;	 Catch:{ Exception -> 0x0119 }
        r0 = r11.f534f;	 Catch:{ Exception -> 0x0119 }
        r0 = r0 instanceof javax.net.ssl.HttpsURLConnection;	 Catch:{ Exception -> 0x0119 }
        if (r0 == 0) goto L_0x002c;	 Catch:{ Exception -> 0x0119 }
    L_0x0021:
        r0 = r11.f534f;	 Catch:{ Exception -> 0x0119 }
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ Exception -> 0x0119 }
        r2 = com.ihealth.communication.cloud.p002a.C2052m.m395a();	 Catch:{ Exception -> 0x0119 }
        r0.setSSLSocketFactory(r2);	 Catch:{ Exception -> 0x0119 }
    L_0x002c:
        r0 = r11.f534f;	 Catch:{ Exception -> 0x0119 }
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;	 Catch:{ Exception -> 0x0119 }
        r0.setConnectTimeout(r2);	 Catch:{ Exception -> 0x0119 }
        r0 = r11.f534f;	 Catch:{ Exception -> 0x0119 }
        r2 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;	 Catch:{ Exception -> 0x0119 }
        r0.setReadTimeout(r2);	 Catch:{ Exception -> 0x0119 }
        r0 = r11.f534f;	 Catch:{ Exception -> 0x0119 }
        r2 = 1;	 Catch:{ Exception -> 0x0119 }
        r0.setDoInput(r2);	 Catch:{ Exception -> 0x0119 }
        r0 = r11.f534f;	 Catch:{ Exception -> 0x0119 }
        r2 = "GET";	 Catch:{ Exception -> 0x0119 }
        r0.setRequestMethod(r2);	 Catch:{ Exception -> 0x0119 }
        r0 = r11.f534f;	 Catch:{ Exception -> 0x0119 }
        r2 = "Content-Type";	 Catch:{ Exception -> 0x0119 }
        r3 = "application/x-www-form-urlencoded";	 Catch:{ Exception -> 0x0119 }
        r0.setRequestProperty(r2, r3);	 Catch:{ Exception -> 0x0119 }
        r0 = r11.f534f;	 Catch:{ Exception -> 0x0119 }
        r2 = "Accept-Charset";	 Catch:{ Exception -> 0x0119 }
        r3 = r11.f531c;	 Catch:{ Exception -> 0x0119 }
        r0.setRequestProperty(r2, r3);	 Catch:{ Exception -> 0x0119 }
        r0 = r11.f534f;	 Catch:{ Exception -> 0x0119 }
        r2 = "contentType";	 Catch:{ Exception -> 0x0119 }
        r3 = r11.f531c;	 Catch:{ Exception -> 0x0119 }
        r0.setRequestProperty(r2, r3);	 Catch:{ Exception -> 0x0119 }
        r0 = r11.f534f;	 Catch:{ Exception -> 0x0119 }
        if (r0 == 0) goto L_0x00f3;	 Catch:{ Exception -> 0x0119 }
    L_0x0066:
        r0 = r11.f534f;	 Catch:{ Exception -> 0x0119 }
        r2 = r0.getInputStream();	 Catch:{ Exception -> 0x0119 }
        r0 = r11.f534f;	 Catch:{ Exception -> 0x0119 }
        r3 = r0.getHeaderFields();	 Catch:{ Exception -> 0x0119 }
        if (r3 == 0) goto L_0x00f3;	 Catch:{ Exception -> 0x0119 }
    L_0x0074:
        r0 = "filetype";	 Catch:{ Exception -> 0x0119 }
        r0 = r3.get(r0);	 Catch:{ Exception -> 0x0119 }
        r0 = (java.util.List) r0;	 Catch:{ Exception -> 0x0119 }
        r4 = 0;	 Catch:{ Exception -> 0x0119 }
        r0 = r0.get(r4);	 Catch:{ Exception -> 0x0119 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0119 }
        r4 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0119 }
        r0 = "totalblock";	 Catch:{ Exception -> 0x0119 }
        r0 = r3.get(r0);	 Catch:{ Exception -> 0x0119 }
        r0 = (java.util.List) r0;	 Catch:{ Exception -> 0x0119 }
        r3 = 0;	 Catch:{ Exception -> 0x0119 }
        r0 = r0.get(r3);	 Catch:{ Exception -> 0x0119 }
        r0 = (java.lang.String) r0;	 Catch:{ Exception -> 0x0119 }
        r3 = java.lang.Integer.parseInt(r0);	 Catch:{ Exception -> 0x0119 }
        r0 = r11.f536h;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        if (r0 == 0) goto L_0x0144;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
    L_0x009e:
        r0 = r11.f529a;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r0 = r0.m369a(r4);	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r5 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r5.<init>(r0);	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r11.f535g = r5;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r5.<init>();	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r0 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r6 = new byte[r0];	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r0 = r1;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
    L_0x00b5:
        r1 = r2.read(r6);	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r7 = -1;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        if (r1 == r7) goto L_0x0137;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
    L_0x00bc:
        r7 = r11.f536h;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        if (r7 == 0) goto L_0x0137;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
    L_0x00c0:
        r7 = new java.lang.String;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r8 = 0;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r7.<init>(r6, r8, r1);	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r8 = r11.f535g;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r9 = 0;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r8.write(r6, r9, r1);	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r5.append(r7);	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r0 = r0 + r1;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r1 = r11.f533e;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        if (r1 == 0) goto L_0x00b5;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
    L_0x00d4:
        if (r4 != r10) goto L_0x0100;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
    L_0x00d6:
        r1 = r11.f533e;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r7 = r0 * 50;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r7 = r7 / r3;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r1.m380a(r7);	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        goto L_0x00b5;
    L_0x00df:
        r0 = move-exception;
        r0 = r11.f529a;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r1 = "";	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r0.f527a = r1;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r0 = 0;
        r11.f536h = r0;	 Catch:{ Exception -> 0x0119 }
        r0 = r11.f535g;	 Catch:{ Exception -> 0x0170 }
        if (r0 == 0) goto L_0x00f3;	 Catch:{ Exception -> 0x0170 }
    L_0x00ee:
        r0 = r11.f535g;	 Catch:{ Exception -> 0x0170 }
        r0.close();	 Catch:{ Exception -> 0x0170 }
    L_0x00f3:
        r0 = 0;
        r11.f536h = r0;	 Catch:{ Exception -> 0x0183 }
        r0 = r11.f534f;	 Catch:{ Exception -> 0x0183 }
        if (r0 == 0) goto L_0x00ff;	 Catch:{ Exception -> 0x0183 }
    L_0x00fa:
        r0 = r11.f534f;	 Catch:{ Exception -> 0x0183 }
        r0.disconnect();	 Catch:{ Exception -> 0x0183 }
    L_0x00ff:
        return;
    L_0x0100:
        r1 = r11.f533e;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r7 = r0 * 50;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r7 = r7 / r3;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r7 = r7 + 50;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r1.m380a(r7);	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        goto L_0x00b5;
    L_0x010b:
        r0 = move-exception;
        r1 = 0;
        r11.f536h = r1;	 Catch:{ Exception -> 0x0119 }
        r1 = r11.f535g;	 Catch:{ Exception -> 0x017a }
        if (r1 == 0) goto L_0x0118;	 Catch:{ Exception -> 0x017a }
    L_0x0113:
        r1 = r11.f535g;	 Catch:{ Exception -> 0x017a }
        r1.close();	 Catch:{ Exception -> 0x017a }
    L_0x0118:
        throw r0;	 Catch:{ Exception -> 0x0119 }
    L_0x0119:
        r0 = move-exception;
        r0 = r11.f529a;	 Catch:{ all -> 0x015a }
        r1 = "";	 Catch:{ all -> 0x015a }
        r0.f527a = r1;	 Catch:{ all -> 0x015a }
        r0 = 0;
        r11.f536h = r0;	 Catch:{ Exception -> 0x012e }
        r0 = r11.f534f;	 Catch:{ Exception -> 0x012e }
        if (r0 == 0) goto L_0x00ff;	 Catch:{ Exception -> 0x012e }
    L_0x0128:
        r0 = r11.f534f;	 Catch:{ Exception -> 0x012e }
        r0.disconnect();	 Catch:{ Exception -> 0x012e }
        goto L_0x00ff;
    L_0x012e:
        r0 = move-exception;
        r0 = "CLOUD";
        r1 = "conn类中释放资源出错";
        com.ihealth.communication.utils.Log.e(r0, r1);
        goto L_0x00ff;
    L_0x0137:
        r0 = r11.f536h;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        if (r0 == 0) goto L_0x0168;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
    L_0x013b:
        r0 = r11.f529a;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r1 = r5.toString();	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r0.f527a = r1;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
    L_0x0144:
        r0 = 0;
        r11.f536h = r0;	 Catch:{ Exception -> 0x0119 }
        r0 = r11.f535g;	 Catch:{ Exception -> 0x0151 }
        if (r0 == 0) goto L_0x00f3;	 Catch:{ Exception -> 0x0151 }
    L_0x014b:
        r0 = r11.f535g;	 Catch:{ Exception -> 0x0151 }
        r0.close();	 Catch:{ Exception -> 0x0151 }
        goto L_0x00f3;
    L_0x0151:
        r0 = move-exception;
        r0 = "CLOUD";	 Catch:{ Exception -> 0x0119 }
        r1 = "fout类中释放资源出错";	 Catch:{ Exception -> 0x0119 }
        com.ihealth.communication.utils.Log.e(r0, r1);	 Catch:{ Exception -> 0x0119 }
        goto L_0x00f3;
    L_0x015a:
        r0 = move-exception;
        r1 = 0;
        r11.f536h = r1;	 Catch:{ Exception -> 0x018d }
        r1 = r11.f534f;	 Catch:{ Exception -> 0x018d }
        if (r1 == 0) goto L_0x0167;	 Catch:{ Exception -> 0x018d }
    L_0x0162:
        r1 = r11.f534f;	 Catch:{ Exception -> 0x018d }
        r1.disconnect();	 Catch:{ Exception -> 0x018d }
    L_0x0167:
        throw r0;
    L_0x0168:
        r0 = r11.f529a;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r1 = "";	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        r0.f527a = r1;	 Catch:{ Exception -> 0x00df, all -> 0x010b }
        goto L_0x0144;
    L_0x0170:
        r0 = move-exception;
        r0 = "CLOUD";	 Catch:{ Exception -> 0x0119 }
        r1 = "fout类中释放资源出错";	 Catch:{ Exception -> 0x0119 }
        com.ihealth.communication.utils.Log.e(r0, r1);	 Catch:{ Exception -> 0x0119 }
        goto L_0x00f3;	 Catch:{ Exception -> 0x0119 }
    L_0x017a:
        r1 = move-exception;	 Catch:{ Exception -> 0x0119 }
        r1 = "CLOUD";	 Catch:{ Exception -> 0x0119 }
        r2 = "fout类中释放资源出错";	 Catch:{ Exception -> 0x0119 }
        com.ihealth.communication.utils.Log.e(r1, r2);	 Catch:{ Exception -> 0x0119 }
        goto L_0x0118;
    L_0x0183:
        r0 = move-exception;
        r0 = "CLOUD";
        r1 = "conn类中释放资源出错";
        com.ihealth.communication.utils.Log.e(r0, r1);
        goto L_0x00ff;
    L_0x018d:
        r1 = move-exception;
        r1 = "CLOUD";
        r2 = "conn类中释放资源出错";
        com.ihealth.communication.utils.Log.e(r1, r2);
        goto L_0x0167;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ihealth.communication.cloud.a.h.run():void");
    }
}
