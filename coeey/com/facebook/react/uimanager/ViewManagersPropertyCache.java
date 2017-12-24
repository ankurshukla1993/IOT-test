package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

class ViewManagersPropertyCache {
    private static final Map<Class, Map<String, PropSetter>> CLASS_PROPS_CACHE = new HashMap();
    private static final Map<String, PropSetter> EMPTY_PROPS_MAP = new HashMap();

    private static class BooleanPropSetter extends PropSetter {
        private final boolean mDefaultValue;

        public BooleanPropSetter(ReactProp prop, Method setter, boolean defaultValue) {
            super(prop, "boolean", setter, null);
            this.mDefaultValue = defaultValue;
        }

        protected Object extractProperty(ReactStylesDiffMap props) {
            return props.getBoolean(this.mPropName, this.mDefaultValue) ? Boolean.TRUE : Boolean.FALSE;
        }
    }

    private static class DoublePropSetter extends PropSetter {
        private final double mDefaultValue;

        public DoublePropSetter(ReactProp prop, Method setter, double defaultValue) {
            super(prop, "number", setter, null);
            this.mDefaultValue = defaultValue;
        }

        public DoublePropSetter(ReactPropGroup prop, Method setter, int index, double defaultValue) {
            super(prop, "number", setter, index, null);
            this.mDefaultValue = defaultValue;
        }

        protected Object extractProperty(ReactStylesDiffMap props) {
            return Double.valueOf(props.getDouble(this.mPropName, this.mDefaultValue));
        }
    }

    private static class DynamicPropSetter extends PropSetter {
        public DynamicPropSetter(ReactProp prop, Method setter) {
            super(prop, "mixed", setter, null);
        }

        public DynamicPropSetter(ReactPropGroup prop, Method setter, int index) {
            super(prop, "mixed", setter, index, null);
        }

        protected Object extractProperty(ReactStylesDiffMap props) {
            return props.getDynamic(this.mPropName);
        }
    }

    private static class FloatPropSetter extends PropSetter {
        private final float mDefaultValue;

        public FloatPropSetter(ReactProp prop, Method setter, float defaultValue) {
            super(prop, "number", setter, null);
            this.mDefaultValue = defaultValue;
        }

        public FloatPropSetter(ReactPropGroup prop, Method setter, int index, float defaultValue) {
            super(prop, "number", setter, index, null);
            this.mDefaultValue = defaultValue;
        }

        protected Object extractProperty(ReactStylesDiffMap props) {
            return Float.valueOf(props.getFloat(this.mPropName, this.mDefaultValue));
        }
    }

    private static class IntPropSetter extends PropSetter {
        private final int mDefaultValue;

        public IntPropSetter(ReactProp prop, Method setter, int defaultValue) {
            super(prop, "number", setter, null);
            this.mDefaultValue = defaultValue;
        }

        public IntPropSetter(ReactPropGroup prop, Method setter, int index, int defaultValue) {
            super(prop, "number", setter, index, null);
            this.mDefaultValue = defaultValue;
        }

        protected Object extractProperty(ReactStylesDiffMap props) {
            return Integer.valueOf(props.getInt(this.mPropName, this.mDefaultValue));
        }
    }

    ViewManagersPropertyCache() {
    }

    static Map<String, String> getNativePropsForView(Class<? extends ViewManager> viewManagerTopClass, Class<? extends ReactShadowNode> shadowNodeTopClass) {
        Map<String, String> nativeProps = new HashMap();
        for (PropSetter setter : getNativePropSettersForViewManagerClass(viewManagerTopClass).values()) {
            nativeProps.put(setter.getPropName(), setter.getPropType());
        }
        for (PropSetter setter2 : getNativePropSettersForShadowNodeClass(shadowNodeTopClass).values()) {
            nativeProps.put(setter2.getPropName(), setter2.getPropType());
        }
        return nativeProps;
    }

    static Map<String, PropSetter> getNativePropSettersForViewManagerClass(Class<? extends ViewManager> cls) {
        if (cls == ViewManager.class) {
            return EMPTY_PROPS_MAP;
        }
        Map<String, PropSetter> props = (Map) CLASS_PROPS_CACHE.get(cls);
        if (props != null) {
            return props;
        }
        props = new HashMap(getNativePropSettersForViewManagerClass(cls.getSuperclass()));
        extractPropSettersFromViewManagerClassDefinition(cls, props);
        CLASS_PROPS_CACHE.put(cls, props);
        return props;
    }

    static Map<String, PropSetter> getNativePropSettersForShadowNodeClass(Class<? extends ReactShadowNode> cls) {
        if (cls == ReactShadowNode.class) {
            return EMPTY_PROPS_MAP;
        }
        Map<String, PropSetter> props = (Map) CLASS_PROPS_CACHE.get(cls);
        if (props != null) {
            return props;
        }
        props = new HashMap(getNativePropSettersForShadowNodeClass(cls.getSuperclass()));
        extractPropSettersFromShadowNodeClassDefinition(cls, props);
        CLASS_PROPS_CACHE.put(cls, props);
        return props;
    }

