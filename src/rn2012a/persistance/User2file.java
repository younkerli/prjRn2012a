package rn2012a.persistance;

import rn2012a.entities.User;

public class User2file
{
    private User userInstance;
    
    public void setUserInstance(User userInstance)
    {
        this.userInstance = userInstance;
    }
    
    public void save(String username, String password)
    {
        userInstance.save(username, password);
    }

    public boolean validation(String username, String password)
    {
        if (userInstance.IsUsersEmpty())
        {
            userInstance.loadUsers();
        }
        return userInstance.userValidation(username, password);
    }
    
    public boolean IsUsernameExisted(String username)
    {
        if (userInstance.IsUsersEmpty())
        {
            userInstance.loadUsers();
        }
        return userInstance.Exist(username);
    }
}
