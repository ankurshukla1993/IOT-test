package io.realm;

import io.realm.internal.SharedRealm;
import io.realm.internal.SharedRealm.InitializationCallback;

class BaseRealm$2 implements InitializationCallback {
    final /* synthetic */ BaseRealm this$0;
    final /* synthetic */ Realm$Transaction val$initialDataTransaction;

    BaseRealm$2(BaseRealm this$0, Realm$Transaction realm$Transaction) {
        this.this$0 = this$0;
        this.val$initialDataTransaction = realm$Transaction;
    }

    public void onInit(SharedRealm sharedRealm) {
        this.val$initialDataTransaction.execute(Realm.createInstance(sharedRealm));
    }
}
