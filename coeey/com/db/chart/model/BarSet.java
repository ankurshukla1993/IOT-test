package com.db.chart.model;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Size;
import com.db.chart.util.Preconditions;
import java.util.Iterator;

public class BarSet extends ChartSet {
    private static final String TAG = "chart.model.BarSet";

    public BarSet(@NonNull String[] labels, @NonNull float[] values) {
        if (labels.length != values.length) {
            throw new IllegalArgumentException("Arrays size doesn't match.");
        }
        Preconditions.checkNotNull(labels);
        Preconditions.checkNotNull(values);
        int nEntries = labels.length;
        for (int i = 0; i < nEntries; i++) {
            addBar(labels[i], values[i]);
        }
    }

    public void addBar(String label, float value) {
        addBar(new Bar(label, value));
    }

    public void addBar(@NonNull Bar bar) {
        addEntry((ChartEntry) Preconditions.checkNotNull(bar));
    }

    public int getColor() {
        return getEntry(0).getColor();
    }

    public BarSet setColor(@ColorInt int color) {
        Iterator it = getEntries().iterator();
        while (it.hasNext()) {
            ((ChartEntry) it.next()).setColor(color);
        }
        return this;
    }

    public BarSet setGradientColor(@Size(min = 1) @NonNull int[] colors, float[] positions) {
        if (colors.length == 0) {
            throw new IllegalArgumentException("Colors argument can't be null or empty.");
        }
        Preconditions.checkNotNull(colors);
        Iterator it = getEntries().iterator();
        while (it.hasNext()) {
            ((Bar) ((ChartEntry) it.next())).setGradientColor(colors, positions);
        }
        return this;
    }
}
