package com.facebook.drawee.drawable;

import android.graphics.drawable.Drawable;

class ArrayDrawable$1 implements DrawableParent {
    final /* synthetic */ ArrayDrawable this$0;
    final /* synthetic */ int val$index;

    ArrayDrawable$1(ArrayDrawable this$0, int i) {
        this.this$0 = this$0;
        this.val$index = i;
    }

    public Drawable setDrawable(Drawable newDrawable) {
        return this.this$0.setDrawable(this.val$index, newDrawable);
    }

    public Drawable getDrawable() {
        return this.this$0.getDrawable(this.val$index);
    }
}
