package dev.nightq.wts.ui.article.publish;

import dagger.Component;
import dev.nightq.wts.app.component.UserComponent;
import dev.nightq.wts.app.scope.ActivityScope;

/**
 * Created by Nightq on 16/9/13.
 */

@ActivityScope
@Component(dependencies = UserComponent.class,
        modules = {PublishArticleModule.class})
public interface PublishArticleComponent {
    PublishArticleActivity inject(PublishArticleActivity activity);
}
