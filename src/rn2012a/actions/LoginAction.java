package rn2012a.actions;

import com.opensymphony.xwork2.ActionSupport;

import rn2012a.persistance.User2file;

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

	private User2file userService;
	
	public void setUserService(User2file userService)
    {
        this.userService = userService;
    }
	
	@Override
	public String execute() throws Exception {
		System.out.println("Username:" + username + " Password:" + password);
		if (userService.validation(username, password))
        {
		    return SUCCESS;
        }
		return "fail";
	}

}
