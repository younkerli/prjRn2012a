package rn2012a.persistance;

import java.util.List;

import rn2012a.dataPack.DataEvent;
import rn2012a.service.DataPackageMap;

public class Event2file
{
    private DataPackageMap dataPackageMap;

    public void setDataPackageMap(DataPackageMap dataPackageMap)
    {
        this.dataPackageMap = dataPackageMap;
    }
    
    public void save()
    {
        dataPackageMap.evts2file();
    }
    
    public void load()
    {
        dataPackageMap.loadEvts();
    }
    
    public List<DataEvent> getEventData(int devId)
    {
        return dataPackageMap.getDataPackage(devId).getEventData();
    }
    
}
