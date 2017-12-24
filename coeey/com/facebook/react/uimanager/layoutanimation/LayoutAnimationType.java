package com.facebook.react.uimanager.layoutanimation;

enum LayoutAnimationType {
    CREATE("create"),
    UPDATE("update"),
    DELETE("delete");
    
    private final String mName;

    private LayoutAnimationType(String name) {
        this.mName = name;
    }

    public String toString() {
        return this.mName;
    }
}
