package io.realm.rx;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import java.util.IdentityHashMap;
import java.util.Map;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

public class RealmObservableFactory implements RxObservableFactory {
    ThreadLocal<StrongReferenceCounter<RealmList>> listRefs = new C24482();
    ThreadLocal<StrongReferenceCounter<RealmModel>> objectRefs = new C24493();
    ThreadLocal<StrongReferenceCounter<RealmResults>> resultsRefs = new C24471();

    class C24471 extends ThreadLocal<StrongReferenceCounter<RealmResults>> {
        C24471() {
        }

        protected StrongReferenceCounter<RealmResults> initialValue() {
            return new StrongReferenceCounter();
        }
    }

    class C24482 extends ThreadLocal<StrongReferenceCounter<RealmList>> {
        C24482() {
        }

        protected StrongReferenceCounter<RealmList> initialValue() {
            return new StrongReferenceCounter();
        }
    }

    class C24493 extends ThreadLocal<StrongReferenceCounter<RealmModel>> {
        C24493() {
        }

        protected StrongReferenceCounter<RealmModel> initialValue() {
            return new StrongReferenceCounter();
        }
    }

    private static class StrongReferenceCounter<K> {
        private final Map<K, Integer> references;

        private StrongReferenceCounter() {
            this.references = new IdentityHashMap();
        }

        public void acquireReference(K object) {
            Integer count = (Integer) this.references.get(object);
            if (count == null) {
                this.references.put(object, Integer.valueOf(1));
            } else {
                this.references.put(object, Integer.valueOf(count.intValue() + 1));
            }
        }

        public void releaseReference(K object) {
            Integer count = (Integer) this.references.get(object);
            if (count == null) {
                throw new IllegalStateException("Object does not have any references: " + object);
            } else if (count.intValue() > 1) {
                this.references.put(object, Integer.valueOf(count.intValue() - 1));
            } else if (count.intValue() == 1) {
                this.references.remove(object);
            } else {
                throw new IllegalStateException("Invalid reference count: " + count);
            }
        }
    }

