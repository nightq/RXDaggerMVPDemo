package freedom.nightq.wts.tools;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Nightq on 16/9/8.
 */

public class InputHelper {

    /**
     * 显示输入键盘
     */
    public static void showInput(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void hideSoftInput(Context context, View v) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        //这里真的要用0才有效。。。～～～
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);//InputMethodManager.HIDE_IMPLICIT_ONLY
    }

    public static void hideSoftInput(android.app.Activity activity) {
        if (activity != null) {
            try {
                hideSoftInput(activity, activity.getCurrentFocus());
            } catch (Exception e) {
            }
        }
    }
}
