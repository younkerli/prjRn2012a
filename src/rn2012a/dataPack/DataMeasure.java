package rn2012a.dataPack;

public class DataMeasure extends DataBox {

	private float Ua;
	private float Ub;
	private float Uc;
	private float Ubat;
	private float Ia;
	private float Ib;
	private float Ic;
	private float In;
	private float Frequency;
	private int meetCount;

	public float getUa() {
		return Ua;
	}

	public void setUa(int ua) {
		this.Ua = ua;
		this.Ua /= 100;
	}

	public float getUb() {
		return Ub;
	}

	public void setUb(int ub) {
		this.Ub = ub;
		this.Ub /= 100;
	}

	public float getUc() {
		return Uc;
	}

	public void setUc(int uc) {
		this.Uc = uc;
		this.Uc /= 100;
	}

	public float getUbat() {
		return Ubat;
	}

	public void setUbat(int ubat) {
		this.Ubat = ubat;
		this.Ubat /= 100;
	}

	public float getIa() {
		return Ia;
	}

	public void setIa(int ia) {
		this.Ia = ia;
		this.Ia /= 100;
	}

	public float getIb() {
		return Ib;
	}

	public void setIb(int ib) {
		this.Ib = ib;
		this.Ib /= 100;
	}

	public float getIc() {
		return Ic;
	}

	public void setIc(int ic) {
		this.Ic = ic;
		this.Ic /= 100;
	}

	public float getIn() {
		return In;
	}

	public void setIn(int in) {
		this.In = in;
		this.In /= 1000;
	}

	public float getFrequency() {
		return Frequency;
	}

	public void setFrequency(int frequency) {
		this.Frequency = frequency;
		this.Frequency /= 100;
	}

	public int getMeetCount() {
		return meetCount;
	}

	public void setMeetCount(int meetCount) {
		this.meetCount = meetCount;
	}

	@Override
	public int getLen() {
		return 4 * 8 + 2 + 2;
	}

	@Override
	public String toString() {
		return "DataMeasure [Ua=" + Ua + ", Ub=" + Ub + ", Uc=" + Uc + ", Ubat=" + Ubat + ", Ia=" + Ia + ", Ib=" + Ib
				+ ", Ic=" + Ic + ", In=" + In + ", Frequency=" + Frequency + ", meetCount=" + meetCount + "]";
	}

}
