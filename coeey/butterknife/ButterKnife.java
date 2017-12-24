package butterknife;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.CheckResult;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.UiThread;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.Property;
import android.view.View;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ButterKnife {
    @VisibleForTesting
    static final Map<Class<?>, Constructor<? extends Unbinder>> BINDINGS = new LinkedHashMap();
    private static final String TAG = "ButterKnife";
    private static boolean debug = false;

    public interface Action<T extends View> {
        @UiThread
        void apply(@NonNull T t, int i);
    }

    public interface Setter<T extends View, V> {
        @UiThread
        void set(@NonNull T t, V v, int i);
    }

    private ButterKnife() {
        throw new AssertionError("No instances.");
    }

    public static void setDebug(boolean debug) {
        debug = debug;
    }

    @UiThread
    @NonNull
    public static Unbinder bind(@NonNull Activity target) {
        return createBinding(target, target.getWindow().getDecorView());
    }

    @UiThread
    @NonNull
    public static Unbinder bind(@NonNull View target) {
        return createBinding(target, target);
    }

    @UiThread
    @NonNull
    public static Unbinder bind(@NonNull Dialog target) {
        return createBinding(target, target.getWindow().getDecorView());
    }

    @UiThread
    @NonNull
    public static Unbinder bind(@NonNull Object target, @NonNull Activity source) {
        return createBinding(target, source.getWindow().getDecorView());
    }

    @UiThread
    @NonNull
    public static Unbinder bind(@NonNull Object target, @NonNull View source) {
        return createBinding(target, source);
    }

    @UiThread
    @NonNull
    public static Unbinder bind(@NonNull Object target, @NonNull Dialog source) {
        return createBinding(target, source.getWindow().getDecorView());
    }

    private static Unbinder createBinding(@NonNull Object target, @NonNull View source) {
        Class<?> targetClass = target.getClass();
        if (debug) {
            Log.d(TAG, "Looking up binding for " + targetClass.getName());
        }
        Constructor<? extends Unbinder> constructor = findBindingConstructorForClass(targetClass);
        if (constructor == null) {
            return Unbinder.EMPTY;
        }
        try {
            return (Unbinder) constructor.newInstance(new Object[]{target, source});
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to invoke " + constructor, e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("Unable to invoke " + constructor, e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unable to create binding instance.", cause);
            }
        }
    }

    @UiThread
    @CheckResult
    @Nullable
    private static Constructor<? extends Unbinder> findBindingConstructorForClass(Class<?> cls) {
        Constructor<? extends Unbinder> bindingCtor = (Constructor) BINDINGS.get(cls);
        if (bindingCtor != null) {
            if (debug) {
                Log.d(TAG, "HIT: Cached in binding map.");
            }
            return bindingCtor;
        }
        String clsName = cls.getName();
        if (clsName.startsWith("android.") || clsName.startsWith("java.")) {
            if (debug) {
                Log.d(TAG, "MISS: Reached framework class. Abandoning search.");
            }
            return null;
        }
        try {
            bindingCtor = cls.getClassLoader().loadClass(clsName + "_ViewBinding").getConstructor(new Class[]{cls, View.class});
            if (debug) {
                Log.d(TAG, "HIT: Loaded binding class and constructor.");
            }
        } catch (ClassNotFoundException e) {
            if (debug) {
                Log.d(TAG, "Not found. Trying superclass " + cls.getSuperclass().getName());
            }
            bindingCtor = findBindingConstructorForClass(cls.getSuperclass());
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException("Unable to find binding constructor for " + clsName, e2);
        }
        BINDINGS.put(cls, bindingCtor);
        return bindingCtor;
    }

    @UiThread
    @SafeVarargs
    public static <T extends View> void apply(@NonNull List<T> list, @NonNull Action<? super T>... actions) {
        int count = list.size();
        for (int i = 0; i < count; i++) {
            for (Action<? super T> action : actions) {
                action.apply((View) list.get(i), i);
            }
        }
    }

    @UiThread
    @SafeVarargs
    public static <T extends View> void apply(@NonNull T[] array, @NonNull Action<? super T>... actions) {
        int count = array.length;
        for (int i = 0; i < count; i++) {
            for (Action<? super T> action : actions) {
                action.apply(array[i], i);
            }
        }
    }

    @UiThread
    public static <T extends View> void apply(@NonNull List<T> list, @NonNull Action<? super T> action) {
        int count = list.size();
        for (int i = 0; i < count; i++) {
            action.apply((View) list.get(i), i);
        }
    }

    @UiThread
    public static <T extends View> void apply(@NonNull T[] array, @NonNull Action<? super T> action) {
        int count = array.length;
        for (int i = 0; i < count; i++) {
            action.apply(array[i], i);
        }
    }

    @UiThread
    @SafeVarargs
    public static <T extends View> void apply(@NonNull T view, @NonNull Action<? super T>... actions) {
        for (Action<? super T> action : actions) {
            action.apply(view, 0);
        }
    }

    @UiThread
    public static <T extends View> void apply(@NonNull T view, @NonNull Action<? super T> action) {
        action.apply(view, 0);
    }

    @UiThread
    public static <T extends View, V> void apply(@NonNull List<T> list, @NonNull Setter<? super T, V> setter, V value) {
        int count = list.size();
        for (int i = 0; i < count; i++) {
            setter.set((View) list.get(i), value, i);
        }
    }

    @UiThread
    public static <T extends View, V> void apply(@NonNull T[] array, @NonNull Setter<? super T, V> setter, V value) {
        int count = array.length;
        for (int i = 0; i < count; i++) {
            setter.set(array[i], value, i);
        }
    }

    @UiThread
    public static <T extends View, V> void apply(@NonNull T view, @NonNull Setter<? super T, V> setter, V value) {
        setter.set(view, value, 0);
    }

    @UiThread
    @RequiresApi(14)
    @TargetApi(14)
    public static <T extends View, V> void apply(@NonNull List<T> list, @NonNull Property<? super T, V> setter, V value) {
        int count = list.size();
        for (int i = 0; i < count; i++) {
            setter.set(list.get(i), value);
        }
    }

    @UiThread
    @RequiresApi(14)
    @TargetApi(14)
    public static <T extends View, V> void apply(@NonNull T[] array, @NonNull Property<? super T, V> setter, V value) {
        for (Object obj : array) {
            setter.set(obj, value);
        }
    }

    @UiThread
    @RequiresApi(14)
    @TargetApi(14)
    public static <T extends View, V> void apply(@NonNull T view, @NonNull Property<? super T, V> setter, V value) {
        setter.set(view, value);
    }

    @Deprecated
    @CheckResult
    public static <T extends View> T findById(@NonNull View view, @IdRes int id) {
        return view.findViewById(id);
    }

    @Deprecated
    @CheckResult
    public static <T extends View> T findById(@NonNull Activity activity, @IdRes int id) {
        return activity.findViewById(id);
    }

    @Deprecated
    @CheckResult
    public static <T extends View> T findById(@NonNull Dialog dialog, @IdRes int id) {
        return dialog.findViewById(id);
    }
}
