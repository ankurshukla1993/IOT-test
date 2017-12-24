package com.facebook.fbui.textlayoutbuilder;

import android.support.v4.text.TextDirectionHeuristicCompat;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils.TruncateAt;
import com.facebook.fbui.textlayoutbuilder.proxy.StaticLayoutProxy;
import java.lang.reflect.Field;

class StaticLayoutHelper {
    private static final String SPACE_AND_ELLIPSIS = " …";

    StaticLayoutHelper() {
    }

    private static StaticLayout getStaticLayoutMaybeMaxLines(CharSequence text, int start, int end, TextPaint paint, int width, Alignment alignment, float spacingMult, float spacingAdd, boolean includePadding, TruncateAt ellipsize, int ellipsisWidth, int maxLines, TextDirectionHeuristicCompat textDirection) {
        try {
            return StaticLayoutProxy.create(text, start, end, paint, width, alignment, spacingMult, spacingAdd, includePadding, ellipsize, ellipsisWidth, maxLines, textDirection);
        } catch (LinkageError e) {
            return getStaticLayoutNoMaxLines(text, start, end, paint, width, alignment, spacingMult, spacingAdd, includePadding, ellipsize, ellipsisWidth);
        }
    }

    private static StaticLayout getStaticLayoutNoMaxLines(CharSequence text, int start, int end, TextPaint paint, int width, Alignment alignment, float spacingMult, float spacingAdd, boolean includePadding, TruncateAt ellipsize, int ellipsisWidth) {
        return new StaticLayout(text, start, end, paint, width, alignment, spacingMult, spacingAdd, includePadding, ellipsize, ellipsisWidth);
    }

    public static StaticLayout make(CharSequence text, int start, int end, TextPaint paint, int width, Alignment alignment, float spacingMult, float spacingAdd, boolean includePadding, TruncateAt ellipsize, int ellipsisWidth, int maxLines, TextDirectionHeuristicCompat textDirection) {
        StaticLayout layout = getStaticLayoutMaybeMaxLines(text, start, end, paint, width, alignment, spacingMult, spacingAdd, includePadding, ellipsize, ellipsisWidth, maxLines, textDirection);
        if (maxLines > 0) {
            while (layout.getLineCount() > maxLines) {
                int newEnd = layout.getLineStart(maxLines);
                if (newEnd >= end) {
                    break;
                }
                while (newEnd > start) {
                    if (!Character.isSpace(text.charAt(newEnd - 1))) {
                        break;
                    }
                    newEnd--;
                }
                end = newEnd;
                layout = getStaticLayoutMaybeMaxLines(text, start, end, paint, width, alignment, spacingMult, spacingAdd, includePadding, ellipsize, ellipsisWidth, maxLines, textDirection);
                if (layout.getLineCount() >= maxLines && layout.getEllipsisCount(maxLines - 1) == 0) {
                    CharSequence ellipsizedText = text.subSequence(start, end) + SPACE_AND_ELLIPSIS;
                    layout = getStaticLayoutMaybeMaxLines(ellipsizedText, 0, ellipsizedText.length(), paint, width, alignment, spacingMult, spacingAdd, includePadding, ellipsize, ellipsisWidth, maxLines, textDirection);
                }
            }
        }
        do {
        } while (!fixLayout(layout));
        return layout;
    }

    public static boolean fixLayout(StaticLayout layout) {
        int lineStart = layout.getLineStart(0);
        int i = 0;
        int lineCount = layout.getLineCount();
        while (i < lineCount) {
            int lineEnd = layout.getLineEnd(i);
            if (lineEnd < lineStart) {
                try {
                    Field mLinesField = StaticLayout.class.getDeclaredField("mLines");
                    mLinesField.setAccessible(true);
                    Field mColumnsField = StaticLayout.class.getDeclaredField("mColumns");
                    mColumnsField.setAccessible(true);
                    int[] mLines = (int[]) mLinesField.get(layout);
                    int mColumns = mColumnsField.getInt(layout);
                    for (int j = 0; j < mColumns; j++) {
                        swap(mLines, (mColumns * i) + j, ((mColumns * i) + j) + mColumns);
                    }
                    return false;
                } catch (Exception e) {
                }
            } else {
                lineStart = lineEnd;
                i++;
            }
        }
        return true;
    }

    private static void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }
}
