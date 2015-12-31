package rn2012a.actions;

import rn2012a.service.DataPackage;
import rn2012a.service.DataPackageMap;
import rn2012a.service.ServerHandler;

public class TestAction {

	private int devId;

	public void setDevId(int devId) {
		this.devId = devId;
	}

	private ServerHandler minaServer;

	public void setMinaServer(ServerHandler minaServer) {
		this.minaServer = minaServer;
	}

	private DataPackageMap dataPackageMap;
	
	public void setDataPackageMap(DataPackageMap dataPackageMap) {
		this.dataPackageMap = dataPackageMap;
	}
	
	private DataPackage dataPackage;
	
	public DataPackage getDataPackage() {
		this.dataPackage = dataPackageMap.getDataPackage(devId);
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
