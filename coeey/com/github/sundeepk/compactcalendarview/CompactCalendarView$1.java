package com.github.sundeepk.compactcalendarview;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

class CompactCalendarView$1 extends SimpleOnGestureListener {
    final /* synthetic */ CompactCalendarView this$0;

    CompactCalendarView$1(CompactCalendarView this$0) {
        this.this$0 = this$0;
    }

    public void onLongPress(MotionEvent e) {
    }

    public boolean onSingleTapUp(MotionEvent e) {
        CompactCalendarView.access$000(this.this$0).onSingleTapUp(e);
        this.this$0.invalidate();
        return super.onSingleTapUp(e);
    }

    public boolean onDown(MotionEvent e) {
        return true;
    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return true;
    }

    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (!CompactCalendarView.access$100(this.this$0) || Math.abs(distanceX) <= 0.0f) {
            return false;
        }
        this.this$0.getParent().requestDisallowInterceptTouchEvent(true);
        CompactCalendarView.access$000(this.this$0).onScroll(e1, e2, distanceX, distanceY);
        this.this$0.invalidate();
        return true;
    }
}
