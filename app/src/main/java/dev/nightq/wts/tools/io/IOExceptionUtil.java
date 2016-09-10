package dev.nightq.wts.tools.io;

import java.io.IOException;

import dev.nightq.wts.R;
import dev.nightq.wts.tools.ResourceHelper;

/**
 * Created by Nightq on 16/9/8.
 */

public class IOExceptionUtil {

    /**
     * 抛出 普通 IO 异常
     *
     * @throws IOException
     */
    public static void throwIOError(String msg) throws IOException {
        throw new IOException(ResourceHelper.getString(R.string.base_io_exception, msg));
    }

    /**
     * 文件创建失败
     *
     * @throws IOException
     */
    public static void throwFileMakeError(String msg) throws IOException {
        throw new IOException(ResourceHelper.getString(R.string.base_file_exception, msg));
    }

    /**
     * 目录获取失败
     *
     * @throws IOException
     */
    public static void throwDirError(String msg) throws IOException {
        throw new IOException(ResourceHelper.getString(R.string.base_dir_exception, msg));
    }

    /**
     * 没有权限
     *
     * @throws IOException
     */
    public static void throwDenyError(String msg) throws IOException {
        throw new IOException(ResourceHelper.getString(R.string.base_deny_exception, msg));
    }
}
