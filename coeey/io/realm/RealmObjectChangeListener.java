package io.realm;

import javax.annotation.Nullable;

public interface RealmObjectChangeListener<T extends RealmModel> {
    void onChange(T t, @Nullable ObjectChangeSet objectChangeSet);
}
