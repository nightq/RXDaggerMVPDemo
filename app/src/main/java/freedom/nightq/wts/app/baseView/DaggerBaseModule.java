package freedom.nightq.wts.app.baseView;

import dagger.Module;
import dagger.Provides;

/**
 * dagger 的基础 module, 主要是有 view
 *
 */
@Module
public abstract class DaggerBaseModule<T extends BaseMVPView> {

    public T mView;

    public DaggerBaseModule(T view) {
        this.mView = view;
    }

    @Provides
    public T provideView() {
        return mView;
    }

}