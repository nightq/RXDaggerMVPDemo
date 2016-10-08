package freedom.nightq.wts.app.module.global;

import dagger.Module;
import dagger.Provides;
import freedom.nightq.wts.app.WTSApplication;

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
