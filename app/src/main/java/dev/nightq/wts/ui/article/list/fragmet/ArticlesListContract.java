package dev.nightq.wts.ui.article.list.fragmet;

import com.avos.avoscloud.AVException;

import dev.nightq.wts.app.baseView.BaseMVPPresenter;
import dev.nightq.wts.app.baseView.BaseMVPView;


/**
 * Created by Nightq on 16/8/9.
 */

public interface ArticlesListContract {


    interface View extends BaseMVPView {

        /**
         * 成功
         */
        void loadSuccess();

        /**
         * 失败
         */
        void loadFailed(AVException e);

    }

    abstract class Presenter extends BaseMVPPresenter<View> {

        /**
         * 加载数据
         */
        abstract void loadDetail();
    }
}
