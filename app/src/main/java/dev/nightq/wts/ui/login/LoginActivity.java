package dev.nightq.wts.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.avos.sns.SNS;
import com.avos.sns.SNSType;

import butterknife.Bind;
import butterknife.OnClick;
import dev.nightq.wts.R;
import dev.nightq.wts.app.WTSApplication;
import dev.nightq.wts.app.baseView.activity.MVPActivityBase;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity
        extends MVPActivityBase<LoginPresenter>
        implements LoginContract.View {

    @Bind(R.id.btnLoginQQ)
    Button btnLoginQQ;
    @Bind(R.id.btnLoginWeibo)
    Button btnLoginWeibo;

    @Override
    public void getIntentDataInActivityBase(Bundle savedInstanceState) {

    }

    @Override
    public int onCreateBase() {
        return R.layout.activity_login;
    }

    @Override
    public void initActivityBaseView() {

    }

    @Override
    public void loadDataOnCreate() {
    }

    @Override
    public void setupComponent() {
        DaggerLoginComponent.builder()
                .userComponent(WTSApplication.getInstance().getUserComponent())
                .loginModule(new LoginModule(this))
                .build().inject(this);
    }

    SNSType lastLoginType;

    @OnClick({
            R.id.btnLoginQQ,
            R.id.btnLoginWeibo,
            R.id.btnLoginLean})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLoginQQ:
                lastLoginType = SNSType.AVOSCloudSNSQQ;
                mPresenter.login(
                        this,
                        lastLoginType,
                        "728184422",
                        "150e64dd066d18d63ee0304ac242308d",
                        "https://leancloud.cn/1.1/sns/goto/6l6n90b9f08sya8d");
                break;
            case R.id.btnLoginWeibo:
                lastLoginType = SNSType.AVOSCloudSNSSinaWeibo;
                mPresenter.login(
                        this,
                        lastLoginType,
                        "1105611977",
                        "HDUG0fIbJThScfAE",
                        "https://leancloud.cn/1.1/sns/goto/2mt4iu7n6pwsv370");
                break;
            case R.id.btnLoginLean:
//                lastLoginType = SNSType.AVOSCloudSNS;
//                mPresenter.login(
//                        this,
//                        lastLoginType,
//                        "1105611977",
//                        "HDUG0fIbJThScfAE",
//                        "https://leancloud.cn/1.1/sns/goto/2mt4iu7n6pwsv370");
                break;
        }
    }

    // 当登录完成后，请调用 SNS.onActivityResult(requestCode, resultCode, data, type);
    // 这样你的回调用将会被调用到
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SNS.onActivityResult(requestCode, resultCode, data, lastLoginType);
    }

}

