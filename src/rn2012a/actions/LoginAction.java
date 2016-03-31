package rn2012a.actions;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import rn2012a.entities.User;

public class LoginAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	private User2file userService;
//	
//	public void setUserService(User2file userService)
//    {
//        this.userService = userService;
//    }
//	
	private User userService;
	
	public void setUserService(User userService)
    {
        this.userService = userService;
    }
	
	@Override
	public String execute() throws Exception {
		System.out.println("Username:" + username + " Password:" + password);
		if (userService.IsUsersEmpty())
        {
            userService.loadUsers();
        }
		if (userService.userValidation(username, password))
        {
		    return SUCCESS;
        } else {
            ActionContext actionContext = ActionContext.getContext();
            Map<String, Object> sessionMap =  actionContext.getSession();
            sessionMap.put("msg", "登陆失败！");
            return "fail";
        }
		
	}

}
