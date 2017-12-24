package com.cooey.android.users;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.cooey.android.users.databinding.LayoutPeopleSelectorViewBinding;

public class PeopleSelectorView extends FrameLayout {
    private String patientId;
    private UserSelectedCallback userSelectedCallback;

    public PeopleSelectorView(@NonNull Context context) {
        super(context);
    }

    public void initialize(String patientId, UserSelectedCallback userSelectedCallback) {
        this.userSelectedCallback = userSelectedCallback;
        this.patientId = patientId;
        initializeView();
    }

    public PeopleSelectorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PeopleSelectorView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = 21)
    public PeopleSelectorView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr, @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    void initializeView() {
        setLayoutParams(new LayoutParams(-1, -1));
        LayoutPeopleSelectorViewBinding layoutPeopleSelectorViewBinding = LayoutPeopleSelectorViewBinding.inflate(LayoutInflater.from(getContext()));
        layoutPeopleSelectorViewBinding.setPeopleSelectorViewModel(new PeopleSelectorViewModel(getContext(), this.patientId, this.userSelectedCallback));
        addView(layoutPeopleSelectorViewBinding.getRoot());
    }
}
