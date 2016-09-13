package dev.nightq.wts.widgets.recyclerView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Nightq on 16/9/13.
 */

public class RecycleViewDivider extends RecyclerView.ItemDecoration {

    private Paint mPaint;
    private Drawable mDivider;
    private int mOrientation;
    private int dividerSize;
    private int dividerWidth;
    private int dividerHeight;
    //列表的方向：LinearLayoutManager.VERTICAL或LinearLayoutManager.HORIZONTAL

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation 列表方向
     * @param drawableId  分割线图片
     */
    public RecycleViewDivider(Context context, int orientation, int drawableId) {
        if (orientation != LinearLayoutManager.VERTICAL
                && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("请输入正确的参数！");
        }
        mDivider = ContextCompat.getDrawable(context, drawableId);
        if (mDivider == null) {
            initDivider(orientation, 0, 0);
        } else {
            initDivider(orientation,
                    mDivider.getIntrinsicWidth(),
                    mDivider.getIntrinsicHeight());
        }
    }

    /**
     * 自定义分割线
     *
     * @param orientation   列表方向
     * @param dividerHeight 分割线高度
     * @param dividerColor  分割线颜色
     */
    public RecycleViewDivider(int orientation,
                              int dividerWidth,
                              int dividerHeight, int dividerColor) {
        initDivider(orientation, dividerWidth, dividerHeight);
        mPaint.setColor(dividerColor);
    }

    /**
     *
     */
    public void initDivider(int orientation,
                            int dividerWidth,
                            int dividerHeight) {
        if (orientation != LinearLayoutManager.VERTICAL
                && orientation != LinearLayoutManager.HORIZONTAL) {
            throw new IllegalArgumentException("请输入正确的参数！");
        }
        mOrientation = orientation;
        this.dividerWidth = dividerWidth;
        this.dividerHeight = dividerHeight;
        switch (mOrientation) {
            case LinearLayoutManager.VERTICAL:
                dividerSize = dividerHeight;
                break;
            case LinearLayoutManager.HORIZONTAL:
                dividerSize = dividerWidth;
                break;
        }
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
    }


    /**
     * 获取分割线尺寸
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//        super.getItemOffsets(outRect, view, parent, state);
        switch (mOrientation) {
            case LinearLayoutManager.VERTICAL:
                outRect.set(0, 0, 0, dividerSize);
                break;
            case LinearLayoutManager.HORIZONTAL:
                outRect.set(0, 0, dividerSize, 0);
                break;
        }
    }

    /**
     * 绘制分割线
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawHLineForVertical(c, parent);
        } else {
            drawVLineForHorizontal(c, parent);
        }
    }

    /**
     * 为竖直方向的列表绘制横向 item 分割线
     * @param canvas
     * @param parent
     */
    private void drawHLineForVertical(Canvas canvas, RecyclerView parent) {
        final int left = (canvas.getWidth() - dividerWidth)/2;
        final int right = parent.getMeasuredWidth() - left;
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + dividerSize;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    /**
     * 为水平方向的列表绘制纵向 item 分割线
     * @param canvas
     * @param parent
     */
    private void drawVLineForHorizontal(Canvas canvas, RecyclerView parent) {
        final int top = (canvas.getHeight() - dividerHeight)/2;
        final int bottom = canvas.getHeight() - top;
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + dividerSize;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }
}