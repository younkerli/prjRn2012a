package rn2012a.entities;

import java.io.File;
import java.io.IOException;

public class FileOperate
{
    public File initFile(String filepath, String filename )
    {
        File file = new File(filepath + filename);
        if (!file.exists())
        {
            System.out.println("文件不存在！");
            if (file.getParentFile().exists())
            {
                System.out.println("目录已经存在！");
            } else
            {
                System.out.println("目录不存在！");
//                if (!file.getParentFile().mkdirs())
//                {
//                    System.out.println("创建目录失败！");
//                    return file;
//                }
                file.getParentFile().mkdirs();
            }

            try
            {
                file.createNewFile();
            } catch (IOException e)
            {
                System.out.println("文件创建失败！");
                e.printStackTrace();
            }
        }
        return file;
    }
}
