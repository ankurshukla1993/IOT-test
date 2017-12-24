package com.google.protobuf;

import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.List;

public class Internal {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    public static final ByteBuffer EMPTY_BYTE_BUFFER = ByteBuffer.wrap(EMPTY_BYTE_ARRAY);

    public interface EnumLiteMap {
        EnumLite findValueByNumber(int i);
    }

    public interface EnumLite {
        int getNumber();
    }

    public static byte[] byteArrayDefaultValue(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (Throwable e) {
            throw new IllegalStateException("Java VM does not support a standard character set.", e);
        }
    }

    public static ByteBuffer byteBufferDefaultValue(String str) {
        return ByteBuffer.wrap(byteArrayDefaultValue(str));
    }

    public static ByteString bytesDefaultValue(String str) {
        try {
            return ByteString.copyFrom(str.getBytes("ISO-8859-1"));
        } catch (Throwable e) {
            throw new IllegalStateException("Java VM does not support a standard character set.", e);
        }
    }

    public static ByteBuffer copyByteBuffer(ByteBuffer byteBuffer) {
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.clear();
        ByteBuffer allocate = ByteBuffer.allocate(duplicate.capacity());
        allocate.put(duplicate);
        allocate.clear();
        return allocate;
    }

    public static boolean equals(List list, List list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals((byte[]) list.get(i), (byte[]) list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsByteBuffer(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        return byteBuffer.capacity() != byteBuffer2.capacity() ? false : byteBuffer.duplicate().clear().equals(byteBuffer2.duplicate().clear());
    }

    public static boolean equalsByteBuffer(List list, List list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!equalsByteBuffer((ByteBuffer) list.get(i), (ByteBuffer) list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    public static int hashBoolean(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int hashCode(List list) {
        int i = 1;
        for (byte[] hashCode : list) {
            i = hashCode(hashCode) + (i * 31);
        }
        return i;
    }

    public static int hashCode(byte[] bArr) {
        return LiteralByteString.hashCode(bArr);
    }

    public static int hashCodeByteBuffer(ByteBuffer byteBuffer) {
        int i = 4096;
        int hashCode;
        if (byteBuffer.hasArray()) {
            hashCode = LiteralByteString.hashCode(byteBuffer.capacity(), byteBuffer.array(), byteBuffer.arrayOffset(), byteBuffer.capacity());
            if (hashCode != 0) {
                return hashCode;
            }
        }
        if (byteBuffer.capacity() <= 4096) {
            i = byteBuffer.capacity();
        }
        byte[] bArr = new byte[i];
        ByteBuffer duplicate = byteBuffer.duplicate();
        duplicate.clear();
        hashCode = byteBuffer.capacity();
        while (duplicate.remaining() > 0) {
            int remaining = duplicate.remaining() <= i ? duplicate.remaining() : i;
            duplicate.get(bArr, 0, remaining);
            hashCode = LiteralByteString.hashCode(hashCode, bArr, 0, remaining);
        }
        if (hashCode != 0) {
            return hashCode;
        }
        return 1;
    }

    public static int hashCodeByteBuffer(List list) {
        int i = 1;
        for (ByteBuffer hashCodeByteBuffer : list) {
            i = hashCodeByteBuffer(hashCodeByteBuffer) + (i * 31);
        }
        return i;
    }

    public static int hashEnum(EnumLite enumLite) {
        return enumLite.getNumber();
    }

    public static int hashEnumList(List list) {
        int i = 1;
        for (EnumLite hashEnum : list) {
            i = hashEnum(hashEnum) + (i * 31);
        }
        return i;
    }

    public static int hashLong(long j) {
        return (int) ((j >>> 32) ^ j);
    }

    public static boolean isValidUtf8(ByteString byteString) {
        return byteString.isValidUtf8();
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return Utf8.isValidUtf8(bArr);
    }

    public static String stringDefaultValue(String str) {
        try {
            return new String(str.getBytes("ISO-8859-1"), "UTF-8");
        } catch (Throwable e) {
            throw new IllegalStateException("Java VM does not support a standard character set.", e);
        }
    }

    public static byte[] toByteArray(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (Throwable e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }

    public static String toStringUtf8(byte[] bArr) {
        try {
            return new String(bArr, "UTF-8");
        } catch (Throwable e) {
            throw new RuntimeException("UTF-8 not supported?", e);
        }
    }
}
