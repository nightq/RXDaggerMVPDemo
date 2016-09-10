package dev.nightq.wts.app.module.global;

import dagger.Module;
import dagger.Provides;
import dev.nightq.wts.app.WTSApplication;
import dev.nightq.wts.app.scope.ActivityScope;
import dev.nightq.wts.repository.GlobalSPRepository;

/**
 * Created by Nightq on 16/4/25.
 */
@Module
public class AppModule {

    private WTSApplication application;

    public AppModule(WTSApplication application){
        this.application=application;
    }

    @Provides
    public WTSApplication provideApplication () {
        return application;
    }


//    @Provides
//    @ActivityScope
//    public GlobalSPRepository provideGlobalSPRepository (GlobalSPRepository globalSPRepository) {
//        return globalSPRepository;
//    }

}
