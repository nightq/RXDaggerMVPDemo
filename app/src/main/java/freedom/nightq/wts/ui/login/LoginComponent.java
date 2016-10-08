package freedom.nightq.wts.ui.login;

import dagger.Component;
import freedom.nightq.wts.app.component.UserComponent;
import freedom.nightq.wts.app.scope.ActivityScope;

/**
 * Created by Nightq on 16/8/18.
 */
@ActivityScope
@Component(dependencies = UserComponent.class,
        modules = {LoginModule.class})
public interface LoginComponent {
    LoginActivity inject(LoginActivity activity);

}
