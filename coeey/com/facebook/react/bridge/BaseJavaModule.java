package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.NativeModule.NativeMethod;
import com.facebook.systrace.Systrace;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nullable;

public abstract class BaseJavaModule implements NativeModule {
    private static final ArgumentExtractor<ReadableNativeArray> ARGUMENT_EXTRACTOR_ARRAY = new 6();
    private static final ArgumentExtractor<Boolean> ARGUMENT_EXTRACTOR_BOOLEAN = new 1();
    private static final ArgumentExtractor<Callback> ARGUMENT_EXTRACTOR_CALLBACK = new C06549();
    private static final ArgumentExtractor<Double> ARGUMENT_EXTRACTOR_DOUBLE = new 2();
    private static final ArgumentExtractor<Dynamic> ARGUMENT_EXTRACTOR_DYNAMIC = new 7();
    private static final ArgumentExtractor<Float> ARGUMENT_EXTRACTOR_FLOAT = new 3();
    private static final ArgumentExtractor<Integer> ARGUMENT_EXTRACTOR_INTEGER = new 4();
    private static final ArgumentExtractor<ReadableMap> ARGUMENT_EXTRACTOR_MAP = new 8();
    private static final ArgumentExtractor<Promise> ARGUMENT_EXTRACTOR_PROMISE = new 10();
    private static final ArgumentExtractor<String> ARGUMENT_EXTRACTOR_STRING = new 5();
    public static final String METHOD_TYPE_ASYNC = "async";
    public static final String METHOD_TYPE_PROMISE = "promise";
    public static final String METHOD_TYPE_SYNC = "sync";
    @Nullable
    private Map<String, NativeMethod> mMethods;

    private static abstract class ArgumentExtractor<T> {
        @Nullable
        public abstract T extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray readableNativeArray, int i);

        private ArgumentExtractor() {
        }

        public int getJSArgumentsNeeded() {
            return 1;
        }
    }

    static class C06549 extends ArgumentExtractor<Callback> {
        C06549() {
            super();
        }

        @Nullable
        public Callback extractArgument(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray jsArguments, int atIndex) {
            if (jsArguments.isNull(atIndex)) {
                return null;
            }
            return new CallbackImpl(catalystInstance, executorToken, (int) jsArguments.getDouble(atIndex));
        }
    }

    private void findMethods() {
        if (this.mMethods == null) {
            Systrace.beginSection(0, "findMethods");
            this.mMethods = new HashMap();
            for (Method targetMethod : getClass().getDeclaredMethods()) {
                ReactMethod annotation = (ReactMethod) targetMethod.getAnnotation(ReactMethod.class);
                if (annotation != null) {
                    String methodName = targetMethod.getName();
                    if (this.mMethods.containsKey(methodName)) {
                        throw new IllegalArgumentException("Java Module " + getName() + " method name already registered: " + methodName);
                    }
                    this.mMethods.put(methodName, new JavaMethod(this, targetMethod, annotation.isBlockingSynchronousMethod()));
                }
            }
            Systrace.endSection(0);
        }
    }

    public final Map<String, NativeMethod> getMethods() {
        findMethods();
        return (Map) Assertions.assertNotNull(this.mMethods);
    }

    @Nullable
    public Map<String, Object> getConstants() {
        return null;
    }

    public void initialize() {
    }

    public boolean canOverrideExistingModule() {
        return false;
    }

    public void onCatalystInstanceDestroy() {
    }

    public boolean supportsWebWorkers() {
        return false;
    }

    private static char paramTypeToChar(Class paramClass) {
        char tryCommon = commonTypeToChar(paramClass);
        if (tryCommon != '\u0000') {
            return tryCommon;
        }
        if (paramClass == ExecutorToken.class) {
            return 'T';
        }
        if (paramClass == Callback.class) {
            return 'X';
        }
        if (paramClass == Promise.class) {
            return 'P';
        }
        if (paramClass == ReadableMap.class) {
            return 'M';
        }
        if (paramClass == ReadableArray.class) {
            return 'A';
        }
        if (paramClass == Dynamic.class) {
            return 'Y';
        }
        throw new RuntimeException("Got unknown param class: " + paramClass.getSimpleName());
    }

    private static char returnTypeToChar(Class returnClass) {
        char tryCommon = commonTypeToChar(returnClass);
        if (tryCommon != '\u0000') {
            return tryCommon;
        }
        if (returnClass == Void.TYPE) {
            return 'v';
        }
        if (returnClass == WritableMap.class) {
            return 'M';
        }
        if (returnClass == WritableArray.class) {
            return 'A';
        }
        throw new RuntimeException("Got unknown return class: " + returnClass.getSimpleName());
    }

    private static char commonTypeToChar(Class typeClass) {
        if (typeClass == Boolean.TYPE) {
            return 'z';
        }
        if (typeClass == Boolean.class) {
            return 'Z';
        }
        if (typeClass == Integer.TYPE) {
            return 'i';
        }
        if (typeClass == Integer.class) {
            return 'I';
        }
        if (typeClass == Double.TYPE) {
            return 'd';
        }
        if (typeClass == Double.class) {
            return 'D';
        }
        if (typeClass == Float.TYPE) {
            return 'f';
        }
        if (typeClass == Float.class) {
            return 'F';
        }
        if (typeClass == String.class) {
            return 'S';
        }
        return '\u0000';
    }
}
