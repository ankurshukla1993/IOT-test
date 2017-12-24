package com.appeaser.sublimepickerlibrary.utilities;

import android.support.v4.view.accessibility.AccessibilityEventCompat;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

public class AccessibilityUtils {
    public static void makeAnnouncement(View view, CharSequence announcement) {
        if (view != null) {
            if (SUtils.isApi_16_OrHigher()) {
                view.announceForAccessibility(announcement);
                return;
            }
            AccessibilityManager am = (AccessibilityManager) view.getContext().getSystemService("accessibility");
            if (am.isEnabled()) {
                AccessibilityEvent event = AccessibilityEvent.obtain(64);
                AccessibilityEventCompat.asRecord(event).setSource(view);
                event.setClassName(view.getClass().getName());
                event.setPackageName(view.getContext().getPackageName());
                event.setEnabled(view.isEnabled());
                event.getText().add(announcement);
                am.sendAccessibilityEvent(event);
            }
        }
    }
}
