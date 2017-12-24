package retrofit2;

import android.os.Build.VERSION;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

class Platform {
    private static final Platform PLATFORM = findPlatform();

    Platform() {
    }

    static Platform get() {
        return PLATFORM;
    }

    private static Platform findPlatform() {
        try {
            Class.forName("android.os.Build");
            if (VERSION.SDK_INT != 0) {
                return new Android();
            }
        } catch (ClassNotFoundException e) {
        }
        try {
            Class.forName("java.util.Optional");
            return new Java8();
        } catch (ClassNotFoundException e2) {
            return new Platform();
        }
    }

    @Nullable
    Executor defaultCallbackExecutor() {
        return null;
    }

    CallAdapter$Factory defaultCallAdapterFactory(@Nullable Executor callbackExecutor) {
        if (callbackExecutor != null) {
            return new ExecutorCallAdapterFactory(callbackExecutor);
        }
        return DefaultCallAdapterFactory.INSTANCE;
    }

    boolean isDefaultMethod(Method method) {
        return false;
    }

    @Nullable
    Object invokeDefaultMethod(Method method, Class<?> cls, Object object, @Nullable Object... args) throws Throwable {
        throw new UnsupportedOperationException();
    }
}
