package rn2012a.actions;

import rn2012a.service.DataPackage;
import rn2012a.service.ServerHandler;

public class TestAction {

	private int devId;

	public void setDevId(int devId) {
	    Integer[] devIds = new Integer[addressMap.getLength()];
	    addressMap.getAddrMap().keySet().toArray(devIds);
		this.devId = devIds[devId];
	}

	private ServerHandler minaServer;

	public void setMinaServer(ServerHandler minaServer) {
		this.minaServer = minaServer;
	}

//	private DataPackageMap dataPackageMap;
//	
//	public void setDataPackageMap(DataPackageMap dataPackageMap) {
//		this.dataPackageMap = dataPackageMap;
//	}
	
	private AddressMap addressMap;
	
	public void setAddressMap(AddressMap addressMap)
    {
        this.addressMap = addressMap;
    }
	
	private DataPackage dataPackage;
	
	public DataPackage getDataPackage() {
		this.dataPackage = addressMap.getDataPackageMap().getDataPackage(devId);
		return dataPackage;
	}

//	public void setDataPackage(DataPackage dataPackage) {
//		this.dataPackage = dataPackage;
//	}

	public String execute() {

		minaServer.sendMessage(devId, 11);

		return "success";
	}

}
