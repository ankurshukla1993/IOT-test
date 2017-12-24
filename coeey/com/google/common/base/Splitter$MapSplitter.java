package com.google.common.base;

import com.google.common.annotations.Beta;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

@Beta
public final class Splitter$MapSplitter {
    private static final String INVALID_ENTRY_MESSAGE = "Chunk [%s] is not a valid entry";
    private final Splitter entrySplitter;
    private final Splitter outerSplitter;

    private Splitter$MapSplitter(Splitter outerSplitter, Splitter entrySplitter) {
        this.outerSplitter = outerSplitter;
        this.entrySplitter = (Splitter) Preconditions.checkNotNull(entrySplitter);
    }

    public Map<String, String> split(CharSequence sequence) {
        Map<String, String> map = new LinkedHashMap();
        for (String entry : this.outerSplitter.split(sequence)) {
            boolean z;
            Iterator<String> entryFields = Splitter.access$000(this.entrySplitter, entry);
            Preconditions.checkArgument(entryFields.hasNext(), INVALID_ENTRY_MESSAGE, entry);
            String key = (String) entryFields.next();
            if (map.containsKey(key)) {
                z = false;
            } else {
                z = true;
            }
            Preconditions.checkArgument(z, "Duplicate key [%s] found.", key);
            Preconditions.checkArgument(entryFields.hasNext(), INVALID_ENTRY_MESSAGE, entry);
            map.put(key, (String) entryFields.next());
            if (entryFields.hasNext()) {
                z = false;
            } else {
                z = true;
            }
            Preconditions.checkArgument(z, INVALID_ENTRY_MESSAGE, entry);
        }
        return Collections.unmodifiableMap(map);
    }
}
