package com.facebook.cache.disk;

import com.facebook.cache.disk.DiskStorage.Entry;
import com.facebook.common.internal.VisibleForTesting;

public class ScoreBasedEvictionComparatorSupplier implements EntryEvictionComparatorSupplier {
    private final float mAgeWeight;
    private final float mSizeWeight;

    class C11191 implements EntryEvictionComparator {
        long now = System.currentTimeMillis();

        C11191() {
        }

        public int compare(Entry lhs, Entry rhs) {
            float score1 = ScoreBasedEvictionComparatorSupplier.this.calculateScore(lhs, this.now);
            float score2 = ScoreBasedEvictionComparatorSupplier.this.calculateScore(rhs, this.now);
            if (score1 < score2) {
                return 1;
            }
            return score2 == score1 ? 0 : -1;
        }
    }

    public ScoreBasedEvictionComparatorSupplier(float ageWeight, float sizeWeight) {
        this.mAgeWeight = ageWeight;
        this.mSizeWeight = sizeWeight;
    }

    public EntryEvictionComparator get() {
        return new C11191();
    }

    @VisibleForTesting
    float calculateScore(Entry entry, long now) {
        return (this.mAgeWeight * ((float) (now - entry.getTimestamp()))) + (this.mSizeWeight * ((float) entry.getSize()));
    }
}
