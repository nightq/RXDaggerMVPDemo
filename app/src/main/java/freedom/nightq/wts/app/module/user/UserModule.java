package freedom.nightq.wts.app.module.user;

import dagger.Module;
import dagger.Provides;
import freedom.nightq.wts.model.user.User;

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
