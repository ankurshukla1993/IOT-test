package com.facebook.react.views.toolbar;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.MeasureSpec;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.drawee.view.MultiDraweeHolder;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.PixelUtil;
import com.google.common.primitives.Ints;
import javax.annotation.Nullable;

public class ReactToolbar extends Toolbar {
    private static final String PROP_ACTION_ICON = "icon";
    private static final String PROP_ACTION_SHOW = "show";
    private static final String PROP_ACTION_SHOW_WITH_TEXT = "showWithText";
    private static final String PROP_ACTION_TITLE = "title";
    private static final String PROP_ICON_HEIGHT = "height";
    private static final String PROP_ICON_URI = "uri";
    private static final String PROP_ICON_WIDTH = "width";
    private final MultiDraweeHolder<GenericDraweeHierarchy> mActionsHolder = new MultiDraweeHolder();
    private final Runnable mLayoutRunnable = new C13534();
    private IconControllerListener mLogoControllerListener;
    private final DraweeHolder mLogoHolder;
    private IconControllerListener mNavIconControllerListener;
    private final DraweeHolder mNavIconHolder;
    private IconControllerListener mOverflowIconControllerListener;
    private final DraweeHolder mOverflowIconHolder;

    private abstract class IconControllerListener extends BaseControllerListener<ImageInfo> {
        private final DraweeHolder mHolder;
        private IconImageInfo mIconImageInfo;

        protected abstract void setDrawable(Drawable drawable);

        public IconControllerListener(DraweeHolder holder) {
            this.mHolder = holder;
        }

        public void setIconImageInfo(IconImageInfo iconImageInfo) {
            this.mIconImageInfo = iconImageInfo;
        }

