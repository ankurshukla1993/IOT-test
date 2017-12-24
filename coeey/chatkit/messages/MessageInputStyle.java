package chatkit.messages;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import chatkit.commons.Style;
import com.cooey.maya.R;

class MessageInputStyle extends Style {
    private static final int DEFAULT_MAX_LINES = 5;
    private Drawable inputBackground;
    private Drawable inputButtonBackground;
    private int inputButtonHeight;
    private Drawable inputButtonIcon;
    private int inputButtonMargin;
    private int inputButtonWidth;
    private Drawable inputCursorDrawable;
    private int inputDefaultPaddingBottom;
    private int inputDefaultPaddingLeft;
    private int inputDefaultPaddingRight;
    private int inputDefaultPaddingTop;
    private String inputHint;
    private int inputHintColor;
    private int inputMaxLines;
    private String inputText;
    private int inputTextColor;
    private int inputTextSize;

    static MessageInputStyle parse(Context context, AttributeSet attrs) {
        MessageInputStyle style = new MessageInputStyle(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MessageInput);
        style.inputButtonBackground = typedArray.getDrawable(R.styleable.MessageInput_inputButtonBackground);
        if (style.inputButtonBackground == null) {
            style.inputButtonBackground = ContextCompat.getDrawable(context, R.drawable.selector_bg_send);
        }
        style.inputButtonIcon = typedArray.getDrawable(R.styleable.MessageInput_inputButtonIcon);
        if (style.inputButtonIcon == null) {
            style.inputButtonIcon = ContextCompat.getDrawable(context, R.drawable.selector_icon_send);
        }
        style.inputButtonWidth = typedArray.getDimensionPixelSize(R.styleable.MessageInput_inputButtonWidth, style.getDimension(R.dimen.input_button_width));
        style.inputButtonHeight = typedArray.getDimensionPixelSize(R.styleable.MessageInput_inputButtonHeight, style.getDimension(R.dimen.input_button_height));
        style.inputButtonMargin = typedArray.getDimensionPixelSize(R.styleable.MessageInput_inputButtonMargin, style.getDimension(R.dimen.input_button_margin));
        style.inputMaxLines = typedArray.getInt(R.styleable.MessageInput_inputMaxLines, 5);
        style.inputHint = typedArray.getString(R.styleable.MessageInput_inputHint);
        style.inputText = typedArray.getString(R.styleable.MessageInput_inputText);
        style.inputTextSize = typedArray.getDimensionPixelSize(R.styleable.MessageInput_inputTextSize, style.getDimension(R.dimen.input_text_size));
        style.inputTextColor = typedArray.getColor(R.styleable.MessageInput_inputTextColor, ContextCompat.getColor(context, R.color.dark_grey_two));
        style.inputHintColor = typedArray.getColor(R.styleable.MessageInput_inputHintColor, ContextCompat.getColor(context, R.color.warm_grey_three));
        style.inputBackground = typedArray.getDrawable(R.styleable.MessageInput_inputBackground);
        style.inputCursorDrawable = typedArray.getDrawable(R.styleable.MessageInput_inputCursorDrawable);
        typedArray.recycle();
        style.inputDefaultPaddingLeft = style.getDimension(R.dimen.input_padding_left);
        style.inputDefaultPaddingRight = style.getDimension(R.dimen.input_padding_right);
        style.inputDefaultPaddingTop = style.getDimension(R.dimen.input_padding_top);
        style.inputDefaultPaddingBottom = style.getDimension(R.dimen.input_padding_bottom);
        return style;
    }

    private MessageInputStyle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    Drawable getInputButtonBackground() {
        return this.inputButtonBackground;
    }

    int getInputButtonMargin() {
        return this.inputButtonMargin;
    }

    int getInputButtonWidth() {
        return this.inputButtonWidth;
    }

    int getInputButtonHeight() {
        return this.inputButtonHeight;
    }

    int getInputMaxLines() {
        return this.inputMaxLines;
    }

    String getInputHint() {
        return this.inputHint;
    }

    String getInputText() {
        return this.inputText;
    }

    int getInputTextSize() {
        return this.inputTextSize;
    }

    int getInputTextColor() {
        return this.inputTextColor;
    }

    int getInputHintColor() {
        return this.inputHintColor;
    }

    Drawable getInputBackground() {
        return this.inputBackground;
    }

    Drawable getInputCursorDrawable() {
        return this.inputCursorDrawable;
    }

    int getInputDefaultPaddingLeft() {
        return this.inputDefaultPaddingLeft;
    }

    int getInputDefaultPaddingRight() {
        return this.inputDefaultPaddingRight;
    }

    int getInputDefaultPaddingTop() {
        return this.inputDefaultPaddingTop;
    }

    int getInputDefaultPaddingBottom() {
        return this.inputDefaultPaddingBottom;
    }

    Drawable getInputButtonIcon() {
        return this.inputButtonIcon;
    }
}
