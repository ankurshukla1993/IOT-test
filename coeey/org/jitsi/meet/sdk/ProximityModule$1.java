package org.jitsi.meet.sdk;

class ProximityModule$1 implements Runnable {
    final /* synthetic */ ProximityModule this$0;
    final /* synthetic */ boolean val$enabled;

    ProximityModule$1(ProximityModule this$0, boolean z) {
        this.this$0 = this$0;
        this.val$enabled = z;
    }

    public void run() {
        if (this.val$enabled) {
            if (!ProximityModule.access$000(this.this$0).isHeld()) {
                ProximityModule.access$000(this.this$0).acquire();
            }
        } else if (ProximityModule.access$000(this.this$0).isHeld()) {
            ProximityModule.access$000(this.this$0).release();
        }
    }
}
