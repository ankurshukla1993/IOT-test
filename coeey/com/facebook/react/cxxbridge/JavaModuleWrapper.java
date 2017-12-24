package com.facebook.react.cxxbridge;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.BaseJavaModule$JavaMethod;
import com.facebook.react.bridge.CatalystInstance;
import com.facebook.react.bridge.ExecutorToken;
import com.facebook.react.bridge.NativeArray;
import com.facebook.react.bridge.NativeModule.NativeMethod;
import com.facebook.react.bridge.NativeModuleLogger;
import com.facebook.react.bridge.ReadableNativeArray;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@DoNotStrip
class JavaModuleWrapper {
    private final CatalystInstance mCatalystInstance;
    private final ArrayList<NativeMethod> mMethods = new ArrayList();
    private final ModuleHolder mModuleHolder;

    @DoNotStrip
    public class MethodDescriptor {
        @DoNotStrip
        Method method;
        @DoNotStrip
        String name;
        @DoNotStrip
        String signature;
        @DoNotStrip
        String type;
    }

    public JavaModuleWrapper(CatalystInstance catalystinstance, ModuleHolder moduleHolder) {
        this.mCatalystInstance = catalystinstance;
        this.mModuleHolder = moduleHolder;
    }

    @DoNotStrip
    public BaseJavaModule getModule() {
        return (BaseJavaModule) this.mModuleHolder.getModule();
    }

    @DoNotStrip
    public String getName() {
        return this.mModuleHolder.getInfo().name();
    }

    @DoNotStrip
    public List<MethodDescriptor> getMethodDescriptors() {
        ArrayList<MethodDescriptor> descs = new ArrayList();
        for (Entry<String, NativeMethod> entry : getModule().getMethods().entrySet()) {
            MethodDescriptor md = new MethodDescriptor();
            md.name = (String) entry.getKey();
            md.type = ((NativeMethod) entry.getValue()).getType();
            BaseJavaModule$JavaMethod method = (BaseJavaModule$JavaMethod) entry.getValue();
            if (md.type == BaseJavaModule.METHOD_TYPE_SYNC) {
                md.signature = method.getSignature();
                md.method = method.getMethod();
            }
            this.mMethods.add(method);
            descs.add(md);
        }
        return descs;
    }

    @DoNotStrip
    public NativeArray getConstants() {
        SystraceMessage.beginSection(0, "Map constants").arg("moduleName", getName()).flush();
        BaseJavaModule baseJavaModule = getModule();
        Map map = baseJavaModule.getConstants();
        Systrace.endSection(0);
        SystraceMessage.beginSection(0, "WritableNativeMap constants").arg("moduleName", getName()).flush();
        if (baseJavaModule instanceof NativeModuleLogger) {
            ((NativeModuleLogger) baseJavaModule).startConstantsMapConversion();
        }
        try {
            WritableNativeMap writableNativeMap = Arguments.makeNativeMap(map);
            WritableNativeArray array = new WritableNativeArray();
            array.pushMap(writableNativeMap);
            if (baseJavaModule instanceof NativeModuleLogger) {
                ((NativeModuleLogger) baseJavaModule).endConstantsMapConversion();
            }
            return array;
        } finally {
            Systrace.endSection(0);
        }
    }

    @DoNotStrip
    public boolean supportsWebWorkers() {
        return getModule().supportsWebWorkers();
    }

    @DoNotStrip
    public void invoke(ExecutorToken token, int methodId, ReadableNativeArray parameters) {
        if (this.mMethods != null && methodId < this.mMethods.size()) {
            ((NativeMethod) this.mMethods.get(methodId)).invoke(this.mCatalystInstance, token, parameters);
        }
    }
}
