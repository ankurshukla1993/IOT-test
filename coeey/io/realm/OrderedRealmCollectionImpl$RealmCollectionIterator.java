package io.realm;

import io.realm.internal.Collection.Iterator;
import io.realm.internal.UncheckedRow;

class OrderedRealmCollectionImpl$RealmCollectionIterator extends Iterator<E> {
    final /* synthetic */ OrderedRealmCollectionImpl this$0;

    OrderedRealmCollectionImpl$RealmCollectionIterator(OrderedRealmCollectionImpl orderedRealmCollectionImpl) {
        this.this$0 = orderedRealmCollectionImpl;
        super(orderedRealmCollectionImpl.collection);
    }

    protected E convertRowToObject(UncheckedRow row) {
        return this.this$0.realm.get(this.this$0.classSpec, this.this$0.className, row);
    }
}