        public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable animatable) {
            ImageInfo info;
            super.onFinalImageSet(id, imageInfo, animatable);
            if (this.mIconImageInfo != null) {
                info = this.mIconImageInfo;
            } else {
                info = imageInfo;
            }
            setDrawable(new DrawableWithIntrinsicSize(this.mHolder.getTopLevelDrawable(), info));
        }
    }

    class C13534 implements Runnable {
        C13534() {
        }

        public void run() {
            ReactToolbar.this.measure(MeasureSpec.makeMeasureSpec(ReactToolbar.this.getWidth(), Ints.MAX_POWER_OF_TWO), MeasureSpec.makeMeasureSpec(ReactToolbar.this.getHeight(), Ints.MAX_POWER_OF_TWO));
            ReactToolbar.this.layout(ReactToolbar.this.getLeft(), ReactToolbar.this.getTop(), ReactToolbar.this.getRight(), ReactToolbar.this.getBottom());
        }
    }

    private class ActionIconControllerListener extends IconControllerListener {
        private final MenuItem mItem;

        ActionIconControllerListener(MenuItem item, DraweeHolder holder) {
            super(holder);
            this.mItem = item;
        }

        protected void setDrawable(Drawable d) {
            this.mItem.setIcon(d);
        }
    }

    private static class IconImageInfo implements ImageInfo {
        private int mHeight;
        private int mWidth;

        public IconImageInfo(int width, int height) {
            this.mWidth = width;
            this.mHeight = height;
        }

        public int getWidth() {
            return this.mWidth;
        }

        public int getHeight() {
            return this.mHeight;
        }

        public QualityInfo getQualityInfo() {
            return null;
        }
    }

    public ReactToolbar(Context context) {
        super(context);
        this.mLogoHolder = DraweeHolder.create(createDraweeHierarchy(), context);
        this.mNavIconHolder = DraweeHolder.create(createDraweeHierarchy(), context);
        this.mOverflowIconHolder = DraweeHolder.create(createDraweeHierarchy(), context);
        this.mLogoControllerListener = new IconControllerListener(this.mLogoHolder) {
            protected void setDrawable(Drawable d) {
                ReactToolbar.this.setLogo(d);
            }
        };
        this.mNavIconControllerListener = new IconControllerListener(this.mNavIconHolder) {
            protected void setDrawable(Drawable d) {
                ReactToolbar.this.setNavigationIcon(d);
            }
        };
        this.mOverflowIconControllerListener = new IconControllerListener(this.mOverflowIconHolder) {
            protected void setDrawable(Drawable d) {
                ReactToolbar.this.setOverflowIcon(d);
            }
        };
    }

    public void requestLayout() {
        super.requestLayout();
        post(this.mLayoutRunnable);
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        detachDraweeHolders();
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        detachDraweeHolders();
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        attachDraweeHolders();
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        attachDraweeHolders();
    }

    private void detachDraweeHolders() {
        this.mLogoHolder.onDetach();
        this.mNavIconHolder.onDetach();
        this.mOverflowIconHolder.onDetach();
        this.mActionsHolder.onDetach();
    }

    private void attachDraweeHolders() {
        this.mLogoHolder.onAttach();
        this.mNavIconHolder.onAttach();
        this.mOverflowIconHolder.onAttach();
        this.mActionsHolder.onAttach();
    }

    void setLogoSource(@Nullable ReadableMap source) {
        setIconSource(source, this.mLogoControllerListener, this.mLogoHolder);
    }

    void setNavIconSource(@Nullable ReadableMap source) {
        setIconSource(source, this.mNavIconControllerListener, this.mNavIconHolder);
    }

    void setOverflowIconSource(@Nullable ReadableMap source) {
        setIconSource(source, this.mOverflowIconControllerListener, this.mOverflowIconHolder);
    }

    void setActions(@Nullable ReadableArray actions) {
        Menu menu = getMenu();
        menu.clear();
        this.mActionsHolder.clear();
        if (actions != null) {
            for (int i = 0; i < actions.size(); i++) {
                int showAsAction;
                ReadableMap action = actions.getMap(i);
                MenuItem item = menu.add(0, 0, i, action.getString("title"));
                if (action.hasKey("icon")) {
                    setMenuItemIcon(item, action.getMap("icon"));
                }
                if (action.hasKey(PROP_ACTION_SHOW)) {
                    showAsAction = action.getInt(PROP_ACTION_SHOW);
                } else {
                    showAsAction = 0;
                }
                if (action.hasKey(PROP_ACTION_SHOW_WITH_TEXT) && action.getBoolean(PROP_ACTION_SHOW_WITH_TEXT)) {
                    showAsAction |= 4;
                }
                item.setShowAsAction(showAsAction);
            }
        }
    }

    private void setMenuItemIcon(MenuItem item, ReadableMap iconSource) {
        DraweeHolder<GenericDraweeHierarchy> holder = DraweeHolder.create(createDraweeHierarchy(), getContext());
        ActionIconControllerListener controllerListener = new ActionIconControllerListener(item, holder);
        controllerListener.setIconImageInfo(getIconImageInfo(iconSource));
        setIconSource(iconSource, controllerListener, holder);
        this.mActionsHolder.add(holder);
    }

    private void setIconSource(ReadableMap source, IconControllerListener controllerListener, DraweeHolder holder) {
        String uri;
        if (source != null) {
            uri = source.getString("uri");
        } else {
            uri = null;
        }
        if (uri == null) {
            controllerListener.setIconImageInfo(null);
            controllerListener.setDrawable(null);
        } else if (uri.startsWith("http://") || uri.startsWith("https://") || uri.startsWith("file://")) {
            controllerListener.setIconImageInfo(getIconImageInfo(source));
            holder.setController(((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(Uri.parse(uri)).setControllerListener(controllerListener)).setOldController(holder.getController())).build());
            holder.getTopLevelDrawable().setVisible(true, true);
        } else {
            controllerListener.setDrawable(getDrawableByName(uri));
        }
    }

    private GenericDraweeHierarchy createDraweeHierarchy() {
        return new GenericDraweeHierarchyBuilder(getResources()).setActualImageScaleType(ScalingUtils$ScaleType.FIT_CENTER).setFadeDuration(0).build();
    }

    private int getDrawableResourceByName(String name) {
        return getResources().getIdentifier(name, "drawable", getContext().getPackageName());
    }

    private Drawable getDrawableByName(String name) {
        if (getDrawableResourceByName(name) != 0) {
            return getResources().getDrawable(getDrawableResourceByName(name));
        }
        return null;
    }

    private IconImageInfo getIconImageInfo(ReadableMap source) {
        if (source.hasKey("width") && source.hasKey("height")) {
            return new IconImageInfo(Math.round(PixelUtil.toPixelFromDIP((float) source.getInt("width"))), Math.round(PixelUtil.toPixelFromDIP((float) source.getInt("height"))));
        }
        return null;
    }
}
