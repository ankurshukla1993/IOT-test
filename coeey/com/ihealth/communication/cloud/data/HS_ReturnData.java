package com.ihealth.communication.cloud.data;

import java.util.ArrayList;

public class HS_ReturnData {
    private String f934a;
    private String f935b;
    private String f936c;
    private String f937d;
    private long f938e;
    private int f939f;
    private String f940g;
    private ArrayList f941h;

    public String getAccessToken() {
        return this.f935b;
    }

    public long getExpires() {
        return this.f938e;
    }

    public int getLeftNumber() {
        return this.f939f;
    }

    public String getOwnerId() {
        return this.f940g;
    }

    public String getRefreshToken() {
        return this.f936c;
    }

    public String getRegionHost() {
        return this.f937d;
    }

    public String getResultMessage() {
        return this.f934a;
    }

    public ArrayList getWtArr() {
        return this.f941h;
    }

    public void setAccessToken(String accessToken) {
        this.f935b = accessToken;
    }

    public void setExpires(long expires) {
        this.f938e = expires;
    }

    public void setLeftNumber(int leftNumber) {
        this.f939f = leftNumber;
    }

    public void setOwnerId(String ownerId) {
        this.f940g = ownerId;
    }

    public void setRefreshToken(String refreshToken) {
        this.f936c = refreshToken;
    }

    public void setRegionHost(String regionHost) {
        this.f937d = regionHost;
    }

    public void setResultMessage(String resultMessage) {
        this.f934a = resultMessage;
    }

    public void setWtArr(ArrayList wtArr) {
        this.f941h = wtArr;
    }
}
