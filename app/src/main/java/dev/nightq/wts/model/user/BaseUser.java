package dev.nightq.wts.model.user;

import dev.nightq.wts.model.base.BaseModel;

/**
 */
public class BaseUser extends BaseModel {

    /**
     * 是否是本地的用户
     */
    public String id;

    /**
     * 是否是本地的用户
     */
    public boolean isLocal;

    public static final String DEFAULT_ID = "TMP_USER_ID";
    public static final String DEFAULT_USER_NAME = "临时用户"+System.currentTimeMillis();

    /**
     * 获取id
     * @return
     */
    public String getId() {
        return id == null ? DEFAULT_ID : id;
    }

    /**
     * 是不是临时用户
     * @return
     */
    public boolean isTempUser() {
        return isLocal;
    }
}
