package io.reactivex.internal.queue;

import io.reactivex.annotations.Nullable;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import java.util.concurrent.atomic.AtomicReference;

public final class MpscLinkedQueue<T> implements SimplePlainQueue<T> {
    private final AtomicReference<LinkedQueueNode<T>> consumerNode = new AtomicReference();
    private final AtomicReference<LinkedQueueNode<T>> producerNode = new AtomicReference();

    static final class LinkedQueueNode<E> extends AtomicReference<LinkedQueueNode<E>> {
        private static final long serialVersionUID = 2404266111789071508L;
        private E value;

        LinkedQueueNode() {
        }

        LinkedQueueNode(E val) {
            spValue(val);
        }

        public E getAndNullValue() {
            E temp = lpValue();
            spValue(null);
            return temp;
        }

        public E lpValue() {
            return this.value;
        }

        public void spValue(E newValue) {
            this.value = newValue;
        }

        public void soNext(LinkedQueueNode<E> n) {
            lazySet(n);
        }

        public LinkedQueueNode<E> lvNext() {
            return (LinkedQueueNode) get();
        }
    }

    public MpscLinkedQueue() {
        LinkedQueueNode<T> node = new LinkedQueueNode();
        spConsumerNode(node);
        xchgProducerNode(node);
    }

    public boolean offer(T e) {
        if (e == null) {
            throw new NullPointerException("Null is not a valid element");
        }
        LinkedQueueNode<T> nextNode = new LinkedQueueNode(e);
        xchgProducerNode(nextNode).soNext(nextNode);
        return true;
    }

    @Nullable
    public T poll() {
        LinkedQueueNode<T> currConsumerNode = lpConsumerNode();
        LinkedQueueNode<T> nextNode = currConsumerNode.lvNext();
        T nextValue;
        if (nextNode != null) {
            nextValue = nextNode.getAndNullValue();
            spConsumerNode(nextNode);
            return nextValue;
        } else if (currConsumerNode == lvProducerNode()) {
            return null;
        } else {
            do {
                nextNode = currConsumerNode.lvNext();
            } while (nextNode == null);
            nextValue = nextNode.getAndNullValue();
            spConsumerNode(nextNode);
            return nextValue;
        }
    }

    public boolean offer(T v1, T v2) {
        offer(v1);
        offer(v2);
        return true;
    }

    public void clear() {
        while (poll() != null) {
            if (isEmpty()) {
                return;
            }
        }
    }

    LinkedQueueNode<T> lvProducerNode() {
        return (LinkedQueueNode) this.producerNode.get();
    }

    LinkedQueueNode<T> xchgProducerNode(LinkedQueueNode<T> node) {
        return (LinkedQueueNode) this.producerNode.getAndSet(node);
    }

    LinkedQueueNode<T> lvConsumerNode() {
        return (LinkedQueueNode) this.consumerNode.get();
    }

    LinkedQueueNode<T> lpConsumerNode() {
        return (LinkedQueueNode) this.consumerNode.get();
    }

    void spConsumerNode(LinkedQueueNode<T> node) {
        this.consumerNode.lazySet(node);
    }

    public boolean isEmpty() {
        return lvConsumerNode() == lvProducerNode();
    }
}
