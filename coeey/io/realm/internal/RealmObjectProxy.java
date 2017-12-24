package io.realm.internal;

import io.realm.ProxyState;
import io.realm.RealmModel;

public interface RealmObjectProxy extends RealmModel {

    public static class CacheData<E extends RealmModel> {
        public int minDepth;
        public final E object;

        public CacheData(int minDepth, E object) {
            this.minDepth = minDepth;
            this.object = object;
        }
    }

    void realm$injectObjectContext();

    ProxyState realmGet$proxyState();
}
