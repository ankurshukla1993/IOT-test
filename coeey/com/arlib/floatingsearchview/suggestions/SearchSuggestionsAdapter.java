package com.arlib.floatingsearchview.suggestions;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.arlib.floatingsearchview.C0614R;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.arlib.floatingsearchview.util.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchSuggestionsAdapter extends Adapter<ViewHolder> {
    private static final String TAG = "SearchSuggestionsAdapter";
    private int mBodyTextSizePx;
    private Context mContext;
    private Listener mListener;
    private OnBindSuggestionCallback mOnBindSuggestionCallback;
    private int mRightIconColor = -1;
    private Drawable mRightIconDrawable;
    private List<? extends SearchSuggestion> mSearchSuggestions = new ArrayList();
    private boolean mShowRightMoveUpBtn = false;
    private int mTextColor = -1;

    public interface Listener {
        void onItemSelected(SearchSuggestion searchSuggestion);

        void onMoveItemToSearchClicked(SearchSuggestion searchSuggestion);
    }

    class C06151 implements Listener {
        C06151() {
        }

        public void onItemClicked(int adapterPosition) {
            if (SearchSuggestionsAdapter.this.mListener != null) {
                SearchSuggestionsAdapter.this.mListener.onItemSelected((SearchSuggestion) SearchSuggestionsAdapter.this.mSearchSuggestions.get(adapterPosition));
            }
        }

        public void onMoveItemToSearchClicked(int adapterPosition) {
            if (SearchSuggestionsAdapter.this.mListener != null) {
                SearchSuggestionsAdapter.this.mListener.onMoveItemToSearchClicked((SearchSuggestion) SearchSuggestionsAdapter.this.mSearchSuggestions.get(adapterPosition));
            }
        }
    }

    public interface OnBindSuggestionCallback {
        void onBindSuggestion(View view, ImageView imageView, TextView textView, SearchSuggestion searchSuggestion, int i);
    }

    public static class SearchSuggestionViewHolder extends ViewHolder {
        public TextView body;
        public ImageView leftIcon;
        private Listener mListener;
        public ImageView rightIcon;

        public interface Listener {
            void onItemClicked(int i);

            void onMoveItemToSearchClicked(int i);
        }

        class C06161 implements OnClickListener {
            C06161() {
            }

            public void onClick(View v) {
                int adapterPosition = SearchSuggestionViewHolder.this.getAdapterPosition();
                if (SearchSuggestionViewHolder.this.mListener != null && adapterPosition != -1) {
                    SearchSuggestionViewHolder.this.mListener.onMoveItemToSearchClicked(SearchSuggestionViewHolder.this.getAdapterPosition());
                }
            }
        }

        class C06172 implements OnClickListener {
            C06172() {
            }

            public void onClick(View v) {
                int adapterPosition = SearchSuggestionViewHolder.this.getAdapterPosition();
                if (SearchSuggestionViewHolder.this.mListener != null && adapterPosition != -1) {
                    SearchSuggestionViewHolder.this.mListener.onItemClicked(adapterPosition);
                }
            }
        }

        public SearchSuggestionViewHolder(View v, Listener listener) {
            super(v);
            this.mListener = listener;
            this.body = (TextView) v.findViewById(C0614R.id.body);
            this.leftIcon = (ImageView) v.findViewById(C0614R.id.left_icon);
            this.rightIcon = (ImageView) v.findViewById(C0614R.id.right_icon);
            this.rightIcon.setOnClickListener(new C06161());
            this.itemView.setOnClickListener(new C06172());
        }
    }

    public SearchSuggestionsAdapter(Context context, int suggestionTextSize, Listener listener) {
        this.mContext = context;
        this.mListener = listener;
        this.mBodyTextSizePx = suggestionTextSize;
        this.mRightIconDrawable = Util.getWrappedDrawable(this.mContext, C0614R.drawable.ic_arrow_back_black_24dp);
        DrawableCompat.setTint(this.mRightIconDrawable, Util.getColor(this.mContext, C0614R.color.gray_active_icon));
    }

    public void swapData(List<? extends SearchSuggestion> searchSuggestions) {
        this.mSearchSuggestions = searchSuggestions;
        notifyDataSetChanged();
    }

    public List<? extends SearchSuggestion> getDataSet() {
        return this.mSearchSuggestions;
    }

    public void setOnBindSuggestionCallback(OnBindSuggestionCallback callback) {
        this.mOnBindSuggestionCallback = callback;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        SearchSuggestionViewHolder viewHolder = new SearchSuggestionViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(C0614R.layout.search_suggestion_item, viewGroup, false), new C06151());
        viewHolder.rightIcon.setImageDrawable(this.mRightIconDrawable);
        viewHolder.body.setTextSize(0, (float) this.mBodyTextSizePx);
        return viewHolder;
    }

    public void onBindViewHolder(ViewHolder vh, int position) {
        SearchSuggestionViewHolder viewHolder = (SearchSuggestionViewHolder) vh;
        if (this.mShowRightMoveUpBtn) {
            viewHolder.rightIcon.setEnabled(true);
            viewHolder.rightIcon.setVisibility(0);
        } else {
            viewHolder.rightIcon.setEnabled(false);
            viewHolder.rightIcon.setVisibility(4);
        }
        SearchSuggestion suggestionItem = (SearchSuggestion) this.mSearchSuggestions.get(position);
        viewHolder.body.setText(suggestionItem.getBody());
        if (this.mTextColor != -1) {
            viewHolder.body.setTextColor(this.mTextColor);
        }
        if (this.mRightIconColor != -1) {
            Util.setIconColor(viewHolder.rightIcon, this.mRightIconColor);
        }
        if (this.mOnBindSuggestionCallback != null) {
            this.mOnBindSuggestionCallback.onBindSuggestion(viewHolder.itemView, viewHolder.leftIcon, viewHolder.body, suggestionItem, position);
        }
    }

    public int getItemCount() {
        return this.mSearchSuggestions != null ? this.mSearchSuggestions.size() : 0;
    }

    public void setTextColor(int color) {
        boolean notify = false;
        if (this.mTextColor != color) {
            notify = true;
        }
        this.mTextColor = color;
        if (notify) {
            notifyDataSetChanged();
        }
    }

    public void setRightIconColor(int color) {
        boolean notify = false;
        if (this.mRightIconColor != color) {
            notify = true;
        }
        this.mRightIconColor = color;
        if (notify) {
            notifyDataSetChanged();
        }
    }

    public void setShowMoveUpIcon(boolean show) {
        boolean notify = false;
        if (this.mShowRightMoveUpBtn != show) {
            notify = true;
        }
        this.mShowRightMoveUpBtn = show;
        if (notify) {
            notifyDataSetChanged();
        }
    }

    public void reverseList() {
        Collections.reverse(this.mSearchSuggestions);
        notifyDataSetChanged();
    }
}
