package rn2012a.actions;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.opensymphony.xwork2.ActionSupport;

import rn2012a.persistance.User2file;

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

    private User2file user2file;

    public void setUser2file(User2file user2file)
    {
        this.user2file = user2file;
    }

    @Override
    public String execute() throws Exception
    {
        System.out.println("username:" + username + "||password:" + password);
        user2file.save(username, password);
        return SUCCESS;
    }

    private InputStream inputStream;

    public InputStream getResult() {
        return inputStream;
    }
    
    public String exist() throws UnsupportedEncodingException
    {
        inputStream = user2file.IsUsernameExisted(username) 
                ? new ByteArrayInputStream("1".getBytes("UTF-8"))
                : new ByteArrayInputStream("0".getBytes("UTF-8"));
        return SUCCESS;
    }
    
    
}
