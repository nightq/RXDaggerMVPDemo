package dev.nightq.wts.repository;

import android.content.SharedPreferences;
import android.text.TextUtils;

import com.avos.avoscloud.AVUser;

import javax.inject.Inject;
import javax.inject.Singleton;

import dev.nightq.wts.app.WTSApplication;
import dev.nightq.wts.model.user.User;
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

    /**
     * 获取当前登录的账户
     * @return
     */
    public User getCurrentUser() {
        String userAccount = mGlobalSP.getString(Constants.UC.USER_ACCOUT, null);
        if (TextUtils.isEmpty(userAccount)) {
            return new User();
        }
        User user = new User(userAccount);
        user.isLocal = false;
        AVUser.getCurrentUser();
        return user;
    }


    /**
     * 设置当前登录的账户
     * @return
     */
    public void saveCurrentUser(User user) {
        if (user == null) {
            mGlobalSP.edit().remove(Constants.UC.USER_ACCOUT).apply();
        } else {
            mGlobalSP.edit().putString(Constants.UC.USER_ACCOUT, user.id).apply();
        }
    }

}
