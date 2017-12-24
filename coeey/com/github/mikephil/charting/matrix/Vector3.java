package com.github.mikephil.charting.matrix;

import com.google.android.flexbox.FlexItem;

public final class Vector3 {
    public static final Vector3 UNIT_X = new Vector3(FlexItem.FLEX_SHRINK_DEFAULT, 0.0f, 0.0f);
    public static final Vector3 UNIT_Y = new Vector3(0.0f, FlexItem.FLEX_SHRINK_DEFAULT, 0.0f);
    public static final Vector3 UNIT_Z = new Vector3(0.0f, 0.0f, FlexItem.FLEX_SHRINK_DEFAULT);
    public static final Vector3 ZERO = new Vector3(0.0f, 0.0f, 0.0f);
    public float f180x;
    public float f181y;
    public float f182z;

    public Vector3(float[] array) {
        set(array[0], array[1], array[2]);
    }

    public Vector3(float xValue, float yValue, float zValue) {
        set(xValue, yValue, zValue);
    }

    public Vector3(Vector3 other) {
        set(other);
    }

    public final void add(Vector3 other) {
        this.f180x += other.f180x;
        this.f181y += other.f181y;
        this.f182z += other.f182z;
    }

    public final void add(float otherX, float otherY, float otherZ) {
        this.f180x += otherX;
        this.f181y += otherY;
        this.f182z += otherZ;
    }

    public final void subtract(Vector3 other) {
        this.f180x -= other.f180x;
        this.f181y -= other.f181y;
        this.f182z -= other.f182z;
    }

    public final void subtractMultiple(Vector3 other, float multiplicator) {
        this.f180x -= other.f180x * multiplicator;
        this.f181y -= other.f181y * multiplicator;
        this.f182z -= other.f182z * multiplicator;
    }

    public final void multiply(float magnitude) {
        this.f180x *= magnitude;
        this.f181y *= magnitude;
        this.f182z *= magnitude;
    }

    public final void multiply(Vector3 other) {
        this.f180x *= other.f180x;
        this.f181y *= other.f181y;
        this.f182z *= other.f182z;
    }

    public final void divide(float magnitude) {
        if (magnitude != 0.0f) {
            this.f180x /= magnitude;
            this.f181y /= magnitude;
            this.f182z /= magnitude;
        }
    }

    public final void set(Vector3 other) {
        this.f180x = other.f180x;
        this.f181y = other.f181y;
        this.f182z = other.f182z;
    }

    public final void set(float xValue, float yValue, float zValue) {
        this.f180x = xValue;
        this.f181y = yValue;
        this.f182z = zValue;
    }

    public final float dot(Vector3 other) {
        return ((this.f180x * other.f180x) + (this.f181y * other.f181y)) + (this.f182z * other.f182z);
    }

    public final Vector3 cross(Vector3 other) {
        return new Vector3((this.f181y * other.f182z) - (this.f182z * other.f181y), (this.f182z * other.f180x) - (this.f180x * other.f182z), (this.f180x * other.f181y) - (this.f181y * other.f180x));
    }

    public final float length() {
        return (float) Math.sqrt((double) length2());
    }

    public final float length2() {
        return ((this.f180x * this.f180x) + (this.f181y * this.f181y)) + (this.f182z * this.f182z);
    }

    public final float distance2(Vector3 other) {
        float dx = this.f180x - other.f180x;
        float dy = this.f181y - other.f181y;
        float dz = this.f182z - other.f182z;
        return ((dx * dx) + (dy * dy)) + (dz * dz);
    }

    public final float normalize() {
        float magnitude = length();
        if (magnitude != 0.0f) {
            this.f180x /= magnitude;
            this.f181y /= magnitude;
            this.f182z /= magnitude;
        }
        return magnitude;
    }

    public final void zero() {
        set(0.0f, 0.0f, 0.0f);
    }

    public final boolean pointsInSameDirection(Vector3 other) {
        return dot(other) > 0.0f;
    }
}
