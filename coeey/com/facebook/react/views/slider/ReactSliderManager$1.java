package com.facebook.react.views.slider;

import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;

class ReactSliderManager$1 implements OnSeekBarChangeListener {
    ReactSliderManager$1() {
    }

    public void onProgressChanged(SeekBar seekbar, int progress, boolean fromUser) {
        ((UIManagerModule) ((ReactContext) seekbar.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ReactSliderEvent(seekbar.getId(), ((ReactSlider) seekbar).toRealProgress(progress), fromUser));
    }

    public void onStartTrackingTouch(SeekBar seekbar) {
    }

    public void onStopTrackingTouch(SeekBar seekbar) {
        ((UIManagerModule) ((ReactContext) seekbar.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ReactSlidingCompleteEvent(seekbar.getId(), ((ReactSlider) seekbar).toRealProgress(seekbar.getProgress())));
    }
}
