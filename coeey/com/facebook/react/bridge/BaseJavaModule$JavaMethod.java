package com.facebook.react.bridge;

import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.BaseJavaModule.ArgumentExtractor;
import com.facebook.react.bridge.NativeModule.NativeMethod;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseJavaModule$JavaMethod implements NativeMethod {
    private final ArgumentExtractor[] mArgumentExtractors;
    private final Object[] mArguments;
    private final int mJSArgumentsNeeded;
    private Method mMethod;
    private final String mSignature;
    private final String mTraceName;
    private String mType = BaseJavaModule.METHOD_TYPE_ASYNC;
    final /* synthetic */ BaseJavaModule this$0;

    public BaseJavaModule$JavaMethod(BaseJavaModule this$0, Method method, boolean isSync) {
        this.this$0 = this$0;
        this.mMethod = method;
        this.mMethod.setAccessible(true);
        if (isSync) {
            this.mType = BaseJavaModule.METHOD_TYPE_SYNC;
        }
        Class[] parameterTypes = method.getParameterTypes();
        this.mArgumentExtractors = buildArgumentExtractors(parameterTypes);
        this.mSignature = buildSignature(this.mMethod, parameterTypes, isSync);
        this.mArguments = new Object[parameterTypes.length];
        this.mJSArgumentsNeeded = calculateJSArgumentsNeeded();
        this.mTraceName = this$0.getName() + "." + this.mMethod.getName();
    }

    public Method getMethod() {
        return this.mMethod;
    }

    public String getSignature() {
        return this.mSignature;
    }

    private String buildSignature(Method method, Class[] paramTypes, boolean isSync) {
        StringBuilder builder = new StringBuilder(paramTypes.length + 2);
        if (isSync) {
            builder.append(BaseJavaModule.access$200(method.getReturnType()));
            builder.append('.');
        } else {
            builder.append("v.");
        }
        int i = 0;
        while (i < paramTypes.length) {
            Class paramClass = paramTypes[i];
            if (paramClass == ExecutorToken.class) {
                if (!this.this$0.supportsWebWorkers()) {
                    throw new RuntimeException("Module " + this.this$0 + " doesn't support web workers, but " + this.mMethod.getName() + " takes an ExecutorToken.");
                }
            } else if (paramClass == Promise.class) {
                Assertions.assertCondition(i == paramTypes.length + -1, "Promise must be used as last parameter only");
                if (!isSync) {
                    this.mType = BaseJavaModule.METHOD_TYPE_PROMISE;
                }
            }
            builder.append(BaseJavaModule.access$300(paramClass));
            i++;
        }
        if (!this.this$0.supportsWebWorkers() || builder.charAt(2) == 'T') {
            return builder.toString();
        }
        throw new RuntimeException("Module " + this.this$0 + " supports web workers, but " + this.mMethod.getName() + "does not take an ExecutorToken as its first parameter.");
    }

    private ArgumentExtractor[] buildArgumentExtractors(Class[] paramTypes) {
        int executorTokenOffset = 0;
        if (this.this$0.supportsWebWorkers()) {
            if (paramTypes[0] != ExecutorToken.class) {
                throw new RuntimeException("Module " + this.this$0 + " supports web workers, but " + this.mMethod.getName() + "does not take an ExecutorToken as its first parameter.");
            }
            executorTokenOffset = 1;
        }
        ArgumentExtractor[] argumentExtractors = new ArgumentExtractor[(paramTypes.length - executorTokenOffset)];
        for (int i = 0; i < paramTypes.length - executorTokenOffset; i += argumentExtractors[i].getJSArgumentsNeeded()) {
            int paramIndex = i + executorTokenOffset;
            Class argumentClass = paramTypes[paramIndex];
            if (argumentClass == Boolean.class || argumentClass == Boolean.TYPE) {
                argumentExtractors[i] = BaseJavaModule.access$400();
            } else if (argumentClass == Integer.class || argumentClass == Integer.TYPE) {
                argumentExtractors[i] = BaseJavaModule.access$500();
            } else if (argumentClass == Double.class || argumentClass == Double.TYPE) {
                argumentExtractors[i] = BaseJavaModule.access$600();
            } else if (argumentClass == Float.class || argumentClass == Float.TYPE) {
                argumentExtractors[i] = BaseJavaModule.access$700();
            } else if (argumentClass == String.class) {
                argumentExtractors[i] = BaseJavaModule.access$800();
            } else if (argumentClass == Callback.class) {
                argumentExtractors[i] = BaseJavaModule.access$100();
            } else if (argumentClass == Promise.class) {
                boolean z;
                argumentExtractors[i] = BaseJavaModule.access$900();
                if (paramIndex == paramTypes.length - 1) {
                    z = true;
                } else {
                    z = false;
                }
                Assertions.assertCondition(z, "Promise must be used as last parameter only");
                this.mType = BaseJavaModule.METHOD_TYPE_PROMISE;
            } else if (argumentClass == ReadableMap.class) {
                argumentExtractors[i] = BaseJavaModule.access$1000();
            } else if (argumentClass == ReadableArray.class) {
                argumentExtractors[i] = BaseJavaModule.access$1100();
            } else if (argumentClass == Dynamic.class) {
                argumentExtractors[i] = BaseJavaModule.access$1200();
            } else {
                throw new RuntimeException("Got unknown argument class: " + argumentClass.getSimpleName());
            }
        }
        return argumentExtractors;
    }

    private int calculateJSArgumentsNeeded() {
        int n = 0;
        for (ArgumentExtractor extractor : this.mArgumentExtractors) {
            n += extractor.getJSArgumentsNeeded();
        }
        return n;
    }

    private String getAffectedRange(int startIndex, int jsArgumentsNeeded) {
        return jsArgumentsNeeded > 1 ? "" + startIndex + "-" + ((startIndex + jsArgumentsNeeded) - 1) : "" + startIndex;
    }

    public void invoke(CatalystInstance catalystInstance, ExecutorToken executorToken, ReadableNativeArray parameters) {
        int jsArgumentsConsumed;
        SystraceMessage.beginSection(0, "callJavaModuleMethod").arg("method", this.mTraceName).flush();
        int i;
        try {
            if (this.mJSArgumentsNeeded != parameters.size()) {
                throw new NativeArgumentsParseException(this.this$0.getName() + "." + this.mMethod.getName() + " got " + parameters.size() + " arguments, expected " + this.mJSArgumentsNeeded);
            }
            i = 0;
            jsArgumentsConsumed = 0;
            int executorTokenOffset = 0;
            if (this.this$0.supportsWebWorkers()) {
                this.mArguments[0] = executorToken;
                executorTokenOffset = 1;
            }
            while (i < this.mArgumentExtractors.length) {
                this.mArguments[i + executorTokenOffset] = this.mArgumentExtractors[i].extractArgument(catalystInstance, executorToken, parameters, jsArgumentsConsumed);
                jsArgumentsConsumed += this.mArgumentExtractors[i].getJSArgumentsNeeded();
                i++;
            }
            this.mMethod.invoke(this.this$0, this.mArguments);
            Systrace.endSection(0);
        } catch (IllegalArgumentException ie) {
            throw new RuntimeException("Could not invoke " + this.this$0.getName() + "." + this.mMethod.getName(), ie);
        } catch (IllegalAccessException iae) {
            throw new RuntimeException("Could not invoke " + this.this$0.getName() + "." + this.mMethod.getName(), iae);
        } catch (InvocationTargetException ite) {
            if (ite.getCause() instanceof RuntimeException) {
                throw ((RuntimeException) ite.getCause());
            }
            throw new RuntimeException("Could not invoke " + this.this$0.getName() + "." + this.mMethod.getName(), ite);
        } catch (UnexpectedNativeTypeException e) {
            throw new NativeArgumentsParseException(e.getMessage() + " (constructing arguments for " + this.this$0.getName() + "." + this.mMethod.getName() + " at argument index " + getAffectedRange(jsArgumentsConsumed, this.mArgumentExtractors[i].getJSArgumentsNeeded()) + ")", e);
        } catch (Throwable th) {
            Systrace.endSection(0);
        }
    }

    public String getType() {
        return this.mType;
    }
}
