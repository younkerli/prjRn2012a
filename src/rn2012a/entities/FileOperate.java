package rn2012a.entities;

import java.io.File;
import java.io.IOException;

public class FileOperate
{
    public static boolean initFile(String filepath, String filename )
    {
        
        File file = new File(filepath + filename);
        if (file.exists())
        {
            System.out.println(filename+ "已经存在！");
            return true;
        }
        else
        {
            System.out.println(filename + "不存在！");
            
            if (file.getParentFile().exists())
            {
                System.out.println("目录已经存在！");
            } else
            {
                System.out.println("目录不存在！");
                if (!file.getParentFile().mkdirs())
                {
                    System.out.println("创建目录失败！");
                    return false;
                } else {
                    System.out.println("创建目录成功！");
                }
//                file.getParentFile().mkdirs();
            }

            try
            {
                if (file.createNewFile())
                {
                    System.out.println(filename + "创建成功！");
                    return true;
                } 
            } catch (IOException e)
            {
                System.out.println("文件创建失败！");
                e.printStackTrace();
            }
            return false;
        }
    }
}
