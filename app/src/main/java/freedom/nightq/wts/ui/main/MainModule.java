package freedom.nightq.wts.ui.main;


import dagger.Module;
import freedom.nightq.wts.app.baseView.DaggerBaseModule;

/**
 * Created by Nightq on 16/8/22.
 */

@Module
public class MainModule extends DaggerBaseModule<MainContract.View> {

    public MainModule(MainContract.View view) {
        super(view);
    }

//    @Provides
//    UserSPRepository provideuserff(UserSPRepository userSPRepository) {
//        return userSPRepository;
//    }


}
