package com.facebook.react.uimanager;

import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

class ViewManagersPropertyCache$BoxedIntPropSetter extends ViewManagersPropertyCache$PropSetter {
    public ViewManagersPropertyCache$BoxedIntPropSetter(ReactProp prop, Method setter) {
        super(prop, "number", setter);
    }

    public ViewManagersPropertyCache$BoxedIntPropSetter(ReactPropGroup prop, Method setter, int index) {
        super(prop, "number", setter, index);
    }

    @Nullable
    protected Object extractProperty(ReactStylesDiffMap props) {
        if (props.isNull(this.mPropName)) {
            return null;
        }
        return Integer.valueOf(props.getInt(this.mPropName, 0));
    }
}
