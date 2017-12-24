package chatkit.messages;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import chatkit.commons.Style;
import com.cooey.maya.R;

class MessagesListStyle extends Style {
    private String dateHeaderFormat;
    private int dateHeaderPadding;
    private int dateHeaderTextColor;
    private int dateHeaderTextSize;
    private int incomingAvatarHeight;
    private int incomingAvatarWidth;
    private int incomingBubbleDrawable;
    private int incomingDefaultBubbleColor;
    private int incomingDefaultBubblePaddingBottom;
    private int incomingDefaultBubblePaddingLeft;
    private int incomingDefaultBubblePaddingRight;
    private int incomingDefaultBubblePaddingTop;
    private int incomingDefaultBubblePressedColor;
    private int incomingDefaultBubbleSelectedColor;
    private int incomingTextColor;
    private int incomingTextSize;
    private int incomingTimeTextColor;
    private int incomingTimeTextSize;
    private int outcomingBubbleDrawable;
    private int outcomingDefaultBubbleColor;
    private int outcomingDefaultBubblePaddingBottom;
    private int outcomingDefaultBubblePaddingLeft;
    private int outcomingDefaultBubblePaddingRight;
    private int outcomingDefaultBubblePaddingTop;
    private int outcomingDefaultBubblePressedColor;
    private int outcomingDefaultBubbleSelectedColor;
    private int outcomingTextColor;
    private int outcomingTextSize;
    private int outcomingTimeTextColor;
    private int outcomingTimeTextSize;

