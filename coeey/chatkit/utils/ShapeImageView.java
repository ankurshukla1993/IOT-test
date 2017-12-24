package chatkit.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.widget.ImageView;

public class ShapeImageView extends ImageView {
    private Path path;

    public ShapeImageView(Context context) {
        super(context);
    }

    public ShapeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.path = new Path();
        float halfWidth = ((float) w) / 2.0f;
        float firstParam = ((float) w) * 0.1f;
        float secondParam = ((float) w) * 0.8875f;
        this.path.moveTo(halfWidth, (float) w);
        this.path.cubicTo(firstParam, (float) w, 0.0f, secondParam, 0.0f, halfWidth);
        this.path.cubicTo(0.0f, firstParam, firstParam, 0.0f, halfWidth, 0.0f);
        this.path.cubicTo(secondParam, 0.0f, (float) w, firstParam, (float) w, halfWidth);
        this.path.cubicTo((float) w, secondParam, secondParam, (float) w, halfWidth, (float) w);
        this.path.close();
    }

    protected void onDraw(Canvas canvas) {
        if (canvas != null) {
            canvas.clipPath(this.path);
            super.onDraw(canvas);
        }
    }
}
