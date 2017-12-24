package io.realm;

public interface OrderedCollectionChangeSet {

    public static class Range {
        public final int length;
        public final int startIndex;

        public Range(int startIndex, int length) {
            this.startIndex = startIndex;
            this.length = length;
        }
    }

    Range[] getChangeRanges();

    int[] getChanges();

    Range[] getDeletionRanges();

    int[] getDeletions();

    Range[] getInsertionRanges();

    int[] getInsertions();
}
