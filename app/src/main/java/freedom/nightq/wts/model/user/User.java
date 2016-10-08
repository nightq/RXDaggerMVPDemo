package freedom.nightq.wts.model.user;

import com.avos.avoscloud.AVUser;

import freedom.nightq.wts.R;
import freedom.nightq.wts.tools.ToastHelper;

/**
 * Created by Nightq on 16/9/7.
 */

public class User extends BaseUser {

    /**
     * 是否是本地的用户
     */
    public boolean isLocal;

    public AVUser mAVUser;

    /**
     * 是不是临时用户
     *
     * @return
     */
    public boolean isTempUser() {
        return isLocal;
    }

    /**
     * 默认创建一个本地user
     */
    public User() {
        isLocal = true;
    }

    /**
     * 已经登录的 user
     *
     * @param mAVUser
     */
    public User(AVUser mAVUser) {
        this.mAVUser = mAVUser;
        isLocal = false;
    }

    @Override
    public String getId() {
        return mAVUser != null ? mAVUser.getObjectId() : DEFAULT_ID;
    }

    public String getUsername() {
        return mAVUser != null ? mAVUser.getUsername() : DEFAULT_USER_NAME;
    }

    /**
     * @return
     */
    public boolean checkValidSession(boolean showToast) {
        if (!isTempUser() && mAVUser != null) {
            return true;
        } else {
            if (showToast) {
                ToastHelper.show(R.string.base_no_session_need_login);
            }
            return false;
        }
    }
}
