package dev.nightq.wts.ui.login;

import javax.inject.Inject;

import dev.nightq.wts.app.baseView.BaseMVPPresenter;
import dev.nightq.wts.app.scope.ActivityScope;
import dev.nightq.wts.ui.main.MainActivity;

/**
 * Created by Nightq on 16/9/9.
 */
@ActivityScope
public class LoginPresenter implements LoginContract.Presenter {

    @Inject
    public LoginPresenter() {
    }


    @Override
    public void loadAfterCreated() {

    }

    @Override
    public void login(String account, String password) {
        // todo nightq now
    }
}
