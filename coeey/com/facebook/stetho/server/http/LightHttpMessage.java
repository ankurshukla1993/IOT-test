package com.facebook.stetho.server.http;

import android.support.annotation.Nullable;
import java.util.ArrayList;

public class LightHttpMessage {
    public final ArrayList<String> headerNames = new ArrayList();
    public final ArrayList<String> headerValues = new ArrayList();

    public void addHeader(String name, String value) {
        this.headerNames.add(name);
        this.headerValues.add(value);
    }

    @Nullable
    public String getFirstHeaderValue(String name) {
        int N = this.headerNames.size();
        for (int i = 0; i < N; i++) {
            if (name.equals(this.headerNames.get(i))) {
                return (String) this.headerValues.get(i);
            }
        }
        return null;
    }

    public void reset() {
        this.headerNames.clear();
        this.headerValues.clear();
    }
}
