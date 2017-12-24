package android.databinding.adapters;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.Untaggable;
import android.databinding.ViewStubProxy;
import android.view.ViewStub;
import android.view.ViewStub.OnInflateListener;

@BindingMethods({@BindingMethod(attribute = "android:layout", method = "setLayoutResource", type = ViewStub.class)})
@Untaggable({"android.view.ViewStub"})
public class ViewStubBindingAdapter {
    @BindingAdapter({"android:onInflate"})
    public static void setOnInflateListener(ViewStubProxy viewStubProxy, OnInflateListener listener) {
        viewStubProxy.setOnInflateListener(listener);
    }
}
