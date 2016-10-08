package freedom.nightq.wts.ui.article.list.fragmet;


import java.util.List;

import dagger.Module;
import dagger.Provides;
import freedom.nightq.wts.app.baseView.DaggerBaseModule;
import freedom.nightq.wts.model.article.Article;

/**
 * Created by Nightq on 16/8/22.
 */

@Module
public class ArticlesListModule extends DaggerBaseModule<ArticlesListContract.View> {

    List<Article> mData;

    public ArticlesListModule(ArticlesListContract.View view, List<Article> data) {
        super(view);
        mData = data;
    }

    @Provides
    public List<Article> provideArticles () {
        return mData;
    }

}
