/*******************************************************************************
 * Copyright 2011-2013 Sergey Tarasevich
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package freedom.nightq.wts.tools.io;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;

import freedom.nightq.wts.app.BaseApplication;
import freedom.nightq.wts.tools.LogHelper;

import static android.os.Environment.MEDIA_MOUNTED;

/**
 * Provides application storage paths
 * 文件存储系统的工具
 * 管理文件目录
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 * @since 1.0.0
 */
public final class FileDirUtils {

    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";

    /**
     * Returns application cache directory. Cache directory will be created on SD card
     * <i>("/Android/data/[app_package_name]/cache")</i> if card is mounted and app has appropriate permission. Else -
     * Android defines cache directory on device's file system.
     *
     * @param context Application context
     * @return Cache {@link java.io.File directory}.<br />
     * <b>NOTE:</b> Can be null in some unpredictable cases (if SD card is unmounted and
     * {@link android.content.Context#getCacheDir() Context.getCacheDir()} returns null).
     */
    private static File getCacheDirectory(Context context) {
        return getCacheDirectory(context, true);
    }

    /**
     * Returns application cache directory. Cache directory will be created on SD card
     * <i>("/Android/data/[app_package_name]/cache")</i> (if card is mounted and app has appropriate permission) or
     * on device's file system depending incoming parameters.
     *
     * @param context        Application context
     * @param preferExternal Whether prefer external location for cache
     * @return Cache {@link java.io.File directory}.<br />
     * <b>NOTE:</b> Can be null in some unpredictable cases (if SD card is unmounted and
     * {@link android.content.Context#getCacheDir() Context.getCacheDir()} returns null).
     */
    private static File getCacheDirectory(Context context, boolean preferExternal) {
        File appCacheDir = null;
        if (preferExternal && MEDIA_MOUNTED
                .equals(Environment.getExternalStorageState())
                && hasExternalStoragePermission(context)) {
            appCacheDir = getExternalCacheDir(context);
        }
        if (appCacheDir == null) {
            appCacheDir = context.getCacheDir();
        }
        if (appCacheDir == null) {
            appCacheDir = getRootCacheDir(context);
        }
        return appCacheDir;
    }

    /**
     * 使用外部的 cache
     * @param context
     * @return
     */
    private static File getExternalCacheDir(Context context) {
        File dataDir = new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data");
        File appCacheDir = new File(new File(dataDir, context.getPackageName()), "cache");
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                Log.w("StorageUtils", "Unable to create external cache directory");
                return null;
            }
            try {
                new File(appCacheDir, ".nomedia").createNewFile();
            } catch (IOException e) {
                Log.i("StorageUtils", "Can't create \".nomedia\" file in application external cache directory");
            }
        }
        return appCacheDir;
    }

    /**
     * 使用根目录的 cache
     * @param context
     * @return
     */
    private static File getRootCacheDir(Context context) {
        String cacheDirPath = "/data/data/" + context.getPackageName() + "/cache/";
        LogHelper.e("Can't define system cache directory! '%s' will be used.", cacheDirPath);
        File appCacheDir = new File(cacheDirPath);
        if (!appCacheDir.exists()) {
            if (!appCacheDir.mkdirs()) {
                Log.w("StorageUtils", "Unable to create external cache directory");
                return null;
            }
            try {
                new File(appCacheDir, ".nomedia").createNewFile();
            } catch (IOException e) {
                Log.i("StorageUtils", "Can't create \".nomedia\" file in application external cache directory");
            }
        }
        return appCacheDir;
    }


    /**
     * 是否又权限
     * @param context
     * @return
     */
    private static boolean hasExternalStoragePermission(Context context) {
        int perm = context.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
        return perm == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * 获取对应的 cache 文件夹
     * @return
     */
    private static File getCacheDir(String folderName) throws IOException {
        File cacheDir = getCacheDirectory(BaseApplication.getInstance());
        if (cacheDir == null || !cacheDir.exists()) {
            IOExceptionUtil.throwDirError("dir cache");
        }
        File childCache = new File(cacheDir, folderName);
        if (!childCache.exists()) {
            if (!childCache.mkdirs()) {
                IOExceptionUtil.throwDirError("dir " + folderName);
            }
        }
        return childCache;
    }

	/**
	 * 存放 图片的 cache 的文件夹
	 **/
	public static File getImageCache() throws IOException{
		return getCacheDir("images");
	}


    /**
     * 返回照片文件夹 如果外部存储不可用返回null，如果文件夹不存在，则依路径创建,主要是为了存储下载的照片和拍照。
     *
     * @return 需要处理返回null得状况
     */
    public static String getDCIMDir() throws IOException{
        StringBuilder basePath = new StringBuilder();
        if (isExternalStorageWriteable()) {
            basePath.append(
                    Environment
                            .getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM))
                    .append("/camera");
        } else {
            IOExceptionUtil.throwDenyError("DCIM");
            return null;
        }

        File dir = new File(basePath.toString());
        boolean success = true;
        if (!dir.exists()) {
            try {
                success = dir.mkdirs();
            } catch (SecurityException e) {
                // do nothing
            }
        }
        if (success) {
            return basePath.toString();
        } else {
            IOExceptionUtil.throwDenyError("DCIM");
            return null;
        }
    }

    /**
     * 外部存储是否可写
     *
     * @return
     */
    private static boolean isExternalStorageWriteable() {
        return Environment.MEDIA_MOUNTED.equals(Environment
                .getExternalStorageState());
    }


}
