package android.arch.lifecycle;

import android.arch.lifecycle.Lifecycle.Event;

public class LiveData_LifecycleBoundObserver_LifecycleAdapter implements GenericLifecycleObserver {
    final LifecycleBoundObserver mReceiver;

    LiveData_LifecycleBoundObserver_LifecycleAdapter(LifecycleBoundObserver receiver) {
        this.mReceiver = receiver;
    }

    public void onStateChanged(LifecycleOwner owner, Event event) {
        this.mReceiver.onStateChange();
    }
}
