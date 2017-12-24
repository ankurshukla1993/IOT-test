package com.github.mikephil.charting.utils;

import android.content.res.Resources;
import android.graphics.Color;
import com.facebook.imageutils.JfifUtil;
import java.util.ArrayList;
import java.util.List;
import tw.com.prolific.driver.pl2303.PL2303Driver;

public class ColorTemplate {
    public static final int[] COLORFUL_COLORS = new int[]{Color.rgb(193, 37, 82), Color.rgb(255, 102, 0), Color.rgb(245, 199, 0), Color.rgb(106, PL2303Driver.BAUD150, 31), Color.rgb(179, 100, 53)};
    public static final int COLOR_NONE = -1;
    public static final int COLOR_SKIP = -2;
    public static final int[] JOYFUL_COLORS = new int[]{Color.rgb(JfifUtil.MARKER_EOI, 80, 138), Color.rgb(254, 149, 7), Color.rgb(254, 247, 120), Color.rgb(106, 167, 134), Color.rgb(53, 194, 209)};
    public static final int[] LIBERTY_COLORS = new int[]{Color.rgb(207, 248, 246), Color.rgb(148, 212, 212), Color.rgb(136, 180, 187), Color.rgb(118, 174, 175), Color.rgb(42, 109, 130)};
    public static final int[] PASTEL_COLORS = new int[]{Color.rgb(64, 89, 128), Color.rgb(149, 165, 124), Color.rgb(JfifUtil.MARKER_EOI, 184, 162), Color.rgb(191, 134, 134), Color.rgb(179, 48, 80)};
    public static final int[] VORDIPLOM_COLORS = new int[]{Color.rgb(JfifUtil.MARKER_SOFn, 255, 140), Color.rgb(255, 247, 140), Color.rgb(255, JfifUtil.MARKER_RST0, 140), Color.rgb(140, 234, 255), Color.rgb(255, 140, 157)};

    public static int getHoloBlue() {
        return Color.rgb(51, 181, 229);
    }

    public static List<Integer> createColors(Resources r, int[] colors) {
        List<Integer> result = new ArrayList();
        for (int i : colors) {
            result.add(Integer.valueOf(r.getColor(i)));
        }
        return result;
    }

    public static List<Integer> createColors(int[] colors) {
        List<Integer> result = new ArrayList();
        for (int i : colors) {
            result.add(Integer.valueOf(i));
        }
        return result;
    }
}
