package org.jitsi.meet.sdk;

import java.util.Map;

public abstract class JitsiMeetViewAdapter implements JitsiMeetViewListener {
    public void onConferenceFailed(Map<String, Object> map) {
    }

    public void onConferenceJoined(Map<String, Object> map) {
    }

    public void onConferenceLeft(Map<String, Object> map) {
    }

    public void onConferenceWillJoin(Map<String, Object> map) {
    }

    public void onConferenceWillLeave(Map<String, Object> map) {
    }

    public void onLoadConfigError(Map<String, Object> map) {
    }
}
