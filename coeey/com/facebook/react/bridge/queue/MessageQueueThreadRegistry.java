package com.facebook.react.bridge.queue;

import com.facebook.infer.annotation.Assertions;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class MessageQueueThreadRegistry {
    private static ThreadLocal<MessageQueueThread> sMyMessageQueueThread = new ThreadLocal();

    static void register(MessageQueueThread mqt) {
        mqt.assertIsOnThread();
        sMyMessageQueueThread.set(mqt);
    }

    @DoNotStrip
    public static MessageQueueThread myMessageQueueThread() {
        return (MessageQueueThread) Assertions.assertNotNull(sMyMessageQueueThread.get(), "This thread doesn't have a MessageQueueThread registered to it!");
    }
}
