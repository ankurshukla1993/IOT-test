package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.facebook.react.uimanager.ViewManagersPropertyCache.PropSetter;
import java.util.HashMap;
import java.util.Map;

public class ViewManagerPropertyUpdater {
    private static final Map<Class<?>, ShadowNodeSetter<?>> SHADOW_NODE_SETTER_MAP = new HashMap();
    private static final String TAG = "ViewManagerPropertyUpdater";
    private static final Map<Class<?>, ViewManagerSetter<?, ?>> VIEW_MANAGER_SETTER_MAP = new HashMap();

    public interface Settable {
        void getProperties(Map<String, String> map);
    }

    public interface ShadowNodeSetter<T extends ReactShadowNode> extends Settable {
        void setProperty(T t, String str, ReactStylesDiffMap reactStylesDiffMap);
    }

    private static class FallbackShadowNodeSetter<T extends ReactShadowNode> implements ShadowNodeSetter<T> {
        private final Map<String, PropSetter> mPropSetters;

        private FallbackShadowNodeSetter(Class<? extends ReactShadowNode> shadowNodeClass) {
            this.mPropSetters = ViewManagersPropertyCache.getNativePropSettersForShadowNodeClass(shadowNodeClass);
        }

        public void setProperty(ReactShadowNode node, String name, ReactStylesDiffMap props) {
            PropSetter setter = (PropSetter) this.mPropSetters.get(name);
            if (setter != null) {
                setter.updateShadowNodeProp(node, props);
            }
        }

        public void getProperties(Map<String, String> props) {
            for (PropSetter setter : this.mPropSetters.values()) {
                props.put(setter.getPropName(), setter.getPropType());
            }
        }
    }

    public interface ViewManagerSetter<T extends ViewManager, V extends View> extends Settable {
        void setProperty(T t, V v, String str, ReactStylesDiffMap reactStylesDiffMap);
    }

    private static class FallbackViewManagerSetter<T extends ViewManager, V extends View> implements ViewManagerSetter<T, V> {
        private final Map<String, PropSetter> mPropSetters;

        private FallbackViewManagerSetter(Class<? extends ViewManager> viewManagerClass) {
            this.mPropSetters = ViewManagersPropertyCache.getNativePropSettersForViewManagerClass(viewManagerClass);
        }

        public void setProperty(T manager, V v, String name, ReactStylesDiffMap props) {
            PropSetter setter = (PropSetter) this.mPropSetters.get(name);
            if (setter != null) {
                setter.updateViewProp(manager, v, props);
            }
        }

        public void getProperties(Map<String, String> props) {
            for (PropSetter setter : this.mPropSetters.values()) {
                props.put(setter.getPropName(), setter.getPropType());
            }
        }
    }

    public static <T extends ViewManager, V extends View> void updateProps(T manager, V v, ReactStylesDiffMap props) {
        ViewManagerSetter<T, V> setter = findManagerSetter(manager.getClass());
        ReadableMapKeySetIterator iterator = props.mBackingMap.keySetIterator();
        while (iterator.hasNextKey()) {
            setter.setProperty(manager, v, iterator.nextKey(), props);
        }
    }

    public static <T extends ReactShadowNode> void updateProps(T node, ReactStylesDiffMap props) {
        ShadowNodeSetter<T> setter = findNodeSetter(node.getClass());
        ReadableMapKeySetIterator iterator = props.mBackingMap.keySetIterator();
        while (iterator.hasNextKey()) {
            setter.setProperty(node, iterator.nextKey(), props);
        }
    }

    public static Map<String, String> getNativeProps(Class<? extends ViewManager> viewManagerTopClass, Class<? extends ReactShadowNode> shadowNodeTopClass) {
        Map<String, String> props = new HashMap();
        findManagerSetter(viewManagerTopClass).getProperties(props);
        findNodeSetter(shadowNodeTopClass).getProperties(props);
        return props;
    }

    private static <T extends ViewManager, V extends View> ViewManagerSetter<T, V> findManagerSetter(Class<? extends ViewManager> managerClass) {
        ViewManagerSetter<T, V> setter = (ViewManagerSetter) VIEW_MANAGER_SETTER_MAP.get(managerClass);
        if (setter == null) {
            setter = (ViewManagerSetter) findGeneratedSetter(managerClass);
            if (setter == null) {
                setter = new FallbackViewManagerSetter(managerClass);
            }
            VIEW_MANAGER_SETTER_MAP.put(managerClass, setter);
        }
        return setter;
    }

    private static <T extends ReactShadowNode> ShadowNodeSetter<T> findNodeSetter(Class<? extends ReactShadowNode> nodeClass) {
        ShadowNodeSetter<T> setter = (ShadowNodeSetter) SHADOW_NODE_SETTER_MAP.get(nodeClass);
        if (setter == null) {
            setter = (ShadowNodeSetter) findGeneratedSetter(nodeClass);
            if (setter == null) {
                setter = new FallbackShadowNodeSetter(nodeClass);
            }
            SHADOW_NODE_SETTER_MAP.put(nodeClass, setter);
        }
        return setter;
    }

    private static <T> T findGeneratedSetter(Class<?> cls) {
        ReflectiveOperationException e;
        String clsName = cls.getName();
        try {
            return Class.forName(clsName + "$$PropsSetter").newInstance();
        } catch (ClassNotFoundException e2) {
            FLog.m151w(TAG, "Could not find generated setter for " + cls);
            return null;
        } catch (InstantiationException e3) {
            e = e3;
            throw new RuntimeException("Unable to instantiate methods getter for " + clsName, e);
        } catch (IllegalAccessException e4) {
            e = e4;
            throw new RuntimeException("Unable to instantiate methods getter for " + clsName, e);
        }
    }
}
