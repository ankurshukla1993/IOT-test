package com.google.common.eventbus;

import com.google.common.base.Objects;
import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

class AnnotatedSubscriberFinder implements SubscriberFindingStrategy {
    private static final LoadingCache<Class<?>, ImmutableList<Method>> subscriberMethodsCache = CacheBuilder.newBuilder().weakKeys().build(new C17671());

    static class C17671 extends CacheLoader<Class<?>, ImmutableList<Method>> {
        C17671() {
        }

        public ImmutableList<Method> load(Class<?> concreteClass) throws Exception {
            return AnnotatedSubscriberFinder.getAnnotatedMethodsInternal(concreteClass);
        }
    }

    private static final class MethodIdentifier {
        private final String name;
        private final List<Class<?>> parameterTypes;

        MethodIdentifier(Method method) {
            this.name = method.getName();
            this.parameterTypes = Arrays.asList(method.getParameterTypes());
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{this.name, this.parameterTypes});
        }

        public boolean equals(@Nullable Object o) {
            if (!(o instanceof MethodIdentifier)) {
                return false;
            }
            MethodIdentifier ident = (MethodIdentifier) o;
            if (this.name.equals(ident.name) && this.parameterTypes.equals(ident.parameterTypes)) {
                return true;
            }
            return false;
        }
    }

    AnnotatedSubscriberFinder() {
    }

    public Multimap<Class<?>, EventSubscriber> findAllSubscribers(Object listener) {
        Multimap<Class<?>, EventSubscriber> methodsInListener = HashMultimap.create();
        Iterator i$ = getAnnotatedMethods(listener.getClass()).iterator();
        while (i$.hasNext()) {
            Method method = (Method) i$.next();
            methodsInListener.put(method.getParameterTypes()[0], makeSubscriber(listener, method));
        }
        return methodsInListener;
    }

    private static ImmutableList<Method> getAnnotatedMethods(Class<?> clazz) {
        try {
            return (ImmutableList) subscriberMethodsCache.getUnchecked(clazz);
        } catch (UncheckedExecutionException e) {
            throw Throwables.propagate(e.getCause());
        }
    }

    private static ImmutableList<Method> getAnnotatedMethodsInternal(Class<?> clazz) {
        Set<? extends Class<?>> supers = TypeToken.of(clazz).getTypes().rawTypes();
        Map<MethodIdentifier, Method> identifiers = Maps.newHashMap();
        for (Class<?> superClazz : supers) {
            for (Method superClazzMethod : superClazz.getMethods()) {
                if (superClazzMethod.isAnnotationPresent(Subscribe.class) && !superClazzMethod.isBridge()) {
                    Class<?>[] parameterTypes = superClazzMethod.getParameterTypes();
                    if (parameterTypes.length != 1) {
                        String valueOf = String.valueOf(String.valueOf(superClazzMethod));
                        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 128).append("Method ").append(valueOf).append(" has @Subscribe annotation, but requires ").append(parameterTypes.length).append(" arguments.  Event subscriber methods must require a single argument.").toString());
                    }
                    MethodIdentifier ident = new MethodIdentifier(superClazzMethod);
                    if (!identifiers.containsKey(ident)) {
                        identifiers.put(ident, superClazzMethod);
                    }
                }
            }
        }
        return ImmutableList.copyOf(identifiers.values());
    }

    private static EventSubscriber makeSubscriber(Object listener, Method method) {
        if (methodIsDeclaredThreadSafe(method)) {
            return new EventSubscriber(listener, method);
        }
        return new SynchronizedEventSubscriber(listener, method);
    }

    private static boolean methodIsDeclaredThreadSafe(Method method) {
        return method.getAnnotation(AllowConcurrentEvents.class) != null;
    }
}
