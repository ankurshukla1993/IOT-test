package com.ihealth.communication.p001a;

import com.ihealth.communication.manager.iHealthDevicesManager;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.util.List;
import java.util.Queue;
import java.util.Timer;
import java.util.concurrent.ConcurrentLinkedQueue;

public class C2023a {
    public C2025c f263a = new C2025c(this);
    private String f264b;
    private String f265c;
    private String f266d;
    private Queue f267e = new ConcurrentLinkedQueue();
    private Timer f268f;

    public C2023a(String str, String str2, String str3) {
        this.f264b = str;
        this.f265c = str2;
        this.f266d = str3;
        this.f268f = new Timer();
    }

    private void m227c() {
        if (this.f267e.size() > 0) {
            Log.p("CommandCacheControl", Level.INFO, "__Execute", new Object[]{"* ing *, [" + this.f267e.size() + "]command left in queue", this.f264b});
            C2024b c2024b = (C2024b) this.f267e.peek();
            C2026d b = c2024b.m236b();
            if (b != null) {
                b.mo5513a();
                this.f263a = m229a(this.f263a);
                if (c2024b.f272d > 0) {
                    this.f268f.schedule(this.f263a, c2024b.f272d);
                    return;
                }
                return;
            }
            return;
        }
        Log.p("CommandCacheControl", Level.INFO, "__Execute", new Object[]{"* ing * , command finish in queue", this.f264b});
    }

    public C2025c m229a(C2025c c2025c) {
        if (c2025c != null) {
            c2025c.cancel();
        }
        return new C2025c(this);
    }

    public void m230a() {
        if (!this.f267e.isEmpty()) {
            this.f267e.clear();
        }
    }

    public void m231a(String str, String str2, String str3) {
        Log.p("CommandCacheControl", Level.INFO, "Execute", new Object[]{str2, str3, str, this.f264b});
        if (this.f263a != null) {
            this.f263a.cancel();
        }
        if (str2.equals(this.f266d)) {
            Log.p("CommandCacheControl", Level.WARN, "ERROR NUM CallBack notifyInfoClass.message=" + str3, new Object[0]);
            m230a();
        } else if (str2.equals(iHealthDevicesManager.IHEALTH_COMM_TIMEOUT)) {
            if (this.f267e.size() > 0) {
                this.f267e.poll();
                m227c();
            }
        } else if (this.f267e.size() > 0 && ((C2024b) this.f267e.peek()).m235a().contains(str2)) {
            this.f267e.poll();
            m227c();
        }
    }

    public synchronized void m232a(List list, long j, C2026d c2026d) {
        Log.p("CommandCacheControl", Level.INFO, "commandExecuteInsSet", new Object[]{"Action = " + ((String) list.get(0)), " Queen Size =" + this.f267e.size(), this.f264b});
        C2024b c2024b = new C2024b(this, list, j, c2026d);
        if (this.f267e.isEmpty()) {
            this.f267e.offer(c2024b);
            Log.p("CommandCacheControl", Level.INFO, "__commandExecuteInsSet", new Object[]{"commandCacheQueue.size() offer Action = " + c2024b.m235a(), " Queen Size =" + this.f267e.size()});
            c2026d.mo5513a();
            this.f263a = m229a(this.f263a);
            if (c2024b.f272d > 0) {
                this.f268f.schedule(this.f263a, c2024b.f272d);
            }
        } else {
            this.f267e.offer(c2024b);
            Log.p("CommandCacheControl", Level.INFO, "__commandExecuteInsSet", new Object[]{"Queen Size =" + this.f267e.size()});
        }
    }

    public Queue m233b() {
        return this.f267e;
    }
}
