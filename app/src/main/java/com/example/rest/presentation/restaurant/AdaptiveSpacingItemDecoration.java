package com.example.rest.presentation.restaurant;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import com.example.rest.presentation.mainpage.MainPageFragment;

public class AdaptiveSpacingItemDecoration extends RecyclerView.ItemDecoration {
    private final int size;
    private final boolean edgeEnabled;
    public AdaptiveSpacingItemDecoration (int size, boolean edgeEnabled) {
        this.size = size;
        this.edgeEnabled = edgeEnabled;
    }
    @Override
    public void getItemOffsets (Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            makeGridSpacing(
                    outRect,
                    parent.getChildAdapterPosition(view),
                    state.getItemCount(),
                    ((GridLayoutManager) layoutManager).getOrientation(),
                    ((GridLayoutManager) layoutManager).getSpanCount(),
                    ((GridLayoutManager) layoutManager).getReverseLayout()
            );
        } else if (layoutManager instanceof StaggeredGridLayoutManager) {
            makeGridSpacing(
                    outRect,
                    parent.getChildAdapterPosition(view),
                    state.getItemCount(),
                    ((StaggeredGridLayoutManager) layoutManager).getOrientation(),
                    ((StaggeredGridLayoutManager) layoutManager).getSpanCount(),
                    ((StaggeredGridLayoutManager) layoutManager).getReverseLayout()
                    );
        }
    }

    private void makeGridSpacing(
            Rect outRect,
            int position,
            int itemCount,
            int orientation,
            int spanCount,
            boolean isReversed
    ) {

        boolean isLastPosition = position == (itemCount - 1);
        int sizeBasedOnEdge = edgeEnabled? size:0;
        int sizeBasedOnLastPosition = isLastPosition ? sizeBasedOnEdge : size;

        int subsideCount = (itemCount % spanCount == 0) ? itemCount / spanCount: (itemCount / spanCount) + 1;

        int xAxis = (orientation == RecyclerView.HORIZONTAL) ? position / spanCount : position % spanCount;
        int yAxis = (orientation == RecyclerView.HORIZONTAL) ? position % spanCount : position / spanCount;

        boolean isFirstColumn = xAxis == 0;
        boolean isFirstRow = yAxis == 0;
        boolean isLastColumn = (orientation == RecyclerView.HORIZONTAL) ? xAxis == subsideCount - 1 : xAxis == spanCount - 1;
        boolean isLastRow = (orientation == RecyclerView.HORIZONTAL) ? yAxis == spanCount - 1 : yAxis == subsideCount - 1;

        int sizeBasedOnFirstColumn = isFirstColumn ? sizeBasedOnEdge: 0;
        int sizeBasedOnLastColumn = !isLastColumn ? sizeBasedOnLastPosition: sizeBasedOnEdge;
        int sizeBasedOnFirstRow = isFirstRow ? sizeBasedOnEdge: 0;
        int sizeBasedOnLastRow = !isLastRow ? size: sizeBasedOnEdge;
        switch (orientation) {
            case RecyclerView.HORIZONTAL:
                outRect.left = isReversed ? sizeBasedOnLastColumn: sizeBasedOnFirstColumn;
                outRect.top = edgeEnabled ? size * (spanCount - yAxis) / spanCount : size * yAxis / spanCount;
                outRect.right = isReversed ? sizeBasedOnFirstColumn : sizeBasedOnLastColumn;
                outRect.bottom = edgeEnabled ? size * (yAxis + 1) / spanCount : size * (spanCount - (yAxis + 1)) / spanCount;
                break;
            case RecyclerView.VERTICAL:
                outRect.left = edgeEnabled ? size * (spanCount - xAxis) / spanCount : size * xAxis / spanCount;
                outRect.top = isReversed ? sizeBasedOnLastRow : sizeBasedOnFirstRow;
                outRect.right = edgeEnabled ? size * (xAxis + 1) / spanCount : size * (spanCount - (xAxis + 1)) / spanCount;
                outRect.bottom = isReversed ? sizeBasedOnFirstRow: sizeBasedOnLastRow;
                break;
        }
    }
    private static final int NO_SPACING = 0;
}