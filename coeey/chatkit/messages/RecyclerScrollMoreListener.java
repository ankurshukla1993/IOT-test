package chatkit.messages;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.StaggeredGridLayoutManager;

class RecyclerScrollMoreListener extends OnScrollListener {
    private int currentPage = 0;
    private OnLoadMoreListener loadMoreListener;
    private boolean loading = true;
    private LayoutManager mLayoutManager;
    private int previousTotalItemCount = 0;

    interface OnLoadMoreListener {
        void onLoadMore(int i, int i2);
    }

    RecyclerScrollMoreListener(LinearLayoutManager layoutManager, OnLoadMoreListener loadMoreListener) {
        this.mLayoutManager = layoutManager;
        this.loadMoreListener = loadMoreListener;
    }

    private int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    public void onScrolled(RecyclerView view, int dx, int dy) {
        if (this.loadMoreListener != null) {
            int lastVisibleItemPosition = 0;
            int totalItemCount = this.mLayoutManager.getItemCount();
            if (this.mLayoutManager instanceof StaggeredGridLayoutManager) {
                lastVisibleItemPosition = getLastVisibleItem(((StaggeredGridLayoutManager) this.mLayoutManager).findLastVisibleItemPositions(null));
            } else if (this.mLayoutManager instanceof LinearLayoutManager) {
                lastVisibleItemPosition = ((LinearLayoutManager) this.mLayoutManager).findLastVisibleItemPosition();
            } else if (this.mLayoutManager instanceof GridLayoutManager) {
                lastVisibleItemPosition = ((GridLayoutManager) this.mLayoutManager).findLastVisibleItemPosition();
            }
            if (totalItemCount < this.previousTotalItemCount) {
                this.currentPage = 0;
                this.previousTotalItemCount = totalItemCount;
                if (totalItemCount == 0) {
                    this.loading = true;
                }
            }
            if (this.loading && totalItemCount > this.previousTotalItemCount) {
                this.loading = false;
                this.previousTotalItemCount = totalItemCount;
            }
            if (!this.loading && lastVisibleItemPosition + 5 > totalItemCount) {
                this.currentPage++;
                this.loadMoreListener.onLoadMore(this.currentPage, totalItemCount);
                this.loading = true;
            }
        }
    }
}
