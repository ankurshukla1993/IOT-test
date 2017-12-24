package com.facebook.messenger;

import java.util.List;

public class MessengerThreadParams {
    public final String metadata;
    public final Origin origin;
    public final List<String> participants;
    public final String threadToken;

    public enum Origin {
        REPLY_FLOW,
        COMPOSE_FLOW,
        UNKNOWN
    }

    public MessengerThreadParams(Origin origin, String threadToken, String metadata, List<String> participants) {
        this.threadToken = threadToken;
        this.metadata = metadata;
        this.participants = participants;
        this.origin = origin;
    }
}
