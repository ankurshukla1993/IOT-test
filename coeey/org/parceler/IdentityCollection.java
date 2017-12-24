package org.parceler;

import java.util.ArrayList;
import java.util.List;

public final class IdentityCollection {
    private static final Object RESERVATION = new Object();
    private final List<Object> values = new ArrayList();

    public IdentityCollection() {
        put(null);
    }

    public boolean containsKey(int id) {
        return id < this.values.size();
    }

    public int reserve() {
        return put(RESERVATION);
    }

    public boolean isReserved(int id) {
        return this.values.get(id) == RESERVATION;
    }

    public void put(int id, Object input) {
        this.values.remove(id);
        this.values.add(id, input);
    }

    public int put(Object input) {
        this.values.add(input);
        return this.values.size() - 1;
    }

    public <T> T get(int id) {
        return this.values.get(id);
    }

    public int getKey(Object input) {
        for (int i = 0; i < this.values.size(); i++) {
            if (this.values.get(i) == input) {
                return i;
            }
        }
        return -1;
    }
}
