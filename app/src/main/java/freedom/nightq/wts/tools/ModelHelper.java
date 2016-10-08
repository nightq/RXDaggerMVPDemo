package freedom.nightq.wts.tools;

import com.avos.avoscloud.AVObject;

/**
 * Created by Nightq on 16/9/13.
 */

public class ModelHelper {

    /**
     * 取数据
     */
    public static <T> T getDataFromAVO (AVObject object, String key, T defaultValue) {
        if (object == null || !object.containsKey(key)) {
            return defaultValue;
        }
        return (T) object.get(key);
    }

}
