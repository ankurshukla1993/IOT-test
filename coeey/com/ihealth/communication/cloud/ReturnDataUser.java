package com.ihealth.communication.cloud;

public class ReturnDataUser {
    private String f493a;
    private String f494b;
    private String f495c;
    private long f496d;
    private String f497e;
    private int f498f;
    private int f499g;
    private String f500h;

    public String getAccessToken() {
        return this.f495c;
    }

    public String getApiName() {
        return this.f494b;
    }

    public long getExpires() {
        return this.f496d;
    }

    public int getId() {
        return this.f498f;
    }

    public String getRefreshToken() {
        return this.f497e;
    }

    public String getRegionHost() {
        return this.f500h;
    }

    public String getResultCode() {
        return this.f493a;
    }

    public int getStatus() {
        return this.f499g;
    }

    public void setAccessToken(String accessToken) {
        this.f495c = accessToken;
    }

    public void setApiName(String apiName) {
        this.f494b = apiName;
    }

    public void setExpires(long expires) {
        this.f496d = expires;
    }

    public void setId(int id) {
        this.f498f = id;
    }

    public void setRefreshToken(String refreshToken) {
        this.f497e = refreshToken;
    }

    public void setRegionHost(String regionHost) {
        this.f500h = regionHost;
    }

    public void setResultCode(String resultCode) {
        this.f493a = resultCode;
    }

    public void setStatus(int status) {
        this.f499g = status;
    }

    public String toString() {
        return "ReturnDataUser{resultCode='" + this.f493a + '\'' + ", apiName='" + this.f494b + '\'' + ", accessToken='" + this.f495c + '\'' + ", expires=" + this.f496d + ", refreshToken='" + this.f497e + '\'' + ", id=" + this.f498f + ", Status=" + this.f499g + ", regionHost='" + this.f500h + '\'' + '}';
    }
}
