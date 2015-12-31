package rn2012a.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class DataPackageMap {

	private static Logger logger = Logger.getLogger(DataPackageMap.class);
	
	private Map<Integer, DataPackage> map = new HashMap<Integer, DataPackage>();
	
	public void addDataPackage(Integer devId, DataPackage dataPackage) {
		logger.info("===DataPackageMap.addDataPackage():保存数据包到DataPackageMap--- devId=" + devId);
		this.map.put(devId, dataPackage);
	}
	
	public DataPackage getDataPackage(Integer devId) {
		logger.info("===DataPackageMap.getDataPackage():从DataPackageMap获取数据包--- devId=" + devId);
		return this.map.get(devId);
	}
	
	public void removeDataPackage(Integer devId) {
		logger.info("===DataPackageMap.removeDataPackage():删除设备" + devId + "对应的数据包。");
		this.map.remove(devId);
	}
}
