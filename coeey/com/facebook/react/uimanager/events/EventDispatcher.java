package com.facebook.react.uimanager.events;

import android.util.LongSparseArray;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.MapBuilder;
import com.facebook.systrace.Systrace;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;

public class EventDispatcher implements LifecycleEventListener {
    private static final Comparator<Event> EVENT_COMPARATOR = new 1();
    private final ScheduleDispatchFrameCallback mCurrentFrameCallback;
    private final DispatchEventsRunnable mDispatchEventsRunnable = new DispatchEventsRunnable(this, null);
    private final LongSparseArray<Integer> mEventCookieToLastEventIdx = new LongSparseArray();
    private final Map<String, Short> mEventNameToEventId = MapBuilder.newHashMap();
    private final ArrayList<Event> mEventStaging = new ArrayList();
    private final Object mEventsStagingLock = new Object();
    private Event[] mEventsToDispatch = new Event[16];
    private final Object mEventsToDispatchLock = new Object();
    private int mEventsToDispatchSize = 0;
    private volatile boolean mHasDispatchScheduled = false;
    private volatile int mHasDispatchScheduledCount = 0;
    private final ArrayList<EventDispatcherListener> mListeners = new ArrayList();
    private short mNextEventTypeId = (short) 0;
    @Nullable
    private volatile RCTEventEmitter mRCTEventEmitter;
    private final ReactApplicationContext mReactContext;

    public EventDispatcher(ReactApplicationContext reactContext) {
        this.mReactContext = reactContext;
        this.mReactContext.addLifecycleEventListener(this);
        this.mCurrentFrameCallback = new ScheduleDispatchFrameCallback(this, null);
    }

    public void dispatchEvent(Event event) {
        Assertions.assertCondition(event.isInitialized(), "Dispatched event hasn't been initialized");
        Iterator it = this.mListeners.iterator();
        while (it.hasNext()) {
            ((EventDispatcherListener) it.next()).onEventDispatch(event);
        }
        synchronized (this.mEventsStagingLock) {
            this.mEventStaging.add(event);
            Systrace.startAsyncFlow(0, event.getEventName(), event.getUniqueID());
        }
        if (this.mRCTEventEmitter != null) {
            this.mCurrentFrameCallback.maybePostFromNonUI();
        }
    }

    public void addListener(EventDispatcherListener listener) {
        this.mListeners.add(listener);
    }

    public void removeListener(EventDispatcherListener listener) {
        this.mListeners.remove(listener);
    }

    public void onHostResume() {
        UiThreadUtil.assertOnUiThread();
        if (this.mRCTEventEmitter == null) {
            this.mRCTEventEmitter = (RCTEventEmitter) this.mReactContext.getJSModule(RCTEventEmitter.class);
        }
        this.mCurrentFrameCallback.maybePost();
    }

    public void onHostPause() {
        stopFrameCallback();
    }

    public void onHostDestroy() {
        stopFrameCallback();
    }

    public void onCatalystInstanceDestroyed() {
        stopFrameCallback();
    }

    private void stopFrameCallback() {
        UiThreadUtil.assertOnUiThread();
        this.mCurrentFrameCallback.stop();
    }

    private void moveStagedEventsToDispatchQueue() {
        synchronized (this.mEventsStagingLock) {
            synchronized (this.mEventsToDispatchLock) {
                for (int i = 0; i < this.mEventStaging.size(); i++) {
                    Event event = (Event) this.mEventStaging.get(i);
                    if (event.canCoalesce()) {
                        long eventCookie = getEventCookie(event.getViewTag(), event.getEventName(), event.getCoalescingKey());
                        Event eventToAdd = null;
                        Event eventToDispose = null;
                        Integer lastEventIdx = (Integer) this.mEventCookieToLastEventIdx.get(eventCookie);
                        if (lastEventIdx == null) {
                            eventToAdd = event;
                            this.mEventCookieToLastEventIdx.put(eventCookie, Integer.valueOf(this.mEventsToDispatchSize));
                        } else {
                            Event lastEvent = this.mEventsToDispatch[lastEventIdx.intValue()];
                            Event coalescedEvent = event.coalesce(lastEvent);
                            if (coalescedEvent != lastEvent) {
                                eventToAdd = coalescedEvent;
                                this.mEventCookieToLastEventIdx.put(eventCookie, Integer.valueOf(this.mEventsToDispatchSize));
                                eventToDispose = lastEvent;
                                this.mEventsToDispatch[lastEventIdx.intValue()] = null;
                            } else {
                                eventToDispose = event;
                            }
                        }
                        if (eventToAdd != null) {
                            addEventToEventsToDispatch(eventToAdd);
                        }
                        if (eventToDispose != null) {
                            eventToDispose.dispose();
                        }
                    } else {
                        addEventToEventsToDispatch(event);
                    }
                }
            }
            this.mEventStaging.clear();
        }
    }

    private long getEventCookie(int viewTag, String eventName, short coalescingKey) {
        short eventTypeId;
        Short eventIdObj = (Short) this.mEventNameToEventId.get(eventName);
        if (eventIdObj != null) {
            eventTypeId = eventIdObj.shortValue();
        } else {
            eventTypeId = this.mNextEventTypeId;
            this.mNextEventTypeId = (short) (eventTypeId + 1);
            this.mEventNameToEventId.put(eventName, Short.valueOf(eventTypeId));
        }
        return getEventCookie(viewTag, eventTypeId, coalescingKey);
    }

    private static long getEventCookie(int viewTag, short eventTypeId, short coalescingKey) {
        return (((long) viewTag) | ((((long) eventTypeId) & 65535) << 32)) | ((((long) coalescingKey) & 65535) << 48);
    }

    private void addEventToEventsToDispatch(Event event) {
        if (this.mEventsToDispatchSize == this.mEventsToDispatch.length) {
            this.mEventsToDispatch = (Event[]) Arrays.copyOf(this.mEventsToDispatch, this.mEventsToDispatch.length * 2);
        }
        Event[] eventArr = this.mEventsToDispatch;
        int i = this.mEventsToDispatchSize;
        this.mEventsToDispatchSize = i + 1;
        eventArr[i] = event;
    }

    private void clearEventsToDispatch() {
        Arrays.fill(this.mEventsToDispatch, 0, this.mEventsToDispatchSize, null);
        this.mEventsToDispatchSize = 0;
    }
}
