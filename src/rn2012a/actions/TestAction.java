package rn2012a.actions;

import rn2012a.service.DataPackage;

public class TestAction {

	private int devId;

	public void setDevId(int devId) {
	    Integer[] devIds = addressMap.getSessionMap().getDevIds();
		this.devId = devIds[devId];
	}

	private AddressMap addressMap;
	
	public void setAddressMap(AddressMap addressMap)
    {
        this.addressMap = addressMap;
    }
	
	private DataPackage dataPackage;
	
	public DataPackage getDataPackage() {
		this.dataPackage = addressMap.getDataPackageMap().getDataPackage(devId);
		if (dataPackage.getEventData().isEmpty())
        {
            addressMap.getDataPackageMap().loadEvtsById(devId);
        }
		return dataPackage;
	}

	public String execute() {

		addressMap.getMinaServer().sendMessage(devId, 11);

		return "success";
	}

}
