package dev.nightq.wts.app.component;

import dagger.Subcomponent;
import dev.nightq.wts.app.module.user.UserModule;
import dev.nightq.wts.app.module.user.UserRepositoryModule;
import dev.nightq.wts.app.scope.UserScope;
import dev.nightq.wts.model.user.User;
import dev.nightq.wts.repository.GlobalSPRepository;
import dev.nightq.wts.repository.UserSPRepository;

/**
 * Created by Nightq on 16/4/25.
 */
@UserScope
@Subcomponent(
        modules = {
                UserModule.class, UserRepositoryModule.class})
public interface UserComponent {
    GlobalSPRepository getGlobalSPRepository();
    UserSPRepository getUserSPRepository();

    User getCurrentUser();

}