    private static PropSetter createPropSetter(ReactProp annotation, Method method, Class<?> propTypeClass) {
        if (propTypeClass == Dynamic.class) {
            return new DynamicPropSetter(annotation, method);
        }
        if (propTypeClass == Boolean.TYPE) {
            return new BooleanPropSetter(annotation, method, annotation.defaultBoolean());
        }
        if (propTypeClass == Integer.TYPE) {
            return new IntPropSetter(annotation, method, annotation.defaultInt());
        }
        if (propTypeClass == Float.TYPE) {
            return new FloatPropSetter(annotation, method, annotation.defaultFloat());
        }
        if (propTypeClass == Double.TYPE) {
            return new DoublePropSetter(annotation, method, annotation.defaultDouble());
        }
        if (propTypeClass == String.class) {
            return new StringPropSetter(annotation, method);
        }
        if (propTypeClass == Boolean.class) {
            return new BoxedBooleanPropSetter(annotation, method);
        }
        if (propTypeClass == Integer.class) {
            return new BoxedIntPropSetter(annotation, method);
        }
        if (propTypeClass == ReadableArray.class) {
            return new ArrayPropSetter(annotation, method);
        }
        if (propTypeClass == ReadableMap.class) {
            return new MapPropSetter(annotation, method);
        }
        throw new RuntimeException("Unrecognized type: " + propTypeClass + " for method: " + method.getDeclaringClass().getName() + "#" + method.getName());
    }

    private static void createPropSetters(ReactPropGroup annotation, Method method, Class<?> propTypeClass, Map<String, PropSetter> props) {
        String[] names = annotation.names();
        int i;
        if (propTypeClass == Dynamic.class) {
            for (i = 0; i < names.length; i++) {
                props.put(names[i], new DynamicPropSetter(annotation, method, i));
            }
        } else if (propTypeClass == Integer.TYPE) {
            for (i = 0; i < names.length; i++) {
                props.put(names[i], new IntPropSetter(annotation, method, i, annotation.defaultInt()));
            }
        } else if (propTypeClass == Float.TYPE) {
            for (i = 0; i < names.length; i++) {
                props.put(names[i], new FloatPropSetter(annotation, method, i, annotation.defaultFloat()));
            }
        } else if (propTypeClass == Double.TYPE) {
            for (i = 0; i < names.length; i++) {
                props.put(names[i], new DoublePropSetter(annotation, method, i, annotation.defaultDouble()));
            }
        } else if (propTypeClass == Integer.class) {
            for (i = 0; i < names.length; i++) {
                props.put(names[i], new BoxedIntPropSetter(annotation, method, i));
            }
        } else {
            throw new RuntimeException("Unrecognized type: " + propTypeClass + " for method: " + method.getDeclaringClass().getName() + "#" + method.getName());
        }
    }

    private static void extractPropSettersFromViewManagerClassDefinition(Class<? extends ViewManager> cls, Map<String, PropSetter> props) {
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method method : declaredMethods) {
            Class<?>[] paramTypes;
            ReactProp annotation = (ReactProp) method.getAnnotation(ReactProp.class);
            if (annotation != null) {
                paramTypes = method.getParameterTypes();
                if (paramTypes.length != 2) {
                    throw new RuntimeException("Wrong number of args for prop setter: " + cls.getName() + "#" + method.getName());
                } else if (View.class.isAssignableFrom(paramTypes[0])) {
                    props.put(annotation.name(), createPropSetter(annotation, method, paramTypes[1]));
                } else {
                    throw new RuntimeException("First param should be a view subclass to be updated: " + cls.getName() + "#" + method.getName());
                }
            }
            ReactPropGroup groupAnnotation = (ReactPropGroup) method.getAnnotation(ReactPropGroup.class);
            if (groupAnnotation != null) {
                paramTypes = method.getParameterTypes();
                if (paramTypes.length != 3) {
                    throw new RuntimeException("Wrong number of args for group prop setter: " + cls.getName() + "#" + method.getName());
                } else if (!View.class.isAssignableFrom(paramTypes[0])) {
                    throw new RuntimeException("First param should be a view subclass to be updated: " + cls.getName() + "#" + method.getName());
                } else if (paramTypes[1] != Integer.TYPE) {
                    throw new RuntimeException("Second argument should be property index: " + cls.getName() + "#" + method.getName());
                } else {
                    createPropSetters(groupAnnotation, method, paramTypes[2], props);
                }
            }
        }
    }

    private static void extractPropSettersFromShadowNodeClassDefinition(Class<? extends ReactShadowNode> cls, Map<String, PropSetter> props) {
        for (Method method : cls.getDeclaredMethods()) {
            Class<?>[] paramTypes;
            ReactProp annotation = (ReactProp) method.getAnnotation(ReactProp.class);
            if (annotation != null) {
                paramTypes = method.getParameterTypes();
                if (paramTypes.length != 1) {
                    throw new RuntimeException("Wrong number of args for prop setter: " + cls.getName() + "#" + method.getName());
                }
                props.put(annotation.name(), createPropSetter(annotation, method, paramTypes[0]));
            }
            ReactPropGroup groupAnnotation = (ReactPropGroup) method.getAnnotation(ReactPropGroup.class);
            if (groupAnnotation != null) {
                paramTypes = method.getParameterTypes();
                if (paramTypes.length != 2) {
                    throw new RuntimeException("Wrong number of args for group prop setter: " + cls.getName() + "#" + method.getName());
                } else if (paramTypes[0] != Integer.TYPE) {
                    throw new RuntimeException("Second argument should be property index: " + cls.getName() + "#" + method.getName());
                } else {
                    createPropSetters(groupAnnotation, method, paramTypes[1], props);
                }
            }
        }
    }
}
