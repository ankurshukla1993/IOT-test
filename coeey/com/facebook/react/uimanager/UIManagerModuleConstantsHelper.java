package com.facebook.react.uimanager;

import com.facebook.react.common.MapBuilder;
import com.facebook.systrace.Systrace;
import com.facebook.systrace.SystraceMessage;
import java.util.List;
import java.util.Map;

class UIManagerModuleConstantsHelper {
    private static final String CUSTOM_BUBBLING_EVENT_TYPES_KEY = "customBubblingEventTypes";
    private static final String CUSTOM_DIRECT_EVENT_TYPES_KEY = "customDirectEventTypes";

    UIManagerModuleConstantsHelper() {
    }

    static Map<String, Object> createConstants(List<ViewManager> viewManagers, boolean lazyViewManagersEnabled) {
        Map<String, Object> constants = UIManagerModuleConstants.getConstants();
        Map bubblingEventTypesConstants = UIManagerModuleConstants.getBubblingEventTypeConstants();
        Map directEventTypesConstants = UIManagerModuleConstants.getDirectEventTypeConstants();
        for (ViewManager viewManager : viewManagers) {
            SystraceMessage.beginSection(0, "constants for ViewManager").arg("ViewManager", viewManager.getName()).flush();
            try {
                Map viewManagerBubblingEvents = viewManager.getExportedCustomBubblingEventTypeConstants();
                if (viewManagerBubblingEvents != null) {
                    recursiveMerge(bubblingEventTypesConstants, viewManagerBubblingEvents);
                }
                Map viewManagerDirectEvents = viewManager.getExportedCustomDirectEventTypeConstants();
                if (viewManagerDirectEvents != null) {
                    recursiveMerge(directEventTypesConstants, viewManagerDirectEvents);
                }
                Map viewManagerConstants = MapBuilder.newHashMap();
                Map customViewConstants = viewManager.getExportedViewConstants();
                if (customViewConstants != null) {
                    viewManagerConstants.put("Constants", customViewConstants);
                }
                Map viewManagerCommands = viewManager.getCommandsMap();
                if (viewManagerCommands != null) {
                    viewManagerConstants.put("Commands", viewManagerCommands);
                }
                Map<String, String> viewManagerNativeProps = viewManager.getNativeProps();
                if (!viewManagerNativeProps.isEmpty()) {
                    viewManagerConstants.put("NativeProps", viewManagerNativeProps);
                }
                if (!viewManagerConstants.isEmpty()) {
                    constants.put(viewManager.getName(), viewManagerConstants);
                }
                Systrace.endSection(0);
            } catch (Throwable th) {
                Systrace.endSection(0);
            }
        }
        constants.put(CUSTOM_BUBBLING_EVENT_TYPES_KEY, bubblingEventTypesConstants);
        constants.put(CUSTOM_DIRECT_EVENT_TYPES_KEY, directEventTypesConstants);
        constants.put("AndroidLazyViewManagersEnabled", Boolean.valueOf(lazyViewManagersEnabled));
        return constants;
    }

    private static void recursiveMerge(Map dest, Map source) {
        for (Object key : source.keySet()) {
            Object sourceValue = source.get(key);
            Object destValue = dest.get(key);
            if (destValue != null && (sourceValue instanceof Map) && (destValue instanceof Map)) {
                recursiveMerge((Map) destValue, (Map) sourceValue);
            } else {
                dest.put(key, sourceValue);
            }
        }
    }
}
