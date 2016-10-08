package freedom.nightq.wts.ui.article.publish;

import dagger.Component;
import freedom.nightq.wts.app.component.UserComponent;
import freedom.nightq.wts.app.scope.ActivityScope;

/**
 * Created by Nightq on 16/9/13.
 */

@ActivityScope
@Component(dependencies = UserComponent.class,
        modules = {PublishArticleModule.class})
public interface PublishArticleComponent {
    PublishArticleActivity inject(PublishArticleActivity activity);
}
