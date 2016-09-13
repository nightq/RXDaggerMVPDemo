package dev.nightq.wts.ui.article.read;

import android.app.Activity;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.LogInCallback;
import com.avos.sns.SNS;
import com.avos.sns.SNSBase;
import com.avos.sns.SNSCallback;
import com.avos.sns.SNSException;
import com.avos.sns.SNSType;

import javax.inject.Inject;

import dev.nightq.wts.R;
import dev.nightq.wts.app.WTSApplication;
import dev.nightq.wts.app.scope.ActivityScope;
import dev.nightq.wts.model.article.Article;
import dev.nightq.wts.model.leancloudTable.TableNames;
import dev.nightq.wts.model.user.User;
import dev.nightq.wts.tools.ResourceHelper;
import dev.nightq.wts.tools.ToastHelper;

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
