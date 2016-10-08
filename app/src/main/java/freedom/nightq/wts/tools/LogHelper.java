package freedom.nightq.wts.tools;

import android.util.Log;

import freedom.nightq.wts.BuildConfig;


public class LogHelper {

    public static final boolean mDebug = BuildConfig.DEBUG;

    @SuppressWarnings("rawtypes")
    public static String makeLogTag(Class cls) {
        return cls.getSimpleName();
    }

    public static void d(String tag, String message) {
        if (mDebug) {
            Log.d(tag, message);
        }
    }

    public static void d(String tag, String format, Object... args) {
        if (mDebug) {
            Log.d(tag, String.format(format, args));
        }
    }

    public static void e(String tag, String message) {
        if (mDebug && message != null) {
            Log.e(tag, message);
        }
    }

    public static void e(String msg) {
        if (mDebug && msg != null) {
            Log.e("NightQ", msg);
        }
    }

    public static void e(String tag, String format, Object... args) {
        if (mDebug && format != null) {
            Log.e(tag, String.format(format, args));
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (mDebug && msg != null) {
            Log.e(tag, msg, tr);
        }
    }

    public static void i(String tag, String message) {
        if (mDebug && message != null) {
            Log.i(tag, message);
        }
    }

    public static void i(String tag, String format, Object... args) {
        if (mDebug && format != null) {
            Log.i(tag, String.format(format, args));
        }
    }

    public static void v(String tag, String format, Object... args) {
        if (mDebug && format != null) {
            if (args.length > 0) {
                Log.v(tag, String.format(format, args));
            } else {
                Log.v(tag, format);
            }
        }
    }

    public static void w(String tag, String format, Object... args) {
        if (mDebug) {
            if (args.length > 0) {
                Log.w(tag, String.format(format, args));
            } else {
                Log.w(tag, format);
            }
        }
    }
}
