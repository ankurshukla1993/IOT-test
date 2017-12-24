package com.thefinestartist.utils.etc;

import android.util.SparseArray;
import java.util.ArrayList;

public class SparseArrayUtil {
    private SparseArrayUtil() {
    }

    public static <C> ArrayList<C> asArrayList(SparseArray<C> sparseArray) {
        if (sparseArray == null) {
            return new ArrayList();
        }
        ArrayList<C> arrayList = new ArrayList(sparseArray.size());
        for (int i = 0; i < sparseArray.size(); i++) {
            arrayList.add(sparseArray.valueAt(i));
        }
        return arrayList;
    }
}
