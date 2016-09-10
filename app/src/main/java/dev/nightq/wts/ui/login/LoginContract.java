package dev.nightq.wts.ui.login;

import dev.nightq.wts.app.baseView.BaseMVPPresenter;
import dev.nightq.wts.app.baseView.BaseMVPView;


/**
 * Created by Nightq on 16/8/9.
 */

public interface LoginContract {


    interface View extends BaseMVPView<Presenter> {


    }

    interface Presenter extends BaseMVPPresenter {

        /**
         * 登录
         */
        void login (String account, String password);

    }
}
