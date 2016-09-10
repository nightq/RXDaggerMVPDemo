package dev.nightq.wts.ui.login;

import dagger.Component;
import dev.nightq.wts.app.component.UserComponent;
import dev.nightq.wts.app.scope.ActivityScope;

/**
 * Created by Nightq on 16/8/18.
 */
@ActivityScope
@Component(dependencies = UserComponent.class,
        modules = {LoginModule.class})
public interface LoginComponent {
    LoginActivity inject(LoginActivity activity);

}
