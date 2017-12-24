package com.facebook.react.uimanager;

import com.facebook.react.uimanager.annotations.ReactProp;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

class ViewManagersPropertyCache$StringPropSetter extends ViewManagersPropertyCache$PropSetter {
    public ViewManagersPropertyCache$StringPropSetter(ReactProp prop, Method setter) {
        super(prop, "String", setter);
    }

    @Nullable
    protected Object extractProperty(ReactStylesDiffMap props) {
        return props.getString(this.mPropName);
    }
}
