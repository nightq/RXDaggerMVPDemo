package dev.nightq.wts.app;

import com.avos.avoscloud.AVUser;

import dev.nightq.wts.model.user.User;

/**
 * Created by Nightq on 16/9/13.
 */

public class UserSessionHelper {

    /**
     * 退出
     */
    public static void logout () {
        AVUser.logOut();
        WTSApplication.getInstance().refreUserSessionAndSendEvent();
    }

    /**
     * 从 avuser 取得 user
     * @param avUser
     * @return
     */
    public static User getUserFromAVUser (AVUser avUser) {
        if (avUser == null) {
            return null;
        } else {
            return new User(avUser);
        }
    }

}
