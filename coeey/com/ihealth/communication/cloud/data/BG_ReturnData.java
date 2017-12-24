package com.ihealth.communication.cloud.data;

public class BG_ReturnData {
    private String f585a;
    private String f586b;
    private String f587c;
    private String f588d;
    private long f589e;

    public String getAccessToken() {
        return this.f586b;
    }

    public long getExpires() {
        return this.f589e;
    }

    public String getRefreshToken() {
        return this.f587c;
    }

    public String getRegionHost() {
        return this.f588d;
    }

    public String getResultMessage() {
        return this.f585a;
    }

    public void setAccessToken(String accessToken) {
        this.f586b = accessToken;
    }

    public void setExpires(long expires) {
        this.f589e = expires;
    }

    public void setRefreshToken(String refreshToken) {
        this.f587c = refreshToken;
    }

    public void setRegionHost(String regionHost) {
        this.f588d = regionHost;
    }

    public void setResultMessage(String resultMessage) {
        this.f585a = resultMessage;
    }
}
