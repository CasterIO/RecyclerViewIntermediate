package bootstrap.casterio.com.myapplication.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import bootstrap.casterio.com.myapplication.R;

public class GridDividerDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = { android.R.attr.listDivider };

    private Drawable mDivider;
    private int mInsets;

    public GridDividerDecoration(Context context) {
        TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();

        mInsets = context.getResources().getDimensionPixelSize(R.dimen.card_insets);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //Draw under the view content

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        //Draw over the view content
//        drawVertical(c, parent);
//        drawHorizontal(c, parent);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        int absolutePosition = parent.getChildAdapterPosition(view);
        if (absolutePosition == RecyclerView.NO_POSITION) {
            return;
        } else if (absolutePosition < 3) {
            // First 3 items are top row
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.set(56, 56, 56, 56);
        }
    }

    /** Draw dividers at each expected grid interval */
    public void drawVertical(Canvas c, RecyclerView parent) {
        if (parent.getChildCount() == 0) return;

        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final View child = parent.getChildAt(0);
        if (child.getHeight() == 0) return;

        final RecyclerView.LayoutParams params =
                (RecyclerView.LayoutParams) child.getLayoutParams();
        int top = child.getBottom() + params.bottomMargin + mInsets;
        int bottom = top + mDivider.getIntrinsicHeight();

        final int parentBottom = parent.getHeight() - parent.getPaddingBottom();
        while (bottom < parentBottom) {
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);

            top += mInsets + params.topMargin + child.getHeight() + params.bottomMargin + mInsets;
            bottom = top + mDivider.getIntrinsicHeight();
        }
    }

    /** Draw dividers to the right of each child view */
    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params =
                    (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + params.rightMargin + mInsets;
            final int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}