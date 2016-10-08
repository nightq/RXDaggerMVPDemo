package freedom.nightq.wts.widgets.recyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nightq on 16/9/13.
 */

public abstract class RecyclerAdapterBase<DataType, ViewHolder extends ViewHolderBase<DataType>>
        extends RecyclerView.Adapter<ViewHolder> {
    protected List<DataType> mData;

    public RecyclerAdapterBase(List<DataType> dataList) {
        super();
        this.mData = dataList;
    }

    public void setData(List<DataType> dataList) {
        if (this.mData == null) {
            this.mData = new ArrayList<>();
        } else {
            this.mData.clear();
        }
        if (dataList != null) {
            this.mData.addAll(dataList);
        }
    }

    public List<DataType> getData() {
        return this.mData;
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(itemLayoutId(viewType), null);
        return createViewHolderBase(viewType, view);
    }

    /**
     * 取item layout id
     * @param viewType
     */
    public abstract int itemLayoutId(int viewType);

    /**
     * 创建 viewholder
     * @param viewType
     */
    public abstract ViewHolder createViewHolderBase(int viewType, View view);

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindData(getDataByPosition(position));
    }

    /**
     * 取数据
     * @param position
     * @return
     */
    public DataType getDataByPosition (int position) {
        return mData == null ? null : mData.get(position);
    }

}
