package com.facebook.stetho.server.http;

public class ExactPathMatcher implements PathMatcher {
    private final String mPath;

    public ExactPathMatcher(String path) {
        this.mPath = path;
    }

    public boolean match(String path) {
        return this.mPath.equals(path);
    }
}
