package io.realm;

public enum Case {
    SENSITIVE(true),
    INSENSITIVE(false);
    
    private final boolean value;

    private Case(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return this.value;
    }
}
