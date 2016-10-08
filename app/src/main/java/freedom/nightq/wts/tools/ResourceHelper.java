package freedom.nightq.wts.tools;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.view.View;

import java.io.InputStream;

import freedom.nightq.wts.R;
import freedom.nightq.wts.app.BaseApplication;

/**
 * Created by Nightq on 16/9/7.
 */

public class ResourceHelper {

    public static Resources getResource() {
        return BaseApplication.getInstance().getResources();
    }

    public static int getColorResource(@ColorRes int resource) {
        return ContextCompat.getColor(BaseApplication.getInstance(), resource);
    }

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static ColorStateList getColorStateList(int colorStateRes) {
        return getResource().getColorStateList(colorStateRes);
    }

    public static int getAppMainColor() {
        return getColorResource(R.color.app_main_color);
    }

    public static float getDimension(int dimension) {
        return getResource().getDimension(dimension);
    }

    public static Drawable getDrawable(int resource) {
        Drawable drawable = null;
        try {
            drawable = ContextCompat.getDrawable(BaseApplication.getInstance(), resource);
        } catch (Exception e) {
        }
        return drawable;
    }

    public static String getString(int resId, Object... args) {
        return getResource().getString(resId, args);
    }

    public static String[] getStringArray(int resource) {
        return getResource().getStringArray(resource);
    }

    public static int[] getIntArray(int resource) {
        return getResource().getIntArray(resource);
    }

    public static Integer[] getResourceArray(int resource) {
        TypedArray imgs = getResource().obtainTypedArray(resource);
        Integer[] resArr = new Integer[imgs.length()];
        for (int n = 0; n < imgs.length(); n++) {
            resArr[n] = imgs.getResourceId(n, -1);
        }
        imgs.recycle();
        return resArr;
    }

    public static InputStream openRawResource(int resource) {
        return getResource().openRawResource(resource);
    }

    public static String color2String(int color) {
        String colorStr = Integer.toHexString(color);
        return '#' + colorStr.substring(2);
    }
}
