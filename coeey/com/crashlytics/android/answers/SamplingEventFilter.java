package com.crashlytics.android.answers;

import java.util.HashSet;
import java.util.Set;

class SamplingEventFilter implements EventFilter {
    static final Set<Type> EVENTS_TYPE_TO_SAMPLE = new C10011();
    final int samplingRate;

    static class C10011 extends HashSet<Type> {
        C10011() {
            add(Type.START);
            add(Type.RESUME);
            add(Type.PAUSE);
            add(Type.STOP);
        }
    }

    public SamplingEventFilter(int samplingRate) {
        this.samplingRate = samplingRate;
    }

    public boolean skipEvent(SessionEvent sessionEvent) {
        boolean canBeSampled;
        if (EVENTS_TYPE_TO_SAMPLE.contains(sessionEvent.type) && sessionEvent.sessionEventMetadata.betaDeviceToken == null) {
            canBeSampled = true;
        } else {
            canBeSampled = false;
        }
        boolean isSampledId;
        if (Math.abs(sessionEvent.sessionEventMetadata.installationId.hashCode() % this.samplingRate) != 0) {
            isSampledId = true;
        } else {
            isSampledId = false;
        }
        if (canBeSampled && isSampledId) {
            return true;
        }
        return false;
    }
}
