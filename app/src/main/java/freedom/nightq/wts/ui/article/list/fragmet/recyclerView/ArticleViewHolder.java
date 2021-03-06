package freedom.nightq.wts.ui.article.list.fragmet.recyclerView;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import freedom.nightq.wts.R;
import freedom.nightq.wts.model.article.Article;
import freedom.nightq.wts.tools.DateHelper;
import freedom.nightq.wts.ui.article.read.ReadArticleActivity;
import freedom.nightq.wts.widgets.recyclerView.ViewHolderBase;

/**
 * Created by Nightq on 16/9/13.
 */

public class ArticleViewHolder extends ViewHolderBase<Article> {

    @Bind(R.id.layoutItemContent)
    LinearLayout layoutItemContent;
    @Bind(R.id.txtArticleTitle)
    TextView txtArticleTitle;
    @Bind(R.id.txtArticleContent)
    TextView txtArticleContent;
    @Bind(R.id.txtArticleAuthor)
    TextView txtArticleAuthor;
    @Bind(R.id.txtArticleDate)
    TextView txtArticleDate;

    public ArticleViewHolder(View itemView) {
        super(itemView);
//        R.layout.articles_item_layout
    }

    @Override
    public void bindData(Article data, Object... extras) {
        super.bindData(data, extras);
        txtArticleTitle.setText(data.getTitle());
        txtArticleContent.setText(data.getContent());
        txtArticleDate.setText(DateHelper.formatYMDDate(data.getWriteOn()));
        txtArticleAuthor.setText(data.getAuthorName());
    }

    @OnClick(R.id.layoutItemContent)
    public void onClick (View view) {
        ReadArticleActivity
                .startReadArticleActivity(
                        layoutItemContent.getContext(), mData);
    }

}
