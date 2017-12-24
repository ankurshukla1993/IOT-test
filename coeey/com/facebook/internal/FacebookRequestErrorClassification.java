package com.facebook.internal;

import com.facebook.FacebookRequestError.Category;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public final class FacebookRequestErrorClassification {
    public static final int EC_APP_TOO_MANY_CALLS = 4;
    public static final int EC_INVALID_SESSION = 102;
    public static final int EC_INVALID_TOKEN = 190;
    public static final int EC_RATE = 9;
    public static final int EC_SERVICE_UNAVAILABLE = 2;
    public static final int EC_TOO_MANY_USER_ACTION_CALLS = 341;
    public static final int EC_USER_TOO_MANY_CALLS = 17;
    public static final String KEY_LOGIN_RECOVERABLE = "login_recoverable";
    public static final String KEY_NAME = "name";
    public static final String KEY_OTHER = "other";
    public static final String KEY_RECOVERY_MESSAGE = "recovery_message";
    public static final String KEY_TRANSIENT = "transient";
    private static FacebookRequestErrorClassification defaultInstance;
    private final Map<Integer, Set<Integer>> loginRecoverableErrors;
    private final String loginRecoverableRecoveryMessage;
    private final Map<Integer, Set<Integer>> otherErrors;
    private final String otherRecoveryMessage;
    private final Map<Integer, Set<Integer>> transientErrors;
    private final String transientRecoveryMessage;

    static class C12201 extends HashMap<Integer, Set<Integer>> {
        C12201() {
            put(Integer.valueOf(2), null);
            put(Integer.valueOf(4), null);
            put(Integer.valueOf(9), null);
            put(Integer.valueOf(17), null);
            put(Integer.valueOf(FacebookRequestErrorClassification.EC_TOO_MANY_USER_ACTION_CALLS), null);
        }
    }

    static class C12212 extends HashMap<Integer, Set<Integer>> {
        C12212() {
            put(Integer.valueOf(102), null);
            put(Integer.valueOf(FacebookRequestErrorClassification.EC_INVALID_TOKEN), null);
        }
    }

    FacebookRequestErrorClassification(Map<Integer, Set<Integer>> otherErrors, Map<Integer, Set<Integer>> transientErrors, Map<Integer, Set<Integer>> loginRecoverableErrors, String otherRecoveryMessage, String transientRecoveryMessage, String loginRecoverableRecoveryMessage) {
        this.otherErrors = otherErrors;
        this.transientErrors = transientErrors;
        this.loginRecoverableErrors = loginRecoverableErrors;
        this.otherRecoveryMessage = otherRecoveryMessage;
        this.transientRecoveryMessage = transientRecoveryMessage;
        this.loginRecoverableRecoveryMessage = loginRecoverableRecoveryMessage;
    }

    public Map<Integer, Set<Integer>> getOtherErrors() {
        return this.otherErrors;
    }

    public Map<Integer, Set<Integer>> getTransientErrors() {
        return this.transientErrors;
    }

    public Map<Integer, Set<Integer>> getLoginRecoverableErrors() {
        return this.loginRecoverableErrors;
    }

    public String getRecoveryMessage(Category category) {
        switch (category) {
            case OTHER:
                return this.otherRecoveryMessage;
            case LOGIN_RECOVERABLE:
                return this.loginRecoverableRecoveryMessage;
            case TRANSIENT:
                return this.transientRecoveryMessage;
            default:
                return null;
        }
    }

    public Category classify(int errorCode, int errorSubCode, boolean isTransient) {
        if (isTransient) {
            return Category.TRANSIENT;
        }
        Set<Integer> subCodes;
        if (this.otherErrors != null && this.otherErrors.containsKey(Integer.valueOf(errorCode))) {
            subCodes = (Set) this.otherErrors.get(Integer.valueOf(errorCode));
            if (subCodes == null || subCodes.contains(Integer.valueOf(errorSubCode))) {
                return Category.OTHER;
            }
        }
        if (this.loginRecoverableErrors != null && this.loginRecoverableErrors.containsKey(Integer.valueOf(errorCode))) {
            subCodes = (Set) this.loginRecoverableErrors.get(Integer.valueOf(errorCode));
            if (subCodes == null || subCodes.contains(Integer.valueOf(errorSubCode))) {
                return Category.LOGIN_RECOVERABLE;
            }
        }
        if (this.transientErrors != null && this.transientErrors.containsKey(Integer.valueOf(errorCode))) {
            subCodes = (Set) this.transientErrors.get(Integer.valueOf(errorCode));
            if (subCodes == null || subCodes.contains(Integer.valueOf(errorSubCode))) {
                return Category.TRANSIENT;
            }
        }
        return Category.OTHER;
    }

    public static synchronized FacebookRequestErrorClassification getDefaultErrorClassification() {
        FacebookRequestErrorClassification facebookRequestErrorClassification;
        synchronized (FacebookRequestErrorClassification.class) {
            if (defaultInstance == null) {
                defaultInstance = getDefaultErrorClassificationImpl();
            }
            facebookRequestErrorClassification = defaultInstance;
        }
        return facebookRequestErrorClassification;
    }

    private static FacebookRequestErrorClassification getDefaultErrorClassificationImpl() {
        return new FacebookRequestErrorClassification(null, new C12201(), new C12212(), null, null, null);
    }

    private static Map<Integer, Set<Integer>> parseJSONDefinition(JSONObject definition) {
        JSONArray itemsArray = definition.optJSONArray("items");
        if (itemsArray.length() == 0) {
            return null;
        }
        Map<Integer, Set<Integer>> items = new HashMap();
        for (int i = 0; i < itemsArray.length(); i++) {
            JSONObject item = itemsArray.optJSONObject(i);
            if (item != null) {
                int code = item.optInt("code");
                if (code != 0) {
                    Set<Integer> subcodes = null;
                    JSONArray subcodesArray = item.optJSONArray("subcodes");
                    if (subcodesArray != null && subcodesArray.length() > 0) {
                        subcodes = new HashSet();
                        for (int j = 0; j < subcodesArray.length(); j++) {
                            int subCode = subcodesArray.optInt(j);
                            if (subCode != 0) {
                                subcodes.add(Integer.valueOf(subCode));
                            }
                        }
                    }
                    items.put(Integer.valueOf(code), subcodes);
                }
            }
        }
        return items;
    }

    public static FacebookRequestErrorClassification createFromJSON(JSONArray jsonArray) {
        if (jsonArray == null) {
            return null;
        }
        Map<Integer, Set<Integer>> otherErrors = null;
        Map<Integer, Set<Integer>> transientErrors = null;
        Map<Integer, Set<Integer>> loginRecoverableErrors = null;
        String otherRecoveryMessage = null;
        String transientRecoveryMessage = null;
        String loginRecoverableRecoveryMessage = null;
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject definition = jsonArray.optJSONObject(i);
            if (definition != null) {
                String name = definition.optString("name");
                if (name != null) {
                    if (name.equalsIgnoreCase(KEY_OTHER)) {
                        otherRecoveryMessage = definition.optString(KEY_RECOVERY_MESSAGE, null);
                        otherErrors = parseJSONDefinition(definition);
                    } else if (name.equalsIgnoreCase(KEY_TRANSIENT)) {
                        transientRecoveryMessage = definition.optString(KEY_RECOVERY_MESSAGE, null);
                        transientErrors = parseJSONDefinition(definition);
                    } else if (name.equalsIgnoreCase(KEY_LOGIN_RECOVERABLE)) {
                        loginRecoverableRecoveryMessage = definition.optString(KEY_RECOVERY_MESSAGE, null);
                        loginRecoverableErrors = parseJSONDefinition(definition);
                    }
                }
            }
        }
        return new FacebookRequestErrorClassification(otherErrors, transientErrors, loginRecoverableErrors, otherRecoveryMessage, transientRecoveryMessage, loginRecoverableRecoveryMessage);
    }
}