    public Observable<Realm> from(Realm realm) {
        final RealmConfiguration realmConfig = realm.getConfiguration();
        return Observable.create(new OnSubscribe<Realm>() {
            public void call(final Subscriber<? super Realm> subscriber) {
                final Realm observableRealm = Realm.getInstance(realmConfig);
                final RealmChangeListener<Realm> listener = new RealmChangeListener<Realm>() {
                    public void onChange(Realm realm) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(observableRealm);
                        }
                    }
                };
                observableRealm.addChangeListener(listener);
                subscriber.add(Subscriptions.create(new Action0() {
                    public void call() {
                        observableRealm.removeChangeListener(listener);
                        observableRealm.close();
                    }
                }));
                subscriber.onNext(observableRealm);
            }
        });
    }

    public Observable<DynamicRealm> from(DynamicRealm realm) {
        final RealmConfiguration realmConfig = realm.getConfiguration();
        return Observable.create(new OnSubscribe<DynamicRealm>() {
            public void call(final Subscriber<? super DynamicRealm> subscriber) {
                final DynamicRealm observableRealm = DynamicRealm.getInstance(realmConfig);
                final RealmChangeListener<DynamicRealm> listener = new RealmChangeListener<DynamicRealm>() {
                    public void onChange(DynamicRealm realm) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(observableRealm);
                        }
                    }
                };
                observableRealm.addChangeListener(listener);
                subscriber.add(Subscriptions.create(new Action0() {
                    public void call() {
                        observableRealm.removeChangeListener(listener);
                        observableRealm.close();
                    }
                }));
                subscriber.onNext(observableRealm);
            }
        });
    }

    public <E extends RealmModel> Observable<RealmResults<E>> from(Realm realm, final RealmResults<E> results) {
        final RealmConfiguration realmConfig = realm.getConfiguration();
        return Observable.create(new OnSubscribe<RealmResults<E>>() {
            public void call(final Subscriber<? super RealmResults<E>> subscriber) {
                final Realm observableRealm = Realm.getInstance(realmConfig);
                ((StrongReferenceCounter) RealmObservableFactory.this.resultsRefs.get()).acquireReference(results);
                final RealmChangeListener<RealmResults<E>> listener = new RealmChangeListener<RealmResults<E>>() {
                    public void onChange(RealmResults<E> realmResults) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(results);
                        }
                    }
                };
                results.addChangeListener(listener);
                subscriber.add(Subscriptions.create(new Action0() {
                    public void call() {
                        results.removeChangeListener(listener);
                        observableRealm.close();
                        ((StrongReferenceCounter) RealmObservableFactory.this.resultsRefs.get()).releaseReference(results);
                    }
                }));
                subscriber.onNext(results);
            }
        });
    }

    public Observable<RealmResults<DynamicRealmObject>> from(DynamicRealm realm, final RealmResults<DynamicRealmObject> results) {
        final RealmConfiguration realmConfig = realm.getConfiguration();
        return Observable.create(new OnSubscribe<RealmResults<DynamicRealmObject>>() {
            public void call(final Subscriber<? super RealmResults<DynamicRealmObject>> subscriber) {
                final DynamicRealm observableRealm = DynamicRealm.getInstance(realmConfig);
                ((StrongReferenceCounter) RealmObservableFactory.this.resultsRefs.get()).acquireReference(results);
                final RealmChangeListener<RealmResults<DynamicRealmObject>> listener = new RealmChangeListener<RealmResults<DynamicRealmObject>>() {
                    public void onChange(RealmResults<DynamicRealmObject> realmResults) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(results);
                        }
                    }
                };
                results.addChangeListener(listener);
                subscriber.add(Subscriptions.create(new Action0() {
                    public void call() {
                        results.removeChangeListener(listener);
                        observableRealm.close();
                        ((StrongReferenceCounter) RealmObservableFactory.this.resultsRefs.get()).releaseReference(results);
                    }
                }));
                subscriber.onNext(results);
            }
        });
    }

    public <E extends RealmModel> Observable<RealmList<E>> from(Realm realm, final RealmList<E> list) {
        final RealmConfiguration realmConfig = realm.getConfiguration();
        return Observable.create(new OnSubscribe<RealmList<E>>() {
            public void call(final Subscriber<? super RealmList<E>> subscriber) {
                final Realm observableRealm = Realm.getInstance(realmConfig);
                ((StrongReferenceCounter) RealmObservableFactory.this.listRefs.get()).acquireReference(list);
                final RealmChangeListener<RealmList<E>> listener = new RealmChangeListener<RealmList<E>>() {
                    public void onChange(RealmList<E> realmList) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(list);
                        }
                    }
                };
                list.addChangeListener(listener);
                subscriber.add(Subscriptions.create(new Action0() {
                    public void call() {
                        list.removeChangeListener(listener);
                        observableRealm.close();
                        ((StrongReferenceCounter) RealmObservableFactory.this.listRefs.get()).releaseReference(list);
                    }
                }));
                subscriber.onNext(list);
            }
        });
    }

    public Observable<RealmList<DynamicRealmObject>> from(DynamicRealm realm, final RealmList<DynamicRealmObject> list) {
        final RealmConfiguration realmConfig = realm.getConfiguration();
        return Observable.create(new OnSubscribe<RealmList<DynamicRealmObject>>() {
            public void call(final Subscriber<? super RealmList<DynamicRealmObject>> subscriber) {
                final DynamicRealm observableRealm = DynamicRealm.getInstance(realmConfig);
                ((StrongReferenceCounter) RealmObservableFactory.this.listRefs.get()).acquireReference(list);
                final RealmChangeListener<RealmList<DynamicRealmObject>> listener = new RealmChangeListener<RealmList<DynamicRealmObject>>() {
                    public void onChange(RealmList<DynamicRealmObject> realmList) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(list);
                        }
                    }
                };
                list.addChangeListener(listener);
                subscriber.add(Subscriptions.create(new Action0() {
                    public void call() {
                        list.removeChangeListener(listener);
                        observableRealm.close();
                        ((StrongReferenceCounter) RealmObservableFactory.this.listRefs.get()).releaseReference(list);
                    }
                }));
                subscriber.onNext(list);
            }
        });
    }

    public <E extends RealmModel> Observable<E> from(Realm realm, final E object) {
        final RealmConfiguration realmConfig = realm.getConfiguration();
        return Observable.create(new OnSubscribe<E>() {
            public void call(final Subscriber<? super E> subscriber) {
                final Realm observableRealm = Realm.getInstance(realmConfig);
                ((StrongReferenceCounter) RealmObservableFactory.this.objectRefs.get()).acquireReference(object);
                final RealmChangeListener<E> listener = new RealmChangeListener<E>() {
                    public void onChange(E object) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(object);
                        }
                    }
                };
                RealmObject.addChangeListener(object, listener);
                subscriber.add(Subscriptions.create(new Action0() {
                    public void call() {
                        RealmObject.removeChangeListener(object, listener);
                        observableRealm.close();
                        ((StrongReferenceCounter) RealmObservableFactory.this.objectRefs.get()).releaseReference(object);
                    }
                }));
                subscriber.onNext(object);
            }
        });
    }

    public Observable<DynamicRealmObject> from(DynamicRealm realm, final DynamicRealmObject object) {
        final RealmConfiguration realmConfig = realm.getConfiguration();
        return Observable.create(new OnSubscribe<DynamicRealmObject>() {
            public void call(final Subscriber<? super DynamicRealmObject> subscriber) {
                final DynamicRealm observableRealm = DynamicRealm.getInstance(realmConfig);
                ((StrongReferenceCounter) RealmObservableFactory.this.objectRefs.get()).acquireReference(object);
                final RealmChangeListener<DynamicRealmObject> listener = new RealmChangeListener<DynamicRealmObject>() {
                    public void onChange(DynamicRealmObject object) {
                        if (!subscriber.isUnsubscribed()) {
                            subscriber.onNext(object);
                        }
                    }
                };
                RealmObject.addChangeListener(object, listener);
                subscriber.add(Subscriptions.create(new Action0() {
                    public void call() {
                        RealmObject.removeChangeListener(object, listener);
                        observableRealm.close();
                        ((StrongReferenceCounter) RealmObservableFactory.this.objectRefs.get()).releaseReference(object);
                    }
                }));
                subscriber.onNext(object);
            }
        });
    }

    public <E extends RealmModel> Observable<RealmQuery<E>> from(Realm realm, RealmQuery<E> realmQuery) {
        throw new RuntimeException("RealmQuery not supported yet.");
    }

    public Observable<RealmQuery<DynamicRealmObject>> from(DynamicRealm realm, RealmQuery<DynamicRealmObject> realmQuery) {
        throw new RuntimeException("RealmQuery not supported yet.");
    }

    public boolean equals(Object o) {
        return o instanceof RealmObservableFactory;
    }

    public int hashCode() {
        return 37;
    }
}
