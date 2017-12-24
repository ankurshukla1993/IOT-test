package com.ihealth.communication.cloud.data;

public class Date_TB_HS6MeasureResult {
    private String f904a;
    private String f905b;
    private long f906c;
    private int f907d;
    private int f908e;
    private int f909f;
    private int f910g;
    private int f911h;
    private String f912i;
    private String f913j;
    private String f914k;
    private String[] f915l;
    private String[] f916m;
    private int f917n;
    private int f918o;
    private long f919p;
    private int f920q;
    private double f921r;
    private int f922s;
    private int f923t;

    public Date_TB_HS6MeasureResult() {
        this.f904a = "";
        this.f905b = "";
        this.f912i = "";
        this.f913j = "";
        this.f914k = "";
        this.f915l = new String[0];
        this.f916m = new String[0];
    }

    public Date_TB_HS6MeasureResult(String mac, String model, long ts, int status, int action, int position, String latestversion, String mandatoryupgrade, String description, String[] beforeimage, String[] afterimage, int changetype, int agree, long time, int timezone, int temperature, int humidity, int light) {
        int i;
        this.f904a = "";
        this.f905b = "";
        this.f912i = "";
        this.f913j = "";
        this.f914k = "";
        this.f915l = new String[0];
        this.f916m = new String[0];
        this.f904a = mac;
        this.f905b = model;
        this.f906c = ts;
        this.f907d = status;
        this.f908e = action;
        this.f909f = position;
        this.f912i = latestversion;
        this.f913j = mandatoryupgrade;
        this.f914k = description;
        this.f915l = new String[beforeimage.length];
        for (i = 0; i < beforeimage.length; i++) {
            this.f915l[i] = beforeimage[i];
        }
        this.f916m = new String[afterimage.length];
        for (i = 0; i < afterimage.length; i++) {
            this.f916m[i] = afterimage[i];
        }
        this.f917n = changetype;
        this.f918o = agree;
        this.f919p = time;
        this.f920q = timezone;
        this.f921r = (double) temperature;
        this.f922s = humidity;
        this.f923t = light;
    }

    public int getAction() {
        return this.f908e;
    }

    public String[] getAfterImage() {
        return this.f916m;
    }

    public int getAgree() {
        return this.f918o;
    }

    public String[] getBeforeImage() {
        return this.f915l;
    }

    public int getBindNum() {
        return this.f911h;
    }

    public int getChangeType() {
        return this.f917n;
    }

    public String getDescription() {
        return this.f914k;
    }

    public int getHumidity() {
        return this.f922s;
    }

    public String getLatestVersion() {
        return this.f912i;
    }

    public int getLight() {
        return this.f923t;
    }

    public String getMAC() {
        return this.f904a;
    }

    public String getMandatoryupgrade() {
        return this.f913j;
    }

    public String getModel() {
        return this.f905b;
    }

    public int getPosition() {
        return this.f909f;
    }

    public int getSetWifi() {
        return this.f910g;
    }

    public int getStatus() {
        return this.f907d;
    }

    public long getTS() {
        return this.f906c;
    }

    public double getTemperature() {
        return this.f921r;
    }

    public long getTime() {
        return this.f919p;
    }

    public int getTimeZone() {
        return this.f920q;
    }

    public void setAction(int action) {
        this.f908e = action;
    }

    public void setAfterImage(String[] afterimage) {
        this.f916m = new String[afterimage.length];
        for (int i = 0; i < afterimage.length; i++) {
            this.f916m[i] = afterimage[i];
        }
    }

    public void setAgree(int agree) {
        this.f918o = agree;
    }

    public void setBeforeImage(String[] beforeimage) {
        this.f915l = new String[beforeimage.length];
        for (int i = 0; i < beforeimage.length; i++) {
            this.f915l[i] = beforeimage[i];
        }
    }

    public void setBindNum(int bindNum) {
        this.f911h = bindNum;
    }

    public void setChangeType(int changetype) {
        this.f917n = changetype;
    }

    public void setDescription(String description) {
        this.f914k = description;
    }

    public void setHumidity(int humidity) {
        this.f922s = humidity;
    }

    public void setLatestVersion(String latestversion) {
        this.f912i = latestversion;
    }

    public void setLight(int light) {
        this.f923t = light;
    }

    public void setMAC(String mac) {
        this.f904a = mac;
    }

    public void setMandatoryupgrade(String mandatoryupgrade) {
        this.f913j = mandatoryupgrade;
    }

    public void setModel(String model) {
        this.f905b = model;
    }

    public void setPosition(int position) {
        this.f909f = position;
    }

    public void setSetWifi(int setWifi) {
        this.f910g = setWifi;
    }

    public void setStatus(int status) {
        this.f907d = status;
    }

    public void setTS(long ts) {
        this.f906c = ts;
    }

    public void setTemperature(double d) {
        this.f921r = d;
    }

    public void setTime(long time) {
        this.f919p = time;
    }

    public void setTimeZone(int timezone) {
        this.f920q = timezone;
    }
}
