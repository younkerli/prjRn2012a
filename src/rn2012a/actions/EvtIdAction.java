package rn2012a.actions;

import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import rn2012a.dataPack.DataEvent;
import rn2012a.service.DataPackage;
import rn2012a.service.DataPackageMap;

public class EvtIdAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataPackageMap dataPackageMap;
	
	public void setDataPackageMap(DataPackageMap dataPackageMap) {
		this.dataPackageMap = dataPackageMap;
	}
	
	private DataPackage dataPackage;
	
	public DataPackage getDataPackage() {
		this.dataPackage = dataPackageMap.getDataPackage(devId);
		return dataPackage;
	}
	
	private int devId;
	
	public void setDevId(int devId) {
		this.devId = devId;
	}
	
	private Integer[] evtIds;
	
	public Integer[] getEvtIds() {
		List<DataEvent> events = dataPackageMap.getDataPackage(devId).getEventData();
		
		evtIds = new Integer[events.size()];
		for (int i = 0; i < evtIds.length; i++) {
			evtIds[i] = i + 1;
		}
		return evtIds;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

}
