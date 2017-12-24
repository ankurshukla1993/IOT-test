package android.arch.lifecycle;

import android.arch.core.util.Function;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;

public class Transformations {
    private Transformations() {
    }

    @MainThread
    public static <X, Y> LiveData<Y> map(LiveData<X> source, final Function<X, Y> func) {
        final MediatorLiveData<Y> result = new MediatorLiveData();
        result.addSource(source, new Observer<X>() {
            public void onChanged(@Nullable X x) {
                result.setValue(func.apply(x));
            }
        });
        return result;
    }

    @MainThread
    public static <X, Y> LiveData<Y> switchMap(LiveData<X> trigger, final Function<X, LiveData<Y>> func) {
        final MediatorLiveData<Y> result = new MediatorLiveData();
        result.addSource(trigger, new Observer<X>() {
            LiveData<Y> mSource;

            class C00171 implements Observer<Y> {
                C00171() {
                }

                public void onChanged(@Nullable Y y) {
                    result.setValue(y);
                }
            }

            public void onChanged(@Nullable X x) {
                LiveData<Y> newLiveData = (LiveData) func.apply(x);
                if (this.mSource != newLiveData) {
                    if (this.mSource != null) {
                        result.removeSource(this.mSource);
                    }
                    this.mSource = newLiveData;
                    if (this.mSource != null) {
                        result.addSource(this.mSource, new C00171());
                    }
                }
            }
        });
        return result;
    }
}
