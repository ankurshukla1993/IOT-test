package com.ihealth.communication.cloud.data;

import java.util.ArrayList;

public class AM_ReturnData {
    private String f560a;
    private String f561b;
    private String f562c;
    private String f563d;
    private long f564e;
    private int f565f;
    private String f566g;
    private ArrayList f567h;

    public String getAccessToken() {
        return this.f561b;
    }

    public long getExpires() {
        return this.f564e;
    }

    public int getLeftNumber() {
        return this.f565f;
    }

    public String getOwnerId() {
        return this.f566g;
    }

    public String getRefreshToken() {
        return this.f562c;
    }

    public String getRegionHost() {
        return this.f563d;
    }

    public String getResultMessage() {
        return this.f560a;
    }

    public ArrayList getWtArr() {
        return this.f567h;
    }

    public void setAccessToken(String accessToken) {
        this.f561b = accessToken;
    }

    public void setExpires(long expires) {
        this.f564e = expires;
    }

    public void setLeftNumber(int leftNumber) {
        this.f565f = leftNumber;
    }

    public void setOwnerId(String ownerId) {
        this.f566g = ownerId;
    }

    public void setRefreshToken(String refreshToken) {
        this.f562c = refreshToken;
    }

    public void setRegionHost(String regionHost) {
        this.f563d = regionHost;
    }

    public void setResultMessage(String resultMessage) {
        this.f560a = resultMessage;
    }

    public void setWtArr(ArrayList wtArr) {
        this.f567h = wtArr;
    }
}
