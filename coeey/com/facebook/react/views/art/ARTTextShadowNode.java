package com.facebook.react.views.art;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.text.TextUtils;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.annotations.ReactProp;
import javax.annotation.Nullable;

public class ARTTextShadowNode extends ARTShapeShadowNode {
    private static final int DEFAULT_FONT_SIZE = 12;
    private static final String PROP_FONT = "font";
    private static final String PROP_FONT_FAMILY = "fontFamily";
    private static final String PROP_FONT_SIZE = "fontSize";
    private static final String PROP_FONT_STYLE = "fontStyle";
    private static final String PROP_FONT_WEIGHT = "fontWeight";
    private static final String PROP_LINES = "lines";
    private static final int TEXT_ALIGNMENT_CENTER = 2;
    private static final int TEXT_ALIGNMENT_LEFT = 0;
    private static final int TEXT_ALIGNMENT_RIGHT = 1;
    @Nullable
    private ReadableMap mFrame;
    private int mTextAlignment = 0;

    @ReactProp(name = "frame")
    public void setFrame(@Nullable ReadableMap frame) {
        this.mFrame = frame;
    }

    @ReactProp(defaultInt = 0, name = "alignment")
    public void setAlignment(int alignment) {
        this.mTextAlignment = alignment;
    }

    public void draw(Canvas canvas, Paint paint, float opacity) {
        if (this.mFrame != null) {
            opacity *= this.mOpacity;
            if (opacity > 0.01f && this.mFrame.hasKey(PROP_LINES)) {
                ReadableArray linesProp = this.mFrame.getArray(PROP_LINES);
                if (linesProp != null && linesProp.size() != 0) {
                    saveAndSetupCanvas(canvas);
                    String[] lines = new String[linesProp.size()];
                    for (int i = 0; i < lines.length; i++) {
                        lines[i] = linesProp.getString(i);
                    }
                    String text = TextUtils.join("\n", lines);
                    if (setupStrokePaint(paint, opacity)) {
                        applyTextPropertiesToPaint(paint);
                        if (this.mPath == null) {
                            canvas.drawText(text, 0.0f, -paint.ascent(), paint);
                        } else {
                            canvas.drawTextOnPath(text, this.mPath, 0.0f, 0.0f, paint);
                        }
                    }
                    if (setupFillPaint(paint, opacity)) {
                        applyTextPropertiesToPaint(paint);
                        if (this.mPath == null) {
                            canvas.drawText(text, 0.0f, -paint.ascent(), paint);
                        } else {
                            canvas.drawTextOnPath(text, this.mPath, 0.0f, 0.0f, paint);
                        }
                    }
                    restoreCanvas(canvas);
                    markUpdateSeen();
                }
            }
        }
    }

    private void applyTextPropertiesToPaint(Paint paint) {
        switch (this.mTextAlignment) {
            case 0:
                paint.setTextAlign(Align.LEFT);
                break;
            case 1:
                paint.setTextAlign(Align.RIGHT);
                break;
            case 2:
                paint.setTextAlign(Align.CENTER);
                break;
        }
        if (this.mFrame != null && this.mFrame.hasKey(PROP_FONT)) {
            ReadableMap font = this.mFrame.getMap(PROP_FONT);
            if (font != null) {
                boolean isBold;
                boolean isItalic;
                int fontStyle;
                float fontSize = 12.0f;
                if (font.hasKey("fontSize")) {
                    fontSize = (float) font.getDouble("fontSize");
                }
                paint.setTextSize(this.mScale * fontSize);
                if (font.hasKey("fontWeight") && "bold".equals(font.getString("fontWeight"))) {
                    isBold = true;
                } else {
                    isBold = false;
                }
                if (font.hasKey("fontStyle") && "italic".equals(font.getString("fontStyle"))) {
                    isItalic = true;
                } else {
                    isItalic = false;
                }
                if (isBold && isItalic) {
                    fontStyle = 3;
                } else if (isBold) {
                    fontStyle = 1;
                } else if (isItalic) {
                    fontStyle = 2;
                } else {
                    fontStyle = 0;
                }
                paint.setTypeface(Typeface.create(font.getString("fontFamily"), fontStyle));
            }
        }
    }
}
