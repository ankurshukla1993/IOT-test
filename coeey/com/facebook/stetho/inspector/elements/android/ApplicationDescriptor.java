package com.facebook.stetho.inspector.elements.android;

import android.app.Activity;
import android.app.Application;
import com.facebook.stetho.common.Accumulator;
import com.facebook.stetho.inspector.elements.AbstractChainedDescriptor;
import com.facebook.stetho.inspector.elements.NodeType;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

final class ApplicationDescriptor extends AbstractChainedDescriptor<Application> {
    private final ActivityTracker mActivityTracker = ActivityTracker.get();
    private final Map<Application, ElementContext> mElementToContextMap = Collections.synchronizedMap(new IdentityHashMap());

    private class ElementContext {
        private Application mElement;
        private final ActivityTracker$Listener mListener = new C14431();

        class C14431 implements ActivityTracker$Listener {
            C14431() {
            }

            public void onActivityAdded(Activity activity) {
            }

            public void onActivityRemoved(Activity activity) {
            }
        }

        public void hook(Application element) {
            this.mElement = element;
            ApplicationDescriptor.this.mActivityTracker.registerListener(this.mListener);
        }

        public void unhook() {
            ApplicationDescriptor.this.mActivityTracker.unregisterListener(this.mListener);
            this.mElement = null;
        }

        public List<WeakReference<Activity>> getActivitiesList() {
            return ApplicationDescriptor.this.mActivityTracker.getActivitiesView();
        }
    }

    ApplicationDescriptor() {
    }

    private ElementContext getContext(Application element) {
        return (ElementContext) this.mElementToContextMap.get(element);
    }

    protected void onHook(Application element) {
        ElementContext context = new ElementContext();
        context.hook(element);
        this.mElementToContextMap.put(element, context);
    }

    protected void onUnhook(Application element) {
        ((ElementContext) this.mElementToContextMap.remove(element)).unhook();
    }

    protected NodeType onGetNodeType(Application element) {
        return NodeType.ELEMENT_NODE;
    }

    protected void onGetChildren(Application element, Accumulator<Object> children) {
        List<WeakReference<Activity>> activities = getContext(element).getActivitiesList();
        for (int i = activities.size() - 1; i >= 0; i--) {
            Activity activity = (Activity) ((WeakReference) activities.get(i)).get();
            if (activity != null) {
                children.store(activity);
            }
        }
    }
}
