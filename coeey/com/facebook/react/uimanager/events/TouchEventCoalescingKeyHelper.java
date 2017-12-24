package com.facebook.react.uimanager.events;

import android.util.SparseIntArray;

public class TouchEventCoalescingKeyHelper {
    private final SparseIntArray mDownTimeToCoalescingKey = new SparseIntArray();

    public void addCoalescingKey(long downTime) {
        this.mDownTimeToCoalescingKey.put((int) downTime, 0);
    }

    public void incrementCoalescingKey(long downTime) {
        int currentValue = this.mDownTimeToCoalescingKey.get((int) downTime, -1);
        if (currentValue == -1) {
            throw new RuntimeException("Tried to increment non-existent cookie");
        }
        this.mDownTimeToCoalescingKey.put((int) downTime, currentValue + 1);
    }

    public short getCoalescingKey(long downTime) {
        int currentValue = this.mDownTimeToCoalescingKey.get((int) downTime, -1);
        if (currentValue != -1) {
            return (short) (65535 & currentValue);
        }
        throw new RuntimeException("Tried to get non-existent cookie");
    }

    public void removeCoalescingKey(long downTime) {
        this.mDownTimeToCoalescingKey.delete((int) downTime);
    }

    public boolean hasCoalescingKey(long downTime) {
        if (this.mDownTimeToCoalescingKey.get((int) downTime, -1) == -1) {
            return false;
        }
        return true;
    }
}
