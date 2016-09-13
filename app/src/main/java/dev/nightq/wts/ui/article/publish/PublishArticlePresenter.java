package dev.nightq.wts.ui.article.publish;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.SaveCallback;

import java.util.Date;

import javax.inject.Inject;

import dev.nightq.wts.app.scope.ActivityScope;
import dev.nightq.wts.model.article.Article;
import dev.nightq.wts.model.leancloudTable.TableNames;
import dev.nightq.wts.model.user.User;

/**
 * Created by Nightq on 16/9/9.
 */
@ActivityScope
public class PublishArticlePresenter extends PublishArticleContract.Presenter {

    public User mUser;

    @Inject
    public PublishArticlePresenter(
            @NonNull User user) {
        mUser = user;
    }

    @Override
    void publishArticle(Activity activity, String title, String content) {
        if (!mUser.checkValidSession(true)) return;
        final AVObject article = new AVObject(TableNames.Article.Name);
        article.put(TableNames.Article.Title, title);
        article.put(TableNames.Article.Content, content);
        article.put(TableNames.Article.WriteOn, new Date());

        // 添加作者信息
        try {
            article.put(TableNames.Article.Author,
                    AVUser.createWithoutData(AVUser.class,
                            mUser.mAVUser.getObjectId()));
            article.saveInBackground(new SaveCallback() {
                @Override
                public void done(AVException e) {
                    if (e == null) {
                        mView.publishSuccess(new Article(article));
                    } else {
                        mView.publishFailed(e);
                    }
                }
            });
        } catch (AVException e) {
            e.printStackTrace();
            mView.publishFailed(e);
        }

    }

}
