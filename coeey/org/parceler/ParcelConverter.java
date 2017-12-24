package org.parceler;

import android.os.Parcel;

public interface ParcelConverter<T> extends TypeRangeParcelConverter<T, T> {
    public static final String CONVERT_FROM_PARCEL = "fromParcel";
    public static final String CONVERT_TO_PARCEL = "toParcel";

    public static class EmptyConverter implements ParcelConverter<Object> {
        public void toParcel(Object input, Parcel destinationParcel) {
            throw new ParcelerRuntimeException("Empty Converter should not be used.");
        }

        public Object fromParcel(Parcel parcel) {
            throw new ParcelerRuntimeException("Empty Converter should not be used.");
        }
    }
}
