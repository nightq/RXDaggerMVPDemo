package dev.nightq.wts.ui.login;

import android.app.Activity;

import com.avos.sns.SNSType;

import dev.nightq.wts.app.baseView.BaseMVPPresenter;
import dev.nightq.wts.app.baseView.BaseMVPView;


/**
 * Created by Nightq on 16/8/9.
 */

public interface LoginContract {


    interface View extends BaseMVPView {

        /**
         * 登录成功
         */
        void loginSuccess();

    }

    abstract class Presenter extends BaseMVPPresenter<View> {

        /**
         * 登录
         */
        abstract void login(Activity activity,
                            SNSType type, String appKey,
                            String appSec,
                            String redirectUrl);
    }
}
