package com.facebook.quicklog;

public class QuickPerformanceLogger {
    public void markerStart(int markerId) {
    }

    public void markerStart(int markerId, int instnaceKey) {
    }

    public void markerStart(int markerId, int instnaceKey, long timestamp) {
    }

    public void markerEnd(int markerId, short actionId) {
    }

    public void markerEnd(int markerId, int instanceKey, short actionId) {
    }

    public void markerEnd(int markerId, int instanceKey, short actionId, long timestamp) {
    }

    public void markerNote(int markerId, short actionId) {
    }

    public void markerNote(int markerId, int instanceKey, short actionId) {
    }

    public void markerNote(int markerId, int instanceKey, short actionId, long timestamp) {
    }

    public void markerCancel(int markerId) {
    }

    public void markerCancel(int markerId, int instanceKey) {
    }

    public void markerTag(int markerId, String tag) {
    }

    public void markerTag(int markerId, int instanceKey, String tag) {
    }

    public long currentMonotonicTimestamp() {
        return 0;
    }
}
