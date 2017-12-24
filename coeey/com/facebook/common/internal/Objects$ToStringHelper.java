package com.facebook.common.internal;

import javax.annotation.Nullable;

public final class Objects$ToStringHelper {
    private final String className;
    private ValueHolder holderHead;
    private ValueHolder holderTail;
    private boolean omitNullValues;

    private static final class ValueHolder {
        String name;
        ValueHolder next;
        Object value;

        private ValueHolder() {
        }
    }

    private Objects$ToStringHelper(String className) {
        this.holderHead = new ValueHolder();
        this.holderTail = this.holderHead;
        this.omitNullValues = false;
        this.className = (String) Preconditions.checkNotNull(className);
    }

    public Objects$ToStringHelper omitNullValues() {
        this.omitNullValues = true;
        return this;
    }

    public Objects$ToStringHelper add(String name, @Nullable Object value) {
        return addHolder(name, value);
    }

    public Objects$ToStringHelper add(String name, boolean value) {
        return addHolder(name, String.valueOf(value));
    }

    public Objects$ToStringHelper add(String name, char value) {
        return addHolder(name, String.valueOf(value));
    }

    public Objects$ToStringHelper add(String name, double value) {
        return addHolder(name, String.valueOf(value));
    }

    public Objects$ToStringHelper add(String name, float value) {
        return addHolder(name, String.valueOf(value));
    }

    public Objects$ToStringHelper add(String name, int value) {
        return addHolder(name, String.valueOf(value));
    }

    public Objects$ToStringHelper add(String name, long value) {
        return addHolder(name, String.valueOf(value));
    }

    public Objects$ToStringHelper addValue(@Nullable Object value) {
        return addHolder(value);
    }

    public Objects$ToStringHelper addValue(boolean value) {
        return addHolder(String.valueOf(value));
    }

    public Objects$ToStringHelper addValue(char value) {
        return addHolder(String.valueOf(value));
    }

    public Objects$ToStringHelper addValue(double value) {
        return addHolder(String.valueOf(value));
    }

    public Objects$ToStringHelper addValue(float value) {
        return addHolder(String.valueOf(value));
    }

    public Objects$ToStringHelper addValue(int value) {
        return addHolder(String.valueOf(value));
    }

    public Objects$ToStringHelper addValue(long value) {
        return addHolder(String.valueOf(value));
    }

    public String toString() {
        boolean omitNullValuesSnapshot = this.omitNullValues;
        String nextSeparator = "";
        StringBuilder builder = new StringBuilder(32).append(this.className).append('{');
        ValueHolder valueHolder = this.holderHead.next;
        while (valueHolder != null) {
            if (!omitNullValuesSnapshot || valueHolder.value != null) {
                builder.append(nextSeparator);
                nextSeparator = ", ";
                if (valueHolder.name != null) {
                    builder.append(valueHolder.name).append('=');
                }
                builder.append(valueHolder.value);
            }
            valueHolder = valueHolder.next;
        }
        return builder.append('}').toString();
    }

    private ValueHolder addHolder() {
        ValueHolder valueHolder = new ValueHolder();
        this.holderTail.next = valueHolder;
        this.holderTail = valueHolder;
        return valueHolder;
    }

    private Objects$ToStringHelper addHolder(@Nullable Object value) {
        addHolder().value = value;
        return this;
    }

    private Objects$ToStringHelper addHolder(String name, @Nullable Object value) {
        ValueHolder valueHolder = addHolder();
        valueHolder.value = value;
        valueHolder.name = (String) Preconditions.checkNotNull(name);
        return this;
    }
}
