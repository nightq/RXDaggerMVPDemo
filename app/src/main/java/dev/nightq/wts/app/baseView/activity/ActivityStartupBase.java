package dev.nightq.wts.app.baseView.activity;

import android.os.Bundle;

/**
 * 这个类是使用
 * @see ActivityStartUpImpl 这样的方式加载界面的
 */
public abstract class ActivityStartupBase
        extends AppCompatBaseActivity
        implements ActivityStartUpImpl {

    public int currentLayoutId;

    /**
     * 需要处理getintent的数据
     * 需要处理设置setcontentview
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getIntentDataInActivityBase(savedInstanceState);
        super.onCreate(savedInstanceState);
        currentLayoutId = onCreateBase();
        if (currentLayoutId > 0) {
            setContentView(currentLayoutId);
        } else {
            initActivityBaseView();
        }
        setupComponent();
        loadDataOnCreate();
    }

    /**
     * 这里会调用initActivityBaseView，初始化view
     * @param layoutResID
     */
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        initActivityBaseView();
    }
}
