package com.facebook.react.views.picker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

public abstract class ReactPickerManager extends SimpleViewManager<ReactPicker> {

    private static class ReactPickerAdapter extends ArrayAdapter<ReadableMap> {
        private final LayoutInflater mInflater;
        @Nullable
        private Integer mPrimaryTextColor;

        public ReactPickerAdapter(Context context, ReadableMap[] data) {
            super(context, 0, data);
            this.mInflater = (LayoutInflater) Assertions.assertNotNull(context.getSystemService("layout_inflater"));
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent, false);
        }

        public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return getView(position, convertView, parent, true);
        }

        private View getView(int position, View convertView, ViewGroup parent, boolean isDropdown) {
            ReadableMap item = (ReadableMap) getItem(position);
            if (convertView == null) {
                convertView = this.mInflater.inflate(isDropdown ? 17367049 : 17367048, parent, false);
            }
            TextView textView = (TextView) convertView;
            textView.setText(item.getString("label"));
            if (!isDropdown && this.mPrimaryTextColor != null) {
                textView.setTextColor(this.mPrimaryTextColor.intValue());
            } else if (item.hasKey(ViewProps.COLOR) && !item.isNull(ViewProps.COLOR)) {
                textView.setTextColor(item.getInt(ViewProps.COLOR));
            }
            return convertView;
        }

        public void setPrimaryTextColor(@Nullable Integer primaryTextColor) {
            this.mPrimaryTextColor = primaryTextColor;
            notifyDataSetChanged();
        }
    }

    @ReactProp(name = "items")
    public void setItems(ReactPicker view, @Nullable ReadableArray items) {
        if (items != null) {
            ReadableMap[] data = new ReadableMap[items.size()];
            for (int i = 0; i < items.size(); i++) {
                data[i] = items.getMap(i);
            }
            ReactPickerAdapter adapter = new ReactPickerAdapter(view.getContext(), data);
            adapter.setPrimaryTextColor(view.getPrimaryColor());
            view.setAdapter(adapter);
            return;
        }
        view.setAdapter(null);
    }

    @ReactProp(customType = "Color", name = "color")
    public void setColor(ReactPicker view, @Nullable Integer color) {
        view.setPrimaryColor(color);
        ReactPickerAdapter adapter = (ReactPickerAdapter) view.getAdapter();
        if (adapter != null) {
            adapter.setPrimaryTextColor(color);
        }
    }

    @ReactProp(name = "prompt")
    public void setPrompt(ReactPicker view, @Nullable String prompt) {
        view.setPrompt(prompt);
    }

    @ReactProp(defaultBoolean = true, name = "enabled")
    public void setEnabled(ReactPicker view, boolean enabled) {
        view.setEnabled(enabled);
    }

    @ReactProp(name = "selected")
    public void setSelected(ReactPicker view, int selected) {
        view.setStagedSelection(selected);
    }

    protected void onAfterUpdateTransaction(ReactPicker view) {
        super.onAfterUpdateTransaction(view);
        view.updateStagedSelection();
    }

    protected void addEventEmitters(ThemedReactContext reactContext, ReactPicker picker) {
        picker.setOnSelectListener(new PickerEventEmitter(picker, ((UIManagerModule) reactContext.getNativeModule(UIManagerModule.class)).getEventDispatcher()));
    }
}
