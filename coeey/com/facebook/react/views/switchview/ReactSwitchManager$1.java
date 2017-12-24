package com.facebook.react.views.switchview;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;

class ReactSwitchManager$1 implements OnCheckedChangeListener {
    ReactSwitchManager$1() {
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ((UIManagerModule) ((ReactContext) buttonView.getContext()).getNativeModule(UIManagerModule.class)).getEventDispatcher().dispatchEvent(new ReactSwitchEvent(buttonView.getId(), isChecked));
    }
}
