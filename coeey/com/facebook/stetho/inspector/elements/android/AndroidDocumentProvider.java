package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.common.ThreadBound;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.inspector.elements.Descriptor;
import com.facebook.stetho.inspector.elements.DescriptorMap;
import com.facebook.stetho.inspector.elements.DescriptorProvider;
import com.facebook.stetho.inspector.elements.DocumentProvider;
import com.facebook.stetho.inspector.elements.DocumentProviderListener;
import com.facebook.stetho.inspector.elements.NodeDescriptor;
import com.facebook.stetho.inspector.elements.ObjectDescriptor;
import com.facebook.stetho.inspector.helper.ThreadBoundProxy;
import java.util.List;
import javax.annotation.Nullable;

final class AndroidDocumentProvider extends ThreadBoundProxy implements DocumentProvider, AndroidDescriptorHost {
    private static final int INSPECT_HOVER_COLOR = 1077952767;
    private static final int INSPECT_OVERLAY_COLOR = 1090519039;
    private static final long REPORT_CHANGED_INTERVAL_MS = 1000;
    private final Application mApplication;
    private final DescriptorMap mDescriptorMap;
    private final AndroidDocumentRoot mDocumentRoot;
    private final ViewHighlighter mHighlighter;
    private final Rect mHighlightingBoundsRect = new Rect();
    private final Rect mHitRect = new Rect();
    private final InspectModeHandler mInspectModeHandler;
    private boolean mIsReportChangesTimerPosted = false;
    @Nullable
    private DocumentProviderListener mListener;
    private final Runnable mReportChangesTimer = new 1(this);

    public AndroidDocumentProvider(Application application, List<DescriptorProvider> descriptorProviders, ThreadBound enforcer) {
        super(enforcer);
        this.mApplication = (Application) Util.throwIfNull(application);
        this.mDocumentRoot = new AndroidDocumentRoot(application);
        this.mDescriptorMap = new DescriptorMap().beginInit().registerDescriptor(Activity.class, new ActivityDescriptor()).registerDescriptor(AndroidDocumentRoot.class, this.mDocumentRoot).registerDescriptor(Application.class, new ApplicationDescriptor()).registerDescriptor(Dialog.class, new DialogDescriptor()).registerDescriptor(Object.class, new ObjectDescriptor()).registerDescriptor(TextView.class, new TextViewDescriptor()).registerDescriptor(View.class, new ViewDescriptor()).registerDescriptor(ViewGroup.class, new ViewGroupDescriptor()).registerDescriptor(Window.class, new WindowDescriptor());
        DialogFragmentDescriptor.register(this.mDescriptorMap);
        FragmentDescriptor.register(this.mDescriptorMap);
        int size = descriptorProviders.size();
        for (int i = 0; i < size; i++) {
            ((DescriptorProvider) descriptorProviders.get(i)).registerDescriptor(this.mDescriptorMap);
        }
        this.mDescriptorMap.setHost(this).endInit();
        this.mHighlighter = ViewHighlighter.newInstance();
        this.mInspectModeHandler = new InspectModeHandler(this, null);
    }

    public void dispose() {
        verifyThreadAccess();
        this.mHighlighter.clearHighlight();
        this.mInspectModeHandler.disable();
        removeCallbacks(this.mReportChangesTimer);
        this.mIsReportChangesTimerPosted = false;
        this.mListener = null;
    }

    public void setListener(DocumentProviderListener listener) {
        verifyThreadAccess();
        this.mListener = listener;
        if (this.mListener == null && this.mIsReportChangesTimerPosted) {
            this.mIsReportChangesTimerPosted = false;
            removeCallbacks(this.mReportChangesTimer);
        } else if (this.mListener != null && !this.mIsReportChangesTimerPosted) {
            this.mIsReportChangesTimerPosted = true;
            postDelayed(this.mReportChangesTimer, REPORT_CHANGED_INTERVAL_MS);
        }
    }

    public Object getRootElement() {
        verifyThreadAccess();
        return this.mDocumentRoot;
    }

    public NodeDescriptor getNodeDescriptor(Object element) {
        verifyThreadAccess();
        return getDescriptor(element);
    }

    public void highlightElement(Object element, int color) {
        verifyThreadAccess();
        HighlightableDescriptor descriptor = getHighlightableDescriptor(element);
        if (descriptor == null) {
            this.mHighlighter.clearHighlight();
            return;
        }
        this.mHighlightingBoundsRect.setEmpty();
        View highlightingView = descriptor.getViewAndBoundsForHighlighting(element, this.mHighlightingBoundsRect);
        if (highlightingView == null) {
            this.mHighlighter.clearHighlight();
        } else {
            this.mHighlighter.setHighlightedView(highlightingView, this.mHighlightingBoundsRect, color);
        }
    }

    public void hideHighlight() {
        verifyThreadAccess();
        this.mHighlighter.clearHighlight();
    }

    public void setInspectModeEnabled(boolean enabled) {
        verifyThreadAccess();
        if (enabled) {
            this.mInspectModeHandler.enable();
        } else {
            this.mInspectModeHandler.disable();
        }
    }

    public void setAttributesAsText(Object element, String text) {
        verifyThreadAccess();
        Descriptor descriptor = this.mDescriptorMap.get(element.getClass());
        if (descriptor != null) {
            descriptor.setAttributesAsText(element, text);
        }
    }

    public Descriptor getDescriptor(Object element) {
        return element == null ? null : this.mDescriptorMap.get(element.getClass());
    }

    public void onAttributeModified(Object element, String name, String value) {
        if (this.mListener != null) {
            this.mListener.onAttributeModified(element, name, value);
        }
    }

    public void onAttributeRemoved(Object element, String name) {
        if (this.mListener != null) {
            this.mListener.onAttributeRemoved(element, name);
        }
    }

    @Nullable
    public HighlightableDescriptor getHighlightableDescriptor(@Nullable Object element) {
        if (element == null) {
            return null;
        }
        HighlightableDescriptor highlightableDescriptor = null;
        Class<?> theClass = element.getClass();
        Descriptor lastDescriptor = null;
        while (highlightableDescriptor == null && theClass != null) {
            Descriptor descriptor = this.mDescriptorMap.get(theClass);
            if (descriptor == null) {
                return null;
            }
            if (descriptor != lastDescriptor && (descriptor instanceof HighlightableDescriptor)) {
                highlightableDescriptor = (HighlightableDescriptor) descriptor;
            }
            lastDescriptor = descriptor;
            theClass = theClass.getSuperclass();
        }
        return highlightableDescriptor;
    }

    private void getWindows(Accumulator<Window> accumulator) {
        Descriptor appDescriptor = getDescriptor(this.mApplication);
        if (appDescriptor != null) {
            appDescriptor.getChildren(this.mApplication, new 2(this, accumulator));
        }
    }
}
