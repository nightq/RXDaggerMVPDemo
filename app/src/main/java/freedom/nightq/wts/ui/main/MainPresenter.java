package freedom.nightq.wts.ui.main;

import javax.inject.Inject;

import freedom.nightq.wts.app.scope.ActivityScope;

/**
 * Created by Nightq on 16/9/9.
 */
@ActivityScope
public class MainPresenter extends MainContract.Presenter {

    @Inject
    public MainPresenter() {
    }


    @Override
    public void loadAfterCreated() {

    }
}
