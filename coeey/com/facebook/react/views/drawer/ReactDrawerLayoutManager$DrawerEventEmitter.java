package com.facebook.react.views.drawer;

import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.DrawerLayout.DrawerListener;
import android.view.View;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.drawer.events.DrawerClosedEvent;
import com.facebook.react.views.drawer.events.DrawerOpenedEvent;
import com.facebook.react.views.drawer.events.DrawerSlideEvent;
import com.facebook.react.views.drawer.events.DrawerStateChangedEvent;

public class ReactDrawerLayoutManager$DrawerEventEmitter implements DrawerListener {
    private final DrawerLayout mDrawerLayout;
    private final EventDispatcher mEventDispatcher;

    public ReactDrawerLayoutManager$DrawerEventEmitter(DrawerLayout drawerLayout, EventDispatcher eventDispatcher) {
        this.mDrawerLayout = drawerLayout;
        this.mEventDispatcher = eventDispatcher;
    }

    public void onDrawerSlide(View view, float v) {
        this.mEventDispatcher.dispatchEvent(new DrawerSlideEvent(this.mDrawerLayout.getId(), v));
    }

    public void onDrawerOpened(View view) {
        this.mEventDispatcher.dispatchEvent(new DrawerOpenedEvent(this.mDrawerLayout.getId()));
    }

    public void onDrawerClosed(View view) {
        this.mEventDispatcher.dispatchEvent(new DrawerClosedEvent(this.mDrawerLayout.getId()));
    }

    public void onDrawerStateChanged(int i) {
        this.mEventDispatcher.dispatchEvent(new DrawerStateChangedEvent(this.mDrawerLayout.getId(), i));
    }
}
