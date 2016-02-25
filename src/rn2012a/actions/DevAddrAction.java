package rn2012a.actions;

import com.opensymphony.xwork2.ActionSupport;

public class DevAddrAction extends ActionSupport
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private AddressMap addrMap;

    public void setAddrMap(AddressMap addrMap)
    {
        this.addrMap = addrMap;
    }

    private String[] devAddrs;
    
    public String[] getDevAddrs()
    {
        devAddrs = addrMap.getAddrs();
        return devAddrs;
    }

    @Override
    public String execute() throws Exception
    {

        return SUCCESS;
    }

}
