package dev.nightq.wts.app;

import android.support.multidex.MultiDexApplication;

import com.avos.avoscloud.AVOSCloud;

/**
 * Created by Nightq on 16/9/6.
 */

public class BaseApplication extends MultiDexApplication {

    private static BaseApplication sInstance;

    public static BaseApplication getInstance() {
        return sInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        // 初始化参数依次为 this, AppId, AppKey
        AVOSCloud.initialize(this,"RhyOsjDNV4bEXtBseEIqDJ5m-gzGzoHsz","D4z9vfiJURKYhSDscxAhte1T");
    }

}
