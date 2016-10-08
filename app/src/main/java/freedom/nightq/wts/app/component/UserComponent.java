package freedom.nightq.wts.app.component;

import dagger.Subcomponent;
import freedom.nightq.wts.app.module.user.UserModule;
import freedom.nightq.wts.app.module.user.UserRepositoryModule;
import freedom.nightq.wts.app.scope.UserScope;
import freedom.nightq.wts.model.user.User;
import freedom.nightq.wts.repository.GlobalSPRepository;
import freedom.nightq.wts.repository.UserSPRepository;

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
