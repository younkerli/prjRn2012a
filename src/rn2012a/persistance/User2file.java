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
        return userInstance.userValidation(username, password);
    }
    
}
