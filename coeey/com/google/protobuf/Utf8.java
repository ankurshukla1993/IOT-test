package com.google.protobuf;

final class Utf8 {
    public static final int COMPLETE = 0;
    public static final int MALFORMED = -1;

    private Utf8() {
    }

    private static int incompleteStateFor(int i) {
        return i > -12 ? -1 : i;
    }

    private static int incompleteStateFor(int i, int i2) {
        return (i > -12 || i2 > -65) ? -1 : (i2 << 8) ^ i;
    }

    private static int incompleteStateFor(int i, int i2, int i3) {
        return (i > -12 || i2 > -65 || i3 > -65) ? -1 : ((i2 << 8) ^ i) ^ (i3 << 16);
    }

    private static int incompleteStateFor(byte[] bArr, int i, int i2) {
        int i3 = bArr[i - 1];
        switch (i2 - i) {
            case 0:
                return incompleteStateFor(i3);
            case 1:
                return incompleteStateFor(i3, bArr[i]);
            case 2:
                return incompleteStateFor(i3, bArr[i], bArr[i + 1]);
            default:
                throw new AssertionError();
        }
    }

    public static boolean isValidUtf8(byte[] bArr) {
        return isValidUtf8(bArr, 0, bArr.length);
    }

    public static boolean isValidUtf8(byte[] bArr, int i, int i2) {
        return partialIsValidUtf8(bArr, i, i2) == 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int partialIsValidUtf8(int r8, byte[] r9, int r10, int r11) {
        /*
        r3 = -32;
        r6 = -96;
        r1 = -1;
        r5 = -65;
        if (r8 == 0) goto L_0x0085;
    L_0x0009:
        if (r10 < r11) goto L_0x000c;
    L_0x000b:
        return r8;
    L_0x000c:
        r4 = (byte) r8;
        if (r4 >= r3) goto L_0x001b;
    L_0x000f:
        r0 = -62;
        if (r4 < r0) goto L_0x0019;
    L_0x0013:
        r0 = r10 + 1;
        r2 = r9[r10];
        if (r2 <= r5) goto L_0x0084;
    L_0x0019:
        r8 = r1;
        goto L_0x000b;
    L_0x001b:
        r0 = -16;
        if (r4 >= r0) goto L_0x0047;
    L_0x001f:
        r0 = r8 >> 8;
        r0 = r0 ^ -1;
        r0 = (byte) r0;
        if (r0 != 0) goto L_0x0031;
    L_0x0026:
        r0 = r10 + 1;
        r2 = r9[r10];
        if (r0 < r11) goto L_0x0033;
    L_0x002c:
        r8 = incompleteStateFor(r4, r2);
        goto L_0x000b;
    L_0x0031:
        r2 = r0;
        r0 = r10;
    L_0x0033:
        if (r2 > r5) goto L_0x0045;
    L_0x0035:
        if (r4 != r3) goto L_0x0039;
    L_0x0037:
        if (r2 < r6) goto L_0x0045;
    L_0x0039:
        r3 = -19;
        if (r4 != r3) goto L_0x003f;
    L_0x003d:
        if (r2 >= r6) goto L_0x0045;
    L_0x003f:
        r10 = r0 + 1;
        r0 = r9[r0];
        if (r0 <= r5) goto L_0x0085;
    L_0x0045:
        r8 = r1;
        goto L_0x000b;
    L_0x0047:
        r0 = r8 >> 8;
        r0 = r0 ^ -1;
        r0 = (byte) r0;
        if (r0 != 0) goto L_0x0059;
    L_0x004e:
        r2 = r10 + 1;
        r0 = r9[r10];
        if (r2 < r11) goto L_0x008a;
    L_0x0054:
        r8 = incompleteStateFor(r4, r0);
        goto L_0x000b;
    L_0x0059:
        r2 = r8 >> 16;
        r2 = (byte) r2;
        r3 = r0;
        r0 = r2;
        r2 = r10;
    L_0x005f:
        if (r0 != 0) goto L_0x006c;
    L_0x0061:
        r0 = r2 + 1;
        r2 = r9[r2];
        if (r0 < r11) goto L_0x006f;
    L_0x0067:
        r8 = incompleteStateFor(r4, r3, r2);
        goto L_0x000b;
    L_0x006c:
        r7 = r2;
        r2 = r0;
        r0 = r7;
    L_0x006f:
        if (r3 > r5) goto L_0x0082;
    L_0x0071:
        r4 = r4 << 28;
        r3 = r3 + 112;
        r3 = r3 + r4;
        r3 = r3 >> 30;
        if (r3 != 0) goto L_0x0082;
    L_0x007a:
        if (r2 > r5) goto L_0x0082;
    L_0x007c:
        r10 = r0 + 1;
        r0 = r9[r0];
        if (r0 <= r5) goto L_0x0085;
    L_0x0082:
        r8 = r1;
        goto L_0x000b;
    L_0x0084:
        r10 = r0;
    L_0x0085:
        r8 = partialIsValidUtf8(r9, r10, r11);
        goto L_0x000b;
    L_0x008a:
        r3 = 0;
        r7 = r0;
        r0 = r3;
        r3 = r7;
        goto L_0x005f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.Utf8.partialIsValidUtf8(int, byte[], int, int):int");
    }

    public static int partialIsValidUtf8(byte[] bArr, int i, int i2) {
        while (i < i2 && bArr[i] >= (byte) 0) {
            i++;
        }
        return i >= i2 ? 0 : partialIsValidUtf8NonAscii(bArr, i, i2);
    }

    private static int partialIsValidUtf8NonAscii(byte[] bArr, int i, int i2) {
        while (i < i2) {
            int i3 = i + 1;
            byte b = bArr[i];
            if (b < (byte) 0) {
                int i4;
                if (b < (byte) -32) {
                    if (i3 >= i2) {
                        return b;
                    }
                    if (b >= (byte) -62) {
                        i4 = i3 + 1;
                        if (bArr[i3] > (byte) -65) {
                        }
                    }
                    return -1;
                } else if (b < (byte) -16) {
                    if (i3 >= i2 - 1) {
                        return incompleteStateFor(bArr, i3, i2);
                    }
                    r3 = i3 + 1;
                    r2 = bArr[i3];
                    if (r2 <= (byte) -65 && ((b != (byte) -32 || r2 >= (byte) -96) && (b != (byte) -19 || r2 < (byte) -96))) {
                        i4 = r3 + 1;
                        if (bArr[r3] > (byte) -65) {
                        }
                    }
                    return -1;
                } else if (i3 >= i2 - 2) {
                    return incompleteStateFor(bArr, i3, i2);
                } else {
                    r3 = i3 + 1;
                    r2 = bArr[i3];
                    if (r2 <= (byte) -65 && (((b << 28) + (r2 + 112)) >> 30) == 0) {
                        i3 = r3 + 1;
                        if (bArr[r3] <= (byte) -65) {
                            i4 = i3 + 1;
                            if (bArr[i3] > (byte) -65) {
                            }
                        }
                    }
                    return -1;
                }
                i = i4;
            } else {
                i = i3;
            }
        }
        return 0;
    }
}
