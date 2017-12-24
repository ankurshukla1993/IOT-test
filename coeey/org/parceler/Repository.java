package org.parceler;

import java.util.Map;

public interface Repository<T> {
    Map<Class, T> get();
}
