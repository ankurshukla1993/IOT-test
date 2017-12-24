package com.facebook.react.uimanager.layoutanimation;

enum InterpolatorType {
    LINEAR("linear"),
    EASE_IN("easeIn"),
    EASE_OUT("easeOut"),
    EASE_IN_EASE_OUT("easeInEaseOut"),
    SPRING("spring");
    
    private final String mName;

    private InterpolatorType(String name) {
        this.mName = name;
    }

    public static InterpolatorType fromString(String name) {
        for (InterpolatorType type : values()) {
            if (type.toString().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unsupported interpolation type : " + name);
    }

    public String toString() {
        return this.mName;
    }
}
