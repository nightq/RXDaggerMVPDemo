package freedom.nightq.wts.ui.article.list.fragmet;

import dagger.Component;
import freedom.nightq.wts.app.component.UserComponent;
import freedom.nightq.wts.app.scope.ActivityScope;

/**
 * Created by Nightq on 16/8/18.
 */
@ActivityScope
@Component(dependencies = UserComponent.class,
        modules = {ArticlesListModule.class})
public interface ArticlesListComponent {
    ArticlesListFragment inject(ArticlesListFragment fragment);
}
