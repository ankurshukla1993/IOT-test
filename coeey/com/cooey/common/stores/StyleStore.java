package com.cooey.common.stores;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;
import java.util.Map;

public class StyleStore {
    Map<String, Typeface> fontMap = new HashMap();

    public StyleStore(Context context) {
        this.fontMap.put("Bitter-Bold", Typeface.createFromAsset(context.getAssets(), "fonts/Bitter/Bitter-Bold.ttf"));
        this.fontMap.put("Bitter-Italic", Typeface.createFromAsset(context.getAssets(), "fonts/Bitter/Bitter-Italic.ttf"));
        this.fontMap.put("Bitter", Typeface.createFromAsset(context.getAssets(), "fonts/Bitter/Bitter-Regular.ttf"));
        this.fontMap.put("Lato-Black", Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-Black.ttf"));
        this.fontMap.put("Lato-Bold", Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-Bold.ttf"));
        this.fontMap.put("Lato-Light", Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-Light.ttf"));
        this.fontMap.put("Lato", Typeface.createFromAsset(context.getAssets(), "fonts/Lato/Lato-Regular.ttf"));
    }

    public Typeface getFont(String font) {
        return (Typeface) this.fontMap.get(font);
    }
}
