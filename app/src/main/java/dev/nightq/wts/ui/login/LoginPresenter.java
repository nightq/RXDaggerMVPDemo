package dev.nightq.wts.ui.login;

import android.app.Activity;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
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
import dev.nightq.wts.model.user.User;
import dev.nightq.wts.tools.ResourceHelper;
import dev.nightq.wts.tools.ToastHelper;

/**
 * Created by Nightq on 16/9/9.
 */
@ActivityScope
public class LoginPresenter extends LoginContract.Presenter {

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void login(Activity activity, SNSType type, String appKey, String appSec, String redirectUrl) {
        final SNSCallback myCallback = new SNSCallback() {
            @Override
            public void done(SNSBase object, SNSException e) {
                if (e == null) {
                    ToastHelper.show("login ok " + object);
                    SNS.loginWithAuthData(object.userInfo(), new LogInCallback<AVUser>() {
                        @Override
                        public void done(AVUser avUser, AVException e) {
                            WTSApplication.getInstance().switchUser(new User(avUser));
                            mView.loginSuccess();
                        }
                    });
                }
            }
        };

        // 关联
        try {
            SNS.setupPlatform(activity, type, appKey, appSec, redirectUrl);
            SNS.loginWithCallback(activity, type, myCallback);
        } catch (Exception e) {
            ToastHelper.show(
                    ResourceHelper.getString(
                            R.string.toast_login_error, e.getMessage()));
        }

    }

    public void register() {
//        SNS.lo

    }

}
