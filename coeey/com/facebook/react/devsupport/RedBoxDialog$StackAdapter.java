package com.facebook.react.devsupport;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.facebook.react.C1275R;
import com.facebook.react.devsupport.StackTraceHelper.StackFrame;

class RedBoxDialog$StackAdapter extends BaseAdapter {
    private static final int VIEW_TYPE_COUNT = 2;
    private static final int VIEW_TYPE_STACKFRAME = 1;
    private static final int VIEW_TYPE_TITLE = 0;
    private final StackFrame[] mStack;
    private final String mTitle;

    private static class FrameViewHolder {
        private final TextView mFileView;
        private final TextView mMethodView;

        private FrameViewHolder(View v) {
            this.mMethodView = (TextView) v.findViewById(C1275R.id.rn_frame_method);
            this.mFileView = (TextView) v.findViewById(C1275R.id.rn_frame_file);
        }
    }

    public RedBoxDialog$StackAdapter(String title, StackFrame[] stack) {
        this.mTitle = title;
        this.mStack = stack;
    }

    public boolean areAllItemsEnabled() {
        return false;
    }

    public boolean isEnabled(int position) {
        return position > 0;
    }

    public int getCount() {
        return this.mStack.length + 1;
    }

    public Object getItem(int position) {
        return position == 0 ? this.mTitle : this.mStack[position - 1];
    }

    public long getItemId(int position) {
        return (long) position;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public int getItemViewType(int position) {
        return position == 0 ? 0 : 1;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (position == 0) {
            TextView title;
            if (convertView != null) {
                title = (TextView) convertView;
            } else {
                title = (TextView) LayoutInflater.from(parent.getContext()).inflate(C1275R.layout.redbox_item_title, parent, false);
            }
            title.setText(this.mTitle);
            return title;
        }
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(C1275R.layout.redbox_item_frame, parent, false);
            convertView.setTag(new FrameViewHolder(convertView));
        }
        StackFrame frame = this.mStack[position - 1];
        FrameViewHolder holder = (FrameViewHolder) convertView.getTag();
        holder.mMethodView.setText(frame.getMethod());
        holder.mFileView.setText(StackTraceHelper.formatFrameSource(frame));
        return convertView;
    }
}
