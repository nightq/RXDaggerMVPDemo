package dev.nightq.wts.ui.main;

import dev.nightq.wts.app.baseView.BaseMVPPresenter;
import dev.nightq.wts.app.baseView.BaseMVPView;


/**
 * Created by Nightq on 16/8/9.
 */

public interface MainContract {


    interface View extends BaseMVPView<Presenter> {

    }

    interface Presenter extends BaseMVPPresenter {

    }
}
