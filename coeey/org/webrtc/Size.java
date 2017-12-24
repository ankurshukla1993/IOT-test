package org.webrtc;

import com.facebook.internal.NativeProtocol;

public class Size {
    public int height;
    public int width;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }

    public boolean equals(Object other) {
        if (!(other instanceof Size)) {
            return false;
        }
        Size otherSize = (Size) other;
        if (this.width == otherSize.width && this.height == otherSize.height) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((NativeProtocol.MESSAGE_GET_ACCESS_TOKEN_REPLY * this.width) + 1) + this.height;
    }
}
