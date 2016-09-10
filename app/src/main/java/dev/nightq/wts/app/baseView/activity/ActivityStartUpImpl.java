package dev.nightq.wts.app.baseView.activity;

import android.os.Bundle;

/**
 * Created by Nightq on 16/9/8.
 *
 * 这个类是使用
 * @see #getIntentDataInActivityBase(Bundle) 获取intent 的内容
 * @see #onCreateBase() 获取 layout id
 * @see #initActivityBaseView() 初始化 view
 * @see #setupComponent 构建 Component
 * @see #loadDataOnCreate() 加载数据
 * 这样的方式加载界面的
 *
 */

public interface ActivityStartUpImpl {

    /**
     * 在setcontentview之前，需要从getintent里面获取的数据
     * 首先，activity再这里获取getIntent()里面的数据，并储存。
     *
     * @return IN 主线程。
     */
    void getIntentDataInActivityBase(Bundle savedInstanceState);

    /**
     * 代替activity的onCreate。只用返回layout id
     * 这里只为了向ActivityBase传入root layout的id。
     * IN 主线程。
     */
    int onCreateBase();

    /**
     * 初始化view，完全不包含任何数据，只为了显示初始化的界面
     * ActivityBase在从onCreateBase获取到要显示的root layout之后，在这个方法里面可以进行对应的view的find和初始化。
     * 因为这里是主线程，所以不要把需要从异步线程获取数据再显示的操作放在这里。
     * IN 主线程。
     */
    void initActivityBaseView();

    /**
     * 在OnCreate中加载数据到view
     * IN 主线程。
     */
    void loadDataOnCreate();

    /**
     * 建立 Component
     */
    void setupComponent();

}
