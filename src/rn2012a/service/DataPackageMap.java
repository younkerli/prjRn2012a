package rn2012a.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import rn2012a.entities.Event;

public class DataPackageMap
{

    private static Logger logger = Logger.getLogger(DataPackageMap.class);

    private Map<Integer, DataPackage> map = new HashMap<Integer, DataPackage>();

    public void addDataPackage(Integer devId, DataPackage dataPackage)
    {
        logger.info("===DataPackageMap.addDataPackage():保存数据包到DataPackageMap--- devId=" + devId);
        event.loadEvts(devId, dataPackage.getEventData());
        this.map.put(devId, dataPackage);
    }

    public DataPackage getDataPackage(Integer devId)
    {
//        logger.info("===DataPackageMap.getDataPackage():从DataPackageMap获取数据包--- devId=" + devId);
        DataPackage dp = this.map.get(devId);
        return dp;
    }

    public void removeDataPackage(Integer devId)
    {
        logger.info("===DataPackageMap.removeDataPackage():删除设备" + devId + "对应的数据包。");
        this.map.remove(devId);
    }

    public Integer getLength()
    {
        return map.size();
    }

    public String[] getAddrs()
    {
        String[] addrs = new String[map.size()];
        DataPackage[] packages = new DataPackage[map.size()];
        map.values().toArray(packages);
        for (int i = 0; i < packages.length; i++)
        {
            addrs[i] = packages[i].getAddrData().getAddress();
        }
        return addrs;
    }

    private Event event = new Event();

    public void evts2file()
    {
        for (Integer devid : map.keySet())
        {
//            event.setEvtdata(map.get(devid).getEventData());
            event.save(devid, map.get(devid).getEventData());
        }
    }

    public void loadEvts()
    {
        for (Integer devid : map.keySet())
        {
//            event.setEvtdata(map.get(devid).getEventData());
            event.loadEvts(devid, map.get(devid).getEventData());
        }
    }

    public boolean saveEvtsById(Integer devId)
    {
        if ( !map.keySet().contains(devId))
        {
            System.out.println("不存在的设备编号！");
            return false;
        } else {
//            event.setEvtdata(map.get(devId).getEventData());
            event.save(devId, map.get(devId).getEventData());
            return true;
        }
    }

    public String getAddrById(Integer integer)
    {
        return map.get(integer).getAddrData().getAddress();
    }
    
//    public void loadEvtsById(Integer devId)
//    {
//        event.setEvtdata(map.get(devId).getEventData());
//        if (map.get(devId).getEventData().isEmpty())
//        {
//            
//        }
//    }
    
    // public HashMap<Integer, String> getDevAddrs()
    // {
    // HashMap<Integer, String> addrMap = new HashMap<>();
    // for (Integer devId : map.keySet())
    // {
    // addrMap.put(devId, map.get(devId).getAddrData().getAddress());
    // }
    //
    // return addrMap;
    // }

    // public Integer[] getDevIds()
    // {
    // Integer[] devIds = new Integer[map.size()];
    // map.keySet().toArray(devIds);
    // return devIds;
    // }
}
