package co.ceryle.segmentedbutton;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.widget.FrameLayout;

class RoundedCornerLayout extends FrameLayout {
    private static final float CORNER_RADIUS = 6.0f;
    private float cornerRadius;

    @TargetApi(21)
    private class ButtonOutlineProvider extends ViewOutlineProvider {
        private ButtonOutlineProvider() {
        }

        public void getOutline(View view, Outline outline) {
            outline.setRoundRect(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight(), RoundedCornerLayout.this.cornerRadius);
        }
    }

    public RoundedCornerLayout(Context context) {
        super(context);
        init(context, null, 0);
    }

    public RoundedCornerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs, 0);
    }

    public RoundedCornerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        this.cornerRadius = TypedValue.applyDimension(1, CORNER_RADIUS, context.getResources().getDisplayMetrics());
        setLayerType(1, null);
        if (VERSION.SDK_INT >= 21) {
            setOutlineProvider(new ButtonOutlineProvider());
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        int count = canvas.save();
        Path path = new Path();
        path.addRoundRect(new RectF(0.0f, 0.0f, (float) canvas.getWidth(), (float) canvas.getHeight()), this.cornerRadius, this.cornerRadius, Direction.CW);
        canvas.clipPath(path, Op.REPLACE);
        canvas.clipPath(path);
        super.dispatchDraw(canvas);
        canvas.restoreToCount(count);
    }

    protected void setRadius(float radius) {
        this.cornerRadius = radius;
    }
}
