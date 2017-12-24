package co.ceryle.segmentedbutton;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Build.VERSION;
import android.view.View;
import android.widget.LinearLayout;

class RoundHelper {
    RoundHelper() {
    }

    private static GradientDrawable getGradientDrawable(int dividerColor, int dividerRadius, int dividerSize) {
        GradientDrawable gradient = new GradientDrawable(Orientation.BOTTOM_TOP, new int[]{dividerColor, dividerColor});
        gradient.setShape(0);
        gradient.setCornerRadius((float) dividerRadius);
        gradient.setSize(dividerSize, 0);
        return gradient;
    }

    static void makeRound(View view, int dividerColor, int dividerRadius, int dividerSize) {
        GradientDrawable gradient = getGradientDrawable(dividerColor, dividerRadius, dividerSize);
        if (VERSION.SDK_INT >= 21) {
            view.setBackground(gradient);
        } else {
            view.setBackgroundDrawable(gradient);
        }
    }

    static void makeDividerRound(LinearLayout layout, int dividerColor, int dividerRadius, int dividerSize, Drawable drawable) {
        GradientDrawable gradient = null;
        if (drawable == null) {
            gradient = getGradientDrawable(dividerColor, dividerRadius, dividerSize);
        } else if (drawable instanceof GradientDrawable) {
            gradient = (GradientDrawable) drawable;
            if (dividerSize != 0) {
                gradient.setSize(dividerSize, 0);
            }
            if (dividerRadius != 0) {
                gradient.setCornerRadius((float) dividerRadius);
            }
        } else {
            layout.setDividerDrawable(drawable);
        }
        layout.setDividerDrawable(gradient);
    }
}
