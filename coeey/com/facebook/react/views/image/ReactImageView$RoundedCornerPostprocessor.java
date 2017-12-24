package com.facebook.react.views.image;

import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.react.uimanager.FloatUtil;

class ReactImageView$RoundedCornerPostprocessor extends BasePostprocessor {
    final /* synthetic */ ReactImageView this$0;

    private ReactImageView$RoundedCornerPostprocessor(ReactImageView reactImageView) {
        this.this$0 = reactImageView;
    }

    void getRadii(Bitmap source, float[] computedCornerRadii, float[] mappedRadii) {
        ReactImageView.access$100(this.this$0).getTransform(ReactImageView.access$000(), new Rect(0, 0, source.getWidth(), source.getHeight()), source.getWidth(), source.getHeight(), 0.0f, 0.0f);
        ReactImageView.access$000().invert(ReactImageView.access$200());
        mappedRadii[0] = ReactImageView.access$200().mapRadius(computedCornerRadii[0]);
        mappedRadii[1] = mappedRadii[0];
        mappedRadii[2] = ReactImageView.access$200().mapRadius(computedCornerRadii[1]);
        mappedRadii[3] = mappedRadii[2];
        mappedRadii[4] = ReactImageView.access$200().mapRadius(computedCornerRadii[2]);
        mappedRadii[5] = mappedRadii[4];
        mappedRadii[6] = ReactImageView.access$200().mapRadius(computedCornerRadii[3]);
        mappedRadii[7] = mappedRadii[6];
    }

    public void process(Bitmap output, Bitmap source) {
        ReactImageView.access$400(this.this$0, ReactImageView.access$300());
        output.setHasAlpha(true);
        if (FloatUtil.floatsEqual(ReactImageView.access$300()[0], 0.0f) && FloatUtil.floatsEqual(ReactImageView.access$300()[1], 0.0f) && FloatUtil.floatsEqual(ReactImageView.access$300()[2], 0.0f) && FloatUtil.floatsEqual(ReactImageView.access$300()[3], 0.0f)) {
            super.process(output, source);
            return;
        }
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(new BitmapShader(source, TileMode.CLAMP, TileMode.CLAMP));
        Canvas canvas = new Canvas(output);
        float[] radii = new float[8];
        getRadii(source, ReactImageView.access$300(), radii);
        Path pathForBorderRadius = new Path();
        pathForBorderRadius.addRoundRect(new RectF(0.0f, 0.0f, (float) source.getWidth(), (float) source.getHeight()), radii, Direction.CW);
        canvas.drawPath(pathForBorderRadius, paint);
    }
}
