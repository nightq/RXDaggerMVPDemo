package dev.nightq.wts.ui.main;

import javax.inject.Inject;

import dev.nightq.wts.app.scope.ActivityScope;

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
