package org.jitsi.meet.sdk;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

class ExternalAPIModule extends ReactContextBaseJavaModule {
    private static final Map<String, Method> JITSI_MEET_VIEW_LISTENER_METHODS = new HashMap();
    private static final String MODULE_NAME = "ExternalAPI";

    static {
        Pattern onPattern = Pattern.compile("^on[A-Z]+");
        Pattern camelcasePattern = Pattern.compile("([a-z0-9]+)([A-Z0-9]+)");
        for (Method method : JitsiMeetViewListener.class.getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers()) && Void.TYPE.equals(method.getReturnType())) {
                String name = method.getName();
                if (onPattern.matcher(name).find()) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1 && parameterTypes[0].isAssignableFrom(HashMap.class)) {
                        JITSI_MEET_VIEW_LISTENER_METHODS.put(camelcasePattern.matcher(name.substring(2)).replaceAll("$1_$2").toUpperCase(Locale.ROOT), method);
                    }
                }
            }
        }
    }

    public ExternalAPIModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    public String getName() {
        return MODULE_NAME;
    }

    @ReactMethod
    public void sendEvent(String name, ReadableMap data, String scope) {
        JitsiMeetView view = JitsiMeetView.findViewByExternalAPIScope(scope);
        if (view != null) {
            JitsiMeetViewListener listener = view.getListener();
            if (listener != null) {
                Method method = (Method) JITSI_MEET_VIEW_LISTENER_METHODS.get(name);
                if (method != null) {
                    try {
                        method.invoke(listener, new Object[]{toHashMap(data)});
                    } catch (ReflectiveOperationException roe) {
                        throw new RuntimeException(roe);
                    }
                }
            }
        }
    }

    private HashMap<String, Object> toHashMap(ReadableMap readableMap) {
        HashMap<String, Object> hashMap = new HashMap();
        ReadableMapKeySetIterator i = readableMap.keySetIterator();
        while (i.hasNextKey()) {
            String key = i.nextKey();
            hashMap.put(key, readableMap.getString(key));
        }
        return hashMap;
    }
}
