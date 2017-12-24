package com.cooey.common.views;

import android.content.Context;
import android.support.constraint.ConstraintLayout.LayoutParams;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cooey.common.C0842R;
import com.cooey.common.stores.StyleStore;
import com.github.thunder413.datetimeutils.DateTimeUtils;
import java.util.Date;

public class TimelineItemView extends FrameLayout {
    private FrameLayout content;
    int contentDefaultTopMargin;
    StyleStore styleStore;
    private long timestamp;

    public TimelineItemView(Context context) {
        this(context, null);
    }

    public TimelineItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimelineItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context, C0842R.layout.layout_timeline_item, this);
        this.content = (FrameLayout) findViewById(C0842R.id.content);
        this.styleStore = new StyleStore(context);
        this.contentDefaultTopMargin = ((LayoutParams) this.content.getLayoutParams()).topMargin;
    }

    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (this.content == null) {
            super.addView(child, index, params);
        } else {
            this.content.addView(child, index, params);
        }
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        try {
            ((TextView) findViewById(C0842R.id.date_text_view)).setText(DateTimeUtils.getTimeAgo(getContext(), new Date(timestamp)));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void showDate(boolean showDate) {
        LinearLayout indicator = (LinearLayout) findViewById(C0842R.id.indication);
        TextView dateTextView = (TextView) findViewById(C0842R.id.date_text_view);
        LayoutParams layoutParams;
        if (showDate) {
            indicator.setVisibility(0);
            dateTextView.setVisibility(0);
            layoutParams = (LayoutParams) this.content.getLayoutParams();
            layoutParams.topMargin = this.contentDefaultTopMargin;
            this.content.setLayoutParams(layoutParams);
        } else {
            indicator.setVisibility(8);
            dateTextView.setVisibility(8);
            layoutParams = (LayoutParams) this.content.getLayoutParams();
            layoutParams.topMargin = 0;
            this.content.setLayoutParams(layoutParams);
        }
        invalidate();
    }
}
