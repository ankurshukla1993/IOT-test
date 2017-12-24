package com.facebook.stetho.inspector.elements;

import com.facebook.stetho.common.Accumulator;

public final class ObjectDescriptor extends Descriptor<Object> {
    public void hook(Object element) {
    }

    public void unhook(Object element) {
    }

    public NodeType getNodeType(Object element) {
        return NodeType.ELEMENT_NODE;
    }

    public String getNodeName(Object element) {
        return element.getClass().getName();
    }

    public String getLocalName(Object element) {
        return getNodeName(element);
    }

    public String getNodeValue(Object element) {
        return null;
    }

    public void getChildren(Object element, Accumulator<Object> accumulator) {
    }

    public void getAttributes(Object element, AttributeAccumulator attributes) {
    }

    public void setAttributesAsText(Object element, String text) {
    }

    public void getStyleRuleNames(Object element, StyleRuleNameAccumulator accumulator) {
    }

    public void getStyles(Object element, String ruleName, StyleAccumulator accumulator) {
    }

    public void setStyle(Object element, String ruleName, String name, String value) {
    }

    public void getComputedStyles(Object element, ComputedStyleAccumulator accumulator) {
    }
}
