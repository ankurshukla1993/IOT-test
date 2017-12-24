package de.hdodenhof.circleimageview;

import android.graphics.Outline;
import android.graphics.Rect;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewOutlineProvider;

@RequiresApi(api = 21)
class CircleImageView$OutlineProvider extends ViewOutlineProvider {
    final /* synthetic */ CircleImageView this$0;

    private CircleImageView$OutlineProvider(CircleImageView circleImageView) {
        this.this$0 = circleImageView;
    }

    public void getOutline(View view, Outline outline) {
        Rect bounds = new Rect();
        CircleImageView.access$100(this.this$0).roundOut(bounds);
        outline.setRoundRect(bounds, ((float) bounds.width()) / 2.0f);
    }
}
