package com.ihealth.androidbg.audio;

public class TransToneData {
    public static String bytes2HexString(byte[] b, int len) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < len; i++) {
            String toHexString = Integer.toHexString(b[i] & 255);
            if (toHexString.length() == 1) {
                toHexString = '0' + toHexString;
            }
            stringBuilder.append(toHexString.toUpperCase());
        }
        return stringBuilder.toString();
    }

    public static int[] getDataByOrder(byte[] order) {
        int i = 0;
        int i2 = -1;
        int[] iArr = new int[((order.length * 2) + 2)];
        iArr[0] = 17;
        while (i < order.length) {
            iArr[(i * 2) + 1] = (order[i] & 240) >>> 4;
            if (i2 == 16) {
                i2 = iArr[(i * 2) + 1];
            } else if (i2 == iArr[(i * 2) + 1]) {
                iArr[(i * 2) + 1] = 16;
                i2 = iArr[(i * 2) + 1];
            } else {
                i2 = iArr[(i * 2) + 1];
            }
            iArr[(i * 2) + 2] = order[i] & 15;
            if (i2 == 16) {
                i2 = iArr[(i * 2) + 2];
            } else if (i2 == iArr[(i * 2) + 2]) {
                iArr[(i * 2) + 2] = 16;
                i2 = iArr[(i * 2) + 2];
            } else {
                i2 = iArr[(i * 2) + 2];
            }
            i++;
        }
        iArr[(order.length * 2) + 1] = 18;
        return iArr;
    }

    public static int[] transDataToTone(int[] data) {
        int[] iArr = new int[data.length];
        for (int i = 0; i < data.length; i++) {
            if (data[i] == 0) {
                iArr[i] = 16600;
            }
            if (data[i] == 1) {
                iArr[i] = 13100;
            }
            if (data[i] == 2) {
                iArr[i] = 10900;
            }
            if (data[i] == 3) {
                iArr[i] = 9250;
            }
            if (data[i] == 4) {
                iArr[i] = 8050;
            }
            if (data[i] == 5) {
                iArr[i] = 7150;
            }
            if (data[i] == 6) {
                iArr[i] = 6400;
            }
            if (data[i] == 7) {
                iArr[i] = 5810;
            }
            if (data[i] == 8) {
                iArr[i] = 5310;
            }
            if (data[i] == 9) {
                iArr[i] = 4900;
            }
            if (data[i] == 10) {
                iArr[i] = 4550;
            }
            if (data[i] == 11) {
                iArr[i] = 4240;
            }
            if (data[i] == 12) {
                iArr[i] = 3970;
            }
            if (data[i] == 13) {
                iArr[i] = 3730;
            }
            if (data[i] == 14) {
                iArr[i] = 3520;
            }
            if (data[i] == 15) {
                iArr[i] = 3330;
            }
            if (data[i] == 16) {
                iArr[i] = 3165;
            }
            if (data[i] == 17) {
                iArr[i] = 3015;
            }
            if (data[i] == 18) {
                iArr[i] = 2875;
            }
            if (data[i] == 19) {
                iArr[i] = 1000;
            }
        }
        return iArr;
    }

    public static int transToneToData(int tone) {
        int i = 0;
        if (tone == 8929) {
        }
        if (tone == 7813) {
            i = 1;
        }
        if (tone == 6944) {
            i = 2;
        }
        if (tone == 6250) {
            i = 3;
        }
        if (tone == 5682) {
            i = 4;
        }
        if (tone == 5208) {
            i = 5;
        }
        if (tone == 4808) {
            i = 6;
        }
        if (tone == 4464) {
            i = 7;
        }
        if (tone == 4167) {
            i = 8;
        }
        if (tone == 3676) {
            i = 9;
        }
        if (tone == 3289) {
            i = 10;
        }
        if (tone == 2841) {
            i = 11;
        }
        if (tone == 2500) {
            i = 12;
        }
        if (tone == 2155) {
            i = 13;
        }
        if (tone == 1838) {
            i = 14;
        }
        if (tone == 1524) {
            i = 15;
        }
        if (tone == 1202) {
            i = 16;
        }
        if (tone == 906) {
            i = 17;
        }
        return tone == 601 ? 18 : i;
    }

    public static int[] transToneToData(int[] tone) {
        int[] iArr = new int[tone.length];
        for (int i = 0; i < tone.length; i++) {
            if (tone[i] == 8929) {
                iArr[i] = 0;
            }
            if (tone[i] == 7813) {
                iArr[i] = 1;
            }
            if (tone[i] == 6944) {
                iArr[i] = 2;
            }
            if (tone[i] == 6250) {
                iArr[i] = 3;
            }
            if (tone[i] == 5682) {
                iArr[i] = 4;
            }
            if (tone[i] == 5208) {
                iArr[i] = 5;
            }
            if (tone[i] == 4808) {
                iArr[i] = 6;
            }
            if (tone[i] == 4464) {
                iArr[i] = 7;
            }
            if (tone[i] == 4167) {
                iArr[i] = 8;
            }
            if (tone[i] == 3676) {
                iArr[i] = 9;
            }
            if (tone[i] == 3289) {
                iArr[i] = 10;
            }
            if (tone[i] == 2841) {
                iArr[i] = 11;
            }
            if (tone[i] == 2500) {
                iArr[i] = 12;
            }
            if (tone[i] == 2155) {
                iArr[i] = 13;
            }
            if (tone[i] == 1838) {
                iArr[i] = 14;
            }
            if (tone[i] == 1524) {
                iArr[i] = 15;
            }
            if (tone[i] == 1202) {
                iArr[i] = 16;
            }
            if (tone[i] == 906) {
                iArr[i] = 17;
            }
            if (tone[i] == 601) {
                iArr[i] = 18;
            }
        }
        return iArr;
    }
}
