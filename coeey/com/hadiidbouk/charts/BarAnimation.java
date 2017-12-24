package com.hadiidbouk.charts;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

public class BarAnimation extends Animation {
    private float from;
    private ProgressBar progressBar;
    private float to;

    public BarAnimation(ProgressBar progressBar, float from, float to) {
        this.progressBar = progressBar;
        this.from = from;
        this.to = to;
    }

    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        this.progressBar.setProgress((int) (this.from + ((this.to - this.from) * interpolatedTime)));
    }
}
