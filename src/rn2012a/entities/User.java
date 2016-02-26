package rn2012a.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class User
{
    private String username;
    private String password;

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    private static String separator = "--";

//    private String filepath = this.getClass().getClassLoader().getResource("").getPath().replace("%20", " ") + "files/user/";
    private String filepath = "C:/prjData/files/user/";
    private String filename = "user1.txt";
    
    @Override
    public String toString()
    {
        return "User [" + username + separator + password + "]";
    }

    public void save(String username, String password)
    {
        this.password = password;
        this.username = username;
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
                if (!file.getParentFile().mkdirs())
                {
                    System.out.println("创建目录失败！");
                    return;
                }
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
        String s = username + separator + password + "\r\n";
        try
        {
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write(s);
            fileWriter.close();
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public boolean userValidation(String username, String password)
    {
        this.username = username;
        this.password = password;
        File file = new File(filepath + filename);
        if (!file.exists())
        {
            System.out.println( filename + "不存在！");
            return false;
        }
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(file));
            try
            {
                String tmpStr = null;
                String[] data = null;
                while ((tmpStr = reader.readLine()) != null)
                {
                    data = tmpStr.split(separator);
                    if (username.equals(data[0]) && password.equals(data[1]))
                    {
                        System.out.println("验证成功！");
                        return true;
                    }
                }
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (FileNotFoundException e)
        {
            System.out.println( filename + "读取失败！");
            e.printStackTrace();
        } finally {
            try
            {
                reader.close();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return false;
    }
}
