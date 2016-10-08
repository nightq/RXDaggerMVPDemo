package freedom.nightq.wts.tools;

import android.support.annotation.StringRes;
import android.widget.Toast;

import freedom.nightq.wts.app.BaseApplication;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;

/**
 * Created by Nightq on 16/9/7.
 */

public class ToastHelper {
    public static void show(@StringRes int contentRes) {
        show(ResourceHelper.getString(contentRes));
    }

    public static void show(final CharSequence content) {
        AndroidSchedulers.mainThread().createWorker().schedule(new Action0() {
            @Override
            public void call() {
                Toast.makeText(
                        BaseApplication.getInstance(),
                        content,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
