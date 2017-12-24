package com.appeaser.sublimepickerlibrary.utilities;

import android.content.res.ColorStateList;

public class TextColorHelper {
    public static ColorStateList resolveMaterialHeaderTextColor() {
        states = new int[2][];
        states[0] = new int[]{16843518};
        states[1] = new int[0];
        return new ColorStateList(states, new int[]{SUtils.COLOR_TEXT_PRIMARY, SUtils.COLOR_TEXT_SECONDARY});
    }
}
