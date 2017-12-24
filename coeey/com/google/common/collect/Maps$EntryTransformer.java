package com.google.common.collect;

import javax.annotation.Nullable;

public interface Maps$EntryTransformer<K, V1, V2> {
    V2 transformEntry(@Nullable K k, @Nullable V1 v1);
}
