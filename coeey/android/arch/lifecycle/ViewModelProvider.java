package android.arch.lifecycle;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

public class ViewModelProvider {
    private static final String DEFAULT_KEY = "android.arch.lifecycle.ViewModelProvider.DefaultKey";
    private final Factory mFactory;
    private final ViewModelStore mViewModelStore;

    public interface Factory {
        <T extends ViewModel> T create(Class<T> cls);
    }

    public static class NewInstanceFactory implements Factory {
        public <T extends ViewModel> T create(Class<T> modelClass) {
            try {
                return (ViewModel) modelClass.newInstance();
            } catch (InstantiationException e) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e);
            } catch (IllegalAccessException e2) {
                throw new RuntimeException("Cannot create an instance of " + modelClass, e2);
            }
        }
    }

    public ViewModelProvider(@NonNull ViewModelStoreOwner owner, @NonNull Factory factory) {
        this(owner.getViewModelStore(), factory);
    }

    public ViewModelProvider(ViewModelStore store, Factory factory) {
        this.mFactory = factory;
        this.mViewModelStore = store;
    }

    public <T extends ViewModel> T get(Class<T> modelClass) {
        String canonicalName = modelClass.getCanonicalName();
        if (canonicalName != null) {
            return get("android.arch.lifecycle.ViewModelProvider.DefaultKey:" + canonicalName, modelClass);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @MainThread
    @NonNull
    public <T extends ViewModel> T get(@NonNull String key, @NonNull Class<T> modelClass) {
        ViewModel viewModel = this.mViewModelStore.get(key);
        if (modelClass.isInstance(viewModel)) {
            return viewModel;
        }
        if (viewModel != null) {
            viewModel = this.mFactory.create(modelClass);
            this.mViewModelStore.put(key, viewModel);
        } else {
            viewModel = this.mFactory.create(modelClass);
            this.mViewModelStore.put(key, viewModel);
        }
        return viewModel;
    }
}
