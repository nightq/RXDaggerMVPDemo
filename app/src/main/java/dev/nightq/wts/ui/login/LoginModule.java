package dev.nightq.wts.ui.login;


import dagger.Module;
import dev.nightq.wts.app.baseView.DaggerBaseModule;

/**
 * Created by Nightq on 16/8/22.
 */

@Module
public class LoginModule extends DaggerBaseModule<LoginContract.View> {

    public LoginModule(LoginContract.View view) {
        super(view);
    }

}
