package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.touch.JSResponderHandler;
import com.facebook.react.touch.ReactInterceptingViewGroup;
import com.facebook.react.uimanager.annotations.ReactPropertyHolder;
import java.util.Map;
import javax.annotation.Nullable;

@ReactPropertyHolder
public abstract class ViewManager<T extends View, C extends ReactShadowNode> extends BaseJavaModule {
    public abstract C createShadowNodeInstance();

    protected abstract T createViewInstance(ThemedReactContext themedReactContext);

    public abstract String getName();

    public abstract Class<? extends C> getShadowNodeClass();

    public abstract void updateExtraData(T t, Object obj);

    public final void updateProperties(T viewToUpdate, ReactStylesDiffMap props) {
        ViewManagerPropertyUpdater.updateProps(this, viewToUpdate, props);
        onAfterUpdateTransaction(viewToUpdate);
    }

    public final T createView(ThemedReactContext reactContext, JSResponderHandler jsResponderHandler) {
        T view = createViewInstance(reactContext);
        addEventEmitters(reactContext, view);
        if (view instanceof ReactInterceptingViewGroup) {
            ((ReactInterceptingViewGroup) view).setOnInterceptTouchEventListener(jsResponderHandler);
        }
        return view;
    }

    public void onDropViewInstance(T t) {
    }

    protected void addEventEmitters(ThemedReactContext reactContext, T t) {
    }

    protected void onAfterUpdateTransaction(T t) {
    }

    public void receiveCommand(T t, int commandId, @Nullable ReadableArray args) {
    }

    @Nullable
    public Map<String, Integer> getCommandsMap() {
        return null;
    }

    @Nullable
    public Map<String, Object> getExportedCustomBubblingEventTypeConstants() {
        return null;
    }

    @Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return null;
    }

    @Nullable
    public Map<String, Object> getExportedViewConstants() {
        return null;
    }

    public Map<String, String> getNativeProps() {
        return ViewManagerPropertyUpdater.getNativeProps(getClass(), getShadowNodeClass());
    }
}
