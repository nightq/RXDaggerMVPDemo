package dev.nightq.wts.ui.article.read;

import android.app.Activity;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.GetCallback;

import javax.inject.Inject;

import dev.nightq.wts.app.scope.ActivityScope;
import dev.nightq.wts.model.article.Article;
import dev.nightq.wts.model.leancloudTable.TableNames;

/**
 * Created by Nightq on 16/9/9.
 */
@ActivityScope
public class ReadArticlePresenter extends ReadArticleContract.Presenter {

    @Inject
    public ReadArticlePresenter() {
    }

    @Override
    void loadDetail(Activity activity, String id) {
        AVQuery<AVObject> avQuery = new AVQuery<>(TableNames.Article.Name);
        avQuery.getInBackground(id, new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if (e == null) {
                    mView.loadSuccess(new Article(avObject));
                } else {
                    mView.loadFailed(e);
                }
            }
        });
    }
}
