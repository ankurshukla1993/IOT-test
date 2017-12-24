package com.facebook.drawee.view;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.drawee.interfaces.DraweeHierarchy;
import java.util.ArrayList;

public class MultiDraweeHolder<DH extends DraweeHierarchy> {
    @VisibleForTesting
    ArrayList<DraweeHolder<DH>> mHolders = new ArrayList();
    @VisibleForTesting
    boolean mIsAttached = false;

    public void onAttach() {
        if (!this.mIsAttached) {
            this.mIsAttached = true;
            for (int i = 0; i < this.mHolders.size(); i++) {
                ((DraweeHolder) this.mHolders.get(i)).onAttach();
            }
        }
    }

    public void onDetach() {
        if (this.mIsAttached) {
            this.mIsAttached = false;
            for (int i = 0; i < this.mHolders.size(); i++) {
                ((DraweeHolder) this.mHolders.get(i)).onDetach();
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        for (int i = 0; i < this.mHolders.size(); i++) {
            if (((DraweeHolder) this.mHolders.get(i)).onTouchEvent(event)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        if (this.mIsAttached) {
            for (int i = 0; i < this.mHolders.size(); i++) {
                ((DraweeHolder) this.mHolders.get(i)).onDetach();
            }
        }
        this.mHolders.clear();
    }

    public void add(DraweeHolder<DH> holder) {
        add(this.mHolders.size(), holder);
    }

    public void add(int index, DraweeHolder<DH> holder) {
        Preconditions.checkNotNull(holder);
        Preconditions.checkElementIndex(index, this.mHolders.size() + 1);
        this.mHolders.add(index, holder);
        if (this.mIsAttached) {
            holder.onAttach();
        }
    }

    public void remove(int index) {
        DraweeHolder<DH> holder = (DraweeHolder) this.mHolders.get(index);
        if (this.mIsAttached) {
            holder.onDetach();
        }
        this.mHolders.remove(index);
    }

    public DraweeHolder<DH> get(int index) {
        return (DraweeHolder) this.mHolders.get(index);
    }

    public int size() {
        return this.mHolders.size();
    }

    public void draw(Canvas canvas) {
        for (int i = 0; i < this.mHolders.size(); i++) {
            Drawable drawable = get(i).getTopLevelDrawable();
            if (drawable != null) {
                drawable.draw(canvas);
            }
        }
    }

    public boolean verifyDrawable(Drawable who) {
        for (int i = 0; i < this.mHolders.size(); i++) {
            if (who == get(i).getTopLevelDrawable()) {
                return true;
            }
        }
        return false;
    }
}
