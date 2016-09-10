package dev.nightq.wts.app.baseView;

/**
 * @author Nightq
 * 用于MVP模式 的 presenter
 * @param <T>
 */
public interface BaseMVPPresenter<T> {

    /**
     * 初始化加载
     */
    void loadAfterCreated();

}