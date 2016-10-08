package freedom.nightq.wts.tools;

/**
 * Created by Nightq on 16/9/7.
 */

public interface Constants {

    /**
     * 常量
     */
    interface Config {
        String USER_SP = "user_sp";
        String GLOBAL_SP = "global_sp";
    }

    /**
     * global 常量
     */
    interface GC {
        String USER_SP = "user_sp";
        String GLOBAL_SP = "global_sp";
    }

    /**
     * user account 常量
     */
    interface UC {
        String USER_ACCOUT = "user_account";
    }

    /**
     * activity request code 常量
     */
    interface ActivityRequest {
        int AR_LOGIN = 10000;
        int AR_ARTICLE_PUBLISH = 10100;
        int AR_ARTICLE_READ = 10200;
        int AR_ARTICLE_LIST = 10300;
    }

    /**
     * intent extra name
     */
    interface Extra {
        String EXTRA_ID = "extra_id";
    }

}
