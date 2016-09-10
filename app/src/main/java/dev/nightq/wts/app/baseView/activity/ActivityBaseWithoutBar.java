package dev.nightq.wts.app.baseView.activity;

import android.support.annotation.LayoutRes;

import butterknife.ButterKnife;

/**
 */
public abstract class ActivityBaseWithoutBar extends ActivityStartupBase {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        // 如果layout中有叫action_bar的Toolbar，就将其设为support actionbar
        initToolbar();
    }
}
