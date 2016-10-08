package freedom.nightq.wts.ui.main;

import freedom.nightq.wts.app.baseView.BaseMVPPresenter;
import freedom.nightq.wts.app.baseView.BaseMVPView;


/**
 * Created by Nightq on 16/8/9.
 */

public interface MainContract {

    interface View extends BaseMVPView {

    }

    class Presenter extends BaseMVPPresenter<View> {

    }
}
