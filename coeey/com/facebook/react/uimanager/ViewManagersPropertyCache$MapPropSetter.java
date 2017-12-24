package com.facebook.react.uimanager;

import com.facebook.react.uimanager.annotations.ReactProp;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

class ViewManagersPropertyCache$MapPropSetter extends ViewManagersPropertyCache$PropSetter {
    public ViewManagersPropertyCache$MapPropSetter(ReactProp prop, Method setter) {
        super(prop, "Map", setter);
    }

    @Nullable
    protected Object extractProperty(ReactStylesDiffMap props) {
        return props.getMap(this.mPropName);
    }
}
