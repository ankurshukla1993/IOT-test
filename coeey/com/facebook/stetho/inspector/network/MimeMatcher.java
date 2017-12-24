package com.facebook.stetho.inspector.network;

import java.util.ArrayList;
import javax.annotation.Nullable;

public class MimeMatcher<T> {
    private final ArrayList<MimeMatcherRule> mRuleMap = new ArrayList();

    public void addRule(String ruleExpression, T resultIfMatched) {
        this.mRuleMap.add(new MimeMatcherRule(this, ruleExpression, resultIfMatched));
    }

    public void clear() {
        this.mRuleMap.clear();
    }

    @Nullable
    public T match(String mimeT) {
        int ruleMapN = this.mRuleMap.size();
        for (int i = 0; i < ruleMapN; i++) {
            MimeMatcherRule rule = (MimeMatcherRule) this.mRuleMap.get(i);
            if (rule.match(mimeT)) {
                return rule.getResultIfMatched();
            }
        }
        return null;
    }
}
