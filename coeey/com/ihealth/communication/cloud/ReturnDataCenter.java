package com.ihealth.communication.cloud;

import java.util.ArrayList;

public class ReturnDataCenter {
    private String f489a;
    private ArrayList f490b = new ArrayList();
    private String f491c;
    private String f492d;

    public ArrayList getApiInfo() {
        return this.f490b;
    }

    public String getAuthorizationTitle() {
        return this.f491c;
    }

    public String getContent() {
        return this.f489a;
    }

    public String getReturncode() {
        return this.f492d;
    }

    public void setApiInfo(ArrayList apiInfo) {
        this.f490b = apiInfo;
    }

    public void setAuthorizationTitle(String authorizationTitle) {
        this.f491c = authorizationTitle;
    }

    public void setContent(String content) {
        this.f489a = content;
    }

    public void setReturncode(String returncode) {
        this.f492d = returncode;
    }
}
