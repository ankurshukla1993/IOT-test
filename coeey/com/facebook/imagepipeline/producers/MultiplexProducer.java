package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.VisibleForTesting;
import java.io.Closeable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public abstract class MultiplexProducer<K, T extends Closeable> implements Producer<T> {
    private final Producer<T> mInputProducer;
    @GuardedBy("this")
    @VisibleForTesting
    final Map<K, Multiplexer> mMultiplexers = new HashMap();

    protected abstract T cloneOrNull(T t);

    protected abstract K getKey(ProducerContext producerContext);

    protected MultiplexProducer(Producer<T> inputProducer) {
        this.mInputProducer = inputProducer;
    }

    public void produceResults(Consumer<T> consumer, ProducerContext context) {
        K key = getKey(context);
        Multiplexer multiplexer;
        do {
            boolean createdNewMultiplexer = false;
            synchronized (this) {
                multiplexer = getExistingMultiplexer(key);
                if (multiplexer == null) {
                    multiplexer = createAndPutNewMultiplexer(key);
                    createdNewMultiplexer = true;
                }
            }
        } while (!multiplexer.addNewConsumer(consumer, context));
        if (createdNewMultiplexer) {
            Multiplexer.access$000(multiplexer);
        }
    }

    private synchronized Multiplexer getExistingMultiplexer(K key) {
        return (Multiplexer) this.mMultiplexers.get(key);
    }

    private synchronized Multiplexer createAndPutNewMultiplexer(K key) {
        Multiplexer multiplexer;
        multiplexer = new Multiplexer(this, key);
        this.mMultiplexers.put(key, multiplexer);
        return multiplexer;
    }

    private synchronized void removeMultiplexer(K key, Multiplexer multiplexer) {
        if (this.mMultiplexers.get(key) == multiplexer) {
            this.mMultiplexers.remove(key);
        }
    }
}
