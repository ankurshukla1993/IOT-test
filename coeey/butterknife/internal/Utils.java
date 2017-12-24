package butterknife.internal;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.support.annotation.AttrRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.TypedValue;
import android.view.View;
import java.lang.reflect.Array;
import java.util.List;

public final class Utils {
    private static final TypedValue VALUE = new TypedValue();

    @UiThread
    public static Drawable getTintedDrawable(Context context, @DrawableRes int id, @AttrRes int tintAttrId) {
        if (context.getTheme().resolveAttribute(tintAttrId, VALUE, true)) {
            Drawable drawable = DrawableCompat.wrap(ContextCompat.getDrawable(context, id).mutate());
            DrawableCompat.setTint(drawable, ContextCompat.getColor(context, VALUE.resourceId));
            return drawable;
        }
        throw new NotFoundException("Required tint color attribute with name " + context.getResources().getResourceEntryName(tintAttrId) + " and attribute ID " + tintAttrId + " was not found.");
    }

    @UiThread
    public static float getFloat(Context context, @DimenRes int id) {
        TypedValue value = VALUE;
        context.getResources().getValue(id, value, true);
        if (value.type == 4) {
            return value.getFloat();
        }
        throw new NotFoundException("Resource ID #0x" + Integer.toHexString(id) + " type #0x" + Integer.toHexString(value.type) + " is not valid");
    }

    @SafeVarargs
    public static <T> T[] arrayOf(T... views) {
        return filterNull(views);
    }

    @SafeVarargs
    public static <T> List<T> listOf(T... views) {
        return new ImmutableList(filterNull(views));
    }

    private static <T> T[] filterNull(T[] views) {
        int length = views.length;
        int i = 0;
        int end = 0;
        while (i < length) {
            int end2;
            T view = views[i];
            if (view != null) {
                end2 = end + 1;
                views[end] = view;
            } else {
                end2 = end;
            }
            i++;
            end = end2;
        }
        if (end == length) {
            return views;
        }
        T[] newViews = (Object[]) ((Object[]) Array.newInstance(views.getClass().getComponentType(), end));
        System.arraycopy(views, 0, newViews, 0, end);
        return newViews;
    }

    public static <T> T findOptionalViewAsType(View source, @IdRes int id, String who, Class<T> cls) {
        return castView(source.findViewById(id), id, who, cls);
    }

    public static View findRequiredView(View source, @IdRes int id, String who) {
        View view = source.findViewById(id);
        if (view != null) {
            return view;
        }
        throw new IllegalStateException("Required view '" + getResourceEntryName(source, id) + "' with ID " + id + " for " + who + " was not found. If this view is optional add '@Nullable' (fields) or '@Optional' (methods) annotation.");
    }

    public static <T> T findRequiredViewAsType(View source, @IdRes int id, String who, Class<T> cls) {
        return castView(findRequiredView(source, id, who), id, who, cls);
    }

    public static <T> T castView(View view, @IdRes int id, String who, Class<T> cls) {
        try {
            return cls.cast(view);
        } catch (ClassCastException e) {
            throw new IllegalStateException("View '" + getResourceEntryName(view, id) + "' with ID " + id + " for " + who + " was of the wrong type. See cause for more info.", e);
        }
    }

    public static <T> T castParam(Object value, String from, int fromPos, String to, int toPos, Class<T> cls) {
        try {
            return cls.cast(value);
        } catch (ClassCastException e) {
            throw new IllegalStateException("Parameter #" + (fromPos + 1) + " of method '" + from + "' was of the wrong type for parameter #" + (toPos + 1) + " of method '" + to + "'. See cause for more info.", e);
        }
    }

    private static String getResourceEntryName(View view, @IdRes int id) {
        if (view.isInEditMode()) {
            return "<unavailable while editing>";
        }
        return view.getContext().getResources().getResourceEntryName(id);
    }

    private Utils() {
        throw new AssertionError("No instances.");
    }
}
