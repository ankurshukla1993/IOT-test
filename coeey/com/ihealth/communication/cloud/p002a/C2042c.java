package com.ihealth.communication.cloud.p002a;

import com.facebook.internal.ServerProtocol;
import com.facebook.stetho.server.http.HttpStatus;
import com.ihealth.communication.manager.iHealthDevicesIDPS;
import com.ihealth.communication.utils.Log;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class C2042c {
    public int f509a;
    public float f510b;
    public String f511c;
    public String f512d;
    public String f513e;
    public C2043d f514f;
    private int f515g = 1;
    private C2046g f516h;

    public void m363a(int i) {
        this.f515g = i;
    }

    public boolean m364a() {
        if (this.f516h == null) {
            return false;
        }
        this.f516h.m376b();
        return true;
    }

    public boolean m365a(String str, String str2, String str3, String str4, String str5) {
        this.f509a = 0;
        this.f510b = 0.0f;
        this.f511c = new String();
        this.f512d = new String();
        this.f513e = new String();
        this.f514f = new C2043d(this);
        try {
            Map hashMap = new HashMap();
            hashMap.put("sc", "4c60fce10c154ff2a3ebd4fbe040e782");
            hashMap.put("sv", "87a5c5fde8a1413bb34f1059e6a9a377");
            hashMap.put(iHealthDevicesIDPS.HARDWAREVERSION, str4);
            hashMap.put("productmodel", str3);
            hashMap.put("productnum", str2);
            hashMap.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, str);
            hashMap.put("testcode", str5);
            hashMap.put("Country", Locale.getDefault().getCountry());
            hashMap.put("Lan", Locale.getDefault().getLanguage());
            String str6 = "https://api.ihealthlabs.com:443/upgrade/checkverson2013.htm?";
            if (this.f515g == HttpStatus.HTTP_NOT_FOUND) {
                str6 = "http://test.ihealthlabs.com:8000/upgrade/checkverson2013.htm?";
            }
            this.f513e = hashMap.toString();
            Log.i("CommCloudAMInstall", "AMVersionCheckPOST213 String= " + this.f513e);
            this.f512d = new C2049j().m381a(str6, hashMap, "UTF-8");
            Log.i("UpDeviceManager", "AMVersionCheckReturn213  String =" + this.f512d);
            if (this.f512d.equals("")) {
                Log.e("CommCloudAMInstall", "cloudAMVersionCheck213 返回信息检测，信息为空");
                return false;
            }
            JSONArray jSONArray;
            int i;
            JSONObject jSONObject = new JSONObject(this.f512d);
            this.f514f.f517a = jSONObject.getString("Description");
            this.f514f.f518b = jSONObject.getString("latestversion");
            this.f514f.f519c = jSONObject.getInt("mandatoryupgrade");
            if (jSONObject.getString("BeforeImage").equals("[]")) {
                this.f514f.f520d = new String[0];
            } else {
                jSONArray = new JSONArray(jSONObject.getString("BeforeImage"));
                int length = jSONArray.length();
                this.f514f.f520d = new String[length];
                for (i = 0; i < length; i++) {
                    this.f514f.f520d[i] = jSONArray.optString(i);
                }
            }
            if (jSONObject.getString("AfterImage").equals("[]")) {
                this.f514f.f521e = new String[0];
            } else {
                jSONArray = new JSONArray(jSONObject.getString("BeforeImage"));
                int length2 = jSONArray.length();
                this.f514f.f521e = new String[length2];
                for (i = 0; i < length2; i++) {
                    this.f514f.f521e[i] = jSONArray.optString(i);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean m366a(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, C2048i c2048i) {
        this.f509a = 0;
        this.f510b = 0.0f;
        this.f511c = new String();
        this.f512d = new String();
        this.f513e = new String();
        try {
            Map hashMap = new HashMap();
            hashMap.put("sc", "4c60fce10c154ff2a3ebd4fbe040e782");
            hashMap.put("sv", "ace761655f754e11843fcd408517db5d");
            hashMap.put(ServerProtocol.FALLBACK_DIALOG_PARAM_VERSION, str);
            hashMap.put("productnum", str2);
            hashMap.put("productmodel", str3);
            hashMap.put(iHealthDevicesIDPS.HARDWAREVERSION, str4);
            hashMap.put("testcode", str5);
            hashMap.put("filetype", i + "");
            hashMap.put("beginblock", i2 + "");
            hashMap.put("endblock", i3 + "");
            String str6 = "https://api.ihealthlabs.com:443/upgrade/downloadblock.htm?";
            if (this.f515g == HttpStatus.HTTP_NOT_FOUND) {
                str6 = "http://test.ihealthlabs.com:8000/upgrade/downloadblock.htm?";
            }
            this.f513e = hashMap.toString();
            Log.i("CommCloudAMInstall", "AMVersionDownloadGET = " + this.f513e);
            this.f516h = new C2046g();
            this.f512d = this.f516h.m375a(str6, hashMap, "UTF-8", i, c2048i);
            if (!this.f512d.equals("FAILfile") && !this.f512d.equals("")) {
                return true;
            }
            Log.e("CommCloudAMInstall", "AMVersionDownloadRETURN 返回信息检测，信息为空");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
