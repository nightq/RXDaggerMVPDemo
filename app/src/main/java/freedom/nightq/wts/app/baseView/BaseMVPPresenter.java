package freedom.nightq.wts.app.baseView;

import javax.inject.Inject;

/**
 * @author Nightq
 * 用于MVP模式 的 presenter
 * @param <T>
 */
public abstract class BaseMVPPresenter<T extends BaseMVPView> {

    @Inject
    public T mView;
    /**
     * 初始化加载
     */
    public void loadAfterCreated() {};

}