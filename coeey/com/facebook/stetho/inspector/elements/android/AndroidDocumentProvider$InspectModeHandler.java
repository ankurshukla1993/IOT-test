package com.facebook.stetho.inspector.elements.android;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.Predicate;
import java.util.ArrayList;
import java.util.List;

final class AndroidDocumentProvider$InspectModeHandler {
    private List<View> mOverlays;
    private final Predicate<View> mViewSelector;
    final /* synthetic */ AndroidDocumentProvider this$0;

    class C14411 implements Predicate<View> {
        C14411() {
        }

        public boolean apply(View view) {
            return !(view instanceof DocumentHiddenView);
        }
    }

    class C14422 implements Accumulator<Window> {
        C14422() {
        }

        public void store(Window object) {
            if (object.peekDecorView() instanceof ViewGroup) {
                ViewGroup decorView = (ViewGroup) object.peekDecorView();
                OverlayView overlayView = new OverlayView(AndroidDocumentProvider.access$300(AndroidDocumentProvider$InspectModeHandler.this.this$0));
                LayoutParams layoutParams = new LayoutParams();
                layoutParams.width = -1;
                layoutParams.height = -1;
                decorView.addView(overlayView, layoutParams);
                decorView.bringChildToFront(overlayView);
                AndroidDocumentProvider$InspectModeHandler.this.mOverlays.add(overlayView);
            }
        }
    }

    private final class OverlayView extends DocumentHiddenView {
        public OverlayView(Context context) {
            super(context);
        }

        protected void onDraw(Canvas canvas) {
            canvas.drawColor(1090519039);
            super.onDraw(canvas);
        }

        public boolean onTouchEvent(MotionEvent event) {
            int x = (int) event.getX();
            int y = (int) event.getY();
            Object parent = getParent();
            while (true) {
                HighlightableDescriptor descriptor = AndroidDocumentProvider$InspectModeHandler.this.this$0.getHighlightableDescriptor(parent);
                if (descriptor != null) {
                    AndroidDocumentProvider.access$600(AndroidDocumentProvider$InspectModeHandler.this.this$0).setEmpty();
                    Object element = descriptor.getElementToHighlightAtPosition(parent, x, y, AndroidDocumentProvider.access$600(AndroidDocumentProvider$InspectModeHandler.this.this$0));
                    x -= AndroidDocumentProvider.access$600(AndroidDocumentProvider$InspectModeHandler.this.this$0).left;
                    y -= AndroidDocumentProvider.access$600(AndroidDocumentProvider$InspectModeHandler.this.this$0).top;
                    if (element == parent) {
                        break;
                    }
                    parent = element;
                } else {
                    break;
                }
            }
            if (parent != null) {
                descriptor = AndroidDocumentProvider$InspectModeHandler.this.this$0.getHighlightableDescriptor(parent);
                if (descriptor != null) {
                    View viewToHighlight = descriptor.getViewAndBoundsForHighlighting(parent, AndroidDocumentProvider.access$700(AndroidDocumentProvider$InspectModeHandler.this.this$0));
                    if (!(event.getAction() == 3 || viewToHighlight == null)) {
                        AndroidDocumentProvider.access$800(AndroidDocumentProvider$InspectModeHandler.this.this$0).setHighlightedView(viewToHighlight, AndroidDocumentProvider.access$700(AndroidDocumentProvider$InspectModeHandler.this.this$0), 1077952767);
                        if (event.getAction() == 1 && AndroidDocumentProvider.access$100(AndroidDocumentProvider$InspectModeHandler.this.this$0) != null) {
                            AndroidDocumentProvider.access$100(AndroidDocumentProvider$InspectModeHandler.this.this$0).onInspectRequested(parent);
                        }
                    }
                }
            }
            return true;
        }
    }

    private AndroidDocumentProvider$InspectModeHandler(AndroidDocumentProvider androidDocumentProvider) {
        this.this$0 = androidDocumentProvider;
        this.mViewSelector = new C14411();
    }

    public void enable() {
        this.this$0.verifyThreadAccess();
        if (this.mOverlays != null) {
            disable();
        }
        this.mOverlays = new ArrayList();
        AndroidDocumentProvider.access$500(this.this$0, new C14422());
    }

    public void disable() {
        this.this$0.verifyThreadAccess();
        if (this.mOverlays != null) {
            for (int i = 0; i < this.mOverlays.size(); i++) {
                View overlayView = (View) this.mOverlays.get(i);
                ((ViewGroup) overlayView.getParent()).removeView(overlayView);
            }
            this.mOverlays = null;
        }
    }
}
