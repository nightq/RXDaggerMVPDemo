package freedom.nightq.wts.ui.main;

import dagger.Component;
import freedom.nightq.wts.app.component.UserComponent;
import freedom.nightq.wts.app.scope.ActivityScope;

/**
 * Created by Nightq on 16/8/18.
 */
@ActivityScope
@Component(dependencies = UserComponent.class,
        modules = {MainModule.class})
public interface MainComponent {
    MainActivity inject(MainActivity activity);

}
