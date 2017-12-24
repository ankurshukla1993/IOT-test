package com.google.common.reflect;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList$Builder;
import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.TypeToken.TypeCollector;

class TypeToken$TypeCollector$3 extends TypeToken$TypeCollector$ForwardingTypeCollector<K> {
    final /* synthetic */ TypeCollector this$0;

    TypeToken$TypeCollector$3(TypeCollector typeCollector, TypeCollector x0) {
        this.this$0 = typeCollector;
        super(x0);
    }

    Iterable<? extends K> getInterfaces(K k) {
        return ImmutableSet.of();
    }

    ImmutableList<K> collectTypes(Iterable<? extends K> types) {
        ImmutableList$Builder<K> builder = ImmutableList.builder();
        for (Object type : types) {
            if (!getRawType(type).isInterface()) {
                builder.add(type);
            }
        }
        return super.collectTypes(builder.build());
    }
}
