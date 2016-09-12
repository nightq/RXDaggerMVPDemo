package dev.nightq.wts.repository;

import android.content.SharedPreferences;

import javax.inject.Inject;

import dev.nightq.wts.app.WTSApplication;
import dev.nightq.wts.app.scope.UserScope;
import dev.nightq.wts.model.user.User;
import dev.nightq.wts.tools.Constants;

/**
 * Created by Nightq on 16/9/7.
 */

@UserScope
public class UserSPRepository {

    /**
     * user 本地的 存储
     */
    SharedPreferences mUserSP;

    @Inject
    public UserSPRepository(
            WTSApplication application,
            User user) {
        mUserSP = application.getSharedPreferences(
                Constants.Config.USER_SP + user.getId(),
                0);
    }

    public SharedPreferences getUserSP() {
        return mUserSP;
    }
}
