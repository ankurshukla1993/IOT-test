package retrofit2;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;
import retrofit2.CallAdapter.Factory;

class Platform$Android extends Platform {

    static class MainThreadExecutor implements Executor {
        private final Handler handler = new Handler(Looper.getMainLooper());

        MainThreadExecutor() {
        }

        public void execute(Runnable r) {
            this.handler.post(r);
        }
    }

    Platform$Android() {
    }

    public Executor defaultCallbackExecutor() {
        return new MainThreadExecutor();
    }

    Factory defaultCallAdapterFactory(@Nullable Executor callbackExecutor) {
        if (callbackExecutor != null) {
            return new ExecutorCallAdapterFactory(callbackExecutor);
        }
        throw new AssertionError();
    }
}
