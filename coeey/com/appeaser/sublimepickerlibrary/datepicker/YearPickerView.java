package com.appeaser.sublimepickerlibrary.datepicker;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.appeaser.sublimepickerlibrary.C0563R;
import com.appeaser.sublimepickerlibrary.utilities.SUtils;
import java.util.Calendar;

public class YearPickerView extends ListView {
    private YearAdapter mAdapter;
    private int mChildSize;
    private OnYearSelectedListener mOnYearSelectedListener;
    private int mViewSize;

    public interface OnYearSelectedListener {
        void onYearChanged(YearPickerView yearPickerView, int i);
    }

    class C05821 implements OnItemClickListener {
        C05821() {
        }

        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
            int year = YearPickerView.this.mAdapter.getYearForPosition(position);
            YearPickerView.this.mAdapter.setSelection(year);
            if (YearPickerView.this.mOnYearSelectedListener != null) {
                YearPickerView.this.mOnYearSelectedListener.onYearChanged(YearPickerView.this, year);
            }
        }
    }

    private static class YearAdapter extends BaseAdapter {
        private static final int ITEM_LAYOUT = C0563R.layout.year_label_text_view;
        private static final int ITEM_TEXT_ACTIVATED_APPEARANCE = C0563R.style.SPYearLabelActivatedTextAppearance;
        private static final int ITEM_TEXT_APPEARANCE = C0563R.style.SPYearLabelTextAppearance;
        private int mActivatedYear;
        private final Context mContext;
        private int mCount;
        private final LayoutInflater mInflater;
        private int mMinYear;

        public YearAdapter(Context context) {
            this.mContext = context;
            this.mInflater = LayoutInflater.from(context);
        }

        public void setRange(Calendar minDate, Calendar maxDate) {
            int minYear = minDate.get(1);
            int count = (maxDate.get(1) - minYear) + 1;
            if (this.mMinYear != minYear || this.mCount != count) {
                this.mMinYear = minYear;
                this.mCount = count;
                notifyDataSetInvalidated();
            }
        }

        public boolean setSelection(int year) {
            if (this.mActivatedYear == year) {
                return false;
            }
            this.mActivatedYear = year;
            notifyDataSetChanged();
            return true;
        }

        public int getCount() {
            return this.mCount;
        }

        public Integer getItem(int position) {
            return Integer.valueOf(getYearForPosition(position));
        }

        public long getItemId(int position) {
            return (long) getYearForPosition(position);
        }

        public int getPositionForYear(int year) {
            return year - this.mMinYear;
        }

        public int getYearForPosition(int position) {
            return this.mMinYear + position;
        }

        public boolean hasStableIds() {
            return true;
        }

        @SuppressLint({"SetTextI18n"})
        public View getView(int position, View convertView, ViewGroup parent) {
            boolean hasNewView;
            TextView v;
            boolean activated = true;
            if (convertView == null) {
                hasNewView = true;
            } else {
                hasNewView = false;
            }
            if (hasNewView) {
                v = (TextView) this.mInflater.inflate(ITEM_LAYOUT, parent, false);
            } else {
                v = (TextView) convertView;
            }
            int year = getYearForPosition(position);
            if (this.mActivatedYear != year) {
                activated = false;
            }
            if (hasNewView || v.isActivated() != activated) {
                int textAppearanceResId;
                if (!activated || ITEM_TEXT_ACTIVATED_APPEARANCE == 0) {
                    textAppearanceResId = ITEM_TEXT_APPEARANCE;
                } else {
                    textAppearanceResId = ITEM_TEXT_ACTIVATED_APPEARANCE;
                }
                if (SUtils.isApi_23_OrHigher()) {
                    v.setTextAppearance(textAppearanceResId);
                } else {
                    v.setTextAppearance(this.mContext, textAppearanceResId);
                }
                v.setActivated(activated);
            }
            v.setText(Integer.toString(year));
            return v;
        }

        public int getItemViewType(int position) {
            return 0;
        }

        public int getViewTypeCount() {
            return 1;
        }

        public boolean isEmpty() {
            return false;
        }

        public boolean areAllItemsEnabled() {
            return true;
        }

        public boolean isEnabled(int position) {
            return true;
        }
    }

    public YearPickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 16842868);
    }

    public YearPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(21)
    public YearPickerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        setLayoutParams(new LayoutParams(-1, -2));
        Resources res = getContext().getResources();
        this.mViewSize = res.getDimensionPixelOffset(C0563R.dimen.datepicker_view_animator_height);
        this.mChildSize = res.getDimensionPixelOffset(C0563R.dimen.datepicker_year_label_height);
        setOnItemClickListener(new C05821());
        this.mAdapter = new YearAdapter(getContext());
        setAdapter(this.mAdapter);
    }

    public void setOnYearSelectedListener(OnYearSelectedListener listener) {
        this.mOnYearSelectedListener = listener;
    }

    public void setYear(final int year) {
        this.mAdapter.setSelection(year);
        post(new Runnable() {
            public void run() {
                int position = YearPickerView.this.mAdapter.getPositionForYear(year);
                if (position >= 0 && position < YearPickerView.this.getCount()) {
                    YearPickerView.this.setSelectionCentered(position);
                }
            }
        });
    }

    private void setSelectionCentered(int position) {
        setSelectionFromTop(position, (this.mViewSize / 2) - (this.mChildSize / 2));
    }

    public void setRange(Calendar min, Calendar max) {
        this.mAdapter.setRange(min, max);
    }

    public int getFirstPositionOffset() {
        View firstChild = getChildAt(0);
        if (firstChild == null) {
            return 0;
        }
        return firstChild.getTop();
    }
}
