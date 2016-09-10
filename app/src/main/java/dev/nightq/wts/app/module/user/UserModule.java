package dev.nightq.wts.app.module.user;

import dagger.Module;
import dagger.Provides;
import dev.nightq.wts.app.scope.ActivityScope;
import dev.nightq.wts.app.scope.UserScope;
import dev.nightq.wts.model.User;
import dev.nightq.wts.repository.GlobalSPRepository;
import dev.nightq.wts.repository.UserSPRepository;

/**
 * Created by Nightq on 16/4/25.
 */
@Module
public class UserModule {

    public User mUser;

    public UserModule(User user) {
        this.mUser = user;
    }

    @Provides
    public User provideUser() {
        return mUser;
    }
//
//    @Provides
//    @ActivityScope
//    public UserSPRepository provideUserSPRepository (UserSPRepository userSPRepository) {
//        return userSPRepository;
//    }
}
