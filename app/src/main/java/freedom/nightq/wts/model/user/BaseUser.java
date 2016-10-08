package freedom.nightq.wts.model.user;

import freedom.nightq.wts.model.base.BaseModel;

/**
 */
public class BaseUser extends BaseModel {

    public static final String DEFAULT_ID = "TMP_USER_ID";
    public static final String DEFAULT_USER_NAME = "临时用户"+System.currentTimeMillis();

    public String getId() {
        return DEFAULT_ID;
    }
}
