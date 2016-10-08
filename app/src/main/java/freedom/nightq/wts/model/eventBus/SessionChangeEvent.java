package freedom.nightq.wts.model.eventBus;

/**
 * Created by Nightq on 16/9/10.
 * 登录状态变化
 */

public class SessionChangeEvent {

    public boolean haveSession;

    public SessionChangeEvent() {
    }

    public SessionChangeEvent(boolean haveSession) {
        this.haveSession = haveSession;
    }
}
