package com.facebook.react.uimanager.layoutanimation;

enum AnimatedPropertyType {
    OPACITY("opacity"),
    SCALE_XY("scaleXY");
    
    private final String mName;

    private AnimatedPropertyType(String name) {
        this.mName = name;
    }

    public static AnimatedPropertyType fromString(String name) {
        for (AnimatedPropertyType property : values()) {
            if (property.toString().equalsIgnoreCase(name)) {
                return property;
            }
        }
        throw new IllegalArgumentException("Unsupported animated property : " + name);
    }

    public String toString() {
        return this.mName;
    }
}
