package dev.nightq.wts.app.baseView.activity;

import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import dev.nightq.wts.R;
import dev.nightq.wts.tools.ResourceHelper;

/**
 */
public abstract class ActivityBaseWithBar extends ActivityStartupBase {

    @Bind(R.id.baseActivityContentLayoutContainer)
    FrameLayout baseActivityContentLayoutContainer;
    @Bind(R.id.baseActivityContentLayout)
    FrameLayout baseActivityContentLayout;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.base_activity_layout);
        LayoutInflater.from(this).inflate(layoutResID,
                (ViewGroup) findViewById(R.id.baseActivityContentLayoutContainer), true);
        ButterKnife.bind(this);
        // 如果layout中有叫action_bar的Toolbar，就将其设为support actionbar
        initToolbar();
    }

    /**
     * 设置 toolbar 是否 overlay
     * 默认 不是 overlay 的
     * 需要在 setContentView 之后调用
     *
     * @warn 默认布局是使用的 below ，因为如果使用 topmargin 会在部分界面 隐藏 actionbar 的时候仍然有topmargin 。
     *       所以默认布局使用 below
     *       这里设置使用 top margin， 是因为，部分布局动态显示隐藏
     *       如果使用 below 会没有刷新界面导致内容布局没有在actionbar 的下面
     */
    public void setToolbarOverlay(boolean arg) {
        if (arg) {
            // 设置 over lay
//            RelativeLayout.LayoutParams rl =
//                    (RelativeLayout.LayoutParams)
//                            baseActivityContentLayoutContainer.getLayoutParams();
//            rl.topMargin = 0;
            RelativeLayout.LayoutParams rl = new RelativeLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            baseActivityContentLayoutContainer.setLayoutParams(rl);
        } else {
            // 这里最好还是使用 below。
//            RelativeLayout.LayoutParams rl =
//                    (RelativeLayout.LayoutParams)
//                            baseActivityContentLayoutContainer.getLayoutParams();
//            rl.addRule(RelativeLayout.BELOW, R.id.action_bar);
            RelativeLayout.LayoutParams rl =
                    new RelativeLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
            rl.topMargin = (int) ResourceHelper.getDimension(R.dimen.base_actionbar_height);

            baseActivityContentLayoutContainer.setLayoutParams(rl);
        }
    }
}
