package com.cooey.maya;

import com.evernote.android.job.JobRequest;
import java.io.IOException;
import javax.websocket.MessageHandler.Whole;

public class TestClient {
    static int f52c = 0;
    static long sleep = JobRequest.DEFAULT_BACKOFF_MS;
    static TestWebSocketConnector testWebSocketConnector = new TestWebSocketConnector("5878926d602b7ea81e7b0e18");

    static class C09821 implements Whole<String> {
        C09821() {
        }

        public void onMessage(String message) {
        }
    }

    public static void main(String[] args) {
        while (true) {
            try {
                f52c++;
                if (testWebSocketConnector.session != null) {
                    try {
                        testWebSocketConnector.session.getBasicRemote().sendText("Help");
                        Thread.sleep(sleep);
                        testWebSocketConnector.session.getBasicRemote().sendText("Help");
                        sleep += JobRequest.DEFAULT_BACKOFF_MS;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    testWebSocketConnector.session.close();
                }
                System.out.println("Count c " + f52c);
                System.out.println("Sleep time " + sleep);
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }

    private static void initializeWebSockets() {
        testWebSocketConnector.connect(new C09821());
    }
}
