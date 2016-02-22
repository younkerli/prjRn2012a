package rn2012a.actions;

import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import rn2012a.service.SessionMap;

public class DevAddrAction extends ActionSupport
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private SessionMap sessionMap;

    public void setSessionMap(SessionMap sessionMap)
    {
        this.sessionMap = sessionMap;
    }

    private Integer[] devIds;

    public Integer[] getDevIds()
    {
        this.devIds = sessionMap.getDevIds();
        return devIds;
    }

    private AddressMap addrMap;

    public void setAddrMap(AddressMap addrMap)
    {
        this.addrMap = addrMap;
    }

    private String[] devAddrs;
    
    public String[] getDevAddrs()
    {
        addrMap.getAddrMap().values().toArray(devAddrs);
        return devAddrs;
    }

    @Override
    public String execute() throws Exception
    {

        return SUCCESS;
    }

}
