package dev.nightq.wts.widgets.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Nightq on 16/9/13.
 */

public class ViewHolderBase<DataType> extends RecyclerView.ViewHolder {

    public DataType mData;

    public ViewHolderBase(View itemView) {
        super(itemView);
        inflateLayout();
        ButterKnife.bind(this, itemView);
    }

    /**
     * 可以生成 view
     */
    public void inflateLayout () {
        // nothing
    }

    /**
     * bind 数据
     */
    public void bindData (DataType data, Object...extras) {
        mData = data;
    }

}
