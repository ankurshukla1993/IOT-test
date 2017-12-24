package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Util;
import javax.annotation.Nullable;

public abstract class AbstractChainedDescriptor<E> extends Descriptor<E> implements ChainedDescriptor<E> {
    private Descriptor<? super E> mSuper;

    public void setSuper(Descriptor<? super E> superDescriptor) {
        Util.throwIfNull(superDescriptor);
        if (superDescriptor == this.mSuper) {
            return;
        }
        if (this.mSuper != null) {
            throw new IllegalStateException();
        }
        this.mSuper = superDescriptor;
    }

    final Descriptor<? super E> getSuper() {
        return this.mSuper;
    }

    public final void hook(E element) {
        verifyThreadAccess();
        this.mSuper.hook(element);
        onHook(element);
    }

    protected void onHook(E e) {
    }

    public final void unhook(E element) {
        verifyThreadAccess();
        onUnhook(element);
        this.mSuper.unhook(element);
    }

    protected void onUnhook(E e) {
    }

    public final NodeType getNodeType(E element) {
        return onGetNodeType(element);
    }

    protected NodeType onGetNodeType(E element) {
        return this.mSuper.getNodeType(element);
    }

    public final String getNodeName(E element) {
        return onGetNodeName(element);
    }

    protected String onGetNodeName(E element) {
        return this.mSuper.getNodeName(element);
    }

    public final String getLocalName(E element) {
        return onGetLocalName(element);
    }

    protected String onGetLocalName(E element) {
        return this.mSuper.getLocalName(element);
    }

    public final String getNodeValue(E element) {
        return onGetNodeValue(element);
    }

    @Nullable
    public String onGetNodeValue(E element) {
        return this.mSuper.getNodeValue(element);
    }

    public final void getChildren(E element, Accumulator<Object> children) {
        this.mSuper.getChildren(element, children);
        onGetChildren(element, children);
    }

    protected void onGetChildren(E e, Accumulator<Object> accumulator) {
    }

    public final void getAttributes(E element, AttributeAccumulator attributes) {
        this.mSuper.getAttributes(element, attributes);
        onGetAttributes(element, attributes);
    }

    protected void onGetAttributes(E e, AttributeAccumulator attributes) {
    }

    public final void setAttributesAsText(E element, String text) {
        onSetAttributesAsText(element, text);
    }

    protected void onSetAttributesAsText(E element, String text) {
        this.mSuper.setAttributesAsText(element, text);
    }

    public final void getStyleRuleNames(E element, StyleRuleNameAccumulator accumulator) {
        this.mSuper.getStyleRuleNames(element, accumulator);
        onGetStyleRuleNames(element, accumulator);
    }

    protected void onGetStyleRuleNames(E e, StyleRuleNameAccumulator accumulator) {
    }

    public final void getStyles(E element, String ruleName, StyleAccumulator accumulator) {
        this.mSuper.getStyles(element, ruleName, accumulator);
        onGetStyles(element, ruleName, accumulator);
    }

    protected void onGetStyles(E e, String ruleName, StyleAccumulator accumulator) {
    }

    public final void setStyle(E element, String ruleName, String name, String value) {
        this.mSuper.setStyle(element, ruleName, name, value);
        onSetStyle(element, ruleName, name, value);
    }

    protected void onSetStyle(E e, String ruleName, String name, String value) {
    }

    public void getComputedStyles(E element, ComputedStyleAccumulator accumulator) {
        this.mSuper.getComputedStyles(element, accumulator);
        onGetComputedStyles(element, accumulator);
    }

    protected void onGetComputedStyles(E e, ComputedStyleAccumulator accumulator) {
    }
}
