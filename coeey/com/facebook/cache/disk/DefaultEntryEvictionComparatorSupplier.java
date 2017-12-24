package com.facebook.cache.disk;

import com.facebook.cache.disk.DiskStorage.Entry;

public class DefaultEntryEvictionComparatorSupplier implements EntryEvictionComparatorSupplier {

    class C11171 implements EntryEvictionComparator {
        C11171() {
        }

        public int compare(Entry e1, Entry e2) {
            long time1 = e1.getTimestamp();
            long time2 = e2.getTimestamp();
            if (time1 < time2) {
                return -1;
            }
            return time2 == time1 ? 0 : 1;
        }
    }

    public EntryEvictionComparator get() {
        return new C11171();
    }
}
