package com.facebook.react.uimanager;

import com.facebook.react.uimanager.annotations.ReactProp;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

class ViewManagersPropertyCache$ArrayPropSetter extends ViewManagersPropertyCache$PropSetter {
    public ViewManagersPropertyCache$ArrayPropSetter(ReactProp prop, Method setter) {
        super(prop, "Array", setter);
    }

    @Nullable
    protected Object extractProperty(ReactStylesDiffMap props) {
        return props.getArray(this.mPropName);
    }
}
