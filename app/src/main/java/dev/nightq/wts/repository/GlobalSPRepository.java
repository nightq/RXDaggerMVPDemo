package dev.nightq.wts.repository;

import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import dev.nightq.wts.app.WTSApplication;
import dev.nightq.wts.tools.Constants;

/**
 * Created by Nightq on 16/9/7.
 */

@Singleton
public class GlobalSPRepository {

    /**
     * global 本地的 存储
     */
    SharedPreferences mGlobalSP;

    @Inject
    public GlobalSPRepository(WTSApplication application) {
        mGlobalSP = application.getSharedPreferences(
                Constants.Config.GLOBAL_SP,
                0);
    }

    public SharedPreferences getGlobalSP() {
        return mGlobalSP;
    }

}
