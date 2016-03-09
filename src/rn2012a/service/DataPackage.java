package rn2012a.service;

import java.util.ArrayList;
import java.util.List;

import rn2012a.dataPack.DataAddr;
import rn2012a.dataPack.DataEvent;
import rn2012a.dataPack.DataLed;
import rn2012a.dataPack.DataMeasure;
import rn2012a.dataPack.DataTop;
import rn2012a.dataPack.DataUser;
import rn2012a.dataPack.DataValue;

public class DataPackage {

	private List<DataEvent> eventData = new ArrayList<DataEvent>();
	private DataMeasure measureData;
	private DataTop topData;
	private DataUser userData;
	private DataValue valueData;
	private DataLed ledData;
	private DataAddr addrData;

	public DataAddr getAddrData()
    {
	    if (addrData == null)
        {
	        addrData = new DataAddr();
        }
        return addrData;
    }

    public void setAddrData(DataAddr addrData)
    {
        if (addrData == null)
        {
            this.addrData = new DataAddr();
        } else {
            this.addrData = addrData;
        }
    }

    private boolean hasData = false;

	public boolean isHasData() {
		return hasData;
	}

	public List<DataEvent> getEventData() {
		return eventData;
	}

	public void setEventData(DataEvent eventData) {
		this.eventData.add(0, eventData);
		this.hasData = true;
	}

	public DataMeasure getMeasureData() {
		return measureData;
	}

	public void setMeasureData(DataMeasure measureData) {
		this.measureData = new DataMeasure();
		this.measureData = measureData;
		this.measureData.setEmpty(false);
		this.hasData = true;
	}

	public DataTop getTopData() {
		return topData;
	}

	public void setTopData(DataTop topData) {
		this.topData = new DataTop();
		this.topData = topData;
		this.topData.setEmpty(false);
		this.hasData = true;
	}

	public DataUser getUserData() {
		return userData;
	}

	public void setUserData(DataUser userData) {
		this.userData = new DataUser();
		this.userData = userData;
		this.userData.setEmpty(false);
		this.hasData = true;
	}

	public DataValue getValueData() {
		return valueData;
	}

	public void setValueData(DataValue valueData) {
		this.valueData = new DataValue();
		this.valueData = valueData;
		this.valueData.setEmpty(false);
		this.hasData = true;
	}

	public DataLed getLedData() {
		return ledData;
	}

	public void setLedData(DataLed ledData) {
		this.ledData = new DataLed();
		this.ledData = ledData;
		this.ledData.setEmpty(false);
		this.hasData = true;
	}

//	public Integer[] getEvtIds() {
//		Integer[] evtIds = new Integer[eventData.size()];
//		for (int i = 0; i < evtIds.length; i++) {
//			evtIds[i] = i + 1;
//		}
//		return evtIds;
//	}

}
