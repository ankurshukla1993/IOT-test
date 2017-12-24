package com.facebook.react.modules.debug;

import com.facebook.react.bridge.NotThreadSafeBridgeIdleDebugListener;
import com.facebook.react.common.LongArray;
import com.facebook.react.uimanager.debug.NotThreadSafeViewHierarchyUpdateDebugListener;

public class DidJSUpdateUiDuringFrameDetector implements NotThreadSafeBridgeIdleDebugListener, NotThreadSafeViewHierarchyUpdateDebugListener {
    private final LongArray mTransitionToBusyEvents = LongArray.createWithInitialCapacity(20);
    private final LongArray mTransitionToIdleEvents = LongArray.createWithInitialCapacity(20);
    private final LongArray mViewHierarchyUpdateEnqueuedEvents = LongArray.createWithInitialCapacity(20);
    private final LongArray mViewHierarchyUpdateFinishedEvents = LongArray.createWithInitialCapacity(20);
    private volatile boolean mWasIdleAtEndOfLastFrame = true;

    public synchronized void onTransitionToBridgeIdle() {
        this.mTransitionToIdleEvents.add(System.nanoTime());
    }

    public synchronized void onTransitionToBridgeBusy() {
        this.mTransitionToBusyEvents.add(System.nanoTime());
    }

    public synchronized void onViewHierarchyUpdateEnqueued() {
        this.mViewHierarchyUpdateEnqueuedEvents.add(System.nanoTime());
    }

    public synchronized void onViewHierarchyUpdateFinished() {
        this.mViewHierarchyUpdateFinishedEvents.add(System.nanoTime());
    }

    public synchronized boolean getDidJSHitFrameAndCleanup(long frameStartTimeNanos, long frameEndTimeNanos) {
        boolean hitFrame;
        boolean finishedUiUpdate = hasEventBetweenTimestamps(this.mViewHierarchyUpdateFinishedEvents, frameStartTimeNanos, frameEndTimeNanos);
        boolean didEndFrameIdle = didEndFrameIdle(frameStartTimeNanos, frameEndTimeNanos);
        if (finishedUiUpdate) {
            hitFrame = true;
        } else {
            if (didEndFrameIdle) {
                if (!hasEventBetweenTimestamps(this.mViewHierarchyUpdateEnqueuedEvents, frameStartTimeNanos, frameEndTimeNanos)) {
                    hitFrame = true;
                }
            }
            hitFrame = false;
        }
        cleanUp(this.mTransitionToIdleEvents, frameEndTimeNanos);
        cleanUp(this.mTransitionToBusyEvents, frameEndTimeNanos);
        cleanUp(this.mViewHierarchyUpdateEnqueuedEvents, frameEndTimeNanos);
        cleanUp(this.mViewHierarchyUpdateFinishedEvents, frameEndTimeNanos);
        this.mWasIdleAtEndOfLastFrame = didEndFrameIdle;
        return hitFrame;
    }

    private static boolean hasEventBetweenTimestamps(LongArray eventArray, long startTime, long endTime) {
        for (int i = 0; i < eventArray.size(); i++) {
            long time = eventArray.get(i);
            if (time >= startTime && time < endTime) {
                return true;
            }
        }
        return false;
    }

    private static long getLastEventBetweenTimestamps(LongArray eventArray, long startTime, long endTime) {
        long lastEvent = -1;
        for (int i = 0; i < eventArray.size(); i++) {
            long time = eventArray.get(i);
            if (time >= startTime && time < endTime) {
                lastEvent = time;
            } else if (time >= endTime) {
                break;
            }
        }
        return lastEvent;
    }

    private boolean didEndFrameIdle(long startTime, long endTime) {
        long lastIdleTransition = getLastEventBetweenTimestamps(this.mTransitionToIdleEvents, startTime, endTime);
        long lastBusyTransition = getLastEventBetweenTimestamps(this.mTransitionToBusyEvents, startTime, endTime);
        if (lastIdleTransition == -1 && lastBusyTransition == -1) {
            return this.mWasIdleAtEndOfLastFrame;
        }
        return lastIdleTransition > lastBusyTransition;
    }

    private static void cleanUp(LongArray eventArray, long endTime) {
        int i;
        int size = eventArray.size();
        int indicesToRemove = 0;
        for (i = 0; i < size; i++) {
            if (eventArray.get(i) < endTime) {
                indicesToRemove++;
            }
        }
        if (indicesToRemove > 0) {
            for (i = 0; i < size - indicesToRemove; i++) {
                eventArray.set(i, eventArray.get(i + indicesToRemove));
            }
            eventArray.dropTail(indicesToRemove);
        }
    }
}
