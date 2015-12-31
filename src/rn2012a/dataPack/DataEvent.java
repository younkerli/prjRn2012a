package rn2012a.dataPack;

public class DataEvent extends DataBox {

	private TimeInfo tm;
	private int event;
	private float voltage;
	private String pharse;
	private int reserved;

	public TimeInfo getTm() {
		return tm;
	}

	public void setTm(TimeInfo tm) {
		this.tm = tm;
	}

	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		this.event = event;
	}

	public float getVoltage() {
		return voltage;
	}

	public void setVoltage(int voltage) {
		this.voltage = voltage;
		if (this.event == 14) {
			this.voltage /= 1000;
		} else {
			this.voltage /= 100;
		}
	}

	public String getPharse() {
		return pharse;
	}

	public void setPharse(String pharse) {
		this.pharse = pharse;
	}

	public int getReserved() {
		return reserved;
	}

	public void setReserved(int reserved) {
		this.reserved = reserved;
	}

	@Override
	public int getLen() {
		return tm.getLen() + 2 + 2 + 1 + 1;
	}

	@Override
	public String toString() {
		return "DataEvent [tm=" + tm + ", event=" + event + ", voltage=" + voltage + ", pharse=" + pharse
				+ ", reserved=" + reserved + "]";
	}
	
}
