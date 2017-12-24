package io.realm;

public enum Sort {
    ASCENDING(true),
    DESCENDING(false);
    
    private final boolean value;

    private Sort(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }
}
