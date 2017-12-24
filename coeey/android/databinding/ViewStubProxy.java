package android.databinding;

import android.view.View;
import android.view.ViewStub;
import android.view.ViewStub.OnInflateListener;

public class ViewStubProxy {
    private ViewDataBinding mContainingBinding;
    private OnInflateListener mOnInflateListener;
    private OnInflateListener mProxyListener = new C00571();
    private View mRoot;
    private ViewDataBinding mViewDataBinding;
    private ViewStub mViewStub;

    class C00571 implements OnInflateListener {
        C00571() {
        }

        public void onInflate(ViewStub stub, View inflated) {
            ViewStubProxy.this.mRoot = inflated;
            ViewStubProxy.this.mViewDataBinding = DataBindingUtil.bind(ViewStubProxy.this.mContainingBinding.mBindingComponent, inflated, stub.getLayoutResource());
            ViewStubProxy.this.mViewStub = null;
            if (ViewStubProxy.this.mOnInflateListener != null) {
                ViewStubProxy.this.mOnInflateListener.onInflate(stub, inflated);
                ViewStubProxy.this.mOnInflateListener = null;
            }
            ViewStubProxy.this.mContainingBinding.invalidateAll();
            ViewStubProxy.this.mContainingBinding.forceExecuteBindings();
        }
    }

    public ViewStubProxy(ViewStub viewStub) {
        this.mViewStub = viewStub;
        this.mViewStub.setOnInflateListener(this.mProxyListener);
    }

    public void setContainingBinding(ViewDataBinding containingBinding) {
        this.mContainingBinding = containingBinding;
    }

    public boolean isInflated() {
        return this.mRoot != null;
    }

    public View getRoot() {
        return this.mRoot;
    }

    public ViewDataBinding getBinding() {
        return this.mViewDataBinding;
    }

    public ViewStub getViewStub() {
        return this.mViewStub;
    }

    public void setOnInflateListener(OnInflateListener listener) {
        if (this.mViewStub != null) {
            this.mOnInflateListener = listener;
        }
    }
}
