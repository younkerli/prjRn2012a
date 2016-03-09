package rn2012a.persistance;

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
    
}
