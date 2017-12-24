package android.databinding;

import android.databinding.CallbackRegistry.NotifierCallback;
import android.databinding.ObservableMap.OnMapChangedCallback;

public class MapChangeRegistry extends CallbackRegistry<OnMapChangedCallback, ObservableMap, Object> {
    private static NotifierCallback<OnMapChangedCallback, ObservableMap, Object> NOTIFIER_CALLBACK = new C00391();

    static class C00391 extends NotifierCallback<OnMapChangedCallback, ObservableMap, Object> {
        C00391() {
        }

        public void onNotifyCallback(OnMapChangedCallback callback, ObservableMap sender, int arg, Object arg2) {
            callback.onMapChanged(sender, arg2);
        }
    }

    public MapChangeRegistry() {
        super(NOTIFIER_CALLBACK);
    }

    public void notifyChange(ObservableMap sender, Object key) {
        notifyCallbacks(sender, 0, key);
    }
}
