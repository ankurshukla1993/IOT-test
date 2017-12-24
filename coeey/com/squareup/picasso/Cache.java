package com.squareup.picasso;

import android.graphics.Bitmap;

public interface Cache {
    public static final Cache NONE = new C23151();

    static class C23151 implements Cache {
        C23151() {
        }

        public Bitmap get(String key) {
            return null;
        }

        public void set(String key, Bitmap bitmap) {
        }

        public int size() {
            return 0;
        }

        public int maxSize() {
            return 0;
        }

        public void clear() {
        }

        public void clearKeyUri(String keyPrefix) {
        }
    }

    void clear();

    void clearKeyUri(String str);

    Bitmap get(String str);

    int maxSize();

    void set(String str, Bitmap bitmap);

    int size();
}
