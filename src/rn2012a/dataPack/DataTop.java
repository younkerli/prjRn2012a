package rn2012a.dataPack;

public class DataTop extends DataBox {

	private TimeInfo tm;
	private int switchState;
	private float ptValue;

	public TimeInfo getTm() {
		return tm;
	}

	public void setTm(TimeInfo tm) {
		this.tm = tm;
	}

	public int getSwitchState() {
		return switchState;
	}

	public void setSwitchState(int switchState) {
		this.switchState = switchState;
	}

	public float getPtValue() {
		return ptValue;
	}

	public void setPtValue(int ptValue) {
		this.ptValue = ptValue;
		this.ptValue /= 100;
	}

	@Override
	public int getLen() {
		return tm.getLen() + 2 + 2;
	}

	@Override
	public String toString() {
		return "DataTop [tm=" + tm + ", switchState=" + switchState + ", ptValue=" + ptValue + "]";
	}

}
