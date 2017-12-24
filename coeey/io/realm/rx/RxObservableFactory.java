package io.realm.rx;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmModel;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import rx.Observable;

public interface RxObservableFactory {
    Observable<DynamicRealm> from(DynamicRealm dynamicRealm);

    Observable<DynamicRealmObject> from(DynamicRealm dynamicRealm, DynamicRealmObject dynamicRealmObject);

    Observable<RealmList<DynamicRealmObject>> from(DynamicRealm dynamicRealm, RealmList<DynamicRealmObject> realmList);

    Observable<RealmQuery<DynamicRealmObject>> from(DynamicRealm dynamicRealm, RealmQuery<DynamicRealmObject> realmQuery);

    Observable<RealmResults<DynamicRealmObject>> from(DynamicRealm dynamicRealm, RealmResults<DynamicRealmObject> realmResults);

    Observable<Realm> from(Realm realm);

    <E extends RealmModel> Observable<RealmList<E>> from(Realm realm, RealmList<E> realmList);

    <E extends RealmModel> Observable<E> from(Realm realm, E e);

    <E extends RealmModel> Observable<RealmQuery<E>> from(Realm realm, RealmQuery<E> realmQuery);

    <E extends RealmModel> Observable<RealmResults<E>> from(Realm realm, RealmResults<E> realmResults);
}
