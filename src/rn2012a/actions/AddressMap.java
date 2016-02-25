package rn2012a.actions;

import org.apache.log4j.Logger;

import rn2012a.service.DataPackageMap;
import rn2012a.service.ServerHandler;
import rn2012a.service.SessionMap;

public class AddressMap
{
    private static Logger logger = Logger.getLogger(AddressMap.class);

    // private Map<Integer, String> map = new HashMap<Integer, String>();

    private DataPackageMap dataPackageMap;

    public void setDataPackageMap(DataPackageMap dataPackageMap)
    {
        this.dataPackageMap = dataPackageMap;
    }

    public DataPackageMap getDataPackageMap()
    {
        return dataPackageMap;
    }

    private ServerHandler minaServer;

    public ServerHandler getMinaServer()
    {
        return minaServer;
    }

    public void setMinaServer(ServerHandler minaServer)
    {
        this.minaServer = minaServer;
    }

    private SessionMap sessionMap;

    public SessionMap getSessionMap()
    {
        return sessionMap;
    }

    public void setSessionMap(SessionMap sessionMap)
    {
        this.sessionMap = sessionMap;
    }

    public void setAddress(Integer devId, String address)
    {
        logger.info("===AddressMap.addAddress():保存设备地址到AddressMap--- devId=" + devId);
        this.dataPackageMap.getDataPackage(devId).getAddrData().setAddress(address);
        this.minaServer.sendMessage(devId, 13);
    }

    public String getAddress(Integer devId)
    {
        logger.info("===AddressMap.getAddress():从AddressMap中获取设备地址--- devId=" + devId);
        if (devId == null)
        {
            return null;
        }
        String address = this.dataPackageMap.getDataPackage(devId).getAddrData().getAddress();
        if (address == null)
        {
            this.minaServer.sendMessage(devId, 13);
            return null;
        } else {
            return address;
        }
    }

    public Integer[] getDevIds()
    {
        if (sessionMap == null)
        {
            return null;
        }
        return sessionMap.getDevIds();
    }

    public String[] getAddrs()
    {
        if (dataPackageMap == null)
        {
            return null;
        }
        return dataPackageMap.getAddrs(); 
        
    }
    
    public Integer getLength()
    {
        if (dataPackageMap == null)
        {
            return 0;
        } else {
            return dataPackageMap.getLength();
        }
    }

}
