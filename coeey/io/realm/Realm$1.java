package io.realm;

import io.realm.Realm$Transaction.OnError;
import io.realm.Realm$Transaction.OnSuccess;
import io.realm.exceptions.RealmException;
import io.realm.internal.RealmNotifier;
import io.realm.internal.SharedRealm.VersionID;

class Realm$1 implements Runnable {
    final /* synthetic */ Realm this$0;
    final /* synthetic */ boolean val$canDeliverNotification;
    final /* synthetic */ OnError val$onError;
    final /* synthetic */ OnSuccess val$onSuccess;
    final /* synthetic */ RealmConfiguration val$realmConfiguration;
    final /* synthetic */ RealmNotifier val$realmNotifier;
    final /* synthetic */ Realm$Transaction val$transaction;

    Realm$1(Realm this$0, RealmConfiguration realmConfiguration, Realm$Transaction realm$Transaction, boolean z, OnSuccess onSuccess, RealmNotifier realmNotifier, OnError onError) {
        this.this$0 = this$0;
        this.val$realmConfiguration = realmConfiguration;
        this.val$transaction = realm$Transaction;
        this.val$canDeliverNotification = z;
        this.val$onSuccess = onSuccess;
        this.val$realmNotifier = realmNotifier;
        this.val$onError = onError;
    }

    public void run() {
        if (!Thread.currentThread().isInterrupted()) {
            VersionID versionID = null;
            Throwable exception = null;
            Realm bgRealm = Realm.getInstance(this.val$realmConfiguration);
            bgRealm.beginTransaction();
            try {
                this.val$transaction.execute(bgRealm);
                if (Thread.currentThread().isInterrupted()) {
                    try {
                        if (bgRealm.isInTransaction()) {
                            bgRealm.cancelTransaction();
                        }
                        bgRealm.close();
                    } catch (Throwable th) {
                        bgRealm.close();
                    }
                } else {
                    bgRealm.commitTransaction();
                    versionID = bgRealm.sharedRealm.getVersionID();
                    try {
                        if (bgRealm.isInTransaction()) {
                            bgRealm.cancelTransaction();
                        }
                        bgRealm.close();
                        final Throwable backgroundException = exception;
                        final VersionID backgroundVersionID = versionID;
                        if (this.val$canDeliverNotification) {
                            if (backgroundVersionID != null && this.val$onSuccess != null) {
                                this.val$realmNotifier.post(new Runnable() {

                                    class C24301 implements Runnable {
                                        C24301() {
                                        }

                                        public void run() {
                                            Realm$1.this.val$onSuccess.onSuccess();
                                        }
                                    }

                                    public void run() {
                                        if (Realm$1.this.this$0.isClosed()) {
                                            Realm$1.this.val$onSuccess.onSuccess();
                                        } else if (Realm$1.this.this$0.sharedRealm.getVersionID().compareTo(backgroundVersionID) < 0) {
                                            Realm$1.this.this$0.sharedRealm.realmNotifier.addTransactionCallback(new C24301());
                                        } else {
                                            Realm$1.this.val$onSuccess.onSuccess();
                                        }
                                    }
                                });
                            } else if (backgroundException != null) {
                                this.val$realmNotifier.post(new Runnable() {
                                    public void run() {
                                        if (Realm$1.this.val$onError != null) {
                                            Realm$1.this.val$onError.onError(backgroundException);
                                            return;
                                        }
                                        throw new RealmException("Async transaction failed", backgroundException);
                                    }
                                });
                            }
                        } else if (backgroundException != null) {
                            throw new RealmException("Async transaction failed", backgroundException);
                        }
                    } catch (Throwable th2) {
                        bgRealm.close();
                    }
                }
            } catch (Throwable th3) {
                bgRealm.close();
            }
        }
    }
}
