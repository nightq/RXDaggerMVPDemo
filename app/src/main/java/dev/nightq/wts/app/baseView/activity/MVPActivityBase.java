package dev.nightq.wts.app.baseView.activity;

import javax.inject.Inject;

import dev.nightq.wts.app.baseView.BaseMVPPresenter;

/**
 * 包含了 可控制的顶部 toolbar
 * @see MVPActivityBase#createDefaultToolBar() 控制是否使用默认框架包含toolbar
 */
public abstract class MVPActivityBase<T extends BaseMVPPresenter>
        extends ActivityBase {

    @Inject
    public T mPresenter;

}
