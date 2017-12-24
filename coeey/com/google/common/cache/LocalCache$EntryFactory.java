package com.google.common.cache;

import com.google.common.cache.LocalCache.ReferenceEntry;
import com.google.common.cache.LocalCache.Segment;
import javax.annotation.Nullable;

enum LocalCache$EntryFactory {
    STRONG {
        <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            return new LocalCache$StrongEntry(key, hash, next);
        }
    },
    STRONG_ACCESS {
        <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            return new LocalCache$StrongAccessEntry(key, hash, next);
        }

        <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            copyAccessEntry(original, newEntry);
            return newEntry;
        }
    },
    STRONG_WRITE {
        <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            return new LocalCache$StrongWriteEntry(key, hash, next);
        }

        <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            copyWriteEntry(original, newEntry);
            return newEntry;
        }
    },
    STRONG_ACCESS_WRITE {
        <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            return new LocalCache$StrongAccessWriteEntry(key, hash, next);
        }

        <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            copyAccessEntry(original, newEntry);
            copyWriteEntry(original, newEntry);
            return newEntry;
        }
    },
    WEAK {
        <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            return new LocalCache$WeakEntry(segment.keyReferenceQueue, key, hash, next);
        }
    },
    WEAK_ACCESS {
        <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            return new LocalCache$WeakAccessEntry(segment.keyReferenceQueue, key, hash, next);
        }

        <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            copyAccessEntry(original, newEntry);
            return newEntry;
        }
    },
    WEAK_WRITE {
        <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            return new LocalCache$WeakWriteEntry(segment.keyReferenceQueue, key, hash, next);
        }

        <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            copyWriteEntry(original, newEntry);
            return newEntry;
        }
    },
    WEAK_ACCESS_WRITE {
        <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
            return new LocalCache$WeakAccessWriteEntry(segment.keyReferenceQueue, key, hash, next);
        }

        <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
            ReferenceEntry<K, V> newEntry = super.copyEntry(segment, original, newNext);
            copyAccessEntry(original, newEntry);
            copyWriteEntry(original, newEntry);
            return newEntry;
        }
    };
    
    static final int ACCESS_MASK = 1;
    static final int WEAK_MASK = 4;
    static final int WRITE_MASK = 2;
    static final LocalCache$EntryFactory[] factories = null;

    abstract <K, V> ReferenceEntry<K, V> newEntry(Segment<K, V> segment, K k, int i, @Nullable ReferenceEntry<K, V> referenceEntry);

    static {
        factories = new LocalCache$EntryFactory[]{STRONG, STRONG_ACCESS, STRONG_WRITE, STRONG_ACCESS_WRITE, WEAK, WEAK_ACCESS, WEAK_WRITE, WEAK_ACCESS_WRITE};
    }

    static LocalCache$EntryFactory getFactory(LocalCache$Strength keyStrength, boolean usesAccessQueue, boolean usesWriteQueue) {
        int i;
        int i2 = 0;
        if (keyStrength == LocalCache$Strength.WEAK) {
            i = 4;
        } else {
            i = 0;
        }
        int i3 = (usesAccessQueue ? 1 : 0) | i;
        if (usesWriteQueue) {
            i2 = 2;
        }
        return factories[i3 | i2];
    }

    <K, V> ReferenceEntry<K, V> copyEntry(Segment<K, V> segment, ReferenceEntry<K, V> original, ReferenceEntry<K, V> newNext) {
        return newEntry(segment, original.getKey(), original.getHash(), newNext);
    }

    <K, V> void copyAccessEntry(ReferenceEntry<K, V> original, ReferenceEntry<K, V> newEntry) {
        newEntry.setAccessTime(original.getAccessTime());
        LocalCache.connectAccessOrder(original.getPreviousInAccessQueue(), newEntry);
        LocalCache.connectAccessOrder(newEntry, original.getNextInAccessQueue());
        LocalCache.nullifyAccessOrder(original);
    }

    <K, V> void copyWriteEntry(ReferenceEntry<K, V> original, ReferenceEntry<K, V> newEntry) {
        newEntry.setWriteTime(original.getWriteTime());
        LocalCache.connectWriteOrder(original.getPreviousInWriteQueue(), newEntry);
        LocalCache.connectWriteOrder(newEntry, original.getNextInWriteQueue());
        LocalCache.nullifyWriteOrder(original);
    }
}
