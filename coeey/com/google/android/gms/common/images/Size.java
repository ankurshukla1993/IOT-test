package com.google.android.gms.common.images;

public final class Size {
    private final int zzakw;
    private final int zzakx;

    public Size(int i, int i2) {
        this.zzakw = i;
        this.zzakx = i2;
    }

    public static Size parseSize(String str) throws NumberFormatException {
        if (str == null) {
            throw new IllegalArgumentException("string must not be null");
        }
        int indexOf = str.indexOf(42);
        if (indexOf < 0) {
            indexOf = str.indexOf(120);
        }
        if (indexOf < 0) {
            throw zzfy(str);
        }
        try {
            return new Size(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
        } catch (NumberFormatException e) {
            throw zzfy(str);
        }
    }

    private static NumberFormatException zzfy(String str) {
        throw new NumberFormatException(new StringBuilder(String.valueOf(str).length() + 16).append("Invalid Size: \"").append(str).append("\"").toString());
    }

    public final boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        return this.zzakw == size.zzakw && this.zzakx == size.zzakx;
    }

    public final int getHeight() {
        return this.zzakx;
    }

    public final int getWidth() {
        return this.zzakw;
    }

    public final int hashCode() {
        return this.zzakx ^ ((this.zzakw << 16) | (this.zzakw >>> 16));
    }

    public final String toString() {
        int i = this.zzakw;
        return i + "x" + this.zzakx;
    }
}
