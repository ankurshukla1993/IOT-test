package android.databinding;

import android.databinding.CallbackRegistry.NotifierCallback;
import android.databinding.Observable.OnPropertyChangedCallback;

public class PropertyChangeRegistry extends CallbackRegistry<OnPropertyChangedCallback, Observable, Void> {
    private static final NotifierCallback<OnPropertyChangedCallback, Observable, Void> NOTIFIER_CALLBACK = new C00491();

    static class C00491 extends NotifierCallback<OnPropertyChangedCallback, Observable, Void> {
        C00491() {
        }

        public void onNotifyCallback(OnPropertyChangedCallback callback, Observable sender, int arg, Void notUsed) {
            callback.onPropertyChanged(sender, arg);
        }
    }

    public PropertyChangeRegistry() {
        super(NOTIFIER_CALLBACK);
    }

    public void notifyChange(Observable observable, int propertyId) {
        notifyCallbacks(observable, propertyId, null);
    }
}
