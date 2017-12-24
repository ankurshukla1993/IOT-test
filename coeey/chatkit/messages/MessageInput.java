package chatkit.messages;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.Space;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cooey.maya.R;
import java.lang.reflect.Field;

public class MessageInput extends RelativeLayout implements OnClickListener, TextWatcher {
    protected Space buttonSpace;
    private CharSequence input;
    private InputListener inputListener;
    protected EditText messageInput;
    protected ImageButton messageSendButton;

    public interface InputListener {
        boolean onSubmit(CharSequence charSequence);
    }

    public MessageInput(Context context) {
        super(context);
        init(context);
    }

    public MessageInput(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MessageInput(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setInputListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    public EditText getInputEditText() {
        return this.messageInput;
    }

    public ImageButton getButton() {
        return this.messageSendButton;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.messageSendButton && onSubmit()) {
            this.messageInput.setText("");
        }
    }

    public void onTextChanged(CharSequence s, int start, int count, int after) {
        this.input = s;
        this.messageSendButton.setEnabled(this.input.length() > 0);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    public void afterTextChanged(Editable editable) {
    }

    private boolean onSubmit() {
        return this.inputListener != null && this.inputListener.onSubmit(this.input);
    }

    private void init(Context context, AttributeSet attrs) {
        init(context);
        MessageInputStyle style = MessageInputStyle.parse(context, attrs);
        this.messageInput.setMaxLines(style.getInputMaxLines());
        this.messageInput.setHint(style.getInputHint());
        this.messageInput.setText(style.getInputText());
        this.messageInput.setTextSize(0, (float) style.getInputTextSize());
        this.messageInput.setTextColor(style.getInputTextColor());
        this.messageInput.setHintTextColor(style.getInputHintColor());
        this.messageInput.setBackground(style.getInputBackground());
        setCursor(style.getInputCursorDrawable());
        this.messageSendButton.setBackground(style.getInputButtonBackground());
        this.messageSendButton.setImageDrawable(style.getInputButtonIcon());
        this.messageSendButton.getLayoutParams().width = style.getInputButtonWidth();
        this.messageSendButton.getLayoutParams().height = style.getInputButtonHeight();
        this.buttonSpace.getLayoutParams().width = style.getInputButtonMargin();
        if (getPaddingLeft() == 0 && getPaddingRight() == 0 && getPaddingTop() == 0 && getPaddingBottom() == 0) {
            setPadding(style.getInputDefaultPaddingLeft(), style.getInputDefaultPaddingTop(), style.getInputDefaultPaddingRight(), style.getInputDefaultPaddingBottom());
        }
    }

    private void init(Context context) {
        inflate(context, R.layout.view_message_input, this);
        this.messageInput = (EditText) findViewById(R.id.messageInput);
        this.messageSendButton = (ImageButton) findViewById(R.id.messageSendButton);
        this.buttonSpace = (Space) findViewById(R.id.buttonSpace);
        this.messageSendButton.setOnClickListener(this);
        this.messageInput.addTextChangedListener(this);
        this.messageInput.setText("");
    }

    private void setCursor(Drawable drawable) {
        try {
            Field f = TextView.class.getDeclaredField("mCursorDrawableRes");
            f.setAccessible(true);
            f.set(this.messageInput, drawable);
        } catch (Exception e) {
        }
    }
}
