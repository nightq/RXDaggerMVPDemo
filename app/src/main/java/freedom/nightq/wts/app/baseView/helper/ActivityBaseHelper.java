package freedom.nightq.wts.app.baseView.helper;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.avos.avoscloud.AVAnalytics;
import com.umeng.analytics.MobclickAgent;

import freedom.nightq.wts.tools.InputHelper;


/**
 * Created by Nightq on 14-8-16.
 */
public class ActivityBaseHelper {

    /**
     * 使用类似facebook的uihelper的方式来管理界面的生命周期的调用。这样使每一个activity或者fragment更简洁。
     * 在对应的生命周期方法调用
     *
     * @param context
     */
    public static void onPause(Context context) {
        MobclickAgent.onPause(context);
    }

    /**
     * 使用类似facebook的uihelper的方式来管理界面的生命周期的调用。这样使每一个activity或者fragment更简洁。
     * 在对应的生命周期方法调用
     *
     * @param context
     */
    public static void onResume(Context context) {
        MobclickAgent.onResume(context);
    }


    /**
     * 在对应的生命周期方法调用
     *
     * @param context
     */
    public static void onCreate(Activity context, Bundle savedInstanceState) {
        /**
         * 判断是否需要向服务器标记通知已读
         */
//        Notifier.targetRead(context,
//                context.getIntent().getLongExtra(Constants.NOTIFICATION_ID, -1));
//        Global.initialize(context);
        // 为了 统计
        AVAnalytics.trackAppOpened(context.getIntent());
    }

    /**
     * 使用类似facebook的uihelper的方式来管理界面的生命周期的调用。这样使每一个activity或者fragment更简洁。
     * 在对应的生命周期方法调用
     *
     * @param context
     */
    public static void onStart(Activity context) {
        // nothing
    }


    /**
     * 使用类似facebook的uihelper的方式来管理界面的生命周期的调用。这样使每一个activity或者fragment更简洁。
     * 在对应的生命周期方法调用
     *
     * @param context
     */
    public static void onStop(Activity context) {
        InputHelper.hideSoftInput(context);
    }

    /**
     * 使用类似facebook的uihelper的方式来管理界面的生命周期的调用。这样使每一个activity或者fragment更简洁。
     * 在对应的生命周期方法调用
     *
     * @param context
     */
    public static void onDestroy(Activity context) {
        //这句可能不需要了～～～因为后台回收这个会导致界面上可能其他地方显示的dialog被消失，不合理。
        ActivityDialogHelper.hideDialog(context);
    }

    /**
     * 设置actionbutton
     *
     * @param id         需要设置的actionbutton的id
     * @param menu       传入menu
     * @param drawableId actionbutton的图片资源
     */
    public static void setActionButton(Menu menu, int id, int drawableId) {
        if (menu == null) {
            return;
        }
        final MenuItem item = menu.findItem(id);
        if (item != null) {
            item.setIcon(drawableId);
        }
    }

    /**
     * 设置actionbutton
     *
     * @param id     需要设置的actionbutton的id
     * @param menu   传入menu
     * @param enable
     */
    public static void setActionButtonEnabled(Menu menu, int id, boolean enable) {
        if (menu == null) {
            return;
        }
        final MenuItem item = menu.findItem(id);
        if (item != null) {
            item.setEnabled(enable);
        }
    }

    /**
     * 设置actionbutton
     *
     * @param id      需要设置的actionbutton的id
     * @param menu    传入menu
     * @param visible actionbutton的显示
     */
    public static void setActionButtonVisible(Activity activity, Menu menu, int id, boolean visible) {
        if (menu == null || activity == null) {
            return;
        }
        final MenuItem item = menu.findItem(id);
        if (item != null) {
            item.setVisible(visible);
            try {
//                activity.invalidateOptionsMenu();
            } catch (Exception e) {

            }
        }
    }
}
