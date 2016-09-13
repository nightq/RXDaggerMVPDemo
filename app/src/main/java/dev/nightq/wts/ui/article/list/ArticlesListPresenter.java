package dev.nightq.wts.ui.article.list;

import android.app.Activity;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;

import java.util.List;

import javax.inject.Inject;

import dev.nightq.wts.app.scope.ActivityScope;
import dev.nightq.wts.model.article.Article;
import dev.nightq.wts.model.leancloudTable.TableNames;

/**
 * Created by Nightq on 16/9/9.
 */
@ActivityScope
public class ArticlesListPresenter extends ArticlesListContract.Presenter {

    List<Article> mData;

    @Inject
    public ArticlesListPresenter(List<Article> data) {
        mData = data;
    }

    @Override
    void loadDetail(Activity activity) {
        AVQuery<AVObject> avQuery = new AVQuery<>(TableNames.Article.Name);
        avQuery.addDescendingOrder(TableNames.Article.WriteOn)
                .findInBackground(new FindCallback<AVObject>() {
                    @Override
                    public void done(List<AVObject> list, AVException e) {
                        if (e == null) {
                            for (AVObject avObject : list) {
                                mData.add(new Article(avObject));
                            }
                            mView.loadSuccess();
                        } else {
                            mView.loadFailed(e);
                        }
                    }
                });
    }
}
