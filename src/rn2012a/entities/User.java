package rn2012a.entities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class User extends FileOperate
{

    private static String separator = "--";

    // private String filepath =
    // this.getClass().getClassLoader().getResource("").getPath().replace("%20",
    // " ") + "files/user/";
    private String filepath = "C:/prjData/files/user/";
    private String filename = "user1.txt";

    public void save(String username, String password)
    {
        Users.put(username, password);
        saveUsers();
    }

    public boolean userValidation(String username, String password)
    {
        if (!Users.containsKey(username))
        {
            System.out.println("用户不存在！");
            return false;
        }
        if (!Users.get(username).equals(password))
        {
            System.out.println("密码错误！");
            return false;
        }
        return true;
    }

    private Map<String, String> Users = new HashMap<>();

    public void loadUsers()
    {
        File file = new File(filepath + filename);
        if (!file.exists())
        {
            System.out.println("文件不存在！");
            return;
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
                    Users.put(data[0], data[1]);
                }
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("文件读取失败！");
            e.printStackTrace();
        } finally
        {
            try
            {
                reader.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public void saveUsers()
    {
        String[] usernames = new String[Users.size()];
        Users.keySet().toArray(usernames);
        FileWriter fileWriter = null;
        try
        {
            fileWriter = new FileWriter(this.initFile(filepath, filename));
            String string = null;
            for (String name : usernames)
            {
                string = name + separator + Users.get(name) + "\r\n";
                fileWriter.write(string);
            }
        } catch (IOException e)
        {
            System.out.println("文件写入失败！");
            e.printStackTrace();
        } finally
        {
            try
            {
                fileWriter.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public User()
    {
        loadUsers();
    }

    public boolean Exist(String username)
    {
        return Users.containsKey(username);
    }
}
