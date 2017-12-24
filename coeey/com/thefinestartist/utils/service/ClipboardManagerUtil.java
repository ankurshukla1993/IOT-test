package com.thefinestartist.utils.service;

import android.content.ClipData;
import android.content.ClipDescription;
import android.text.ClipboardManager;
import com.thefinestartist.utils.etc.APILevel;

public class ClipboardManagerUtil {
    private ClipboardManagerUtil() {
    }

    public static void setText(CharSequence text) {
        ClipboardManager clipboardManager = ServiceUtil.getClipboardManager();
        if (APILevel.require(11)) {
            ((android.content.ClipboardManager) clipboardManager).setPrimaryClip(ClipData.newPlainText("ClipboardManagerUtil", text));
        } else {
            clipboardManager.setText(text);
        }
    }

    public static boolean hasText() {
        ClipboardManager clipboardManager = ServiceUtil.getClipboardManager();
        if (!APILevel.require(11)) {
            return clipboardManager.hasText();
        }
        android.content.ClipboardManager cm = (android.content.ClipboardManager) clipboardManager;
        ClipDescription description = cm.getPrimaryClipDescription();
        return (cm.getPrimaryClip() == null || description == null || !description.hasMimeType("text/plain")) ? false : true;
    }

    public static CharSequence getText() {
        ClipboardManager clipboardManager = ServiceUtil.getClipboardManager();
        if (!APILevel.require(11)) {
            return clipboardManager.getText();
        }
        android.content.ClipboardManager cm = (android.content.ClipboardManager) clipboardManager;
        ClipDescription description = cm.getPrimaryClipDescription();
        ClipData clipData = cm.getPrimaryClip();
        if (clipData == null || description == null || !description.hasMimeType("text/plain")) {
            return null;
        }
        return clipData.getItemAt(0).getText();
    }
}
