package com.facebook.yoga;

import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public interface YogaMeasureFunction {
    @DoNotStrip
    long measure(YogaNodeAPI yogaNodeAPI, float f, YogaMeasureMode yogaMeasureMode, float f2, YogaMeasureMode yogaMeasureMode2);
}
