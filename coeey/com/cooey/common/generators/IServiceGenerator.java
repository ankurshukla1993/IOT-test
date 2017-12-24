package com.cooey.common.generators;

public interface IServiceGenerator {
    <T> T create(Class<T> cls);
}
