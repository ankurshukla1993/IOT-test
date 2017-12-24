package android.arch.lifecycle;

import android.arch.core.executor.AppToolkitTaskExecutor;
import android.arch.core.internal.SafeIterableMap;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.Lifecycle.State;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import java.util.Iterator;
import java.util.Map.Entry;

public abstract class LiveData<T> {
    private static final LifecycleOwner ALWAYS_ON = new C00101();
    private static final Object NOT_SET = new Object();
    static final int START_VERSION = -1;
    private int mActiveCount = 0;
    private volatile Object mData = NOT_SET;
    private final Object mDataLock = new Object();
    private boolean mDispatchInvalidated;
    private boolean mDispatchingValue;
    private SafeIterableMap<Observer<T>, LifecycleBoundObserver> mObservers = new SafeIterableMap();
    private volatile Object mPendingData = NOT_SET;
    private final Runnable mPostValueRunnable = new C00112();
    private int mVersion = -1;

    static class C00101 implements LifecycleOwner {
        private LifecycleRegistry mRegistry = init();

        C00101() {
        }

        private LifecycleRegistry init() {
            LifecycleRegistry registry = new LifecycleRegistry(this);
            registry.handleLifecycleEvent(Event.ON_CREATE);
            registry.handleLifecycleEvent(Event.ON_START);
            registry.handleLifecycleEvent(Event.ON_RESUME);
            return registry;
        }

        public Lifecycle getLifecycle() {
            return this.mRegistry;
        }
    }

    class C00112 implements Runnable {
        C00112() {
        }

        public void run() {
            Object newValue;
            synchronized (LiveData.this.mDataLock) {
                newValue = LiveData.this.mPendingData;
                LiveData.this.mPendingData = LiveData.NOT_SET;
            }
            LiveData.this.setValue(newValue);
        }
    }

    class LifecycleBoundObserver implements LifecycleObserver {
        public boolean active;
        public int lastVersion = -1;
        public final Observer<T> observer;
        public final LifecycleOwner owner;

        LifecycleBoundObserver(LifecycleOwner owner, Observer<T> observer) {
            this.owner = owner;
            this.observer = observer;
        }

        @OnLifecycleEvent(Event.ON_ANY)
        void onStateChange() {
            if (this.owner.getLifecycle().getCurrentState() == State.DESTROYED) {
                LiveData.this.removeObserver(this.observer);
            } else {
                activeStateChanged(LiveData.isActiveState(this.owner.getLifecycle().getCurrentState()));
            }
        }

        void activeStateChanged(boolean newActive) {
            int i = 1;
            if (newActive != this.active) {
                this.active = newActive;
                boolean wasInactive = LiveData.this.mActiveCount == 0;
                LiveData liveData = LiveData.this;
                int access$300 = liveData.mActiveCount;
                if (!this.active) {
                    i = -1;
                }
                liveData.mActiveCount = i + access$300;
                if (wasInactive && this.active) {
                    LiveData.this.onActive();
                }
                if (LiveData.this.mActiveCount == 0 && !this.active) {
                    LiveData.this.onInactive();
                }
                if (this.active) {
                    LiveData.this.dispatchingValue(this);
                }
            }
        }
    }

    private void considerNotify(LifecycleBoundObserver observer) {
        if (observer.active && isActiveState(observer.owner.getLifecycle().getCurrentState()) && observer.lastVersion < this.mVersion) {
            observer.lastVersion = this.mVersion;
            observer.observer.onChanged(this.mData);
        }
    }

    private void dispatchingValue(@Nullable LifecycleBoundObserver initiator) {
        if (this.mDispatchingValue) {
            this.mDispatchInvalidated = true;
            return;
        }
        this.mDispatchingValue = true;
        do {
            this.mDispatchInvalidated = false;
            if (initiator == null) {
                Iterator<Entry<Observer<T>, LifecycleBoundObserver>> iterator = this.mObservers.iteratorWithAdditions();
                while (iterator.hasNext()) {
                    considerNotify((LifecycleBoundObserver) ((Entry) iterator.next()).getValue());
                    if (this.mDispatchInvalidated) {
                        break;
                    }
                }
            }
            considerNotify(initiator);
            initiator = null;
        } while (this.mDispatchInvalidated);
        this.mDispatchingValue = false;
    }

    @MainThread
    public void observe(LifecycleOwner owner, Observer<T> observer) {
        if (owner.getLifecycle().getCurrentState() != State.DESTROYED) {
            LifecycleBoundObserver wrapper = new LifecycleBoundObserver(owner, observer);
            LifecycleBoundObserver existing = (LifecycleBoundObserver) this.mObservers.putIfAbsent(observer, wrapper);
            if (existing != null && existing.owner != wrapper.owner) {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            } else if (existing == null) {
                owner.getLifecycle().addObserver(wrapper);
                wrapper.activeStateChanged(isActiveState(owner.getLifecycle().getCurrentState()));
            }
        }
    }

    @MainThread
    public void observeForever(Observer<T> observer) {
        observe(ALWAYS_ON, observer);
    }

    @MainThread
    public void removeObserver(Observer<T> observer) {
        assertMainThread("removeObserver");
        LifecycleBoundObserver removed = (LifecycleBoundObserver) this.mObservers.remove(observer);
        if (removed != null) {
            removed.owner.getLifecycle().removeObserver(removed);
            removed.activeStateChanged(false);
        }
    }

    @MainThread
    public void removeObservers(LifecycleOwner owner) {
        assertMainThread("removeObservers");
        Iterator it = this.mObservers.iterator();
        while (it.hasNext()) {
            Entry<Observer<T>, LifecycleBoundObserver> entry = (Entry) it.next();
            if (((LifecycleBoundObserver) entry.getValue()).owner == owner) {
                removeObserver((Observer) entry.getKey());
            }
        }
    }

    protected void postValue(T value) {
        synchronized (this.mDataLock) {
            boolean postTask = this.mPendingData == NOT_SET;
            this.mPendingData = value;
        }
        if (postTask) {
            AppToolkitTaskExecutor.getInstance().postToMainThread(this.mPostValueRunnable);
        }
    }

    @MainThread
    protected void setValue(T value) {
        assertMainThread("setValue");
        this.mVersion++;
        this.mData = value;
        dispatchingValue(null);
    }

    @Nullable
    public T getValue() {
        Object data = this.mData;
        return data != NOT_SET ? data : null;
    }

    int getVersion() {
        return this.mVersion;
    }

    protected void onActive() {
    }

    protected void onInactive() {
    }

    public boolean hasObservers() {
        return this.mObservers.size() > 0;
    }

    public boolean hasActiveObservers() {
        return this.mActiveCount > 0;
    }

    static boolean isActiveState(State state) {
        return state.isAtLeast(State.STARTED);
    }

    private void assertMainThread(String methodName) {
        if (!AppToolkitTaskExecutor.getInstance().isMainThread()) {
            throw new IllegalStateException("Cannot invoke " + methodName + " on a background" + " thread");
        }
    }
}
