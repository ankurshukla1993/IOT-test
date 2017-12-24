package com.ihealth.communication.cloud.data;

public class BP_ReturnData {
    private String f602a;
    private String f603b;
    private String f604c;
    private String f605d;
    private long f606e;

    public String getAccessToken() {
        return this.f603b;
    }

    public long getExpires() {
        return this.f606e;
    }

    public String getRefreshToken() {
        return this.f604c;
    }

    public String getRegionHost() {
        return this.f605d;
    }

    public String getResultMessage() {
        return this.f602a;
    }

    public void setAccessToken(String accessToken) {
        this.f603b = accessToken;
    }

    public void setExpires(long expires) {
        this.f606e = expires;
    }

    public void setRefreshToken(String refreshToken) {
        this.f604c = refreshToken;
    }

    public void setRegionHost(String regionHost) {
        this.f605d = regionHost;
    }

    public void setResultMessage(String resultMessage) {
        this.f602a = resultMessage;
    }
}
