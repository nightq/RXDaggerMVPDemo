package freedom.nightq.wts.ui.article.read;

import android.app.Activity;

import com.avos.avoscloud.AVException;

import freedom.nightq.wts.app.baseView.BaseMVPPresenter;
import freedom.nightq.wts.app.baseView.BaseMVPView;
import freedom.nightq.wts.model.article.Article;


/**
 * Created by Nightq on 16/8/9.
 */

public interface ReadArticleContract {


    interface View extends BaseMVPView {

        /**
         * 成功
         */
        void loadSuccess(Article article);

        /**
         * 失败
         */
        void loadFailed(AVException e);

    }

    abstract class Presenter extends BaseMVPPresenter<View> {

        /**
         * 加载数据
         */
        abstract void loadDetail(Activity activity, String id);
    }
}
