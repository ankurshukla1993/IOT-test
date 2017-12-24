package com.facebook.react.uimanager;

import com.facebook.react.uimanager.annotations.ReactProp;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

class ViewManagersPropertyCache$BoxedBooleanPropSetter extends ViewManagersPropertyCache$PropSetter {
    public ViewManagersPropertyCache$BoxedBooleanPropSetter(ReactProp prop, Method setter) {
        super(prop, "boolean", setter);
    }

    @Nullable
    protected Object extractProperty(ReactStylesDiffMap props) {
        if (props.isNull(this.mPropName)) {
            return null;
        }
        return props.getBoolean(this.mPropName, false) ? Boolean.TRUE : Boolean.FALSE;
    }
}
