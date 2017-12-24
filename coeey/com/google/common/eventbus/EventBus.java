package com.google.common.eventbus;

import com.google.common.annotations.Beta;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.SetMultimap;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.logging.Level;
import java.util.logging.Logger;

@Beta
public class EventBus {
    private static final LoadingCache<Class<?>, Set<Class<?>>> flattenHierarchyCache = CacheBuilder.newBuilder().weakKeys().build(new C17691());
    private final ThreadLocal<Queue<EventWithSubscriber>> eventsToDispatch;
    private final SubscriberFindingStrategy finder;
    private final ThreadLocal<Boolean> isDispatching;
    private SubscriberExceptionHandler subscriberExceptionHandler;
    private final SetMultimap<Class<?>, EventSubscriber> subscribersByType;
    private final ReadWriteLock subscribersByTypeLock;

    static class C17691 extends CacheLoader<Class<?>, Set<Class<?>>> {
        C17691() {
        }

        public Set<Class<?>> load(Class<?> concreteClass) {
            return TypeToken.of(concreteClass).getTypes().rawTypes();
        }
    }

    class C17702 extends ThreadLocal<Queue<EventWithSubscriber>> {
        C17702() {
        }

        protected Queue<EventWithSubscriber> initialValue() {
            return new LinkedList();
        }
    }

    class C17713 extends ThreadLocal<Boolean> {
        C17713() {
        }

        protected Boolean initialValue() {
            return Boolean.valueOf(false);
        }
    }

    static class EventWithSubscriber {
        final Object event;
        final EventSubscriber subscriber;

        public EventWithSubscriber(Object event, EventSubscriber subscriber) {
            this.event = Preconditions.checkNotNull(event);
            this.subscriber = (EventSubscriber) Preconditions.checkNotNull(subscriber);
        }
    }

    private static final class LoggingSubscriberExceptionHandler implements SubscriberExceptionHandler {
        private final Logger logger;

        public LoggingSubscriberExceptionHandler(String identifier) {
            String valueOf = String.valueOf(String.valueOf(EventBus.class.getName()));
            String valueOf2 = String.valueOf(String.valueOf((String) Preconditions.checkNotNull(identifier)));
            this.logger = Logger.getLogger(new StringBuilder((valueOf.length() + 1) + valueOf2.length()).append(valueOf).append(".").append(valueOf2).toString());
        }

        public void handleException(Throwable exception, SubscriberExceptionContext context) {
            Logger logger = this.logger;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(String.valueOf(context.getSubscriber()));
            String valueOf2 = String.valueOf(String.valueOf(context.getSubscriberMethod()));
            logger.log(level, new StringBuilder((valueOf.length() + 30) + valueOf2.length()).append("Could not dispatch event: ").append(valueOf).append(" to ").append(valueOf2).toString(), exception.getCause());
        }
    }

    public EventBus() {
        this("default");
    }

    public EventBus(String identifier) {
        this(new LoggingSubscriberExceptionHandler(identifier));
    }

    public EventBus(SubscriberExceptionHandler subscriberExceptionHandler) {
        this.subscribersByType = HashMultimap.create();
        this.subscribersByTypeLock = new ReentrantReadWriteLock();
        this.finder = new AnnotatedSubscriberFinder();
        this.eventsToDispatch = new C17702();
        this.isDispatching = new C17713();
        this.subscriberExceptionHandler = (SubscriberExceptionHandler) Preconditions.checkNotNull(subscriberExceptionHandler);
    }

    public void register(Object object) {
        Multimap<Class<?>, EventSubscriber> methodsInListener = this.finder.findAllSubscribers(object);
        this.subscribersByTypeLock.writeLock().lock();
        try {
            this.subscribersByType.putAll(methodsInListener);
        } finally {
            this.subscribersByTypeLock.writeLock().unlock();
        }
    }

    public void unregister(Object object) {
        for (Entry<Class<?>, Collection<EventSubscriber>> entry : this.finder.findAllSubscribers(object).asMap().entrySet()) {
            Class<?> eventType = (Class) entry.getKey();
            Collection<EventSubscriber> eventMethodsInListener = (Collection) entry.getValue();
            this.subscribersByTypeLock.writeLock().lock();
            try {
                Set<EventSubscriber> currentSubscribers = this.subscribersByType.get(eventType);
                if (currentSubscribers.containsAll(eventMethodsInListener)) {
                    currentSubscribers.removeAll(eventMethodsInListener);
                    this.subscribersByTypeLock.writeLock().unlock();
                } else {
                    String valueOf = String.valueOf(String.valueOf(object));
                    throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 65).append("missing event subscriber for an annotated method. Is ").append(valueOf).append(" registered?").toString());
                }
            } catch (Throwable th) {
                this.subscribersByTypeLock.writeLock().unlock();
            }
        }
    }

    public void post(Object event) {
        boolean dispatched = false;
        for (Class<?> eventType : flattenHierarchy(event.getClass())) {
            this.subscribersByTypeLock.readLock().lock();
            try {
                Set<EventSubscriber> wrappers = this.subscribersByType.get(eventType);
                if (!wrappers.isEmpty()) {
                    dispatched = true;
                    for (EventSubscriber wrapper : wrappers) {
                        enqueueEvent(event, wrapper);
                    }
                }
                this.subscribersByTypeLock.readLock().unlock();
            } catch (Throwable th) {
                this.subscribersByTypeLock.readLock().unlock();
            }
        }
        if (!(dispatched || (event instanceof DeadEvent))) {
            post(new DeadEvent(this, event));
        }
        dispatchQueuedEvents();
    }

    void enqueueEvent(Object event, EventSubscriber subscriber) {
        ((Queue) this.eventsToDispatch.get()).offer(new EventWithSubscriber(event, subscriber));
    }

    void dispatchQueuedEvents() {
        if (!((Boolean) this.isDispatching.get()).booleanValue()) {
            this.isDispatching.set(Boolean.valueOf(true));
            try {
                Queue<EventWithSubscriber> events = (Queue) this.eventsToDispatch.get();
                while (true) {
                    EventWithSubscriber eventWithSubscriber = (EventWithSubscriber) events.poll();
                    if (eventWithSubscriber == null) {
                        break;
                    }
                    dispatch(eventWithSubscriber.event, eventWithSubscriber.subscriber);
                }
            } finally {
                this.isDispatching.remove();
                this.eventsToDispatch.remove();
            }
        }
    }

    void dispatch(Object event, EventSubscriber wrapper) {
        try {
            wrapper.handleEvent(event);
        } catch (InvocationTargetException e) {
            this.subscriberExceptionHandler.handleException(e.getCause(), new SubscriberExceptionContext(this, event, wrapper.getSubscriber(), wrapper.getMethod()));
        } catch (Throwable t) {
            Logger.getLogger(EventBus.class.getName()).log(Level.SEVERE, String.format("Exception %s thrown while handling exception: %s", new Object[]{t, e.getCause()}), t);
        }
    }

    @VisibleForTesting
    Set<Class<?>> flattenHierarchy(Class<?> concreteClass) {
        try {
            return (Set) flattenHierarchyCache.getUnchecked(concreteClass);
        } catch (UncheckedExecutionException e) {
            throw Throwables.propagate(e.getCause());
        }
    }
}
