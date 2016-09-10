package dev.nightq.wts.ui.login;

import android.app.Activity;
import android.content.Context;

import com.avos.avoscloud.AVException;
import com.avos.sns.SNSType;

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
        void login(Activity activity,
                           SNSType type, String appKey,
                           String appSec,
                           String redirectUrl);
    }
}
