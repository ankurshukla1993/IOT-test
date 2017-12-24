package com.arlib.floatingsearchview.util.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.EditText;

public class SearchInputView extends EditText {
    private OnKeyListener mOnKeyListener = new C06281();
    private OnKeyboardDismissedListener mOnKeyboardDismissedListener;
    private OnKeyboardSearchKeyClickListener mSearchKeyListener;

    public interface OnKeyboardDismissedListener {
        void onKeyboardDismissed();
    }

    public interface OnKeyboardSearchKeyClickListener {
        void onSearchKeyClicked();
    }

    class C06281 implements OnKeyListener {
        C06281() {
        }

        public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
            if (keyCode != 66 || SearchInputView.this.mSearchKeyListener == null) {
                return false;
            }
            SearchInputView.this.mSearchKeyListener.onSearchKeyClicked();
            return true;
        }
    }

    public SearchInputView(Context context) {
        super(context);
        init();
    }

    public SearchInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SearchInputView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setOnKeyListener(this.mOnKeyListener);
    }

    public boolean onKeyPreIme(int keyCode, KeyEvent ev) {
        if (ev.getKeyCode() == 4 && this.mOnKeyboardDismissedListener != null) {
            this.mOnKeyboardDismissedListener.onKeyboardDismissed();
        }
        return super.onKeyPreIme(keyCode, ev);
    }

    public void setOnKeyboardDismissedListener(OnKeyboardDismissedListener onKeyboardDismissedListener) {
        this.mOnKeyboardDismissedListener = onKeyboardDismissedListener;
    }

    public void setOnSearchKeyListener(OnKeyboardSearchKeyClickListener searchKeyListener) {
        this.mSearchKeyListener = searchKeyListener;
    }
}
