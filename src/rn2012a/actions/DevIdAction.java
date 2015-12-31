package rn2012a.actions;

import com.opensymphony.xwork2.ActionSupport;

import rn2012a.service.DataPackage;
import rn2012a.service.DataPackageMap;
import rn2012a.service.SessionMap;

public class DevIdAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SessionMap sessionMap;

	public void setSessionMap(SessionMap sessionMap) {
		this.sessionMap = sessionMap;
	}

	private Integer[] devIds;

	public Integer[] getDevIds() {
		this.devIds = sessionMap.getDevIds();
		return devIds;
	}

	@Override
	public String execute() throws Exception {
		
		return SUCCESS;
	}

}
