package com.facebook.fbui.textlayoutbuilder.proxy;

import android.support.v4.text.TextDirectionHeuristicCompat;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.TextUtils.TruncateAt;

public class StaticLayoutProxy {
    public static StaticLayout create(CharSequence text, int start, int end, TextPaint paint, int width, Alignment alignment, float spacingMult, float spacingAdd, boolean includePadding, TruncateAt ellipsize, int ellipsisWidth, int maxLines, TextDirectionHeuristicCompat textDirection) {
        return new StaticLayout(text, start, end, paint, width, alignment, fromTextDirectionHeuristicCompat(textDirection), spacingMult, spacingAdd, includePadding, ellipsize, ellipsisWidth, maxLines);
    }

    private static TextDirectionHeuristic fromTextDirectionHeuristicCompat(TextDirectionHeuristicCompat textDirection) {
        if (textDirection == TextDirectionHeuristicsCompat.LTR) {
            return TextDirectionHeuristics.LTR;
        }
        if (textDirection == TextDirectionHeuristicsCompat.RTL) {
            return TextDirectionHeuristics.RTL;
        }
        if (textDirection == TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR) {
            return TextDirectionHeuristics.FIRSTSTRONG_LTR;
        }
        if (textDirection == TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL) {
            return TextDirectionHeuristics.FIRSTSTRONG_RTL;
        }
        if (textDirection == TextDirectionHeuristicsCompat.ANYRTL_LTR) {
            return TextDirectionHeuristics.ANYRTL_LTR;
        }
        if (textDirection == TextDirectionHeuristicsCompat.LOCALE) {
            return TextDirectionHeuristics.LOCALE;
        }
        return TextDirectionHeuristics.FIRSTSTRONG_LTR;
    }
}
