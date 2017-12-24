package com.google.android.gms.internal;

final class zzfgc {
    static String zzaq(zzfdh com_google_android_gms_internal_zzfdh) {
        zzfge com_google_android_gms_internal_zzfgd = new zzfgd(com_google_android_gms_internal_zzfdh);
        StringBuilder stringBuilder = new StringBuilder(com_google_android_gms_internal_zzfgd.size());
        for (int i = 0; i < com_google_android_gms_internal_zzfgd.size(); i++) {
            byte zzkd = com_google_android_gms_internal_zzfgd.zzkd(i);
            switch (zzkd) {
                case (byte) 7:
                    stringBuilder.append("\\a");
                    break;
                case (byte) 8:
                    stringBuilder.append("\\b");
                    break;
                case (byte) 9:
                    stringBuilder.append("\\t");
                    break;
                case (byte) 10:
                    stringBuilder.append("\\n");
                    break;
                case (byte) 11:
                    stringBuilder.append("\\v");
                    break;
                case (byte) 12:
                    stringBuilder.append("\\f");
                    break;
                case (byte) 13:
                    stringBuilder.append("\\r");
                    break;
                case (byte) 34:
                    stringBuilder.append("\\\"");
                    break;
                case (byte) 39:
                    stringBuilder.append("\\'");
                    break;
                case (byte) 92:
                    stringBuilder.append("\\\\");
                    break;
                default:
                    if (zzkd >= (byte) 32 && zzkd <= (byte) 126) {
                        stringBuilder.append((char) zzkd);
                        break;
                    }
                    stringBuilder.append('\\');
                    stringBuilder.append((char) (((zzkd >>> 6) & 3) + 48));
                    stringBuilder.append((char) (((zzkd >>> 3) & 7) + 48));
                    stringBuilder.append((char) ((zzkd & 7) + 48));
                    break;
            }
        }
        return stringBuilder.toString();
    }
}
