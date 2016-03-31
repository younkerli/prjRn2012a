package rn2012a.actions;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import rn2012a.dataPack.DataEvent;
import rn2012a.service.DataPackageMap;
import rn2012a.service.SessionMap;

public class EvtIdAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DataPackageMap dataPackageMap;
	
	public void setDataPackageMap(DataPackageMap dataPackageMap) {
		this.dataPackageMap = dataPackageMap;
	}
	
//	private Event2file event2file;
//	
//	public void setEvent2file(Event2file event2file)
//    {
//        this.event2file = event2file;
//    }
	
//	private DataPackage dataPackage;
//	
//	public DataPackage getDataPackage() {
//		this.dataPackage = dataPackageMap.getDataPackage(devId);
//		return dataPackage;
//	}
	
	private SessionMap sessionMap;
	
	public void setSessionMap(SessionMap sessionMap)
    {
        this.sessionMap = sessionMap;
    }
	
	private int devId;
	
	public void setDevId(int devId)
    {
        this.devId = devId;
    }
//	private Integer[] evtIds;
//	
//	public Integer[] getEvtIds() {
//		List<DataEvent> events = dataPackageMap.getDataPackage(devId).getEventData();
//		
//		evtIds = new Integer[events.size()];
//		for (int i = 0; i < evtIds.length; i++) {
//			evtIds[i] = i + 1;
//		}
//		return evtIds;
//	}
//	
	private String[] evtTms;
	
	public String[] getEvtTms() {
	    Integer[] devIds = sessionMap.getDevIds();
        this.devId = devIds[devId];
	    List<DataEvent> events = dataPackageMap.getDataPackage(devId).getEventData();
	    evtTms = new String[events.size()];
	    for (int i = 0; i < evtTms.length; i++)
        {
	        evtTms[i] = events.get(i).getTm().strDateInfo();
        }
	    return evtTms;
	}
	
	@Override
	public String execute() throws Exception {
		return SUCCESS;
	}

}
