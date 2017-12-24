package chatkit.dialogs;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import chatkit.commons.Style;
import com.cooey.maya.R;

class DialogListStyle extends Style {
    private int dialogAvatarHeight;
    private int dialogAvatarWidth;
    private int dialogDateColor;
    private int dialogDateSize;
    private int dialogDateStyle;
    private int dialogDividerColor;
    private boolean dialogDividerEnabled;
    private int dialogDividerLeftPadding;
    private int dialogDividerRightPadding;
    private int dialogItemBackground;
    private boolean dialogMessageAvatarEnabled;
    private int dialogMessageAvatarHeight;
    private int dialogMessageAvatarWidth;
    private int dialogMessageTextColor;
    private int dialogMessageTextSize;
    private int dialogMessageTextStyle;
    private int dialogTitleTextColor;
    private int dialogTitleTextSize;
    private int dialogTitleTextStyle;
    private int dialogUnreadBubbleBackgroundColor;
    private boolean dialogUnreadBubbleEnabled;
    private int dialogUnreadBubbleTextColor;
    private int dialogUnreadBubbleTextSize;
    private int dialogUnreadBubbleTextStyle;
    private int dialogUnreadDateColor;
    private int dialogUnreadDateStyle;
    private int dialogUnreadItemBackground;
    private int dialogUnreadMessageTextColor;
    private int dialogUnreadMessageTextStyle;
    private int dialogUnreadTitleTextColor;
    private int dialogUnreadTitleTextStyle;

