package com.facebook.react.animated;

import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import java.util.List;
import javax.annotation.Nullable;

class EventAnimationDriver implements RCTEventEmitter {
    private List<String> mEventPath;
    ValueAnimatedNode mValueNode;

    public EventAnimationDriver(List<String> eventPath, ValueAnimatedNode valueNode) {
        this.mEventPath = eventPath;
        this.mValueNode = valueNode;
    }

    public void receiveEvent(int targetTag, String eventName, @Nullable WritableMap event) {
        if (event == null) {
            throw new IllegalArgumentException("Native animated events must have event data.");
        }
        ReadableMap curMap = event;
        for (int i = 0; i < this.mEventPath.size() - 1; i++) {
            curMap = curMap.getMap((String) this.mEventPath.get(i));
        }
        this.mValueNode.mValue = curMap.getDouble((String) this.mEventPath.get(this.mEventPath.size() - 1));
    }

    public void receiveTouches(String eventName, WritableArray touches, WritableArray changedIndices) {
        throw new RuntimeException("receiveTouches is not support by native animated events");
    }
}
