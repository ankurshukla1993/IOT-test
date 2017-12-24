package com.facebook.react.views.picker;

import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.views.picker.events.PickerItemSelectEvent;

class ReactPickerManager$PickerEventEmitter implements ReactPicker$OnSelectListener {
    private final EventDispatcher mEventDispatcher;
    private final ReactPicker mReactPicker;

    public ReactPickerManager$PickerEventEmitter(ReactPicker reactPicker, EventDispatcher eventDispatcher) {
        this.mReactPicker = reactPicker;
        this.mEventDispatcher = eventDispatcher;
    }

    public void onItemSelected(int position) {
        this.mEventDispatcher.dispatchEvent(new PickerItemSelectEvent(this.mReactPicker.getId(), position));
    }
}
