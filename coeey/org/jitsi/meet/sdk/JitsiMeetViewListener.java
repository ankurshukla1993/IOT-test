package org.jitsi.meet.sdk;

import java.util.Map;

public interface JitsiMeetViewListener {
    void onConferenceFailed(Map<String, Object> map);

    void onConferenceJoined(Map<String, Object> map);

    void onConferenceLeft(Map<String, Object> map);

    void onConferenceWillJoin(Map<String, Object> map);

    void onConferenceWillLeave(Map<String, Object> map);

    void onLoadConfigError(Map<String, Object> map);
}
