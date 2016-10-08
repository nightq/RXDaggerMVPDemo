package freedom.nightq.wts.app.component;

import javax.inject.Singleton;

import dagger.Component;
import freedom.nightq.wts.app.WTSApplication;
import freedom.nightq.wts.app.module.global.AppModule;
import freedom.nightq.wts.app.module.global.GlobalRepositoryModule;
import freedom.nightq.wts.app.module.user.UserModule;
import freedom.nightq.wts.app.module.user.UserRepositoryModule;

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
