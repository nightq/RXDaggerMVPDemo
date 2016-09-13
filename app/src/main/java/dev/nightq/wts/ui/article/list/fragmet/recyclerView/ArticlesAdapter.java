package dev.nightq.wts.ui.article.list.fragmet.recyclerView;

import android.view.View;

import java.util.List;

import javax.inject.Inject;

import dev.nightq.wts.R;
import dev.nightq.wts.model.article.Article;
import dev.nightq.wts.widgets.recyclerView.RecyclerAdapterBase;

/**
 * Created by Nightq on 16/9/13.
 */

public class ArticlesAdapter extends RecyclerAdapterBase<Article, ArticleViewHolder> {

    @Inject
    public ArticlesAdapter(List<Article> dataList) {
        super(dataList);
    }

    @Override
    public int itemLayoutId(int viewType) {
        return R.layout.articles_item_layout;
    }

    @Override
    public ArticleViewHolder createViewHolderBase(int viewType, View view) {
        return new ArticleViewHolder(view);
    }
}
