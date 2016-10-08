package freedom.nightq.wts.ui.login;

import android.app.Activity;

import com.avos.sns.SNSType;

import freedom.nightq.wts.app.baseView.BaseMVPPresenter;
import freedom.nightq.wts.app.baseView.BaseMVPView;


/**
 * Created by Nightq on 16/8/9.
 */

public interface LoginContract {


    interface View extends BaseMVPView {

        /**
         * 失败
         */
        void loginFailed(Exception e);

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
