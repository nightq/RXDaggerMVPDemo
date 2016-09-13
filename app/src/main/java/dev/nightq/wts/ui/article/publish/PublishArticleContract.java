package dev.nightq.wts.ui.article.publish;

import android.app.Activity;

import com.avos.avoscloud.AVException;

import dev.nightq.wts.app.baseView.BaseMVPPresenter;
import dev.nightq.wts.app.baseView.BaseMVPView;
import dev.nightq.wts.model.article.Article;


/**
 * Created by Nightq on 16/8/9.
 */

public interface PublishArticleContract {


    interface View extends BaseMVPView {

        /**
         * 成功
         */
        void publishSuccess(Article article);

        /**
         * 失败
         */
        void publishFailed(AVException e);

    }

    abstract class Presenter extends BaseMVPPresenter<View> {

        /**
         * 发布文章
         */
        abstract void publishArticle(Activity activity,
                                     String title,
                                     String content);
    }
}
