package com.facebook.react.bridge.queue;

import com.facebook.infer.annotation.Assertions;
import javax.annotation.Nullable;

public class ReactQueueConfigurationSpec$Builder {
    @Nullable
    private MessageQueueThreadSpec mJSQueueSpec;
    @Nullable
    private MessageQueueThreadSpec mNativeModulesQueueSpec;

    public ReactQueueConfigurationSpec$Builder setNativeModulesQueueThreadSpec(MessageQueueThreadSpec spec) {
        Assertions.assertCondition(this.mNativeModulesQueueSpec == null, "Setting native modules queue spec multiple times!");
        this.mNativeModulesQueueSpec = spec;
        return this;
    }

    public ReactQueueConfigurationSpec$Builder setJSQueueThreadSpec(MessageQueueThreadSpec spec) {
        Assertions.assertCondition(this.mJSQueueSpec == null, "Setting JS queue multiple times!");
        this.mJSQueueSpec = spec;
        return this;
    }

    public ReactQueueConfigurationSpec build() {
        return new ReactQueueConfigurationSpec((MessageQueueThreadSpec) Assertions.assertNotNull(this.mNativeModulesQueueSpec), (MessageQueueThreadSpec) Assertions.assertNotNull(this.mJSQueueSpec), null);
    }
}
