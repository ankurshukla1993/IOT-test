package com.facebook.react.bridge.queue;

import android.os.Build.VERSION;

public class ReactQueueConfigurationSpec {
    private static final long LEGACY_STACK_SIZE_BYTES = 2000000;
    private final MessageQueueThreadSpec mJSQueueThreadSpec;
    private final MessageQueueThreadSpec mNativeModulesQueueThreadSpec;

    private ReactQueueConfigurationSpec(MessageQueueThreadSpec nativeModulesQueueThreadSpec, MessageQueueThreadSpec jsQueueThreadSpec) {
        this.mNativeModulesQueueThreadSpec = nativeModulesQueueThreadSpec;
        this.mJSQueueThreadSpec = jsQueueThreadSpec;
    }

    public MessageQueueThreadSpec getNativeModulesQueueThreadSpec() {
        return this.mNativeModulesQueueThreadSpec;
    }

    public MessageQueueThreadSpec getJSQueueThreadSpec() {
        return this.mJSQueueThreadSpec;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static ReactQueueConfigurationSpec createDefault() {
        MessageQueueThreadSpec spec;
        if (VERSION.SDK_INT < 21) {
            spec = MessageQueueThreadSpec.newBackgroundThreadSpec("native_modules", LEGACY_STACK_SIZE_BYTES);
        } else {
            spec = MessageQueueThreadSpec.newBackgroundThreadSpec("native_modules");
        }
        return builder().setJSQueueThreadSpec(MessageQueueThreadSpec.newBackgroundThreadSpec("js")).setNativeModulesQueueThreadSpec(spec).build();
    }
}
