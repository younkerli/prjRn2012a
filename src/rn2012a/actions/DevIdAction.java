package rn2012a.actions;

import com.opensymphony.xwork2.ActionSupport;

public class DevIdAction extends ActionSupport
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private AddressMap addressMap;

    public AddressMap getAddressMap()
    {
        return addressMap;
    }

    public void setAddressMap(AddressMap addressMap)
    {
        this.addressMap = addressMap;
    }

    private Integer[] devIds;

    public Integer[] getDevIds()
    {
        // if (addressMap.getAddrMap() == null)
        // {
        // return null;
        // }
        // addressMap.getAddrMap().keySet().toArray(devIds);
        this.devIds = addressMap.getSessionMap().getDevIds();
        return devIds;
    }

    // private String[] devAddrs;
    //
    // public String[] getDevAddrs()
    // {
    // addressMap.getAddrMap().values().toArray(devAddrs);
    // return devAddrs;
    // }

    @Override
    public String execute() throws Exception
    {
        return SUCCESS;
    }

    private Integer devId;

    public void setDevId(Integer devId)
    {
        this.devId = devId;
    }

    private String devAddr;

    public String getDevAddr()
    {
        return devAddr;
    }

    public String show()
    {
        String addr = addressMap.getAddress(devId);
        if (addr != null)
        {
            devAddr = addr;
        }
        id = devId;
        return SUCCESS;
    }

    private Integer id;

    private String addr;

//    public Integer getId()
//    {
//        return id;
//    }

    public String getAddr()
    {
        return addr;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setAddr(String addr)
    {
        this.addr = addr;
    }

    public String save()
    {
        boolean unModified = addr.equals(addressMap.getAddress(id));
        
        if (!unModified)
        {
            addressMap.setAddress(id, addr);

        }
        return SUCCESS;
    }

}
