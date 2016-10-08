package freedom.nightq.wts.tools.io;

import android.content.Context;
import android.graphics.Bitmap;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 读写的工具
 */
public class FileIOHelper {

    /**
     * 图片保存文件质量
     */
    public static final int BMP_TO_FILE_QUALITY = 80;

    /**
     * 保存bitmap到文件
     *
     * @param filePath
     * @param bmp
     * @return
     * @throws Exception
     */
    public static void saveBitmapToFile(
            String filePath,
            Bitmap bmp) throws Exception {
        File myCaptureFile = new File(filePath);
        BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(myCaptureFile));
        bmp.compress(Bitmap.CompressFormat.JPEG, BMP_TO_FILE_QUALITY, bos);
        bos.flush();
        bos.close();
    }

    /**
     * copy assert
     *
     * @param context
     * @param assetFilePath
     * @param sdCardFilePath
     * @return
     */
    public static boolean copyAssetFileToSDCard(Context context, String assetFilePath, String sdCardFilePath) {
        InputStream is = null;
        FileOutputStream destination = null;
        try {
            is = context.getAssets().open(assetFilePath);
            File destinationFile = new File(sdCardFilePath);
            destinationFile.getParentFile().mkdirs();

            destination = new FileOutputStream(destinationFile);
            byte[] buffer = new byte[1024];
            int nread;

            while ((nread = is.read(buffer)) != -1) {
                if (nread == 0) {
                    nread = is.read();
                    if (nread < 0)
                        break;
                    destination.write(nread);
                    continue;
                }
                destination.write(buffer, 0, nread);
            }

            destination.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (destination != null) {
                try {
                    destination.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

}
