package dev.nightq.wts.app;

import dev.nightq.wts.app.component.AppComponent;
import dev.nightq.wts.app.component.DaggerAppComponent;
import dev.nightq.wts.app.component.UserComponent;
import dev.nightq.wts.app.module.global.AppModule;
import dev.nightq.wts.app.module.global.GlobalRepositoryModule;
import dev.nightq.wts.app.module.user.UserModule;
import dev.nightq.wts.app.module.user.UserRepositoryModule;
import dev.nightq.wts.model.User;

/**
 * Created by Nightq on 16/9/6.
 */

public class WTSApplication extends BaseApplication {

    private static WTSApplication sInstance;

    public static WTSApplication getInstance() {
        return sInstance;
    }

    private AppComponent mAppComponent;
    private UserComponent mUserComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .globalRepositoryModule(new GlobalRepositoryModule())
                .build();
        aotuLogin();
    }

    /**
     * 自动登录上一个用户
     */
    public void aotuLogin () {
        // todo nightq now
        User user = new User();
        switchUser(user);
    }

    public UserComponent switchUser(User user) {
        mUserComponent = mAppComponent.plus(new UserModule(user),
                new UserRepositoryModule());
        return mUserComponent;
    }

    public UserComponent getUserComponent() {
        return mUserComponent;
    }
}
