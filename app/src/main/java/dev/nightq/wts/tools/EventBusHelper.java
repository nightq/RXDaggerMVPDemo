package dev.nightq.wts.tools;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Nightq on 16/9/13.
 */

public class EventBusHelper {

    /**
     * get stick event and remove
     */
    public static <T> T getStickEventAndRemove (Class<T> classType) {
        T data = EventBus.getDefault().getStickyEvent(classType);
        EventBus.getDefault().removeStickyEvent(classType);
        return data;
    }

}
