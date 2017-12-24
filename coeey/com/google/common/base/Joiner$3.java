package com.google.common.base;

import java.util.AbstractList;

class Joiner$3 extends AbstractList<Object> {
    final /* synthetic */ Object val$first;
    final /* synthetic */ Object[] val$rest;
    final /* synthetic */ Object val$second;

    Joiner$3(Object[] objArr, Object obj, Object obj2) {
        this.val$rest = objArr;
        this.val$first = obj;
        this.val$second = obj2;
    }

    public int size() {
        return this.val$rest.length + 2;
    }

    public Object get(int index) {
        switch (index) {
            case 0:
                return this.val$first;
            case 1:
                return this.val$second;
            default:
                return this.val$rest[index - 2];
        }
    }
}
