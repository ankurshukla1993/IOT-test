package com.facebook.imagepipeline.common;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.util.HashCodeUtil;
import java.util.Locale;

public class ResizeOptions {
    public final int height;
    public final int width;

    public ResizeOptions(int width, int height) {
        boolean z;
        boolean z2 = true;
        if (width > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (height <= 0) {
            z2 = false;
        }
        Preconditions.checkArgument(z2);
        this.width = width;
        this.height = height;
    }

    public int hashCode() {
        return HashCodeUtil.hashCode(this.width, this.height);
    }

    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ResizeOptions)) {
            return false;
        }
        ResizeOptions that = (ResizeOptions) other;
        if (this.width == that.width && this.height == that.height) {
            return true;
        }
        return false;
    }

    public String toString() {
        return String.format((Locale) null, "%dx%d", new Object[]{Integer.valueOf(this.width), Integer.valueOf(this.height)});
    }
}