    static DialogListStyle parse(Context context, AttributeSet attrs) {
        DialogListStyle dialogStyle = new DialogListStyle(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.DialogsList);
        dialogStyle.setDialogItemBackground(typedArray.getColor(R.styleable.DialogsList_dialogItemBackground, ContextCompat.getColor(context, R.color.transparent)));
        dialogStyle.setDialogUnreadItemBackground(typedArray.getColor(R.styleable.DialogsList_dialogUnreadItemBackground, ContextCompat.getColor(context, R.color.transparent)));
        dialogStyle.setDialogTitleTextColor(typedArray.getColor(R.styleable.DialogsList_dialogTitleTextColor, ContextCompat.getColor(context, R.color.dialog_title_text)));
        dialogStyle.setDialogTitleTextSize(typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogTitleTextSize, context.getResources().getDimensionPixelSize(R.dimen.dialog_title_text_size)));
        dialogStyle.setDialogTitleTextStyle(typedArray.getInt(R.styleable.DialogsList_dialogTitleTextStyle, 0));
        dialogStyle.setDialogUnreadTitleTextColor(typedArray.getColor(R.styleable.DialogsList_dialogUnreadTitleTextColor, ContextCompat.getColor(context, R.color.dialog_title_text)));
        dialogStyle.setDialogUnreadTitleTextStyle(typedArray.getInt(R.styleable.DialogsList_dialogUnreadTitleTextStyle, 0));
        dialogStyle.setDialogMessageTextColor(typedArray.getColor(R.styleable.DialogsList_dialogMessageTextColor, ContextCompat.getColor(context, R.color.dialog_message_text)));
        dialogStyle.setDialogMessageTextSize(typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogMessageTextSize, context.getResources().getDimensionPixelSize(R.dimen.dialog_message_text_size)));
        dialogStyle.setDialogMessageTextStyle(typedArray.getInt(R.styleable.DialogsList_dialogMessageTextStyle, 0));
        dialogStyle.setDialogUnreadMessageTextColor(typedArray.getColor(R.styleable.DialogsList_dialogUnreadMessageTextColor, ContextCompat.getColor(context, R.color.dialog_message_text)));
        dialogStyle.setDialogUnreadMessageTextStyle(typedArray.getInt(R.styleable.DialogsList_dialogUnreadMessageTextStyle, 0));
        dialogStyle.setDialogDateColor(typedArray.getColor(R.styleable.DialogsList_dialogDateColor, ContextCompat.getColor(context, R.color.dialog_date_text)));
        dialogStyle.setDialogDateSize(typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogDateSize, context.getResources().getDimensionPixelSize(R.dimen.dialog_date_text_size)));
        dialogStyle.setDialogDateStyle(typedArray.getInt(R.styleable.DialogsList_dialogDateStyle, 0));
        dialogStyle.setDialogUnreadDateColor(typedArray.getColor(R.styleable.DialogsList_dialogUnreadDateColor, ContextCompat.getColor(context, R.color.dialog_date_text)));
        dialogStyle.setDialogUnreadDateStyle(typedArray.getInt(R.styleable.DialogsList_dialogUnreadDateStyle, 0));
        dialogStyle.setDialogUnreadBubbleEnabled(typedArray.getBoolean(R.styleable.DialogsList_dialogUnreadBubbleEnabled, true));
        dialogStyle.setDialogUnreadBubbleBackgroundColor(typedArray.getColor(R.styleable.DialogsList_dialogUnreadBubbleBackgroundColor, ContextCompat.getColor(context, R.color.dialog_unread_bubble)));
        dialogStyle.setDialogUnreadBubbleTextColor(typedArray.getColor(R.styleable.DialogsList_dialogUnreadBubbleTextColor, ContextCompat.getColor(context, R.color.dialog_unread_text)));
        dialogStyle.setDialogUnreadBubbleTextSize(typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogUnreadBubbleTextSize, context.getResources().getDimensionPixelSize(R.dimen.dialog_unread_bubble_text_size)));
        dialogStyle.setDialogUnreadBubbleTextStyle(typedArray.getInt(R.styleable.DialogsList_dialogUnreadBubbleTextStyle, 0));
        dialogStyle.setDialogAvatarWidth(typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogAvatarWidth, context.getResources().getDimensionPixelSize(R.dimen.dialog_avatar_width)));
        dialogStyle.setDialogAvatarHeight(typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogAvatarHeight, context.getResources().getDimensionPixelSize(R.dimen.dialog_avatar_height)));
        dialogStyle.setDialogMessageAvatarEnabled(typedArray.getBoolean(R.styleable.DialogsList_dialogMessageAvatarEnabled, true));
        dialogStyle.setDialogMessageAvatarWidth(typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogMessageAvatarWidth, context.getResources().getDimensionPixelSize(R.dimen.dialog_last_message_avatar_width)));
        dialogStyle.setDialogMessageAvatarHeight(typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogMessageAvatarHeight, context.getResources().getDimensionPixelSize(R.dimen.dialog_last_message_avatar_height)));
        dialogStyle.setDialogDividerEnabled(typedArray.getBoolean(R.styleable.DialogsList_dialogDividerEnabled, true));
        dialogStyle.setDialogDividerColor(typedArray.getColor(R.styleable.DialogsList_dialogDividerColor, ContextCompat.getColor(context, R.color.dialog_divider)));
        dialogStyle.setDialogDividerLeftPadding(typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogDividerLeftPadding, context.getResources().getDimensionPixelSize(R.dimen.dialog_divider_margin_left)));
        dialogStyle.setDialogDividerRightPadding(typedArray.getDimensionPixelSize(R.styleable.DialogsList_dialogDividerRightPadding, context.getResources().getDimensionPixelSize(R.dimen.dialog_divider_margin_right)));
        typedArray.recycle();
        return dialogStyle;
    }

    private DialogListStyle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    int getDialogTitleTextColor() {
        return this.dialogTitleTextColor;
    }

    void setDialogTitleTextColor(int dialogTitleTextColor) {
        this.dialogTitleTextColor = dialogTitleTextColor;
    }

    int getDialogTitleTextSize() {
        return this.dialogTitleTextSize;
    }

    void setDialogTitleTextSize(int dialogTitleTextSize) {
        this.dialogTitleTextSize = dialogTitleTextSize;
    }

    int getDialogTitleTextStyle() {
        return this.dialogTitleTextStyle;
    }

    void setDialogTitleTextStyle(int dialogTitleTextStyle) {
        this.dialogTitleTextStyle = dialogTitleTextStyle;
    }

    int getDialogUnreadTitleTextColor() {
        return this.dialogUnreadTitleTextColor;
    }

    void setDialogUnreadTitleTextColor(int dialogUnreadTitleTextColor) {
        this.dialogUnreadTitleTextColor = dialogUnreadTitleTextColor;
    }

    int getDialogUnreadTitleTextStyle() {
        return this.dialogUnreadTitleTextStyle;
    }

    void setDialogUnreadTitleTextStyle(int dialogUnreadTitleTextStyle) {
        this.dialogUnreadTitleTextStyle = dialogUnreadTitleTextStyle;
    }

    int getDialogMessageTextColor() {
        return this.dialogMessageTextColor;
    }

    void setDialogMessageTextColor(int dialogMessageTextColor) {
        this.dialogMessageTextColor = dialogMessageTextColor;
    }

    int getDialogMessageTextSize() {
        return this.dialogMessageTextSize;
    }

    void setDialogMessageTextSize(int dialogMessageTextSize) {
        this.dialogMessageTextSize = dialogMessageTextSize;
    }

    int getDialogMessageTextStyle() {
        return this.dialogMessageTextStyle;
    }

    void setDialogMessageTextStyle(int dialogMessageTextStyle) {
        this.dialogMessageTextStyle = dialogMessageTextStyle;
    }

    int getDialogUnreadMessageTextColor() {
        return this.dialogUnreadMessageTextColor;
    }

    void setDialogUnreadMessageTextColor(int dialogUnreadMessageTextColor) {
        this.dialogUnreadMessageTextColor = dialogUnreadMessageTextColor;
    }

    int getDialogUnreadMessageTextStyle() {
        return this.dialogUnreadMessageTextStyle;
    }

    void setDialogUnreadMessageTextStyle(int dialogUnreadMessageTextStyle) {
        this.dialogUnreadMessageTextStyle = dialogUnreadMessageTextStyle;
    }

    int getDialogDateColor() {
        return this.dialogDateColor;
    }

    void setDialogDateColor(int dialogDateColor) {
        this.dialogDateColor = dialogDateColor;
    }

    int getDialogDateSize() {
        return this.dialogDateSize;
    }

    void setDialogDateSize(int dialogDateSize) {
        this.dialogDateSize = dialogDateSize;
    }

    int getDialogDateStyle() {
        return this.dialogDateStyle;
    }

    void setDialogDateStyle(int dialogDateStyle) {
        this.dialogDateStyle = dialogDateStyle;
    }

    int getDialogUnreadDateColor() {
        return this.dialogUnreadDateColor;
    }

    void setDialogUnreadDateColor(int dialogUnreadDateColor) {
        this.dialogUnreadDateColor = dialogUnreadDateColor;
    }

    int getDialogUnreadDateStyle() {
        return this.dialogUnreadDateStyle;
    }

    void setDialogUnreadDateStyle(int dialogUnreadDateStyle) {
        this.dialogUnreadDateStyle = dialogUnreadDateStyle;
    }

    boolean isDialogUnreadBubbleEnabled() {
        return this.dialogUnreadBubbleEnabled;
    }

    void setDialogUnreadBubbleEnabled(boolean dialogUnreadBubbleEnabled) {
        this.dialogUnreadBubbleEnabled = dialogUnreadBubbleEnabled;
    }

    int getDialogUnreadBubbleTextColor() {
        return this.dialogUnreadBubbleTextColor;
    }

    void setDialogUnreadBubbleTextColor(int dialogUnreadBubbleTextColor) {
        this.dialogUnreadBubbleTextColor = dialogUnreadBubbleTextColor;
    }

    int getDialogUnreadBubbleTextSize() {
        return this.dialogUnreadBubbleTextSize;
    }

    void setDialogUnreadBubbleTextSize(int dialogUnreadBubbleTextSize) {
        this.dialogUnreadBubbleTextSize = dialogUnreadBubbleTextSize;
    }

    int getDialogUnreadBubbleTextStyle() {
        return this.dialogUnreadBubbleTextStyle;
    }

    void setDialogUnreadBubbleTextStyle(int dialogUnreadBubbleTextStyle) {
        this.dialogUnreadBubbleTextStyle = dialogUnreadBubbleTextStyle;
    }

    int getDialogUnreadBubbleBackgroundColor() {
        return this.dialogUnreadBubbleBackgroundColor;
    }

    void setDialogUnreadBubbleBackgroundColor(int dialogUnreadBubbleBackgroundColor) {
        this.dialogUnreadBubbleBackgroundColor = dialogUnreadBubbleBackgroundColor;
    }

    int getDialogAvatarWidth() {
        return this.dialogAvatarWidth;
    }

    void setDialogAvatarWidth(int dialogAvatarWidth) {
        this.dialogAvatarWidth = dialogAvatarWidth;
    }

    int getDialogAvatarHeight() {
        return this.dialogAvatarHeight;
    }

    void setDialogAvatarHeight(int dialogAvatarHeight) {
        this.dialogAvatarHeight = dialogAvatarHeight;
    }

    boolean isDialogDividerEnabled() {
        return this.dialogDividerEnabled;
    }

    void setDialogDividerEnabled(boolean dialogDividerEnabled) {
        this.dialogDividerEnabled = dialogDividerEnabled;
    }

    int getDialogDividerColor() {
        return this.dialogDividerColor;
    }

    void setDialogDividerColor(int dialogDividerColor) {
        this.dialogDividerColor = dialogDividerColor;
    }

    int getDialogDividerLeftPadding() {
        return this.dialogDividerLeftPadding;
    }

    void setDialogDividerLeftPadding(int dialogDividerLeftMargin) {
        this.dialogDividerLeftPadding = dialogDividerLeftMargin;
    }

    int getDialogDividerRightPadding() {
        return this.dialogDividerRightPadding;
    }

    void setDialogDividerRightPadding(int dialogDividerRightMargin) {
        this.dialogDividerRightPadding = dialogDividerRightMargin;
    }

    int getDialogItemBackground() {
        return this.dialogItemBackground;
    }

    void setDialogItemBackground(int dialogItemBackground) {
        this.dialogItemBackground = dialogItemBackground;
    }

    int getDialogUnreadItemBackground() {
        return this.dialogUnreadItemBackground;
    }

    void setDialogUnreadItemBackground(int dialogUnreadItemBackground) {
        this.dialogUnreadItemBackground = dialogUnreadItemBackground;
    }

    void setDialogMessageAvatarEnabled(boolean dialogMessageAvatarEnabled) {
        this.dialogMessageAvatarEnabled = dialogMessageAvatarEnabled;
    }

    boolean isDialogMessageAvatarEnabled() {
        return this.dialogMessageAvatarEnabled;
    }

    int getDialogMessageAvatarWidth() {
        return this.dialogMessageAvatarWidth;
    }

    void setDialogMessageAvatarWidth(int dialogMessageAvatarWidth) {
        this.dialogMessageAvatarWidth = dialogMessageAvatarWidth;
    }

    int getDialogMessageAvatarHeight() {
        return this.dialogMessageAvatarHeight;
    }

    void setDialogMessageAvatarHeight(int dialogMessageAvatarHeight) {
        this.dialogMessageAvatarHeight = dialogMessageAvatarHeight;
    }
}
