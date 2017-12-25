package com.facebook.stetho.inspector.elements;

import java.util.ArrayList;

public final class Document$AttributeListAccumulator extends ArrayList<String> implements AttributeAccumulator {
    public void store(String name, String value) {
        add(name);
        add(value);
    }
}