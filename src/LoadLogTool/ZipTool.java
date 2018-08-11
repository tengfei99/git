package LoadLogTool;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipOutputStream;

/**
 * 功能：压缩指定的文件
 * 
 * @author lishicun l00101203
 * 
 */
public class ZipTool
{

    /**
     * 功能：压缩文件
     * 
     * @param filepath 待压缩的文件所在目录
     * @param zipFilename 压缩后的文件名
     * @param sourceFileName 待压缩的文件
     * 
     * @throws Exception
     */
    public void createFilesToZip(String filepath, String zipFilename,
            String[] sourceFileName)
    {
        System.out.println("Info：now, begin compress file(s) .......");
        try
        {
            byte[] buf = new byte[1024];

            // 压缩文件名
            File objFile = new File(filepath + zipFilename);

            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
                    objFile));

            ZipEntry ze = null;

            for (int i = 0; i < sourceFileName.length; i++)
            {
                File sourceFile = new File(filepath + sourceFileName[i]);

                // 文件不存在则跳过继续下一文件处理
                if (!sourceFile.exists())
                {
                    continue;
                }

                // 创建一个ZipEntry，并设置Name和其它的一些属性
                ze = new ZipEntry(sourceFile.getName());
                ze.setSize(sourceFile.length());
                ze.setTime(sourceFile.lastModified());

                // 将ZipEntry加到zos中，再写入实际的文件内容
                zos.putNextEntry(ze);

                InputStream is = new BufferedInputStream(new FileInputStream(
                        sourceFile));

                sourceFile = null;
                ze = null;

                int readLen = -1;
                while ((readLen = is.read(buf, 0, 1024)) != -1)
                {
                    zos.write(buf, 0, readLen);
                }
                is.close();

            }
            zos.close();

            System.out.println("Info：Compress file success!");
            System.exit(0);
        }
        catch (ZipException ze)
        {
            System.out
                    .println("Warn：ZIP file must have at least one entry to Compress！");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
