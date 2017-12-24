package com.google.common.io;

import com.google.common.base.Preconditions;
import java.io.IOException;
import java.io.Reader;

final class CharSource$ConcatenatedCharSource extends CharSource {
    private final Iterable<? extends CharSource> sources;

    CharSource$ConcatenatedCharSource(Iterable<? extends CharSource> sources) {
        this.sources = (Iterable) Preconditions.checkNotNull(sources);
    }

    public Reader openStream() throws IOException {
        return new MultiReader(this.sources.iterator());
    }

    public boolean isEmpty() throws IOException {
        for (CharSource source : this.sources) {
            if (!source.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.sources));
        return new StringBuilder(valueOf.length() + 19).append("CharSource.concat(").append(valueOf).append(")").toString();
    }
}
