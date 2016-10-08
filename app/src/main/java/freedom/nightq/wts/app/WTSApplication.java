package freedom.nightq.wts.app;

import android.support.annotation.NonNull;

import com.avos.avoscloud.AVUser;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import freedom.nightq.wts.app.component.AppComponent;
import freedom.nightq.wts.app.component.DaggerAppComponent;
import freedom.nightq.wts.app.component.UserComponent;
import freedom.nightq.wts.app.module.global.AppModule;
import freedom.nightq.wts.app.module.global.GlobalRepositoryModule;
import freedom.nightq.wts.app.module.user.UserModule;
import freedom.nightq.wts.app.module.user.UserRepositoryModule;
import freedom.nightq.wts.model.eventBus.SessionChangeEvent;
import freedom.nightq.wts.model.user.User;
import freedom.nightq.wts.repository.GlobalSPRepository;

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

    @Inject
    GlobalSPRepository mGlobalSPRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        // 初始化 user
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this))
                .globalRepositoryModule(new GlobalRepositoryModule())
                .build();
        mAppComponent.inject(this);

        refreUserSessionAndSendEvent();

    }

    /**
     * 自动登录上一个用户
     */
    public void refreUserSessionAndSendEvent() {
        AVUser avUser = AVUser.getCurrentUser();
        User user;
        if (avUser != null) {
            user = UserSessionHelper.getUserFromAVUser(avUser);
        } else {
            user = new User();
        }
        switchUser(user);
    }

    /**
     * @param user
     * @return
     */
    private UserComponent switchUser(
            @NonNull User user) {
        mUserComponent = mAppComponent.plus(new UserModule(user),
                new UserRepositoryModule());
        if (user.isLocal) {
            EventBus.getDefault().post(new SessionChangeEvent(false));
        } else {
            EventBus.getDefault().post(new SessionChangeEvent(true));
        }
        return mUserComponent;
    }

    public UserComponent getUserComponent() {
        return mUserComponent;
    }
}
