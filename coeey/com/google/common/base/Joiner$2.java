package com.google.common.base;

import com.google.common.base.Joiner.MapJoiner;
import java.io.IOException;
import java.util.Iterator;

class Joiner$2 extends Joiner {
    final /* synthetic */ Joiner this$0;

    Joiner$2(Joiner joiner, Joiner x0) {
        this.this$0 = joiner;
        super(x0, null);
    }

    public <A extends Appendable> A appendTo(A appendable, Iterator<?> parts) throws IOException {
        Preconditions.checkNotNull(appendable, "appendable");
        Preconditions.checkNotNull(parts, "parts");
        while (parts.hasNext()) {
            Object part = parts.next();
            if (part != null) {
                appendable.append(this.this$0.toString(part));
                break;
            }
        }
        while (parts.hasNext()) {
            part = parts.next();
            if (part != null) {
                appendable.append(Joiner.access$100(this.this$0));
                appendable.append(this.this$0.toString(part));
            }
        }
        return appendable;
    }

    public Joiner useForNull(String nullText) {
        throw new UnsupportedOperationException("already specified skipNulls");
    }

    public MapJoiner withKeyValueSeparator(String kvs) {
        throw new UnsupportedOperationException("can't use .skipNulls() with maps");
    }
}
