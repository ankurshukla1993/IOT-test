package com.facebook.login;

public enum LoginBehavior {
    SSO_WITH_FALLBACK(true, true),
    SSO_ONLY(true, false),
    SUPPRESS_SSO(false, true);
    
    private final boolean allowsKatanaAuth;
    private final boolean allowsWebViewAuth;

    private LoginBehavior(boolean allowsKatanaAuth, boolean allowsWebViewAuth) {
        this.allowsKatanaAuth = allowsKatanaAuth;
        this.allowsWebViewAuth = allowsWebViewAuth;
    }

    boolean allowsKatanaAuth() {
        return this.allowsKatanaAuth;
    }

    boolean allowsWebViewAuth() {
        return this.allowsWebViewAuth;
    }
}
