package freedom.nightq.wts.ui.article.publish;


import dagger.Module;
import freedom.nightq.wts.app.baseView.DaggerBaseModule;

/**
 * Created by Nightq on 16/8/22.
 */

@Module
public class PublishArticleModule extends DaggerBaseModule<PublishArticleContract.View> {

    public PublishArticleModule(PublishArticleContract.View view) {
        super(view);
    }

}
