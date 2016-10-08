package freedom.nightq.wts.app.baseView.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.annotation.Nullable;

import butterknife.Bind;
import butterknife.ButterKnife;
import freedom.nightq.wts.R;
import freedom.nightq.wts.model.eventBus.SessionChangeEvent;
import freedom.nightq.wts.tools.LogHelper;

/**
 * 包含了 可控制的顶部 toolbar
 *
 * @see ActivityBase#createDefaultToolBar() 控制是否使用默认框架包含toolbar
 */
public abstract class ActivityBase
        extends ActivityStartupBase
        implements ToolBarControllerImpl {

    @Nullable
    @Bind(R.id.baseActivityContentLayoutContainer)
    FrameLayout baseActivityContentLayoutContainer;
    @Nullable
    @Bind(R.id.baseActivityLayout)
    CoordinatorLayout baseActivityLayout;

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (createDefaultToolBar()) {
            super.setContentView(R.layout.base_activity_layout);
            LayoutInflater.from(this).inflate(layoutResID,
                    (ViewGroup) findViewById(R.id.baseActivityContentLayoutContainer), true);
        } else {
            super.setContentView(layoutResID);
        }
        ButterKnife.bind(this);
        // 如果layout中有叫action_bar的Toolbar，就将其设为support actionbar
        initToolbar();
    }

    /**
     * 可以控制是否有 自带的 toolbar
     *
     * @return true 默认自带默认的 toolbar
     */
    @Override
    public boolean createDefaultToolBar() {
        return true;
    }

    /**
     * 登录态变化的时候需要重新注入吗
     *
     * @return true 默认不需要重新注入
     */
    public boolean reinjectWhenSessionChange() {
        return false;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SessionChangeEvent event) {
        // 状态变化,需要全部重新刷新 Component, 重新注入新的数据
        if (reinjectWhenSessionChange()) {
            setupComponent();
        }
        onSessionChange();
    }

    /**
     * 登录状态变化
     */
    protected void onSessionChange() {
        // todo nightq now
        LogHelper.e("nightq", "");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
