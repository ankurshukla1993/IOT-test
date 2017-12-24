package com.facebook.react.views.text;

import android.graphics.Typeface;
import android.util.SparseArray;

class ReactFontManager$FontFamily {
    private SparseArray<Typeface> mTypefaceSparseArray;

    private ReactFontManager$FontFamily() {
        this.mTypefaceSparseArray = new SparseArray(4);
    }

    public Typeface getTypeface(int style) {
        return (Typeface) this.mTypefaceSparseArray.get(style);
    }

    public void setTypeface(int style, Typeface typeface) {
        this.mTypefaceSparseArray.put(style, typeface);
    }
}
