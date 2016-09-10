package dev.nightq.wts.ui.main;


import dagger.Module;
import dagger.Provides;
import dev.nightq.wts.app.baseView.DaggerBaseModule;
import dev.nightq.wts.repository.UserSPRepository;

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
