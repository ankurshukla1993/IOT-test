package com.facebook.stetho.inspector.network;

import android.annotation.SuppressLint;

@SuppressLint({"BadMethodUse-java.lang.String.length"})
class MimeMatcher$MimeMatcherRule {
    private final boolean mHasWildcard;
    private final String mMatchPrefix;
    private final T mResultIfMatched;
    final /* synthetic */ MimeMatcher this$0;

    public MimeMatcher$MimeMatcherRule(MimeMatcher mimeMatcher, String ruleExpression, T resultIfMatched) {
        this.this$0 = mimeMatcher;
        if (ruleExpression.endsWith("*")) {
            this.mHasWildcard = true;
            this.mMatchPrefix = ruleExpression.substring(0, ruleExpression.length() - 1);
        } else {
            this.mHasWildcard = false;
            this.mMatchPrefix = ruleExpression;
        }
        if (this.mMatchPrefix.contains("*")) {
            throw new IllegalArgumentException("Multiple wildcards present in rule expression " + ruleExpression);
        }
        this.mResultIfMatched = resultIfMatched;
    }

    public boolean match(String mimeType) {
        if (!mimeType.startsWith(this.mMatchPrefix)) {
            return false;
        }
        if (this.mHasWildcard || mimeType.length() == this.mMatchPrefix.length()) {
            return true;
        }
        return false;
    }

    public T getResultIfMatched() {
        return this.mResultIfMatched;
    }
}
