package com.ihealth.communication.cloud.data;

import java.util.ArrayList;

public class PO_ReturnData {
    private String f957a;
    private String f958b;
    private String f959c;
    private String f960d;
    private long f961e;
    private int f962f;
    private String f963g;
    private ArrayList f964h;

    public String getAccessToken() {
        return this.f958b;
    }

    public long getExpires() {
        return this.f961e;
    }

    public int getLeftNumber() {
        return this.f962f;
    }

    public String getOwnerId() {
        return this.f963g;
    }

    public String getRefreshToken() {
        return this.f959c;
    }

    public String getRegionHost() {
        return this.f960d;
    }

    public String getResultMessage() {
        return this.f957a;
    }

    public ArrayList getWtArr() {
        return this.f964h;
    }

    public void setAccessToken(String accessToken) {
        this.f958b = accessToken;
    }

    public void setExpires(long expires) {
        this.f961e = expires;
    }

    public void setLeftNumber(int leftNumber) {
        this.f962f = leftNumber;
    }

    public void setOwnerId(String ownerId) {
        this.f963g = ownerId;
    }

    public void setRefreshToken(String refreshToken) {
        this.f959c = refreshToken;
    }

    public void setRegionHost(String regionHost) {
        this.f960d = regionHost;
    }

    public void setResultMessage(String resultMessage) {
        this.f957a = resultMessage;
    }

    public void setWtArr(ArrayList wtArr) {
        this.f964h = wtArr;
    }
}
