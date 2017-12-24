package com.facebook.react.flat;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;

final class ElementsList<E> {
    private Scope mCurrentScope = null;
    private final ArrayDeque<E> mElements = new ArrayDeque();
    private final E[] mEmptyArray;
    private int mScopeIndex = 0;
    private final ArrayList<Scope> mScopesStack = new ArrayList();

    private static final class Scope {
        Object[] elements;
        int index;
        int size;

        private Scope() {
        }
    }

    public ElementsList(E[] emptyArray) {
        this.mEmptyArray = emptyArray;
        this.mScopesStack.add(this.mCurrentScope);
    }

    public void start(Object[] elements) {
        pushScope();
        Scope scope = getCurrentScope();
        scope.elements = elements;
        scope.index = 0;
        scope.size = this.mElements.size();
    }

    public E[] finish() {
        Scope scope = getCurrentScope();
        popScope();
        E[] result = null;
        int size = this.mElements.size() - scope.size;
        if (scope.index != scope.elements.length) {
            result = extractElements(size);
        } else {
            for (int i = 0; i < size; i++) {
                this.mElements.pollLast();
            }
        }
        scope.elements = null;
        return result;
    }

    public void add(E element) {
        Scope scope = getCurrentScope();
        if (scope.index >= scope.elements.length || scope.elements[scope.index] != element) {
            scope.index = Integer.MAX_VALUE;
        } else {
            scope.index++;
        }
        this.mElements.add(element);
    }

    public void clear() {
        if (getCurrentScope() != null) {
            throw new RuntimeException("Must call finish() for every start() call being made.");
        }
        this.mElements.clear();
    }

    private E[] extractElements(int size) {
        if (size == 0) {
            return this.mEmptyArray;
        }
        Object[] elements = (Object[]) ((Object[]) Array.newInstance(this.mEmptyArray.getClass().getComponentType(), size));
        for (int i = size - 1; i >= 0; i--) {
            elements[i] = this.mElements.pollLast();
        }
        return elements;
    }

    private void pushScope() {
        this.mScopeIndex++;
        if (this.mScopeIndex == this.mScopesStack.size()) {
            this.mCurrentScope = new Scope();
            this.mScopesStack.add(this.mCurrentScope);
            return;
        }
        this.mCurrentScope = (Scope) this.mScopesStack.get(this.mScopeIndex);
    }

    private void popScope() {
        this.mScopeIndex--;
        this.mCurrentScope = (Scope) this.mScopesStack.get(this.mScopeIndex);
    }

    private Scope getCurrentScope() {
        return this.mCurrentScope;
    }
}
