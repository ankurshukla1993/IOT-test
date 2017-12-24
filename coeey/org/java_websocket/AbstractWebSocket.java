package org.java_websocket;

import com.google.android.gms.common.ConnectionResult;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

public abstract class AbstractWebSocket extends WebSocketAdapter {
    private int connectionLostTimeout = 60;
    private Timer connectionLostTimer;
    private TimerTask connectionLostTimerTask;
    private boolean tcpNoDelay;

    class C25431 extends TimerTask {
        C25431() {
        }

        public void run() {
            Collection<WebSocket> con = AbstractWebSocket.this.connections();
            synchronized (con) {
                long current = System.currentTimeMillis() - ((long) (AbstractWebSocket.this.connectionLostTimeout * ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED));
                for (WebSocket conn : con) {
                    if (conn instanceof WebSocketImpl) {
                        if (((WebSocketImpl) conn).getLastPong() < current) {
                            if (WebSocketImpl.DEBUG) {
                                System.out.println("Closing connection due to no pong received: " + conn.toString());
                            }
                            conn.close(1006);
                        } else {
                            conn.sendPing();
                        }
                    }
                }
            }
        }
    }

    protected abstract Collection<WebSocket> connections();

    public int getConnectionLostTimeout() {
        return this.connectionLostTimeout;
    }

    public void setConnectionLostTimeout(int connectionLostTimeout) {
        this.connectionLostTimeout = connectionLostTimeout;
        if (this.connectionLostTimeout <= 0) {
            stopConnectionLostTimer();
        } else {
            startConnectionLostTimer();
        }
    }

    protected void stopConnectionLostTimer() {
        if (this.connectionLostTimer != null || this.connectionLostTimerTask != null) {
            if (WebSocketImpl.DEBUG) {
                System.out.println("Connection lost timer stoped");
            }
            cancelConnectionLostTimer();
        }
    }

    protected void startConnectionLostTimer() {
        if (this.connectionLostTimeout > 0) {
            if (WebSocketImpl.DEBUG) {
                System.out.println("Connection lost timer started");
            }
            cancelConnectionLostTimer();
            this.connectionLostTimer = new Timer();
            this.connectionLostTimerTask = new C25431();
            this.connectionLostTimer.scheduleAtFixedRate(this.connectionLostTimerTask, (long) (this.connectionLostTimeout * 1000), (long) (this.connectionLostTimeout * 1000));
        } else if (WebSocketImpl.DEBUG) {
            System.out.println("Connection lost timer deactivated");
        }
    }

    private void cancelConnectionLostTimer() {
        if (this.connectionLostTimer != null) {
            this.connectionLostTimer.cancel();
            this.connectionLostTimer = null;
        }
        if (this.connectionLostTimerTask != null) {
            this.connectionLostTimerTask.cancel();
            this.connectionLostTimerTask = null;
        }
    }

    public boolean isTcpNoDelay() {
        return this.tcpNoDelay;
    }

    public void setTcpNoDelay(boolean tcpNoDelay) {
        this.tcpNoDelay = tcpNoDelay;
    }
}
