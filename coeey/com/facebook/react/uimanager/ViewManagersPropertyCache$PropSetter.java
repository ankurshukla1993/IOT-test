package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.lang.reflect.Method;
import java.util.Arrays;
import javax.annotation.Nullable;

abstract class ViewManagersPropertyCache$PropSetter {
    private static final Object[] SHADOW_ARGS = new Object[1];
    private static final Object[] SHADOW_GROUP_ARGS = new Object[2];
    private static final Object[] VIEW_MGR_ARGS = new Object[2];
    private static final Object[] VIEW_MGR_GROUP_ARGS = new Object[3];
    @Nullable
    protected final Integer mIndex;
    protected final String mPropName;
    protected final String mPropType;
    protected final Method mSetter;

    @Nullable
    protected abstract Object extractProperty(ReactStylesDiffMap reactStylesDiffMap);

    private ViewManagersPropertyCache$PropSetter(ReactProp prop, String defaultType, Method setter) {
        this.mPropName = prop.name();
        if (!"__default_type__".equals(prop.customType())) {
            defaultType = prop.customType();
        }
        this.mPropType = defaultType;
        this.mSetter = setter;
        this.mIndex = null;
    }

    private ViewManagersPropertyCache$PropSetter(ReactPropGroup prop, String defaultType, Method setter, int index) {
        this.mPropName = prop.names()[index];
        if (!"__default_type__".equals(prop.customType())) {
            defaultType = prop.customType();
        }
        this.mPropType = defaultType;
        this.mSetter = setter;
        this.mIndex = Integer.valueOf(index);
    }

    public String getPropName() {
        return this.mPropName;
    }

    public String getPropType() {
        return this.mPropType;
    }

    public void updateViewProp(ViewManager viewManager, View viewToUpdate, ReactStylesDiffMap props) {
        try {
            if (this.mIndex == null) {
                VIEW_MGR_ARGS[0] = viewToUpdate;
                VIEW_MGR_ARGS[1] = extractProperty(props);
                this.mSetter.invoke(viewManager, VIEW_MGR_ARGS);
                Arrays.fill(VIEW_MGR_ARGS, null);
                return;
            }
            VIEW_MGR_GROUP_ARGS[0] = viewToUpdate;
            VIEW_MGR_GROUP_ARGS[1] = this.mIndex;
            VIEW_MGR_GROUP_ARGS[2] = extractProperty(props);
            this.mSetter.invoke(viewManager, VIEW_MGR_GROUP_ARGS);
            Arrays.fill(VIEW_MGR_GROUP_ARGS, null);
        } catch (Throwable t) {
            FLog.e(ViewManager.class, "Error while updating prop " + this.mPropName, t);
            JSApplicationIllegalArgumentException jSApplicationIllegalArgumentException = new JSApplicationIllegalArgumentException("Error while updating property '" + this.mPropName + "' of a view managed by: " + viewManager.getName(), t);
        }
    }

    public void updateShadowNodeProp(ReactShadowNode nodeToUpdate, ReactStylesDiffMap props) {
        try {
            if (this.mIndex == null) {
                SHADOW_ARGS[0] = extractProperty(props);
                this.mSetter.invoke(nodeToUpdate, SHADOW_ARGS);
                Arrays.fill(SHADOW_ARGS, null);
                return;
            }
            SHADOW_GROUP_ARGS[0] = this.mIndex;
            SHADOW_GROUP_ARGS[1] = extractProperty(props);
            this.mSetter.invoke(nodeToUpdate, SHADOW_GROUP_ARGS);
            Arrays.fill(SHADOW_GROUP_ARGS, null);
        } catch (Throwable t) {
            FLog.e(ViewManager.class, "Error while updating prop " + this.mPropName, t);
            JSApplicationIllegalArgumentException jSApplicationIllegalArgumentException = new JSApplicationIllegalArgumentException("Error while updating property '" + this.mPropName + "' in shadow node of type: " + nodeToUpdate.getViewClass(), t);
        }
    }
}