    static MessagesListStyle parse(Context context, AttributeSet attrs) {
        MessagesListStyle style = new MessagesListStyle(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MessagesList);
        style.incomingAvatarWidth = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingAvatarWidth, context.getResources().getDimensionPixelSize(R.dimen.message_avatar_width));
        style.incomingAvatarHeight = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingAvatarHeight, context.getResources().getDimensionPixelSize(R.dimen.message_avatar_height));
        style.incomingBubbleDrawable = typedArray.getResourceId(R.styleable.MessagesList_incomingBubbleDrawable, -1);
        style.incomingDefaultBubbleColor = typedArray.getColor(R.styleable.MessagesList_incomingDefaultBubbleColor, ContextCompat.getColor(context, R.color.white_two));
        style.incomingDefaultBubblePressedColor = typedArray.getColor(R.styleable.MessagesList_incomingDefaultBubblePressedColor, ContextCompat.getColor(context, R.color.white_two));
        style.incomingDefaultBubbleSelectedColor = typedArray.getColor(R.styleable.MessagesList_incomingDefaultBubbleSelectedColor, ContextCompat.getColor(context, R.color.cornflower_blue_two_24));
        style.incomingDefaultBubblePaddingLeft = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingBubblePaddingLeft, context.getResources().getDimensionPixelSize(R.dimen.message_padding_left));
        style.incomingDefaultBubblePaddingRight = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingBubblePaddingRight, context.getResources().getDimensionPixelSize(R.dimen.message_padding_right));
        style.incomingDefaultBubblePaddingTop = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingBubblePaddingTop, context.getResources().getDimensionPixelSize(R.dimen.message_padding_top));
        style.incomingDefaultBubblePaddingBottom = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingBubblePaddingBottom, context.getResources().getDimensionPixelSize(R.dimen.message_padding_bottom));
        style.incomingTextColor = typedArray.getColor(R.styleable.MessagesList_incomingTextColor, ContextCompat.getColor(context, R.color.dark_grey_two));
        style.incomingTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingTextSize, context.getResources().getDimensionPixelSize(R.dimen.message_text_size));
        style.incomingTimeTextColor = typedArray.getColor(R.styleable.MessagesList_incomingTimeTextColor, ContextCompat.getColor(context, R.color.warm_grey_four));
        style.incomingTimeTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_incomingTimeTextSize, context.getResources().getDimensionPixelSize(R.dimen.message_time_text_size));
        style.outcomingBubbleDrawable = typedArray.getResourceId(R.styleable.MessagesList_outcomingBubbleDrawable, -1);
        style.outcomingDefaultBubbleColor = typedArray.getColor(R.styleable.MessagesList_outcomingDefaultBubbleColor, ContextCompat.getColor(context, R.color.cornflower_blue_two));
        style.outcomingDefaultBubblePressedColor = typedArray.getColor(R.styleable.MessagesList_outcomingDefaultBubblePressedColor, ContextCompat.getColor(context, R.color.cornflower_blue_two));
        style.outcomingDefaultBubbleSelectedColor = typedArray.getColor(R.styleable.MessagesList_outcomingDefaultBubbleSelectedColor, ContextCompat.getColor(context, R.color.cornflower_blue_two_24));
        style.outcomingDefaultBubblePaddingLeft = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingBubblePaddingLeft, context.getResources().getDimensionPixelSize(R.dimen.message_padding_left));
        style.outcomingDefaultBubblePaddingRight = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingBubblePaddingRight, context.getResources().getDimensionPixelSize(R.dimen.message_padding_right));
        style.outcomingDefaultBubblePaddingTop = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingBubblePaddingTop, context.getResources().getDimensionPixelSize(R.dimen.message_padding_top));
        style.outcomingDefaultBubblePaddingBottom = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingBubblePaddingBottom, context.getResources().getDimensionPixelSize(R.dimen.message_padding_bottom));
        style.outcomingTextColor = typedArray.getColor(R.styleable.MessagesList_outcomingTextColor, ContextCompat.getColor(context, R.color.white));
        style.outcomingTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingTextSize, context.getResources().getDimensionPixelSize(R.dimen.message_text_size));
        style.outcomingTimeTextColor = typedArray.getColor(R.styleable.MessagesList_outcomingTimeTextColor, ContextCompat.getColor(context, R.color.white60));
        style.outcomingTimeTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_outcomingTimeTextSize, context.getResources().getDimensionPixelSize(R.dimen.message_time_text_size));
        style.dateHeaderTextColor = typedArray.getColor(R.styleable.MessagesList_dateHeaderTextColor, ContextCompat.getColor(context, R.color.warm_grey_two));
        style.dateHeaderTextSize = typedArray.getDimensionPixelSize(R.styleable.MessagesList_dateHeaderTextSize, context.getResources().getDimensionPixelSize(R.dimen.message_date_header_text_size));
        style.dateHeaderPadding = typedArray.getDimensionPixelSize(R.styleable.MessagesList_dateHeaderPadding, context.getResources().getDimensionPixelSize(R.dimen.message_date_header_padding));
        style.dateHeaderFormat = typedArray.getString(R.styleable.MessagesList_dateHeaderFormat);
        typedArray.recycle();
        return style;
    }

    MessagesListStyle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    Drawable getMessageSelector(@ColorInt int normalColor, @ColorInt int selectedColor, @ColorInt int pressedColor, @DrawableRes int shape) {
        Drawable button = DrawableCompat.wrap(getVectorDrawable(shape));
        r2 = new int[3][];
        r2[0] = new int[]{16842913};
        r2[1] = new int[]{16842919};
        r2[2] = new int[]{-16842919, -16842913};
        DrawableCompat.setTintList(button, new ColorStateList(r2, new int[]{selectedColor, pressedColor, normalColor}));
        return button;
    }

    int getIncomingAvatarWidth() {
        return this.incomingAvatarWidth;
    }

    int getIncomingAvatarHeight() {
        return this.incomingAvatarHeight;
    }

    int getIncomingDefaultBubblePaddingLeft() {
        return this.incomingDefaultBubblePaddingLeft;
    }

    int getIncomingDefaultBubblePaddingRight() {
        return this.incomingDefaultBubblePaddingRight;
    }

    int getIncomingDefaultBubblePaddingTop() {
        return this.incomingDefaultBubblePaddingTop;
    }

    int getIncomingDefaultBubblePaddingBottom() {
        return this.incomingDefaultBubblePaddingBottom;
    }

    int getIncomingTextColor() {
        return this.incomingTextColor;
    }

    int getIncomingTextSize() {
        return this.incomingTextSize;
    }

    Drawable getOutcomingBubbleDrawable() {
        if (this.outcomingBubbleDrawable == -1) {
            return getMessageSelector(this.outcomingDefaultBubbleColor, this.outcomingDefaultBubbleSelectedColor, this.outcomingDefaultBubblePressedColor, R.drawable.shape_outcoming_message);
        }
        return ContextCompat.getDrawable(this.context, this.outcomingBubbleDrawable);
    }

    int getOutcomingDefaultBubblePaddingLeft() {
        return this.outcomingDefaultBubblePaddingLeft;
    }

    int getOutcomingDefaultBubblePaddingRight() {
        return this.outcomingDefaultBubblePaddingRight;
    }

    int getOutcomingDefaultBubblePaddingTop() {
        return this.outcomingDefaultBubblePaddingTop;
    }

    int getOutcomingDefaultBubblePaddingBottom() {
        return this.outcomingDefaultBubblePaddingBottom;
    }

    int getOutcomingTextColor() {
        return this.outcomingTextColor;
    }

    int getOutcomingTextSize() {
        return this.outcomingTextSize;
    }

    int getDateHeaderTextColor() {
        return this.dateHeaderTextColor;
    }

    int getDateHeaderTextSize() {
        return this.dateHeaderTextSize;
    }

    int getDateHeaderPadding() {
        return this.dateHeaderPadding;
    }

    public String getDateHeaderFormat() {
        return this.dateHeaderFormat;
    }

    int getIncomingTimeTextSize() {
        return this.incomingTimeTextSize;
    }

    int getIncomingTimeTextColor() {
        return this.incomingTimeTextColor;
    }

    int getOutcomingTimeTextColor() {
        return this.outcomingTimeTextColor;
    }

    int getOutcomingTimeTextSize() {
        return this.outcomingTimeTextSize;
    }

    Drawable getIncomingBubbleDrawable() {
        if (this.incomingBubbleDrawable == -1) {
            return getMessageSelector(this.incomingDefaultBubbleColor, this.incomingDefaultBubbleSelectedColor, this.incomingDefaultBubblePressedColor, R.drawable.shape_incoming_message);
        }
        return ContextCompat.getDrawable(this.context, this.incomingBubbleDrawable);
    }
}
