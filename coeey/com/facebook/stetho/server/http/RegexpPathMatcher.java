package com.facebook.stetho.server.http;

import java.util.regex.Pattern;

public class RegexpPathMatcher implements PathMatcher {
    private final Pattern mPattern;

    public RegexpPathMatcher(Pattern pattern) {
        this.mPattern = pattern;
    }

    public boolean match(String path) {
        return this.mPattern.matcher(path).matches();
    }
}
