package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.json.annotation.JsonValue;

public enum Origin {
    INJECTED("injected"),
    USER_AGENT("user-agent"),
    INSPECTOR("inspector"),
    REGULAR("regular");
    
    private final String mValue;

    private Origin(String value) {
        this.mValue = value;
    }

    @JsonValue
    public String getProtocolValue() {
        return this.mValue;
    }
}
