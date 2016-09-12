package dev.nightq.wts.app.component;

import javax.inject.Singleton;

import dagger.Component;
import dev.nightq.wts.app.WTSApplication;
import dev.nightq.wts.app.module.global.AppModule;
import dev.nightq.wts.app.module.global.GlobalRepositoryModule;
import dev.nightq.wts.app.module.user.UserModule;
import dev.nightq.wts.app.module.user.UserRepositoryModule;

/**
 * Created by Nightq on 16/4/25.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                GlobalRepositoryModule.class})
public interface AppComponent {

    UserComponent plus(
            UserModule userModule,
            UserRepositoryModule userRepositoryModule);

    WTSApplication inject (WTSApplication application);

}
