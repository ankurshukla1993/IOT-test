package io.realm.internal;

import io.realm.RealmChangeListener;
import io.realm.internal.ObserverPairList.Callback;
import io.realm.internal.ObserverPairList.ObserverPair;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

@Keep
public abstract class RealmNotifier implements Closeable {
    private final Callback<RealmObserverPair> onChangeCallBack = new C24411();
    private ObserverPairList<RealmObserverPair> realmObserverPairs = new ObserverPairList();
    private SharedRealm sharedRealm;
    private List<Runnable> transactionCallbacks = new ArrayList();

    class C24411 implements Callback<RealmObserverPair> {
        C24411() {
        }

        public void onCalled(RealmObserverPair pair, Object observer) {
            if (RealmNotifier.this.sharedRealm != null && !RealmNotifier.this.sharedRealm.isClosed()) {
                pair.onChange(observer);
            }
        }
    }

    private static class RealmObserverPair<T> extends ObserverPair<T, RealmChangeListener<T>> {
        public RealmObserverPair(T observer, RealmChangeListener<T> listener) {
            super(observer, listener);
        }

        private void onChange(T observer) {
            if (observer != null) {
                ((RealmChangeListener) this.listener).onChange(observer);
            }
        }
    }

    public abstract boolean post(Runnable runnable);

    protected RealmNotifier(@Nullable SharedRealm sharedRealm) {
        this.sharedRealm = sharedRealm;
    }

    void didChange() {
        this.realmObserverPairs.foreach(this.onChangeCallBack);
        if (!this.transactionCallbacks.isEmpty()) {
            List<Runnable> callbacks = this.transactionCallbacks;
            this.transactionCallbacks = new ArrayList();
            for (Runnable runnable : callbacks) {
                runnable.run();
            }
        }
    }

    void beforeNotify() {
        this.sharedRealm.invalidateIterators();
    }

    public void close() {
        removeAllChangeListeners();
    }

    public <T> void addChangeListener(T observer, RealmChangeListener<T> realmChangeListener) {
        this.realmObserverPairs.add(new RealmObserverPair(observer, realmChangeListener));
    }

    public <E> void removeChangeListener(E observer, RealmChangeListener<E> realmChangeListener) {
        this.realmObserverPairs.remove(observer, realmChangeListener);
    }

    public <E> void removeChangeListeners(E observer) {
        this.realmObserverPairs.removeByObserver(observer);
    }

    private void removeAllChangeListeners() {
        this.realmObserverPairs.clear();
    }

    public void addTransactionCallback(Runnable runnable) {
        this.transactionCallbacks.add(runnable);
    }

    public int getListenersListSize() {
        return this.realmObserverPairs.size();
    }
}
