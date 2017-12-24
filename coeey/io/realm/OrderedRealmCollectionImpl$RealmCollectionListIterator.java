package io.realm;

import io.realm.internal.Collection.ListIterator;
import io.realm.internal.UncheckedRow;

class OrderedRealmCollectionImpl$RealmCollectionListIterator extends ListIterator<E> {
    final /* synthetic */ OrderedRealmCollectionImpl this$0;

    OrderedRealmCollectionImpl$RealmCollectionListIterator(OrderedRealmCollectionImpl orderedRealmCollectionImpl, int start) {
        this.this$0 = orderedRealmCollectionImpl;
        super(orderedRealmCollectionImpl.collection, start);
    }

    protected E convertRowToObject(UncheckedRow row) {
        return this.this$0.realm.get(this.this$0.classSpec, this.this$0.className, row);
    }
}
