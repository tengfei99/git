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
 * ���ܣ�ѹ��ָ�����ļ�
 * 
 * @author lishicun l00101203
 * 
 */
public class ZipTool
{

    /**
     * ���ܣ�ѹ���ļ�
     * 
     * @param filepath ��ѹ�����ļ�����Ŀ¼
     * @param zipFilename ѹ������ļ���
     * @param sourceFileName ��ѹ�����ļ�
     * 
     * @throws Exception
     */
    public void createFilesToZip(String filepath, String zipFilename,
            String[] sourceFileName)
    {
        System.out.println("Info��now, begin compress file(s) .......");
        try
        {
            byte[] buf = new byte[1024];

            // ѹ���ļ���
            File objFile = new File(filepath + zipFilename);

            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
                    objFile));

            ZipEntry ze = null;

            for (int i = 0; i < sourceFileName.length; i++)
            {
                File sourceFile = new File(filepath + sourceFileName[i]);

                // �ļ�������������������һ�ļ�����
                if (!sourceFile.exists())
                {
                    continue;
                }

                // ����һ��ZipEntry��������Name��������һЩ����
                ze = new ZipEntry(sourceFile.getName());
                ze.setSize(sourceFile.length());
                ze.setTime(sourceFile.lastModified());

                // ��ZipEntry�ӵ�zos�У���д��ʵ�ʵ��ļ�����
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

            System.out.println("Info��Compress file success!");
            System.exit(0);
        }
        catch (ZipException ze)
        {
            System.out
                    .println("Warn��ZIP file must have at least one entry to Compress��");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
