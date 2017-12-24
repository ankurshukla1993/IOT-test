package com.facebook.react.uimanager.events;

import com.facebook.infer.annotation.Assertions;
import com.facebook.systrace.Systrace;
import java.util.Arrays;

class EventDispatcher$DispatchEventsRunnable implements Runnable {
    final /* synthetic */ EventDispatcher this$0;

    private EventDispatcher$DispatchEventsRunnable(EventDispatcher eventDispatcher) {
        this.this$0 = eventDispatcher;
    }

    public void run() {
        Systrace.beginSection(0, "DispatchEventsRunnable");
        try {
            Systrace.endAsyncFlow(0, "ScheduleDispatchFrameCallback", EventDispatcher.access$500(this.this$0));
            EventDispatcher.access$402(this.this$0, false);
            EventDispatcher.access$508(this.this$0);
            Assertions.assertNotNull(EventDispatcher.access$900(this.this$0));
            synchronized (EventDispatcher.access$1000(this.this$0)) {
                if (EventDispatcher.access$300(this.this$0) > 1) {
                    Arrays.sort(EventDispatcher.access$1100(this.this$0), 0, EventDispatcher.access$300(this.this$0), EventDispatcher.access$1200());
                }
                for (int eventIdx = 0; eventIdx < EventDispatcher.access$300(this.this$0); eventIdx++) {
                    Event event = EventDispatcher.access$1100(this.this$0)[eventIdx];
                    if (event != null) {
                        Systrace.endAsyncFlow(0, event.getEventName(), event.getUniqueID());
                        event.dispatch(EventDispatcher.access$900(this.this$0));
                        event.dispose();
                    }
                }
                EventDispatcher.access$1300(this.this$0);
                EventDispatcher.access$1400(this.this$0).clear();
            }
        } finally {
            Systrace.endSection(0);
        }
    }
}
