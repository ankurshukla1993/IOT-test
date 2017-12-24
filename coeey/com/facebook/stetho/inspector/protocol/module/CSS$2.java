package com.facebook.stetho.inspector.protocol.module;

import com.facebook.stetho.common.ListUtil;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.inspector.elements.Origin;
import com.facebook.stetho.inspector.elements.StyleAccumulator;
import com.facebook.stetho.inspector.elements.StyleRuleNameAccumulator;
import com.facebook.stetho.inspector.protocol.module.CSS.CSSProperty;
import com.facebook.stetho.inspector.protocol.module.CSS.CSSRule;
import com.facebook.stetho.inspector.protocol.module.CSS.CSSStyle;
import com.facebook.stetho.inspector.protocol.module.CSS.GetMatchedStylesForNodeRequest;
import com.facebook.stetho.inspector.protocol.module.CSS.GetMatchedStylesForNodeResult;
import com.facebook.stetho.inspector.protocol.module.CSS.RuleMatch;
import com.facebook.stetho.inspector.protocol.module.CSS.Selector;
import com.facebook.stetho.inspector.protocol.module.CSS.SelectorList;
import java.util.ArrayList;
import java.util.Collections;

class CSS$2 implements Runnable {
    final /* synthetic */ CSS this$0;
    final /* synthetic */ GetMatchedStylesForNodeRequest val$request;
    final /* synthetic */ GetMatchedStylesForNodeResult val$result;

    CSS$2(CSS this$0, GetMatchedStylesForNodeRequest getMatchedStylesForNodeRequest, GetMatchedStylesForNodeResult getMatchedStylesForNodeResult) {
        this.this$0 = this$0;
        this.val$request = getMatchedStylesForNodeRequest;
        this.val$result = getMatchedStylesForNodeResult;
    }

    public void run() {
        final Object elementForNodeId = CSS.access$200(this.this$0).getElementForNodeId(this.val$request.nodeId);
        if (elementForNodeId == null) {
            LogUtil.m201w("Failed to get style of an element that does not exist, nodeid=" + this.val$request.nodeId);
        } else {
            CSS.access$200(this.this$0).getElementStyleRuleNames(elementForNodeId, new StyleRuleNameAccumulator() {
                public void store(String ruleName, boolean editable) {
                    final ArrayList<CSSProperty> properties = new ArrayList();
                    RuleMatch match = new RuleMatch(null);
                    match.matchingSelectors = ListUtil.newImmutableList(Integer.valueOf(0));
                    Selector selector = new Selector(null);
                    selector.value = ruleName;
                    CSSRule rule = new CSSRule(null);
                    rule.origin = Origin.REGULAR;
                    rule.selectorList = new SelectorList(null);
                    rule.selectorList.selectors = ListUtil.newImmutableList(selector);
                    rule.style = new CSSStyle(null);
                    rule.style.cssProperties = properties;
                    rule.style.shorthandEntries = Collections.emptyList();
                    if (editable) {
                        rule.style.styleSheetId = String.format("%s.%s", new Object[]{Integer.toString(CSS$2.this.val$request.nodeId), selector.value});
                    }
                    CSS.access$200(CSS$2.this.this$0).getElementStyles(elementForNodeId, ruleName, new StyleAccumulator() {
                        public void store(String name, String value, boolean isDefault) {
                            CSSProperty property = new CSSProperty(null);
                            property.name = name;
                            property.value = value;
                            properties.add(property);
                        }
                    });
                    match.rule = rule;
                    CSS$2.this.val$result.matchedCSSRules.add(match);
                }
            });
        }
    }
}
