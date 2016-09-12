package dev.nightq.wts.model.user;

import com.avos.avoscloud.AVUser;

/**
 * Created by Nightq on 16/9/7.
 */

public class User extends BaseUser {

    public AVUser mAVUser;

    public User() {
        isLocal = false;
    }

    public User(String id) {
        this.id = id;
        isLocal = false;
    }

    public User(AVUser mAVUser) {
        this.mAVUser = mAVUser;
        isLocal = false;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    public String getUsername () {
        return mAVUser != null ? mAVUser.getUsername() : DEFAULT_USER_NAME;
    }

}
