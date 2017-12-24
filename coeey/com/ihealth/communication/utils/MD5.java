package com.ihealth.communication.utils;

import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;

public class MD5 {
    public static byte[] md5(String string) {
        try {
            return MessageDigest.getInstance(CommonUtils.MD5_INSTANCE).digest(string.getBytes("UTF-8"));
        } catch (Throwable e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        } catch (Throwable e2) {
            throw new RuntimeException("Huh, UTF-8 should be supported?", e2);
        }
    }

    public static String md5String(String string) {
        return ByteBufferUtil.Bytes2HexString(md5(string));
    }
}
