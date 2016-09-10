package dev.nightq.wts.app.baseView.helper;

import android.app.Activity;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.Hashtable;

import dev.nightq.wts.R;
import dev.nightq.wts.app.baseView.dialog.ProgressBaseDialog;
import dev.nightq.wts.tools.ResourceHelper;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Nightq on 14-8-8.
 */
public class ActivityDialogHelper {
    public static Hashtable<String, DialogFragment> waitingTable = new Hashtable<>();
    public static Hashtable<String, DialogFragment> loadingTable = new Hashtable<>();

    /**
     * 显示可以取消的非全屏的waiting
     */
    public static void showWaitDialog(FragmentActivity activity) {
        String key = activity.getClass().getName();

        if (!waitingTable.contains(key)) {
            final ProgressBaseDialog dialog = ProgressBaseDialog.newInstance(
                    ResourceHelper.getString(R.string.base_waiting));
            waitingTable.put(key, dialog);
            Single.just(activity.getSupportFragmentManager()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<FragmentManager>() {
                        @Override
                        public void call(FragmentManager fm) {
                            dialog.show(fm);
                        }
                    });
        }
    }

    /**
     * 显示数据加载的Dialog
     *
     * @param activity
     */
    public static void showLoadDialog(FragmentActivity activity) {
        String key = activity.getClass().getName();
        if (!loadingTable.contains(key)) {
            final ProgressBaseDialog dialog = ProgressBaseDialog.newInstance();
            loadingTable.put(key, dialog);
            Single.just(
                    activity.getSupportFragmentManager()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<FragmentManager>() {
                        @Override
                        public void call(FragmentManager fm) {
                            dialog.show(fm);
                        }
                    });
        }
    }

    public static void hideDialog(Activity context) {
        hideWaitingDialog(context);
        hideLoadingDialog(context);
    }

    /**
     * 隐藏cancel的dialog,注意这里的uncancel的意思是不能点击背后,但是可以back取消
     *
     * @param activity
     */
    public static void hideWaitingDialog(Activity activity) {
        String key = activity.getClass().getName();
        DialogFragment view = waitingTable.remove(key);
        if (view != null) {
            Single.just(view).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<DialogFragment>() {
                        @Override
                        public void call(DialogFragment dialogFragment) {
                            dialogFragment.dismiss();
                        }
                    });
        }
    }

    /**
     * 隐藏数据加载的view
     *
     * @param activity
     */
    public static void hideLoadingDialog(Activity activity) {
        String key = activity.getClass().getName();
        DialogFragment view = loadingTable.remove(key);
        if (view != null) {
            Single.just(view).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<DialogFragment>() {
                        @Override
                        public void call(DialogFragment dialogFragment) {
                            dialogFragment.dismiss();
                        }
                    });
        }
    }
}
