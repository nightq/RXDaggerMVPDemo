package dev.nightq.wts.model.base;

/**
 */
public class BaseUser extends BaseModel {
    /**
     * 是否是本地的用户
     */
    public boolean isLocal;

    public long id;

    /**
     * 获取id
     * @return
     */
    public String getIdString () {
        return String.valueOf(id);
    }

    public BaseUser() {
    }

}
