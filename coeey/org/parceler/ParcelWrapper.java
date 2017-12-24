package org.parceler;

public interface ParcelWrapper<T> {
    public static final String GET_PARCEL = "getParcel";

    T getParcel();
}
