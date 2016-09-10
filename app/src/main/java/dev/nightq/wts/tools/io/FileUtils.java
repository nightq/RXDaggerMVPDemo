package dev.nightq.wts.tools.io;

import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import dev.nightq.wts.R;
import dev.nightq.wts.tools.LogHelper;
import dev.nightq.wts.tools.ResourceHelper;
import rx.Single;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * 专门 file 的操作工具
 * 包括文件的获取 删除 修改
 */
public class FileUtils {


    /**
     * 返回照片文件夹
     * 如果外部存储不可用返回null，如果文件夹不存在，则依路径创建,主要是为了存储下载的照片和拍照。
     *
     * @param filename
     * @return 需要处理返回null得状况
     */
    public static String getDCIMPath(String filename) throws IOException {
        String dcimDir = FileDirUtils.getDCIMDir();
        if (!TextUtils.isEmpty(dcimDir)) {
            return dcimDir + "/" + filename;
        } else {
            IOExceptionUtil.throwDenyError("DCIM");
            return null;
        }
    }

    /**
     * 获取对应的 file name
     *
     * @throws IOException
     */
    public static String getImageFile() throws IOException {
        return getFile(FileDirUtils.getImageCache(), System.currentTimeMillis() + ".jpg");
    }

    /**
     * 获取对应的 file name
     *
     * @param fileName
     * @throws IOException
     */
    public static String getImageFile(String fileName) throws IOException {
        return getFile(FileDirUtils.getImageCache(), fileName);
    }

    /**
     * 获取对应的 file name
     *
     * @param dir
     * @param fileName
     * @return
     * @throws IOException
     */
    private static String getFile(File dir, String fileName) throws IOException {
        if (dir == null || !dir.exists()) {
            IOExceptionUtil.throwDirError("dir");
        }
        return new File(dir, fileName).getPath();
    }


    /**
     * 删除文件
     *
     * @param filepath
     * @IOThread
     */
    public static void deleteFile(String filepath) {
        if (TextUtils.isEmpty(filepath) || filepath.startsWith("http")) {
            return;
        }

        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 任何线程
     *
     * @param filepath
     */
    public static void asyncDeleteFile(String filepath) {
        if (TextUtils.isEmpty(filepath) || filepath.startsWith("http")) {
            return;
        }
        Single.just(filepath)
                .observeOn(Schedulers.io())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String filepath) {
                        File file = new File(filepath);
                        if (file.exists()) {
                            file.delete();
                        }
                    }
                });
    }

    /**
     * 递归删除文件和文件夹
     * @param file 要删除的根目录
     */
    public static void deleteFileAll(File file, boolean deleteSelf) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            // 删除子文件
            File[] childFile = file.listFiles();
            if (childFile != null && childFile.length > 0) {
                for (File f : childFile) {
                    deleteFileAll(f, true);
                }
            }
            // 删除自己
            if (deleteSelf) {
                file.delete();
            }
        }
    }

    /**
     * 获取文件目录大小
     * @param file
     * @return
     */
    public static long getFolderSize(File file) {
        if (file == null || !file.exists()) {
            return 0;
        }
        if (file.isFile()) {
            return file.length();
        }
        File[] childFiles = file.listFiles();
        long size = 0;
        for (File f : childFiles) {
            size += getFolderSize(f);
        }
        return size;
    }

    /**
     * Copies a file to another location.
     */
    public static void copyFile(String fromFilename, String toFilename) throws IOException {
        copyFile(new File(fromFilename), new File(toFilename));
    }

    /**
     * copy
     *
     * @param source
     * @param dest
     * @throws IOException
     */
    public static void copyFile(File source, File dest) throws IOException {
        FileChannel input = null;
        FileChannel output = null;
        try {
            input = new FileInputStream(source).getChannel();
            output = new FileOutputStream(dest).getChannel();
            output.transferFrom(input, 0, input.size());
        } finally {
            input.close();
            output.close();
        }
    }

    /**
     * 移动单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static boolean moveFile(String oldPath, String newPath) {
        final File tempFile = new File(oldPath);
        boolean flag = tempFile.renameTo(new File(newPath));
        if (flag) {
            flag = true;
        } else {
            try {
                copyFile(oldPath, newPath);
                flag = true;
            } catch (IOException e) {
                //
                LogHelper.e(ResourceHelper.getString(R.string.base_io_exception));
            }
        }
        return flag;
    }


}