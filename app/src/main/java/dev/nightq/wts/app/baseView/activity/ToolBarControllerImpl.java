package dev.nightq.wts.app.baseView.activity;

/**
 * Created by Nightq on 16/9/8.
 * <p>
 * 可以控制是否有 自带的 toolbar
 */

public interface ToolBarControllerImpl {

    /**
     * 可以控制是否有 自带的 toolbar
     *
     * @return true 默认自带默认的 toolbar
     */
    boolean createDefaultToolBar();
}
