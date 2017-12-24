package com.ihealth.communication.task;

import android.content.Context;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class iHDTaskQueue {
    private BlockingQueue f2058a = new LinkedBlockingQueue();
    private C2171a f2059b = new C2171a(this, this.f2058a);

    public iHDTaskQueue(Context context) {
        this.f2059b.run();
    }

    public void add(AbstractTask task, String action, Object... objs) {
        this.f2058a.add(new iHDtask(task, action, objs));
    }

    public void destory() {
        if (this.f2058a != null) {
            this.f2058a.clear();
            this.f2058a = null;
        }
    }
}
