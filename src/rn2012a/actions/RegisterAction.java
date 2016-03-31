package rn2012a.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.opensymphony.xwork2.ActionSupport;

import rn2012a.entities.User;

public class RegisterAction extends ActionSupport
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String username;

    private String password;

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

//    private User2file user2file;
//
//    public void setUser2file(User2file user2file)
//    {
//        this.user2file = user2file;
//    }

    private User userService;
    
    public void setUserService(User userService)
    {
        this.userService = userService;
    }
    
    @Override
    public String execute() throws Exception
    {
        System.out.println("username:" + username + "||password:" + password);
        userService.save(username, password);
        return SUCCESS;
    }

    private InputStream inputStream;

    public InputStream getResult() {
        return inputStream;
    }
    
    public String exist() throws UnsupportedEncodingException
    {
        if (userService.IsUsersEmpty())
        {
            userService.loadUsers();
        }
        inputStream = userService.Exist(username) 
                ? new ByteArrayInputStream("1".getBytes("UTF-8"))
                : new ByteArrayInputStream("0".getBytes("UTF-8"));
        return SUCCESS;
    }
    
    
}
